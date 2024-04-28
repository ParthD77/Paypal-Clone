import java.text.DecimalFormat;

/**
 * 
 */

/**
 * @author shubhpatel
 * Date: 16 Dec 2023 
 * Description: This class represents the object with the account records
 *Method List: 
 * 			-> public AccountRecord(Chequing c, Savings s) - constructor with accounts 
 * 			-> public AccountRecord(String input) - constructor with a string
 * 			-> public String toString() - tostring method 
 *			-> public void processString(String record) - processes strings 
 * 			-> public Chequing getChequing() - getter for chequing acc
 * 			-> public Savings getSavings() - getter for savings acc
 */
public class AccountRecord {
	/**
	 * Private data/attributes
	 */
	private Chequing c; 
	private Savings s; 

	/**
	 * Overloaded constructor 
	 */
	public AccountRecord(Chequing c, Savings s) {
		this.c = c; 
		this.s = s; 
	}

	/**
	 * Overloaded constructor
	 * Makes a record from a formatted string 
	 */
	public AccountRecord(String input) {
		processString(input); 
	}

	/**
	 * toString Method
	 */
	public String toString() {
		// set up to round to 2 decimal places
		DecimalFormat twoD = new DecimalFormat("0.00"); 

		return c.getCustomer().getCustName() + 
				" || Chequing #" + c.getAccNum() + " Balance: $" + twoD.format(c.getBalance()) + 
				" || Savings #" + s.getAccNum() + " Balance: $" + twoD.format(s.getBalance());
	}

	/**
	 * processString Method 
	 * processes a formatted String
	 */
	public void processString(String record) {
		// split the elements using "/" as a delimiter 
		String info [] = record.split("/"); 

		// make a new customer based on the info
		Customer cNew = new Customer(info[0], info[1], info[2], info[3]); 

		// set new customer to chequing and savings account
		c = new Chequing(cNew); 
		s = new Savings(cNew); 

		// since depositing the amounts from the file will lead to a fee 
		// even though the customer hasn't deposited that amount themselves, 
		// I get the fee and then turn it to zero and then deposit the amount
		// in the account and then change the fee back to what it was before

		// get the fee to deposit into both accounts
		double feeToDepositChequing = c.getFeeToDeposit(); 

		// set the fees to zero 
		c.setFeeToDeposit(0);

		// set the account number and deposit the balance for chequing
		this.c.setAccNum(Long.parseLong(info[4]));
		this.c.deposit(Double.parseDouble(info[5]));

		// set the account number and deposit the balance for savings
		this.s.setAccNum(Long.parseLong(info[6])); 
		this.s.deposit(Double.parseDouble(info[7]));

		// set the fees back to original 
		c.setFeeToDeposit(feeToDepositChequing); 

	}

	/**
	 * Getter for chequing
	 */
	public Chequing getChequing() {
		return this.c; 
	}

	/**
	 * Getter for savings
	 */
	public Savings getSavings() {
		return this.s; 
	}

	/**
	 * Self-testing Main
	 */
	public static void main(String[] args) {
		// test the overloaded constructor
		Customer cst = new Customer("Shubh Patel", "32 Unkown St.", "123-123-1234", "password"); 

		Chequing c = new Chequing(cst); 
		Savings s = new Savings(cst); 

		AccountRecord r = new AccountRecord(c, s); 
		System.out.println(r.toString());

		// test the overloaded constructor that makes the record based on a string 
		String record = "Tony Campos/HorseCookies Land/999-999-9999/password1234/893748293801/50.00/123678493092/10000.00";

		AccountRecord r1 = new AccountRecord(record); 
		System.out.println(r1.toString());

		// test the getters 
		System.out.println(r.getChequing().toString());
		System.out.println(r1.getSavings().toString()); 

		// test the processString method 
		r.processString(record);
		System.out.println(r.toString() + "\n"); 

	}

}
