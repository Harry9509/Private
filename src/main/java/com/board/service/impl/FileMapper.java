package com.board.service.impl;

import java.util.List;

import com.board.service.BoardVO;
import com.board.service.FileVO;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("fileMapper")
public interface FileMapper {
	
	int insertFileDatas(List<FileVO> fileList) throws Exception;
	
	List<FileVO> selectFilesByBoardUid(BoardVO vo) throws Exception;
	
	FileVO selectFileByUid(int uid) throws Exception;
	
	void deleteFileByBoardUid(int uid) throws Exception;
	
	void deleteFileByUid(List<FileVO> fileList) throws Exception;
}
