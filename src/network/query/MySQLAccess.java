package network.query;


import com.sun.xml.internal.bind.v2.TODO;
import network.util.ConnectionConfiguration;

import javax.jws.soap.SOAPBinding;
import java.sql.*;


/**
 * @author michal wozniak
 * @version 1.0
 */
public class MySQLAccess
{

    public static String login(String username)
    {
        Connection  con =null;
        PreparedStatement statement = null;
        ConnectionConfiguration connect = new ConnectionConfiguration();

        String message = null;

        try{
            con = connect.getConnection();

            //check if user has a profile
            String queryCheck = "SELECT username FROM profile WHERE username = ? ";
            statement = con.prepareStatement(queryCheck);
            statement.setString(1,username);
            ResultSet result = statement.executeQuery();

            //user exist
            if(result.isBeforeFirst())
            {
                //result message
                message = "Successful login";
            }
            else
            {
                //write to log error message
                message = "ERROR :"+username+" not in database ";
            }

        }catch (SQLException e){

            //e.printStackTrace();
            //String error = e.getMessage();
            //write to log error message
        }finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return message;
    }
    /**
     * create a user account in the database
     * @param username twibbler username
     * @param email user email
     * @return successful/failure message
     */
    public static String register(String username, String email)
    {
        Connection  con =null;
        PreparedStatement statement = null;
        ConnectionConfiguration connect = new ConnectionConfiguration();

        String message = null;

        try{
            String query = "INSERT INTO users (username, email, DateOfRegistration)" +" VALUE(?,?,?)";
            con = connect.getConnection();
            java.util.Date date = new java.util.Date();


            statement = con.prepareStatement(query);
            statement.setString(1,username);
            statement.setString(2,email);
            //Date need to be a sql Date object
            statement.setDate(3, new Date(date.getTime()));


            statement.executeUpdate();

            //result message
            message = "Registration was successful";


        }catch (SQLException e)
        {
            //e.printStackTrace();
            String error = e.getMessage();
            //write to log error message
            message = "Registration has failed : user " + username +" already exist!";
        }finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return message;
    }

//    TODO need to do that after deregister user is logout
    /**
     * Delete the user account in the database
     * @param username the username of the user
     * @return successful/failure message
     */
    public static String deRegister(String username){
        Connection  con =null;
        PreparedStatement statement = null;
        ConnectionConfiguration connect = new ConnectionConfiguration();

        String message = null;

        try{
            String query = "DELETE FROM users WHERE username= ?";
            con = connect.getConnection();

            statement = con.prepareStatement(query);
            statement.setString(1,username);
            statement.executeUpdate();

            //result message
            message = "Deregistration was successful";

        }catch (SQLException e){
            //e.printStackTrace();
            String error = e.getMessage();
            //write to log error message
            message = "Deregistration has failed";
        }finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return message;
    }

    /**
     * add new entry into profile database
     * @return successful/failure message
     */
    public static String postProfile(String username, String location, String interest)
    {
        Connection  con =null;
        PreparedStatement statement = null;
        ConnectionConfiguration connect = new ConnectionConfiguration();

        java.util.Date date = new java.util.Date();
        String message = null;

        try{
            String query = "INSERT INTO profile(username, location, interest, DateOfPostingProfile)" +"VALUES(?,?,?,?)";
            con = connect.getConnection();

            statement = con.prepareStatement(query);
            statement.setString(1,username);
            statement.setString(2,location);
            statement.setString(3,interest);
            statement.setDate(4, new Date(date.getTime()));
            statement.executeUpdate();

            //result message
            message = "profile was successfully created";

        }catch (SQLException e){
            //e.printStackTrace();
            String error = e.getMessage();
            //write to log error message
            message = "FAILURE : there was a error that stopped the profile from being created";
        }finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return message;
    }

    public static String updateProfile(String username, String location, String interest)
    {
        Connection  con =null;
        PreparedStatement statement = null;
        ConnectionConfiguration connect = new ConnectionConfiguration();

        java.util.Date date = new java.util.Date();
        String message = null;

        try{
            String query = "UPDATE profile SET location =? ,interest =? WHERE username =?";
            con = connect.getConnection();

            statement = con.prepareStatement(query);
            statement.setString(1,location);
            statement.setString(2,interest);
            statement.setString(3,username);
            statement.executeUpdate();

            //result message
            message = "profile was successfully updated";

        }catch (SQLException e){
            //e.printStackTrace();
            String error = e.getMessage();
            //write to log error message
            message = "FAILURE : profiled was not updated";
        }finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return message;
    }

    /**
     * Check database for users with a profile and return
     * @return users that have a profile
     */
    public static ResultSet showPublishers()
    {
        Connection  con =null;
        PreparedStatement statement = null;
        ConnectionConfiguration connect = new ConnectionConfiguration();

        java.util.Date date = new java.util.Date();
        ResultSet queryResult = null;

        try{
            String query = "SELECT username  FROM twibblerdata.profile";
            con = connect.getConnection();

            statement = con.prepareStatement(query);
            queryResult = statement.executeQuery();

            //  TODO the return give a ResultSet object which the client will need to while output
            //For testing
            /*while(queryResult.next()){
                System.out.print(queryResult.getString("username"));
            }*/

            //result message

        }catch (SQLException e){
            //e.printStackTrace();
        }finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return queryResult;
    }

