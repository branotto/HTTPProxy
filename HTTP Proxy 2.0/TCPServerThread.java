import java.io.*;
import java.net.*;


public class TCPServerThread extends Thread
{
	private Socket browserSocket = null;
	
	public TCPServerThread(Socket theSocket)
	{
		super("TCPServerThread");
		this.browserSocket = theSocket;
	}
	
	public void run()
	{
		//To hold the initial request from the client.
		String browserRequest = "";
		String browserLine = "";
		
		//To hold the formatted request to send out.
		String HTTPRequest = "";
		
		//To hold the network response.
		byte HTTPResponse;
		
		//Create a Parser object.
		Parser initialRequest;
		
		//To hold the host.
		String hostName = "";
		
		//To hold the port.
		int portNumber = -1;
		
		try{
		
			//Create a reader to read the browser message.
			BufferedReader inFromBrowser = new BufferedReader(new InputStreamReader(browserSocket.getInputStream()));
			
			//Create an outputstream to write the browser response message.
			DataOutputStream outToBrowser = new DataOutputStream(browserSocket.getOutputStream());
			
			browserLine = inFromBrowser.readLine();
			
			while(!browserLine.equals(""))
			{
				browserRequest += browserLine + "\r\n";
				browserLine = inFromBrowser.readLine();
			}
						
			try
			{
				
			//Pass the HTTP message to the parser.
	    	initialRequest = new Parser(browserRequest);
	    	
			//Save the formatted request to the string.
	    	HTTPRequest = initialRequest.formattedRequest();
	    	

	    	//Assign the host name.
			hostName = initialRequest.getHost();
			
			//Assign the port number.
			portNumber = Integer.parseInt(initialRequest.getPort());
			
			//Create the network socket
			Socket NetworkSocket = new Socket(hostName, portNumber);
			
			DataOutputStream outToNetwork = new DataOutputStream(NetworkSocket.getOutputStream());
		
			DataInputStream inFromNetwork = new DataInputStream(NetworkSocket.getInputStream());

			outToNetwork.writeBytes(HTTPRequest);
			
				try
				{
					while(true)
					{
						HTTPResponse = inFromNetwork.readByte();
						outToBrowser.writeByte(HTTPResponse);
					}	
				} catch(EOFException e)
					{
						NetworkSocket.close();
					}
		
			} catch(ImproperRequestForm e)
			{
				outToBrowser.writeUTF(e.getMessage());
			}
			
			//Close the socket with the browser.
			browserSocket.close();
			
			} catch (Exception e)
			{
				
			} 
	}
}
