package MainPackage;
import java.util.Scanner;

public class Customer
{
	private int CustomerID;
	private String name;
	private String address;
	private String PhoneNo;
	
	private int CheckingAccount;
	private int SavingAccount;
	
	private Account saving;
	private Account checking;
	
	public static int idcount = 2000;
	
	
	// Getter And Setters
	
	public int getCustomerID() {
		return CustomerID;
	}

	public void setCustomerID(int customerID) {
		CustomerID = customerID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNo() {
		return PhoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		PhoneNo = phoneNo;
	}

	public int getCheckingAccount() {
		return CheckingAccount;
	}

	public void setCheckingAccount(int checkingAccount) {
		CheckingAccount = checkingAccount;
	}

	public int getSavingAccount() {
		return SavingAccount;
	}

	public void setSavingAccount(int savingAccount) {
		SavingAccount = savingAccount;
	}

	public Account getSaving() {
		return saving;
	}

	public void setSaving(Account saving) {
		this.saving = saving;
	}

	public Account getChecking() {
		return checking;
	}

	public void setChecking(Account checking) {
		this.checking = checking;
	}

	public int getIdcount() {
		return idcount;
	}

	public void setIdcount(int idcount) {
		Customer.idcount = idcount;
	}

	
	
	
	
	
	
	
	public Account getAccount(int n)
	{
		if (n == 1 ) {return checking;}
		else {return saving;}
	}
	
	public Customer()
	{
		CustomerID = ++idcount;
		System.out.println("New Customer Number: "+ CustomerID );
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter Your Name: ");
		name = sc.nextLine();
		
		System.out.print("Enter Your Address: ");
		address = sc.nextLine();
		
		System.out.print("Enter Your PhoneNo: ");
		PhoneNo = sc.nextLine();
		
		System.out.print("Press 1 to Create Checking Account\nPress 2 to Create Saving Account\nEnter Choice (1~2): ");
		int choice = 0;
		choice = sc.nextInt();
		
		while(choice!= 1 &&  choice != 2)
		 {
			System.out.print("Invalid Choice! Enter Again: ");
			choice = sc.nextInt();
		 }
		CheckingAccount = 0;
		SavingAccount = 0;
		createAccount(choice);
		
	}
	
	public Customer(int id, String name , String address , String ph , int ca , int sa)
	{
		CustomerID = id;
		this.name = name;
		this.address = address;
		PhoneNo = ph;
		CheckingAccount = ca;
		SavingAccount = sa;
		idcount = id;
	}
	public boolean CheckAccount(int n)
	{
		if ( n==1 && CheckingAccount == 1 )
			return true;
		
		if (n==2 && SavingAccount == 1)
			return true;
		
		else return false;
	}
	
	public int getID() {return CustomerID;}
	
	public void ShowDetails()
	{
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("Name: " + name );
		System.out.println("Address: " + address );
		System.out.println("PhoneNo: " + PhoneNo );
		System.out.println("Number Of Saving Accounts: " + SavingAccount );
		System.out.println("Number Of Checking Accounts: " + CheckingAccount );
	}
	
	public String returndetails()
	{
		String data =  CustomerID + "," + name + "," + address + "," + PhoneNo + "," + CheckingAccount + "," + SavingAccount  + "\n";
		return data;
	}
	
	
	public void createAccount(int n)
	{
		if (n == 1)
		{
			checking = new CheckingAccount();
			CheckingAccount = 1;
		}
		else if (n == 2)
		{
			saving = new SavingAccount();
			SavingAccount = 1;
		}
	}

	public void deleteaccount(int n)
	{
		if (n == 1)
		{
			CheckingAccount--;
			checking = null;
		}
		else if (n == 2 )
		{
			SavingAccount--;
			saving = null;
		}
	}
}
