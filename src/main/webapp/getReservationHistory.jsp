<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="ReservationHistory.ReservationHistoryDTO"%>

<%
ReservationHistoryDTO ReservationHistory = new ReservationHistoryDTO();
ReservationHistory = (ReservationHistoryDTO) session.getAttribute("ReservationHistory");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
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
					<li><a href="insertCustomer.jsp"> 사용자 등록</a> <li><a href="getAirplanList.jsp"> 항공기 정보 리스트</a>
					
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
	<video
							id="video-background" autoplay muted loop>
							<source src="./img/4.mp4" type="video/mp4" />
		</video>



	
					</header>


	<h1>글 수정하기</h1>
	<hr>
	<br>
	<br>
	<form method="post" action="updateReservationHistory.Reservation">

		<input type="hidden" name="ReservationNumber"
			value="<%=ReservationHistory.getReservationNumber()%>">
		<table border="1" width="700px" cellpadding="5px">

			<tr>
				<td bgcolor="orange" align="center">SerialNumber</td>
				<td><input type="text" name="SerialNumber"
					value="<%=ReservationHistory.getSerialNumber()%>"></td>
			</tr>

			<tr>
				<td bgcolor="orange" align="center">CustomerNumber</td>
				<td><input type="text" name="RoomType"
					value="<%=ReservationHistory.getCustomerNumber()%>"></td>
			</tr>

			<tr>
				<td bgcolor="orange" align="center">ReservationStartDate</td>
				<td><textarea name="ReservationStartDate" rows="10" cols="70"> <%=ReservationHistory.getReservationStartDate()%> </textarea></td>
			</tr>

			<tr>
				<td bgcolor="orange" align="center">ReservationEndDate</td>
				<td><textarea name="ReservationEndDate" rows="10" cols="70"> <%=ReservationHistory.getReservationEndDate()%> </textarea></td>
			</tr>

			<tr>
				<td bgcolor="orange" align="center">NumberOfPeople</td>
				<td><input type="text" name="NumberOfPeople"
					value="<%=ReservationHistory.getNumberOfPeople()%>"></td>
			</tr>

			<tr>
				<td bgcolor="orange" align="center">CustomerNumber</td>
				<td><input type="text" name="PaymentMethod"
					value="<%=ReservationHistory.getPaymentMethod()%>"></td>
			</tr>



			<tr>
				<td colspan="2" align="center"><input type="submit"
					value="글 수정하기"></td>
			</tr>
		</table>
	</form>

	<br>
	<br>
	<a
		href="deleteReservationHistory.Reservation?ReservationNumber=<%=ReservationHistory.getReservationNumber()%>">
		글 삭제</a>

	<p />
	<a href="http://localhost:8081/HOTEL"> 홈으로 </a>

	<p />
	<a href="getReservationHistoryList.jsp"> 글 목록</a>
	<p />
	<a href="insertReservationHistory.jsp"> 새 글쓰기</a>
	<p />
	<a href="deleteReservationHistory.jsp"> 글 삭제</a>


</body>
</html>