import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;


/**
 * @author Parth Dhroovji
 * Date: Dec 17, 2023
 * Description: A program where you load into a bank account with a login account.
 * Different customers have different login details. This is the UI for the accounts.
 * You can transfer money, deposit and withdraw money. You can view your money and each
 * page is displayed seperately through the use of JPanels A account can be created 
 * through this UI.
 * Method List: 
 * (Doesnt inlude a tester as methods either create UI elements which are tested when the program runs or the methods manipulate components)
 * 		/ do transactions (amount to transfer, with or depo, chequing or savings, and user)
 * 		public String transaction(String amount, int type, int account, String userN) throws IOException {
 *		/ refresh all balance elements in balance page
 *	 	public void setBals() {
 *		// create a JButton and return it
 * 		public JButton buttonFactory(String imgName) {
 *		// create a JLabel and return it
 *		public JLabel labelFactory(String text, Font f, Color c) {
 *		// create a JTextField and return it
 *		public JTextField textFieldFactory(String text) {
 *		// detect action events
 *		public void actionPerformed(ActionEvent e) {
 *		// detect text changes in TextFields from user
 *		public void changedUpdate(DocumentEvent e) {
 *		public void insertUpdate(DocumentEvent e) {
 *		public void removeUpdate(DocumentEvent e) {
 *
 */
public class Bankify extends JFrame implements ActionListener, DocumentListener{
	// create buttons for login page and for the different pages
	private JButton login, signup, walletBut, activityBut, transferBut, logoutBut, chequingBut, savingBut;
	// buttons for sorting, saving to file, confirim transactions and changing password
	private JButton ascSortBut, amountSortBut, saveBut, confirmBut, depoBut, withBut, forgotPass;
	// panels for login page, balances page, transfering page, buttons header and activity page
	private JPanel loginP, balP, butP, transferP, activityP;
	// fields for user login info and inputting records and amounts for transactions
	private JTextField user, pass, number, initS, initC, address, searchRecord, sendPInput, sendAmntInput;
	private JTextField depoAmnt, withAmnt;
	// labels to display logos, and other basic UI elements and displaying info 
	// (used instead of image icons) Jlabel uses imageicon
	private JLabel logo, doubleBox, orLine, box, invalidCreds, inavlidSignup, doubleBox2, trippleBox, bal;
	private JLabel buttonsBack, balanceDis, balanceDis2, balanceDis3,  filterByL, sendL, transferBals;
	private JLabel chequingBalN, transferAmntN, newBalN, transfErr, searchBar1, searchBar2, balError;
	private JLabel depoAmntL, withAmntL;
	// displaying transactions
	private JScrollPane infoDis; 
	private JTextArea transactionsDis;
	// decimal formatting with and without a$ in front
	private NumberFormat currD;
	private DecimalFormat twoD;
	// saving whos logged in and in which account (chequing or savings)
	private int person, selectedAcc; 
	private AccountList accounts; // reading and storing the persons info from a file
	private TransactionList transactions; // reading and storinga persons history from a file

