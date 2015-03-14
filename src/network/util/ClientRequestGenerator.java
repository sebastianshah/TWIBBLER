package network.util;
import java.util.Properties;
import java.util.Scanner;

import network.query.MySQLAccess;


public class ClientRequestGenerator {

	
	
	
	public static TwibblerMessage promptRequest(String username){
		
		Scanner kb = new Scanner(System.in);
		
		TwibblerMessage outputRequest = new TwibblerMessage();
		outputRequest.setUsername(username);
		int selection;
		
		System.out.println("REQUEST OPTIONS");
		System.out.println("===============");
	
		System.out.println("1 - Post Profile");
		System.out.println("2 - Update Profile");
		System.out.println("3 - Show Publishers");
		System.out.println("4 - Post Twibble");
		System.out.println("5 - Delete Twibble");
		System.out.println("6 - Subscribe");
		System.out.println("7 - Unsubscribe");
		System.out.println("8 - Deregister");
		System.out.println("9 - Exit");
		System.out.println();
		
		do {
			System.out.println("Select option: ");
			selection = kb.nextInt();
		} while (!(selection > 0 && selection < 10));
			
		switch(selection){
			case 1 :
				outputRequest.setType(TwibblerMessage.MessageType.POST_PROFILE);
				
				//TODO: 30 char max
				System.out.println("Enter Location: ");
				String location = kb.next();
				//TODO: 30 char max
				System.out.println("Enter Interest: ");
				String interest = kb.next();
				
				outputRequest.setLocation(location);
				outputRequest.setInterest(interest);			
				
				return outputRequest;
			case 2 :
				outputRequest.setType(TwibblerMessage.MessageType.UPDATE_PROFILE);
				//TODO: 30 char max
				
				System.out.println("Enter Location: ");
				String ulocation = kb.next();
				//TODO: 30 char max
				System.out.println("Enter Interest: ");
				String uinterest = kb.next();	
				
				outputRequest.setLocation(ulocation);
				outputRequest.setInterest(uinterest);	
				
				return outputRequest;
			case 3 :
				outputRequest.setType(TwibblerMessage.MessageType.SHOW_PUBLISHERS);
				
				return outputRequest;
			case 4 :
				outputRequest.setType(TwibblerMessage.MessageType.POST_TWIBBLE);
				//TODO: 255 char max
				
				System.out.println("Enter Twibble: ");
				String twibble = kb.next();
				
				outputRequest.setContent(twibble);
				
				return outputRequest;
			case 5 :
				outputRequest.setType(TwibblerMessage.MessageType.DELETE_TWIBBLE);
				
				System.out.println("Enter Twibble ID: ");
				int iD = kb.nextInt();
				
				outputRequest.setID(iD);
				
				return outputRequest;
			case 6 :
				outputRequest.setType(TwibblerMessage.MessageType.SUBSCRIBE);
				
				System.out.println("Enter publisher username: ");
				String publisherUsername = kb.next();
				
				outputRequest.setPublisherUsername(publisherUsername);
				
				return outputRequest;
			case 7 :
				outputRequest.setType(TwibblerMessage.MessageType.UNSUBSCRIBE);
				
				System.out.println("Enter publisher username to unsubscribe: ");
				String uPublisherUsername = kb.next();
				
				outputRequest.setPublisherUsername(uPublisherUsername);
				
				return outputRequest;
			case 8 :
				outputRequest.setType(TwibblerMessage.MessageType.DEREGISTER);
				return outputRequest;
			case 9 :
				System.out.println("System is exiting");
				return null;
			default	:
				System.out.println("Invalid Selection");
				return null;
		}
		
		
	}

	
	
	
	
	
}
