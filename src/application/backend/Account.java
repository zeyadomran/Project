package application.backend;

import java.util.ArrayList;

/**
 * @author Austin Shawaga
 *
 */

public class Account {
	
	private String username;
	private String name;
	private String password;
	private double balance;
	private ArrayList<Message> messages;
	private ArrayList<Transaction> log;
	/**
	 * Constructor for Account, initializes a new object of the class
	 * 
	 * @param uN  -> string representing username
	 * @param nm  -> string representing name
	 * @param pwd -> string representing password
	 * 
	 * 
	 */
	public Account(String uN, String nm, String pwd) {
		this.setUsername(uN);
		this.setName(nm);
		this.setPassword(pwd);
		this.balance = 0.0;
		this.messages = new ArrayList<Message>();
		this.log = new ArrayList<Transaction>();
	}

	/**
	 * Copy Constructor
	 *
	 * @param copyFrom -> the object of Account being copied
	 */
	public Account(Account copyFrom) {
		this.setUsername(copyFrom.getUsername());
		this.setName(copyFrom.getName());
		this.password = copyFrom.getPassword();
		this.setBalance(copyFrom.getBalance());
		this.messages = copyFrom.getMessages();
		this.log = copyFrom.getLog();
	}

	// *************************SETTERS*************************

	/**
	 * Sets username of an object with argument passed
	 * 
	 * @param uN -> String representing username
	 * 
	 */
	public void setUsername(String uN) {
		this.username = uN;
	}

	/**
	 * Sets first and last name of an object with the arguments passed
	 * 
	 * @param fN -> String representing a user's first name,
	 * @param lN -> String representing a user's last name
	 */
	public void setName(String nm) {
		this.name = nm;
	}

	/**
	 * Sets an objects password 
	 * 
	 * @param pwd -> String representing the password being set
	 */
	public void setPassword(String pwd) {
		// ensures that only a new password can be set, and not overridden
			this.password = pwd;
	}

	/**
	 * Sets object's balance. Error check within, argument can't be
	 * negative. If an error is detected, balance of the object is set to 0
	 * 
	 * @param bal -> the amount that the object's balance should be set to
	 */
	public void setBalance(double bal) {
		if (bal >= 0.0) {
			this.balance = bal;
		} else {
			this.balance = 0.0;
		}
	}
	
	/**
	 * Sets object's messages. 
	 * 
	 * @param msgs -> ArrayList with Object Message
	 */
	public void setMessages(ArrayList<Message> msgs) {
		for(Message msg : msgs) {
			this.messages.add(msg);
		}
	}
	
	/**
	 * Sets object's transactions. 
	 * 
	 * @param trans -> ArrayList with Object transaction
	 */
	public void setTransactions(ArrayList<Transaction> trans) {
		for(Transaction tran : trans) {
			this.log.add(tran);
		}
	}

	// *************************GETTERS*************************

	/**
	 * Get method for instance username
	 * 
	 * @return a String representing the object's instance username
	 */
	public String getUsername() {
		return this.username;
	}

	/**
	 * Get method for instance balance
	 * 
	 * @return a double representing the object's instance balance
	 */
	public double getBalance() {
		return this.balance;
	}
	
	/**
	 * Get method for instance password
	 * 
	 * @return a String representing the object's instance password
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * Method used to return the name as a String
	 * 
	 * @return a String representing the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Get method for ArrayList messages, encapsulation within to prevent privacy leaks
	 * 
	 * @return a copy of the object's instance 'messages', completely encapsulated.
	 */
	public ArrayList<Message> getMessages() {
		return new ArrayList<Message>(this.messages);
	}
	
	/**
	 * Get method for ArrayList log, encapsulation within to prevent privacy leaks
	 * 
	 * @return a copy of the object's instance 'log', completely encapsulated.
	 */
	public ArrayList<Transaction> getLog() {
		return new ArrayList<Transaction>(this.log);
	}

	// *************************Other_Methods*************************

	/**
	 * Function used to deposit a value into the object's balance. Error checking
	 * within to ensure the argument is greater then zero. If an argument is
	 * invalid, nothing is added to the object's balance
	 * 
	 * @param amount -> a double representing the amount to deposit within the
	 *               object's balance
	 * @return 1 -> success
	 * @return 0 -> amount less than 0
	 */
	public double deposit(double amount) {
		if (amount > 0) {
			this.balance += amount;
			this.addTransaction("Deposit", "Deposit to account", amount);
			return this.balance;
		}
		return this.balance;
	}
	