	// constructs the frame
	public Bankify() throws IOException {
		// set frame things
		super("Bankify Login");
		setLayout(null);
		setSize(1000, 700);
		setLocation(100, 10);
		getContentPane().setBackground(Color.white);

		// create panels and set absolute layout
		loginP = new JPanel();
		loginP.setLayout(null);
		balP = new JPanel();
		balP.setLayout(null);
		butP = new JPanel();
		butP.setLayout(null);
		transferP = new JPanel();
		transferP.setLayout(null);
		activityP = new JPanel();
		activityP.setLayout(null);

		// load accounts data and set currency formatting and create accounts and transaction histories
		accounts = new AccountList();
		transactions = new TransactionList();
		accounts.readFromFile("Customer_Info.txt", true);
		currD = NumberFormat.getCurrencyInstance();
		twoD = new DecimalFormat("#0.00");

		
		// set background to white and position panels
		loginP.setBackground(Color.white);
		loginP.setBounds(400, 0, 500, 600);
		balP.setBackground(Color.white);
		balP.setBounds(0, 100, 1000, 600);
		butP.setBounds(0, 0, 1000, 100);
		activityP.setBounds(0, 100, 1000, 600);
		activityP.setBackground(Color.white);
		transferP.setBounds(0, 100, 1000, 600);
		transferP.setBackground(Color.white);

		transactionsDis = new JTextArea(); // make the text area
		transactionsDis.setLineWrap(true); // Enable line wrapping
		transactionsDis.setWrapStyleWord(true); // Wrap at word boundaries

		// add the text area to the scroll pane
		infoDis = new JScrollPane(transactionsDis);
		infoDis.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // make scroll bar always show

		// create buttons
		login = buttonFactory("src/Images/LoginButton.png");
		signup = buttonFactory("src/Images/SignupButton.png");
		walletBut = buttonFactory("src/Images/WalletBut.png");
		chequingBut = buttonFactory("src/Images/ChequingBut.png");
		savingBut = buttonFactory("src/Images/SavingDesBut.png");
		ascSortBut = buttonFactory("src/Images/DateAsc.png");
		amountSortBut = buttonFactory("src/Images/Amount.png");
		saveBut = buttonFactory("src/Images/SaveBut.png");
		confirmBut = buttonFactory("src/Images/confirmBut.png");
		depoBut = buttonFactory("src/Images/depoBut.png");
		withBut = buttonFactory("src/Images/withBut.png");
		activityBut = buttonFactory("src/Images/ActivityBut.png");
		transferBut = buttonFactory("src/Images/TransferBut.png");
		logoutBut = buttonFactory("src/Images/SignOutBut.png");
		forgotPass = buttonFactory("src/Images/ChangePass.png");

		// create text fields
		user = textFieldFactory("Full Name");
		pass = textFieldFactory("Password");
		number = textFieldFactory("Phone Number (012-345-6789)");
		address = textFieldFactory("Home Adress");
		initC = textFieldFactory("Chequing Balance ($)");
		initS = textFieldFactory("Savings Balance ($)");
		searchRecord = textFieldFactory("Search (Amount)");
		sendPInput = textFieldFactory("Full Name");
		sendAmntInput = textFieldFactory("0.00");
		depoAmnt = textFieldFactory("0.00");
		withAmnt = textFieldFactory("0.00");

		//   make some of them bigger and add documet listeners
		sendPInput.setFont(new Font("Tahoma", Font.PLAIN, 30));
		sendPInput.getDocument().addDocumentListener(this);
		sendAmntInput.setFont(new Font("Tahoma", Font.PLAIN, 30));
		sendAmntInput.getDocument().addDocumentListener(this); // credits below
		searchRecord.getDocument().addDocumentListener(this);
		
		// create image labels
		box = new JLabel(new ImageIcon("src/Images/Box.png"));
		logo = new JLabel(new ImageIcon("src/Images/Logo.png"));
		doubleBox = new JLabel(new ImageIcon("src/Images/DoubleBox.png"));
		doubleBox2 = new JLabel(new ImageIcon("src/Images/DoubleBox.png"));
		trippleBox = new JLabel(new ImageIcon("src/Images/TrippleBox.png"));
		orLine = new JLabel(new ImageIcon("src/Images/ORLINE.png"));
		bal = new JLabel(new ImageIcon("src/Images/Balance.png"));
		buttonsBack = new JLabel(new ImageIcon("src/Images/ButtonsBackG.png"));
		searchBar1 = new JLabel(new ImageIcon("src/Images/searchBarBack.png"));
		searchBar2 = new JLabel(new ImageIcon("src/Images/searchBarBack.png"));
		depoAmntL = new JLabel(new ImageIcon("src/Images/depoAmnt.png"));
		withAmntL = new JLabel(new ImageIcon("src/Images/withAmnt.png"));
		transferBals = new JLabel(new ImageIcon("src/Images/transferBack.png"));

		// create text Labels
		Color r = Color.red;
		Color bl = Color.black;
		invalidCreds = labelFactory("", new Font("Futura", Font.PLAIN, 15), r);
		inavlidSignup = labelFactory("", new Font("Futura", Font.PLAIN, 20), r);
		balanceDis = labelFactory("$0.00", new Font("Futura", Font.PLAIN, 50), bl);		
		balanceDis2 = labelFactory("$0.00", new Font("Futura", Font.PLAIN, 30), bl);		
		balanceDis3 = labelFactory("$0.00", new Font("Futura", Font.PLAIN, 30), bl);
		filterByL = labelFactory("Sort By", new Font("Futura", Font.PLAIN, 25), bl);
		sendL = labelFactory("Transfer Money", new Font("Tahoma", Font.BOLD, 30), bl);
		chequingBalN = labelFactory("$0.00" ,new Font("Futura", Font.PLAIN, 20), bl);
		transferAmntN = labelFactory("$0.00", new Font("Futura", Font.PLAIN, 20), bl);
		newBalN = labelFactory("$0.00", new Font("Futura", Font.PLAIN, 20), bl);
		transfErr = labelFactory("", new Font("Futura", Font.PLAIN, 20), bl);
		balError = labelFactory("", new Font("Futura", Font.PLAIN, 20), bl);

		//https://www.tabnine.com/code/java/methods/javax.swing.JTextField/setHorizontalAlignment
		balanceDis.setHorizontalAlignment(SwingConstants.CENTER); // make text centered

		//  login Panel
		logo.setBounds(120, 80, 50, 50);
		user.setBounds(25, 155, 235, 30);
		pass.setBounds(25, 210, 235, 30);
		doubleBox.setBounds(20, 140, 250, 108);
		login.setBounds(20, 240, 240, 50);
		orLine.setBounds(20, 330, 250, 50);
		signup.setBounds(20, 430, 255, 50);
		invalidCreds.setBounds(40, 200, 250, 250);
		box.setBounds(-100, 50, 500, 500);
		forgotPass.setBounds(30, 285, 150, 25);

		// sign up components (are added to the login panel when signing up)
		number.setBounds(25, 260, 235, 30);
		address.setBounds(25, 315, 235, 20);
		initC.setBounds(25, 350, 115, 20);
		initS.setBounds(150, 350, 115, 20);
		doubleBox2.setBounds(20, 250, 250, 108);
		trippleBox.setBounds(20, 300, 250, 90);
		inavlidSignup.setBounds(20, 270, 250, 250);

		// buttons  panel
		logoutBut.setBounds(880, 55, 130, 40);
		transferBut.setBounds(600, 25, 130, 40);
		walletBut.setBounds(100, 25, 130, 40);
		activityBut.setBounds(350, 25, 130, 40);
		buttonsBack.setBounds(0, 0, 1000, 95);

		// balance panel
		balanceDis.setBounds(490, 130, 500, 50);
		balanceDis2.setBounds(730, 355, 300, 50);
		balanceDis3.setBounds(730, 445, 300, 50);
		bal.setBounds(500, 0, 500, 500);
		chequingBut.setBounds(50, 50, 350, 88);
		savingBut.setBounds(50, 150, 350, 88);
		depoAmnt.setBounds(80, 310, 250, 20);
		withAmnt.setBounds(80, 410, 250, 20);
		depoBut.setBounds(350, 335, 60, 32);
		withBut.setBounds(340, 435, 70, 32);
		depoAmntL.setBounds(50, 250, 360, 150);
		withAmntL.setBounds(50, 350, 360, 150);
		balError.setBounds(50, 500, 500, 20);

		// activity panel
		filterByL.setBounds(100, 110, 200, 30);
		ascSortBut.setBounds(100, 135, 140, 50);
		amountSortBut.setBounds(300, 135, 140, 50);
		saveBut.setBounds(800, 135, 50, 50);
		infoDis.setBounds(100, 185, 750, 350);
		searchRecord.setBounds(100, 50, 500, 50);
		searchBar1.setBounds(40, 40, 650, 70);

		// transfer panel
		sendPInput.setBounds(60, 85, 550, 50);
		sendL.setBounds(60, 15, 250, 40);
		sendAmntInput.setBounds(435, 190, 200, 40);
		confirmBut.setBounds(190, 470, 200, 45);
		chequingBalN.setBounds(470, 290, 250, 30);
		transferAmntN.setBounds(420, 355, 250, 30);
		newBalN.setBounds(430, 420, 250, 30);
		transfErr.setBounds(190, 520, 500, 15);
		searchBar2.setBounds(0, 75, 650, 70);
		transferBals.setBounds(180, 130, 700, 400);

		// add comps to login panel
		loginP.add(forgotPass);
		loginP.add(logo);
		loginP.add(user);
		loginP.add(pass);
		loginP.add(doubleBox);
		loginP.add(login);
		loginP.add(orLine);
		loginP.add(signup);
		loginP.add(invalidCreds);
		loginP.add(box);

		// add comps to buttons panel
		butP.add(logoutBut);
		butP.add(transferBut);
		butP.add(walletBut);
		butP.add(activityBut);
		butP.add(buttonsBack);

		// add comps to balance panel
		balP.add(balanceDis);
		balP.add(balanceDis2);
		balP.add(balanceDis3);
		balP.add(bal);
		balP.add(chequingBut);
		balP.add(savingBut);
		balP.add(depoAmnt);
		balP.add(withAmnt);
		balP.add(depoBut);
		balP.add(withBut);
		balP.add(depoAmntL);
		balP.add(withAmntL);
		balP.add(balError);

		// add comps to activity panel
		activityP.add(filterByL);
		activityP.add(ascSortBut);
		activityP.add(amountSortBut);
		activityP.add(saveBut);
		activityP.add(infoDis);
		activityP.add(searchRecord);
		activityP.add(searchBar1);

		// add comps to transfer panel
		transferP.add(sendPInput);
		transferP.add(sendL);
		transferP.add(sendAmntInput);
		transferP.add(chequingBalN);
		transferP.add(transferAmntN);
		transferP.add(newBalN);
		transferP.add(transfErr);
		transferP.add(searchBar2);
		transferP.add(transferBals);

		// start the program with the login panel
		add(loginP);

		// set visible, non adjustable window size and icon
		setVisible(true);
		setResizable(true);
		setIconImage(new ImageIcon("Logo.png").getImage());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	/**
	 * type 0 = deposits  <p>
	 * type 1 = withdraw  <p>
	 * account = account to use for transaction <p>
	 * userN = users name <p>
	 * amount to do in string 
	 **/
	public String transaction(String amount, int type, int account, String userN) throws IOException {
		boolean temp = false;
		String r  = "", prevBal, newBal;
		// stores previous balance
		if (Double.parseDouble(amount) < 0) return "Invalid Innput";
		if (selectedAcc == 0) prevBal = twoD.format(accounts.getRecord(account).getChequing().getBalance());
		else prevBal = twoD.format(accounts.getRecord(account).getSavings().getBalance());
		
		// deposits or withdraws depending on selected account, if the methods return false,
		// it displays that they dont have enough money and if there is an issue in converting to Double,
		// it displays invalid input
		if (type == 0) { // deposits
			try {  // deposits based on which selected account
				if (selectedAcc == 0) {
					temp = accounts.getRecord(account).getChequing().deposit(Double.parseDouble(amount));
				}
				else {
					temp = accounts.getRecord(account).getSavings().deposit(Double.parseDouble(amount));
				}
			}
			catch (Exception e) { // catch  any errors and display accordinly
				r  = "Invalid Input";
				temp = true;
			}

		}
		else { // withdraws
			try {
				if (selectedAcc == 0) {
					temp = accounts.getRecord(account).getChequing().withdraw(Double.parseDouble(amount));
				}
				else {
					temp = accounts.getRecord(account).getSavings().withdraw(Double.parseDouble(amount));
				}
			}
			catch (Exception e) {
				r  = "Invalid Input";
				temp = true;
			}
		}
		
		// store new balance (done seperately as you  dont know which account to take from)
		if (selectedAcc == 0) newBal = twoD.format(accounts.getRecord(account).getChequing().getBalance());
		else newBal = twoD.format(accounts.getRecord(account).getSavings().getBalance());

		// if transaction didnt fail due to insufficent money
		if (temp == false) r = "Transaction Amount Is Too Low";
		else { // save transactions and create its history
			transactions.insertTransaction(new TransactionRecord(userN, selectedAcc, type, amount, prevBal, newBal));
			accounts.saveToFile("Customer_Info.txt"); // save any transaction done
			transactions.saveToFile("Transactions.txt");
			searchRecord.setText("Search (Amount)"); // reset the transaction list
		}
		repaint();
		return r;
	}
	

	// set balances on the pages
	public void setBals() {
		if (selectedAcc == 0) { // chequing account type
			balanceDis.setText(currD.format(accounts.getRecord(person).getChequing().getBalance()));
			balanceDis2.setText(currD.format(accounts.getRecord(person).getChequing().getBalance()));
			balanceDis3.setText(currD.format((accounts.getRecord(person).getChequing().getBalance()*0.75)));
			//  american  conversion
		}
		else { // savings account type
			balanceDis.setText(currD.format(accounts.getRecord(person).getSavings().getBalance()));
			balanceDis2.setText(currD.format(accounts.getRecord(person).getSavings().getBalance()));
			balanceDis3.setText(currD.format((accounts.getRecord(person).getSavings().getBalance()*0.75)));
		}
		repaint();
	}

	// create a button with a input string
	public JButton buttonFactory(String imgName) {
		JButton tempBut = new JButton(new ImageIcon(imgName)); // create
		// set no background
		tempBut.setBorderPainted(false);
		tempBut.setContentAreaFilled(false);
		tempBut.setFocusable(false);
		// action listener
		tempBut.addActionListener(this);
		return tempBut; // return the button
	}

	// create a label with a input string, font and color
	public JLabel labelFactory(String text, Font f, Color c) {
		JLabel tempLabel = new JLabel(text); 
		tempLabel.setFont(f);
		tempLabel.setForeground(c);
		return tempLabel;
	}

	// create a JTextField and empty its border
	public JTextField textFieldFactory(String text) {
		JTextField tempField = new JTextField(text);
		tempField.setBorder(null);
		return tempField;
	}


	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == login) {	
			//  if login page is showing -> try to login
			if (login.getY() == 240) {
				// reset text
				invalidCreds.setText("");
				accounts.insertionSort(); // sort for searching
				int x = accounts.binarySearch(user.getText());  // search for user in database


				// if an employee detail was input open emplyee window
				if (user.getText().equals("**") && pass.getText().equals("**")) {
					this.dispose(); // close current frame
					new EmployeeGUI();
				}

				// check if valid login is entered
				else if (accounts.binarySearch(user.getText()) >= 0  && accounts.checkPassword(x, pass.getText())) { // if user matches
					remove(loginP); // remove login page and show balance pages
					add(butP);
					add(balP);
					
					person = accounts.binarySearch(user.getText());	// set who logged  in
					selectedAcc = 0; // set selected account to chequing
					setBals(); // display their balances
					try { // read from the transactions file
						transactions.sortFile("Transactions.txt");
					} catch (IOException e1) {
						e1.printStackTrace();
					}

					// set transactions text
					transactionsDis.setText(transactions.listToString(transactions.linearSearch(user.getText())));

				}
				// if no match 
				else {
					invalidCreds.setText("Invalid login credintals");
				}
				repaint();
			}

			// if signup page is showing -> show login page
			else {
				loginP.remove(doubleBox2);
				loginP.remove(number);
				loginP.remove(inavlidSignup);
				loginP.remove(trippleBox);
				loginP.remove(address);
				loginP.remove(initC);
				loginP.remove(initS);
				loginP.add(forgotPass, 0);

				login.setBounds(20, 240, 250, 50);
				orLine.setBounds(20, 330, 250, 50);
			}

			repaint(); //  repaint any changes
		}

