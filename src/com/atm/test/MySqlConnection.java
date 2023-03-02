package com.atm.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection {
	
	static Connection conn=null;
	public static Connection getConnection()
	{
		
		try
		{
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/atm_interface", "root", "root");			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}

		return conn;
	}
	}