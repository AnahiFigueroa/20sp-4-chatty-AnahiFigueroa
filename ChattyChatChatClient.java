import java.io.*;
import java.net.*;
import java.util.Scanner;


public class ChattyChatChatClient {
	private InputStream inputstream;
    static Scanner scanner;
    static String message;
    public ChattyChatChatClient(OutputStream outputstream) {
        this.inputstream = inputstream;
}

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

			//This thread will be used for sending the message to other clients.
			Thread sendmessage = new Thread(new Runnable() {
				  @SuppressWarnings("null")
				@Override
				  public void run() {
			        scanner = new Scanner(System.in);
					boolean done = false;

					 OutputStream outputstream = null;
						try {
							ObjectOutputStream output = new ObjectOutputStream(outputstream);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					while (!done) {

						String userInput = "";

						try {

							System.out.print("InputMessage: ");
		                    message = scanner.nextLine();
		                    System.out.println(message);
		                    ObjectOutputStream output = null;
							output.writeObject(message); //send the string to the server
		                    output.flush();
//						   BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));  //reading user's input from console
//                           System.out.println("Sending message: ");
//							while ((userInput = userIn.readLine()) != null) {
//								System.out.println("Inside while loop for sendmessage");
//								out.println(userInput);
//								System.out.println("echo: " + in.readLine());
//
//							}

//							userInput = userIn.readLine();
//							System.out.println("Sending in message:");

						} catch (IOException e) {
							e.printStackTrace();
						}

						System. out.println(userInput);
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

							while ((userInput = in.readLine()) != null) {
							System.out.println("Inside while loop for readmessage");
								out.println(userInput);
								System.out.println("echo: " + in.readLine());
							}
//							userInput = in.readLine();    //reads line
//							System.out.println(userInput);

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
