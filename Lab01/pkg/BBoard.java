package pkg;
import java.util.*;
import java.io.*;

public class BBoard {		// This is your main file that connects all classes.
	// Think about what your global variables need to be.
	private static User currentUser;
	private static String title;
	private static ArrayList<User> userList;
	private static ArrayList<Message> msgList;
	private Scanner scanner = new Scanner(System.in);
	public Scanner getScanner() {
		return scanner;
	}

	// Default constructor that creates a board with a defaulttitle, empty user and message lists,
	// and no current user
	public BBoard() {
		currentUser = null;
		title = "Lwilbs BBoard";
		userList = new ArrayList<User>();
		msgList = new ArrayList<Message>();
	}

	// Same as the default constructor except it sets the title of the board
	public BBoard(String ttl) {	
		currentUser = null;
		title = ttl;
		userList = new ArrayList<User>();
		msgList = new ArrayList<Message>();
	}

	// Gets a filename of a file that stores the user info in a given format (users.txt)
	// Opens and reads the file of all authorized users and passwords
	// Constructs a User object from each name/password pair, and populates the userList ArrayList.
	public void loadUsers(String inputFile) throws FileNotFoundException {
		File userFile = new File(inputFile);
		Scanner in = new Scanner(userFile);
		while(in.hasNext()) {
			String line = in.nextLine();
			String[] info = line.split(" ");
			userList.add(new User(info[0], info[1]));
		}
		in.close();
		System.out.println(userList);
	}

	// Asks for and validates a user/password. 
	// This function asks for a username and a password, then checks the userList ArrayList for a matching User.
	// If a match is found, it sets currentUser to the identified User from the list
	// If not, it will keep asking until a match is found or the user types 'q' or 'Q' as username to quit
	// When the users chooses to quit, sayu "Bye!" and return from the login function
	public void login(){
		Scanner in = getScanner();
		while(true) {
			System.out.print("Enter your username ('Q' or 'q' to quit): ");
			String username = in.nextLine();
			if (username.equalsIgnoreCase("q")) {
				System.out.print("Bye!");
				return;
			}
			System.out.print("Enter your password: ");
			String password = in.nextLine();
			for (User u: userList) {
				if (u.check(username, password)) {
					currentUser = u;
					System.out.println("\nWelcome back " + username + "!");
					return;
				}
			}
			System.out.println("Invalid Username or Password\n");
		}
	}
	
	// Contains main loop of Bulletin Board
	// IF and ONLY IF there is a valid currentUser, enter main loop, displaying menu items
	// --- Display Messages ('D' or 'd')
	// --- Add New Topic ('N' or 'n')
	// --- Add Reply ('R' or 'r')
	// --- Change Password ('P' or 'p')
	// --- Quit ('Q' or 'q')
	// With any wrong input, user is asked to try again
	// Q/q should reset the currentUser to 0 and then end return
	// Note: if login() did not set a valid currentUser, function must immediately return without showing menu
	public void run(){
		Scanner in = getScanner();
		System.out.println(title);
		login();
		while (true) {	
			if (currentUser != null) {
				System.out.println("\nMenu");
				System.out.println("  - Display Messages ('D' or 'd')");
				System.out.println("  - Add New Topic ('N' or 'n)");
				System.out.println("  - Add New Reply to a Topic ('R' or 'r')");
				System.out.println("  - Change Password ('P' or 'p')");
				System.out.println("  - Quit ('Q' or 'q')");
				System.out.print("Choose an action: ");
				String choice = in.nextLine();

				switch(choice.toUpperCase()) {
					case "D":
						display();
						break;
					case "N":
						addTopic();
						break;
					case "R":
						addReply();
						break;
					case "P":
						setPassword();
						break;
					case "Q":
						currentUser = null;
						System.out.print("\nBye!");
						return;
					default: 
					System.out.println("\nWrong Input - Please enter another.");
				}
			}
			else {
				break;
			} 
		}
	}

	// Traverse the BBoard's message list, and invote the print function on Topic objects ONLY
	// It will then be the responsibility of the Topic object to invoke the print function recursively on its own replies
	// The BBoard display function will ignore all reply objects in its message list
	private void display(){
		if (msgList.size() == 0) {
			System.out.println("\nNothing to display");
			return;
		}
		for (Message m: msgList) {
			if (!m.isReply()) {
				System.out.println("\n--------------------------------------------");
				m.print(0);
				System.out.print("--------------------------------------------");
			}
		}
		System.out.println();
	}


	// This function asks the user to create a new Topic (i.e. the first message of a new discussion "thread")
	// Every Topic includes a subject (single line), and body (single line)

