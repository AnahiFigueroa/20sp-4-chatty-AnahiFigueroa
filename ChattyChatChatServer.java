import java.io.*;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class ChattyChatChatServer {
	static Vector<ChatHandler> chathandlers = new Vector <ChatHandler>();
	static int clientNumber = 0;

	public static void main(String[] args) throws IOException{
		boolean server = true;
		ServerSocket listener = null;
		int portNumber = Integer.parseInt(args[0]);

		listener = new ServerSocket(portNumber);
		System.out.println("Connecting to server");

 	  while (server) {
 			try {
 				ChatHandler chat = new ChatHandler(listener.accept(), clientNumber, "default");
 				System.out.println("Assigning thread to new client");
 				Thread a = new Thread(chat);

        synchronized(chathandlers) {
 				       chathandlers.add(chat);  //tell all the other threads that this new chat has joined
 				}
 				a.start();
 				clientNumber++;
 			} catch (IOException e) {
 				System.out.println("Error while talking with client" + clientNumber);
 			}

 		}
      listener.close();
 	}//END main()


} //END server
