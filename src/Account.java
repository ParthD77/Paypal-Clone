import java.text.NumberFormat;
import java.util.Random;

/**
 * 
 */

/**
 * @author shubhpatel
 * Date: Dec 16 2023 
 * Description: This is the account class for the banking application. 
 * 				This class represents the account object 
 * 
 * Method List: 
 * 			-> public Account() - creates a new account 
 * 			-> public Account(Customer theOwner) - creates a new account with the given customer
 * 			-> private long generateAccNum() - generates a 12-digit account number
 * 			-> public boolean deposit(double amt) - deposit method 
 * 			-> public boolean withdraw(double amt) - withdraw method
 * 			-> public long getAccNum() - getter for account no.
 * 			-> public void setAccNum(long newAccNum) - setter for account no.
 * 			-> public Customer getCustomer() - getter for customer 
 * 			-> public void setCustomer(Customer cst) - setter for customer
 * 			-> public double getBalance() - getter for balance
 * 			-> public String toString() - turns account to string 
 *
 */
public class Account {
	/**
	 * Private attributes 
	 */
	private double balance; 
	private long accNum; 
	private Customer customer; 

	/*
	 * Default Constructor
	 */
	public Account() {
		// initialize balance to 0
		balance = 0; 
		// generating a random 12 digit acc num by calling the generateAccNum Method
		accNum = generateAccNum(); 
		customer = new Customer(); 	// creating a new customer 
	}

	/**
	 * Overloaded Constructor 
	 * to set the customer object to the passed in customer
	 */
	public Account(Customer theOwner) {
		balance = 0; 
		accNum = generateAccNum(); 
		customer = theOwner; 
	}

	/**
	 * Private method to generate the 12-digit account number
	 */
	private long generateAccNum() {
		// create a random class object
		Random random = new Random(); 

		// return a number from 100,000,000,000 to 999,999,999,999
		return 100_000_000_000L + (long) (random.nextDouble() * (999_999_999_999L - 100_000_000_000L + 1));
	}

	/**
	 * The deposit Method
	 * takes in an amount and deposits it in the account
	 */
	public boolean deposit(double amt) {
		// if amount to deposit is greater than 0
		if (amt > 0) {
			// deposit by increasing the balance 
			balance += amt;
			return true;
		}

		return false; 	// not successful 

	}

	/**
	 * The Withdraw Method
	 * Takes in an amount to withdraw from the account
	 * and returns true if successful 
	 */
	public boolean withdraw(double amt) {
		// if there is sufficient balance in the account to withdraw from 
		// and the amount to withdraw is more than 0
		if(amt <= balance && amt > 0) {
			// withdraw by decreasing the balance 
			balance -= amt;
			return true;
		}

		return false;	// not successful (insufficient funds)

	}

	/**
	 * Getter for accNum
	 */
	public long getAccNum() {
		return this.accNum; 
	}

	/**
	 * Setter for accNum
	 */
	public void setAccNum(long newAccNum) {
		this.accNum = newAccNum; 
	}

	/**
	 * Getter for customer object
	 */
	public Customer getCustomer() {
		return this.customer; 
	}

	/**
	 * Setter for customer object
	 */
	public void setCustomer(Customer cst) {
		this.customer = cst; 
	}

	/**
	 * Getter for balance
	 */
	public double getBalance() {
		return this.balance; 
	}

	/**
	 * toString method
	 */
	public String toString() {
		NumberFormat formatter = NumberFormat.getCurrencyInstance();

		return "Account Number: " + getAccNum() + "\nCustomer Info: " + 
		this.customer.toString() + "\nBalance: " + formatter.format(this.balance);
	}

	/**
	 * Self-testing Main Method
	 */
	public static void main(String[] args) {
		// Create an account object
		Account a = new Account(); 

		System.out.println(a.toString() + "\n");

		// test the overloaded constructor 
		Customer c = new Customer("Shubh Patel", "31 Fake St.", "437-111-1111", "password"); 

		Account a1 = new Account(c); 

		System.out.println(a1.toString() + "\n");

		// test the getAccNum method
		System.out.println(a.getAccNum() + "\n"); 

		// test the setAccNum method
		a1.setAccNum(123456789012L);

		System.out.println(a1.getAccNum() + "\n");

		// test the getCustomer method 
		System.out.println(a1.getCustomer().toString() + "\n"); 

		// test the setCustomer method 
		a.setCustomer(new Customer("Tony Campos", "HorseCookies Land", "999-999-9999", "Password!23")); 

		System.out.println(a.getCustomer().toString() + "\n");

		// test the deposit method 
		System.out.println(a.deposit(0));
		System.out.println(a.deposit(-1));
		System.out.println(a.deposit(100) + "\n");

		// test the withdraw method 
		System.out.println(a.withdraw(0)); 
		System.out.println(a.withdraw(-5));
		System.out.println(a.withdraw(50) + "\n"); 

		// test the get balance 
		System.out.println(a.getBalance());

		a1.deposit(99999); 
		System.out.println(a1.getBalance()); 


	}

}
