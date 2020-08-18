package com.board.service.impl;

import java.util.List;

import com.board.service.BoardDefaultVO;
import com.board.service.BoardVO;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("boardMapper")
public interface BoardMapper {
	int insertBoard(BoardVO vo) throws Exception;

	int updateBoard(BoardVO vo) throws Exception;

	void deleteBoard(BoardVO vo) throws Exception;

	BoardVO selectBoard(BoardVO vo) throws Exception;

	List<?> selectBoardList(BoardDefaultVO searchVO) throws Exception;

	int selectBoardListTotCnt();
}
