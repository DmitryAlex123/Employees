package ru.pogorelov.connector;

import com.sun.corba.se.impl.resolver.SplitLocalResolverImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.pogorelov.model.department_data;
import ru.pogorelov.controller.department_controller;
import java.sql.*;
import java.util.Properties;

public class department_database_connection {

    public static ObservableList<department_data> getDepartmentData() {

        ObservableList<department_data> pattern_list = FXCollections.observableArrayList();

        try {
            Class.forName("org.firebirdsql.jdbc.FBDriver");
            try {
                Connection connection = MAIN_CONNECT_DATA.getPool().getConnection();
                Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery("SELECT * FROM DEPARTMENTS");

                while (result.next()) {
                    int id = Integer.parseInt(result.getString(1));
                    String name = result.getString(2);
                    String phone = result.getString(3);
                    String email = result.getString(4);
                    department_data object = new department_data(id, name, phone, email);
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

    public static boolean addDepartment(department_data department_data){

        try {
            Class.forName("org.firebirdsql.jdbc.FBDriver");
            try {
                Connection connection = MAIN_CONNECT_DATA.getPool().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO DEPARTMENTS VALUES ( ?,?,?,?)");
                preparedStatement.setInt(1, department_controller.getIdForNewDepartment());
                preparedStatement.setString(2, department_data.getName());
                preparedStatement.setString(3, department_data.getPhone());
                preparedStatement.setString(4, department_data.getEmail());
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

    public static boolean deleteDepartment(int id){
        try {
            Class.forName("org.firebirdsql.jdbc.FBDriver");
            try {
                Connection connection = MAIN_CONNECT_DATA.getPool().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM DEPARTMENTS WHERE id = ?");
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();

                PreparedStatement Delete_preparedStatement = connection.prepareStatement("DELETE FROM DEPARTMENTS_PERSONAL WHERE ID_DEPARTMENT = ?");
                Delete_preparedStatement.setInt(1, id);
                Delete_preparedStatement.executeUpdate();

                Delete_preparedStatement.close();
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


    public static boolean update_department(int department_id, String name, String phone, String email){
        try {
            Class.forName("org.firebirdsql.jdbc.FBDriver");
            try {
                Connection connection = MAIN_CONNECT_DATA.getPool().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("update departments SET departments.name = ?, departments.phone = ?, departments.email = ? WHERE departments.id = ?;");
                preparedStatement.setInt(4, department_id);
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, phone);
                preparedStatement.setString(3, email);
                preparedStatement.executeUpdate();
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
    static Integer max_id;
    public static int getMaxID(){

        try {
            Class.forName("org.firebirdsql.jdbc.FBDriver");
            try {
                Connection connection = MAIN_CONNECT_DATA.getPool().getConnection();
                Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery("select MAX(departments.id) from departments");
                while (result.next()) {
                    max_id = Integer.parseInt(result.getString(1));
                }
                statement.close();
                result.close();
                connection.close();
            }catch(Exception ex){
                System.out.println("Проблема с установлением соединения или запросом " + ex);
            }

        }catch (Exception ex) {
            System.out.println("Проблема с драйвером " + ex);
        }
        return max_id;
    }

}
