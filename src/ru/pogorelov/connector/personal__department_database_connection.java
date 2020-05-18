package ru.pogorelov.connector;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.pogorelov.controller.department_controller;
import ru.pogorelov.model.personal_data;

import java.sql.*;
import java.util.Properties;

public class personal__department_database_connection {


    public static boolean setPersonal_Department(int personal_id, int department_id) {

        try {
            Class.forName("org.firebirdsql.jdbc.FBDriver");
            try {
                Connection connection = MAIN_CONNECT_DATA.getPool().getConnection();
                PreparedStatement Delete_preparedStatement = connection.prepareStatement("DELETE FROM DEPARTMENTS_PERSONAL WHERE ID_PERSONAL = ?");
                Delete_preparedStatement.setInt(1, personal_id);
                Delete_preparedStatement.executeUpdate();
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO DEPARTMENTS_PERSONAL VALUES (?,?)");
                preparedStatement.setInt(1, department_id);
                preparedStatement.setInt(2, personal_id);
                preparedStatement.executeUpdate();
                connection.close();
                return true;
            }catch(Exception ex){
                System.out.println("Проблема с запросом " + ex);
                return false;
            }

        }catch (Exception ex) {
            System.out.println("Проблема с драйвером " + ex);
            return false;
        }


    }

}
