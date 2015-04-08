package network.util;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by michal on 4/8/2015.
 * This thread will only be used to accept connection on the client port
 */
public class ClientListener extends Thread
{

    private ServerSocket clientSocket;

    public ClientListener(ServerSocket socket)
    {
        this.clientSocket = socket;
        this.start();
    }

    public void run()
    {
        try {
            while(true)
            {
                //accept incoming TCP connection requests and create a connection thread with new client
                //pass new connection thread client socket
                Socket web = clientSocket.accept();
                Connection con = new Connection(web);
            }


        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Fail could not connect " + e.getMessage());
        }
    }
}
