import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * @author shubhpatel, parth
 * Date: Dec 16, 2023
 * Description: This class represents a list of the AccountRecord class
 *Method List: 
 * 			-> public AccountList()
 * 			-> public int getSize() - getter for size
 * 			-> public AccountRecord getRecord(int i) - getter for record
 * 			-> public String toString() - to string method
 * 			-> public boolean insert(AccountRecord record) - inserts a record
 * 			-> public boolean delete(AccountRecord record) - deletes a record 
 * 			-> public boolean change(AccountRecord oldRecord, AccountRecord newRecord) - changes a record 
 * 			-> public boolean increaseSize(int sizeToAdd) - increases the size
 * 			-> public void insertionSort() - sorts by name 
 * 			-> public int binarySearch(String searchKey) - searches for name 
 * 			-> public void saveToFile(String fileName) - saves list to a new file 
 * 			-> public void readFromFile(String fileToOpen, boolean replace) - reads list from a file
 * 			-> public boolean checkPassword(int n, String input) - checks password with the passed in string 
 *
 */
public class AccountList {
	/**
	 * Private data/attributes
	 */
	private AccountRecord list[];
	private int size;
	private int maxSize;

	/**
	 * Default constructor
	 */
	public AccountList() {
		size = 0;
		maxSize = 0;
		list = new AccountRecord[maxSize];
	}

	/**
	 * Getter for size
	 */
	public int getSize() {
		return this.size;
	}

	/**
	 * getRecord Method 
	 * gets the record at a particular index
	 */
	public AccountRecord getRecord(int i) {
		return list[i];
	}

	/**
	 * toString method
	 */
	public String toString() {
		String output = "";

		for(int i = 0; i<this.size; i++) {
			output += list[i].toString() + "\n";
		}

		return output;
	}

	//----------------------------------------------------------------------//
	//--------------------Methods to Modify List----------------------------//
	//----------------------------------------------------------------------//

	/**
	 * method to insert elements into the list
	 * Checks if there is space (size if below maxSize)
	 * If so, it increases the size by 1
	 * and adds the record to the location just below size
	 * If the size is maxed out, it increases the size by 
	 * the amount of elements being added 
	 * returns true if successful
	 */
	public boolean insert(AccountRecord record) {
		// if the size is greater than or equal to maxSize
		if (size >= maxSize) {
			// increase the max size by however many elements are over the maxsize
			increaseSize(size-maxSize+1);

		}
		// if there is space
		 if (size < maxSize) {
			size++;  // increase by 1
			list[size - 1] = record;
			return true; // successful
		}
		return false; // not successful
	}

	/**
	 * Method to delete a record from the valid list
	 * It replaces the record to be deleted with the last record
	 * Then it decreases the size of the valid list.
	 * return true if successful
	 */
	public boolean delete(AccountRecord record) {
		// get the location from the binary search method
		int location = binarySearch(record.getChequing().getCustomer().getCustName());

		// if the location is found
		if(location >= 0) {
			// delete the list at location
			list[location] = list[size-1];
			size--;

			// sort the records by customer name
			insertionSort();

			return true; // if successful
		}
		return false; // if not successful
	}

	/**
	 * Method to change a record
	 * Deletes the old record
	 * Inserts a new record
	 * returns true if successful
	 */
	public boolean change(AccountRecord oldRecord, AccountRecord newRecord) {
		// delete the old record
		if (delete(oldRecord)) {
			insert(newRecord); // insert new record

			// sort the list alphabetically by customer name
			insertionSort();


			return true;
		}
		return false;   // oldR record is not found
	}

	/**
	 * Method to increase the size of the record
	 */
	public boolean increaseSize(int sizeToAdd) {
		// if sizeToAdd plus max size is greater than the maxSize
		if(sizeToAdd + maxSize > maxSize) {
			// create a newList with the new size
			AccountRecord [] newList = new AccountRecord[sizeToAdd+maxSize];
			for(int i = 0; i<size; i++) {
				newList[i] = list[i]; // make the new list equal to the old list
			}

			// equal the new list to the current list
			list = newList;
			maxSize += sizeToAdd; // change the max size to the new size
			return true;
		}

		return false;
	}

	//----------------------------------------------------------------------//
	//--------------------Methods to Search and Sort------------------------//
	//----------------------------------------------------------------------//

	/**
	 * Method to sort the by customer name 
	 * Uses Insertion Sort
	 */
	public void insertionSort() {
		// Iterate through the array starting from the second element (index 1)
		for (int top = 1; top < size; top++) {
			// get the current record for comparison
			AccountRecord record = list[top];
			int i = top;

			// while i is more than 0 and record to compare is greater than the record before it
			while(i > 0 && record.getChequing().getCustomer().getCustName().compareToIgnoreCase
					(list[i-1].getChequing().getCustomer().getCustName()) < 0) {
				// Shift the elements to make room for the current element
				list[i] = list[i-1];
				i--;
			}

			// Place the current element in its correct sorted position
			list[i] = record;
		}
	}

