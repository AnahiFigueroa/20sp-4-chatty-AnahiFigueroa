import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class readmessage extends Thread {
    private Socket Socket; 
    
    public readmessage (Socket socket) {
    	Socket = socket; 
    }
    
	
		@Override
		  public void run() {
			boolean done = false;
			while (!done) {
				String userInput = "";
				try {
					BufferedReader in = new BufferedReader(new InputStreamReader( Socket.getInputStream())); 
					userInput = in.readLine();
					System.out.println(userInput);
					Thread.sleep(100);
				} catch (IOException | InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				

			}//END while
		}

	
}
