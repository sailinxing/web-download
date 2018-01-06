package com.lxx.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String checkcode = request.getParameter("checkcode");
		String word = (String) this.getServletContext().getAttribute("words");
		if(checkcode!=null&&!checkcode.equals("")){
			if(checkcode.equals(word)){
				response.getWriter().write("登陆成功！");
			}else{
				response.getWriter().write("验证码输入错误！");
			}
		}else{
			response.getWriter().write("请输入验证码！");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}