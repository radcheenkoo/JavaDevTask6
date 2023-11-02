package org.example;

import java.lang.ref.PhantomReference;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientService {
    private static final String INSERT_NAME = "INSERT INTO client (name) VALUES(?)";
    private static final String SELECT_NAME_BY_ID = "SELECT name FROM client WHERE id = ?";
    private static final String UPDATE_CLIENT_NAME_BY_ID = "UPDATE client SET name = ? WHERE id = ?";
    private static final String DELETE_CLIENT_BY_ID = "DELETE FROM client WHERE id = ?";
    private static final String SELECT_ALL_CLIENT = "SELECT id, name FROM client";

    Connection connection;

    public ClientService(Connection connection){
        this.connection = connection;
    }

    public long create(String name){

        name = name.trim();

        if (name == null || name.isEmpty() || name.length() < 2 || name.length() > 20) {
            throw new IllegalArgumentException("Ім'я не відповідає вимогам");
        }

        long id = -1;
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NAME, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {


                if (generatedKeys.next()) {
                    id = generatedKeys.getLong(1);
                }
            }

            return id;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getById(long id){
        String name = "";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_NAME_BY_ID);
            preparedStatement.setLong(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                name = resultSet.getString("name");
            }
            return name;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setName(long id, String name){

        name = name.trim();

        if (name == null || name.isEmpty() || name.length() < 2 || name.length() > 20) {
            throw new IllegalArgumentException("Ім'я не відповідає вимогам");
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CLIENT_NAME_BY_ID)){

            preparedStatement.setString(1, name);
            preparedStatement.setLong(2,id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteById(long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CLIENT_BY_ID)) {
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Client> listAll(){
        List<Client> clientList = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CLIENT);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                Client client = new Client(id,name);

                clientList.add(client);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return clientList;
    }
}
