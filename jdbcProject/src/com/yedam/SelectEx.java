package com.yedam;

import java.sql.*;

public class SelectEx {
	public static void main(String[] args) {
		
		String url = "jdbc:oracle:thin:@localhost:1521/xe";
		String user = "proj";
		String pass = "proj";
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "proj", "proj");
			
		//db쿼리 실행 <> 결과
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select "
					+ "* from tbl_users");
			while(rs.next()) {
				System.out.println(rs.getString("user_id") + ","
						+ rs.getString("user_name") + "," 
						+ rs.getDate("user_birth") + ","
						+ rs.getString("user_addr"));
			}
			System.out.println("end of data.");
			conn.close();
			rs.close();
			stmt.close();
			
		}catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
		}
	}
}
