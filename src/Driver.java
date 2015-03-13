import network.query.MySQLAccess;
import network.util.ConnectionConfiguration;

import java.sql.Connection;
import java.sql.ResultSet;
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


        //String message1 = MySQLAccess.register("michal", "email@email.com");
        //String message = MySQLAccess.deRegister("michal");
        //String message2 = MySQLAccess.postProfile("test","montreal","hockey");
        //ResultSet tester = MySQLAccess.showPublishers();
        //String message = MySQLAccess.deRegister("test");
       // MySQLAccess.register("test1", "test@test.com");
        //MySQLAccess.postTwibble("michel","fgfg");
        //String follow = MySQLAccess.subscribe("michal", "test");
        //String delete =MySQLAccess.deleteTwibble("michal", 1);
        //String error = MySQLAccess.unSubscribe("michal", "test");
        //String update = MySQLAccess.updateProfile("michal", "allo", "wut");
        String login = MySQLAccess.login("michal");
       System.out.println(login);

    }
}
