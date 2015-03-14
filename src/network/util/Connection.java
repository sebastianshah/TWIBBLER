package network.util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.ServerSocket; 
import java.net.Socket;

//thread for handling communication with client 
//invokes ServerRequestHandler to handle requests + response 
public class Connection extends Thread {
	protected Socket netClient; 
	protected ObjectInputStream fromClient; 
	protected ObjectOutputStream toClient; 
	
	public Connection(Socket client) { 
		netClient = client; 
		try { 
			fromClient = new ObjectInputStream(netClient.getInputStream());
			toClient = new ObjectOutputStream(netClient.getOutputStream());
		} catch(IOException e) { 
			try { 
				netClient.close(); 
			}catch(IOException e1) { 
				System.out.println("Fail could not set up streams " + e.getMessage());
				return; 
			} 
		} 
		this.start(); 
	} 
				
	public void run() { 
		
		try{ 
			Object in;
			TwibblerMessage inputRequest;
			TwibblerMessage outputResponse;
			
			// Read requests from the client and send response
			while((in = fromClient.readObject()) != null){
				
				inputRequest = (TwibblerMessage) in;
				
				System.out.println ("Twibbler Server has received request from the client"); 
		       	System.out.println(inputRequest.getUsername());
	
		       	//Invoke Request Handler
		       	outputResponse = ServerRequestHandler.getResponse(inputRequest);
		       	//Send Response
		       	toClient.writeObject(outputResponse);
			}
			
			fromClient.close();
			toClient.close();
			netClient.close(); 
		}catch(IOException e){
			System.out.println("Fail could read line " + e.getMessage());
		}catch(ClassNotFoundException e) {
			System.out.println("Fail could class not found " + e.getMessage());
		}
	}
}
