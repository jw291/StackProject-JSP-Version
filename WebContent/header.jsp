<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jdbc.MySQLConnection" %>
<%@ page import="java.util.*" %>
<%@ page import="dataquery.*" %>
<%@ page import="javabean.*" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>header</title>
</head>
<body>
<%
request.setCharacterEncoding("UTF-8");
String threadname = (String)session.getAttribute("user_threadname");
%>
<body class="fixed-nav sticky-footer bg-dark" id="page-top">
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top"  id="mainNav">
    <a class="navbar-brand" href="BoardPaginationAction.do" onclick="BoardPaginationAction.do"><img src="logo.png"></a>
    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarResponsive">
      <ul class="navbar-nav navbar-sidenav" id="exampleAccordion">
				<li class="nav-item" data-toggle="tooltip" data-placement="right" title="Components">
				<li class="nav-item" data-toggle="tooltip" data-placement="right" title="Board">
					<a class="nav-link nav-link-collapse collapsed" data-toggle="collapse" href="#collapseboard" data-parent="#exampleAccordion">
						<i class="fa fa-fw fa-file"></i>
	 				<span class="nav-link-text">게시판</span>
 				</a>
 				<ul class="sidenav-second-level collapse" id="collapseboard">
					<li>
	 					<a href="./AdBoardPaginationAction.do">홍보 게시판</a>
					</li>
					<li>
	 					<a href="./FreeBoardPaginationAction.do">자유 게시판</a>
                    </li>
 			</ul>
		</li>
				<li class="nav-item" data-toggle="tooltip" data-placement="right" title="Components">
					<a class="nav-link nav-link-collapse collapsed" data-toggle="collapse" href="#collapseComponents" data-parent="#exampleAccordion">
						<i class="fa fa-fw fa-file"></i>
						  <span class="nav-link-text">마이페이지</span>
					</a>
					<ul class="sidenav-second-level collapse" id="collapseComponents">
					<% if(threadname==null ||threadname.equals("")){%>
						<li>
							<a href="javascript:ThcallFunction()">나의 스레드</a>
						</li>
					<%}else {%>
						<li>
							<a href="./ThBoardPaginationAction.do?threadname=<%=threadname%>">나의 스레드</a>
						</li>
						<%} %>
						<li>
							<a href="./Thread_Construct.jsp">스레드 개설하기</a>
						</li>
					</ul>
			</li>
		        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Menu Levels">
		          <a class="nav-link nav-link-collapse collapsed" data-toggle="collapse" href="#collapseMulti" data-parent="#exampleAccordion">
		            <i class="fa fa-fw fa-file"></i>
		            <span class="nav-link-text">회원 관리</span>
		          </a>
		          <ul class="sidenav-second-level collapse" id="collapseMulti">
		            <li>
		                  <a href="signup.jsp">회원가입</a>
		                </li>
		                <li>
		                  <a href="forgot-password.jsp">비밀번호 찾기</a>
		                </li>
		          </ul>
		        </li>
		      </ul>
		      <ul class="navbar-nav sidenav-toggler">
		        <li class="nav-item">
		          <a class="nav-link text-center" id="sidenavToggler">
		            <i class="fa fa-fw fa-angle-left"></i>
		          </a>
		        </li>
		      </ul>
		      <ul class="navbar-nav ml-auto">
		      <%
		          	String id = "";
					
					id = (String)session.getAttribute("user_id");
					if(id == null || id.equals("")){
		          %>
		          <li class="nav-item">
		            <a class="nav-link" href="login.jsp">
			     <i class="fa fa-fw fa-sign-in"></i>로그인</a>
		        </li>
		        
		        <%}else{ %>
				<li class="nav-item">
		          <a class="nav-link" data-toggle="modal" data-target="#exampleModal">
		          
		            <i class="fa fa-fw fa-sign-out"></i>로그아웃</a>
		        </li>
		        <%} %>
		      </ul>
		    </div>
		  </nav>
			<!-- nav end-->
</body>
<script type="text/javascript">
function ThcallFunction(){
    alert("스레드에 가입되어 있지 않습니다.");
  }
</script>

</html>