<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%
	request.setCharacterEncoding("UTF-8");
	response.reset();
	response.setContentType("application/octet-stream");
	java.io.OutputStream os = null;
	java.io.BufferedInputStream bis = null;
	String ROOTPATH = new java.io.File("src/main/resources/static/uploaded_files").getAbsolutePath();
    try {
		String filePath = request.getParameter("file_path");
		java.io.File file = new java.io.File(ROOTPATH, filePath);
		response.setHeader ("Content-Length", String.valueOf(file.length()));
		String fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
		bis = new java.io.BufferedInputStream(new java.io.FileInputStream(file));
		if(request.getHeader("User-Agent").contains("MSIE")){
			response.setHeader ("Content-Disposition", "attachment; filename=" + new String(fileName.getBytes("KSC5601"),"ISO8859_1"));
		}else{
			response.setHeader("Content-Disposition", "attachment; filename=\"" + new String(fileName.getBytes("UTF-8"),"ISO8859_1") + "\"");
		}
		response.setHeader("Content-Type", "application/octet-stream; charset=utf-8");
		os = response.getOutputStream();
		int i = -1;
		while((i = bis.read()) > -1){
			os.write(i);
		}
	} catch(Exception e) {
		response.setContentType("text/html; charset=UTF-8");
		System.out.println("e: " + e.getMessage());
		System.out.println("file_path: " + request.getParameter("file_path"));
	} finally {
		if (bis != null) bis.close();
	}
%>