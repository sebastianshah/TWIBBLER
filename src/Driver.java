
import network.query.MySQLAccess;
import network.util.SQLConnectionConfiguration;
import network.util.Email;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
/**
 * FOR TESTING PURPOSE ONLY
 * this driver is used to test different section of our application
 */
public class Driver  {

     public static void main(String[] args)
     {
         SQLConnectionConfiguration connect = new SQLConnectionConfiguration();
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


         //test some function
         System.out.println(MySQLAccess.login("mv740"));

     }
}
