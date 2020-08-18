package com.board.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.board.service.BoardVO;
import com.board.service.FileMngUtil;
import com.board.service.FileService;
import com.board.service.FileVO;

@Service("fileService")
public class FileServiceImpl implements FileService {

	@Resource(name="fileMapper")
	FileMapper fileDAO;
	
	@Resource(name="fileMngUtil")
	private FileMngUtil fileMngUtil;
	
	@Override
	public int insertFileDatas(List<FileVO> fileList) throws Exception { //insertfiledatas, 파일 업로드 
		int result = fileDAO.insertFileDatas(fileList); //DAO에서 가져왔음
		return result;											//객체를 반환해줘야하기 떄문에, return 하여 ,반환해준다. 
	}

	@Override
	public List<FileVO> selectFilesByBoardUid(BoardVO vo) throws Exception {
		List<FileVO> fileResultList = new ArrayList<FileVO>();  //List로 가져왔기에, ArraryList를 사용하여, 먼저 선언 해 준다음에, 
		fileResultList = fileDAO.selectFilesByBoardUid(vo); 	//selectFileByBoardUid를 사용하여,vo를 가져온다. 
		return fileResultList; 									//객체를 반환해줘야하기 때문에 ,return 타입을 정하여, 반환한다. 
	}

	@Override
	public void downloadFile(int f_uid, HttpServletResponse response) throws Exception {
		
		FileVO file = fileDAO.selectFileByUid(f_uid); //다운받는것이니, void를 하여, return 설정하지 않는다. 
		fileMngUtil.downloadFile(file, response);
	}

	@Override
	public void deleteFileByBoardUid(int uid) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteFileByUid(List<FileVO> fileList) throws Exception {
		
		fileMngUtil.deleteFiles(fileList);
		fileDAO.deleteFileByUid(fileList);
	}

}
