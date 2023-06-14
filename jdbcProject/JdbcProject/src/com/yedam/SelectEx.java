package com.yedam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectEx {
	public static void main(String[] args) {

		try {
			Connection conn = Dao.getConnect();

			// db 쿼리실행 <-> 결과.
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from tbl_users");
			while (rs.next()) {
				System.out.println(rs.getString("user_id") + ","//
						+ rs.getString("user_name") + "," //
						+ rs.getDate("user_birth") + "," //
						+ rs.getString("user_addr"));
			}
			System.out.println("end of data.");
			conn.close();
			rs.close();
			stmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}
}
