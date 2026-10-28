package com.example.hotel;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class ListaController {

    @FXML private TableView<Hotel> tabelaClientes;
    @FXML private TableColumn<Hotel, String> colNome;
    @FXML private TableColumn<Hotel, String> colCpf;
    @FXML private TableColumn<Hotel, String> colTelefone;
    @FXML private TableColumn<Hotel, String> colEmail;
    @FXML private TableColumn<Hotel, Integer> colQuarto;
    @FXML private TableColumn<Hotel, Integer> colDias;

    @FXML
    public void initialize() {
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        colTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colQuarto.setCellValueFactory(new PropertyValueFactory<>("quarto"));
        colDias.setCellValueFactory(new PropertyValueFactory<>("diasHospedagem"));

        tabelaClientes.setItems(HotelRepository.getClientes());
    }

    @FXML
    public void onExcluir() {
        Hotel selecionado = tabelaClientes.getSelectionModel().getSelectedItem();
        if (selecionado != null) {
            HotelRepository.remover(selecionado);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atenção");
            alert.setHeaderText(null);
            alert.setContentText("Selecione um cliente na tabela para excluir.");
            alert.showAndWait();
        }
    }

    @FXML
    public void onVoltar() {
        try {
            Stage stage = (Stage) tabelaClientes.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("cadastro-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 450);
            stage.setTitle("Cadastro de Clientes");
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}