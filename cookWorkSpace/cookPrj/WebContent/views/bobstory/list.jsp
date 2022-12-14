<%@page import="com.kh.cook.menu.vo.MenuCateVo"%>
<%@page import="com.kh.cook.bobstory.vo.PageVo"%>
<%@page import="com.kh.cook.bobstory.vo.BobstoryVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<BobstoryVo> voList = (List<BobstoryVo>)request.getAttribute("voList");
	PageVo pv = (PageVo)request.getAttribute("pv");
	MenuCateVo mcv = new MenuCateVo();
%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>집밥쿡선생 :: 쿡스토리</title>
<link rel="stylesheet" href="/cookTeacher/resources/css/header.css">
<link rel="stylesheet" href="/cookTeacher/resources/css/footer.css">
<link rel="stylesheet" href="/cookTeacher/resources/css/bobstory/list.css">
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
</head>
<body>
		<%@include file="/views/common/header.jsp" %> <!-- 헤더부분 가져오기-->
		<!-- 헤더는 컨테이너 밖에 -->
		<div id="container"> <!-- 컨테이너 -->
			<br>
			<br>
			<p>쿡 스토리</p>
			<br>
			<br>
			<ul>
				<li><a href="/cookTeacher/bobstory/list/like">추천순</a></li>
				&nbsp;
				<span> | </span>
				&nbsp;
				<li><a href="/cookTeacher/bobstory/list/view">조회순</a></li>
			</ul>
			<br>
			<table class="table table-striped">
				<thead>
					<th scope="col">번호</th>
					<th scope="col">음식</th>
					<th scope="col">작성자</th>
					<th scope="col">제목</th>
					<th scope="col">조회수</th>
					<th scope="col">작성일시</th>
					<th scope="col">추천</th>
				</thead>
				<tbody>
				<%for(int i = 0; i < voList.size(); i++){%>
					<tr align="center">
						<td style="width: 80px;"><%=voList.get(i).getNo() %></td>
						<td style="width: 80px;"><%=voList.get(i).getCategory() %></td>
						<td style="width: 130px;"><%=voList.get(i).getWriter() %></td>
						<td style="text-align: left;"><a href="<%=root%>/bobstory/detail?bno=<%=voList.get(i).getNo() %>"><%=voList.get(i).getTitle() %></a></td>
						<td style="width: 100px;"><%=voList.get(i).getViewCount() %></td>
						<td style="width: 180px;"><%=voList.get(i).getEnrollDate() %></td>
						<td style="width: 80px;"><%=voList.get(i).getcLike()%></td>
					</tr>
				<%} %>	
				</tbody>
			</table>
			<%if(loginMember != null){%>
				<div id="main-top">
					<button id="write-btn"><a href="/cookTeacher/bobstory/write">글쓰기</a></button>
				</div>
			<%}%>
				<br>
			<div id="page-area">
			        	
				<%if(pv.getStartPage() != 1){%>
					<a href="/cookTeacher/bobstory/list?pno=<%=pv.getStartPage()-1%>" class="btn"><</a>
				<%}%>
			
			
				<%for(int i = pv.getStartPage(); i <= pv.getEndPage(); i++){%>
					<a href="/cookTeacher/bobstory/list?pno=<%=i%>" class="btn"><%=i%></a>
				<%}%>
				
				<%if(pv.getEndPage() != pv.getMaxPage()){%>
						<a href="/cookTeacher/bobstory/list?pno=<%=pv.getEndPage()+1 %>" class="btn">></a>
				<%}%>
				
				
			</div>
			<br>
			<br>
			<!-- <div id="main">
				<%if(loginMember != null){%>
			        <div id="main-top">
			            <a href="/cookTeacher/bobstory/write">글쓰기</a>
			        </div>
				<%}%>
			
			        <div>번호</div>
			        <div>제목</div>
			        <div>작성자</div>
			        <div>작성일시</div>
			        <div>조회수</div>
			        
			        <%for(int i = 0; i < voList.size(); i++){%>
			        	<div><%=voList.get(i).getNo() %></div>
			        	<div><a href="<%=root%>/board/detail?bno=<%=voList.get(i).getNo() %>"><%=voList.get(i).getTitle() %></a></div>
			        	<div><%=voList.get(i).getWriter() %></div>
			        	<div><%=voList.get(i).getEnrollDate() %></div>
			        	<div><%=voList.get(i).getViewCount() %></div>		
			        <%}%>
			
			        <div id="page-area">
			        	
			        	<%if(pv.getStartPage() != 1){%>
				        	<a href="/cookTeacher/bobstory/list?pno=<%=pv.getStartPage()-1%>" class="btn">이전</a>
			        	<%}%>
			        
			        
				        <%for(int i = pv.getStartPage(); i <= pv.getEndPage(); i++){%>
					        <a href="/cookTeacher/bobstory/list?pno=<%=i%>" class="btn"><%=i%></a>
				        <%}%>
				        
				        <%if(pv.getEndPage() != pv.getMaxPage()){%>
						        <a href="/cookTeacher/bobstory/list?pno=<%=pv.getEndPage()+1 %>" class="btn">다음</a>
				        <%}%>
				        
			            
			        </div>
			
			    </div> -->
			<%@include file="/views/common/footer.jsp" %> <!-- 푸터부분 파일 가져오기-->
		</div>

</body>
</html>