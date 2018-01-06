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


public class Download extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String filename = request.getParameter("filename");
		String flname=new String(filename.getBytes("iso8859-1"),"utf-8");
		String path="/download/"+flname;
		String realPath = this.getServletContext().getRealPath(path);
		response.setContentType(getServletContext().getMimeType(flname));
		String agent = request.getHeader("User-Agent");
		String ecFilename="";
		if (agent.contains("MSIE")) {
			// IEä¯ÀÀÆ÷
			ecFilename = URLEncoder.encode(flname, "utf-8");
			ecFilename = ecFilename.replace("+", " ");
	} else if (agent.contains("Firefox")) {
			// »ðºüä¯ÀÀÆ÷
	BASE64Encoder base64Encoder = new BASE64Encoder();
			ecFilename = "=?utf-8?B?"
					+ base64Encoder.encode(flname.getBytes("utf-8")) + "?=";
	} else {
			// ÆäËüä¯ÀÀÆ÷
		 ecFilename= URLEncoder.encode(flname, "utf-8");				
	}
		
		response.setHeader("Content-Disposition","attachment;filename="+ecFilename);
		InputStream is=new FileInputStream(new File(realPath));
		ServletOutputStream os = response.getOutputStream();
		int len=0;
		while((len=is.read())!=-1){
			os.write(len);
		}
		is.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}