	/**
	 * Method to search for customer name
	 * Uses Binary Searching
	 */
	public int binarySearch(String searchKey) {
		int low = 0;
		int high = size-1;
		int middle;

		// while the low end is <= high end
		while(low <= high) {
			middle = (high + low)/2;  // divides the array in 2

			// check if the searchKey is found
			if(searchKey.compareToIgnoreCase(list[middle].getChequing().getCustomer().getCustName()) == 0) {
				return middle;
			}
			// lower end of the alphabet
			else if(searchKey.compareToIgnoreCase(list[middle].getChequing().getCustomer().getCustName()) < 0) {
				high = middle - 1; // change the high end of the list
			}
			else {
				low = middle + 1; // change the low end of the list
			}
		}

		return -1; // return invalid index if not found
	}


	//----------------------------------------------------------------------//
	//-----------------Methods to Read and Write to File--------------------//
	//----------------------------------------------------------------------//


	/**
	 * Method to Save records to a new file
	 */
	public void saveToFile(String fileName) throws IOException {
		// set up to write to a new file
		FileWriter fileW = new FileWriter(fileName);
		PrintWriter out = new PrintWriter(fileW);

		// print how many records there are
		out.println(size);

		// loop through each of the records 
		for(int i = 0; i<size; i++) {
			// create a tempe record 
			AccountRecord tempRecord = list[i];

			// output the customer info, chequing acc info and savings acc info
			// in a formatted string separated by "/"
			out.println(tempRecord.getChequing().getCustomer().getCustName() + "/" +
					tempRecord.getChequing().getCustomer().getCustAddress() + "/" +
					tempRecord.getChequing().getCustomer().getPhNumber() + "/" +
					tempRecord.getChequing().getCustomer().getPassword() + "/" +
					tempRecord.getChequing().getAccNum() + "/" +
					tempRecord.getChequing().getBalance() + "/" +
					tempRecord.getSavings().getAccNum() + "/" +
					tempRecord.getSavings().getBalance());
		}

		out.close(); // saving the file

	}

	/**
	 * Method to Read from a file
	 */
	public void readFromFile(String fileToOpen, boolean replace) throws IOException {
		// set up to read from file
		FileReader fileR = new FileReader(fileToOpen);
		BufferedReader in = new BufferedReader(fileR);

		// get the size to add from the first line
		int fileSize = Integer.parseInt(in.readLine());

		// if info needs to be replaced by the info in the file
		if(replace) {
			// set the size from the first line
			size = fileSize;  

			// create a new list
			AccountRecord [] tempList = new AccountRecord[size];

			// get the info and add it to the new list
			for(int i = 0; i<size; i++) {
				// make a temp record from the line in the file
				tempList[i] = new AccountRecord(in.readLine());

			}

			list = tempList;	// make the main list equal to the tempList
		}

		// if info needs to be added to the existing info
		else {
			// if adding the elements from the file
			// goes over the maxSize of the list
			if(size+fileSize > maxSize) {
				// increase the maxSize
				maxSize = size + fileSize;

				// create a new list with the max size
				AccountRecord [] newList = new AccountRecord[maxSize];

				// add the old info to the new list
				for(int i = 0; i<size; i++) {
					newList[i] = list[i];
				}

				// update the old list with the new increased list
				list = newList;

			}

			// loop from the size to the maximum limit and add the records
			for(int i = size; i<size+fileSize;i++) {
				list[i] = new AccountRecord(in.readLine());
			}

			// update the size of the list
			size+=fileSize;

		}

		// close the file
		in.close();

	}

	/**
	 * Method to check a password from a 
	 * record at a particular index to the given 
	 * password
	 */
	public boolean checkPassword(int n, String input) {
		// compare the password in the record and the input given 
		if(list[n].getChequing().getCustomer().getPassword().equals(input)) {
			return true;	// if they are the same
		}
		return false;	// if they are different
	}



