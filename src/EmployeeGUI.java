import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * 
 */

/**
 * @author shubhpatel
 * Date: 16 Dec 2023 
 * Description: This class is the GUI for the employee portal of the 
 * 				application 
 * 
 * Method List: 
 * 			-> public EmployeeGUI() - window constructor 
 * 			-> private JButton createButton(String name) - method to create the buttons
 * 			-> private char randomChar() - generates a random character
 * 			-> private String generatePassword() - generates a random password
 * 			-> public void actionPerformed(ActionEvent e) - Action Listener
 * 			-> public static void main(String[] args) - main method 
 *
 */
public class EmployeeGUI extends JFrame implements ActionListener {
	/**
	 * Private attributes/data
	 */
	private AccountList accounts; 
	private JPanel titleP, butP, outputP, controlP, backGndP;
	private JLabel title; 
	private JTextArea output;
	private JScrollPane scrollPane; 
	private JButton btnCreateNew, btnChange, btnDelete, btnSortName, btnSearch, btnImport, btnExport, btnLogout, 
	btnResetPassword, btnSaveChanges, btnLoadCurrentCustomers;
	private boolean saved; 
	private JTextField txtAddress, txtPhoneNo, txtChequingBalance, txtSavingsBalance, txtPassword, txtName;

	/**
	 * Default Constructor
	 */
	public EmployeeGUI() {
		// set title 
		super("Employee Portal");

		accounts = new AccountList(); 	// initializing the account list

		setLayout(null); 	// set layout to null for the window 

		// create the backGnd panel 
		backGndP = new JPanel(); 
		backGndP.setBounds(0, 100, 1000, 600);
		backGndP.setBackground(new Color(165, 197, 232));

		// create the title panel 
		titleP = new JPanel(); 
		titleP.setBounds(0, 0, 1000, 100);
		titleP.setLayout(null);

		// create the title imag e
		title = new JLabel(new ImageIcon("src/Images/EmployeeTitle.png"));
		title.setBounds(0,0, 1000, 100); 
		// add the image to the title panel 
		titleP.add(title);

		// create the logout button
		btnLogout = new JButton(new ImageIcon("src/Images/SignOutBut.png"));
		btnLogout.setBounds(864, 33, 75, 34);
		btnLogout.setBorderPainted(false);
		btnLogout.setContentAreaFilled(false);
		btnLogout.setFocusable(false);
		// add to action listener 
		btnLogout.addActionListener(this);

		// create the button panel for the logout button
		butP = new JPanel(); 
		butP.setLayout(null); 
		butP.setBounds(0, 0, 1000, 100);
		butP.add(btnLogout); 	// add logout btn to the button panel


		// create the output panel to display info
		outputP = new JPanel(); 
		outputP.setBounds(89, 133, 850, 380);
		outputP.setBackground(new Color(165, 197, 232));

		// create the control panel for all the buttons 
		controlP = new JPanel(); 
		controlP.setBounds(90, 530, 821, 100);
		controlP.setBackground(new Color(165, 197, 232));

		// create the output area to display the info
		output = new JTextArea(23, 70); 

		scrollPane = new JScrollPane(output); 	// add the scrollpane to the output panel
		outputP.add(scrollPane); 

		// create all the buttons using the createButton method 
		// which adds them to the panel and the actionListener method
		btnCreateNew = createButton("Insert New");
		btnChange = createButton("Change Info");
		btnDelete = createButton("Delete"); 
		btnResetPassword = createButton("Change Password");
		btnSaveChanges = createButton("Save Changes"); 
		btnLoadCurrentCustomers = createButton("Load Current Customers");
		btnSortName = createButton("Sort by Name");
		btnSearch = createButton("Search"); 
		btnImport = createButton("Import");
		btnExport = createButton("Export"); 

		// create all the text fields for user input 
		txtName = new JTextField(); 
		txtAddress = new JTextField(); 
		txtPhoneNo = new JTextField(); 
		txtChequingBalance = new JTextField(); 
		txtSavingsBalance = new JTextField(); 
		txtPassword = new JTextField(); 

		// initialize saved to false
		saved = false; 

		// add all the panels to the window
		add(titleP);
		add(butP);
		add(outputP); 
		add(controlP); 
		add(backGndP); 

		// make the output area not editable 
		output.setEditable(false); 

		// set size, set visible and close on 'x'
		setSize(1000, 700); 
		setDefaultCloseOperation(EXIT_ON_CLOSE); 
		setVisible(true); 
	}

