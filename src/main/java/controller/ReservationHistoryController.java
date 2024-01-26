package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ReservationHistory.ReservationHistoryDAO;
import ReservationHistory.ReservationHistoryDTO;


//http://localhost:8081/HOTEL/*.ReservationHistory
@WebServlet ("*.Reservation")
public class ReservationHistoryController extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReservationHistoryController() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		request.setCharacterEncoding("UTF-8");
		
		System.out.println("ReservationHistory 요청을 처리하는 controller 입니다. ");
		
		//	URL : http://localhost:8181/JSP_MVC_M2/my.ReservationHistory
		// 	URI : /JSP_MVC_M2/my.ReservationHistory
		
		String uri = request.getRequestURI(); 
		System.out.println(uri);
		
		// 
		String path = uri.substring(uri.lastIndexOf("/")); 
		System.out.println(path);
		System.out.println("====================");
		
		
		if (path.equals("/insertReservationHistory.Reservation")) 
		{
			System.out.println("/insertReservationHistory.Reservation 요청");
			//로직 처리 
			
			//1. 클라이언트의 넘어오는 변수가 잘 들어 오는지 확인  (클라이언트 요청) 
			String ReservationNumber = request.getParameter("ReservationNumber");
			String SerialNumber = request.getParameter("SerialNumber"); 
			String CustomerNumber = request.getParameter("CustomerNumber");
			String ReservationStartDate = request.getParameter("ReservationStartDate");
			String ReservationEndDate = request.getParameter("ReservationEndDate");
			Integer NumberOfPeople = Integer.parseInt(request.getParameter("NumberOfPeople"));
			String PaymentMethod = request.getParameter("PaymentMethod");
 
			/*
			System.out.println("title : " + title);
			System.out.println("write : " + write);
			System.out.println("content : " + content);
			*/ 
			
			//2. 클라이언트에서 넘어오는 변수의 값을 DTO에 Setter 주입 
			ReservationHistoryDTO dto = new ReservationHistoryDTO(); 
			dto.setReservationNumber(ReservationNumber);
			dto.setSerialNumber(SerialNumber);
			dto.setCustomerNumber(CustomerNumber);
			dto.setReservationStartDate(ReservationStartDate);
			dto.setReservationEndDate(ReservationEndDate);
			dto.setNumberOfPeople(NumberOfPeople);
			dto.setPaymentMethod(PaymentMethod);
			
			//3. DAO 에 insertReservationHistory (dto)
			ReservationHistoryDAO dao = new ReservationHistoryDAO(); 
			dao.insertReservationHistory(dto); 			//insert 성공 
			
			//System.out.println("DB 저장 성공 ");
			
			//비즈니스 로직 완료 : DTO , DAO   
			
			// 4. 뷰 페이지 전송 : 값을 insertReservationHistory 후 DB의 전체 레코드를 출력 페이지로 이동 
				// 클라이언트가 getReservationHistoryList.ReservationHistory 요청을 새롭게 요청함. 
				//주의 : http://localhost:8181/getReservationHistoryList.ReservationHistory   <== 오류 
				    //  http://localhost:8181/JSP_MVC_M2/getReservationHistoryList.ReservationHistory   <== 정상  
			response.sendRedirect("getReservationHistoryList.Reservation"); 
			
					
		}
		
		else if (path.equals("/getReservationHistoryList.Reservation")) 
		{		// DB의 레코드를 출력 하는 페이지 
			System.out.println("/getReservationHistoryList.Reservation 요청");
			//로직 처리 
			
			//1. ReservationHistoryDTO 객체 생성 
			ReservationHistoryDTO dto = new ReservationHistoryDTO(); 
			
			//2. ReservationHistoryDAO 객체의 getReservationHistoryList(dto) 
			ReservationHistoryDAO dao = new ReservationHistoryDAO (); 
			
			//리턴 받을 변수 선언 
			List<ReservationHistoryDTO> ReservationHistoryList = new ArrayList<>(); 
			
			//ReservationHistoryList : DB의 ReservationHistory 테이블의 레코드를 dto 로 저장후 ArrayList 내의 각 방에 저장된 상태 
			ReservationHistoryList = dao.getReservationHistoryList(dto); 
			
			//ReservationHistoryList 클라이언트 view 페이지로 전송 : Session 변수에 담아서 client 뷰페이지로 전송
			//client 의 session 정보를 가져와서 session 변수에 할당. 
			HttpSession session = request.getSession(); 
			
			//세션에 ReservationHistoryList 를 추가
			session.setAttribute("ReservationHistoryList", ReservationHistoryList); 
			
			//클라이언트 뷰 페이지 
			response.sendRedirect("getReservationHistoryList.jsp"); 
			
			
		}
		
		else if (path.equals("/getReservationHistory.Reservation")) 
		{
			System.out.println("/getReservationHistory.Reservation 요청");
			//로직 처리 
			
			//1. clinet 넘어오는 파라미터 seq 변수의 값을 읽어서 dto에 저장후 dao.getReservationHistory(dto) 
			//http://localhost:8181/JSP_MVC_M2/getReservationHistory.ReservationHistory?seq=5 
			
			String ReservationNumber = request.getParameter("ReservationNumber"); 
			
			//2. dto에 seq 값을 setter 주입 
			ReservationHistoryDTO dto = new ReservationHistoryDTO(); 
			dto.setReservationNumber(ReservationNumber);
			
			//3. DAO의 getReservationHistory(dto) 호출후 리턴 값을 받아서 저장 
			ReservationHistoryDAO dao = new ReservationHistoryDAO(); 
			
			//리턴값을 받을 DTO 선언 
			ReservationHistoryDTO ReservationHistory = new ReservationHistoryDTO(); 
			ReservationHistory = dao.getReservationHistory(dto); 
			
			//4. 세션 변수에 저장후 뷰 페이지로 전송 , 
			HttpSession session = request.getSession(); 
			
			session.setAttribute("ReservationHistory", ReservationHistory); 
			
			//5. 뷰 페이지 
			response.sendRedirect("getReservationHistory.jsp"); 
			
			
		}
		
		else if (path.equals("/updateReservationHistory.Reservation")) 
		{
			System.out.println("/updateReservationHistory.Reservation 요청");
			//로직 처리 
			//1. 클라이언트의 파라미터의 변수를 받아서 새로운 변수에 저장 
			String ReservationNumber = request.getParameter("ReservationNumber");
			String SerialNumber = request.getParameter("SerialNumber"); 
			String CustomerNumber = request.getParameter("CustomerNumber");
			String ReservationStartDate = request.getParameter("ReservationStartDate");
			String ReservationEndDate = request.getParameter("ReservationEndDate");
			Integer NumberOfPeople = Integer.parseInt(request.getParameter("NumberOfPeople"));
			String PaymentMethod = request.getParameter("PaymentMethod");
			
			// 변수값 출력 :
			/*
			System.out.println(title);
			System.out.println(write);
			System.out.println(content);
			System.out.println(seq);
			*/ 
			
			//2. 변수를 ReservationHistoryDTO에 setter 주입 
			ReservationHistoryDTO dto = new ReservationHistoryDTO(); 
			dto.setReservationNumber(ReservationNumber);
			dto.setSerialNumber(SerialNumber);
			dto.setCustomerNumber(CustomerNumber);
			dto.setReservationStartDate(ReservationStartDate);
			dto.setReservationEndDate(ReservationEndDate);
			dto.setNumberOfPeople(NumberOfPeople);
			dto.setPaymentMethod(PaymentMethod);
			
			//3. ReservationHistoryDAO 에 updateReservationHistory(dto)  
			ReservationHistoryDAO dao = new ReservationHistoryDAO (); 
			dao.updateReservationHistory(dto); 
			
			//4. 뷰 페이지로 이동 ( 업데이트 후 리스트 페이지로 이동  
			response.sendRedirect("getReservationHistoryList.Reservation");
					
		}
		
		else if (path.equals("/deleteReservationHistory.Reservation")) 
		{
			System.out.println("/deleteReservationHistory.Reservation 요청");
			//로직 처리 
			
			//1. 클라이언트의 파라미터 변수의 값 할당 : seq 
			String ReservationNumber = request.getParameter("ReservationNumber");
			
			//2. 변수의 값을 ReservationHistoryDTO에 주입 
			ReservationHistoryDTO dto = new ReservationHistoryDTO(); 
			dto.setReservationNumber(ReservationNumber);
			
			//3. ReservationHistoryDAO의 메소드 호출 : deleteReservationHistory(dto) 
			ReservationHistoryDAO dao = new ReservationHistoryDAO(); 
			
			dao.deleteReservationHistory(dto); 
			
			//4. 뷰 페이지 이동 
			response.sendRedirect("getReservationHistoryList.Reservation"); 

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
