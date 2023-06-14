package com.yedam.user;
import java.util.*;

import com.yedam.board.BoardDao;
public class BoardProc {
	private static String menuErrmsg = "잘못된 값을 입력했습니다.";
	private BoardDao dao = BoardDao.getinstance();
	private UserDao uDao = new UserDao();
	private BoardDao brd;
	private Scanner scn = new Scanner(System.in);
	
	public void loginCheck() {
		while(true) {
			String id = promptString("아이디를 입력하세요");
			String pw = promptString("비밀번호를 입력하세요");
			if(uDao.login(id, pw)) {
				return;
			}
			System.out.println("입력정로를 확인하세요");
		}
	}
	public void addBoard() {
		String title = promptString("제목");
	}
}
