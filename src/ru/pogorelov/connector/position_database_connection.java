package ru.pogorelov.connector;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.pogorelov.model.department_data;
import ru.pogorelov.model.position_data;

import java.sql.*;
import java.util.Properties;

public class position_database_connection {

    public static ObservableList<position_data> getPositionData() {

        ObservableList<position_data> pattern_list = FXCollections.observableArrayList();

        try {
            Class.forName("org.firebirdsql.jdbc.FBDriver");
            try {
                Connection connection = MAIN_CONNECT_DATA.getPool().getConnection();
                Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery("SELECT * FROM POSIT");

                while (result.next()) {
                    int id = Integer.parseInt(result.getString(1));
                    String name = result.getString(2);
                    int profit = Integer.parseInt(result.getString(3));
                    position_data object = new position_data(id, name, profit);
                    pattern_list.add(object);
                }
                connection.close();
            }catch(Exception ex){
                System.out.println("Проблема с запросом " + ex);
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
    public static void insertNewPosition(String name, int profit){

        try {
            Class.forName("org.firebirdsql.jdbc.FBDriver");
            try {
                Connection connection = MAIN_CONNECT_DATA.getPool().getConnection();

                Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery("select MAX(posit.id) from POSIT");
                while (result.next()) {
                    max_id = Integer.parseInt(result.getString(1));
                }


                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO POSIT VALUES (?, ?, ?)");
                preparedStatement.setInt(1, position_database_connection.getOverMaxId());
                preparedStatement.setString(2, name);
                preparedStatement.setInt(3, profit);
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

    public static boolean updateField(int id, String name, int profit){
        try {
            Class.forName("org.firebirdsql.jdbc.FBDriver");
            try {
                Connection connection = MAIN_CONNECT_DATA.getPool().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE POSIT SET name = ?, profit = ? WHERE id = ?");
                preparedStatement.setInt(3, id);
                preparedStatement.setString(1, name);
                preparedStatement.setInt(2, profit);
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

    public static boolean deletePosition(int position_id){
        try {
            Class.forName("org.firebirdsql.jdbc.FBDriver");
            try {
                Connection connection = MAIN_CONNECT_DATA.getPool().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM POSIT WHERE id = ?");
                preparedStatement.setInt(1, position_id);
                preparedStatement.executeUpdate();

                PreparedStatement Delete_preparedStatement = connection.prepareStatement("DELETE FROM PERSONAL_POSITION WHERE ID_POSITION = ?");
                Delete_preparedStatement.setInt(1, position_id);
                Delete_preparedStatement.executeUpdate();
                Delete_preparedStatement.close();
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
