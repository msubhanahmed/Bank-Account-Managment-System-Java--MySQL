package MainPackage;
import java.util.Scanner;
import java.util.ArrayList;
import DB.*;

public class Console 
{
	
	// ************************************************************************************************************
	
	public static Account CurrentLogin;
	public static Customer CurrentLogincust;
	public static ArrayList<Customer> customer_list = new ArrayList<Customer>();
	public static DB_Handler db = new DB_Handler();
	
	// ************************************************************************************************************	
	private void display_menu ()
	{
		System.out.println("--------------------------------------------------------------------------------------------------------");
		System.out.println
		(
		  "1. Create New Account \n"
		+ "2. Close An Old Account \n"
		+ "3. Account Login \n"
		+ "4. Deposit Amount \n"
		+ "5. WithDraw Amount \n"
		+ "6. Transfer Amount \n"
		+ "7. Check Balance \n"
		+ "8. Calculate Interest Rate \n"
		+ "9. Calculate Zakat \n"
		+ "10. Print Statement \n"
		+ "11. Show All Deductions \n"
		+ "12. Set Interest Rate \n"
		+ "0. Exit"
		);
		System.out.println("--------------------------------------------------------------------------------------------------------");
		System.out.print("Enter Your Choice ~(0-12): ");
	}
	