		if (e.getSource() == signup){
			// if login page is showing ->  show signup page
			if (login.getY() == 240) {
				loginP.add(doubleBox2, 1);
				loginP.add(trippleBox, 1);
				loginP.add(number, 0);
				loginP.add(address, 1);
				loginP.add(initC, 1);
				loginP.add(initS, 1);
				loginP.remove(forgotPass);
				

				orLine.setBounds(20, 455, 250, 50);
				login.setBounds(20, 480, 250, 50);
			}

			// if sign up page is showing -> try to sign up
			else {
				try { // check if valid input was entered
					Double.parseDouble(initC.getText());
					Double.parseDouble(initS.getText());

					// check if account doesnt already exist
					if (accounts.binarySearch(user.getText()) < 0) {
						inavlidSignup.setText(""); // resets text
						// creates a new customer and a new account based on input details
						Customer cust = new Customer(user.getText(), address.getText(),  
								number.getText(), pass.getText());
						Chequing c = new Chequing(cust);
						Savings s = new Savings(cust);

						// create a record for that customer
						AccountRecord r = new AccountRecord(c, s);
				
						accounts.insert(r); // insert it to the list of accounts 
						// login with info (twice so it shows login page briefly and logs in with already present info)
						login.doClick();
						login.doClick();

						try { // save info with new account to file
							accounts.insertionSort(); // re-sort with new record
							accounts.saveToFile("Customer_Info.txt");
						} catch (IOException e1) {
						}

						//  deposit into chequing and savings
						transaction(initC.getText(), 0, person, user.getText());
						selectedAcc = 1;
						transaction(initS.getText(), 0, person, user.getText());
						selectedAcc = 0;
						setBals(); // set balances to display
					}
					else { // display duplicate accounts
						inavlidSignup.setText("Duplicate Name");
						loginP.add(inavlidSignup, 0);
					}
				}
				catch (Exception s) { // if invalid balances were input in the boxes
					inavlidSignup.setText("Invalid Inital Balances (0.00)");
					loginP.add(inavlidSignup, 0); // display error
				}
			}
			repaint(); //  repaint any changes
		}


