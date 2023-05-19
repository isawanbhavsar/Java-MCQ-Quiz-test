package com.example;

import java.util.ArrayList;

public class CreateTest {
	public static void main(String[] args) {
		try {
			DbOperations dbOperations = new DbOperations();	
			dbOperations.createQuestionTable();// call a method to create a question table
			dbOperations.createStudentTable();// call a method to create a student table
			ArrayList<Question> al= new ArrayList<Question>();
			
			al.add(new Question ("Who invented java Programming "," James Gosling"," Dennice Ritchie"," Guido Russum"," Bjarne Stroustrup", 1));
			al.add(new Question("which component is used to compile,debug and execute the java programs?","JRE","JIT","JDK"," JVM", 3));
			al.add(new Question("Which one of thses cannot be used for a variable name in java?"," identifier & keyword"," identifier"," keyword"," none",3 ));
			al.add(new Question("What is the extension of java code files?"," .js"," .txt"," .java"," .class",3));
			al.add(new Question("Which of these are selection statements in java?"," break "," Continue"," for()"," if()",4));
			al.add(new Question("Which of these keywords are used for the block to be examined for exceptions?","chech","throw","catch","try",4));
			al.add(new Question("Automatic type conversion is possible in which of the possible cases?","byte to int","Int to long","Long to int","Short to int",3));
			al.add(new Question("Select the valid statement","char[] ch=new char(5)","char[] ch=new char[5]","char[] ch=new char()","char[] ch=new char[]",2));
			al.add(new Question("When an array is passed to a method, what does the method receive?","The reference of the array","A copy of the array","Length of the array","Copy of the first element",1));
			al.add(new Question("When id the object created with new keyword?","At run time","At compile time","Depends on the code","None",1));
			dbOperations.writeToDb(al); // call a method to store the questions into question table
		}catch (Exception e) {
			e.getStackTrace();
		}
		
	}
}
