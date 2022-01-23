import java.util.*; 
import java.sql.*;   
public class Student {
    public static void main(String[] args) {
    try {
    	boolean chk=true;
    	Class.forName("com.mysql.jdbc.Driver");  
    	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Intern","root","root");  
    	
    	while(chk)
    	{		
    	System.out.println("Press 1 to Insert student data into Student table");
    	System.out.println("Press 2 to Update student data into Student table");
    	System.out.println("Press 3 to Delete student data from Student table");
    	System.out.println("Press 4 to Get a list of all students");
    	System.out.println("Press 5 to Get one student information depending on the student id filter.");
    	System.out.println("Press 0 to EXIT the Program");
    	
    	Scanner sc= new Scanner(System.in);
    	int ks=sc.nextInt();  
    	
    	switch(ks)
    	{
    	case 0:
    		chk=false;
    		System.out.println("EXIT Successfully!!!");
    		break;
    	case 1:
    		System.out.println("Enter Student Roll Number");
    		int StudentNo1=sc.nextInt();
    		sc.nextLine();
    		System.out.println("Enter Name of Student");
    		String StudentName1=sc.nextLine();
    		sc.nextLine();
    		System.out.println("Enter Date Of Birth(DD-MM-YYYY)");
    		String StudentDOB1=sc.nextLine();
    		System.out.println("Enter Date Of Joining(DD-MM-YYYY)");
    		String StudentDOJ1=sc.nextLine();
    		
    		PreparedStatement pstmt1=con.prepareStatement("insert into STUDENT values(?,?,?,?)"); 
    		pstmt1.setInt(1,StudentNo1);  
    		pstmt1.setString(2,StudentName1);
    		pstmt1.setString(3,StudentDOB1);
    		pstmt1.setString(4,StudentDOJ1);  
    		System.out.println(pstmt1.executeUpdate()+" Record Inserted Sucessfully");  
    		System.out.println();
    		break;
    	case 2:
    		System.out.println("Enter Student Roll Number");
    		int StudentNo2=sc.nextInt();
    		sc.nextLine();
    		System.out.println("Enter Student DOB to be updated");
    		String StudentDOB2=sc.nextLine();
    		
    		PreparedStatement pstmt2 = con.prepareStatement("Update STUDENT set STUDENT_DOB=? where STUDENT_NO=?");
    		pstmt2.setString(1,StudentDOB2);
    		pstmt2.setInt(2, StudentNo2);
    	     pstmt2.executeUpdate();
    	    System.out.println("Date of Birth Updated successfully ");
    		System.out.println();
    	    break;
    	case 3:
    		System.out.println("Enter Student Roll Number");
    		int StudentNo3=sc.nextInt();
    		
    		PreparedStatement pstmt3=con.prepareStatement("delete from STUDENT where STUDENT_NO=?");
    		pstmt3.setInt(1,StudentNo3);
    		pstmt3.executeUpdate();
    		System.out.println("Record Deleted Sucessfully");
    		System.out.println();
    		break;
    	case 4:
    		Statement stmt4=con.createStatement();  		
    		ResultSet rs1=stmt4.executeQuery("select * from STUDENT");  
    		while(rs1.next())
    		{
    		System.out.println(rs1.getInt(1)+"  "+rs1.getString(2)+"  "+rs1.getString(3)+"  "+rs1.getString(4)); 
    		}
    		System.out.println();
    		break;
    	case 5:
    		System.out.println("Enter Student Roll Number");
    		int StudentNo5=sc.nextInt();
    		
    		 PreparedStatement pstmt5 = con.prepareStatement("select * from STUDENT where STUDENT_NO=?");
    		 pstmt5.setInt(1, StudentNo5);
    		 ResultSet rs2 = pstmt5.executeQuery();
    		 while (rs2.next())
    		 {
    	          System.out.println("Stuend ID=" + rs2.getInt(1));
    	          System.out.println("Student Name=" + rs2.getString(2));
    	          System.out.println("Student DOB=" + rs2.getString(3));
    	          System.out.println("Student DOJ=" + rs2.getString(4));
    	          }
    		 System.out.println();
    		break;
    	default:
    		System.out.println("Please enter the correct choice");
    	}
    		}	
    	con.close();	
    	
    }
    catch(Exception e)
    {
    	System.out.println(e);
    }
    }
}
