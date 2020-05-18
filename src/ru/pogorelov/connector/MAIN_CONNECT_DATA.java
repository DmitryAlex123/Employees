package ru.pogorelov.connector;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class MAIN_CONNECT_DATA {
    //старый вариант - не используется
    public static Connection getConnection() throws SQLException {
        Properties props = new Properties();
        props.setProperty("user", "SYSDBA");
        props.setProperty("password", "masterkey");
        props.setProperty("encoding", "UTF8");
        return DriverManager.getConnection("jdbc:firebirdsql://localhost:3050/c:/Users/pdai/Desktop/Java/maven/source/Employees/local_DB/EMPLOYEES.FDB", props);
    }

    //новый вариант: пул подключений
    private static ComboPooledDataSource cpds = new ComboPooledDataSource();

    public static ComboPooledDataSource getPool(){
        return cpds;
    }

    public static void setPoolSettings(){
            try {
            cpds.setDriverClass("org.firebirdsql.jdbc.FBDriver");
            cpds.setJdbcUrl    ("jdbc:firebirdsql://localhost:3050/c:/Users/pdai/Desktop/Java/maven/source/Employees/local_DB/EMPLOYEES.FDB");
            cpds.setUser       ("SYSDBA"   );
            cpds.setPassword   ("masterkey");

            Properties properties = new Properties();
            properties.setProperty ("user"             , "SYSDBA"   );
            properties.setProperty ("password"         , "masterkey");
            properties.setProperty ("useUnicode"       , "true"      );
            properties.setProperty ("characterEncoding", "UTF8"      );
            properties.setProperty ("lc_ctype", "UTF8"      );

            cpds.setProperties(properties);

            // set options
            cpds.setMaxStatements             (180);
            cpds.setMaxStatementsPerConnection(180);
            cpds.setMinPoolSize               ( 50);
            cpds.setAcquireIncrement          ( 10);
            cpds.setMaxPoolSize               ( 60);
            cpds.setMaxIdleTime               ( 30);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
    }

}
