import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

/**
 * Author: Himansh Garg, parth
 *  Date: December 12, 2023 Description: Represents a list
 * of transaction records. This class provides methods for managing and
 * manipulating transaction records. Method List: - TransactionList():
 * Constructor for initializing a TransactionList object. -
 * insertTransaction(TransactionRecord transaction): Inserts a transaction into
 * the list. - deleteTransaction(TransactionRecord record): Deletes a
 * transaction from the list. - sortTransactions(): Sorts transactions based on
 * transaction amount in ascending order. - getSize(): Gets the current size of
 * the list. - setSize(int size): Sets the size of the list. - saveToFile(String
 * fileName): Saves the list into a file. - linearSearch(String uName): Performs
 * a linear search for transactions by username. -
 * listToString(TransactionRecord[] records): Converts a TransactionRecord array
 * to a formatted string. - insertionSort(): Sorts the list alphabetically by
 * username using insertion sort. - searchAmnt(String search): Searches for
 * transactions by transaction amount. - sortFile(String fileName): Loads data
 * from a file, sorts it, and saves it to a new file. - main(String[] args):
 * Self-testing main method for the TransactionList class.
 */

public class TransactionList {
	private TransactionRecord[] list;
	private int maxSize;
	private int size;

	// Default constructor
	public TransactionList() {
		this.size = 0;
		this.maxSize = 100;
		this.list = new TransactionRecord[maxSize];
	}

	/**
	 * Method to Insert a transaction into the list.
	 *
	 * @param transaction The TransactionRecord to be inserted.
	 * @return True if the insertion was successful, false otherwise.
	 */
	public boolean insertTransaction(TransactionRecord transaction) {
		// Validate the input data
		if (transaction == null || transaction.getUserName() == null
				|| transaction.getUserName().isEmpty()) {
			return false;
		}
		// if there
		if (size < maxSize) {
			list[size++] = transaction;
			return true;
		}
		if (size == maxSize) {
			maxSize = maxSize + 1;
			list[size++] = transaction;
			return true;

		}
		return false;
	}

	// Method to delete a transaction from the list based on index
	public boolean deleteTransaction(TransactionRecord record) {
		if (record == null) {
			return false;
		}

		int index = -1;
		for (int i = 0; i < size; i++) {
			if (list[i].equals(record)) {
				index = i;
				break;
			}
		}

		if (index != -1) {
			// Move the last element to the deleted position
			list[index] = list[size - 1];
			list[size - 1] = null;
			size--;
			return true;
		}

		return false;
	}

	// Method to sort transactions based on transaction amount in ascending order
	public void sortTransactions() {
		for (int i = 0; i < size - 1; i++) {
			for (int j = 0; j < size - i - 1; j++) {
				if (list[j].getTransactionAmount() > list[j + 1].getTransactionAmount()) {
					// Swap transactions if out of order
					TransactionRecord temp = list[j];
					list[j] = list[j + 1];
					list[j + 1] = temp;
				}
			}
		}
	}

	// Method to get the current size of the list
	public int getSize() {
		return size;
	}

	// Method to set the size of the list
	public void setSize(int size) {
		this.size = size;
	}

	// Method to save the list into a file
	public void saveToFile(String fileName) throws IOException {

		// set up to write to a new file

		FileWriter fileW = new FileWriter(fileName);

		PrintWriter out = new PrintWriter(fileW);

		out.println(size);

		for (int i = 0; i < size; i++) {

			// format the record properly and write it to file

			TransactionRecord tempRecord = list[i];

			out.println(tempRecord.getUserName() + "/" + tempRecord.getAccType() +
					"/" + tempRecord.getTransactionType()
					+ "/" + tempRecord.getTransactionAmount() + "/" +
					tempRecord.getStartBalance() + "/"
					+ tempRecord.getEndBalance());

		}

		out.close(); // saving the file

	}

	// Method to perform a linear search for transactions by user name

