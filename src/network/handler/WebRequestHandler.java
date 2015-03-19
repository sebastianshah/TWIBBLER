package network.handler;

import network.query.MySQLAccess;
import network.util.Profile;
import network.util.TwibblePost;

import java.util.List;

/**
 * Created by michal on 3/16/2015.
 */
public class WebRequestHandler {


    public static String getProfiles(String web) {

        //System.out.println(web.toString());
        String t = web.toString();
        int indexEnd = t.indexOf("H");
        int indexB = t.indexOf("=");
        //System.out.println(web.substring(indexB+1, indexEnd-1));
        String username = web.substring(indexB + 1, indexEnd - 1);

        //TODO do the search by user if exist else return user not found html page
        //search query
        String httpmessage = "";
        if (web.contains("user=")) {

            httpmessage += "HTTP/1.1 200 Okay\r\nContent-Type: text/html; charset=ISO-8859-4 \r\n\r\n";
            httpmessage += "<!DOCTYPE html><html lang='en'> <head><title>Twibbler</title><link rel='stylesheet' type='text/css' href='//maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css'>"
                    + "</head><body>"
                    + "<div class='container'>"
                    + "<div class='jumbotron text-center'><h1>Twibbler <span class=\"glyphicon glyphicon-globe\" aria-hidden=\"true\"></span></h1></div>"

                    + "<div><table class='table table-striped text-center'>";
            httpmessage += "<tr>"
                    + "<td class='info' colspan='4'><h3 class='text-center'>" + username + " Twibbler feed" + "</h3></td>"
                    + "</tr>";
            httpmessage += "<tr>"
                    + "<td><strong>Username</strong></td>"
                    + "<td><strong>Id</strong></td>"
                    + "<td><strong>Message</strong></td>"
                    + "<td><strong>Date</strong></td>"
                    + "</tr>";

            System.out.println(username);
            System.out.println(username.length());
            List<TwibblePost> posts = MySQLAccess.getTwibbler(username);
            System.out.println("size :" + posts.size());
            for (int i = 0; i < posts.size(); i++) {

                httpmessage += "<tr>"
                        + "<td>" + posts.get(i).getUsername() + "</td>"
                        + "<td>" + posts.get(i).getId() + "</td>"
                        + "<td>" + posts.get(i).getContent() + "</td>"
                        + "<td>" + posts.get(i).getDatetime() + "</td>"
                        + "</tr>";
            }
            //System.out.println("testuser=");
            httpmessage += "</table></div></div></body></html>";


        }
        return httpmessage;
    }

    public static String getTwibblers()
    {
        List<Profile> profiles = MySQLAccess.getProfiles();

        String message= "";

        //toWeb.print("HTTP/1.1 200 OK");
        //toWeb.print("Content-Type: text/html");
        //toWeb.print("\r\n");
        //toWeb.print("<body");

        //<span class="glyphicon glyphicon-search" aria-hidden="true"></span>

        message += "HTTP/1.1 200 Okay\r\nContent-Type: text/html; charset=ISO-8859-4 \r\n\r\n";
        message+="<!DOCTYPE html><html lang='en'> <head><title>Twibbler</title><link rel='stylesheet' type='text/css' href='//maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css'>"
                + "</head><body>"
                +"<div class='container'>"
                + "<div class='jumbotron text-center'>" +
                "<h1>Twibbler <span class=\"glyphicon glyphicon-globe\" aria-hidden=\"true\"></span>" +
                "</h1>"
                +"</div>"

                +   "<div><table class='table table-striped text-center'>";
        message +="<tr>"
                +"<td class='info' colspan='4'><h3 class='text-center'>PROFILES </h3></td>"
                +"</tr>";
        message +="<tr>"
                +"<td><strong>Username</strong></td>"
                +"<td><strong>Location</strong></td>"
                +"<td><strong>Interest</strong></td>"
                +"<td></td>"
                +"</tr>";

        for(int i =0; i<profiles.size(); i++)
        {


            message +="<tr>"
                    +"<td>"+profiles.get(i).getUsername()+"</td>"
                    +"<td>"+profiles.get(i).getLocation()+"</td>"
                    +"<td>"+profiles.get(i).getInterest()+"</td>"
                    +"<td><a href='user="+profiles.get(i).getUsername()+"'>Show Twibblers</a> </td>"
                    +"</tr>";

            //toWeb.print(profiles.get(i).getUsername());
            //toWeb.print(profiles.get(i).getLocation());
            //toWeb.print(profiles.get(i).getInterest());

        }
        message +="</table></div></div></body></html>";

        return message;
    }
}


