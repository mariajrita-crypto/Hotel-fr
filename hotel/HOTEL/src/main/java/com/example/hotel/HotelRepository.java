package com.example.hotel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class HotelRepository {
    private static final ObservableList<Hotel> clientes = FXCollections.observableArrayList();

    public static ObservableList<Hotel> getClientes() {
        return clientes;
    }

    public static void adicionar(Hotel cliente) {
        clientes.add(cliente);
    }

    public static void remover(Hotel cliente) {
        clientes.remove(cliente);
    }
}