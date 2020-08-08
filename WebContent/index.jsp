<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
System.out.println( "@" + JspFactory.getDefaultFactory().getEngineInfo().getSpecificationVersion());
System.out.println("@@" + application.getMajorVersion() + application.getMinorVersion());
System.out.println("@@@" + application.getServerInfo());
response.sendRedirect("BoardPaginationAction.do");
%>
</body>
</html>