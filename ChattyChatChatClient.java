import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class ChatHandler implements Runnable {
	int clientNumber = 0;
	String clientName = "";
	DataInputStream dis = null;
  DataOutputStream dos = null; 
	Socket s;

	public ChatHandler(Socket socket, int i, String a ) {
		clientNumber = i;
		clientName = a;
		s = socket;
		this.dis = dis;
		this.dos = dos;
	}


	@SuppressWarnings("null")
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
					done = true;
					ChattyChatChatServer.chathandlers.remove(this); //removing chathandler from vector
					System.out.println("Disconnecting from server: end of program");
					s.close();
				}

				else if(input[0] == "/dm") {
					String message = "";
					for(int i = 2; i < input.length; i++) {
						message += input[i] + " ";
					}
					for ( ChatHandler chat: ChattyChatChatServer.chathandlers) {
						if(input[1]== chat.clientName) {
							PrintWriter temp = new PrintWriter(chat.s.getOutputStream(), true);
							temp.println(message); //printing to the person's chathandler
						}
					}


				}
				else if (input[0] == "/nick") {
					clientName = input[1];
				}
				else {
					String message = "";
					for(int i = 0; i < input.length; i++) {
						message += input[i] + " ";
					}
					for ( ChatHandler chat: ChattyChatChatServer.chathandlers) {
							PrintWriter temp = new PrintWriter(chat.s.getOutputStream(), true);
							temp.println(message); //printing to the person's chathandler

					}


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

}//Chat handler class
