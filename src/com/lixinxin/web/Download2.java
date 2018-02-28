package com.lixinxin.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.misc.BASE64Encoder;

public class Download2 extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String filename = request.getParameter("filename");
		filename = new String(filename.getBytes("iso8859-1"), "utf-8");
		String path = "/download/" + filename;
		response.setContentType(this.getServletContext().getMimeType(filename));
		String realPath = this.getServletContext().getRealPath(path);
		InputStream is = new FileInputStream(new File(realPath));
		String agent = request.getHeader("User-Agent");
		String encodeFile = "";
		if (agent.contains("MSIE")) {
			// IEä¯ÀÀÆ÷
			encodeFile = URLEncoder.encode(filename, "utf-8");
			encodeFile = encodeFile.replace("+", " ");
		} else if (agent.contains("Firefox")) {
			// »ðºüä¯ÀÀÆ÷
			BASE64Encoder base64Encoder = new BASE64Encoder();
			encodeFile = "=?utf-8?B?" + base64Encoder.encode(filename.getBytes("utf-8")) + "?=";
		} else {
			// ÆäËüä¯ÀÀÆ÷
			encodeFile = URLEncoder.encode(filename, "utf-8");
		}
		response.setHeader("Content-Disposition", "attachment;filename=" + encodeFile);
		ServletOutputStream os = response.getOutputStream();
		int len = 0;
		while ((len = is.read()) != -1) {
			os.write(len);
			os.flush();
		}
		is.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}