	/**
	 * Self-testing Main Method
	 */
	public static void main(String[] args) {
		AccountList accounts = new AccountList();

		JTextField txtName = new JTextField("Will Smith"); 
		JTextField txtAddress = new JTextField("Earth"); 
		JTextField txtPhone = new JTextField("437-989-8980"); 
		JTextField txtPass = new JTextField("password"); 


		Object infoMessage [] = {
				"Name: ", txtName, 
				"Address: ", txtAddress, 
				"Phone No.: ", txtPhone,  
				"Password: ", txtPass
		};

		while (true) {
			char command;
			command = JOptionPane.showInputDialog(null, "i - Insert\n" + 
					"p - Print\n" + 
					"d - Delete\n" + 
					"c - Change\n" +
					"s - Increase Size\n" +
					"n - Insertion Sort (by name)\n" +
					"b - Binary Search (for name)\n" +
					"y - save record to new file\n" +
					"r - read from file\n" +
					"q - Quit", "i").charAt(0);

			if (command == 'q') {
				break;      // break from the while loop
			}

			switch (command)  {

			case 'i': { // insert

				// prompt user for info 
				int option = JOptionPane.showConfirmDialog(null, infoMessage, "Enter Info", JOptionPane.OK_CANCEL_OPTION);

				if (option == JOptionPane.OK_OPTION) {
					// create a new customer with the info
					Customer cst = new Customer(txtName.getText(), txtAddress.getText(), txtPhone.getText(), txtPass.getText());

					// make new accounts for those customer 
					Chequing c1 = new Chequing (cst);
					Savings s1 = new Savings(cst);

					// create the record object
					AccountRecord record = new AccountRecord(c1, s1);

					// insert it in the list
					if(accounts.insert(record)) {
						JOptionPane.showMessageDialog(null, "Record Inserted.");
					}
					else {
						JOptionPane.showMessageDialog(null, "Insert failed.");
					}
					break;
				}

			}
			case 'd': { // delete

				// prompt user for info 
				int option = JOptionPane.showConfirmDialog(null, infoMessage, "Enter Info", JOptionPane.OK_CANCEL_OPTION);

				if (option == JOptionPane.OK_OPTION) {
					// create a new customer with the info
					Customer cst = new Customer(txtName.getText(), txtAddress.getText(), txtPhone.getText(), txtPass.getText());

					// make new accounts for those customer 
					Chequing c1 = new Chequing (cst);
					Savings s1 = new Savings(cst);

					// create the record object
					AccountRecord record = new AccountRecord(c1, s1);

					// insert it in the list
					if(accounts.delete(record)) {
						JOptionPane.showMessageDialog(null, "Record Deleted.");
					}
					else {
						JOptionPane.showMessageDialog(null, "Delete failed.");
					}
					break;
				}

			}

			case 'c': { // change

				// prompt user for info 
				int option1 = JOptionPane.showConfirmDialog(null, infoMessage, "Enter Info", JOptionPane.OK_CANCEL_OPTION);

				if (option1 == JOptionPane.OK_OPTION) {

					int option2 = JOptionPane.showConfirmDialog(null, infoMessage, "Enter Info", JOptionPane.OK_CANCEL_OPTION);

					if (option2 == JOptionPane.OK_OPTION) {
						// create a new customer with the info
						Customer cst1 = new Customer(txtName.getText(), txtAddress.getText(), txtPhone.getText(), txtPass.getText());

						// make new accounts for those customer 
						Chequing c1 = new Chequing (cst1);
						Savings s1 = new Savings(cst1);

						// create the record object
						AccountRecord oldRecord = new AccountRecord(c1, s1);

						// create the second customer 
						Customer cst2 = new Customer(txtName.getText(), txtAddress.getText(), txtPhone.getText(), txtPass.getText());

						// make new accounts for those customer 
						Chequing c2 = new Chequing (cst2);
						Savings s2 = new Savings(cst2);

						// create the record object
						AccountRecord newRecord = new AccountRecord(c2, s2);

						// insert it in the list
						if(accounts.change(oldRecord, newRecord)) {
							JOptionPane.showMessageDialog(null, "Record Changed.");
						}
						else {
							JOptionPane.showMessageDialog(null, "Change failed.");
						}
						break;

					}


				}

			}

			case 's': {
				// ask for the new size
				int newSize = Integer.parseInt(JOptionPane.showInputDialog(null, 
						"Enter the increase in size"));

				if(accounts.increaseSize(newSize)) {
					JOptionPane.showMessageDialog(null, "Size increase successful");
				}
				else {
					JOptionPane.showMessageDialog(null, "Size increase failed");
				}

				break; 
			}

			case 'n': {
				accounts.insertionSort();
				break;
			}

			case 'b': {
				//ask for the search key
				String nameToFind = JOptionPane.showInputDialog(null, "Enter a name to find", "Will Smith");

				// call the binary search method 
				int makeLocation = accounts.binarySearch(nameToFind); 

				// if its found 
				if (makeLocation > 0) {
					JOptionPane.showMessageDialog(null, nameToFind + " Found!");
				}
				else { // if not found 
					JOptionPane.showMessageDialog(null, nameToFind + "NOT Found!"); 
				}

				break;
			}

			case 'y': {
				// prompt for and get the file name
				String fileName = JOptionPane.showInputDialog(null, "Enter the file name", "Account_List_Output_Test.txt");

				// call the saveToFile method 
				try {
					accounts.saveToFile(fileName);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "Error encountered when saving to file"); 
				} 

				break;
			}

			case'r': {
				// prompt for and get the file name to read from 
				String fileToRead = JOptionPane.showInputDialog(null, "Enter the file to read from", "Account_List_Input_Test.txt");

				// prompt for and get if they want to replace it or add to it
				String choice = JOptionPane.showInputDialog(null, "Enter 'TRUE' to replace and 'FALSE' to add", "TRUE");

				boolean replace; 

				if(choice.equals("TRUE")) {
					replace = true;
				}
				else {
					replace = false; 
				}

				// call the readFile method
				try {
					accounts.readFromFile(fileToRead, replace);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Error while reading from file");
				}

				break;
			}

			case 'p': {
				JOptionPane.showMessageDialog(null, accounts.toString());
			}

			}

		}



	}

}