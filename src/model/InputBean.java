package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;

import utility.SQLCommand;

public class InputBean implements SQLCommand{
	private String secretCode;
	private String logicCode;

	public String getSecretCode() {
		return secretCode;
	}

	public void setSecretCode(String secretCode) {
		this.secretCode = secretCode;
	}
	
	public String getLogicCode() {
		return logicCode;
	}

	public void setLogicCode(String logicCode) {
		this.logicCode = logicCode;
	}

	
	//SQL Operations
	public Connection getConnection(String jdbcUrl,
			String dbUserName, String dbPassword){
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			connection = DriverManager
					.getConnection(jdbcUrl, dbUserName, dbPassword);
	}catch(ClassNotFoundException cnfe){
		System.err.println("Class not found: "+cnfe.getMessage());
	}catch(SQLException sqle){
		System.err.println("SQL Exception "+ sqle.getMessage());
	}
		return connection;
	}
	//Insert Records
	public void insertRecord(Connection connection){
		//Connection connection = getConnection();
		 if(connection !=null){
			 try{
				
				 
				 PreparedStatement pstmnt = connection.prepareStatement(INSERT_REC);
				 pstmnt.setString(1, this.secretCode);
				 
				 pstmnt.executeUpdate();
			 }catch(SQLException sqle){
				 throw new RuntimeException();
			 }
		 }
	}
	//View Records
	public ResultSet getAllRecords(Connection connection){
		ResultSet records = null;
		//Connection connection = getConnection();
		 if(connection !=null){
			 try{
				
				 
				 PreparedStatement pstmnt = connection.prepareStatement(GET_ALL_REC);
				 
				 
				records = pstmnt.executeQuery();
			 }catch(SQLException sqle){
				 throw new RuntimeException();
			 }
		 }
		 return records;
	}
	
	
	public void DropTable(Connection connection){
		if(connection !=null){
			try{
				PreparedStatement pstmnt = connection.prepareStatement(DROP_TABLE);
				pstmnt.executeUpdate();
				System.out.println("BYE!");
			}catch(SQLException sqle){
				throw new RuntimeException();
			}
		}
	}
	

}
