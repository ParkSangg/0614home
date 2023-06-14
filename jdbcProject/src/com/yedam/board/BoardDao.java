package com.yedam.board;
import java.sql.*;
import java.util.*;

public class BoardDao {
	
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
	public boolean add(BrdSet board) {
		sql = "insert into tbl_board (brd_no, brd_title, brd_writer, brd_content) "
				+ "values (board_seq.nextval ,? ,? ,? )";
		conn = Dao.getConnect();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, board.getTitle());
			psmt.setString(2, board.getWriter());
			psmt.setString(3, board.getContent());
			
			int r = psmt.executeUpdate();
			if(r > 0) {
				return true;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			close();
		}
		return false;
	}
	public BrdSet remove(String brdNo) {
		sql = "delete from tbl_board"
				+ "   where  brd_No = ?";
		conn = Dao.getConnect();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, brdNo);
			
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
	public boolean modify(BrdSet board) {
		sql = "       update tbl_board"
				+"    set    brd_no                       "
				+"           brd_title = nvl(?,brd_title), "
				+"           brd_writer = nvl(?,brd_writer), "
				+"           brd_content = nvl(?,brd_content), "
				+"    where  brd_no   = ?";
		conn = Dao.getConnect();
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, board.getTitle());
			psmt.setString(2, board.getWriter());
			psmt.setString(3, board.getContent());
			psmt.setInt(4, board.getBrdNo());
			
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
	public List<BrdSet> list(){
		List<BrdSet> list = new ArrayList<>();
		
		sql = "select * from tbl_board";
		conn = Dao.getConnect();
		
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				BrdSet board = new BrdSet();
				board.setBrdNo(rs.getInt("brd_no"));
				board.setTitle(rs.getString("brd_title"));
				board.setWriter(rs.getString("brd_writer"));
				board.setContent(rs.getString("brd_content"));
				
				list.add(board);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	//수정
	
}
