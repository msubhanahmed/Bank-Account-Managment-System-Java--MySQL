package MainPackage;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.Scanner;

public abstract class Account
{
	
	protected 		 int 				balance;
	protected 		 int 				accountno;
	protected		 String 			bankname;
	protected		 ArrayList<String>  statement;
	protected        ArrayList<String>  deductions;
	protected		 int 				Password;
	protected		 String 			DateCreated;
	protected static int 				counter = 1000;
	
	protected Scanner sc1 = new Scanner(System.in);
	
	public Account()
	{
		deductions = new ArrayList<String>();
	}
	
	public Account(int balance, int accountno, String bankname, ArrayList<String> statement, ArrayList<String> deductions, int password, String dateCreated)
	{
		this.balance = balance;
		this.accountno = accountno;
		this.bankname = bankname;
		this.statement = statement;
		this.deductions = deductions;
		Password = password;
		DateCreated = dateCreated;
	}

	public boolean login()
	{
		System.out.print("Enter Your Password: ");
		int temp;
		temp = sc1.nextInt();
		if (temp == Password)
		{
			System.out.println("Login Successfull!");
			return true;
		}
		else 
		{
			System.out.println("Login Failed!");
			return false;
		}
	}
	
	public int getAccountno() 
	{
		
		return accountno;
	}
	
	public int checkBalance()
	{
		System.out.println("Your Current Balance is " + balance + " as of " + LocalDateTime.now());
		return balance;
	}
	
	public void makeDeposit(int amount)
	{
		if (amount == 0)
		{
		System.out.print("Enter The Amount You Want to Deposit: ");
		amount = sc1.nextInt();
		}
		
		if ( amount > 0)
		{
			balance = balance + amount;
			System.out.println("Operation Successfull! Your New Balance is "+balance );
			statement.add( "You Deposited " + amount + " to Account " + accountno + " Date: " + LocalDateTime.now());
		}
		else
		{
			System.out.println("Invalid Operation!");
		}	
		
	}
	
	public void makeWithdrawal(int amount)
	{
		if (amount == 0)
		{
		System.out.print("Enter The Amount You Want to Withdraw: ");
		amount = sc1.nextInt();
		}
		
		if ( amount <= balance)
		{
			balance = balance - amount;
			System.out.println("Operation Successfull! Your New Balance is "+balance );
			statement.add( "You Withdraw  " + amount + " from Account " + accountno + " Date: " + LocalDateTime.now());
		}
		else
		{
			System.out.println("Invalid Operation!");
		}	
		
	}

	public void showStatement()
	{
		System.out.println("Bank Name: " + bankname);
		System.out.println("************************************************************************************");
		for (String s : statement)
		{
			System.out.println(s);
		}
		System.out.println("************************************************************************************");
	}
	
	public void transferAmount(ArrayList<Customer> ac)
	{
		System.out.println("Enter The Amount You Want to Transfer: ");
		int amount = 0;
		amount = sc1.nextInt();
		if ( amount > 0 && amount <= balance)
		{
			System.out.println("Enter The Account No You Want to Transfer amount : ");
			int accountno = 0;
			accountno = sc1.nextInt();
			if (accountno > 0)
			{
				int status = 0;
				for(Customer a : ac)
				{
					if (a.getAccount(1)!=null &&  a.getAccount(1).getAccountno() == accountno)
					{
						
						a.getAccount(1).balance = a.getAccount(1).balance + amount;
						balance = balance - amount;
						status = 1;
						a.getAccount(1).statement.add("Account " + this.accountno + " Deposited " + amount + " to Your Account on Date: " + LocalDateTime.now());
						statement.add( "You Transferred " + amount + " to Account " + accountno + " Date: " + LocalDateTime.now());
						break;
					}
					else if (a.getAccount(2)!=null && a.getAccount(2).getAccountno() == accountno)
					{
						
						a.getAccount(2).balance = a.getAccount(2).balance + amount;
						balance = balance - amount;
						status = 1;
						a.getAccount(2).statement.add("Account " + this.accountno + " Deposited " + amount + " to Your Account on Date: " + LocalDateTime.now());
						statement.add( "You Transferred " + amount + " to Account " + accountno + " Date: " + LocalDateTime.now());
						break;
					}
				}
				if (status == 1)
				{
					System.out.println("Operation Successfull! Your New Balance is "+balance );
					
				}
				else { System.out.println("Operation Failed! Account Not Found");}
			}
			else
			{
				System.out.println("Invalid Operation! Invalid Account No");
			}	
		}
	}
	
	public float CalculateInterestRate()
	{
		System.out.println("You Have A Checking Account. You cannot Perform this Operation!");
		return 0;
	}

	public void calculateZakat()
	{
		double amount = 0;
		amount = balance * 0.025;
		System.out.println("Zakat For this Year : " + amount);
		System.out.println("Do You want to Pay Zakat (0~1): ");
		int choice = sc1.nextInt();
		if (choice == 1 )
		{
			balance = balance - (int) (amount);
			System.out.println("Zakat Paid Successfully!");
			deductions.add("Zakat " + amount + " paid successfully on " + LocalDateTime.now());
			checkBalance();		
		}
	}

	public void viewallDeductions()
	{
		if (deductions.size()>0)
		{
			System.out.println("**************************************************************************************88");
			for(String s : deductions )
			{
				System.out.println(s);
			}
			System.out.println("**************************************************************************************88");
		}
		else 
		{
			System.out.println("No Record Found!");
		}
	}
	
	public abstract String getDetails();

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public String getBankname() {
		return bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	public ArrayList<String> getStatement() {
		return statement;
	}

	public void setStatement(ArrayList<String> statement) {
		this.statement = statement;
	}

	public ArrayList<String> getDeductions() {
		return deductions;
	}

	public void setDeductions(ArrayList<String> deductions) {
		this.deductions = deductions;
	}

	public int getPassword() {
		return Password;
	}

	public void setPassword(int password) {
		Password = password;
	}

	public String getDateCreated() {
		return DateCreated;
	}

	public void setDateCreated(String dateCreated) {
		DateCreated = dateCreated;
	}

	public void setAccountno(int accountno) {
		this.accountno = accountno;
	}
	public abstract int getWithdrawalscount();
	public abstract float getinterestrate();
}
