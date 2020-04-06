import java.io.*; 
import java.net.*;
import java.net.Socket; 



public class ChattyChatChatClient {
	public static void main (String[] args) {
		String hostname = "local host";
		int port = 40000;
		Socket socket = null; 
		
		try {
			
			socket = new Socket (hostname, port); 
			BufferedReader in = 
				new BufferedReader(
				new InputStreamReader(
				socket.getInputStream())); 
			OutputStream out = 
					socket.getOutputStream(), true); 
			String response = in.readLine(); 
			System.out.println(response); 
			for (int i = 0; i < 2; i++) {
				System.out.println(in.readLine()); 
			}
			
			boolean done = false; 
			while (!done) {
				String userInput = userIn.readLine(); 
				out.println(userInput); 
				if (userInput.contentEquals(".")) {
					done = true; 
				}
				
				String response = null; 
			try {
				response = in.readLine(); 
				if (response == null || response.equals("")) {
					done = true; 
				}
			} catch (IOException e) {
				System.out.println("Error recieving" + "from server"); 
				done = true; 
			}
				System.out.println(response); 
			}//END while (!done)
			
			

			} catch (IOException e) {
				System.out.println("Error connecting to server"); 
			}
		
	        finally {
			  try {
					socket.close(); 
				}catch (Exception e) {
				   //it's fine
				}
			} //END finally
	
		
	}//END main()
		  
  } //END client
  
  	
  			
  			
	
