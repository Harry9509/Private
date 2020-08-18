package com.board.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public interface BoardService {

	int insertBoard(BoardVO vo) throws Exception;

	int updateBoard(BoardVO vo) throws Exception;

	void deleteBoard(BoardVO vo) throws Exception;

	BoardVO selectBoard(BoardVO vo) throws Exception;

	List<?> selectBoardList(BoardDefaultVO searchVO) throws Exception;

	int selectBoardListTotCnt();
}
