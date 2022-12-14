package com.kh.cook.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.cook.member.service.MemberService;
import com.kh.cook.member.vo.MemberVo;
@WebServlet(urlPatterns = "/member/find/modify/pwd")
public class MemberModifyPwdController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/member/find/success/pwd.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession s = req.getSession();
		String no = (String)s.getAttribute("no");
		String pwd = req.getParameter("memberPwd1");
		System.out.println(pwd);
		System.out.println(no);
		MemberVo vo = new MemberVo();
		vo.setPwd(pwd);
		vo.setNo(no);
		
		int result = new MemberService().modifyPwd(vo);
		
		if(result == 1) {
			s.setAttribute("alertMsg", "변경된 비밀번호로 로그인해주세요.");
			resp.sendRedirect("/cookTeacher/member/login");
		} else {
			req.getRequestDispatcher("/views/common/errorPage.jsp").forward(req, resp);
		}
		
	}
}
