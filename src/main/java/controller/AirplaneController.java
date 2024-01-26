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

import Airplane.AirplaneDAO;
import Airplane.AirplaneDTO;


//http://localhost:8081/HOTEL/*.Airplane
@WebServlet ("*.Airplane")
public class AirplaneController extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AirplaneController() 
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
		
		System.out.println("Airplane 요청을 처리하는 controller 입니다. ");
		
		//	URL : http://localhost:8181/JSP_MVC_M2/my.Airplane
		// 	URI : /JSP_MVC_M2/my.Airplane
		
		String uri = request.getRequestURI(); 
		System.out.println(uri);
		
		// 
		String path = uri.substring(uri.lastIndexOf("/")); 
		System.out.println(path);
		System.out.println("====================");
		
		
		if (path.equals("/insertAirplane.Airplane")) 
		{
			System.out.println("/insertAirplane.Airplane 요청");
			//로직 처리 
			
			//1. 클라이언트의 넘어오는 변수가 잘 들어 오는지 확인  (클라이언트 요청) 
			String SerialNumber = request.getParameter("SerialNumber"); 
			String AirplaneName = request.getParameter("AirplaneName"); 
			int Capacity = Integer.parseInt(request.getParameter("Capacity"));
			String DepartureCity = request.getParameter("DepartureCity"); 
			String DestinationCity = request.getParameter("DestinationCity");
			/*
			System.out.println("title : " + title);
			System.out.println("write : " + write);
			System.out.println("content : " + content);
			*/ 
			
			//2. 클라이언트에서 넘어오는 변수의 값을 DTO에 Setter 주입 
			AirplaneDTO dto = new AirplaneDTO(); 
			dto.setSerialNumber(SerialNumber); 
			dto.setAirplaneName(AirplaneName);
			dto.setCapacity(Capacity);
			dto.setDepartureCity(DepartureCity); 
			dto.setDestinationCity(DestinationCity);
			
			//3. DAO 에 insertAirplane (dto)
			AirplaneDAO dao = new AirplaneDAO(); 
			dao.insertAirplane(dto); 			//insert 성공 
			
			//System.out.println("DB 저장 성공 ");
			
			//비즈니스 로직 완료 : DTO , DAO   
			
			// 4. 뷰 페이지 전송 : 값을 insertAirplane 후 DB의 전체 레코드를 출력 페이지로 이동 
				// 클라이언트가 getAirplaneList.Airplane 요청을 새롭게 요청함. 
				//주의 : http://localhost:8181/getAirplaneList.Airplane   <== 오류 
				    //  http://localhost:8181/JSP_MVC_M2/getAirplaneList.Airplane   <== 정상  
			response.sendRedirect("getAirplaneList.Airplane"); 
			
					
		}
		
		else if (path.equals("/getAirplaneList.Airplane")) 
		{		// DB의 레코드를 출력 하는 페이지 
			System.out.println("/getAirplaneList.Airplane 요청");
			//로직 처리 
			
			//1. AirplaneDTO 객체 생성 
			AirplaneDTO dto = new AirplaneDTO(); 
			
			//2. AirplaneDAO 객체의 getAirplaneList(dto) 
			AirplaneDAO dao = new AirplaneDAO (); 
			
			//리턴 받을 변수 선언 
			List<AirplaneDTO> AirplaneList = new ArrayList<>(); 
			
			//AirplaneList : DB의 Airplane 테이블의 레코드를 dto 로 저장후 ArrayList 내의 각 방에 저장된 상태 
			AirplaneList = dao.getAirplaneList(dto); 
			
			//AirplaneList 클라이언트 view 페이지로 전송 : Session 변수에 담아서 client 뷰페이지로 전송
			//client 의 session 정보를 가져와서 session 변수에 할당. 
			HttpSession session = request.getSession(); 
			
			//세션에 AirplaneList 를 추가
			session.setAttribute("AirplaneList", AirplaneList); 
			
			//클라이언트 뷰 페이지 
			response.sendRedirect("getAirplaneList.jsp"); 
			
			
		}
		
		else if (path.equals("/getAirplane.Airplane")) 
		{
			System.out.println("/getAirplane.Airplane 요청");
			//로직 처리 
			
			//1. clinet 넘어오는 파라미터 seq 변수의 값을 읽어서 dto에 저장후 dao.getAirplane(dto) 
			//http://localhost:8181/JSP_MVC_M2/getAirplane.Airplane?seq=5 
			
			String SerialNumber = request.getParameter("SerialNumber"); 
			
			//2. dto에 seq 값을 setter 주입 
			AirplaneDTO dto = new AirplaneDTO(); 
			dto.setSerialNumber(SerialNumber); 
			
			//3. DAO의 getAirplane(dto) 호출후 리턴 값을 받아서 저장 
			AirplaneDAO dao = new AirplaneDAO(); 
			
			//리턴값을 받을 DTO 선언 
			AirplaneDTO Airplane = new AirplaneDTO(); 
			Airplane = dao.getAirplane(dto); 
			
			//4. 세션 변수에 저장후 뷰 페이지로 전송 , 
			HttpSession session = request.getSession(); 
			
			session.setAttribute("Airplane", Airplane); 
			
			//5. 뷰 페이지 
			response.sendRedirect("getAirplane.jsp"); 
			
			
		}
		
		else if (path.equals("/updateAirplane.Airplane")) 
		{
			System.out.println("/updateAirplane.Airplane 요청");
			//로직 처리 
			//1. 클라이언트의 파라미터의 변수를 받아서 새로운 변수에 저장 
			String SerialNumber = request.getParameter("SerialNumber"); 
			String AirplaneName = request.getParameter("AirplaneName"); 
			int Capacity = Integer.parseInt(request.getParameter("Capacity"));
			String DepartureCity = request.getParameter("DepartureCity"); 
			String DestinationCity = request.getParameter("DestinationCity"); 
			
			// 변수값 출력 :
			/*
			System.out.println(title);
			System.out.println(write);
			System.out.println(content);
			System.out.println(seq);
			*/ 
			
			//2. 변수를 AirplaneDTO에 setter 주입 
			AirplaneDTO dto = new AirplaneDTO(); 
			dto.setSerialNumber(SerialNumber); 
			dto.setAirplaneName(AirplaneName);
			dto.setCapacity(Capacity);
			dto.setDepartureCity(DepartureCity); 
			dto.setDestinationCity(DestinationCity);
			
			//3. AirplaneDAO 에 updateAirplane(dto)  
			AirplaneDAO dao = new AirplaneDAO (); 
			dao.updateAirplane(dto); 
			
			//4. 뷰 페이지로 이동 ( 업데이트 후 리스트 페이지로 이동  
			response.sendRedirect("getAirplaneList.Airplane");
					
		}
		
		else if (path.equals("/deleteAirplane.Airplane")) 
		{
			System.out.println("/deleteAirplane.Airplane 요청");
			//로직 처리 
			
			//1. 클라이언트의 파라미터 변수의 값 할당 : seq 
			String SerialNumber = request.getParameter("SerialNumber");
			
			//2. 변수의 값을 AirplaneDTO에 주입 
			AirplaneDTO dto = new AirplaneDTO(); 
			dto.setSerialNumber(SerialNumber); 
			
			//3. AirplaneDAO의 메소드 호출 : deleteAirplane(dto) 
			AirplaneDAO dao = new AirplaneDAO(); 
			
			dao.deleteAirplane(dto); 
			
			//4. 뷰 페이지 이동 
			response.sendRedirect("getAirplaneList.Airplane"); 

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
