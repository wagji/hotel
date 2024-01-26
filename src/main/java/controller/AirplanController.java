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

import Airplan.AirplanDAO;
import Airplan.AirplanDTO;


//http://localhost:8081/HOTEL/*.Airplan
@WebServlet ("*.Airplan")
public class AirplanController extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AirplanController() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		request.setCharacterEncoding("UTF-8");
		
		System.out.println("Airplan 요청을 처리하는 controller 입니다. ");
		
		//	URL : http://localhost:8181/JSP_MVC_M2/my.Airplan
		// 	URI : /JSP_MVC_M2/my.Airplan
		
		String uri = request.getRequestURI(); 
		System.out.println(uri);
		
		// 
		String path = uri.substring(uri.lastIndexOf("/")); 
		System.out.println(path);
		System.out.println("====================");
		
		
		if (path.equals("/insertAirplan.Airplan")) 
		{
			System.out.println("/insertAirplan.Airplan 요청");
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
			AirplanDTO dto = new AirplanDTO(); 
			dto.setReservationNumber(ReservationNumber);
			dto.setSerialNumber(SerialNumber);
			dto.setCustomerNumber(CustomerNumber);
			dto.setReservationStartDate(ReservationStartDate);
			dto.setReservationEndDate(ReservationEndDate);
			dto.setNumberOfPeople(NumberOfPeople);
			dto.setPaymentMethod(PaymentMethod);

			
			//3. DAO 에 insertAirplan (dto)
			AirplanDAO dao = new AirplanDAO(); 
			dao.insertAirplan(dto); 			//insert 성공 
			
			//System.out.println("DB 저장 성공 ");
			
			//비즈니스 로직 완료 : DTO , DAO   
			
			// 4. 뷰 페이지 전송 : 값을 insertAirplan 후 DB의 전체 레코드를 출력 페이지로 이동 
				// 클라이언트가 getAirplanList.Airplan 요청을 새롭게 요청함. 
				//주의 : http://localhost:8181/getAirplanList.Airplan   <== 오류 
				    //  http://localhost:8181/JSP_MVC_M2/getAirplanList.Airplan   <== 정상  
			response.sendRedirect("getAirplanList.Airplan"); 
			
					
		}
		
		else if (path.equals("/getAirplanList.Airplan")) 
		{		// DB의 레코드를 출력 하는 페이지 
			System.out.println("/getAirplanList.Airplan 요청");
			//로직 처리 
			
			//1. AirplanDTO 객체 생성 
			AirplanDTO dto = new AirplanDTO(); 
			
			//2. AirplanDAO 객체의 getAirplanList(dto) 
			AirplanDAO dao = new AirplanDAO (); 
			
			//리턴 받을 변수 선언 
			List<AirplanDTO> AirplanList = new ArrayList<>(); 
			
			//AirplanList : DB의 Airplan 테이블의 레코드를 dto 로 저장후 ArrayList 내의 각 방에 저장된 상태 
			AirplanList = dao.getAirplanList(dto); 
			
			//AirplanList 클라이언트 view 페이지로 전송 : Session 변수에 담아서 client 뷰페이지로 전송
			//client 의 session 정보를 가져와서 session 변수에 할당. 
			HttpSession session = request.getSession(); 
			
			//세션에 AirplanList 를 추가
			session.setAttribute("AirplanList", AirplanList); 
			
			//클라이언트 뷰 페이지 
			response.sendRedirect("getAirplanList.jsp"); 
			
			
		}
		
		else if (path.equals("/getAirplan.Airplan")) 
		{
			System.out.println("/getAirplan.Airplan 요청");
			//로직 처리 
			
			//1. clinet 넘어오는 파라미터 seq 변수의 값을 읽어서 dto에 저장후 dao.getAirplan(dto) 
			//http://localhost:8181/JSP_MVC_M2/getAirplan.Airplan?seq=5 
			
			String ReservationNumber = request.getParameter("ReservationNumber"); 
			
			//2. dto에 seq 값을 setter 주입 
			AirplanDTO dto = new AirplanDTO(); 
			dto.setReservationNumber(ReservationNumber);
			
			//3. DAO의 getAirplan(dto) 호출후 리턴 값을 받아서 저장 
			AirplanDAO dao = new AirplanDAO(); 
			
			//리턴값을 받을 DTO 선언 
			AirplanDTO Airplan = new AirplanDTO(); 
			Airplan = dao.getAirplan(dto); 
			
			//4. 세션 변수에 저장후 뷰 페이지로 전송 , 
			HttpSession session = request.getSession(); 
			
			session.setAttribute("Airplan", Airplan); 
			
			//5. 뷰 페이지 
			response.sendRedirect("getAirplan.jsp"); 
			
			
		}
		
		else if (path.equals("/updateAirplan.Airplan")) 
		{
			System.out.println("/updateAirplan.Airplan 요청");
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
			
			//2. 변수를 AirplanDTO에 setter 주입 
			AirplanDTO dto = new AirplanDTO(); 
			dto.setReservationNumber(ReservationNumber);
			dto.setSerialNumber(SerialNumber);
			dto.setCustomerNumber(CustomerNumber);
			dto.setReservationStartDate(ReservationStartDate);
			dto.setReservationEndDate(ReservationEndDate);
			dto.setNumberOfPeople(NumberOfPeople);
			dto.setPaymentMethod(PaymentMethod);
			
			//3. AirplanDAO 에 updateAirplan(dto)  
			AirplanDAO dao = new AirplanDAO (); 
			dao.updateAirplan(dto); 
			
			//4. 뷰 페이지로 이동 ( 업데이트 후 리스트 페이지로 이동  
			response.sendRedirect("getAirplanList.Airplan");
					
		}
		
		else if (path.equals("/deleteAirplan.Airplan")) 
		{
			System.out.println("/deleteAirplan.Airplan 요청");
			//로직 처리 
			
			//1. 클라이언트의 파라미터 변수의 값 할당 : seq 
			String ReservationNumber = request.getParameter("ReservationNumber");
			
			//2. 변수의 값을 AirplanDTO에 주입 
			AirplanDTO dto = new AirplanDTO(); 
			dto.setReservationNumber(ReservationNumber);
			
			//3. AirplanDAO의 메소드 호출 : deleteAirplan(dto) 
			AirplanDAO dao = new AirplanDAO(); 
			
			dao.deleteAirplan(dto); 
			
			//4. 뷰 페이지 이동 
			response.sendRedirect("getAirplanList.Airplan"); 

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
