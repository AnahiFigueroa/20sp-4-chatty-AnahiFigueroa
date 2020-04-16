chat: ChattyChatChatServer.chathandlers) {
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
