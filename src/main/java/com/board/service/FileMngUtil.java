package com.board.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component("fileMngUtil")
public class FileMngUtil {
	private static final String SAVE_PATH = "W:\\eGovFrameDev-3.9.0-64bit\\workspace\\picture";
	
	public List<FileVO> uploadFiles(Map<String, MultipartFile> files) {
		
		List<FileVO> fileList = new ArrayList<FileVO>();
		FileVO fileVO;
		MultipartFile file;
		
		Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
		//String input, name 인데 몇개가 나오는지 알 수없기에, iterator을 사용하여, 하나씩 다 빼준다. 
		while(itr.hasNext()) {
			Entry<String, MultipartFile> entry = itr.next();
			
			file = entry.getValue();
			
			fileVO = uploadFile(file);
			
			if (fileVO.getOriginalName() != null) {
				fileList.add(fileVO);
			}
			
		}
		
		// test cord
		for (int i = 0; i < fileList.size(); i++) {
			System.out.println("====" + fileList.get(i).getOriginalName() + " : " + fileList.get(i).getRealPath() + "====");
		}
		//System.out.println(fileList.size());
		return fileList;
	}
	
	public FileVO uploadFile(MultipartFile file) {
		FileVO fileVO = new FileVO();
		UUID saveName = UUID.randomUUID();
		String originalName = file.getOriginalFilename();
		if (originalName.length() != 0 && originalName != null) {
			String ext = originalName.substring(originalName.lastIndexOf("."), originalName.length());
			String dataName = saveName.toString().substring(0, 10) + ext;
			String realPath = SAVE_PATH + File.separator + dataName;
			
			fileVO.setOriginalName(originalName);
			fileVO.setRealPath(dataName);
			
			try {
				byte[] data = file.getBytes();
				FileOutputStream fos = new FileOutputStream(realPath);
				fos.write(data);
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return fileVO;
	}
	
	public void deleteFiles(List<FileVO> fileList) {
		for (FileVO f : fileList) {
			deleteFile(f);
		}
	}
	
	public void deleteFile(FileVO fileVO) {
		String saveFilePath = SAVE_PATH + File.separator + fileVO.getRealPath(); //file.separator == /이다. 결국, 세이브 장소/파일의 실제 경로.
		File file = new File(saveFilePath);
		
		if (file.exists()) {
			if (file.delete()) {
				System.out.println("== delete '" + saveFilePath + "' ==");
			} else {
				System.out.println("== delete fail '" + saveFilePath + "' ==");
			}
		} else {
			System.out.println("== there is no file '" + saveFilePath + "' ==");
		}
	}
	//삭제하면 끝이기에 void 로 설정
	
	public void downloadFile(FileVO file, HttpServletResponse response) {
		String dataName = file.getRealPath(); 
		String saveFilePath = SAVE_PATH + File.separator + dataName;
		String ext = dataName.substring(dataName.lastIndexOf("."), dataName.length());
		
		File tmpFile = new File(saveFilePath);
		long fileLength = tmpFile.length();
		
		//header setting
		response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getOriginalName() + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setHeader("Content-Type", ext);
		response.setHeader("Content-Length", "" + fileLength);
		response.setHeader("Pragma", "co-cache;");
		response.setHeader("Expires", "-1;");
		
		try (FileInputStream fis = new FileInputStream(saveFilePath);
				OutputStream out = response.getOutputStream();
				) {
			int readCount = 0;
			byte[] buffer = new byte[1024];
			
			while ((readCount = fis.read(buffer)) != -1) {
				out.write(buffer, 0, readCount);
			}
			
			fis.close();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
