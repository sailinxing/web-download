package com.lixinxin.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowMp4 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path="/download/a.mp4";
		String realPath = this.getServletContext().getRealPath(path);
		File file=new File(realPath);
		InputStream is=new FileInputStream(file);
		ServletOutputStream os= response.getOutputStream();
		int len=0;
		byte[] arr=new byte[1024];
		while((len=is.read(arr))!=-1){
			os.write(arr, 0, len);
		}
		is.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}