		// if chequing account was selected
		if (e.getSource() == chequingBut) {
			// show selected icon, set account type and display account balances
			savingBut.setIcon(new ImageIcon("src/Images/SavingDesBut.png"));
			chequingBut.setIcon(new ImageIcon("src/Images/ChequingBut.png"));
			selectedAcc = 0; // set selected account
			setBals();
			repaint();
		}

		// if savings account was selected
		if (e.getSource() == savingBut) {
			// show selected icon, set account type  and display account balances
			chequingBut.setIcon(new ImageIcon("src/Images/ChequingDesBut.png"));
			savingBut.setIcon(new ImageIcon("src/Images/SavingsBut.png"));
			selectedAcc = 1; // set selected account
			setBals();
			repaint();
		}

		// if deposit button was selected
		if (e.getSource() == depoBut) {
			try { 
				balError.setText(""); // reset text
				String worked = transaction(depoAmnt.getText(), 0, person, user.getText()); // do  transaction
				balError.setText(worked); // display if any errors show
				setBals();  // update balances
				repaint();
			}
			catch (Exception s) {
				balError.setText("Only Enter Numbers"); // display if any errors show
			}
		}
		
		// if withdraw button was selected
		if (e.getSource() == withBut) {
			try {
				balError.setText(""); // reset text
				String worked = transaction(withAmnt.getText(), 1, person, user.getText());// do transaction
				balError.setText(worked);// display if any errors show
				setBals(); // update balances
				repaint();
			}
			catch (Exception s) {
				balError.setText("Only Enter Numbers"); // display if any errors show
			}
		}

