package network.util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by michal on 3/9/2015.
 */
public class ConnectionConfiguration {

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
