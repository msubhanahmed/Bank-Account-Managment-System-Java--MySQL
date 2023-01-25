package MainPackage;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class CheckingAccount extends Account
{
	private int withdrawalscount;
	
	public CheckingAccount(int balance, int accountno, String bankname,  int password, String dateCreated , int wdcount , ArrayList<String> statement, ArrayList<String> deductions) {
		super(balance, accountno, bankname, statement, deductions, password, dateCreated);
		this.withdrawalscount = wdcount;
		if(accountno>Account.counter)
		{
			Account.counter = accountno;
		}
	}

	public CheckingAccount()
	{
		withdrawalscount = 0;
		accountno = ++counter;
		balance = 0;
		statement = new ArrayList<String>();
		bankname = "JRS Bank Ltd";
		System.out.println("----------------------------------------------------------------------------------------");
		System.out.println("Account No: " + accountno);
		System.out.print("Enter Password: ");
		Password = sc1.nextInt();
		System.out.println("Operation Successfull! \nAccount No: "+ accountno + "\nPassword: " + Password);
		System.out.println("----------------------------------------------------------------------------------------");
	}

	public void setinterestRate()
	{
		System.out.println("Operation Failed! You have a checking account. You cannot perform this operation!");
	}
	
	public void makeWithdrawal(int amount)
	{
		// if value is passed passed into function then don't take input else take input from user
		int status = 0;
		if (amount == 0)
		{
		System.out.print("Enter The Amount You Want to Withdraw: ");
		amount = sc1.nextInt();
		}
		
		while(amount <= 0 )
		{
			System.out.print("Invalid Input! Enter Again: ");
			amount = sc1.nextInt();
		}
		// if amount is greater than balance + 500 then stop transaction
		
		
		if (withdrawalscount >= 2)
		{
			balance = balance - 10;
			deductions.add("Deduction Fee RS 10 Deducted from your account on withdrawal Date: " + LocalDateTime.now());
		}
		if ( amount <= balance + 500 )
		{
			balance = balance - amount;
			System.out.println("Operation Successfull! Your New Balance is "+balance );
			statement.add( "You Withdraw  " + amount + " from Account " + accountno + " Date: " + LocalDateTime.now());
			withdrawalscount++;
			status = 1;
		}
		else
		{
			if (status ==1 ) { balance = balance + 10 ;}
			System.out.println("Invalid Operation! Amount is greater than Balance");
		}	
		
	}
	
	public String getDetails()
	{
		String data = balance + "," + accountno + "," + bankname + "," + Password + "," + DateCreated + "," + withdrawalscount + "\n";
		data = data + "Statement,";
		for(String i : statement)
		{
			data = data + i + ",";
		}
		data = data + "\nDeductions,";
		for(String i : deductions)
		{
			data = data + i + ",";
		}
		data = data + "\n";
		return data;
	}

	public int getWithdrawalscount() {
		return withdrawalscount;
	}

	public void setWithdrawalscount(int withdrawalscount) {
		this.withdrawalscount = withdrawalscount;
	}

	
	public float getinterestrate() {
		return 0;
	}
	
}