	public TransactionRecord[] linearSearch(String uName) {

		TransactionRecord[] matchingRecords = new TransactionRecord[size];

		int count = 0;

		for (int i = 0; i < size; i++) {
			String recordUsername = list[i].getUserName();
			if (recordUsername.equalsIgnoreCase(uName)) {
				matchingRecords[count] = list[i];
				count++;

			}
		}

		// Create a new array with the exact size of matching records or return an empty
		// array

		TransactionRecord[] result = new TransactionRecord[count];
		for (int i = 0; i < count; i++) {
			result[i] = matchingRecords[i];

		}
		return result;
	}

	// Method to convert TransactionRecord array to String

	public String listToString(TransactionRecord[] records) {

		String result = "Name\tAccount\tType\tAmount\tOld Balance\tNew Balance\n";
		for (int i = 0; i < 150; i++) {
			result += "-";
		}
		result += "\n";
		for (int i = 0; i < records.length; i++) {
			if (records[i] != null) {
				result += records[i].toString() + "\n";
			}
		}
		return result;
	}

	// Method to sort the list alphabetically by username using insertion sort

	public void insertionSort() {

		for (int top = 1; top < size; top++) {

			TransactionRecord key = list[top];

			int i = top;

			while (i > 0 && (key.getUserName().compareToIgnoreCase(list[i -
			                                                            1].getUserName()) < 0)) {

				list[i] = list[i - 1];

				i--;

			}
			list[i] = key;
		}
	}

	// Method to load data from a file, sort it, and save it to a new file
	public String searchAmnt(String search) {
		int count = 0;
		for (int i = 0; i < size; i++) {
			if ((list[i].getTransactionAmount()+"").equals(search)) {
				count++;
			}
		}
		TransactionRecord[] t = new TransactionRecord[count];
		int tIndex = 0; // Index for t array
		for (int i = 0; i < size; i++) {
			if ((list[i].getTransactionAmount() + "").equals(search)) {
				t[tIndex] = list[i];
				tIndex++; // Increment tIndex after assigning a value
			}
		}
		String s = listToString(t);
		return s;
	}

	public void sortFile(String fileName) throws IOException {

		// set up to read from file

		FileReader fileR = new FileReader(fileName);

		BufferedReader in = new BufferedReader(fileR);


		size = Integer.parseInt(in.readLine());


		for (int i = 0; i < size; i++) {

			TransactionRecord tempRecord = new TransactionRecord(in.readLine());

			list[i] = tempRecord;

		}


		for (int top = 1; top < size; top++) {

			TransactionRecord key = list[top];

			int i = top;

			while (i > 0 && (key.getUserName().compareToIgnoreCase(list[i - 1].getUserName()) < 0)) {

				list[i] = list[i - 1];

				i--;

			}

			list[i] = key;

		}


		in.close();


		this.saveToFile(fileName);

	}


	// Self-testing main method
	public static void main(String[] args) throws IOException {

		// Create a TransactionList object

		TransactionList transaction = new TransactionList();

		// Load and sort data from the "text" file

		try {

			transaction.sortFile("Transaction_List_Testing");

		} catch (IOException e) {

			System.err.println("Error loading and sorting data from the file: " +
					e.getMessage());

			return; // Exit the program if there's an error

		}

		// Prompt the user for the username to search for

		String usernameToSearch = JOptionPane.showInputDialog(null, "Enter username to search:");

		// Perform the linear search

		TransactionRecord[] matchingRecords =
				transaction.linearSearch(usernameToSearch);

		String resultString = transaction.listToString(matchingRecords);

		System.out.println(resultString);

		if (matchingRecords != null) {

			if (matchingRecords.length > 0) {

				System.out.println("Matching records for username '" + usernameToSearch + "':");

				for (int i = 0; i < matchingRecords.length; i++) {
					TransactionRecord record = matchingRecords[i];
					System.out.println(record.toString());
				}

			} else {

				System.out.println("No matching records found for username '" +
						usernameToSearch + "'.");

			}

		} else {

			System.err.println("An error occurred during the search.");

		}

	}

}