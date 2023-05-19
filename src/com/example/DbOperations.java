package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;

public class DbOperations {
	void createQuestionTable() throws SQLException{
		Connection con=null;
		Statement stmt=null;
		try {
			String insertQuery= ("create table questiontable (qstring varchar(255),opt1 varchar(255),opt2 varchar(255),opt3 varchar(255),opt4 varchar(255),ans int)");
			// Step-1- Loading the driver class
			Class.forName("com.mysql.jdbc.Driver");
			// Step-2- Establish the connection
		    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "root");
			// Step-3- Create the statement
		    stmt = con.createStatement();
			// Step-4- Submit SQL statement to database
			stmt.execute(insertQuery);
			// Step-5- Process the results(Internally happen)
			System.out.println("Record is inserted successfully.");
						
		} catch (Exception e) {
			System.out.println(e);
			
		}finally {
			con.close();
			stmt.close();
		}
	}
	void createStudentTable() throws SQLException{
		Connection con=null;
		Statement stmt=null;
		try {
			String insertQuery= ("create table student (firstname varchar(255),lastname varchar(255),marks int,grade varchar(255))");
			// Step-1- Loading the driver class
			Class.forName("com.mysql.jdbc.Driver");
			// Step-2- Establish the connection-
		    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "root");
			// Step-3- Create the statement
		    stmt = con.createStatement();
			// Step-4- Submit SQL statement to database
			stmt.execute(insertQuery);
			// Step-5- Process the results(Internally happen)
			System.out.println("Record is inserted successfully.");
						
		} catch (Exception e) {
			System.out.println(e);
			
		}finally {
			con.close();
			stmt.close();
		}
	}
		
	ArrayList<Question> readFromQuestionDb() throws SQLException{
		ArrayList<Question> al=new ArrayList<Question>();
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			// Step-1- Loading the driver class
			Class.forName("com.mysql.jdbc.Driver");
			// Step-2- Establish the connection
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "root");
			// Step-3- Prepare SQL Statements
			ps= con.prepareStatement("select * from questiontable");
			rs=ps.executeQuery();
			while(rs.next()) {
				Question q=new Question();
				q.question = rs.getString("qstring");
                q.opt1 = rs.getString("opt1");
                q.opt2 = rs.getString("opt2");
				q.opt3 = rs.getString("opt3");
                q.opt4 = rs.getString("opt4");
				q.ans = rs.getInt("ans");
				al.add(q);
			}
		} catch (Exception e) {
			e.getStackTrace();
		}finally {
			con.close();
			ps.close();
			rs.close();
		}	
		return al;
	}
	
	ArrayList<Student> readStudent() throws SQLException{
		ArrayList<Student> al=new ArrayList<Student>();
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			// Step-1- Loading the driver class
			Class.forName("com.mysql.jdbc.Driver");
			// Step-2- Establish the connection
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "root");
			// Step-3- Prepare SQL Statements
			ps= con.prepareStatement("select * from student order by marks desc");
			rs=ps.executeQuery();
			while(rs.next()) {
				Student student=new Student();
				student.firstname = rs.getString("firstname");
                student.lastname = rs.getString("lastname");
                student.marks = rs.getInt("marks");
				student.grade= rs.getString("grade");            
				al.add(student);
			}
		} catch (Exception e) {
			e.getStackTrace();
		}finally {
			con.close();
			ps.close();
			rs.close();
		}	
		return al;
	}
		
	void writeToStudentDb(Student student) throws SQLException{
		Connection con=null;
		PreparedStatement ps=null;
		try {
			// Step-1- Loading the driver class
			Class.forName("com.mysql.jdbc.Driver");
			// Step-2- Establish the connection
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "root");
			// Step-3- Prepare SQL Statements
			ps= con.prepareStatement("insert into student(firstname,lastname,marks,grade)values(?,?,?,?)");
			ps.setString(1, student.firstname);
			ps.setString(2, student.lastname);
			ps.setInt(3, student.marks);
			ps.setString(4, student.grade);
			int a = ps.executeUpdate();
			System.out.println("Record is added>>>" + a);
		} catch (Exception e) {
			e.getStackTrace();
		}finally {
			con.close();
			ps.close();
		}	
	}
	
	void writeToDb(ArrayList<Question> al) throws SQLException {
		for(Question q: al) {
			Connection con=null;
			PreparedStatement ps=null;
			try {
				// Step-1- Loading the driver class
				Class.forName("com.mysql.jdbc.Driver");
				// Step-2- Establish the connection
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "root");
				// Step-3- Prepare SQL Statements
				ps= con.prepareStatement("insert into questiontable(qString,opt1,opt2,opt3,opt4,ans)values(?,?,?,?,?,?)");
				ps.setString(1, q.question);
				ps.setString(2, q.opt1);
				ps.setString(3, q.opt2);
				ps.setString(4, q.opt3);
				ps.setString(5, q.opt4);
				ps.setInt(6, q.ans);
				int a = ps.executeUpdate();
				System.out.println("Record is added>>>" + a);
			} catch (Exception e) {
				e.getStackTrace();
			}finally {
				con.close();
				ps.close();
			}
		}
		
	}

	Student readStudentResult(String firstname) throws SQLException{
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Student student=new Student();
		try {
			// Step-1- Loading the driver class
			Class.forName("com.mysql.jdbc.Driver");
			// Step-2- Establish the connection
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "root");
			// Step-3- Prepare SQL Statements
			ps= con.prepareStatement("select * from student where firstname='"+firstname+"'");
			rs=ps.executeQuery();
			while(rs.next()) {
				
				student.firstname = rs.getString("firstname");
                student.lastname = rs.getString("lastname");
                student.marks = rs.getInt("marks");
				student.grade= rs.getString("grade");            

			}
		} catch (Exception e) {
			e.getStackTrace();
		}finally {
			con.close();
			ps.close();
			rs.close();
		}	
		return student;
	} 
}
