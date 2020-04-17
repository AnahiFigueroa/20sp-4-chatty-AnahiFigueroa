import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class ChatHandler implements Runnable {
	int clientNumber = 0;
	String clientName = "";
	Socket s;

	public ChatHandler(Socket socket, int i, String a ) {
		clientNumber = i;
		clientName = a;
		s = socket;

	}

  @Override
	public void run() {
		PrintWriter out = null;
		BufferedReader userIn = null;
		try {
			out = new PrintWriter(s.getOutputStream(), true);
	    userIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
			boolean done = false;

			while (!done) {
				String userInput = "";
				userInput = userIn.readLine();
				String[] input = userInput.split(" ");  //returns array of strings
        String command = input[0];
        // System.out.println(input[0] + ".");

				if(command.equals("/quit")) {
					done = true;
					ChattyChatChatServer.chathandlers.remove(this); //removing chathandler from vector
					System.out.println("Disconnecting from server: end of program");
					s.close();
				}
				else if(command.equals("/dm")) {
					String message = "";
					for(int i = 2; i < input.length; i++) {
						message += input[i] + " ";
					}

          String name = input[1];
					for ( ChatHandler chat: ChattyChatChatServer.chathandlers) {
						if(name.equals(chat.clientName)) {
							PrintWriter temp = new PrintWriter(chat.s.getOutputStream(), true);
							temp.println(message); //printing to the person's chathandler
						}
					}
				}
				else if (command.equals("/nick")) {
					clientName = input[1];
					System.out.println(clientName);
				}
				else {
					for ( ChatHandler chat: ChattyChatChatServer.chathandlers) {
							PrintWriter temp = new PrintWriter(chat.s.getOutputStream(), true);
							temp.println(userInput); //printing to the person's chathandler
					}
				}
			}//END while
		 } catch (IOException e1) {
			    e1.printStackTrace();
		}
	}
}//Chat handler class
