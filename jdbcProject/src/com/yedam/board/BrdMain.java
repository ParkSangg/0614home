package com.yedam.board;

import java.util.List;
import java.util.Scanner;

public class BrdMain {
	public static void main(String[] args) {
		BrdFunc brd = new BrdFunc();
		Scanner scn = new Scanner(System.in);
		int menu = 0;
		
		while(true) {
			System.out.println("1.추가 2.삭제 3.수정 4.목록 5.종료");
			System.out.println("선택 >");
			menu = scn.nextInt();
			scn.nextLine();
			if(menu == 1) {
				System.out.println("제목 >");
				String title = scn.nextLine();
				System.out.println("작성자 >");
				String writer = scn.nextLine();
				System.out.println("내용 >");
				String content = scn.nextLine();
				
				BrdSet board = new BrdSet();
				board.setTitle(title);
				board.setWriter(writer);
				board.setContent(content);
				
				if(brd.add(board)){
					System.out.println("처리성공.");
				}else{
					System.out.println("처리 실패.");
				}
			}else if(menu == 2) {
				System.out.println("삭제화면입니다.");
				System.out.println("삭제할 번호 >");
				String num = scn.nextLine();
				BrdSet board = brd.remove(num);
			}else if(menu == 3) {
				System.out.println("수정할 게시글의 번호?");
				int num = Integer.parseInt(scn.nextLine());
				System.out.println("제목 >");
				String title = scn.nextLine();
				System.out.println("작성자 >");
				String writer = scn.nextLine();
				System.out.println("내용 >");
				String content = scn.nextLine();
				
				BrdSet board = new BrdSet();
				board.setBrdNo(num);
				board.setTitle(title);
				board.setWriter(writer);
				board.setContent(content);
				if(brd.modify(board)) {
					System.out.println("정상적으로 수정이 되었습니다");
				}else {
					System.out.println("수정에 오류가 발생하였습니다.");
				}
			}else if(menu == 4) {
				List<BrdSet> list = brd.list();
				
				if(list.size()==0) {
					System.out.println("조회 결과가 없습니다.");
				}else {
					for(BrdSet board : list){
						System.out.println(board.toString());
					}
				}
			}else if(menu == 5) {
				break;
			}
		}
		System.out.println("end of prog.");
	}
}