	/* 
	Subject: "Thanks"
	Body: "I love this bulletin board that you made!"
	*/

	// Each Topic also stores the username of currentUser; and message ID, which is (index of its Message + 1)

	// For example, the first message on the board will be a Topic who's index will be stored at 0 in the messageList ArrayList,
	// so its message ID will be (0+1) = 1
	// Once the Topic has been constructed, add it to the messageList
	// This should invoke your inheritance of Topic to Message
	private void addTopic(){
		Scanner in = getScanner();
		System.out.print("\nSubject: ");
		String subj = in.nextLine();
		System.out.print("Body: ");
		String body = in.nextLine();
		Message topic = new Topic(currentUser.getUsername(), "\"" + subj + "\"", body, msgList.size() + 1);
		msgList.add(topic);
	}

	// This function asks the user to enter a reply to a given Message (which may be either a Topic or a Reply, so we can handle nested replies).
	//		The addReply function first asks the user for the ID of the Message to which they are replying;
	//		if the number provided is greater than the size of messageList, it should output and error message and loop back,
	// 		continuing to ask for a valid Message ID number until the user enters it or -1.
	// 		(-1 returns to menu, any other negative number asks again for a valid ID number)
	
	// If the ID is valid, then the function asks for the body of the new message, 
	// and constructs the Reply, pushing back the Reply on to the messageList.
	// The subject of the Reply is a copy of the parent Topic's subject with the "Re: " prefix.
	// e.g., suppose the subject of message #9 was "Thanks", the user is replying to that message:


	/*
			Enter Message ID (-1 for Menu): 9
			Body: It was a pleasure implementing this!
	*/

	// Note: As before, the body ends when the user enters an empty line.
	// The above dialog will generate a reply that has "Re: Thanks" as its subject
	// and "It was a pleasure implementing this!" as its body.

	// How will we know what Topic this is a reply to?
	// In addition to keeping a pointer to all the Message objects in BBoard's messageList ArrayList
	// Every Message (wheather Topic or Reply) will also store an ArrayList of pointers to all of its Replies.
	// So whenever we build a Reply, we must immediately store this Message in the parent Message's list. 
	// The Reply's constructor should set the Reply's subject to "Re: " + its parent's subject.
	// Call the addChild function on the parent Message to push back the new Message (to the new Reply) to the parent's childList ArrayList.
	// Finally, push back the Message created to the BBoard's messageList. 
	// Note: When the user chooses to return to the menu, do not call run() again - just return fro mthis addReply function. 
	private void addReply(){
		Scanner in = getScanner();
		int id;
		System.out.println();
		while(true){	
			System.out.print("Enter Message ID (-1 for Menu): ");
			id = Integer.parseInt(in.nextLine());
			if (id > msgList.size()) {
				System.out.println("Invalid Message ID!");
				continue;
			}
			if (id == -1) {
				return;
			}
			else {
				break;
			}
		}
		Message lastMsg = msgList.get(msgList.size() - 1);
		System.out.print("Body: ");
		String body = in.nextLine();
		Message repl = new Reply(currentUser.getUsername(), "Re: " + msgList.get(id - 1).getSubject(), body, lastMsg.getId() + 1);
		msgList.get(id - 1).addChild(repl);
		msgList.add(repl);
	}

	// This function allows the user to change their current password.
	// The user is asked to provide the old password of the currentUser.
	// 		If the received password matches the currentUser password, then the user will be prompted to enter a new password.
	// 		If the received password doesn't match the currentUser password, then the user will be prompted to re-enter the password. 
	// 		The user is welcome to enter 'c' or 'C' to cancel the setting of a password and return to the menu.
	// Any password is allowed except 'c' or 'C' for allowing the user to quit out to the menu. 
	// Once entered, the user will be told "Password Accepted." and returned to the menu.
	private void setPassword(){
		while (true) {	
			System.out.print("\nOld Password ('c' or 'C' for Menu): ");
			Scanner in = getScanner();
			String oldPsw = in.nextLine();
			if (oldPsw.toUpperCase().equals("C")) {
				return;
			}
			if (currentUser.check(currentUser.getUsername(), oldPsw)) {
				while (true) {	
					System.out.print("Please enter your new password: ");
					String newPsw = in.nextLine();
					if (newPsw.toUpperCase().equals("C")) {
						System.out.println("Please put something other than 'c' or 'C'.\n");
						continue;
					}
					currentUser.setPassword(oldPsw, newPsw);
					System.out.println("Password Accepted");
					return;
				}
			}
			else {
				System.out.println("Invalid Password, please re-enter.");
			}}
	}
}
