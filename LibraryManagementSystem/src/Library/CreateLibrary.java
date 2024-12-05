package Library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateLibrary {
	 static String jdbcurl = "jdbc:mysql://localhost:3306/librarymanagementsystem";
	 static String username = "root";
	 static String password = "root";
	public static void main(String[] args) {
		try {
			 Connection connection = DriverManager.getConnection(jdbcurl,username,password);
			 Statement statement = connection.createStatement();
			 String createTable = "CREATE TABLE library(Usn varchar(20) primary key not null,Name varchar(40) not null,Branch varchar(10),BookSNo varchar(20)not null,Bookname varchar(30) not null,Authorname varchar(30),Issuedata varchar(10) not null,Returndate varchar(10) not null)";
			 statement.execute(createTable);
			 System.out.println("Table created successfully");
	}
	catch(SQLException e) {
		e.printStackTrace();
	}

	}

}
