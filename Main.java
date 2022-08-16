package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args)throws Exception {
		//getConnection();
		//createTable();
		//insertion();
		//delete();
		//ArrayList<String> x=display();
		//for(int i=0;i<x.size();i++)
		//{
		//	System.out.print(x.get(i));
		//}
		//update();

		//createTable();
		Scanner s=new Scanner(System.in);
		Scanner s1=new Scanner(System.in).useDelimiter("\n");
		char ch = ' ';
		String usn,name,address="",semester="",section,department;
		long phone_no;
		
		while(ch!='5')
		{
		System.out.print("\n1.Insert data into table\n2.Display data fields in table\n3.Update name with usn\n4.Delete row having usn\n5.Exit\nEnter your choice:");
		ch=s.next().charAt(0);
		switch(ch)
		{
		case '1':
			System.out.print("\nEnter usn:");
			usn=s.next();
			System.out.print("\nEnter name:");
			name=s1.next();
			name= name.substring(0, name.length() - 1);
			System.out.print("\nEnter address:");
			address=s1.next();
			address= address.substring(0, address.length() - 1);
			System.out.print("\nEnter phone number:");
			phone_no=s.nextLong();
			System.out.print("\nEnter semester:");
			semester=s1.next();
			semester= semester.substring(0, semester.length() - 1);
			System.out.print("\nEnter section:");
			section=s.next();
			System.out.print("\nEnter department:");
			department=s.next();
			insertion(usn,name,address,phone_no,semester,section,department);
			continue;
		case '2':
			display();
			continue;
		case '3':
			System.out.print("\nEnter usn to update name:");
			usn=s.next();
			System.out.print("\nEnter name:");
			name=s1.next();
			name= name.substring(0, name.length() - 1);
			update(usn,name);
			continue;
		case '4':
			System.out.print("\nEnter usn to delete record:");
			usn=s.next();
			delete(usn);
			continue;
		case '5':
			break;
		default:
			System.out.println("\nInvalid choice");	
			continue;
		}
		}
		s.close();
		s1.close();
	}
	
	public static void delete(String usn) throws Exception{
		try{
			Connection con = getConnection();
			PreparedStatement delete = con.prepareStatement("delete from student where usn='" + usn + "'");
			delete.executeUpdate();
		}catch(Exception e) {System.out.println(e);}
		finally {System.out.println("Function complete");}
	}
	
	public static void update(String usn,String name) throws Exception{
		try{
			Connection con = getConnection();
			PreparedStatement upd = con.prepareStatement("update student set name='" + name + "' where usn='" + usn + "'");
			upd.executeUpdate();
		}catch(Exception e) {System.out.println(e);}
		finally {System.out.println("update complete");}
	}
	
	public static ArrayList<String> display() throws Exception{
		try {
			Connection con = getConnection();
			PreparedStatement select = con.prepareStatement("select * from student");
			ResultSet res = select.executeQuery();
			ArrayList<String> array = new ArrayList<String>();
			System.out.println("| usn        | name      | address        | phone_no   | semester | section | department |");
			while(res.next())
			{
				System.out.println(" " + res.getString("usn") + "    " + res.getString("name") + "    " + res.getString("address") + "   " + res.getString("phone_no") + "    " + res.getString("semester") + "     " + res.getString("section") + " \t " + res.getString("department"));
				array.add(res.getString("usn"));
			}
			System.out.println("All data fetched.");
			return array;
		}catch(Exception e) {System.out.println(e);}
		return null;
	}
	
	public static void insertion(String usn,String name,String address,long phone_no,String semester,String section,String department) throws Exception{
		try{
			Connection con = getConnection();
			PreparedStatement insert = con.prepareStatement("insert into student values('"+ usn +"','" + name + "','"  + address +  "',"  + phone_no +  ",'"  + semester +  "','"  + section +  "','"  + department +  "')");
			insert.executeUpdate();
		}catch(Exception e) {System.out.println(e);}
		finally {System.out.println("insertion complete");}
	}
	
	public static void createTable() throws Exception{
		try{
			Connection con = getConnection();
			PreparedStatement create = con.prepareStatement("CREATE TABLE if not exists `student1` (  `usn` varchar(10) NOT NULL,  `name` varchar(15) DEFAULT NULL,  `address` varchar(20) DEFAULT NULL,  `phone_no` bigint DEFAULT NULL,  `semester` varchar(10) DEFAULT NULL,  `section` varchar(2) DEFAULT NULL,  `department` varchar(12) DEFAULT NULL,  PRIMARY KEY (`usn`))");
			create.executeUpdate();
		}catch(Exception e) {System.out.println(e);}
		finally {System.out.println("Function complete");}
	}
	
	public static Connection getConnection() throws Exception{
		  try{
		   String driver = "com.mysql.jdbc.Driver";
		   String url = "jdbc:mysql://192.168.81.129:3306/java";
		   String username = "java_demo";
		   String password = "java_demo";
		   Class.forName(driver);
		   Connection conn = DriverManager.getConnection(url,username,password);
		   System.out.println("Connected");
		   return conn;
		  } catch(Exception e){System.out.println(e);}
		  
		  
		  return null;
		 }
	

}
