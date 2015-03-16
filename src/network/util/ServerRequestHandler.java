package network.util;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Properties;

import network.query.MySQLAccess;


public class ServerRequestHandler {
	
	
	//return TwibblerMessage containing response
	public static TwibblerMessage getResponse(TwibblerMessage inputRequest) {
		
//		//UseTokenizer to break request
//		String method = new String();
//		String username = new String();
//		String content = new String();
//		String response = new String();
		
		//call specific method function to handle request
		switch(inputRequest.getType()) {
			case LOGIN : 
				System.out.println("Twibbler server recieved LOGIN request");
				MySQLAccess.writeLog(inputRequest.getUsername(),"Twibbler server recieved LOGIN request");
				return getLoginResponse(inputRequest);
			case REGISTER : 
				System.out.println("Twibbler server recieved REGISTER request");
				MySQLAccess.writeLog(inputRequest.getUsername(),"Twibbler server recieved REGISTER request");
				return getRegisterResponse(inputRequest);
			case DEREGISTER : 
				System.out.println("Twibbler server recieved DEREGISTER request");
				MySQLAccess.writeLog(inputRequest.getUsername(),"Twibbler server recieved DEREGISTER request");
				return getDeregisterResponse(inputRequest);
			case POST_PROFILE : 
				System.out.println("Twibbler server recieved POST_PROFILE request");
				MySQLAccess.writeLog(inputRequest.getUsername(),"Twibbler server recieved POST_PROFILE request");
				return getPostProfileResponse(inputRequest);
			case UPDATE_PROFILE : 
				System.out.println("Twibbler server recieved UPDATE_PROFILE request");
				MySQLAccess.writeLog(inputRequest.getUsername(),"Twibbler server recieved UPDATE_PROFILE request");
				return getUpdateProfileResponse(inputRequest);
			case SHOW_PUBLISHERS : 
				System.out.println("Twibbler server recieved SHOW_PUBLISHERS request");
				MySQLAccess.writeLog(inputRequest.getUsername(),"Twibbler server recieved SHOW_PUBLISHERS request");
				return getShowPublishersResponse(inputRequest);
			case POST_TWIBBLE : 
				System.out.println("Twibbler server recieved POST_TWIBBLE request");
				MySQLAccess.writeLog(inputRequest.getUsername(),"Twibbler server recieved POST_TWIBBLE request");
				return getPostTwibbleResponse(inputRequest);
			case DELETE_TWIBBLE : 
				System.out.println("Twibbler server recieved DELETE_TWIBBLE request");
				MySQLAccess.writeLog(inputRequest.getUsername(),"Twibbler server recieved DELETE_TWIBBLE request");
				return getDeleteTwibbleResponse(inputRequest);
			case SUBSCRIBE : 
				System.out.println("Twibbler server recieved SUBSCRIBE request");
				MySQLAccess.writeLog(inputRequest.getUsername(),"Twibbler server recieved SUBSCRIBE request");
				return getSubscribeResponse(inputRequest);			
			case UNSUBSCRIBE : 
				System.out.println("Twibbler server recieved UNSUBSCRIBE request");
				MySQLAccess.writeLog(inputRequest.getUsername(),"Twibbler server recieved UNSUBSCRIBE request");
				return getUnsubscribeResponse(inputRequest);
			default :
				System.out.println("Twibbler server recieved INVALID request");
				MySQLAccess.writeLog(inputRequest.getUsername(),"Twibbler server recieved INVALID request");
				return null;				
		}
	}
	
	public static TwibblerMessage getLoginResponse(TwibblerMessage inputRequest) {
		TwibblerMessage outputResponse = new TwibblerMessage(); 
		System.out.println(inputRequest.getUsername());
		outputResponse.setContent(MySQLAccess.login(inputRequest.getUsername()));
		
		MySQLAccess.writeLog(inputRequest.getUsername(), outputResponse.getContent());
		
		return outputResponse;
	}
	
