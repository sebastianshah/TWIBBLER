import java.io.IOException;

import java.net.ServerSocket;
import java.util.Properties;

import network.util.*;


public class TwibblerServer {

	public static void main(String[] args) {


		ServerSocket serverSocket = null;
        ServerSocket webSocket = null;
		int serverPort = 9000;
        int webPort = 9001;
		
		//email
        Properties properties = System.getProperties();
        // Configure mail server
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");
        Email.properties = properties;
		
		
		try{ 
			serverSocket = new ServerSocket(serverPort);
            webSocket = new ServerSocket(webPort);

		}catch(IOException e){ 
			System.out.println("Fail could not start server " + e.getMessage()); 
		}
		

        // two thread that will be waiting to accept a web request or a client request
        WebListener webListener = new WebListener(webSocket);
        ClientListener clientListener = new ClientListener(serverSocket);




	
	}

}
