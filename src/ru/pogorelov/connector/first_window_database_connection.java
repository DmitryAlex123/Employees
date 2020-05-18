package ru.pogorelov.connector;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.pogorelov.controller.department_controller;
import ru.pogorelov.model.P_D_join_data;
import ru.pogorelov.model.department_data;

import java.sql.*;
import java.util.Properties;

public class first_window_database_connection {

    public static ObservableList<P_D_join_data> getData() {

        ObservableList<P_D_join_data> pattern_list = FXCollections.observableArrayList();

        try {
            Class.forName("org.firebirdsql.jdbc.FBDriver");
            try {
                Connection connection = MAIN_CONNECT_DATA.getPool().getConnection();
                Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery("SELECT departments.id, departments.name, personal.name, posit.name, posit.profit from personal RIGHT JOIN departments_personal ON personal.id = departments_personal.id_personal LEFT JOIN departments ON departments_personal.id_department = departments.ID LEFT JOIN personal_position ON personal.id = personal_position.id_personal LEFT JOIN posit ON PERSONAL_POSITION.id_position = posit.id");
                while (result.next()) {
                    Integer id = Integer.parseInt(result.getString(1));
                    String name_department = result.getString(2);
                    String name = result.getString(3);
                    String name_position = result.getString(4);
                    Integer profit = Integer.parseInt(result.getString(5));
                    P_D_join_data pddJD = new P_D_join_data(id, name_department, name, name_position, profit);
                    pattern_list.add(pddJD);
                }
                connection.close();
            }catch(Exception ex){
                System.out.println("Проблема с установлением соединения " + ex);
            }

        }catch (Exception ex) {
            System.out.println("Проблема с драйвером " + ex);
        }

        return pattern_list;
    }

    public static ObservableList<P_D_join_data> getSortedData(int idToQuery) {

        ObservableList<P_D_join_data> pattern_list = FXCollections.observableArrayList();

        try {
            Class.forName("org.firebirdsql.jdbc.FBDriver");
            try {
                Connection connection = MAIN_CONNECT_DATA.getPool().getConnection();
                Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery("SELECT departments.id, departments.name, personal.name, posit.name, posit.profit from personal RIGHT JOIN departments_personal ON personal.id = departments_personal.id_personal LEFT JOIN departments ON departments_personal.id_department = departments.ID LEFT JOIN personal_position ON personal.id = personal_position.id_personal LEFT JOIN posit ON PERSONAL_POSITION.id_position = posit.id WHERE departments.id = " + idToQuery);
                while (result.next()) {
                    Integer id = Integer.parseInt(result.getString(1));
                    String name_department = result.getString(2);
                    String name = result.getString(3);
                    String name_position = result.getString(4);
                    Integer profit = Integer.parseInt(result.getString(5));
                    P_D_join_data pddJD = new P_D_join_data(id, name_department, name, name_position, profit);
                    pattern_list.add(pddJD);

                }
                connection.close();
            }catch(Exception ex){
                System.out.println("Проблема с установлением соединения " + ex);
            }

        }catch (Exception ex) {
            System.out.println("Проблема с драйвером " + ex);
        }

        return pattern_list;
    }

}
