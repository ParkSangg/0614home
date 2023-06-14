package com.yedam.board;

public class ExitException extends Exception {
	private String msg;

	public ExitException(String msg) {
		this.msg = msg;
	}

	public void getMsg() {
		System.out.println(msg);
	}
}