    // TODO must then send email to all subcriber
    /**
     * Insert new database twibble entry
     * @return
     */
    public static String postTwibble(String username, String content)
    {
        Connection  con =null;
        PreparedStatement statement = null;
        ConnectionConfiguration connect = new ConnectionConfiguration();

        java.util.Date date = new java.util.Date();
        String message = null;

        try{
            con = connect.getConnection();

            //TODO create custom function just to do this external
            //every user account will use a id number to track his twibblers
            //count the number of twibbler a user has
            String queryCount = "SELECT Username,id  FROM twibble WHERE Username =?";
            statement = con.prepareStatement(queryCount);
            statement.setString(1,username);
            ResultSet result = statement.executeQuery();
            int count =0;
            result.last();
            count =  result.getInt("id");
            System.out.println(count);
            //end

            String query = "INSERT INTO twibblerdata.twibble(username, datetime, content, id)" +"VALUES(?,?,?,?)";

            statement = con.prepareStatement(query);
            statement.setString(1,username);
            statement.setTimestamp(2, new Timestamp(date.getTime()));
            statement.setString(3,content);
            statement.setInt(4, count+1);
            statement.executeUpdate();


            //insert twibble


            //result message
            message = "twibble was successfully posted";

        }catch (SQLException e){
            e.printStackTrace();
            //String error = e.getMessage();
            //write to log error message
            message = "FAILURE : twibble was not posted";
        }finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return message;
    }

    /**
     * @param user logged user
     * @param UserToFollow the user that he want to subscribe to
     * @return successful/failure message
     */
    public static String subscribe(String user, String UserToFollow )
    {
        Connection  con =null;
        PreparedStatement statement = null;
        ConnectionConfiguration connect = new ConnectionConfiguration();

        String message = null;

        try{
            con = connect.getConnection();

            //check if user has a profile
            String queryCheck = "SELECT * FROM profile WHERE username = ? ";
            statement = con.prepareStatement(queryCheck);
            statement.setString(1,UserToFollow);
            ResultSet result = statement.executeQuery();

            //profile exist
            if(result.isBeforeFirst())
            {
                //insert twibble
                String query = "INSERT INTO subscription(Publisher_username, Subscriber_username)" +"VALUES(?,?)";

                statement = con.prepareStatement(query);
                statement.setString(1,UserToFollow);
                statement.setString(2,user);
                statement.executeUpdate();


                //result message
                message = "You have been successfully subscribed to "+ UserToFollow;
            }
            else
            {
                //write to log error message
                message = "ERROR :"+UserToFollow+" does not have a profile ";
            }



        }catch (SQLException e){

            //e.printStackTrace();
            //String error = e.getMessage();
            //write to log error message
        }finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return message;
    }

    public static String unSubscribe(String user, String userToUnfollow)
    {
        Connection  con =null;
        PreparedStatement statement = null;
        ConnectionConfiguration connect = new ConnectionConfiguration();

        String message = null;

        try{

            String queryFind = "SELECT * FROM subscription WHERE Publisher_username= ? AND Subscriber_username =?";
            con = connect.getConnection();

            statement = con.prepareStatement(queryFind);
            statement.setString(1,userToUnfollow);
            statement.setString(2,user);
            ResultSet result = statement.executeQuery();

            //check if data exist
            if(result.isBeforeFirst())
            {
                // data exist
                String query = "DELETE FROM subscription WHERE Publisher_username= ? AND Subscriber_username =?";
                con = connect.getConnection();

                statement = con.prepareStatement(query);
                statement.setString(1,userToUnfollow);
                statement.setString(2,user);
                statement.executeUpdate();

                //result message
                message = "You have been unsubscribed from "+userToUnfollow;
            }
            //no data
            else{
                message ="ERROR : Publisher not found ";
            }


        }catch (SQLException e){
            //e.printStackTrace();
            String error = e.getMessage();
            //write to log error message
        }finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return message;
    }

    public static String deleteTwibble(String username ,int id){
        Connection  con =null;
        PreparedStatement statement = null;
        ConnectionConfiguration connect = new ConnectionConfiguration();
        String message = null;

        try{
            String query = "DELETE FROM twibble WHERE username= ? AND id= ?";
            con = connect.getConnection();

            statement = con.prepareStatement(query);
            statement.setString(1,username);
            statement.setInt(2,id);
            statement.executeUpdate();

            //result message
            message = "twibber "+id+"  was deleted successfully";

        }catch (SQLException e){
            //e.printStackTrace();
            String error = e.getMessage();
            //write to log error message
            message = "ERROR : twibber "+id+"  was not found";
        }finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return message;
    }


}
