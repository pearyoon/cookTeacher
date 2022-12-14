<%@page import="com.kh.cook.cs.vo.CSVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	CSVo FAQvo = (CSVo)request.getAttribute("FAQvo");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>집밥쿡선생 :: 고객센터</title>
<!-- 경로 체크 필수 -->
<link rel="stylesheet" href="/cookTeacher/resources/css/header.css">
<link rel="stylesheet" href="/cookTeacher/resources/css/footer.css">
</head>
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
	height: 50px;
	margin: 0px auto;
	border-top: 3px solid black;
	text-align: center;
	line-height: 50px;
}
#title{
	width: 500px
}

/* 내용 영역 */
.content-area{
	border-top: 3px solid black;
	border-bottom: 3px solid black;
	text-align: center;
	padding: 10px;
}

/* 등록, 취소 버튼 */
#writebtn input{
	height: 30px;
	width: 50px;
	background: #255D00;
	color: #fff;
	border: none;
	border-radius: 5px;
	font-size: small;
	margin: 5px;
}

#writebtn{
	text-align: center;
	display: flex;
	flex-direction: row-reverse;
}

#writebtn input:hover{
    border: 10px yellow;
    background-color: #326e0b;
}
</style>
<body>
<%@include file="/views/common/header.jsp" %> <!-- 헤더부분 가져오기-->
	<!-- 헤더는 컨테이너 밖에 -->
	
	
	<!-- 관리자만 가능한 기능 -->
	
	<div id="container"> <!-- 컨테이너 -->
		<div class="name">
			<!-- 게시판 이름 -->
			<div id="qna">FAQ 자주묻는질문</div>
		</div>
		
		<form id="fff" action="" method="post" onsubmit="return upload();">
			<div class="title-area">
				<!-- 문의 작성 -->
					제목 : <input type="text" id="title" name="title"  value="<%= FAQvo.getTitle() %>">
			</div>
	
			<div class="content-area">
			<br>
				<textarea name="content" rows="12" cols="60" style="resize:none;" required>
				<%= FAQvo.getContent() %>
				</textarea>
			<br><br>
			</div>
			
			<div id="writebtn">
				<input type="button" name="" id="" value="취소" onclick="cancel();">
				<input type="submit" value="수정">
			</div>
			
				<!-- upload()함수 -->
			<script>
 			function upload(){
				
				Swal.fire({
				  title: '수정하시겠습니까?',
				  icon: 'question',
				  showCancelButton: true,
				  confirmButtonText: '확인',
				  cancelButtonText: '취소',
				  })
				  .then((result)=>{
					  console.log(result);
					  if (result.isConfirmed) {
						  
						  
						  Swal.fire({
	                            icon: 'success',
	                            text: '수정되었습니다.',
	                      }).then( ()=> {document.querySelector('#fff').submit();} );
					  }
				  });
				
				  return false;
			}//upload end
				
				function cancel(){
					Swal.fire({
                       title: '수정 취소',
                       icon: 'info',
                    });
					location.href="/cookTeacher/cs/FAQ/detail?no=<%=FAQvo.getQnaNo()%>"
				}
			</script>
		</form>
		<%@include file="/views/common/footer.jsp" %> <!-- 푸터부분 파일 가져오기-->
	</div>
</body>
</html>