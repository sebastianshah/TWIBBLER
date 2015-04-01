package network.util;

/**
 * TwibblePost is used to store all the information send by a twibble message post
 * It is used to send back the information from the database to the web
 */
public class TwibblePost {

	private String username;
	private String content;
	private int id;
	private String datetime;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	
}
