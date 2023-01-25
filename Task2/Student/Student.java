package Student;

public class Student 
{
	private int ID;
	private String fname;
	private String Lname;
	private int semester;
	private String Phoneno;
	
	public Student(int iD, String fname, String lname, int semester, String phoneno) {
		ID = iD;
		this.fname = fname;
		Lname = lname;
		this.semester = semester;
		Phoneno = phoneno;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return Lname;
	}

	public void setLname(String lname) {
		Lname = lname;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public String getPhoneno() {
		return Phoneno;
	}

	public void setPhoneno(String phoneno) {
		Phoneno = phoneno;
	}
	
	public void ShowDetails()
	{
		System.out.println("***************************************************\nID: "+ID+"\nName: "+fname+" "+Lname+"\nSemester: "+semester+"\nContact No: "+ Phoneno + "\n***************************************************\n");
	}
}
