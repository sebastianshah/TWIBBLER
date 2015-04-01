package network.util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * CONFIG FILE for mysql database connection
 */
public class SQLConnectionConfiguration {

    //configuration setting
    private String username = "root";
    private String password = "";
    private String table ="twibblerdata";

    public  Connection getConnection()
    {
        Connection connection = null;

        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+table,username,password);

        }catch(Exception e){
            e.printStackTrace();
        }

        return connection;


    }

}
