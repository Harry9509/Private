package com.board.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.board.service.BoardDefaultVO;
import com.board.service.BoardService;
import com.board.service.BoardVO;

@Service("boardService")
public class BoardServiceImpl implements BoardService {
	
	@Resource(name="boardMapper")
	BoardMapper boardDAO;
	
	@Override
	public int insertBoard(BoardVO vo) throws Exception {
		boardDAO.insertBoard(vo);
		int result = vo.getUid();
		return result;
	}

	@Override
	public int updateBoard(BoardVO vo) throws Exception {
		boardDAO.updateBoard(vo);
		int result = vo.getUid();
		return result;
	}

	@Override
	public void deleteBoard(BoardVO vo) throws Exception {
		boardDAO.deleteBoard(vo);
	}

	@Override
	public BoardVO selectBoard(BoardVO vo) throws Exception {
		return boardDAO.selectBoard(vo);
	}

	@Override
	public List<?> selectBoardList(BoardDefaultVO searchVO) throws Exception {
		List<?> boardList = boardDAO.selectBoardList(searchVO);
		return boardList;
	}

	@Override
	public int selectBoardListTotCnt() {
		return boardDAO.selectBoardListTotCnt();
	}

}
