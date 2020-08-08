<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그아웃</title>
</head>
<body>
<% session.invalidate();
out.println("<script>alert('로그아웃을 완료하였습니다.'); location.href='BoardPaginationAction.do';</script>");
%>

</body>
</html>