		// transfer confirm button
		if (e.getSource() == confirmBut) {
			// do  transaction
			int x = accounts.binarySearch(sendPInput.getText()); // get  transfer to persons name
			int tempAcc = selectedAcc; // force it to be in the chequing account
			selectedAcc = 0;
			try { 
				if (transaction(sendAmntInput.getText(), 1, person, user.getText()).equals("")) { // transfer is valid
					transaction(sendAmntInput.getText(), 0, x, sendPInput.getText());
					sendAmntInput.setText(sendAmntInput.getText()); // force numbers to update
					transfErr.setText("Transfer Successful");
				}
				else{ // transfer didnt work
					transfErr.setText("Transfer Failed");
				}
			} catch (IOException e1) { // invalid input
				transfErr.setText("Transfer Failed");
			}
			selectedAcc = tempAcc; // revert acccount type again
			setBals();
		}
		
		// sort activites by amount
		if (e.getSource()  == amountSortBut) {
			transactions.sortTransactions();
			transactionsDis.setText(transactions.listToString(transactions.linearSearch(user.getText()))); 
		}
		
		// creates a new list as the text file is sorted by oldest to newest by default,
		// I abuse that fact and create a sort button for that to display it in that format
		if (e.getSource() == ascSortBut) {
			TransactionList temp = new TransactionList();
			try {
				temp.sortFile("Transactions.txt"); // new list sorted by date not amount if amount was selected
			} catch (IOException e1) {
			}
			transactionsDis.setText(temp.listToString(temp.linearSearch(user.getText()))); 
		}
		
