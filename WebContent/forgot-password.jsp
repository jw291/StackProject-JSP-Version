<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jdbc.MySQLConnection" %>
<%@ page import="java.util.*" %>
<%@ page import="dataquery.*" %>
<%@ page import="javabean.*" %>
<%@ page import="java.sql.*" %>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="javax.mail.Transport"%>
<%@page import="javax.mail.Message"%>
<%@page import="javax.mail.Address"%>
<%@page import="javax.mail.internet.InternetAddress"%>
<%@page import="javax.mail.internet.MimeMessage"%>
<%@page import="javax.mail.Session"%>
<%@page import="javax.mail.Authenticator"%>
<%@page import="java.util.Properties"%>
<%@page import="checker.SMTPAuthenticatior" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  <title>비밀번호 찾기</title>
  <!-- Bootstrap core CSS-->
  <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <!-- Custom fonts for this template-->
  <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
  <!-- Custom styles for this template-->
  <link href="css/sb-admin.css" rel="stylesheet">
</head>

<body class="bg-dark">
  <div class="container">
    <div class="card card-login mx-auto mt-5">
      <div class="card-header">Forgot your password</div>
      <div class="card-body">
        <div class="text-center mt-4 mb-5">
        	<c:choose>
        		<c:when test="${msg eq 'success'}">
        			<h4> 이메일을 확인하세요</h4>
        		</c:when>
        		<c:when test="${msg eq 'failedID'}">
        			<h4> 존재하지 않는 아이디입니다</h4>
        		</c:when>
        		<c:when test="${msg eq 'failedQA'}">
        			<h4> 비밀번호 문답을 다시 확인해주세요</h4>
        		</c:when>
        		<c:when test="${empty msg}">
        			<h4> 비밀번호를 잊으셨습니까?</h4>
        		</c:when>
        	</c:choose>
        </div>
        <form  class="form-signin" method=POST action="SendMailAction.do">
	  <div class="form-group">
		  <label for="exampleInputQnA">password Q&A</label>
	    <select name="find_question" style="width:290px">
		<option value="나의 보물 1호는?">my...?</option>
		<option value="나의 출신 초등학교는?">나의 출신 초등학교는?</option>
		<option value="나의 출신 고향은?">나의 출신 고향은?</option>
		<option value="나의 이상형은?">나의 이상형은?</option>
		<option value="어머니 성함은?">어머니 성함은?</option>
		<option value="아버지 성함은?">아버지 성함은?</option>
		<option value="가장 좋아하는 색깔은?">가장 좋아하는 색깔은?</option>
	    </select>
	    <input class="form-control" type="text" name="find_answer" placeholder="Enter Answer">
	  </div>

          <div class="form-group">
		  <label for="enterId">Enter ID</label>
            <input class="form-control" name="user_id" type="text" placeholder="Enter id">
          </div>
          <button class="btn btn-primary btn-block" type="submit">Submit</button>
        </form>
        <div class="text-center">
          <a class="d-block small mt-3" href="./signup.jsp">Register an Account</a>
          <a class="d-block small" href="./login.jsp">Login Page</a>
	  <a class="d-block small" href="./BoardPaginationAction.do">home</a>
        </div>
      </div>
    </div>
  </div>
  <!-- Bootstrap core JavaScript-->
  <script src="vendor/jquery/jquery.min.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <!-- Core plugin JavaScript-->
  <script src="vendor/jquery-easing/jquery.easing.min.js"></script>
</body>
</html>