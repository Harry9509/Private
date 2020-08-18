package com.board.service;

public class FileVO {

	private int f_uid;
	
	private int br_uid;
	
	private String realPath;
	
	private String originalName;

	public FileVO() {
		super();
	}

	public int getF_uid() {
		return f_uid;
	}

	public void setF_uid(int f_uid) {
		this.f_uid = f_uid;
	}

	public int getBr_uid() {
		return br_uid;
	}

	public void setBr_uid(int br_uid) {
		this.br_uid = br_uid;
	}

	public String getRealPath() {
		return realPath;
	}

	public void setRealPath(String realPath) {
		this.realPath = realPath;
	}

	public String getOriginalName() {
		return originalName;
	}

	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}
	
	@Override
	public String toString() {
		return this.f_uid + " " + this.br_uid + " " + this.originalName + " "+ this.realPath;
	}
}
