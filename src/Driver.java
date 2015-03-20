import network.query.MySQLAccess;
import network.util.SQLConnectionConfiguration;
import network.util.Email;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


//gui
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Created by michal on 3/9/2015.
 * <p/>
 * DRIVER IS USED FOR TESTING PURPOSES ONLY
 */
public class Driver {

    private JFrame mainFrame;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel controlPanel;
    private JLabel msglabel;

    public Driver() {
        prepareGUI();
    }


    private void prepareGUI(){
        mainFrame = new JFrame("Java SWING Examples");
        mainFrame.setSize(400,400);
        mainFrame.setLayout(new GridLayout(3, 1));

        headerLabel = new JLabel("",JLabel.CENTER );
        statusLabel = new JLabel("",JLabel.CENTER);

        statusLabel.setSize(350,100);
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });
        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        mainFrame.add(headerLabel);
        mainFrame.add(controlPanel);
        mainFrame.add(statusLabel);
        mainFrame.setVisible(true);
    }


    private void showEventDemo(){
        headerLabel.setText("Control in action: Button");

        JButton LoginButton = new JButton("Login");
        JButton RegisterButton = new JButton("Register");
        JButton cancelButton = new JButton("Cancel");

        LoginButton.setActionCommand("Login");
        RegisterButton.setActionCommand("Register");
        cancelButton.setActionCommand("Cancel");

        LoginButton.addActionListener(new ButtonClickListener());
        RegisterButton.addActionListener(new ButtonClickListener());
        cancelButton.addActionListener(new ButtonClickListener());

        controlPanel.add(LoginButton);
        controlPanel.add(RegisterButton);
        controlPanel.add(cancelButton);

        mainFrame.setVisible(true);
    }

    private class ButtonClickListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if( command.equals( "Login" ))  {
                statusLabel.setText("Login Button clicked.");
            }
            else if( command.equals( "Register" ) )  {
                statusLabel.setText("Register Button clicked.");
            }
            else  {
                statusLabel.setText("Cancel Button clicked.");
            }
        }


    }



    public static void main(String[] args) {
        System.out.println("hello");

        SQLConnectionConfiguration connect = new SQLConnectionConfiguration();
        Connection connection = null;

        try {
            connection = connect.getConnection();
            if (connection != null) {
                System.out.println("Connection work!!!");
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
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

        //String mess = MySQLAccess.login("mv740");
        //System.out.print(mess);
        //MySQLAccess.getProfiles();

        //email
        //Properties properties = System.getProperties();
        // Configure mail server
        //properties.put("mail.smtp.starttls.enable", "true");
        //properties.put("mail.smtp.auth", "true");
        //properties.put("mail.smtp.port", "587");
        //Email.properties = properties;
        //Email.sendEmail("mv740","testing");


        Driver swingContainerDemo = new Driver();
        swingContainerDemo.showEventDemo();


    }
}
