# twibbler

##description

It is a micro-blogging service that allows clients to register, view a web-accessible profile, post messages and subscribe to receive email notification of new messages posted by other registered clients.

User can use a GUI client to interact with the system. He can also view information about each public user through a browser.

##Installation

- Database:
	- you are required to have MySQL installed on you system
	- create a database called twibblerdata
	- import the sql file called twibblerdata.sql 

- Server 
 - to start the server, please run TwibblerServer.java
		- you can modify the ports by changing these values ( code line 16-17) 
	  	  - ``` int serverPort = 9000;``` 
  		  - ``` int webPort = 9001;``` 





##client

   - We have two sorts of client interface :
      - with a gui : TwibblerCLientGUI.java
      - text-based : TwibblerCLient.java	

   - Access the web interface using hostaddress:webPort 

###features
  - Posting and updating client profiles
  - Posting and deleting twibbles
  - Subscribing to another poster’s twibbles


   
