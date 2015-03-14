import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import network.util.ClientRequestGenerator;
import network.util.TwibblerMessage;



//TODO: Work on client



public class TwibblerClient {
	
	

	public static void main(String[] args)
	{
	
		//TODO: set username after login"
		String clientUsername = null;
		
		int serverPort = 9000;
		String serverAddress = "localhost";
		Socket socket = null; 
		
		ObjectOutputStream toServer;
		ObjectInputStream fromServer;
		BufferedReader userInput;
		
		try {

			InetAddress host = InetAddress.getByName(serverAddress);
			socket = new Socket(host, serverPort);
			
			toServer = new ObjectOutputStream(socket.getOutputStream());
			fromServer = new ObjectInputStream(socket.getInputStream());
			
			Object in = null;
			TwibblerMessage inputResponse;
			TwibblerMessage outputRequest;
		
			//LOGIN REGISTER
			do {
				
				Scanner kb = new Scanner(System.in);
				outputRequest = new TwibblerMessage();
				
				int selection;
				
				System.out.println("REQUEST OPTIONS");
				System.out.println("===============");
			
				System.out.println("1 - Login");
				System.out.println("2 - Register");
				
				do {
					System.out.println("Select option: ");
					selection = kb.nextInt();
				} while (!(selection > 0 && selection < 3));
				
				
				switch(selection){
					case 1 :
						outputRequest.setType(TwibblerMessage.MessageType.LOGIN);
						System.out.println("Enter username: ");
						String username = kb.next();
						
						outputRequest.setUsername(username);
					
						clientUsername = username;
						break;
					case 2 :
						outputRequest.setType(TwibblerMessage.MessageType.REGISTER);
						
						System.out.println("Enter username: ");
						String rusername = kb.next();
						
						System.out.println("Enter Email: ");
						String email = kb.next();
						
						clientUsername = rusername;
						outputRequest.setEmail(email);
						outputRequest.setUsername(rusername);
						break;
					default	:
						System.out.println("Invalid Selection");
						break;
				}
				
				
				//Read request from client and send request to server
				
				toServer.writeObject(outputRequest);
				
				//Read response from server
				in = fromServer.readObject();
				inputResponse = (TwibblerMessage) in;
				
				System.out.println ("Twibbler client has received response from the server"); 
				//TODO: output correct content
		       	System.out.println("Content: " + inputResponse.getContent());
				System.out.println();
				
			} while (!inputResponse.getContent().equals("Successful login") && !inputResponse.getContent().equals("Successful registration"));
			
			//SET USERNAME*********************************************
			System.out.println(clientUsername);
			//REGULAR REQUESTS AFTER LOGIN
			do {
				
				//Read request from client and send request to server
				outputRequest = ClientRequestGenerator.promptRequest(clientUsername);
				toServer.writeObject(outputRequest);
				
				//Read response from server
				in = fromServer.readObject();
				inputResponse = (TwibblerMessage) in;
				
				System.out.println ("Twibbler client has received response from the server"); 
				//TODO: output correct content
		       	System.out.println("Content: " + inputResponse.getContent());
				System.out.println();
			} while (outputRequest != null);
			
			toServer.close();
			fromServer.close();
			socket.close();
		
		} catch (UnknownHostException e) {
			System.out.println("UnknownHostException:" + e.getMessage());
		} catch (IOException e) {
			System.out.println("IOException:" + e.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
	}

}
