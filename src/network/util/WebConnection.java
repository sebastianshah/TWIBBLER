package network.util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import network.handler.WebRequestHandler;
import network.query.MySQLAccess;

//thread for handling communication with client 
//invokes ServerRequestHandler to handle requests + response 
public class WebConnection extends Thread {
    protected Socket netClient;
    protected ObjectInputStream fromClient;
    protected ObjectOutputStream toClient;
    protected BufferedReader fromWeb;
    protected PrintWriter toWeb;

    public WebConnection(Socket WebClient)  {
        netClient = WebClient;
        try {
            fromWeb = new BufferedReader(new InputStreamReader(netClient.getInputStream()));
            toWeb = new PrintWriter(netClient.getOutputStream(),true);
        } catch(IOException e) {
            try {

                //System.out.println("netclient.close");
                //System.out.println(e.getMessage());
                netClient.close();

            }catch(IOException e1) {
                System.out.println("Fail could not set up streams " + e.getMessage());
                return;
            }
        }
        this.start();
    }

    public void run() {

        String web;
        try{
            if((web = fromWeb.readLine()) != null)
            {

                //System.out.println(web.toString());
                int indexEnd = web.indexOf("H");
                int indexB = web.indexOf("=");
                //System.out.println(web.substring(indexB+1, indexEnd-1));
                String username = web.substring(indexB+1, indexEnd-1);


                //search query
                if(web.contains("user="))
                {
                    toWeb.println(WebRequestHandler.getTwibbles(web, username));
                    if(WebRequestHandler.userFound)
                    {
                        MySQLAccess.writeLog("WebBrowser-Request", "Successful print all twibbles");
                    }
                    else
                    {
                        MySQLAccess.writeLog("WebBrowser-Request", "ERROR : "+username+" not found");
                    }

                }
                else
                {
                    toWeb.println(WebRequestHandler.getProfiles());

                    MySQLAccess.writeLog("WebBrowser-Request", "Successful print all Profiles");
                }

            }
            fromWeb.close();
            toWeb.close();
            netClient.close();

        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
