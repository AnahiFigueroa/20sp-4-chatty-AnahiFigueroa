import java.util.Vector;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class ChatHandler implements Runnable {
	int clientNumber = 0; 
	String clientName = "";
	Socket s; 
    
	public ChatHandler(Socket socket, int i, String a) {
		clientNumber = i; 
		clientName = a; 
		s = socket; 
	}
	
	public static void main(String[] args) {
		
   
     
	}
	
	@Override
	public void run() {
		PrintWriter out = null;
		try {
			out = new PrintWriter(s.getOutputStream(), true);
		} catch (IOException e1) {
			e1.printStackTrace();
		} 
		BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in)); 
		boolean done = false; 
		while (!done) {
			String userInput = ""; 
			try {
				userInput = userIn.readLine();
				userInput.split("");
				String[] input = null; 
				
				if(input[0] == "/quit") {
					System.out.println("Disconnecting from server: end of program");
					s.close(); 
				}
				
				if(input[0] == "/dm") {
					done = false; 
					System.out.println("Connecting to the server");
				}
			
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (userInput.contentEquals("/quit")) {
				done = true; 
			}
			out.println(userInput); 
			out.flush();                                  //resets it
			
		}//END while
		
	
	  
		
		
		
		
		
	}
	
}//Chat handler
		  