		if  (e.getSource() == forgotPass) {
			//https://stackoverflow.com/questions/6555040/multiple-input-in-joptionpane-showinputdialog
			JTextField username = new JTextField();
			JTextField password = new JTextField();
			JTextField newPassword = new JTextField();

			Object[] message = {
			    "Username:", username,
			    "Old Password:", password,
			    "New Password:", newPassword
			};

			// ask for the username, old pass and new pass
			int option = JOptionPane.showConfirmDialog(null, message, "Change Password", JOptionPane.YES_NO_OPTION);
			if (option == JOptionPane.YES_OPTION) {
				int x = accounts.binarySearch(username.getText());
			    if (x > 0 && accounts.checkPassword(x, password.getText())) { // if  pass word is valid for person
			    	// changepassword
			        accounts.getRecord(x).getChequing().getCustomer().setPassword(newPassword.getText());
			    } else { // invalid creds
			        invalidCreds.setText("Invalid User or Pass");
			    }
			} 
		}
		
		// save a users transactions 
		if (e.getSource() == saveBut) {
			// ask for what file name to save to
			String s = JOptionPane.showInputDialog("Name of file to save to:");
			// set up to write to a new file
			FileWriter fileW = null;
			try {
				fileW = new FileWriter(s);
			} catch (IOException e1) {
			}
			// save the info to file
			PrintWriter out = new PrintWriter(fileW);
			out.println(transactionsDis.getText()); // write to file
			out.close(); // saving the file
		}

