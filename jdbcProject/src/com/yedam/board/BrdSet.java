package com.yedam.board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 게시판번호 제목 작성자 내용 날짜 클릭횟수
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrdSet {
	private int brdNo;
	private String title;
	private String writer;
	private String content;
	private String brdDate;
	private int click;
}
