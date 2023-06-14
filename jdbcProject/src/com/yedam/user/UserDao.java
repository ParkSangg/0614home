package com.yedam.user;
import java.sql.*;
import java.util.*;

import com.yedam.board.Dao;
public class UserDao {
	
	Connection conn;
	PreparedStatement psmt;
	ResultSet rs;
	String sql;
	
	private void close() {
		try {
			if(conn!=null) {
				conn.close();
			}
			if(psmt!=null) {
				psmt.close();
			}
			if(rs!=null) {
				rs.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public boolean login(String id, String pw) {
		sql = "select * from tbl_users where user_id=? and user_pw=?";
		conn = Dao.getConnect();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, pw);
			
			rs = psmt.executeQuery();
			if(rs.next()) {
				return true;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return false;
	}
	public UserVO remove(String userId) {
		sql = "delete from tbl_users"
				+ "   where  user_id = ?";
		conn = Dao.getConnect();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, userId);
			
			int r = psmt.executeUpdate();
			
			if(r > 0) {
				System.out.println("처리성공");
			}else {
				System.out.println("처리실패");
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return null;
	}
	
	//조회
	public UserVO search(String userId) {
		sql = "select * from tbl_users where user_id = ? ";
		conn = Dao.getConnect();
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, userId);
			rs = psmt.executeQuery();
			if(rs.next()) {//1건 조회
				UserVO user = new UserVO();
				user.setUserId(rs.getString("user_id"));
				user.setUserPw(rs.getString("user_pw"));
				user.setUserName(rs.getString("user_name"));
//				user.setUserBirth(rs.getString("user_birth"));
//				user.setUserPhone(rs.getString("user_phone"));
//				user.setUserAddr(rs.getString("user_addr"));
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return null;
	}
	//추가
	public boolean add(UserVO user) {
		sql = "insert into tbl_users (user_id, user_pw, user_name) "
				+ "values(?,?,?) ";
		conn = Dao.getConnect();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, user.getUserId());
			psmt.setString(2, user.getUserPw());
			psmt.setString(3, user.getUserName());
			
			int r = psmt.executeUpdate();
			if(r > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return false;
	}
	//목록
	public List<UserVO> list(){
		List<UserVO> list = new ArrayList<>();
		
		sql = "select * from tbl_users";
		conn = Dao.getConnect();
		
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				UserVO user = new UserVO();
				user.setUserId(rs.getString("user_id"));
				user.setUserPw(rs.getString("user_pw"));
				user.setUserName(rs.getString("user_name"));
				user.setUserBirth(rs.getString("user_birth"));
				user.setUserPhone(rs.getString("user_phone"));
				user.setUserAddr(rs.getString("user_addr"));
				
				list.add(user);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	//수정
	public boolean modify(UserVO user) {
		sql = "       update tbl_users"
				+"    set    user_pw = nvl(?,user_pw), "
				+"           user_name = nvl(?,user_name), "
				+"           user_birth = nvl(?,user_birth), "
				+"           user_phone = nvl(?,user_phone), "
				+"           user_addr = nvl(?,user_addr) "
				+"    where  user_id   = ?";
		conn = Dao.getConnect();
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, user.getUserPw());
			psmt.setString(2, user.getUserName());
			psmt.setString(3, user.getUserBirth());
			psmt.setString(4, user.getUserPhone());
			psmt.setString(5, user.getUserAddr());
			psmt.setString(6, user.getUserId());
			
			int r = psmt.executeUpdate();
			if (r > 0) {
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return false;
	}
}
