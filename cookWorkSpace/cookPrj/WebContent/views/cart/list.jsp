<%@page import="com.kh.cook.cart.CartItemVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	List<CartItemVo> cartList = (List<CartItemVo>) request.getAttribute("cartList");
	
	int totalPrice = 0;
	int deliveryFee = 0;
	int totalCnt = 0;
	
	for(int i = 0; i < cartList.size(); i++){
		String price = cartList.get(i).getPrice();
		totalPrice += Integer.parseInt(price);
		
		String cnt = cartList.get(i).getCnt();
		totalCnt += Integer.parseInt(cnt);
	}
	
	if(totalPrice <= 50000){
		deliveryFee = 3000;
	}
	
%>

    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>cookTeacher</title>
        <!-- 경로 체크 필수 -->
        <link rel="stylesheet" href="/cookTeacher/resources/css/header.css">
        <link rel="stylesheet" href="/cookTeacher/resources/css/footer.css">
        <link rel="stylesheet" href="../resources/css/cart/list.css">
    </head>

    <body>

        <%@ include file="/views/common/header.jsp" %>
            <!-- 헤더는 컨테이너 밖에 -->
            <div id="container">
                <!-- 컨테이너 -->
                <main>
                    <div id="cart">
                        <h1>장바구니</h1>
						
                        <div class="cart-container">
                            <div id="cart-wrapper">

                                <div id="cart-header">
                                    <div class="all-btn">
                                            <input id="all" type="checkbox"><label for="all">전체선택</label>
                                        <div class="delete">
                                            <input type="button" value="삭제하기">
                                        </div>
                                    </div>
                                </div>
								
                                <div id="product-area">
                                    <div class="product-header"></div>
                                    <ul>
                                        <c:if test="${empty cartList}">
                                            <div class="none-cart">
                                                <p>장바구니에 담긴 상품이 없습니다.</p></div>
                                        </c:if>
                                   		<c:forEach items="${cartList}" var="cartItem">
                                        <li class="product">
                                            <input type="checkbox">
                                            <div class="thumb">
                                                <img src="<c:url value="/resources/img/product/"></c:url>${cartItem.imgPath}" alt="${cartItem.name}">
                                            </div>
                                            <div class="product-name">
                                                <a href="#">${cartItem.name}</a>
                                            </div>
                                            <div class="count-wrapper">
                                                <button class="minus"></button>
                                                <div class="count">${cartItem.cnt}</div>
                                                <button class="plus"></button>
                                            </div>
                                            <div class="price">${Integer.parseInt(cartItem.price) * Integer.parseInt(cartItem.cnt)}원</div>
                                            <button class="remove"></button>
                                        </li>
                                   		</c:forEach>
                                    </ul>
                                </div>
                            </div>
							
                            <div id="order-price">
                                <div class="price-pay">
                                    <div>결제예정금액</div>
                                </div>
                                <c:set var="totalCnt" value="0" />
                                <div class="price-wrapper">
                                    <div class="price">
                                        <div>상품금액</div>
                                <c:set var="total" value="0"/>
                              	  <c:set var="total" value="${total+totalPrice}" />
                                <c:forEach var="totalPrice" items="${cartItem.price}" varStatus="status">
                                        <div><c:out value="${total}"/>원</div>
                                </c:forEach>
                                    </div>
                                    <!-- <c:if test="${totalPrice >= 50000 || totalPrice == 0 }"></c:if> -->
                                	<c:set var="deliveryFee" value="3000" />
                                    <div class="price">
                                        <div>배송비</div>
                                        <div>${deliveryFee}원</div>
                                    </div>
                                    
                                </div>
                                <div class="sum">
                                    <div class="sum-cnt">총 ${totalCnt}건</div>
                                    <div class="sum-amt"><strong>${totalPrice + deliveryFee}</strong>원</div>
                                </div>
                                <input type="submit" value="주문하기">
                            </div>
                        </div>

                    </div>

                </main>
                <%@include file="/views/common/footer.jsp" %> 
            </div>

    <script>

        
    </script>

    </body>

    </html>