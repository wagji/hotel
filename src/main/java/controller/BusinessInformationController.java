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

import BusinessInformation.BusinessInformationDAO;
import BusinessInformation.BusinessInformationDTO;

//http://localhost:8081/HOTEL/*.BusinessInformation
@WebServlet ("*.Business")
public class BusinessInformationController extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BusinessInformationController() 
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
		
		System.out.println("BusinessInformation 요청을 처리하는 controller 입니다. ");
		
		//	URL : http://localhost:8181/JSP_MVC_M2/my.BusinessInformation
		// 	URI : /JSP_MVC_M2/my.BusinessInformation
		
		String uri = request.getRequestURI(); 
		System.out.println(uri);
		
		// 
		String path = uri.substring(uri.lastIndexOf("/")); 
		System.out.println(path);
		System.out.println("====================");
		
		
		if (path.equals("/insertBusinessInformation.Business")) 
		{
			System.out.println("/insertBusinessInformation.Business 요청");
			//로직 처리 
			
			//1. 클라이언트의 넘어오는 변수가 잘 들어 오는지 확인  (클라이언트 요청) 
			String SerialNumber = request.getParameter("SerialNumber"); 
			String BusinessAddress = request.getParameter("BusinessAddress");
			String RoomType = request.getParameter("RoomType");
			Integer RoomCount = Integer.parseInt(request.getParameter("RoomCount"));
			Integer Fee = Integer.parseInt(request.getParameter("Fee"));
 
			/*
			System.out.println("title : " + title);
			System.out.println("write : " + write);
			System.out.println("content : " + content);
			*/ 
			
			//2. 클라이언트에서 넘어오는 변수의 값을 DTO에 Setter 주입 
			BusinessInformationDTO dto = new BusinessInformationDTO(); 
			dto.setSerialNumber(SerialNumber);
			dto.setBusinessAddress(BusinessAddress);
			dto.setRoomType(RoomType);
			dto.setRoomCount(RoomCount);
			dto.setFee(Fee);
			
			//3. DAO 에 insertBusinessInformation (dto)
			BusinessInformationDAO dao = new BusinessInformationDAO(); 
			dao.insertBusinessInformation(dto); 			//insert 성공 
			
			//System.out.println("DB 저장 성공 ");
			
			//비즈니스 로직 완료 : DTO , DAO   
			
			// 4. 뷰 페이지 전송 : 값을 insertBusinessInformation 후 DB의 전체 레코드를 출력 페이지로 이동 
				// 클라이언트가 getBusinessInformationList.BusinessInformation 요청을 새롭게 요청함. 
				//주의 : http://localhost:8181/getBusinessInformationList.BusinessInformation   <== 오류 
				    //  http://localhost:8181/JSP_MVC_M2/getBusinessInformationList.BusinessInformation   <== 정상  
			response.sendRedirect("getBusinessInformationList.Business"); 
			
					
		}
		
		else if (path.equals("/getBusinessInformationList.Business")) 
		{		// DB의 레코드를 출력 하는 페이지 
			System.out.println("/getBusinessInformationList.Business 요청");
			//로직 처리 
			
			//1. BusinessInformationDTO 객체 생성 
			BusinessInformationDTO dto = new BusinessInformationDTO(); 
			
			//2. BusinessInformationDAO 객체의 getBusinessInformationList(dto) 
			BusinessInformationDAO dao = new BusinessInformationDAO (); 
			
			//리턴 받을 변수 선언 
			List<BusinessInformationDTO> BusinessInformationList = new ArrayList<>(); 
			
			//BusinessInformationList : DB의 BusinessInformation 테이블의 레코드를 dto 로 저장후 ArrayList 내의 각 방에 저장된 상태 
			BusinessInformationList = dao.getBusinessInformationList(dto); 
			
			//BusinessInformationList 클라이언트 view 페이지로 전송 : Session 변수에 담아서 client 뷰페이지로 전송
			//client 의 session 정보를 가져와서 session 변수에 할당. 
			HttpSession session = request.getSession(); 
			
			//세션에 BusinessInformationList 를 추가
			session.setAttribute("BusinessInformationList", BusinessInformationList); 
			
			//클라이언트 뷰 페이지 
			response.sendRedirect("getBusinessInformationList.jsp"); 
			
			
		}
		
		else if (path.equals("/getBusinessInformation.Business")) 
		{
			System.out.println("/getBusinessInformation.Business 요청");
			//로직 처리 
			
			//1. clinet 넘어오는 파라미터 seq 변수의 값을 읽어서 dto에 저장후 dao.getBusinessInformation(dto) 
			//http://localhost:8181/JSP_MVC_M2/getBusinessInformation.BusinessInformation?seq=5 
			
			String SerialNumber = request.getParameter("SerialNumber"); 
			
			//2. dto에 seq 값을 setter 주입 
			BusinessInformationDTO dto = new BusinessInformationDTO(); 
			dto.setSerialNumber(SerialNumber);
			
			//3. DAO의 getBusinessInformation(dto) 호출후 리턴 값을 받아서 저장 
			BusinessInformationDAO dao = new BusinessInformationDAO(); 
			
			//리턴값을 받을 DTO 선언 
			BusinessInformationDTO BusinessInformation = new BusinessInformationDTO(); 
			BusinessInformation = dao.getBusinessInformation(dto); 
			
			//4. 세션 변수에 저장후 뷰 페이지로 전송 , 
			HttpSession session = request.getSession(); 
			
			session.setAttribute("BusinessInformation", BusinessInformation); 
			
			//5. 뷰 페이지 
			response.sendRedirect("getBusinessInformation.jsp"); 
			
			
		}
		
		else if (path.equals("/updateBusinessInformation.Business")) 
		{
			System.out.println("/updateBusinessInformation.Business 요청");
			//로직 처리 
			//1. 클라이언트의 파라미터의 변수를 받아서 새로운 변수에 저장 
			String SerialNumber = request.getParameter("SerialNumber"); 
			String BusinessAddress = request.getParameter("BusinessAddress");
			String RoomType = request.getParameter("RoomType");
			Integer RoomCount = Integer.parseInt(request.getParameter("RoomCount"));
			Integer Fee = Integer.parseInt(request.getParameter("Fee"));
			
			// 변수값 출력 :
			/*
			System.out.println(title);
			System.out.println(write);
			System.out.println(content);
			System.out.println(seq);
			*/ 
			
			//2. 변수를 BusinessInformationDTO에 setter 주입 
			BusinessInformationDTO dto = new BusinessInformationDTO(); 
			dto.setSerialNumber(SerialNumber);
			dto.setBusinessAddress(BusinessAddress);
			dto.setRoomType(RoomType);
			dto.setRoomCount(RoomCount);
			dto.setFee(Fee);

			
			//3. BusinessInformationDAO 에 updateBusinessInformation(dto)  
			BusinessInformationDAO dao = new BusinessInformationDAO (); 
			dao.updateBusinessInformation(dto); 
			
			//4. 뷰 페이지로 이동 ( 업데이트 후 리스트 페이지로 이동  
			response.sendRedirect("getBusinessInformationList.Business");
					
		}
		
		else if (path.equals("/deleteBusinessInformation.Business")) 
		{
			System.out.println("/deleteBusinessInformation.Business 요청");
			//로직 처리 
			
			//1. 클라이언트의 파라미터 변수의 값 할당 : seq 
			String SerialNumber = request.getParameter("SerialNumber");
			
			//2. 변수의 값을 BusinessInformationDTO에 주입 
			BusinessInformationDTO dto = new BusinessInformationDTO(); 
			dto.setSerialNumber(SerialNumber);
			
			//3. BusinessInformationDAO의 메소드 호출 : deleteBusinessInformation(dto) 
			BusinessInformationDAO dao = new BusinessInformationDAO(); 
			
			dao.deleteBusinessInformation(dto); 
			
			//4. 뷰 페이지 이동 
			response.sendRedirect("getBusinessInformationList.Business"); 

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
