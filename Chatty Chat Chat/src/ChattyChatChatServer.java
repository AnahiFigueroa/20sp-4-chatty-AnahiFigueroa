 import java.io.*; 
import java.net.*; 
import java.io.PrintWriter; 
import java.io.IOException; 
import java.io.InputStreamReader; 
import java.net.ServerSocket; 
import java.net.Socket; 

public class ChattyChatChatServer {
	public static void main(String[] args) throws IOException{
	    int clientNumber = 0; 
		boolean server = true; 
		Socket listener = null; 
		 		
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
		 				PrintWriter out = new PrintWriter(
		 						socket.getOutputStream(), true);
		 				out.println
		 			} catch (IOException e) {
		 				System.out.println("Error with socket"); 
		 			}
		 			
		 			finally {
		 				try {
		 					socket.close(); 
		 				}catch (Exception e) {
		 				
		 				}
		 			}
		 		}
		 		
		 	}//END main()
		 		  
		   } //END server
		   
		   	
		   			
		   			
		 	