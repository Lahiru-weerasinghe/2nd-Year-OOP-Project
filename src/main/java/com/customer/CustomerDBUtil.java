package com.customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class CustomerDBUtil {
	
	public static List<Customer> validate(String userName, String password){
		
		ArrayList<Customer> cus = new ArrayList<>();
		
		//creat database connection
		String url = "jdbc:mysql://localhost:3306/hotel";
		String user = "root";
		String pass = "1234";
		
		//validate
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, user, pass);
			Statement stat = con.createStatement();
			String sql = "select * from customer where username='"+userName+"' and password='"+password+"'";
			ResultSet rs = stat.executeQuery(sql);
			
			//rs = stmt.executeQuery(sql);
			
			if (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String email = rs.getString(3);
				String phone = rs.getString(4);
				String userU = rs.getString(5);
				String passU = rs.getString(6);
				
				Customer c = new Customer(id,name,email,phone,userU,passU);
				cus.add(c);
				
				} 
			
		    }
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return cus;
		
	}
	
	
public static boolean insertcustomer(String name, String email, String phone, String username, String password) {
    	
    	boolean isSuccess = false;
    	
    	//creat database connection
    			String url = "jdbc:mysql://localhost:3306/hotel";
    			String user = "root";
    			String pass = "1234";
    	
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, user, pass);
			Statement stat = con.createStatement();
    	    String sql = "insert into customer values (0,'"+name+"','"+email+"','"+phone+"','"+username+"','"+password+"')";
    		int rs = stat.executeUpdate(sql);
    		
    		if(rs > 0) {
    			isSuccess = true;
    		} else {
    			isSuccess = false;
    		}
    		
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    	}
 	
    	return isSuccess;
    }

}
