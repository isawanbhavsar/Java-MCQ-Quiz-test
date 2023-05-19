package com.example;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class ShowResult {
	public static void main(String[] args) throws SQLException {
		DbOperations dbOperations= new DbOperations();
		Scanner sc=new Scanner(System.in);
		int a;
		do {
			System.out.println("press 1 for all students info and press 2 for single student info");
			a = sc.nextInt();
			if(a==1){
				ArrayList<Student>al=dbOperations.readStudent();// call a method to create a questiontable
				for(Student student:al) {
					System.out.println("Student name:" + student.firstname + " " + student.lastname + " \tMarks:" + student.marks + " \tGrade:" + student.grade);		
				}
			}else {
				System.out.println("Enter Student's firstname:");
				String firstname=sc.next();
				Student student=dbOperations.readStudentResult(firstname);
				System.out.println("Student name:" + student.firstname + " " + student.lastname + " \tMarks:" + student.marks + " \tGrade:" + student.grade);		
			}
			System.out.println("press 1 to repeat and press 0 to exit");
			a = sc.nextInt();
		}while(a!=0);
		
		
				
	}

}
