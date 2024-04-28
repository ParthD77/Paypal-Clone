/**

 * Author: Himansh Garg, parth

 * Date: December 10, 2023

 * Description: Represents a Transaction Record.

 * Method List:

 * - TransactionRecord(): Default constructor for initializing a
TransactionRecord object.

 * - getUserName(): Gets the user's name.

 * - setUserName(String userName): Sets the user's name.

 * - getAccType(): Gets the account type.

 * - setAccType(char type): Sets the account type.

 * - getTransactionType(): Gets the transaction type.

 * - setTransactionType(String transaction): Sets the transaction type.

 * - getTransactionAmount(): Gets the transaction amount.

 * - setTransactionAmount(double amount): Sets the transaction amount.

 * - getStartBalance(): Gets the starting balance.

 * - setStartBalance(double balance): Sets the starting balance.

 * - getEndBalance(): Gets the ending balance.

 * - setEndBalance(double balance): Sets the ending balance.

 * - processRecord(String transactionRecord): Processes incoming
information and updates attributes from a string.

 * - toString(): Gets a formatted string representation of the
transaction record.

 * - TransactionRecord(String transactionRecord): Constructor for
initializing a TransactionRecord from a string.

 * - TransactionRecord(String name, int accType, int transactionType,
String amount,

 * String startbal, String endBal): Constructor for initializing a
TransactionRecord with specific attributes.

 * - TransactionRecord(String name, char c, String string, double amt, double d,

 * double balance): Constructor for initializing a TransactionRecord
from individual attributes.

 * - main(String[] args): Self-testing main method for the
TransactionRecord class.

 */



public class TransactionRecord {

	private String userName; // User's name

	private char accType; // Account type (s for Savings, c for Chequing)

	private String transactionType; // Transaction type (Deposit or Withdrawal)

	private double transactionAmount; // Transaction amount

	private double startBalance; // Starting balance

	private double endBalance; // Ending balance


	// Default constructor

	public TransactionRecord() {

		this.userName = "";

		this.accType = ' ';

		this.transactionType = "";

		this.transactionAmount = 0;

		this.startBalance = 0;

		this.endBalance = 0;

	}


	/**

	 * @return the userName

	 */

	public String getUserName() {

		return userName;

	}


	/**

	 * @param userName the userName to set

	 */

	public void setUserName(String userName) {

		this.userName = userName;

	}


	/**

	 * @return the account Type

	 */

	public char getAccType() {

		return accType;

	}


	/**

	 * @param set account Type

	 */

	public void setAccType(char type) {

		this.accType = type;

	}


	/**

	 * @return the transaction Type

	 */

	public String getTransactionType() {

		return transactionType;

	}


	/**

	 * @param set transaction Type

	 */

	public void setTransactionType(String transaction) {

		this.transactionType = transaction;

	}


	/**

	 * @return the transactionAmount

	 */

	public double getTransactionAmount() {

		return transactionAmount;

	}


	/**

	 * @param set transaction Amount

	 */

	public void setTransactionAmount(double amount) {

		this.transactionAmount = amount;

	}


	/**

	 * @return the start Balance

	 */

	public double getStartBalance() {

		return startBalance;

	}


	/**

	 * @param type the type to set

	 */

	public void setStartBalance(double balance) {

		this.startBalance = balance;

	}


	/**

	 * @return the end Balance

	 */

	public double getEndBalance() {

		return endBalance;

	}


	/**

	 * @param type the type to set

	 */

	public void setEndBalance(double balance) {

		this.endBalance = balance;

	}


	/**

	 * /* Method to process incoming information assumes: User name, account type,

	 * transaction type, transaction amount, start and ending balance

	 */


	public void processRecord(String transactionRecord) {

		String tData[] = transactionRecord.split("/");

		if (tData.length == 6) {

			this.userName = tData[0];

			this.accType = tData[1].charAt(0);

			this.transactionType = tData[2];

			this.transactionAmount = Double.parseDouble(tData[3]);

			this.startBalance = Double.parseDouble(tData[4]);

			this.endBalance = Double.parseDouble(tData[5]);

		}

	}


	/**

	 * method to take input data and convert into the appropriate format to update

	 * the properties of a TransactionRecord object. It maps and assigns the

	 * provided data to the relevant attributes of the object.

	 */

	public void processRecord(String name, int accType, int
			transactionType, String amount, String startbal,

			String endBal) {


		if (accType == 1) {

			accType = 's';

		} else {

			accType = 'c';

		}


		String type;

		if (transactionType == 0) {

			type = "Deposit";

		} else {

			type = "Withdraw";

		}


		this.userName = name;

		this.accType = (char) accType;

		this.transactionType = type; // update the private data

		this.transactionAmount = Double.parseDouble(amount);

		this.startBalance = Double.parseDouble(startbal);

		this.endBalance = Double.parseDouble(endBal);


	}


	/**

	 * @return A formatted string representation of the transaction record.

	 */

	public String toString() {

		String type;

		if (accType == 's') {

			type = "Savings" ;

		}

		else {

			type = "Chequing";

		}

		return userName + "\t" + type + "\t" + transactionType + "\t" +
		transactionAmount + "\t" + startBalance

		+ "\t" + endBalance;

	}


	/**

	 * Create an overloaded constructor that initializes the data by calling the

	 * method above. Include a String input as an argument to this constructor..

	 */

	public TransactionRecord(String transactionRecord) {

		processRecord(transactionRecord);

	}


	/**

	 * Initializes a TransactionRecord object with specific attribute values passed

	 * as arguments.

	 */


	public TransactionRecord(String name, int accType, int
			transactionType, String amount, String startbal,

			String endBal) {


		processRecord(name, accType, transactionType, amount, startbal, endBal);

	}


	/**

	 * Creates a TransactionRecord object from individual attributes.

	 */

	public TransactionRecord(String name, char c, String string, double
			amt, double d, double balance) {

		this.userName = name;

		this.accType = c;

		this.transactionType = string;

		this.transactionAmount = amt;

		this.startBalance = d;

		this.endBalance = balance;

	}


	/*

	 * Self testing main method

	 */


	public static void main(String[] args) {

		// Test the TransactionRecord class


		// Sample transaction record string

		String acc = "Full Name/s/Deposit/12.0/832.27/842.77";


		// Create an object of TransactionRecord

		TransactionRecord record = new TransactionRecord();


		// Display the default object state

		System.out.println("Default Constructor:\n" + record);


		// Test the processRecord method by parsing the sample transaction record

		record.processRecord(acc);


		// Display the object state after processing the record

		System.out.println("\nAfter processing record:\n" + record);


		// Test setters to update object attributes

		record.setAccType('c');

		record.setTransactionType("deposit");

		record.setTransactionAmount(2009);

		record.setStartBalance(0);

		record.setEndBalance(10);


		// Display the object state after setting values

		System.out.println("\nAfter setting values:\n" + record);


		// Test Getters to retrieve object attributes

		System.out.println("\nGetters:");

		System.out.println("Account Type: " + record.getAccType());

		System.out.println("Transaction Type: " + record.getTransactionType());

		System.out.println("Transaction Amount: " + record.getTransactionAmount());

		System.out.println("Start Balance: " + record.getStartBalance());

		System.out.println("End Balance: " + record.getEndBalance());

	}

}