		// switch to wallet page
		if (e.getSource() == walletBut) {
			getContentPane().removeAll();
			add(butP);
			add(balP);
			repaint();
		}

		// switch to activity page
		if (e.getSource() == activityBut) {
			getContentPane().removeAll();
			add(butP);
			add(activityP);
			repaint();
			// fix for scroll pane  not displaying propperly
			revalidate(); 
		}

		// switch to transfer page
		if (e.getSource() == transferBut) {
			getContentPane().removeAll();
			add(butP);
			add(transferP);
			repaint();
		}

		// switch to login page
		if (e.getSource() == logoutBut) {
			getContentPane().removeAll();
			// rest all fields when logging out for privacy
			user.setText("Full Name");
			pass.setText("Password");
			number.setText("Phone Number (012-345-6789)");
			address.setText("Home Address");
			initC.setText("Chequing Bal($)");
			initS.setText("Savings Bal($)");
			sendPInput.setText("Full Name");
			sendAmntInput.setText("0.00");
			chequingBalN.setText("$0.00");
			transferAmntN.setText("$0.00");
			newBalN.setText("$0.00");
			transfErr.setText("");
			depoAmnt.setText("0.00");
			withAmnt.setText("0.00");
			add(loginP);
			repaint();
		}
	}

	//https://docs.oracle.com/javase/tutorial/uiswing/events/documentlistener.html
	// checks if JTextArea was changed at all
	public void changedUpdate(DocumentEvent e) {
	}
	public void insertUpdate(DocumentEvent e) {
		removeUpdate(e); // if a letter was added go to other method to prevent repeated code
	}
	public void removeUpdate(DocumentEvent e) {
		// read the documents text and compare to find which input box it is
		Document doc = e.getDocument(); // gets text
		String x = "";
		try {
			x = doc.getText(0, doc.getLength());
		}
		catch (Exception s){
		}

		// compare and find which text box
		if (x.equals(sendPInput.getText())) {  
			// adds button if input username user name is valid
			if (accounts.binarySearch(sendPInput.getText()) > 0) {	
				transfErr.setText("");
				transferP.add(confirmBut, 0);
			}
			// removes button if user name is faulty and set invalid name
			else {
				transferP.remove(confirmBut);
				transfErr.setText("Invalid Name");

			}
		}
		// if amount field was edited
		else if(x.equals(sendAmntInput.getText())) {
			String send = "";
			double sendNum = 0;
			try { // try to display the calculations
				send = currD.format(Double.parseDouble(sendAmntInput.getText()));
				sendNum = Double.parseDouble(sendAmntInput.getText());
				
				chequingBalN.setText(currD.format(accounts.getRecord(person).getChequing().getBalance())+"");
				transferAmntN.setText(send);
				newBalN.setText(currD.format(accounts.getRecord(person).getChequing().getBalance()*0.995-sendNum));
				transfErr.setText(""); // rest error text
			}
			catch (Exception e1) { // if an error happens then display accordingly
				transfErr.setText("Only enter mumbers in amount field");
			}
		}
		else if(x.equals(searchRecord.getText())) { // if search is not empty or starting text, display search
			if (x.equals("") || x.equals("Search (Amount)")) {
				transactionsDis.setText(transactions.listToString(transactions.linearSearch(user.getText())));
			}
			else { // search for the transaction by amount
				transactionsDis.setText(transactions.searchAmnt(x));
			}
		}
		repaint();	// repaint changes
	}

	public static void main(String[] args) throws IOException {
		new Bankify(); // start program
	}
}