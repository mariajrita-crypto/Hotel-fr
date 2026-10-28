package com.example.hotel;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class CadastroController {

    @FXML private TextField txtNome;
    @FXML private TextField txtCpf;
    @FXML private TextField txtTelefone;
    @FXML private TextField txtEmail;
    @FXML private TextField txtQuarto;
    @FXML private TextField txtDias;

    @FXML
    public void initialize() {
        // Limita o campo CPF para aceitar no máximo 11 dígitos numéricos
        aplicarMascaraNumerica(txtCpf, 11);

        // Limita o campo Telefone para aceitar no máximo 11 dígitos numéricos
        aplicarMascaraNumerica(txtTelefone, 11);

        // Limita Quarto e Dias para aceitar apenas números (máx 5 dígitos)
        aplicarMascaraNumerica(txtQuarto, 5);
        aplicarMascaraNumerica(txtDias, 4);
    }

    @FXML
    public void onSalvar() {
        String nome = txtNome.getText().trim();
        String cpf = txtCpf.getText().replaceAll("\\D", ""); // Remove não dígitos
        String telefone = txtTelefone.getText().replaceAll("\\D", "");
        String email = txtEmail.getText().trim();
        String quartoStr = txtQuarto.getText().trim();
        String diasStr = txtDias.getText().trim();

        // 1. Validação de campos vazios
        if (nome.isEmpty() || cpf.isEmpty() || telefone.isEmpty() || email.isEmpty() || quartoStr.isEmpty() || diasStr.isEmpty()) {
            exibirAlerta(Alert.AlertType.WARNING, "Campos Incompletos", "Por favor, preencha todos os campos.");
            return;
        }

        // 2. Validação do CPF (Exatamente 11 dígitos)
        if (cpf.length() != 11) {
            exibirAlerta(Alert.AlertType.ERROR, "CPF Inválido", "O CPF deve conter exatamente 11 números.");
            return;
        }

        // 3. Validação do Telefone (10 ou 11 dígitos com DDD)
        if (telefone.length() < 10 || telefone.length() > 11) {
            exibirAlerta(Alert.AlertType.ERROR, "Telefone Inválido", "O telefone deve conter 10 ou 11 números (incluindo o DDD).");
            return;
        }

        // 4. Validação básica de E-mail
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            exibirAlerta(Alert.AlertType.ERROR, "E-mail Inválido", "Insira um endereço de e-mail válido (ex: nome@email.com).");
            return;
        }

        try {
            int quarto = Integer.parseInt(quartoStr);
            int dias = Integer.parseInt(diasStr);

            if (quarto <= 0 || dias <= 0) {
                exibirAlerta(Alert.AlertType.WARNING, "Valores Inválidos", "O número do quarto e dos dias deve ser maior que zero.");
                return;
            }

            // Formatação do CPF para exibição bonita (000.000.000-00)
            String cpfFormatado = cpf.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");

            // Formatação do Telefone ((00) 00000-0000 ou (00) 0000-0000)
            String telFormatado = telefone.length() == 11
                    ? telefone.replaceAll("(\\d{2})(\\d{5})(\\d{4})", "($1) $2-$3")
                    : telefone.replaceAll("(\\d{2})(\\d{4})(\\d{4})", "($1) $2-$3");

            Hotel cliente = new Hotel(nome, cpfFormatado, telFormatado, email, quarto, dias);
            HotelRepository.adicionar(cliente);

            exibirAlerta(Alert.AlertType.INFORMATION, "Sucesso", "Hóspede cadastrado com sucesso!");
            onLimpar();

        } catch (NumberFormatException e) {
            exibirAlerta(Alert.AlertType.ERROR, "Erro Numérico", "Quarto e Dias devem conter apenas números.");
        }
    }

    @FXML
    public void onLimpar() {
        txtNome.clear();
        txtCpf.clear();
        txtTelefone.clear();
        txtEmail.clear();
        txtQuarto.clear();
        txtDias.clear();
    }

    @FXML
    public void onVerLista() {
        try {
            Stage stage = (Stage) txtNome.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("lista-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 820, 560);
            stage.setTitle("Grand Hotel - Hóspedes Cadastrados");
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
            exibirAlerta(Alert.AlertType.ERROR, "Erro", "Erro ao carregar a lista de hóspedes.");
        }
    }

    /**
     * Função auxiliar para permitir apenas dígitos e limitar o número máximo de caracteres enquanto digita
     */
    private void aplicarMascaraNumerica(TextField textField, int maxCaracteres) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) return;
            // Remove qualquer caractere que não seja número
            String apenasNumeros = newValue.replaceAll("\\D", "");
            if (apenasNumeros.length() > maxCaracteres) {
                apenasNumeros = apenasNumeros.substring(0, maxCaracteres);
            }
            textField.setText(apenasNumeros);
        });
    }

    private void exibirAlerta(Alert.AlertType tipo, String titulo, String mensagem) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}