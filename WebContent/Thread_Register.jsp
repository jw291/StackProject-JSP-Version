<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  <title>스레드 가입신청서</title>
  <!-- Bootstrap core CSS-->
  <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <!-- Custom fonts for this template-->
  <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
  <!-- Custom styles for this template-->
  <link href="css/sb-admin.css" rel="stylesheet">
  <!-- 회원가입 중복체크 폼 js-->
<script type="text/javascript" src="http://code.jquery.com/jquery-2.1.0.min.js" ></script>
<script type="text/javascript" src="../signupjs/registerchecker.js"></script>
<link rel="stylesheet" href="../css2/mySignupForm.css" />
</head>
<!---->
<%
String threadname = request.getParameter("threadname");
String user_id = (String)session.getAttribute("user_id");
%>
<body class="bg-dark">
  <div class="container">
    <div class="card card-login mx-auto mt-5">
      <div class="card-header"><%=threadname%> 가입신청서</div>
      <div class="card-body">
        <form class="form-sign" action="Thread_Register_Update.jsp" method=POST onsubmit="return checkSubmit()">
        <input type="hidden" name="mode" value="register">
        <input type="hidden" name="threadname" value=<%=threadname%>>
          <div class="form-group">
              <label for="exampleInputName">자기소개</label>
              <input class="memberIntroduce form-control" name="user_introduce" type="test" aria-describedby="introHelp" placeholder="간단히 작성해주세요.">
          </div>

          <div class="form-group">
              <label for="exampleInputName">지원 동기</label>
              <input class="memberMotive form-control" name="user_motive" type="text" aria-describedby="nameHelp" placeholder="간단히 작성해주세요.">
          </div>

        <div class="form-group">
            <label for="exampleInputName">학과 입력</label>
            <input class="memberMajor form-control" name="user_major" type="text" aria-describedby="majorHelp" placeholder="학과를 입력하시오.">
        </div>

        <div class="form-group">
            <label for="exampleInputName">학번 입력</label>
            <input class="memberIdNumber form-control" name="user_idnumber" type="number" aria-describedby="idNumberHelp" placeholder="32160000">
        </div>
        <div class="form-group">
            <label for="exampleInputName">이름</label>
            <input class="memberName form-control" name="user_name" type="text" aria-describedby="nameHelp" placeholder="<%= user_id %>" readonly/>
	      </div>

	       <div class="form-group">

          <button class="btn btn-lg btn-primary btn-block submit" type="submit">Submit</button>
        </form>

        <div class="text-center">
          <br>
	        <a class="d-block small" href="BoardPaginationAction.do">메인페이지로</a>
          <a class="d-block small" href="AdBoardPaginationAction.do">목록으로</a>
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