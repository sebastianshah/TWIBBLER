package network.util;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by michal on 4/8/2015.
 * This thread will only be used to accept connection on the web port
 */
public class WebListener extends Thread
{

    private ServerSocket webSocket;

    public WebListener(ServerSocket socket)
    {
        this.webSocket = socket;
        this.start();
    }

    public void run()
    {
        try {
            while(true)
            {
                //accept incoming TCP connection requests and create a connection thread with new client
                //pass new connection thread client socket
                Socket web = webSocket.accept();
                WebConnection con = new WebConnection(web);
            }


        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Fail could not connect " + e.getMessage());
        }
    }
}