	public static TwibblerMessage getRegisterResponse(TwibblerMessage inputRequest) {
		TwibblerMessage outputResponse = new TwibblerMessage(); 
		outputResponse.setContent(MySQLAccess.register(inputRequest.getUsername(), inputRequest.getEmail()));
		
		MySQLAccess.writeLog(inputRequest.getUsername(), outputResponse.getContent());
		
		return outputResponse;	
	}
	
	public static TwibblerMessage getDeregisterResponse(TwibblerMessage inputRequest) {
		TwibblerMessage outputResponse = new TwibblerMessage(); 
		outputResponse.setContent(MySQLAccess.deRegister(inputRequest.getUsername()));
		
		MySQLAccess.writeLog(inputRequest.getUsername(), outputResponse.getContent());
		
		return outputResponse;
	}
	
	public static TwibblerMessage getPostProfileResponse(TwibblerMessage inputRequest) {
		TwibblerMessage outputResponse = new TwibblerMessage(); 
		outputResponse.setContent(MySQLAccess.postProfile(inputRequest.getUsername(), inputRequest.getLocation(), inputRequest.getInterest()));
		
		MySQLAccess.writeLog(inputRequest.getUsername(), outputResponse.getContent());
		
		return outputResponse;
	}
	
	public static TwibblerMessage getUpdateProfileResponse(TwibblerMessage inputRequest) {
		TwibblerMessage outputResponse = new TwibblerMessage(); 
		outputResponse.setContent(MySQLAccess.updateProfile(inputRequest.getUsername(),inputRequest.getLocation(),inputRequest.getLocation()));
		
		MySQLAccess.writeLog(inputRequest.getUsername(), outputResponse.getContent());
		return outputResponse;
	}
	
	public static TwibblerMessage getShowPublishersResponse(TwibblerMessage inputRequest) {
		TwibblerMessage outputResponse = new TwibblerMessage(); 
		outputResponse.setContent(MySQLAccess.showPublishers(inputRequest.getUsername()));
		
		MySQLAccess.writeLog(inputRequest.getUsername(), outputResponse.getContent());
		return outputResponse;
	}
	
	public static TwibblerMessage getPostTwibbleResponse(TwibblerMessage inputRequest) {
		TwibblerMessage outputResponse = new TwibblerMessage(); 
		outputResponse.setContent(MySQLAccess.postTwibble(inputRequest.getUsername(), inputRequest.getContent()));
		MySQLAccess.writeLog(inputRequest.getUsername(), outputResponse.getContent());

		Email.sendEmail(inputRequest.getUsername(), inputRequest.getContent());
		
		return outputResponse;
	}
	
	public static TwibblerMessage getDeleteTwibbleResponse(TwibblerMessage inputRequest) {
		TwibblerMessage outputResponse = new TwibblerMessage(); 
		outputResponse.setContent(MySQLAccess.deleteTwibble(inputRequest.getUsername(), inputRequest.getID()));
		
		MySQLAccess.writeLog(inputRequest.getUsername(), outputResponse.getContent());
		return outputResponse;
	}
	
	public static TwibblerMessage getSubscribeResponse(TwibblerMessage inputRequest) {
		TwibblerMessage outputResponse = new TwibblerMessage(); 
		outputResponse.setContent(MySQLAccess.subscribe(inputRequest.getUsername(), inputRequest.getPublisherUsername()));
		
		MySQLAccess.writeLog(inputRequest.getUsername(), outputResponse.getContent());
		return outputResponse;
	}
	
	public static TwibblerMessage getUnsubscribeResponse(TwibblerMessage inputRequest) {
		TwibblerMessage outputResponse = new TwibblerMessage(); 
		outputResponse.setContent(MySQLAccess.unSubscribe(inputRequest.getUsername(), inputRequest.getPublisherUsername()));
		
		MySQLAccess.writeLog(inputRequest.getUsername(), outputResponse.getContent());
		return outputResponse;
	}

}
