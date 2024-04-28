/**
 * 
 */

/**
 * @author shubhpatel
 * Date: 16 Dec 2023 
 * Description: This class inherits from account and is of type savings
 *Method List:
 *   			-> public Savings() - defualt constructor
 *   			-> public Savings(Customer cst)- overloaded constr. with customer
 *   			-> public boolean withdraw(double amt) - withdraw method
 *   			-> public void setFeeToWithdraw(double newFee) - setter for withdraw fee
 *   			-> public double getFeeToWithdraw() - getter for withdraw fee
 *   			-> public void setMinBalance(double newMinBalance) - setter for min balance
 *  			-> public String toString() - to string method
 */
public class Savings extends Account{
	/**
	 * Private data/attributes
	 */
	private double feeToWithdraw; 
	private double minBalance; 

	/**
	 * Default constructor
	 */
	public Savings() {
		super(); 	// calling the super class (account) constructor 
		feeToWithdraw = 1.25;
		minBalance = 2000;
	}
	
	/**
	 * Overloaded constructor
	 */
	public Savings(Customer cst) {
		super(cst); 	// calling the overloaded super class constructor
		feeToWithdraw = 1.25;
		minBalance = 2000;
	}
	
	/**
	 * Withdraw Method
	 */
	public boolean withdraw(double amt) {
		// if the balance is less than the minimum balance
		if(getBalance() < minBalance) {
			// withdraw amount + fee
			return super.withdraw(amt+feeToWithdraw); 
		}
		// else withdraw just the amount 
		return super.withdraw(amt); 
	}
	
	
	/**
	 * toString Method
	 */
	public String toString() {
		return super.toString(); 
	}
	
	/**
	 * setFeeToWithdraw Method
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
	 * setMinBalance Method
	 */
	public void setMinBalance(double newMinBalance) {
		this.minBalance = newMinBalance;
	}

	/**
	 * Self-testing Main Method
	 */
	public static void main(String[] args) {
		// test the default constructor 
		Savings s = new Savings(); 
		
		System.out.println(s.toString());
		
		// test the overloaded constructor 
		Customer c = new Customer("Shubh Patel", "32 Unkown St.", "123-123-1234", "password"); 
		
		Savings s1 = new Savings(c);  
		
		System.out.println(s1.toString());
		
		// test the withdraw method 
		s.deposit(10000); 
		
		// withdraw with balance over 2000
		System.out.println(s.withdraw(0));
		System.out.println(s.getBalance() + " " + s.withdraw(200) + " " + s.getBalance()); 
		System.out.println(s.withdraw(-2)); 
		
		// withdraw with balance under 2000 
		s1.deposit(200); 
		
		System.out.println(s1.withdraw(201)); 
		System.out.println(s1.getBalance() + " " + s1.withdraw(100) + " " + s1.getBalance());

		
		// test the setter and getter for the feeToWithdraw
		s.setFeeToWithdraw(10);
		
		System.out.println(s.getBalance() + " " + s.withdraw(200) + " " + s.getBalance()); 
		
		System.out.println(s.getFeeToWithdraw());
		
		//test the setter for minimum balance 
		s1.setMinBalance(50);
		
		System.out.println(s.getBalance() + " " + s.withdraw(10) + " " + s.getBalance()); 
		
	}

}
