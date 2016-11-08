import java.net.*;
import java.util.Scanner;

public class proxy
{
	public static void main (String[] args)
	
	{
		int socketPort;
		ServerSocket welcomeSocket;
		Scanner keyboard = new Scanner(System.in);
		
		try
		{
			
		if(args.length > 0 )
			{
				socketPort = Integer.parseInt(args[0]);
			} else 
			{
				System.out.println("Enter port number.");
				socketPort = keyboard.nextInt();
			}
		
		welcomeSocket = new ServerSocket(socketPort);
		
		while(true)
		{
			new TCPServerThread(welcomeSocket.accept()).start();
			
		}
		
		} catch (Exception e)
			{
			System.exit(0); 
			} 
	}
}

