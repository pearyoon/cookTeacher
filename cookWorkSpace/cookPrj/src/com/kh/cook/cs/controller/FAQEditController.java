package com.kh.cook.cs.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.cook.cs.service.FAQService;
import com.kh.cook.cs.vo.CSVo;
import com.kh.cook.member.vo.MemberVo;

@WebServlet(urlPatterns="/cs/FAQ/edit")
public class FAQEditController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//화면 보여주기
		
		HttpSession s = req.getSession();
		MemberVo loginMember = (MemberVo)s.getAttribute("loginMember");
		boolean isAdmin = loginMember != null && loginMember.getId().equals("admin01");
		
		if(isAdmin) {
			//관리자일 때
			String Fno = req.getParameter("no");
			
			CSVo FAQvo = new FAQService().selectFAQOne(Fno);
			
			req.setAttribute("FAQvo", FAQvo);
			req.getRequestDispatcher("/views/cs/FAQ/edit.jsp").forward(req, resp);
		} else {
			//관리자 아닐 때
			req.setAttribute("msg","권한이 없습니다.");
			req.getRequestDispatcher("/views/common/errorPage.jsp").forward(req, resp);
		}
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String Fno = req.getParameter("no");
		
		CSVo FAQvo = new CSVo();
		
		FAQvo.setTitle(title);
		FAQvo.setContent(content);
		FAQvo.setQnaNo(Fno);
		
		int result = new FAQService().edit(FAQvo);
		
		if(result == 1) {
			resp.sendRedirect("/cookTeacher/cs/FAQ/detail?no="+ Fno);
		} else {
			req.getRequestDispatcher("/views/common/errorPage.jsp").forward(req, resp);
		}
	
	}

}
