package ru.pogorelov.connector;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.pogorelov.controller.department_controller;
import ru.pogorelov.model.P_D_join_data;
import ru.pogorelov.model.department_data;
import ru.pogorelov.model.personal_data;

import java.sql.*;
import java.util.Properties;

public class personals_database_connection {

    public static ObservableList<P_D_join_data> getJOIN__P_D(){

        ObservableList<P_D_join_data> pattern_list = FXCollections.observableArrayList();

        try {
            Class.forName("org.firebirdsql.jdbc.FBDriver");
            try {
                Connection connection = MAIN_CONNECT_DATA.getPool().getConnection();
                Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery("SELECT PERSONAL.id, PERSONAL.name, departments.name, posit.name from personal LEFT JOIN departments_personal ON personal.id = departments_personal.id_personal LEFT JOIN departments ON departments_personal.id_department = departments.ID left JOIN personal_position ON personal.id = personal_position.id_personal left JOIN posit ON PERSONAL_POSITION.id_position = posit.id");

                while (result.next()) {
                    int id = Integer.parseInt(result.getString(1));
                    String name = result.getString(2);
                    String department = result.getString(3);
                    String position = result.getString(4);
                    P_D_join_data object = new P_D_join_data(id, name, department, position);
                    pattern_list.add(object);
                }
                statement.close();
                result.close();
                connection.close();
            }catch(Exception ex){
                System.out.println("Проблема с установлением соединения " + ex);
            }

        }catch (Exception ex) {
            System.out.println("Проблема с драйвером " + ex);
        }

        return pattern_list;

    }

    private static Integer max_id;
    private static int getOverMaxId(){
        return max_id + 1;
    }
    public static void insertNewPerson(String name){

        try {
            Class.forName("org.firebirdsql.jdbc.FBDriver");
            try {
                Connection connection = MAIN_CONNECT_DATA.getPool().getConnection();

                Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery("select MAX(personal.id) from PERSONAL");
                while (result.next()) {
                    max_id = Integer.parseInt(result.getString(1));
                }
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO PERSONAL VALUES (?, ?)");
                preparedStatement.setInt(1, personals_database_connection.getOverMaxId());
                preparedStatement.setString(2, name);
                preparedStatement.executeUpdate();
                statement.close();
                result.close();
                connection.close();
            }catch(Exception ex){
                System.out.println("Проблема с установлением соединения " + ex);
            }

        }catch (Exception ex) {
            System.out.println("Проблема с драйвером " + ex);
        }
    }

    public static boolean updateField(int id, String name){
        try {
            Class.forName("org.firebirdsql.jdbc.FBDriver");
            try {
                Connection connection = MAIN_CONNECT_DATA.getPool().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE PERSONAL SET name = ? WHERE id = ?");
                preparedStatement.setInt(2, id);
                preparedStatement.setString(1, name);
                preparedStatement.executeUpdate();

                preparedStatement.close();
                connection.close();
                return true;
            }catch(Exception ex){
                System.out.println("Проблема с установлением соединения или запросом " + ex);
                return false;
            }

        }catch (Exception ex) {
            System.out.println("Проблема с драйвером " + ex);
            return false;
        }
    }

    public static boolean deletePersonal(int presonal_id){
        try {
            Class.forName("org.firebirdsql.jdbc.FBDriver");
            try {
                Connection connection = MAIN_CONNECT_DATA.getPool().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM PERSONAL WHERE id = ?");
                preparedStatement.setInt(1, presonal_id);
                preparedStatement.executeUpdate();

                PreparedStatement Delete_preparedStatement = connection.prepareStatement("DELETE FROM DEPARTMENTS_PERSONAL WHERE ID_PERSONAL = ?");
                Delete_preparedStatement.setInt(1, presonal_id);
                Delete_preparedStatement.executeUpdate();

                PreparedStatement Delete2_preparedStatement = connection.prepareStatement("DELETE FROM PERSONAL_POSITION WHERE ID_PERSONAL = ?");
                Delete2_preparedStatement.setInt(1, presonal_id);
                Delete2_preparedStatement.executeUpdate();

                preparedStatement.close();
                Delete_preparedStatement.close();
                Delete2_preparedStatement.close();
                connection.close();
                return true;
            }catch(Exception ex){
                System.out.println("Проблема с установлением соединения или запросом " + ex);
                return false;
            }

        }catch (Exception ex) {
            System.out.println("Проблема с драйвером " + ex);
            return false;
        }
    }
}
