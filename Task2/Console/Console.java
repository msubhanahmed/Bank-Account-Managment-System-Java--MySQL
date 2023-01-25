package Console;
import Student.Student;
import DB.DB_Handler;
import java.util.*;

public class Console 
{
	public static void main(String[] Args)
	{
		DB_Handler d = new DB_Handler();
		Scanner sc = new Scanner(System.in);
		
		int choice = 0;
		while(true)
		{
			System.out.println("*********************************\nPress 1 to enter new Record\nPress 2 to view all records\nPress 3 to Delete Record\nPress 0 to exit\n*********************************\n");
			System.out.print("Enter Choice : ");
			choice = sc.nextInt();
			if(choice == 0 )
			{
				System.out.println("**************Program Closed************************");
				sc.close();
				return;
			}
			else if (choice == 1 )
			{
				int id,sem;
				String fname,lname,phone;
				System.out.print("Enter ID: ");
				id = sc.nextInt();
				System.out.print("Enter First Name: ");
				fname = sc.next();
				System.out.print("Enter Last Name: ");
				lname = sc.next();
				System.out.print("Enter Semester: ");
				sem = sc.nextInt();
				System.out.print("Enter Phone No: ");
				phone = sc.next();
				
				Student s = new Student(id,fname,lname,sem,phone);
				d.SaveRecord(s);
				System.out.println("Record Saved Successfully....");
			}
			else if (choice == 2)
			{
				ArrayList<Student> list = d.getRecord();
				for(Student i:list)
				{
					i.ShowDetails();
				}
			}
			else if (choice == 3)
			{
				System.out.print("Enter ID: ");
				int id = sc.nextInt();
				d.DeleteRecord(id);
			}
		}
		
	}
}
