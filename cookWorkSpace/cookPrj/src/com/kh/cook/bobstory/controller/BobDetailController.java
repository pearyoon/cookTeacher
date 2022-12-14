package com.kh.cook.bobstory.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.cook.bobstory.service.BobCmtService;
import com.kh.cook.bobstory.service.BobstoryService;
import com.kh.cook.bobstory.vo.AttachmentVo;
import com.kh.cook.bobstory.vo.BobCmtVo;
import com.kh.cook.bobstory.vo.BobstoryVo;

@WebServlet(urlPatterns = "/bobstory/detail")
public class BobDetailController extends HttpServlet{

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//데이터 꺼내기
		String bno = req.getParameter("bno");
		//데이터 뭉치기
		
		//디비 다녀오기
		BobstoryVo vo = new BobstoryService().selectOne(bno);
		AttachmentVo attachmentVo = new BobstoryService().selectAttachment(bno);
		List<BobCmtVo> cvo = new BobCmtService().selectBobCmt(bno);

		//cmtvo 를 가져와서 cvo.postno랑 일치 시켜야함
		
		//화면 선택
		req.setAttribute("cvo", cvo);
		req.setAttribute("vo", vo);
		req.setAttribute("attachmentVo", attachmentVo);
		req.getRequestDispatcher("/views/bobstory/detail.jsp").forward(req, resp);
		
	}

	
}
