package com.board.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

public interface FileService {
	
	int insertFileDatas(List<FileVO> fileList) throws Exception;
	
	List<FileVO> selectFilesByBoardUid(BoardVO vo) throws Exception;
	
	void downloadFile(int f_uid, HttpServletResponse response) throws Exception;
	
	void deleteFileByBoardUid(int uid) throws Exception;
	
	void deleteFileByUid(List<FileVO> fileList) throws Exception;
}
