import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintStream;
import java.io.Serializable;
import java.net.ServerSocket; 
import java.net.Socket;
import java.util.Properties;

import network.util.Connection;
import network.util.Email;





public class TwibblerServer {

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		int serverPort = 9000;
		
		//TODO fix the server email 
		//email
        Properties properties = System.getProperties();
        // Configure mail server
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");
        Email.properties = properties;
		
		
		try{ 
			serverSocket = new ServerSocket(serverPort); 
		}catch(IOException e){ 
			System.out.println("Fail could not start server " + e.getMessage()); 
		}
		
		//accept incoming TCP connection requests and create a connection thread with new client
		//pass new connection thread client socket
		try{ 
			while(true){ 
				Socket client = serverSocket.accept(); 
				Connection con = new Connection(client); 
				} 
			}catch(IOException e){ 
				System.out.println("Fail could not connect " + e.getMessage()); 
			}

	
	}

}
