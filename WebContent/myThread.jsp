<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  <title>STACK</title>
    <!--Font Awesome-->
    <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">

    <!-- Custom fonts for this template-->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">

    <!-- Page level plugin CSS-->
    <link href="vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="css/sb-admin.css" rel="stylesheet">
  <link rel="stylesheet" href="css/paginationcss.css">
  <!--a태그 클릭시 색깔 바뀌게 하기-->
  
  <style type="text/css">
  a:link{text-decoration: none; color: #2E64FE;}
  a:visited{
  text-decoration: underline;
  font-style:italic;
  color: #682692;
  }
  body {
    margin-top: 50px;
    text-align: center;
    font-size: 12px;
    font-family: "Lucida Grande", Helvetica, Arial, Verdana, sans-serif;
  }

  #calendar {
    width: 700px;
    margin: 0 auto;
  }

  .response {
    height: 60px;
  }

  .success {
    background: #cdf3cd;
    padding: 10px 60px;
    border: #c3e6c3 1px solid;
    display: inline-block;
  }
  </style>
  
  <!-- Bootstrap core CSS-->
  <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <!-- Custom fonts for this template-->
  <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
  <!-- Page level plugin CSS-->
  <link href="vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">
  <!-- Custom styles for this template-->
  <link href="css/sb-admin.css" rel="stylesheet">
  <link rel="stylesheet" href="./css1/normalize.css" />
  <link rel="stylesheet" href="./css1/board.css" />
  <link href="css/search.css" rel="stylesheet">
  <script src="js1/jquery-2.1.3.min.js"></script>
  <link rel="stylesheet" href="fullcalendar/fullcalendar.min.css" />
  <script type="text/javascript" src="fullcalendar/lib/jquery.min.js"></script>
  <script type="text/javascript" src="fullcalendar/lib/moment.min.js"></script>
  <script type="text/javascript" src="fullcalendar/fullcalendar.min.js" charset="utf-8"></script>
  <title>my thread</title>
<script>

$(document).ready(function () {
    var calendar = $('#calendar').fullCalendar({
        editable: true,
        events: "CalendarFetchAction.do",
        displayEventTime: false,
        eventRender: function (event, element, view) {
            if (event.allDay === 'true') {
                event.allDay = true;              
            } else {
                event.allDay = false;
            }
        },
        
        selectable: true,
        selectHelper: true,
        select: function (start, end, allDay) {
            var title = prompt('Event Title:');
            displayMessage(allDay);
            if (title) {
                var start = $.fullCalendar.formatDate(start, "Y-MM-DD HH:mm:ss");
                var end = $.fullCalendar.formatDate(end, "Y-MM-DD HH:mm:ss");

                $.ajax({
                    url: 'CalendaraddAction.do',
                    data: 'title=' + title + '&start=' + start + '&end=' + end,
                    type: "POST",
                    success: function (data) {
                        displayMessage("Added Successfully");
                    }
                });
                calendar.fullCalendar('renderEvent',
                        {
                            title: title,
                            start: start,
                            end: end,
                            allDay: allDay
                        },
                true
                        );
            }
            calendar.fullCalendar('unselect');
        },
        
        editable: true,
        eventDrop: function (event, delta) {
                    var start = $.fullCalendar.formatDate(event.start, "Y-MM-DD HH:mm:ss");
                    var end = $.fullCalendar.formatDate(event.end, "Y-MM-DD HH:mm:ss");
                    $.ajax({
                        url: 'CalendarEditAction.do',
                        data: 'title=' + event.title + '&start=' + start + '&end=' + end + '&id=' + event.id,
                        type: "POST",
                        success: function (response) {
                            displayMessage("Updated Successfully");
                        }
                    });
                },
                
        eventClick: function (event) {
            var deleteMsg = confirm("Do you really want to delete?");
            if (deleteMsg) {
                $.ajax({
                    type: "POST",
                    url: "CalendarDeleteAction.do",
                    data: "&id=" + event.id,
                    success: function (response) {
                            $('#calendar').fullCalendar('removeEvents', event.id);
                            displayMessage("Deleted Successfully");
                      
                    }
                });
            }
        }

    });
});

function displayMessage(message) {
	    $(".response").html("<div class='success'>"+message+"</div>");
    setInterval(function() { $(".success").fadeOut(); }, 1000);
}


