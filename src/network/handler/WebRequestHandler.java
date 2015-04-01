package network.handler;

import network.query.MySQLAccess;
import network.util.Profile;
import network.util.TwibblePost;

import javax.jws.soap.SOAPBinding;
import java.util.List;

/**
 * Created by michal on 3/16/2015.
 */
public class WebRequestHandler {

    //used to print error log
    public static boolean userFound;


    /**
     *  This method search for the twibblers written by the uername and return a html page containing them
     *  if the user isn't found in the database, a error page will be returned
     *
     * @param web string send by browser
     * @param username
     * @return correct html page with requested twibbles
     */
    public static String getTwibbles(String web, String username)
    {

        //search query
        String httpmessage = "";


        httpmessage += "HTTP/1.1 200 OK\r\nContent-Type: text/html; charset=ISO-8859-4 \r\n\r\n";
        httpmessage += "<!DOCTYPE html><html lang='en'> <head><title>Twibbler</title><link rel='stylesheet' type='text/css' href='//maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css'>"
                +"</head><body>"
                + "<div class='container'>"
                +"<br/>"
                + "<div class='text-center'><img src=\"http://s1.postimg.org/fljap34cv/Twibbler_Banner.jpg\" alt=\"Twibbler Logo\" ></div>"
                +"<br/>";



        List<TwibblePost> posts = MySQLAccess.getTwibbler(username);

        //check if user is in the database
        if(!posts.isEmpty())
        {
            userFound = true;


            httpmessage +="<div><table class='table table-striped text-center'>"
                    +"<tr>"
                    + "<td class='info' colspan='4'><h3 class='text-center'>" + username + " Twibbles feed" + "</h3></td>"
                    + "</tr>";
            httpmessage += "<tr>"
                    + "<td><strong>Username</strong></td>"
                    + "<td><strong>Id</strong></td>"
                    + "<td><strong>Message</strong></td>"
                    + "<td><strong>Date</strong></td>"
                    + "</tr>";

            for (TwibblePost post : posts) {

                httpmessage += "<tr>"
                        + "<td>" + post.getUsername() + "</td>"
                        + "<td>" + post.getId() + "</td>"
                        + "<td>" + post.getContent() + "</td>"
                        + "<td>" + post.getDatetime() + "</td>"
                        + "</tr>";

            }
        }
        else{

            userFound = false;

            httpmessage +="<div><table class='table table-striped text-center'>"
                    +"<tr>"
                    + "<td class='danger' colspan='4'><h3 class='text-center'>ERROR: " + username + " NOT FOUND!  " + "</h3></td>"
                    + "</tr>";
        }

        //System.out.println("testuser=");
        httpmessage += "</table></div></div></body></html>";

        return httpmessage;
    }


    /**
     * Search for all the available users that got a profile. Users can follow them.
     *
     * @return html page containing all the public profiles
     */
    public static String getProfiles() {
        List<Profile> profiles = MySQLAccess.getProfiles();

        String message = "";

        message += "HTTP/1.1 200 OK\r\nContent-Type: text/html; charset=ISO-8859-4 \r\n\r\n";
        message += "<!DOCTYPE html><html lang='en'> <head><title>Twibbler</title><link rel='stylesheet' type='text/css' href='//maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css'>"
                +"</head><body>"
                + "<div class='container'>"
                +"<br/>"
                + "<div class='text-center'><img src=\"http://s1.postimg.org/fljap34cv/Twibbler_Banner.jpg\" alt=\"Twibbler Logo\" ></div>"
                +"<br/>"
                + "<div><table class='table table-striped text-center'>";
        message += "<tr>"
                + "<td class='info' colspan='4'><h3 class='text-center'>PROFILES </h3></td>"
                + "</tr>";
        message += "<tr>"
                + "<td><strong>Username</strong></td>"
                + "<td><strong>Location</strong></td>"
                + "<td><strong>Interest</strong></td>"
                + "<td></td>"
                + "</tr>";

        for (Profile profile : profiles) {


            message += "<tr>"
                    + "<td>" + profile.getUsername() + "</td>"
                    + "<td>" + profile.getLocation() + "</td>"
                    + "<td>" + profile.getInterest() + "</td>"
                    + "<td><a href='user=" + profile.getUsername() + "'>Show Twibblers</a> </td>"
                    + "</tr>";

            //toWeb.print(profiles.get(i).getUsername());
            //toWeb.print(profiles.get(i).getLocation());
            //toWeb.print(profiles.get(i).getInterest());

        }
        message += "</table></div></div></body></html>";

        return message;
    }
}


