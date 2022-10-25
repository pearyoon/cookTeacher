<%@page import="com.kh.cook.cs.vo.CSVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    CSVo QNAvo = (CSVo)request.getAttribute("QNAvo");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>QnA 글 상세조회</title>
<link rel="stylesheet" href="/cookTeacher/resources/css/header.css">
<link rel="stylesheet" href="/cookTeacher/resources/css/footer.css">
<style>
#container>div{
	box-sizing: border-box;
}

#container{
    position: absolute;
    padding-bottom: 50px;
    top: 280px;
    left: 50%;
    transform: translate(-50%);

}
/* 타이틀 글자 */
.name{
/*position: absolute;*/
display: flex;

font-family: 'Inter';
font-style: normal;
font-weight: 600;
font-size: 30px;
line-height: 50px;
text-align: center;
color: #000000;
}

/* 제목 영역 */
.title-area{
	width: 600px;
	height: 100px;
	margin: 0px auto;
	display: grid;
	grid-template-columns: 1fr 5fr 2fr;
	grid-template-rows: repeat(2, 50px);
	align-content: center;
	border-top: 3px solid black;
	padding-left: 10px;
	padding-right: 10px;
}
#a{
	grid-column: span 3;
}
.title-area>div{
	line-height: 50px;
	text-align: left;
}
.title-area>.write{
	text-align: left;
}
.title-area>#b{
	font-size: small;
}

/* 내용 영역 */
.content-area{
	width: 600px;
	height: 400px;
	border-top: 3px solid black;
	border-bottom: 3px solid black;
	padding: 10px;
	font-size: small;
}
/* 등록 버튼 */
#writebtn{
	height: 20px;
	display: flex;
	flex-direction: row-reverse;
	font-size: small;
	margin: 5px;
}
#writebtn>a{
	margin: 10px;
}
</style>
</head>
<body>
<%@include file="/views/common/header.jsp" %>

	
	<div id="container"> <!-- 컨테이너 -->
		<div class="name">
			<!-- 게시판 이름 -->
			<div id="qna">QnA</div>
		</div>

		<div class="title-area">
			<!-- 문의 작성 -->
			<div id="a">
				제목 : 
				<%=QNAvo.getTitle() %>
			</div>
			<div id="b">작성자 :</div>
			<div class="write" id="b" name="writer"><%=QNAvo.getWriter() %></div>
			<div id="b" name="enrollDate"><%=QNAvo.getQnaDate() %></div>
		</div>

		<div class="content-area">
			<br>
			<%= QNAvo.getContent() %>
			<br><br>
		</div>
		
		<%	if(loginMember != null && loginMember.getNo().equals(QNAvo.getNo())){ %>
		<div id=writebtn>
			<a href="/cookTeacher/cs/QnA/edit?no=<%= QNAvo.getQnaNo() %>">수정</a>
			<a href="/cookTeacher/cs/QnA/delete?no=<%= QNAvo.getQnaNo() %>">삭제</a>
		</div>		
		<% } %>
		
		<%@include file="/views/common/footer.jsp" %>
	</div>
</body>
</html>