 import java.io.*; 
import java.io.PrintWriter; 
import java.io.IOException; 
import java.io.InputStreamReader; 
import java.net.ServerSocket; 
import java.net.Socket; 

public class ChattyChatChatServer {
	public static void main(String[] args) throws IOException{
	    int clientNumber = 0; 
		boolean server = true; 
		ServerSocket listener = null; 
		 		
		 		try {
		 			
		 			listener = new ServerSocket(4000); 

		 			} catch (IOException e) {
		 				System.out.println("Error connecting to server"); 
		 				server = false; 
		 			}
		 		
		 		while (server) {
		 			Socket socket = null; 
		 			try {
		 				socket = listener.accept(); 
		 				System.out.println("New connection with client" + clientNumber); 
		 				PrintWriter out = new PrintWriter(
		 						socket.getOutputStream(), true);
		 				BufferedReader in = 
		 						new BufferedReader(
		 						new InputStreamReader(
		 						socket.getInputStream())); 
		 				
		 			    boolean done = false; 
		 				while (!done){
		 					String input = in.readLine(); 
		 					if (input == null || input.equals(".")) {
		 						done = true; 
		 					}
		 				
		 			} //END while (!done) 
		 				
		 			} catch (IOException e) {
		 				System.out.println("Error while talking with client" + clientNumber); 
		 			}
		 			
		 			finally {
		 				try {
		 					socket.close(); 
		 				}catch (Exception e) {
		 				   //it's okay
		 				}
		 			}
		 		}
		 		
		 	}//END main()
		 		  
		   } //END server
		   
		   	
		   			
		   			
		 	