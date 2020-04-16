import java.io.*;
import java.net.*;
import java.util.Scanner;


public class ChattyChatChatClient {


	public static void main (String[] args) {

		String hostname = args[0];   //taking in a string from argument
		Socket socket = null;
		int portNumber = Integer.parseInt(args[1]);
		Scanner scn = new Scanner(System.in);



		try {

			socket = new Socket (hostname, portNumber);
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
				  BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));
					boolean done = false;
					while (!done) {

						String userInput = "";

						try {

							userInput = userIn.readLine();
	           System.out.println("Sending in message:");

						} catch (IOException e) {
							e.printStackTrace();
						}

						 out.println(userInput);
					   out.flush();                                  //resets it

					}//END while
				}

			});


			Thread readmessage = new Thread(new Runnable() {
				@Override
				  public void run() {
					boolean done = false;

					while (!done) {

					String userInput = "";
						try {
							userInput = in.readLine();    //reads line
							System.out.println(userInput);

						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						// if (userInput.contentEquals("/quit")) {
						// 	done = true;
						// }
						   // System.out.println(userInput);

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
