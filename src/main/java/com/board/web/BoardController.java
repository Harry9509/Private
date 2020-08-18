package com.board.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.board.service.BoardDefaultVO;
import com.board.service.BoardService;
import com.board.service.BoardVO;
import com.board.service.FileMngUtil;
import com.board.service.FileService;
import com.board.service.FileVO;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class BoardController {

	/*BoardService*/
	@Resource(name="boardService")
	private BoardService boardService;
	/*FileService*/
	@Resource(name="fileService")
	private FileService fileService;
	
	@Resource(name="fileMngUtil")
	private FileMngUtil fileMngUtil;
	
	@RequestMapping(value="/board.do")
	public String selectBoardList(@ModelAttribute("searchVO") BoardDefaultVO searchVO, ModelMap model) throws Exception {
		model.addAttribute("totCnt", boardService.selectBoardListTotCnt());
		
		searchVO.setPageUnit(10); //페이지 유닛 10개로 세팅
		searchVO.setPageSize(10); //페이지 사이즈 10개로 세팅
		//System.out.println(searchVO.getPageIndex());
		
		/** pageing setting */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex()); //현재 페이지 넘버
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit()); // 페이지당
		paginationInfo.setPageSize(searchVO.getPageSize()); // 페이지 리스트의 사이즈
		
		int totCnt = boardService.selectBoardListTotCnt(); //토탈 카운트 
		paginationInfo.setTotalRecordCount(totCnt);
		
		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		model.addAttribute("paginationInfo", paginationInfo);
		
		List<?> boardList = boardService.selectBoardList(searchVO);
		model.addAttribute("resultList", boardList);
		return "board";
	}
	
	@RequestMapping(value="/addBoardView.do", method=RequestMethod.POST)
	public String addBoard(ModelMap model) throws Exception {
		
		model.addAttribute("boardVO", new BoardVO());
		model.addAttribute("type", "create");
		return "boardRegister";
	}
	
	@RequestMapping(value="/addBoard.do", method=RequestMethod.POST)
	public String addBoardDo(MultipartHttpServletRequest multiRequest,
			Model model) throws Exception {
		
		BoardVO boardVO = new BoardVO();									// board
		boardVO.setSubject(multiRequest.getParameter("subject"));			// multiparthttpservletRequest boardVO 안에 넣어줄, 내용을 세팅
		boardVO.setContent(multiRequest.getParameter("content"));			// multiparthttpservletRequest boardVO 안에 넣어줄, 내용을 세팅 
		boardVO.setName(multiRequest.getParameter("name"));					// multiparthttpservletRequest boardVO 안에 넣어줄, 내용을 세팅
		boardVO.setPassword(multiRequest.getParameter("password"));			// multiparthttpservletRequest boardVO 안에 넣어줄, 내용을 세팅
		
		int uid = boardService.insertBoard(boardVO);						//uid 라는 이름 아래에, multipartHttpServletRequest를 담아둔다.
		
		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		// map에, String 이라는 key값과(input의 name값이 들어간다.), multipartHttpServletRequest을 MAP화 시킨다.>

		List<FileVO> fileList = fileMngUtil.uploadFiles(files);		//리스트화 시킨, uploadFiles 라는 메소드에, getFileMap를 통해, 파일정보를 값으로하는 MAP를, fileList안에 담는다. 
		if (fileList.size() > 0) {	//List는 사이즈로 들어가있는 객체수를 파악 할 수 있다. 
			for (FileVO vo : fileList) {	 //FileVO vo : fileList를 분해한다.
				vo.setBr_uid(uid);			 //vo.setuid를 통하여, insertBoard의 내용을 담는다. 
			}
			fileService.insertFileDatas(fileList); //if 문에 맞추어서, file을 insertFiledata메서드를 통해, 삽입해준다. 
		}
		
		return "redirect:/board.do";
	}
	
	@RequestMapping(value="/view.do")
	public String viewBoard(@RequestParam("uid") String uid, Model model) throws Exception {
		
		//System.out.println(uid);
		
		BoardVO board = new BoardVO();
		// error
		board.setUid(Integer.parseInt(uid)); //uid를 설정
		board = boardService.selectBoard(board); 	//selectboard 메서드를 가져온다.
		board.setFileList(fileService.selectFilesByBoardUid(board)); //boarduid를 통하여, file을 가져온다. 
		
		// test cord
		List<FileVO> list = board.getFileList(); //list fileVO 를 리스트화 시켜서 가져옵니다.  getfilelist를 가져온다. 
		if(list.size() > 0) {
			System.out.println("=== Get File DATA : " + list.get(0).getOriginalName() + " // " + list.get(0).getRealPath() + "====");
		}
		
		model.addAttribute("boardVO", board);
		model.addAttribute("type", "view");
		return "boardRegister";
	}
	
	@RequestMapping(value="/updateView.do", method=RequestMethod.POST)
	public String updateBoard(@RequestParam("uid") String uid, Model model) throws Exception {
		BoardVO board = new BoardVO();
		board.setUid(Integer.parseInt(uid)); //위의 view단과 같습니다. 
		
		//System.out.println(uid);
		
		board = boardService.selectBoard(board);
		board.setFileList(fileService.selectFilesByBoardUid(board));
				
		// test cord
		List<FileVO> list = board.getFileList();
		if(list.size() > 0) {
			System.out.println("=== Get File DATA : " + list.get(0).getOriginalName() + " // " + list.get(0).getRealPath() + "====");
		}
		
		model.addAttribute("boardVO", board);
		model.addAttribute("type", "modify");
		return "boardRegister";		
	}
	
	@RequestMapping(value="/updateBoard.do", method=RequestMethod.POST)
	public String updateBoardDo(MultipartHttpServletRequest multiRequest, ModelMap model) throws Exception {
		
		BoardVO boardVO = new BoardVO();										//multirequest 를 사용하깅 위한 준비
		boardVO.setUid(Integer.parseInt(multiRequest.getParameter("uid")));		
		boardVO.setContent(multiRequest.getParameter("content")); //
		boardVO.setSubject(multiRequest.getParameter("subject"));
		//기존의 @modelattrubte는 여러가지의 데이터를 입력할 수 없기에, multipartHttpServletRequest를 사용하여, 다양한 데이터를 입력시킬 수있게끔, 작성해준다.  
		
		// get modified f_uid list and select f_uids for delete 
		String[] fuids = multiRequest.getParameterValues("fileUids"); //String f_uid를 통하여, 수정할 파일 1,2,3을 정하여, 배열로 세팅해 둔다. 
		List<FileVO> fList = fileService.selectFilesByBoardUid(boardVO);
		
		//List fList를 통하여, 1,2,3 의 파일 중, (3번 파일을 삭제한다는 가정하에) 1,2의 파일은 fuids에서 빼둔뒤, fList.get을 따로, 빼준다.
		for (int i = 0; i < fuids.length; i++) {
			int uid = Integer.parseInt(fuids[i]); //uid 중, fuids[i]를 사용하여, 수정할 파일 index인, 1,2을 선택해준다. 
			
			for (int j = 0; j < fList.size(); j++) {
				if (uid == fList.get(j).getF_uid()) {//uid 중, fList.get(j).getF_uid() 
					fList.remove(j);
				}
			}
		}
	
		//test cord
		System.out.println(boardVO.getUid() + " : " + boardVO.getContent() + " : " + boardVO.getSubject());
		for (String s : fuids) {
			System.out.print(s + " ");
		}
		System.out.println();
		for (FileVO f : fList) {
			System.out.println(f.toString());
		}
		
		int uid = boardService.updateBoard(boardVO);
		
		// 새로들어온 파일 insert
		final Map<String, MultipartFile> files = multiRequest.getFileMap(); //map 형식으로 file insert 할 세팅
		List<FileVO> fileList = fileMngUtil.uploadFiles(files); //fileList를 통해서 fileMngUtil.uplioadFiles를 사용하여, 리스트화 시킨다. 
		if (fileList.size() > 0) {
			for (FileVO vo : fileList) { //addBoard 메서드와 같다.
				vo.setBr_uid(uid);	
			}
			fileService.insertFileDatas(fileList);	
		}
		// 삭제한 파일 제거
		if (fList.size() > 0) fileService.deleteFileByUid(fList);  //deleteFileByUid를 통하여, fList를 
		
		model.addAttribute("uid", String.valueOf(uid)); //
		
		return "redirect:/view.do";
	}
	
	@RequestMapping(value="/deleteBoard.do", method=RequestMethod.POST)
	public String deleteBoard(@RequestParam("uid") String uid, Model model) throws Exception {
		
		BoardVO board = new BoardVO();
		board.setUid(Integer.parseInt(uid));
		
		boardService.deleteBoard(board);
		
		return "redirect:/board.do";
	}
}
