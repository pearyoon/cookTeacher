<%@page import="com.kh.cook.product.vo.PageVo"%>
<%@page import="com.kh.cook.product.vo.ProductVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	List<ProductVo> voList = (List<ProductVo>)request.getAttribute("voList");
	PageVo pv = (PageVo)request.getAttribute("pv");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>cookTeacher</title>
<link rel="stylesheet" href="/cookTeacher/resources/css/header.css">
<link rel="stylesheet" href="/cookTeacher/resources/css/main.css">
<link rel="stylesheet" href="/cookTeacher/resources/css/footer.css">

<!-- Latest compiled and minified CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"></script>

<style>

a {
	color: black;
}
a:link {
	text-decoration: none;
}
a:visited { 
	color: black; text-decoration: none;
}
a:hover { 
	text-decoration: none;
}

#main-menu-bar a{
    font-size: 16px;
    text-align: center;
}

#main-menu-bar {
margin-top: 30px;
margin-bottom: 30px;
}

#price{
    font-size: larger;
    font-weight: 700;
}
#category-name{
    font-weight: 800;
    font-size: xx-large;
}
#main-top>div:first-child, #main-middle>div:first-child, #main-bottom>div:first-child {
	font-size: 1.5rem;
	padding: 20px;
}   

.header-menu-items > a {
	color: white;
}
#header-search-area>button {
	width: 45px;
	height: 45px;
	background-color: white;
	border-left: white;
	border-bottom-right-radius: 10px;
	border-top-right-radius: 10px;
}

.main-menu-bar-items > a:hover{
	color: white;
	
}
    
</style>
</head>
<body>
   <%@include file="/views/common/header.jsp" %>
    <div id="container">
        <main>
            <hr>
            <div id="main-bottom">
            <div>해산물</div>
            
                <hr>
                <br><br>
                <div class="main-prod-area">
               		<%for(int i = 1; i < 5; ++i){%>
               			<div>
	            			<a href="/cookTeacher/product/detail/productDetail?no=<%=voList.get(i).getProdNo() %>">
		                        <img src="/cookTeacher/resources/img/product/<%=voList.get(i).getImgPath() %>" alt="식재료게시판담당" width="100%" height="100%">
					            <div><%= voList.get(i).getName() %> / <%= voList.get(i).getWeight() %></div>
					            <div><%= voList.get(i).getPrice() %> 원</div>
	                        </a>
                        </div>
					<%}%>
                </div>
                <div class="main-prod-area">
               		<%for(int i = 5; i < 9; ++i){%>
               			<div>
	            			<a href="/cookTeacher/product/detail/productDetail?no=<%=voList.get(i).getProdNo() %>">
		                        <img src="/cookTeacher/resources/img/product/<%=voList.get(i).getImgPath() %>" alt="식재료게시판담당" width="100%" height="100%">
					            <div><%= voList.get(i).getName() %> / <%= voList.get(i).getWeight() %></div>
					            <div><%= voList.get(i).getPrice() %> 원</div>
	                        </a>
                        </div>
					<%}%>
                </div>
                <div class="main-prod-area">
               		<%for(int i = 9; i < 13; ++i){%>
               			<div>
	            			<a href="/cookTeacher/product/detail/productDetail?no=<%=voList.get(i).getProdNo() %>">
		                        <img src="/cookTeacher/resources/img/product/<%=voList.get(i).getImgPath() %>" alt="식재료게시판담당" width="100%" height="100%">
					            <div><%= voList.get(i).getName() %> / <%= voList.get(i).getWeight() %></div>
					            <div><%= voList.get(i).getPrice() %> 원</div>
	                        </a>
                        </div>
					<%}%>
                </div>
                <div class="main-prod-area">
               		<%for(int i = 13; i < 17; ++i){%>
               			<div>
	            			<a href="/cookTeacher/product/detail/productDetail?no=<%=voList.get(i).getProdNo() %>">
		                        <img src="/cookTeacher/resources/img/product/<%=voList.get(i).getImgPath() %>" alt="식재료게시판담당" width="100%" height="100%">
					            <div><%= voList.get(i).getName() %> / <%= voList.get(i).getWeight() %></div>
					            <div><%= voList.get(i).getPrice() %> 원</div>
	                        </a>
                        </div>
					<%}%>
                </div>

		        <div id="page-area">
		        	<!-- 이전 버튼 -->
		        	<%if(pv.getStartPage() != 1){%>
			        	<a href="/cookTeacher/product/main/productList?pno=<%= pv.getStartPage()-1 %>" class="btn btn-primary btn-sm">이전</a>
		        	<%}%>
		        	
		            <!-- 페이지 버튼 -->
		        	<%for(int i = pv.getStartPage(); i<= pv.getEndPage(); ++i){%>
			            <a href="/cookTeacher/product/main/productList?pno=<%= i %>" class="btn btn-primary btn-sm"><%= i %></a>
		        	<%}%>
		        	
		        	<!-- 다음 버튼 -->
		        	<%if(pv.getEndPage() != pv.getMaxPage()){%>
					    <a href="/cookTeacher/product/main/productList?pno=<%= pv.getEndPage()+1 %>" class="btn btn-primary btn-sm">다음</a>
		       		<%}%>
		        	
		        </div>
  

                 
            </div>
		<%@include file="/views/common/footer.jsp" %>

        </main>
    </div>
    

</body>
</html>