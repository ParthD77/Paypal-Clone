/**


/**

 * Author: Himansh Garg

 * Date: December 7, 2023

 * Description: Represents a Customer with attributes such as name,
address, phone number, and password.

 *

 * Method List:

 * - Customer(): Default constructor for initializing a Customer object.

 * - Customer(String name, String address, String number, String
password): Constructor for initializing a Customer with specific
attributes.

 * - getPassword(): Gets the customer's password.

 * - setPassword(String pass): Sets the customer's password.

 * - getCustName(): Gets the customer's name.

 * - setCustName(String custName): Sets the customer's name.

 * - getCustAddress(): Gets the customer's address.

 * - setCustAddress(String custAddress): Sets the customer's address.

 * - getPhNumber(): Gets the customer's phone number.

 * - setPhNumber(String phNumber): Sets the customer's phone number.

 * - toString(): Gets a formatted string representation of the customer.

 * - main(String[] args): Self-testing main method for the Customer class.

 */


public class Customer {



	/**


	 * private instance variables/attributes


	 */


	private String custName;


	private String custAddress;


	private String phNumber;


	private String password;


	/*


	 * default constructor


	 */


	public Customer() {


		// initialize instance data



		this.custName = "";


		this.custAddress = "";


		this.phNumber = "";

		this.password = "";



	}


	public Customer(String name, String address, String number, String password) {


		this.custName = name;


		this.custAddress = address;


		this.phNumber = number;


		this.password = password;

	}


	/**

	 * get password

	 */

	public String getPassword() {

		return password;

	}

	/**

	 * set password

	 */

	public void setPassword(String pass) {

		password = pass;

	}


	/**


	 * @return the custName


	 */


	public String getCustName() {


		return custName;


	}



	/**


	 * @param custName the custName to set


	 */


	public void setCustName(String custName) {


		this.custName = custName;


	}



	/**


	 * @return the custAddress


	 */


	public String getCustAddress() {


		return custAddress;


	}



	/**
	 * @param custAddress the custAddress to set
	 */


	public void setCustAddress(String custAddress) {


		this.custAddress = custAddress;


	}



	/**


	 * @return the phNumber


	 */


	public String getPhNumber() {


		return phNumber;


	}



	/**


	 * @param phNumber the phNumber to set


	 */


	public void setPhNumber(String phNumber) {


		this.phNumber = phNumber;


	}






	/*


	 * to string method converts the objects information into a string


	 */


	public String toString() {


		return "Customer Name: " + custName + ", Customer Address: " +
				custAddress + ", phNumber : " + phNumber + ", password: " + password;


	}



	/**


	 * @param args


	 */


	public static void main(String[] args) {

		// Create an object

		Customer cInfo = new Customer();


		// Display the contents of the object

		System.out.println("Default Constructor:");

		System.out.println(cInfo.toString());


		// Test setters

		cInfo.setCustName("Tony Campos");

		cInfo.setCustAddress("45 Daviselm Dr.");

		cInfo.setPhNumber("99909545678");

		cInfo.setPassword("xyz");

		// Display the contents of the object after setting values

		System.out.println("\nAfter setting values:");

		System.out.println(cInfo.toString());


		// Test Getters

		System.out.println("\nGetters:");

		System.out.println("Customer Name: " + cInfo.getCustName());

		System.out.println("Customer Address: " + cInfo.getCustAddress());

		System.out.println("Phone Number: " + cInfo.getPhNumber());

		System.out.println("Password: " + cInfo.getPassword());

	}



}