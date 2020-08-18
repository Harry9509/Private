package com.board.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.board.service.FileService;

@Controller
public class FileController {
	/*FileService*/
	@Resource(name="fileService")
	private FileService fileService;
	
	@RequestMapping(value="/file/download.do")
	public ModelAndView downloadFile(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		int br_uid = Integer.parseInt(request.getParameter("br_uid"));
		int f_uid = Integer.parseInt(request.getParameter("f_uid"));
		fileService.downloadFile(f_uid, response);
		
		System.out.println("====File Download Success====");
		
		ModelAndView mv = new ModelAndView("/view.do?uid=" + br_uid);
		
		return mv;
	}
}
