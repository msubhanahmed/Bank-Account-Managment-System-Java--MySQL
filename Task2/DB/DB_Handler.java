package DB;
import java.sql.*;
import Student.*;
import java.util.ArrayList;

public class DB_Handler 
{
	private String name = "root";
	private String pass = "Subh@n2004639";
	private Connection con ;
	
	public DB_Handler() 
	{
		try {
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TASK",name,pass);
			System.out.println("Connection made to DB");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Connection failed");
		}
	}
	
	public void SaveRecord(Student s)
	{
		String query = "Insert into Student values ( ? , ? , ? , ? , ?);";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, s.getID());
			ps.setString(2, s.getFname());
			ps.setString(3, s.getLname());
			ps.setInt(4, s.getSemester());
			ps.setString(5, s.getPhoneno());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void DeleteRecord(int id)
	{
		String query = "delete from student where ( ID = ? );";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<Student> getRecord()
	{
		ArrayList<Student> StudentList = new ArrayList<Student>();
		String query = "Select * from Student";
		try {
			Statement s = con.createStatement();
			ResultSet r = s.executeQuery(query);
			while(r.next())
			{
				StudentList.add(new Student(r.getInt(1),r.getString(2),r.getString(3),r.getInt(4),r.getString(5)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return StudentList;
	}
	
}