	/**
	 * Function used to deposit a value into the object's balance. Error checking
	 * within to ensure the argument is greater then zero. If an argument is
	 * invalid, nothing is added to the object's balance
	 * 
	 * @param amount -> a double representing the amount to deposit within the
	 *               object's balance
	 * @param fr-> user the money is deposited from
	 * @return 1 -> success
	 * @return 0 -> amount less than 0
	 */
	public int deposit(double amount, String fr) {
		if (amount > 0) {
			this.balance += amount;
			this.addTransaction("Transfer", "Transfer from " + fr, amount);
			return 1;
		}
		return 0;
	}

	/**
	 * Function used to subtract a value from the object's balance. Error checking
	 * within to ensure the argument is able to be subtracted from the balance. If
	 * the argument is invalid and error message is displayed to console, and the
	 * balance remains unchanged.
	 * 
	 * @param amount -> a double representing the amount to withdraw from an
	 *               object's balance
	 * @return 1 -> success
	 * @return 0 -> balance less than amount             
	 */
	public double withdraw(double amount) {
		if (amount > this.getBalance()) {
			return this.balance;
		} else {
			this.balance -= amount;
			this.addTransaction("Withdraw", "Withdraw from account", (-1 * amount));
			return this.balance;
		}
	}
	
	/**
	 * Function used to subtract a value from the object's balance. Error checking
	 * within to ensure the argument is able to be subtracted from the balance. If
	 * the argument is invalid and error message is displayed to console, and the
	 * balance remains unchanged.
	 * 
	 * @param amount -> a double representing the amount to withdraw from an
	 *               object's balance
	 * @param to -> user the money is withdrawed for
	 * @return 1 -> success
	 * @return 0 -> balance less than amount             
	 */
	public boolean withdraw(double amount, String to) {
		if (amount > this.getBalance()) {
			return false;
		} else {
			this.balance -= amount;
			this.addTransaction("Transfer", "Transfer to " + to, (-1 * amount));
			return true;
		}
	}

	/**
	 * Function used to access if a String matches the object's password
	 * 
	 * @param guess -> A String to be compared with the object's password
	 * @return A Boolean value. True: if the argument matches the object's password.
	 *         False: if the argument does not match the object's password
	 */
	public boolean passwordMatch(String guess) {
		return (this.password.equals(guess));
	}

	/**
	 * Function allowing for adding messages
	 * 
	 * @param fr  -> string representing the sender's username
	 * @param to  -> string representing reciever's username
	 * @param sub  -> string representingthe subject of the message
	 * @param con -> string representing the content of the message
	 */
	public void addMessage(String fr, String to, String sub, String con ) {
		this.messages.add(new Message(fr, to, sub, con));
	}
	
	/**
	 * Method for adding transactions
	 * 
	 * @param type  -> string representing the type of the transaction
	 * @param note  -> string representing the transaction's description
	 * @param amount  -> double representing the amount
	 */
	public void addTransaction(String type, String note, double amount) {
		this.log.add(new Transaction(type, note, amount));
	}

	/**
	 * Function that displays all the messages sent to this instances account
	 */
	public void displayMessages() {
		ArrayList<Message> m = this.getMessages();
		System.out.println("****************************START_OF_MESSAGES****************************");
		if (m.size() == 0) {
			System.out.println("You have no messages!");
		} else {
			for (int i = 0; i < m.size(); i++) {
				System.out.println(String.valueOf(i + 1) + ") \t");
				System.out.println("From: \t" + m.get(i).getSender() + "\nSubject: \t" + m.get(i).getSubject() + "\nContent: \t" + m.get(i).getContent() + "\nTime: \t" + m.get(i).getTimestamp() + "\n");
			}
		}
		System.out.println("*****************************END_OF_MESSAGES*****************************");
	}
	
	/**
	 * Function that displays all the transaction made by this instances account
	 */
	public void displayLog() {
		ArrayList<Transaction> log = this.getLog();
		System.out.println("****************************START_OF_LOG****************************");
		if (log.size() == 0) {
			System.out.println("You have no transactions!");
		} else {
			for (int i = 0; i < log.size(); i++) {
				System.out.println(String.valueOf(i + 1) + ") \t");
				System.out.println("Type: \t" + log.get(i).getType() + "\nNote: \t" + log.get(i).getNote() + "\nAmount: \t" + log.get(i).getAmount() + "\nTime: \t" + log.get(i).getTimestamp() + "\n");
			}
		}
		System.out.println("*****************************END_OF_LOG*****************************");
	}

}
