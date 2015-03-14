import network.query.MySQLAccess;
import network.util.ConnectionConfiguration;
import network.util.Email;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

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
        //MySQLAccess.register("bob", "michalwozniak@live.ca");
        //MySQLAccess.register("robert","michalwozniak06@gmail.com");
        //MySQLAccess.register("steve","michalwozniak06@gmail.com");

        //String message2 = MySQLAccess.postProfile("bob","montreal","hockey");
        //MySQLAccess.postTwibble("michel","fgfg");
        //String follow = MySQLAccess.subscribe("steve", "wozniak");
        //String delete =MySQLAccess.deleteTwibble("michal", 1);
        //String error = MySQLAccess.unSubscribe("michal", "test");
        //String update = MySQLAccess.updateProfile("michal", "allo", "wut");
        //String login = MySQLAccess.login("michal");
       //System.out.println(login);

        //MySQLAccess.getSubscribers("wozniak");

        //email
        //Properties properties = System.getProperties();
        // Configure mail server
        //properties.put("mail.smtp.starttls.enable", "true");
        //properties.put("mail.smtp.auth", "true");
        //Email.sendEmail(properties, "wozniak");
        //MySQLAccess.postTwibble("bob","hello this is a twib");
        //MySQLAccess.getProfiles();
        
        String mess = MySQLAccess.login("mv740");
        System.out.print(mess);
        

    }
}
