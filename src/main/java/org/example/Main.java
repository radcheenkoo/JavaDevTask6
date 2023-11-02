package org.example;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Connection connection = Database.getInstance().getConnection();
        ClientService clientService = new ClientService(connection);

        System.out.println(clientService.create("Dima"));
        System.out.println(clientService.getById(1));
        System.out.println(clientService.create("Peter"));
        clientService.setName(2, "Vasya");
        System.out.println(clientService.getById(5));
        System.out.println(clientService.getById(4));
        clientService.deleteById(2);

        List<Client> clientList = clientService.listAll();

        for (Client c: clientList) {
            System.out.println("Id: " + c.getId() + " Name: " + c.getName());
        }

    }
}