package DB;

import java.sql.*;
import MainPackage.*;
import java.util.ArrayList;

public class DB_Handler 
{
	private String name = "root";
	private String pass = "Subh@n2004639";
	private Connection con ;
	
	public DB_Handler() 
	{
		try {
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/banksystem",name,pass);
			System.out.println("Connection made to DB");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Connection failed");
		}
	}
	
	public void savecustomer(Customer i)
	{
		// save customer details
		String query = "Insert into customer values ( ? , ? , ? , ? , ? ,?);";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, i.getID());
			ps.setString(2, i.getName());
			ps.setString(3, i.getAddress());
			ps.setString(4, i.getPhoneNo());
			ps.setInt(5, i.getCheckingAccount());
			ps.setInt(6, i.getSavingAccount());
			ps.executeUpdate();
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Save Checking Account Details
		if(i.getCheckingAccount() == 1)
		{
			query = "Insert into checkingaccount values ( ? , ? , ? , ? , ? , ? , ?);";
			try {
				PreparedStatement ps = con.prepareStatement(query);
				ps.setInt(1, i.getChecking().getAccountno());
				ps.setInt(2, i.getID());
				ps.setInt(3, i.getChecking().getBalance());
				ps.setInt(4, i.getChecking().getPassword());
				ps.setString(5, i.getChecking().getBankname());
				ps.setString(6, i.getChecking().getDateCreated());
				ps.setInt(7, i.getChecking().getWithdrawalscount());
				ps.executeUpdate();
				
				ArrayList<String> statement = i.getChecking().getStatement();
				ArrayList<String> deduction = i.getChecking().getDeductions();
				
				for(String s : statement)
				{
					String q = "Insert into statement values (?,?)";
					PreparedStatement pq = con.prepareStatement(q);
					pq.setInt(1,i.getChecking().getAccountno() );
					pq.setString(2, s);
					pq.executeUpdate();
				}
				
				for(String s : deduction)
				{
					String q = "Insert into deductions values (?,?)";
					PreparedStatement pq = con.prepareStatement(q);
					pq.setInt(1,i.getChecking().getAccountno() );
					pq.setString(2, s);
					pq.executeUpdate();
				}
			} 
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		
		// Save Saving Account Details
		if(i.getSavingAccount() == 1)
		{
			query = "Insert into savingaccount values ( ? , ? , ? , ? , ? ,?,?);";
			try {
				PreparedStatement ps = con.prepareStatement(query);
				ps.setInt(1, i.getSaving().getAccountno());
				ps.setInt(2, i.getID());
				ps.setInt(3, i.getSaving().getBalance());
				ps.setInt(4, i.getSaving().getPassword());
				ps.setString(5, i.getSaving().getBankname());
				ps.setString(6, i.getSaving().getDateCreated());
				ps.setFloat(7, i.getSaving().getinterestrate());
				ps.executeUpdate();
				
				ArrayList<String> statement = i.getSaving().getStatement();
				ArrayList<String> deduction = i.getSaving().getDeductions();
				
				for(String s : statement)
				{
					String q = "Insert into statement values (?,?)";
					PreparedStatement pq = con.prepareStatement(q);
					pq.setInt(1,i.getChecking().getAccountno() );
					pq.setString(2, s);
					pq.executeUpdate();
				}
				
				for(String s : deduction)
				{
					String q = "Insert into deductions values (?,?)";
					PreparedStatement pq = con.prepareStatement(q);
					pq.setInt(1,i.getChecking().getAccountno() );
					pq.setString(2, s);
					pq.executeUpdate();
				}
			} 
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	public void deletecustomer(Customer i)
	{
		String q3 = "Delete from customer where (id = ?)";
		String q2 = "Delete from statement where (aid = ?);";
		String q1 = "Delete from deductions where (aid = ?);";
		
		try {
			PreparedStatement ps;
			ps = con.prepareStatement(q2);
			if(i.getChecking() != null)
			{
				ps.setInt(1, i.getChecking().getAccountno());
				ps.executeUpdate();
				
				ps = con.prepareStatement(q1);
				ps.setInt(1, i.getChecking().getAccountno());
				ps.executeUpdate();
			}
			if(i.getSaving() != null)
			{	
				ps = con.prepareStatement(q2);
				ps.setInt(1, i.getSaving().getAccountno());
				ps.executeUpdate();
				
				ps = con.prepareStatement(q1);
				ps.setInt(1, i.getSaving().getAccountno());
				ps.executeUpdate();
			}
			ps = con.prepareStatement(q3);
			ps.setInt(1, i.getID());
			ps.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Account loadSavingAccount(Customer c)
	{
		try {
			String q2 = "Select * from savingaccount where (cid = "+c.getID() +")";
			Statement s2 = con.createStatement();
			ResultSet r5 = s2.executeQuery(q2);
			Account a1 = null;
			if(r5.next())
			{
				ArrayList<String> statement = new ArrayList<String>();
				ArrayList<String> deduction = new ArrayList<String>();
				a1= new SavingAccount(r5.getInt(3), r5.getInt(1), r5.getString(5), r5.getInt(4), r5.getString(6), null,null);
				String q3 = "Select record from statement where aid = " + a1.getAccountno();
				Statement s3 = con.createStatement();
				ResultSet r3 = s3.executeQuery(q3);
				while(r3.next())
				{
					statement.add(r3.getString(1));
				}
				
				String q4 = "Select record from deductions where aid = " + a1.getAccountno();
				Statement s4 = con.createStatement();
				ResultSet r4 = s4.executeQuery(q4);
				while(r4.next())
				{
					deduction.add(r4.getString(1));
				}
				
				a1.setStatement(statement);
				a1.setDeductions(deduction);	

				System.out.println(a1.getDetails());
			}
			return a1;
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
		
	}
	public Account loadCheckingAccount(Customer c)
	{
		Account a1 = null;
		try {
			String q2 = "Select * from checkingaccount where (cid = "+c.getID() +")";
			Statement s2 = con.createStatement();
			ResultSet r2 = s2.executeQuery(q2);
			
			if(r2.next())
			{
				ArrayList<String> statement = new ArrayList<String>();
				ArrayList<String> deduction = new ArrayList<String>();
				a1 = new CheckingAccount(r2.getInt(3), r2.getInt(1), r2.getString(5), r2.getInt(4), r2.getString(6) , r2.getInt(7), null,null);
				String q3 = "Select record from statement where aid = " + a1.getAccountno();
				Statement s3 = con.createStatement();
				ResultSet r3 = s3.executeQuery(q3);
				while(r3.next())
				{
					statement.add(r3.getString(1));
				}
				
				String q4 = "Select record from deductions where aid = " + a1.getAccountno();
				//Statement s4 = con.createStatement();
				ResultSet r4 = s3.executeQuery(q4);
				while(r4.next())
				{
					deduction.add(r4.getString(1));
				}
				
				a1.setStatement(statement);
				a1.setDeductions(deduction);
				
				System.out.println(a1.getDetails());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a1;
	}
	
	public void saveChecking(Customer i)
	{
		String query = "Insert into checkingaccount values ( ? , ? , ? , ? , ? , ? , ?);";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, i.getChecking().getAccountno());
			ps.setInt(2, i.getID());
			ps.setInt(3, i.getChecking().getBalance());
			ps.setInt(4, i.getChecking().getPassword());
			ps.setString(5, i.getChecking().getBankname());
			ps.setString(6, i.getChecking().getDateCreated());
			ps.setInt(7, i.getChecking().getWithdrawalscount());
			ps.executeUpdate();
			
			ArrayList<String> statement = i.getChecking().getStatement();
			ArrayList<String> deduction = i.getChecking().getDeductions();
			
			for(String s : statement)
			{
				String q = "Insert into statement values (?,?)";
				PreparedStatement pq = con.prepareStatement(q);
				pq.setInt(1,i.getChecking().getAccountno() );
				pq.setString(2, s);
				pq.executeUpdate();
			}
			
			for(String s : deduction)
			{
				String q = "Insert into deductions values (?,?)";
				PreparedStatement pq = con.prepareStatement(q);
				pq.setInt(1,i.getChecking().getAccountno() );
				pq.setString(2, s);
				pq.executeUpdate();
			}
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void saveSaving(Customer i)
	{
		String query = "Insert into savingaccount values ( ? , ? , ? , ? , ? , ? , ?);";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, i.getSaving().getAccountno());
			ps.setInt(2, i.getID());
			ps.setInt(3, i.getSaving().getBalance());
			ps.setInt(4, i.getSaving().getPassword());
			ps.setString(5, i.getSaving().getBankname());
			ps.setString(6, i.getSaving().getDateCreated());
			ps.setFloat(7, i.getSaving().getinterestrate());
			ps.executeUpdate();
			
			ArrayList<String> statement = i.getSaving().getStatement();
			ArrayList<String> deduction = i.getSaving().getDeductions();
			
			for(String s : statement)
			{
				String q = "Insert into statement values (?,?)";
				PreparedStatement pq = con.prepareStatement(q);
				pq.setInt(1,i.getChecking().getAccountno() );
				pq.setString(2, s);
				pq.executeUpdate();
			}
			
			for(String s : deduction)
			{
				String q = "Insert into deductions values (?,?)";
				PreparedStatement pq = con.prepareStatement(q);
				pq.setInt(1,i.getChecking().getAccountno() );
				pq.setString(2, s);
				pq.executeUpdate();
			}
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deletesaving(Customer i)
	{
		String q3 = "Delete from savingaccount where (cid = ?)";
		String q2 = "Delete from statement where (aid = ?);";
		String q1 = "Delete from deductions where (aid = ?);";
		String q4 = "update customer set saccount = 0 where id = ?";
		
		try {
			PreparedStatement ps;
			
			if(i.getSaving() != null)
			{	
				ps = con.prepareStatement(q2);
				ps.setInt(1, i.getSaving().getAccountno());
				ps.executeUpdate();
				
				ps = con.prepareStatement(q1);
				ps.setInt(1, i.getSaving().getAccountno());
				ps.executeUpdate();
			}
			ps = con.prepareStatement(q3);
			ps.setInt(1, i.getID());
			ps.executeUpdate();
			
			ps = con.prepareStatement(q4);
			ps.setInt(1, i.getID());
			ps.executeUpdate();
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deletechecking(Customer i)
	{
		String q3 = "Delete from checkingaccount where (cid = ?)";
		String q2 = "Delete from statement where (aid = ?);";
		String q1 = "Delete from deductions where (aid = ?);";
		
		try {
			PreparedStatement ps;
			
			if(i.getSaving() != null)
			{	
				ps = con.prepareStatement(q2);
				ps.setInt(1, i.getChecking().getAccountno());
				ps.executeUpdate();
				
				ps = con.prepareStatement(q1);
				ps.setInt(1, i.getChecking().getAccountno());
				ps.executeUpdate();
			}
			ps = con.prepareStatement(q3);
			ps.setInt(1, i.getID());
			ps.executeUpdate();
			
			String q4 = "update customer set caccount = 0 where id = ?";
			
			ps = con.prepareStatement(q4);
			ps.setInt(1, i.getID());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
 	public ArrayList<Customer> loadcustomer()
	{
		ArrayList<Customer> clist = new ArrayList<Customer>();
		String query = "Select * from customer";
		
		try {
			Statement s = con.createStatement();
			ResultSet r = s.executeQuery(query);
			while(r.next())
			{
				Customer c = new Customer(r.getInt(1), r.getString(2) , r.getString(3) , r.getString(4) , r.getInt(5) , r.getInt(6));
				
				//******************************************** Load Account ******************************************
				
				if(c.getCheckingAccount() == 1)
				{
					
					c.setChecking(loadCheckingAccount(c));
				}
				
				if(c.getSavingAccount() == 1)
				{
					c.setSaving(loadSavingAccount(c));
				}
				clist.add(c);				
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return clist;
	}

 	public void adddeduction(int acno , String s)
 	{
 		String q = "Insert into deductions values (?,?)";
		try {
			PreparedStatement pq = con.prepareStatement(q);
			pq.setInt(1,acno);
			pq.setString(2, s);
			pq.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 	}
 	public void addstatement(int acno , String s)
 	{
 		String q = "Insert into statement values (?,?)";
		try {
			PreparedStatement pq = con.prepareStatement(q);
			pq.setInt(1, acno);
			pq.setString(2, s);
			pq.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 	}
 	public void updatebalanceSaving(int acno , int amount)
 	{
 		String q4 = "update savingaccount set balance = ? where aid = ?";
 		try {
 			PreparedStatement ps;
			ps = con.prepareStatement(q4);
			ps.setInt(1, amount);
			ps.setInt(2, acno);
			ps.executeUpdate();
		} catch (SQLException e) {}
 	}
 	public void updatebalanceChecking(int acno , int amount)
 	{
 		String q4 = "update checkingaccount set balance = ? where aid = ?";
 		try {
 			PreparedStatement ps;
			ps = con.prepareStatement(q4);
			ps.setInt(1, amount);
			ps.setInt(2, acno);
			ps.executeUpdate();
		} catch (SQLException e) {}
 	}
 	public void updatesavingaccountstatus(Customer i)
 	{
 		String q4 = "update customer set saccount = 1 where id = ?";
		try {

			PreparedStatement ps;
			ps = con.prepareStatement(q4);
			ps.setInt(1, i.getID());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
 	}
 	public void updatecheckingaccountstatus(Customer i)
 	{
 		String q4 = "update customer set caccount = 1 where id = ?";
		try {

			PreparedStatement ps;
			ps = con.prepareStatement(q4);
			ps.setInt(1, i.getID());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
 	}
}