	/**
	 * Method to create the buttons, add them to a panel, 
	 * and add them a s action listener objects 
	 */
	private JButton createButton(String name) {
		JButton button = new JButton(name); 
		button.addActionListener(this);
		controlP.add(button); 
		return button; 
	}

	/**
	 * Method to create random alpha-numeric characters 
	 */
	private char randomChar() {
		// generate a random number from 0 to 62 (inclusive)
		// this is because there are 10 numbers, 26 lower-case letters, 
		// and 26 upper-case letters 
		int character = (int) (Math.random() * 62);

		// if the character is from 0-9
		if (character < 10) {
			// return a random char from 0-9
			return (char) (character + 48);

		}

		// if the character is from A-Z
		else if (character < 36) {
			// return a random char from A-Z
			return (char) (character + 55);

		}

		// if the character is from a-z
		else {
			// return a random char from a-z
			return (char) (character + 61);

		}

	}

	/**
	 * Method to generate random 10 character
	 * random password 
	 */
	private String generatePassword() {
		// Declare and initialize variable for the random password
		String randomPassword = "";

		// add 10 random characters to the randomPassword 
		for(int i = 0; i<10; i++) {
			randomPassword += randomChar(); 
		}

		// return the random password 
		return randomPassword; 
	}

	/**
	 * Action listener Method
	 */
	public void actionPerformed(ActionEvent e) {
		// object array of the message to ask for name 
		// outside any if statements as it is used in multiple 
		// prompts
		Object [] message = { 
				"Name: " , txtName};

		// if creating a new record 
		if(e.getSource() == btnCreateNew) {
			// message for creating the record
			Object [] messageForCreate = { "Name: " , txtName,
					"Address: " , txtAddress , 
					"Phone No.: " , txtPhoneNo ,
					"Chequing Balance: " , txtChequingBalance , 
					"Savings Balance: " , txtSavingsBalance, 
					"Password: ", txtPassword
			};

			// prompt for and get the customer info 
			int option = JOptionPane.showConfirmDialog(null, messageForCreate, "Create New", JOptionPane.OK_CANCEL_OPTION);

			// if user presses ok 
			if(option == JOptionPane.OK_OPTION) {
				// create a new customer from that info
				Customer c = new Customer(txtName.getText(), txtAddress.getText(), txtPhoneNo.getText(), txtPassword.getText());

				// create a new chequing with that customer and deposit initial value
				Chequing cAcc = new Chequing(c);
				cAcc.deposit(Double.parseDouble(txtChequingBalance.getText()));

				// create a new savings with that customer and deposit initial value
				Savings sAcc = new Savings(c);
				sAcc.deposit(Double.parseDouble(txtSavingsBalance.getText()));

				// create a record based on those accounts
				AccountRecord record = new AccountRecord(cAcc, sAcc); 

				// if the record is inserted 
				if(accounts.insert(record)) {
					JOptionPane.showMessageDialog(null, "Inserted");
				}
				else {	// if it fails to insert
					JOptionPane.showMessageDialog(null, "Insert Failed! Try again!");
				}

			}
			else {	// if user presses cancel 
				JOptionPane.showMessageDialog(null, "Insert Cancelled"); 
			}

			// display the updated info to the user 
			output.setText(accounts.toString());
			saved = false; 	// info is not saved
		}

		// if btnDelete is pressed 
		else if(e.getSource() == btnDelete) {
			// ask for the name to be deleted
			int option = JOptionPane.showConfirmDialog(null, message, "Delete", JOptionPane.OK_CANCEL_OPTION);

			if(option == JOptionPane.OK_OPTION) {
				accounts.insertionSort(); 	// sort the list

				// search for and get the record's index
				int index = accounts.binarySearch(txtName.getText()); 

				// make a record based on that index
				AccountRecord record = accounts.getRecord(index); 

				if(accounts.delete(record)) {	// if delete is successful
					JOptionPane.showMessageDialog(null, "Deleted"); 
				}
				else {	// if delete is unsuccessful or user not found
					JOptionPane.showMessageDialog(null, "Delete Failed! Record not Found!");
				}
			}
			else {	// if user presses cancel
				JOptionPane.showMessageDialog(null , "Delete Cancelled"); 
			}

			// update the output
			output.setText(accounts.toString());

			saved = false; 	// the list is unsaved

		}
		else if(e.getSource() == btnChange) {
			// prompt for and get the name to change
			int option = JOptionPane.showConfirmDialog(null, message, "Change From", JOptionPane.OK_CANCEL_OPTION);

			if(option == JOptionPane.OK_OPTION) {

				// sort and search for the name
				accounts.insertionSort(); 
				int oldRecordIndex = accounts.binarySearch(txtName.getText()); 

				if(oldRecordIndex >= 0) {	// if the name exists in the records

					// make a new record based on the index
					AccountRecord oldRecord = accounts.getRecord(oldRecordIndex); 

					// object message for the new information
					Object [] messageForChange = {
							"New Name: ", txtName, 
							"New Address: ", txtAddress, 
							"New Phone No.: ", txtPhoneNo
					};

					// prompt for and get the new information
					int option2 = JOptionPane.showConfirmDialog(null, messageForChange, "Change To", JOptionPane.OK_CANCEL_OPTION);

					if(option2 == JOptionPane.OK_OPTION) {
						// create a new customer with the info
						Customer c2 = new Customer(txtName.getText(), txtAddress.getText(), txtPhoneNo.getText(), 
								oldRecord.getChequing().getCustomer().getPassword());

						// set the new customer to the account 
						oldRecord.getChequing().setCustomer(c2);
						oldRecord.getSavings().setCustomer(c2);

						// create a record with the new accounts 
						AccountRecord newRecord = new AccountRecord(oldRecord.getChequing(), oldRecord.getSavings());

						// make sure the accounts are sorted
						accounts.insertionSort(); 

						// if change is successful
						if(accounts.change(oldRecord, newRecord)) {
							JOptionPane.showMessageDialog(null, "Changed"); 

						}
						else {	// if change fails
							JOptionPane.showMessageDialog(null, "Change Failed! Try again!");

						}
					}
				}

				else {	// if the name is not found
					JOptionPane.showMessageDialog(null, "Name not Found! Try again!");
				}
			}
			else {	// if user presses cancel
				JOptionPane.showMessageDialog(null , "Change Cancelled"); 

			}

			// update the output info
			output.setText(accounts.toString());

			saved = false; 	// info is unsaved

		}

		// if user wants to reset password 
		else if(e.getSource() == btnResetPassword) {
			// get the name of the customer 
			int option = JOptionPane.showConfirmDialog(null, message, "Change Password", JOptionPane.OK_CANCEL_OPTION);

			if(option == JOptionPane.OK_OPTION) {
				// sort the accounts
				accounts.insertionSort(); 

				// search for name
				int recordIndex = accounts.binarySearch(txtName.getText()); 

				// if name is found 
				if(recordIndex >= 0) {
					// get the record from the index
					AccountRecord record = accounts.getRecord(recordIndex);

					// warn user that the password will be reset to random 
					int option2 = JOptionPane.showConfirmDialog(null, 
							"This will reset " + txtName.getText() + "'s password to a random password!\n\t\t\t\t"
									+ "Press OK to proceed.", "Reset Password", JOptionPane.OK_CANCEL_OPTION);

					if(option2 == JOptionPane.OK_OPTION) {
						// make the random password 
						String randomPassword = generatePassword(); 

						// set the password of the account to the random password
						record.getChequing().getCustomer().setPassword(randomPassword);

						// display confirmation
						JOptionPane.showMessageDialog(null, txtName.getText() + 
								"'s password was successfully reset to: \"" + randomPassword + "\"\nSave this for future purposes!");

					}

				}
				else {	// if name is not found 
					JOptionPane.showMessageDialog(null, "Record not Found! Try Again!");
				}


			}
			else {	// if user presses cancel 
				JOptionPane.showMessageDialog(null, "Password Reset Cancelled");
			}

			saved = false; 	// info is not saved 

		}

		// if btnSaveChanges is pressed 
		else if(e.getSource() == btnSaveChanges) {
			try {
				// save the info to the master file 
				accounts.saveToFile("Customer_Info.txt");
				saved = true;	// info is saved
			} catch (IOException e1) {
				// throw any errors 
				e1.printStackTrace();
			}  
		}

		// if user wants to load current customers 
		else if(e.getSource() == btnLoadCurrentCustomers) {
			try {
				// read from the mast file 
				accounts.readFromFile("Customer_Info.txt", true);
				output.setText(accounts.toString()); 	// update the output
			} catch (IOException e1) {
				// throw any errors
				e1.printStackTrace();
			} 
		}

		// if user wants to sort 
		else if(e.getSource() == btnSortName) {
			accounts.insertionSort(); 	// sort by name

			// update the output info area
			output.setText(accounts.toString());
		}

		// if user wants to search 
		else if(e.getSource() == btnSearch) {
			// prompt for and get the name to search for 
			String name = JOptionPane.showInputDialog(null, "Enter a name to find"); 

			// make sure it's sorted 
			accounts.insertionSort(); 

			// search for the name 
			int location = accounts.binarySearch(name); 

			if(location >= 0) {	// if name is found 
				JOptionPane.showMessageDialog(null, name + " Found in record " + (location+1));
			}	
			else {	// if name is NOT found 
				JOptionPane.showMessageDialog(null, name + "Not Found!");
			}

		}

		// if user wants to import from a new file 
		else if(e.getSource() == btnImport) {	
			try {
				// ask for the file name 
				String name = JOptionPane.showInputDialog(null, "Enter the file to read from", "(FileName).txt");

				// object array for options 
				Object options [] = {"Replace", "Add"};

				// ask if user wants to replace the current file or add to it 
				int n = JOptionPane.showOptionDialog(this,
						"Would you like to replace or add the file info to the record?",
						"Replace or Add",
						JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						null,
						options,
						options[1]);


				if(n == JOptionPane.YES_OPTION) {	// if replace 
					accounts.readFromFile(name, true); 
				}
				else {	// if add 
					accounts.readFromFile(name, false);
				}
			}catch(FileNotFoundException e1) {	// if file not found 
				JOptionPane.showMessageDialog(null, "File Not Found!");
			}
			catch(NullPointerException e3) {	// if user presses cancel 
				JOptionPane.showMessageDialog(null, "Import Cancelled");
			}
			catch(IOException e2) {	// if there is any input errors 
				JOptionPane.showMessageDialog(null, "Error! Check input file!");
			}

			output.setText(accounts.toString());	// update the output area

		}

		// if info is to be exported to a new file 
		else if(e.getSource() == btnExport) {
			// prompt for and get the file name 
			String name = JOptionPane.showInputDialog(null, "Enter the name to save the file", "Employee_Export_File.txt");

			try {
				// save the file 
				accounts.saveToFile(name);
			}catch(IOException e2) {	// if there are any errors encountered 
				JOptionPane.showMessageDialog(null, "Error! File not saved!"); 
			}

			output.setText(accounts.toString());	// update the output area
		}

		// if user wants to log out 
		else if(e.getSource() == btnLogout) {

			if(saved) {	// if info has been saved to master file
				this.dispose(); 	// close this window 
				saved = false; 	// reset the saved boolean 
				try {
					new Bankify();	// call the main gui class
				} catch (IOException e1) {
					// throw any errors
					e1.printStackTrace();
				}
			}
			else {	// if user hasn't saved info stop them from logging out 
				JOptionPane.showMessageDialog(null, "Save Changes before Logging out!"); 
			}


		}

	}

	/**
	 * Main Method 
	 */
	public static void main(String[] args) {
		new EmployeeGUI();

	}



}
