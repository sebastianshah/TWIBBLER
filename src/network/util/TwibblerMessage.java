package network.util;
import java.io.Serializable;


public class TwibblerMessage implements Serializable {
	private	MessageType type;
	private String username;
	private String email;
	private String publisherUsername;
	private String content;
	private String location;
	private String interest;
	private int ID;
	
	
	
	
	//use .name() to convert to string
	public static enum MessageType{
		LOGIN, REGISTER, DEREGISTER, POST_PROFILE, UPDATE_PROFILE, SHOW_PUBLISHERS, POST_TWIBBLE, DELETE_TWIBBLE, SUBSCRIBE, UNSUBSCRIBE
	}




	public MessageType getType() {
		return type;
	}




	public void setType(MessageType type) {
		this.type = type;
	}




	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}



	public String getPublisherUsername() {
		return publisherUsername;
	}




	public void setPublisherUsername(String publisherUsername) {
		this.publisherUsername = publisherUsername;
	}




	public String getContent() {
		return content;
	}




	public void setContent(String content) {
		this.content = content;
	}




	public String getLocation() {
		return location;
	}




	public void setLocation(String location) {
		this.location = location;
	}




	public String getInterest() {
		return interest;
	}




	public void setInterest(String interest) {
		this.interest = interest;
	}




	public int getID() {
		return ID;
	}




	public void setID(int iD) {
		ID = iD;
	}
	

}
