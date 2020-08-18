package com.board.service;

import java.util.ArrayList;
import java.util.List;

public class BoardVO extends BoardDefaultVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int uid = 0;
	
	private String subject = "";
	
	private String content = "";
	
	private String name = "";
	
	private String password = "";
	
	private int viewcnt = 0;
	
	private String regdate = "";
	
	private List<FileVO> fileList = new ArrayList<FileVO>();
	
	public BoardVO() {
		super();
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getViewcnt() {
		return viewcnt;
	}

	public void setViewcnt(int viewcnt) {
		this.viewcnt = viewcnt;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	
	public List<FileVO> getFileList() {
		return fileList;
	}

	public void setFileList(List<FileVO> fileList) {
		this.fileList = fileList;
	}
}
