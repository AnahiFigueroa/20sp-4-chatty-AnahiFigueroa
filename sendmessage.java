import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class sendmessage extends Thread {
   private Socket socket; 
   
   public sendmessage (Socket socket) {
	   this.socket = socket; 
   }
   

	Thread sendmessage = new Thread(new Runnable() {
		  @Override
		  public void run() {
			boolean done = false; 
			while (!done) {
				//String userInput = "";
				try {
					
					PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
					BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));
					out.println(userIn.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
				                            

			}//END while
		}

	});

   
   
   
   
   
}
