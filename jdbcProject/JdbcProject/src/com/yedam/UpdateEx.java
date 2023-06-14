package com.yedam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class UpdateEx {
	public static void main(String[] args) {

		Scanner scn = new Scanner(System.in);
		System.out.print("id> ");
		String id = scn.nextLine();
		System.out.print("pw> ");
		String pw = scn.nextLine();
		System.out.print("addr> ");
		String addr = scn.nextLine();

		Connection conn = null;
		PreparedStatement psmt = null;
		String sql = "update tbl_users"//
				+ "   set    user_pw = ?, "//
				+ "          user_addr = ? "//
				+ "   where user_id = ? ";
		try {
			conn = Dao.getConnect();

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, pw);
			psmt.setString(2, addr);
			psmt.setString(3, id);

			int r = psmt.executeUpdate(); // 처리된 건수를 반환.
			if (r > 0) {
				System.out.println("처리성공");
			} else {
				System.out.println("처리실패");
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				conn.close();
				psmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("end of prog.");
	}
}
