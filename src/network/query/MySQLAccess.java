package network.query;

import network.util.ConnectionConfiguration;

import java.sql.*;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author michal wozniak
 * @version 1.0
 */
public class MySQLAccess {
    public void insert()
    {
        Connection connection =null;
        PreparedStatement preparedStatement = null;
        ConnectionConfiguration connect = new ConnectionConfiguration();


        try{
            connection = connect.getConnection();
            preparedStatement =  connection.prepareStatement("INSERT INTO users (id, username)" + "VALUES (?,?)");
            preparedStatement.setString(1,"1");
            preparedStatement.setString(2,"Michal");
            preparedStatement.executeUpdate();


        }catch (Exception e)
        {
            e.printStackTrace();
        }finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
