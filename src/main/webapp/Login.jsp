<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
String sessionCustomerNumber = (String) session.getAttribute("CustomerNumber");
String sessionCustomerName = (String) session.getAttribute("CustomerName");
%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>로그인페이지</title>
<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" href="css/style2.css">


<style>
/* 여기에 위에서 제공한 CSS 코드를 넣으세요 */
@media only screen and (min-width: 600px) {
	.c_m_1 {
		width: 8.33%;
	}
	.c_m_2 {
		width: 16.66%;
	}
	.c_m_3 {
		width: 25%;
	}
	.c_m_4 {
		width: 33.33%;
	}
	.c_m_5 {
		width: 41.66%;
	}
	.c_m_6 {
		width: 50%;
	}
	.c_m_7 {
		width: 58.33%;
	}
	.c_m_8 {
		width: 66.66%;
	}
	.c_m_9 {
		width: 75%;
	}
	.c_m_10 {
		width: 83.33%;
	}
	.c_m_11 {
		width: 91.66%;
	}
	.c_m_12 {
		width: 100%;
	}
}

@media only screen and (min-width: 768px) {
	.c_1 {
		width: 8.33%;
	}
	.c_2 {
		width: 16.66%;
	}
	.c_3 {
		width: 25%;
	}
	.c_4 {
		width: 33.33%;
	}
	.c_5 {
		width: 41.66%;
	}
	.c_6 {
		width: 50%;
	}
	.c_7 {
		width: 58.33%;
	}
	.c_8 {
		width: 66.66%;
	}
	.c_9 {
		width: 75%;
		font-size: 12px;
	}
	.c_10 {
		width: 83.33%;
	}
	.c_11 {
		width: 91.66%;
	}
	.c_12 {
		width: 100%;
	}
}
</style>
</head>
<body>
	<header>


		<ul class="menu">
			<li><a href="#">메인 페이지</a>
				<ul class="submenu">
					<li><a href="insertCustomer.jsp"> 사용자 등록</a>
					<li><a href="getAirplanList.jsp"> 항공기 정보 리스트</a>
					<li><a href="getAirplaneList.jsp"> 여객기정보 리스트</a>
					<li><a href="getReservationHistoryList.jsp"> 예약
							정보 리스트</a></li>
					<li><a href="Login.jsp">로그인 페이지</a></li>
				</ul></li>

			<li><a href="#">로그인페이지</a>
				<ul class="submenu">
					<li><a href="Login.jsp">로그인 페이지</a></li>
					<li><a href="getHotelsList.jsp"> 호텔정보 리스트</a>
					<li><a href="deleteBusinessInformation.jsp"> 사업자 정보 삭제</a></li>
					<li><a href="getReservationHistoryList.jsp">예약 정보</a></li>
					<li><a href="insertCustomer.jsp"> 사용자 등록</a>
				</ul></li>

			<li><a href="#">정보페이지</a>
				<ul class="submenu">
					<li><a href="insertAirplan.jsp"> 여객기 정보 등록</a>
					<li><a href="insertCustomer.jsp"> 사용자 등록</a>
					<li><a href="insertAirplane.jsp"> 항공기 정보 등록</a>
					<li><a href="insertReservationHistory.jsp">예약 정보
							등록</a></li>
					<li><a href="Login.jsp">로그인 페이지</a></li>
				</ul></li>


			<li><a href="#">등록관리페이지</a>
				<ul class="submenu">
					<li><a href="getCustomerList.jsp"> 사용자 리스트</a>
					<li><a href="insertHotels.jsp"> 호텔 정보 등록</a>
					<li><a href="insertBusinessInformation.jsp"> 사업자 정보 등록</a></li>
					<li><a href="getBusinessInformationList.jsp"> 사업자 정보 리스트</a></li>
					<li><a href="Login.jsp">로그인 페이지</a></li>

				</ul></li>
			<li><a href="#">삭제관리페이지</a>
				<ul class="submenu">
					<li><a href="deleteCustomer.jsp"> 사용자 삭제</a>
					<li><a href="deleteAirplan.jsp"> 항공 정보 삭제</a>
					<li><a href="deleteAirplane.jsp"> 비행기 정보 삭제</a>
					<li><a href="deleteReservationHistory.jsp"> 예약 정보
							삭제</a>
					<li><a href="deleteHotels.jsp"> 호텔 정보 삭제</a></li>

				</ul></li>
		</ul>

		<video id="video-background" autoplay muted loop>
			<source src="./img/1.mp4" type="video/mp4" />
		</video>



	</header>


	<main>


		<section class="c3" id="loginSection" style="width: 80%;">


			<h1>로그인 페이지</h1>
			<%
			if (sessionCustomerNumber == null) {
			%>
			<form method="post" action="login.customer">
				<table>

					<tr>
						<td>CustomerNumber :</td>
						<td><input type="text" name="CustomerNumber"></td>
					</tr>

					<tr>
						<td>Password :</td>
						<td><input type="password" name="password"></td>
					</tr>

					<tr>
						<td colspan="2" align="center"><input type="submit"
							value="로그인"></td>
					</tr>

				</table>
			</form>

			<p />
			<a href="http://localhost:8081/HOTEL"> 홈으로 </a>

			<%
			} else { // 로그인 된 상태
			%>
			<%=sessionCustomerNumber%>
			님 로그인이 성공 되었습니다.
			<p />
			당신의 아이디는
			<%=sessionCustomerName%>
			입니다.
			<p />
			<a href="logout.customer"> 로그 아웃 </a>
			<%
			}
			%>

		</section>



	</main>




	<footer>
		<section id="contact-info" class="bg-dark">
			<div class="container">
				<div class="box">
					<i class="fas fa-hotel fa-3x"></i>
					<p>Location</p>
					<a href="#none"><p>이용약관</p></a> <a href="#none"><p>개인정보처리방침</p></a>

				</div>
				<div class="box">
					<i class="fas fa-phone fa-3x"></i>
					<p>전화번호</p>
					<p>(+82)123-4567</p>
					<a href="#none"><p>회원정보 고객센터</p></a>
				</div>
				<div class="box">
					<i class="fas fa-envelope fa-3x"></i>
					<p>이메일주소</p>
					<p>wl_dhks@naver.com></p>

					<a href="#none"><p>책임의 한계와 법적고지</p></a>

				</div>
			</div>
			<div class="clear"></div>
		</section>
	</footer>
</body>
</html>