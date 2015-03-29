package network.handler;

import network.query.MySQLAccess;
import network.util.Profile;
import network.util.TwibblePost;

import java.util.List;

/**
 * Created by michal on 3/16/2015.
 */
public class WebRequestHandler {


    public static String getTwibbles(String web, String username)
    {

        //search query
        String httpmessage = "";


        httpmessage += "HTTP/1.1 200 Okay\r\nContent-Type: text/html; charset=ISO-8859-4 \r\n\r\n";
        httpmessage += "<!DOCTYPE html><html lang='en'> <head><title>Twibbler</title><link rel='stylesheet' type='text/css' href='//maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css'>"
                +"</head><body>"
                + "<div class='container'>"
                +"<br/>"
                + "<div class='text-center'><img src=\"http://s1.postimg.org/fljap34cv/Twibbler_Banner.jpg\" alt=\"Twibbler Logo\" ></div>"
                +"<br/>";


        //System.out.println(username);
        //System.out.println(username.length());
        List<TwibblePost> posts = MySQLAccess.getTwibbler(username);
        //System.out.println("size :" + posts.size());

        if(!posts.isEmpty())
        {
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

            for (int i = 0; i < posts.size(); i++) {

                httpmessage += "<tr>"
                        + "<td>" + posts.get(i).getUsername() + "</td>"
                        + "<td>" + posts.get(i).getId() + "</td>"
                        + "<td>" + posts.get(i).getContent() + "</td>"
                        + "<td>" + posts.get(i).getDatetime() + "</td>"
                        + "</tr>";

            }
        }
        else{
            httpmessage +="<div><table class='table table-striped text-center'>"
                    +"<tr>"
                    + "<td class='danger' colspan='4'><h3 class='text-center'>ERROR: " + username + " NOT FOUND!  " + "</h3></td>"
                    + "</tr>";
        }

        //System.out.println("testuser=");
        httpmessage += "</table></div></div></body></html>";

        return httpmessage;
    }




    public static String getProfiles() {
        List<Profile> profiles = MySQLAccess.getProfiles();

        String message = "";

        message += "HTTP/1.1 200 Okay\r\nContent-Type: text/html; charset=ISO-8859-4 \r\n\r\n";
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

        for (int i = 0; i < profiles.size(); i++) {


            message += "<tr>"
                    + "<td>" + profiles.get(i).getUsername() + "</td>"
                    + "<td>" + profiles.get(i).getLocation() + "</td>"
                    + "<td>" + profiles.get(i).getInterest() + "</td>"
                    + "<td><a href='user=" + profiles.get(i).getUsername() + "'>Show Twibblers</a> </td>"
                    + "</tr>";

            //toWeb.print(profiles.get(i).getUsername());
            //toWeb.print(profiles.get(i).getLocation());
            //toWeb.print(profiles.get(i).getInterest());

        }
        message += "</table></div></div></body></html>";

        return message;
    }
}


