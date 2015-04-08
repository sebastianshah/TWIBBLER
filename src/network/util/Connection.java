package network.util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import network.handler.ServerRequestHandler;


//thread for handling communication with client 
//invokes ServerRequestHandler to handle requests + response 
public class Connection extends Thread {
	protected Socket netClient; 
	protected ObjectInputStream fromClient; 
	protected ObjectOutputStream toClient; 
	protected BufferedReader fromWeb;
	protected PrintWriter toWeb;
	
	public Connection(Socket client)  { 
		netClient = client; 
		try { 
			//System.out.println("try-object-test-id:"+Thread.currentThread().getId());
			
			fromClient = new ObjectInputStream(netClient.getInputStream());
			toClient = new ObjectOutputStream(netClient.getOutputStream());
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
		
		try{ 
			Object in;
			TwibblerMessage inputRequest;
			TwibblerMessage outputResponse;

			// Read requests from the client and send response
            try {
                while((in = fromClient.readObject()) != null){
                    inputRequest = (TwibblerMessage) in;

                    System.out.println ("Twibbler Server has received request from the client");
                       System.out.println("username: "+inputRequest.getUsername());

                       //Invoke Request Handler
                       outputResponse = ServerRequestHandler.getResponse(inputRequest);
                       //Send Response
                       toClient.writeObject(outputResponse);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            fromClient.close();
			toClient.close();
			netClient.close(); 
		} catch (IOException e) {
            e.printStackTrace();
        }
    }
}
