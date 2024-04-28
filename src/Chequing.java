/**
 * 
 */

/**
 * @author shubhpatel
 * Date: 16 Dec 2023
 * Description: This class represents the Chequing object and 
 * 				it inherits from the Account class
 *Method List: 
 * 			-> public Chequing() - defualt constructor
 * 			-> public Chequing(Customer cst)- overloaded constr. with customer 
 * 			-> 	public boolean withdraw(double amt) - withdraw method 
 * 			-> public boolean deposit(double amt)	- deposit method 
 * 			-> public void setFeeToWithdraw(double newFee) - setter for withdraw fee 
 * 			-> public double getFeeToWithdraw() - getter for withdraw fee 
 * 			-> public void setFeeToDeposit(double newFee) - setter for deposit fee 
 * 			-> public double getFeeToDeposit() - getter for deposit fee 
 * 			-> public String toString() - to string method
 */
public class Chequing extends Account{
	/**
	 * Private data/attributes
	 */
	private double feeToWithdraw;
	private double feeToDeposit;

	/**
	 * Default Constructor
	 */
	public Chequing() {
		super(); 
		feeToWithdraw = 0.005;
		feeToDeposit = 1.50;
	}

	/**
	 * Overloaded Constructor
	 */
	public Chequing(Customer cst) {
		super(cst); 
		feeToWithdraw = 0.005;
		feeToDeposit = 1.50;
	}

	/**
	 * Withdraw Method
	 */
	public boolean withdraw(double amt) {
		// needed to add this if statements because sometimes 
		// there is a lot of balance in the account and if a 
		// small withdrawal of $1 happens, the fee itself is greater than the 
		// withdrawal with causes it to allow calculations even though 
		// the amount entered is negative (because of the multiplication of the 
		// fee and the balance). 
		if(amt > 0) {	// if the amount is a positive no.
			// withdraw the amount plus a percent of the current balance
			return super.withdraw(amt + (getBalance() * feeToWithdraw)); 
		}
		return false; 
	}

	/**
	 * Deposit Method
	 */
	public boolean deposit(double amt) {
		// deposit the amount minus the service fees 
		return super.deposit(amt-feeToDeposit); 
	}

	/**
	 * setter for feeToWithdraw
	 */
	public void setFeeToWithdraw(double newFee) {
		this.feeToWithdraw = newFee;
	}
	
	/**
	 * Getter for feeToWithdraw
	 */
	public double getFeeToWithdraw() {
		return this.feeToWithdraw; 
	}

	/**
	 * Setter for feeToDeposit
	 */
	public void setFeeToDeposit(double newFee) {
		this.feeToDeposit = newFee;
	}
	
	/**
	 * Getter for feeToDeposit
	 */
	public double getFeeToDeposit() {
		return this.feeToDeposit;
	}


	/**
	 * toString Method
	 */
	public String toString() {
		return super.toString();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// test the default constructor 
		Chequing c = new Chequing(); 
		
		System.out.println(c.toString());
		
		// test the overloaded constructor 
		Customer cst = new Customer("Shubh Patel", "32 Unkown St.", "123-123-1234", "password"); 
		
		Chequing c1 = new Chequing(cst); 
		
		System.out.println(c1.toString());
		
		// test the deposit and withdraw method 
		System.out.println(c.deposit(0));
		System.out.println(c.getBalance() + " " + c.deposit(1000) + " " + c.getBalance()); 
		System.out.println(c.deposit(-1) + "\n"); 
		
		System.out.println(c.withdraw(100000));
		System.out.println(c.getBalance() + " " + c.withdraw(100) + " " + c.getBalance());
		System.out.println(c.withdraw(-34));
		
		// test the getters and setters for feeToWithdraw and feeToDeposit 
		c1.setFeeToDeposit(100);
		System.out.println("\n" + c1.getBalance() + " " + c1.deposit(5000) + " " + c1.getBalance());
		System.out.println(c1.getFeeToDeposit()); 
		
		c1.setFeeToWithdraw(0.5); 	//set fee to withdraw to 50%
		System.out.println("\n" + c1.getBalance() + " " + c1.withdraw(100) + " " + c1.getBalance());
		System.out.println(c1.getFeeToWithdraw());
		
		
	}

}
