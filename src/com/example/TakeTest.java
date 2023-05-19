package com.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class TakeTest {
	public static  void main(String[] args) throws SQLException {
		int repeat=0;
		DbOperations dbOperations = new DbOperations();	
		do {
		Scanner sc =new Scanner(System.in);
		System.out.println("Enter the first name:");
		String firstname=sc.next();
		System.out.println("Enter last name:");
		String lastname=sc.next();
		Student student=new Student(firstname, lastname);
		try {
			
			ArrayList<Question> al= dbOperations.readFromQuestionDb();//call a method to read the question from the question table
			Collections.shuffle(al);

			int i=1;
			int marks=0;
			for(Question q: al){
				System.out.println("Q" + i + ": "+q.question);
				System.out.println("Option 1: "+q.opt1);
				System.out.println("Option 2: "+q.opt2);
				System.out.println("Option 3: "+q.opt3);
				System.out.println("Option 4: "+q.opt4);
				System.out.println("Enter your answer: ");				
				int ans= sc.nextInt();
				while(ans <1 || ans>4) {
					System.out.println("Invalid option. Enter again");
					ans=sc.nextInt();
				}
				if(ans==(q.ans)) {
					marks++;	
				}
				i++;
			}
			//System.out.println("Marks: "+marks);
			String grade;
			if(marks<=10 && marks>=8) {
				grade="Class A";	
			}else if(marks<8 && marks>=6) {
				grade="Class B";	
			}else if(marks==5) {
				grade="Class C";
			}else if(marks<5 && marks>1){
				grade="Class D";
			}else{
				grade="Fail";
			}
			student.grade=grade;
			student.marks=marks;	
			dbOperations.writeToStudentDb(student);// call a method to store the student info in student table
		
		}catch (Exception e) {
		 e.getStackTrace();
		}
		
		System.out.println("Press 0 for exit \t\tPress 1 for repeat");
		repeat =sc.nextInt();
		
		}while(repeat!=0);
		
	}
}
	