</script>
</head>
<body>
<%
String threadname = request.getParameter("threadname");
%>
<jsp:include page="header.jsp" /> <%-- 정적 include --%>
  <div class="content-wrapper">
    <div class="container-fluid">
      <!-- Breadcrumbs-->
      <ol class="breadcrumb">
        <li class="breadcrumb-item">
          <a href="home.php" >STACK</a>
        </li>
        <li class="breadcrumb-item active">참여 중인 모임</li>
      </ol>
</div>
      <!-- 프로필 바-->
      <div class="card mb-3">
        <div class="card-header">
          <i class="fas fa-table"></i>
          스레드 프로필</div>
          <div class="card-body">
            <div style="width:200px; height:150px; border:1px; float:left; margin-right:10px;">
              <img src="logo.png" alt="스레드 프로필사진" border="3px" width="200px" height="150px" align="left">
            </div>
            <div style="width:500px; height:150px; border:1px; float:left;">모임명 : ${threadname} <br>모임 종류 : ${t_category}<br>모임장 : ${t_master} <br>모임 소개 : ${t_intro} </div>
	    <br>
              <br>
           
            </div>
          </div>
        

      <!--calender-->
      <div class="card mb-3">
        <div class="card-header">
          <i class="fas fa-table"></i>
          모임 일정</div>
          <div class="card-body">
          	<div class="response"></div>
          	<div id="calendar"></div>

          </div>
        </div>

      <!-- 스레드 게시판 table-->
			<article class="boardArticle" style="margin-left:0;">
										<div class="card mb-3" style="width:1200px;">
											<div class="card-header">
												<i class="fa fa-table"></i> 스레드 게시판</div>
										<div id="boardList">
											<div class="card-body">
												<div class="table-responsive">
													<table class="table table-bordered"  style="width:100%" cellspacing="0">
														<thead>
															<tr>
																<th style="text-align: center;">번호</th>
																<th style="text-align: center;">제목</th>
																<th style="text-align: center;">작성자</th>
																<th style="text-align: center;">작성일</th>
																<th style="text-align: center;">조회수</th>
															</tr>
														</thead>
					<c:forEach var="pe" items="${list}">
					<tr>
						<td align="center">${pe.getBnum()}</td>
						<td align="center"><a href="ThBoardViewAction.do?number=${pe.getBnum()}" style="cursor:pointer;">${pe.getBtitle()}</a></td>
						<td align="center">${pe.getBid()}</td>
						<td align="center">${pe.getBdate()}</td>
						<td align="center">${pe.getBhit()}</td>
					</tr>
					</c:forEach>
													</table>
									</div>
									<div class="btnSet">
									<a class="btnWrite btn" href="./ThBoard_Write.jsp">글쓰기</a>
									</div>
											</div>
									 <div class="toolbar mt-lg">
    <div class="sorter" style="margin-left:37%;">
      <ul class="pagination">
        <li><a href="javascript:PageMove(${paging.firstPageNo},'${threadname}')">맨앞으로</a></li>
        <li><a href="javascript:PageMove(${paging.prevPageNo},'${threadname}')">앞으로</a></li>
 
              <c:forEach var="i" begin="${paging.startPageNo}" end="${paging.endPageNo}" step="1">
                  <c:choose>
                      <c:when test="${i eq paging.pageNo}">
                <li class="active"><a href="javascript:PageMove(${i},'${threadname}')">${i}</a></li>
                      </c:when>
                      <c:otherwise>
                        <li><a href="javascript:PageMove(${i},'${threadname}')">${i}</a></li>
                      </c:otherwise>
                  </c:choose>
              </c:forEach>
        <li><a href="javascript:PageMove(${paging.nextPageNo},'${threadname}')">뒤로</a></li>
        <li><a href="javascript:PageMove(${paging.finalPageNo},'${threadname}')">맨뒤로</a></li>
      </ul>
    </div>
  </div>
										<div class="searchBox">
											<form action="" method="get">
												<select name="searchColumn">
													<option>제목</option>
													<option>내용</option>
													<option>작성자</option>
												</select>
												<input type="text" name="searchText" value="">
												<button type="submit">검색</button>
											</form>
										</div>
										</div>
								 	</div>
								</article>
								
			</div>
<script type="text/javascript">
function PageMove(page,name){
    location.href = "ThBoardPaginationAction.do?page="+page+"&threadname="+name;
  }
</script>
</body>
</html>