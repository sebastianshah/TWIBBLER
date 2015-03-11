import network.query.MySQLAccess;
import network.util.ConnectionConfiguration;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by michal on 3/9/2015.
 */
public class Driver
{
    public static void main(String[] args){
        System.out.println("hello");

        ConnectionConfiguration connect = new ConnectionConfiguration();
        Connection connection = null;

        try{
            connection = connect.getConnection();
            if(connection !=null)
            {
                System.out.println("Connection work!!!");
            }


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(connection !=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }


        MySQLAccess con = new MySQLAccess();
        con.insert();

    }
}