	public static void main(String[] Args)
	{
		
		Scanner sc = new Scanner(System.in);
		Console n = new Console();
		customer_list = db.loadcustomer(); 
		
		while(true)
		{
			
			n.display_menu();
			int choice = sc.nextInt();
			
			// Exit Menu
			// ********************************************Exit Menu *************************************************************************************
			if(choice == 0)
			{
				System.out.println("******************  Program Closed! **************************");
				return ;
			}
			
			/* Create New Account
			 * Ask if customer is already registered or not
			 * If Not then Register Customer
			 * Else Ask Account Type and create account
			 */
			// ****************************************Create New Account **********************************************************************************************
			else if (choice == 1 )
			{
				System.out.print("Press 1 If you're a Registered Customer (0 ~ 1 ): ");		// Ask if Already registered
				choice = sc.nextInt();
				if (choice == 1 )
				{
					System.out.print("Enter Your Existing Customer No: ");						// If yes then confirm and then create new account
					choice = sc.nextInt();
					for(Customer c: customer_list)
					{
						
						if(c.getID() == choice )
						{
							if(c.getCheckingAccount() == 1 && c.getSavingAccount() != 1)
							{
								System.out.print("Press Any Key to Create Saving Account");
								sc.nextLine();
								c.createAccount(2);
								db.saveSaving(c);
								db.updatesavingaccountstatus(c);
							}
							else if (c.CheckAccount(1) == false && c.CheckAccount(2) == true)
							{
								System.out.print("Press Any Key to Create Checking Account");
								sc.nextLine();
								c.createAccount(1);
								db.saveChecking(c);
								db.updatecheckingaccountstatus(c);
							}
							else if (c.CheckAccount(1) == true && c.CheckAccount(2) == true)
							{
								System.out.print("You Cannot Create More Accounts!");
							}
							break;
						}
					}
				}
				else if (choice == 0)
				{
					Customer c = new Customer();
					customer_list.add(c);			// Customer is not registered call customer constructor to register and create account
					db.savecustomer(c);
					
				}
			}

			// ***************************************** Close Account  *********************************************************************************************
			
			
			else if (choice == 2 )
			{
				System.out.print("Enter the Customer No: ");		// Login Into bank Account
				int customerno = 0;
				int bankaccount = 0;
				customerno = sc.nextInt();
				if(customerno > 0)
				{
					int status = 0;
					for(Customer c : customer_list)
					{
						
						if(c.getID() == customerno)
						{
							System.out.print("Enter the Bank Account No: ");		// Login Into bank Account
							bankaccount = sc.nextInt();
							
							
							if(c.getAccount(1) != null &&  c.getAccount(1).getAccountno() == bankaccount)
							{
								if(c.getAccount(1).login() == true )
								{
									if(CurrentLogin != null &&  CurrentLogin.equals(c.getAccount(1)))
									{
										CurrentLogin = null;
									}
									
									db.deletechecking(c);
									c.deleteaccount(1);
									
									status = 1;
									break;
								}
							}
							else  if(c.getAccount(2) != null &&  c.getAccount(2).getAccountno() == bankaccount)
							{
								if(c.getAccount(2).login() == true )
								{
									if(CurrentLogin != null &&  CurrentLogin.equals(c.getAccount(2)))
									{
										CurrentLogin = null;
									}
									
									db.deletesaving(c);
									c.deleteaccount(2);
									status = 1;
									break;
								}
							}
						}
					}
					if (status == 0) {System.out.println(" ***************  Operation Failed! Account Not Found!      ***************");}
					else if (status == 1 ) {System.out.println(" ***************  Operation Successfull! Account Deleted!      ***************");}
				}
				else {System.out.println("Invalid Input!");}
			}
			
			// ***************************************** Login Account  *********************************************************************************************
			
			
			else if (choice == 3 )
			{
				System.out.print("Enter the Bank Account No: ");		// Login Into bank Account
				int bankaccount;
				bankaccount = sc.nextInt();
				if(bankaccount > 0)
				{
					int status = 0;
					for(Customer c : customer_list)
					{
						if(c.getAccount(1) != null &&  c.getAccount(1).getAccountno() == bankaccount)
						{
							if(c.getAccount(1).login() == true )
							{
								CurrentLogin = c.getAccount(1);
								CurrentLogincust = c;
								status = 1;
								break;
							}
						}
						else  if(c.getAccount(2) != null &&  c.getAccount(2).getAccountno() == bankaccount)
						{
							if(c.getAccount(2).login() == true )
							{
								CurrentLogin = c.getAccount(2);
								CurrentLogincust = c;
								status = 1;
								break;
							}
						}
					}
					if (status == 0) {System.out.println(" ***************  Operation Failed! Account Not Found!      ***************");}
					
				}
				else {System.out.println("Invalid Input!");}
			}
			
			// **************************************************Bank Account Operations***********************************************************************************
			// ***************************************************Deposit Amount**********************************************************************************
			
			else if (choice == 4 )
			{
				if (CurrentLogin == null)
				{
					System.out.println(" **  Invalid Operation! Please Login Into Account To Perform Login Operations");
				}
				else 
				{
					CurrentLogin.makeDeposit(0);
					
				}
			}
			
			// ***************************************************WithDraw Amount**********************************************************************************
			
			else if (choice == 5 )
			{
				if (CurrentLogin == null)
				{
					System.out.println(" **  Invalid Operation! Please Login Into Account To Perform Login Operations");
				}
				else 
				{
					CurrentLogin.makeWithdrawal(0);
				}
			}
			
			// ***************************************************Transfer Amount**********************************************************************************
			
			else if (choice == 6 )
			{
				if (CurrentLogin == null)
				{
					System.out.println(" **  Invalid Operation! Please Login Into Account To Perform Login Operations");
				}
				else 
				{
					CurrentLogin.transferAmount(customer_list);
				}
			}
			
			// ***************************************************Check Balance**********************************************************************************
			
			else if (choice == 7 )
			{
				if (CurrentLogin == null)
				{
					System.out.println(" **  Invalid Operation! Please Login Into Account To Perform Login Operations");
				}
				else 
				{
					CurrentLogin.checkBalance();
				}
			}
			
			// ***************************************************Check Interest**********************************************************************************
			
			else if (choice == 8 )
			{
				if (CurrentLogin == null)
				{
					System.out.println(" **  Invalid Operation! Please Login Into Account To Perform Login Operations");
				}
				else 
				{
					CurrentLogin.CalculateInterestRate();
				}
			}

			// ***************************************************Check Zakat **********************************************************************************
			
			else if (choice == 9 )
			{
				if (CurrentLogin == null)
				{
					System.out.println(" **  Invalid Operation! Please Login Into Account To Perform Login Operations");
				}
				else 
				{
					CurrentLogin.calculateZakat();
				}
			}
			
			// ***************************************************Show Statement**********************************************************************************
			
			else if (choice == 10 )
			{
				if (CurrentLogin == null)
				{
					System.out.println(" **  Invalid Operation! Please Login Into Account To Perform Login Operations");
				}
				else 
				{
					CurrentLogincust.ShowDetails();
					CurrentLogin.showStatement();
				}
			}
						
			

			// ***************************************************View All Deductions**********************************************************************************
			
			else if (choice == 11 )
			{
				if (CurrentLogin == null)
				{
					System.out.println(" **  Invalid Operation! Please Login Into Account To Perform Login Operations");
				}
				else 
				{
					CurrentLogincust.ShowDetails();
					CurrentLogin.viewallDeductions();
				}
			}
			

			// ***************************************************Set Interest Rate**********************************************************************************
			
			else if (choice == 12 )
			{
			
				float interestrate;
				System.out.print("Enter the Interest Rate: ");
				interestrate = sc.nextFloat();
				while(interestrate < 0)
				{
					System.out.print("Invalid Input! Enter Again: ");
					interestrate = sc.nextFloat();
				}
				SavingAccount.setinterestRate(interestrate);
			}
		
			if(CurrentLogin != null)
			{
				//****************************
				//n.savecustomer(CurrentLogincust);
			}
		}
	}
	
	
	/*
	private void savetofile()
	{
		File checkdir = new File("Customers");
		if(!checkdir.exists())
		{
			checkdir.mkdir();
		}
		
		for(Customer i : customer_list)
		{
			String FileName = i.getID()+ ".txt";
			File filereader = new File( "Customers"+File.separator+FileName);
			FileWriter  ostream;
			Account n1 , n2;
			try 
			{
				filereader.createNewFile();
			
			}
			catch (IOException e)
			{
				System.out.println("Error Creating File...\n");
				e.printStackTrace();
				return;
			}
			try 
			{
				ostream = new FileWriter("Customers" + File.separator + FileName,false);
				ostream.write(i.returndetails());
				
				if(i.CheckAccount(1) && i.getAccount(1)!=null)
				{
					n1 = i.getAccount(1);
					ostream.write(n1.getDetails());
				}
				if (i.CheckAccount(2) && i.getAccount(2)!=null)
				{
					n2 = i.getAccount(2);
					ostream.write(n2.getDetails());
				}
				ostream.close();
			}
			catch (IOException e) 
			{
				System.out.println("Could Not Open file" + FileName );
				e.printStackTrace();
				return;
			}
		}
		return;
		
	}

	public void savecustomer(Customer c)
	{
		String FileName = c.getID()+ ".txt";
		File filereader = new File( "Customers"+File.separator+FileName);
		FileWriter  ostream;
		Account n1 , n2;
		try 
		{
			filereader.createNewFile();
		
		}
		catch (IOException e)
		{
			System.out.println("Error Creating File...\n");
			e.printStackTrace();
			return;
		}
		try 
		{
			ostream = new FileWriter("Customers" + File.separator + FileName,false);
			ostream.write(c.returndetails());
			
			if(c.CheckAccount(1) && c.getAccount(1)!=null)
			{
				n1 = c.getAccount(1);
				ostream.write(n1.getDetails());
			}
			if (c.CheckAccount(2) && c.getAccount(2)!=null)
			{
				n2 = c.getAccount(2);
				ostream.write(n2.getDetails());
			}
			ostream.close();
		}
		catch (IOException e) 
		{
			System.out.println("Could Not Open file" + FileName );
			e.printStackTrace();
			return;
		}
	}

	public void loadfiles () 
	{
		File filemanager = new File("Customers");
		if(filemanager.exists())
		{
			System.out.println("Directory Found! Importing Files...");
			String[] files = filemanager.list();
			for(String i : files)
			{
				FileInputStream fr;
				try 
				{
					fr = new FileInputStream("Customers" + File.separator + i );
					Scanner sc = new Scanner(fr);
					System.out.println("Found Customer: "+ i);
					String line = new String();
					while(sc.hasNextLine())
					{
						line = line + "\n" + sc.nextLine();
					}
					sc.close();
					loadCustomer(line);
				}
				catch (FileNotFoundException e) 
				{
					e.printStackTrace();
				}
				
			}
		}
		else
		{
			System.out.println("No Customers Directory Found....");
			return;
		}
	}
	
	private Customer loadCustomer(String d)
	{
		StringTokenizer st = new StringTokenizer(d , "\n");
		System.out.println("Tokens:");
	
		if(st.hasMoreTokens())
		{
			String customerdetails = st.nextToken();
			StringTokenizer s2 = new StringTokenizer(customerdetails, ",");
			try {
				Customer c = new Customer(Integer.parseInt(s2.nextToken()),s2.nextToken(),s2.nextToken(),s2.nextToken(),Integer.parseInt(s2.nextToken()),Integer.parseInt(s2.nextToken()));
				if(c.getIdcount() < c.getCustomerID())
				{
					c.setIdcount(c.getCustomerID());
				}
				customer_list.add(c);
				if(c.CheckAccount(1) == true)
				{
					String Account1 = st.nextToken();
					StringTokenizer s3 = new StringTokenizer(Account1 , ",");
					StringTokenizer s4 = new StringTokenizer(st.nextToken(),",");
					StringTokenizer s5 = new StringTokenizer(st.nextToken(),",");
					
					ArrayList<String> statement = new ArrayList<String>();
					ArrayList<String> deductions = new ArrayList<String>();
					
					Account n1;
					try {
						while (s4.hasMoreTokens()) {
							statement.add(s4.nextToken());
						}
						while (s5.hasMoreTokens()) {
							deductions.add(s5.nextToken());
						}
						n1 = new CheckingAccount(Integer.parseInt(s3.nextToken()), Integer.parseInt(s3.nextToken()),s3.nextToken(), Integer.parseInt(s3.nextToken()), s3.nextToken(),Integer.parseInt(s3.nextToken()), statement, deductions);
						c.setChecking(n1);
					} catch (Exception e) {
						System.out.println("Error Parsing Checking Account Of Customer...");
					}
					
					
					
				}
				if(c.CheckAccount(2) == true)
				{
					String Account1 = st.nextToken();
					StringTokenizer s3 = new StringTokenizer(Account1 , ",");
					StringTokenizer s4 = new StringTokenizer(st.nextToken(),",");
					StringTokenizer s5 = new StringTokenizer(st.nextToken(),",");
					
					ArrayList<String> statement = new ArrayList<String>();
					ArrayList<String> deductions = new ArrayList<String>();
					
					Account n1;
					try {
						while (s4.hasMoreTokens()) {
							statement.add(s4.nextToken());
						}
						while (s5.hasMoreTokens()) {
							deductions.add(s5.nextToken());
						}
						n1 = new SavingAccount(Integer.parseInt(s3.nextToken()), Integer.parseInt(s3.nextToken()),s3.nextToken(), Integer.parseInt(s3.nextToken()), s3.nextToken(), statement, deductions);
						c.setSaving(n1);
					} catch (Exception e) {
						System.out.println("Error Parsing Checking Account Of Customer...");
					}
				}
				
				return c;
			} catch (NumberFormatException e) {
				System.out.println("Error Parsing Data....");
				e.printStackTrace();
			}
			
		}
		else
		{
			System.out.println("Error Parsing Data....");
			return null;
		}
		return null;
		
	}

	*/

}
