import java.io.*;
import java.io.IOException;
import java.net.*;


public class ChattyChatChatClient {


	public static void main (String[] args) {

		String hostname = args[0];   //taking in a string from argument
		Socket socket = null;
		int portNumber = Integer.parseInt(args[1]);



		try {

			socket = new Socket (hostname, portNumber);
			System.out.println("Connected to server\n");
			BufferedReader in =
				new BufferedReader(
				new InputStreamReader(
				socket.getInputStream()));

			PrintWriter out = new PrintWriter(socket.getOutputStream(),true);

			//one thread needs to send messages and another threads needs to read messages
			//each thread needs to call @override public void run

			Thread sendmessage = new Thread(new Runnable() {
				  @Override
				  public void run() {

					boolean done = false;
					while (!done) {
						String userInput = "";
						try {
						   BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));  //reading user's input from console
							 userInput = userIn.readLine();
							if(userInput != null && userInput != "/quit") {
								out.println(userInput);
								out.flush();
							} else {
								done = true;
							}

						} catch (IOException e) {
							e.printStackTrace();
						}
					}//END while
				}

			});


			Thread readmessage = new Thread(new Runnable() {
				@Override
				  public void run() {
					boolean done = false;

					while (!done) {
						try {
              String userInput = in.readLine();
							if ( userInput != null && userInput != "/quit") {
								System.out.println(userInput);
							} else {
								System.out.println("Client is quitting");
								done = true;
								System.exit(0);
							}
						} catch (IOException e) {

							e.printStackTrace();
						}

					}//END while
				}

			});

	sendmessage.start();
	readmessage.start();

		}catch (Exception e) {
			e.printStackTrace();
		}

	}//END main()

  } //END client
