package ru.pogorelov.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class personal__position_connection {

    public static boolean setPersonal_Position_to_DB(int personal_id, int position_id) {

        try {
            Class.forName("org.firebirdsql.jdbc.FBDriver");
            try {
                Connection connection = MAIN_CONNECT_DATA.getPool().getConnection();
                PreparedStatement Delete_preparedStatement = connection.prepareStatement("DELETE FROM PERSONAL_POSITION WHERE ID_PERSONAL = ?");
                Delete_preparedStatement.setInt(1, personal_id);
                Delete_preparedStatement.executeUpdate();
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO PERSONAL_POSITION VALUES (?,?)");
                preparedStatement.setInt(1, personal_id);
                preparedStatement.setInt(2, position_id);
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
