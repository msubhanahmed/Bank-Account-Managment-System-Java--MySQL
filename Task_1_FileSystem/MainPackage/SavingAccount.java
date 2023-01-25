package MainPackage;
import java.util.ArrayList;
public class SavingAccount extends Account
{
	private static float interestrate = 8;

	public SavingAccount()
	{
		accountno = ++counter;
		balance = 0;
		statement = new ArrayList<String>();
		bankname = "JRS Bank Ltd";
		System.out.println("----------------------------------------------------------------------------------------");
		System.out.println("Account No: " + accountno);
		System.out.print("Enter Password: ");
		Password = sc1.nextInt();
		System.out.println("----------------------------------------------------------------------------------------");
		System.out.println("Operation Successfull! \nAccount No: "+ accountno + "\nPassword: " + Password);
		System.out.println("----------------------------------------------------------------------------------------");
	}
	public SavingAccount(int balance, int accountno, String bankname,int password, String dateCreated, ArrayList<String> statement, ArrayList<String> deductions) {
		super(balance, accountno, bankname, statement, deductions, password, dateCreated);
		if(accountno>Account.counter)
		{
			Account.counter = accountno;
		}
	}
	public float CalculateInterestRate()
	{
		float interest = 0;
		interest = (interestrate / 100 );
		interest = interest * balance;
		System.out.println("Interest Rate : "+ interestrate + "% \nInterest For This Month will be : " + interest);
		return interest;
	}

	public static void setinterestRate(float rate)
	{
		interestrate = rate;
		System.out.println("Operation Successful! New Interest Rate " + interestrate );
		
	}
	public float getinterestrate() {return interestrate;};
	public String getDetails() 
	{
		String data = balance + "," + accountno + "," + bankname + "," + Password + "," + DateCreated + "\n";
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
		return 0;
	}
	
}
