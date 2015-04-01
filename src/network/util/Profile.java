package network.util;

/**
 *  Profile  stored all the information about a profile
 *  It is used to send back the information from the database to the web
 */
public class Profile {

	private String username;
	private String location;
	private String interest;
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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
	
	
	
	
}
