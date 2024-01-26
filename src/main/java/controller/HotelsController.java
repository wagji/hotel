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

import hotels.HotelsDTO;
import hotels.HotelsDAO;

//http://localhost:8081/HOTEL/*.hotels
@WebServlet ("*.Hotels")
public class HotelsController extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HotelsController() 
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
		
		System.out.println("Hotels 요청을 처리하는 controller 입니다. ");
		
		//	URL : http://localhost:8181/JSP_MVC_M2/my.Hotels
		// 	URI : /JSP_MVC_M2/my.Hotels
		
		String uri = request.getRequestURI(); 
		System.out.println(uri);
		
		// 
		String path = uri.substring(uri.lastIndexOf("/")); 
		System.out.println(path);
		System.out.println("====================");
		
		
		if (path.equals("/insertHotels.Hotels")) 
		{
			System.out.println("/insertHotels.Hotels 요청");
			//로직 처리 
			
			//1. 클라이언트의 넘어오는 변수가 잘 들어 오는지 확인  (클라이언트 요청) 
			String HotelsNumber = request.getParameter("HotelsNumber"); 
			String HotelsName = request.getParameter("HotelsName");
			String HotelsAddress = request.getParameter("HotelsAddress");
 
			
			//2. 클라이언트에서 넘어오는 변수의 값을 DTO에 Setter 주입 
			HotelsDTO dto = new HotelsDTO(); 
			dto.setHotelsNumber(HotelsNumber);
			dto.setHotelsName(HotelsName);
			dto.setHotelsAddress(HotelsAddress);
			
			//3. DAO 에 insertHotels (dto)
			HotelsDAO dao = new HotelsDAO(); 
			dao.insertHotels(dto); 			//insert 성공 
			
			//System.out.println("DB 저장 성공 ");
			
			//비즈니스 로직 완료 : DTO , DAO   
			
			// 4. 뷰 페이지 전송 : 값을 insertHotels 후 DB의 전체 레코드를 출력 페이지로 이동 
				// 클라이언트가 getHotelsList.Hotels 요청을 새롭게 요청함. 
				//주의 : http://localhost:8181/getHotelsList.Hotels   <== 오류 
				    //  http://localhost:8181/JSP_MVC_M2/getHotelsList.Hotels   <== 정상  
			response.sendRedirect("getHotelsList.Hotels"); 
			
					
		}
		
		else if (path.equals("/getHotelsList.Hotels")) 
		{		// DB의 레코드를 출력 하는 페이지 
			System.out.println("/getHotelsList.Hotels 요청");
			//로직 처리 
			
			//1. HotelsDTO 객체 생성 
			HotelsDTO dto = new HotelsDTO(); 
			
			//2. HotelsDAO 객체의 getHotelsList(dto) 
			HotelsDAO dao = new HotelsDAO (); 
			
			//리턴 받을 변수 선언 
			List<HotelsDTO> HotelsList = new ArrayList<>(); 
			
			//HotelsList : DB의 Hotels 테이블의 레코드를 dto 로 저장후 ArrayList 내의 각 방에 저장된 상태 
			HotelsList = dao.getHotelsList(dto); 
			
			//HotelsList 클라이언트 view 페이지로 전송 : Session 변수에 담아서 client 뷰페이지로 전송
			//client 의 session 정보를 가져와서 session 변수에 할당. 
			HttpSession session = request.getSession(); 
			
			//세션에 HotelsList 를 추가
			session.setAttribute("HotelsList", HotelsList); 
			
			//클라이언트 뷰 페이지 
			response.sendRedirect("getHotelsList.jsp"); 
			
			
		}
		
		else if (path.equals("/getHotels.Hotels")) 
		{
			System.out.println("/getHotels.Hotels 요청");
			//로직 처리 
			
			//1. clinet 넘어오는 파라미터 seq 변수의 값을 읽어서 dto에 저장후 dao.getHotels(dto) 
			//http://localhost:8181/JSP_MVC_M2/getHotels.Hotels?seq=5 
			
			String HotelsNumber = request.getParameter("HotelsNumber"); 
			
			//2. dto에 seq 값을 setter 주입 
			HotelsDTO dto = new HotelsDTO(); 
			dto.setHotelsNumber(HotelsNumber);
			
			//3. DAO의 getHotels(dto) 호출후 리턴 값을 받아서 저장 
			HotelsDAO dao = new HotelsDAO(); 
			
			//리턴값을 받을 DTO 선언 
			HotelsDTO Hotels = new HotelsDTO(); 
			Hotels = dao.getHotels(dto); 
			
			//4. 세션 변수에 저장후 뷰 페이지로 전송 , 
			HttpSession session = request.getSession(); 
			
			session.setAttribute("Hotels", Hotels); 
			
			//5. 뷰 페이지 
			response.sendRedirect("getHotels.jsp"); 
			
			
		}
		
		else if (path.equals("/updateHotels.Hotels")) 
		{
			System.out.println("/updateHotels.Hotels 요청");
			//로직 처리 
			//1. 클라이언트의 파라미터의 변수를 받아서 새로운 변수에 저장 
			String HotelsNumber = request.getParameter("Hotelsnumber"); 
			String HotelsName = request.getParameter("HotelsName");
			String HotelsAddress = request.getParameter("HotelsAddress");
			
			// 변수값 출력 :
			/*
			System.out.println(title);
			System.out.println(write);
			System.out.println(content);
			System.out.println(seq);
			*/ 
			
			//2. 변수를 HotelsDTO에 setter 주입 
			HotelsDTO dto = new HotelsDTO(); 
			dto.setHotelsNumber(HotelsNumber);
			dto.setHotelsName(HotelsName);
			dto.setHotelsAddress(HotelsAddress);

			
			//3. HotelsDAO 에 updateHotels(dto)  
			HotelsDAO dao = new HotelsDAO (); 
			dao.updateHotels(dto); 
			
			//4. 뷰 페이지로 이동 ( 업데이트 후 리스트 페이지로 이동  
			response.sendRedirect("getHotelsList.Hotels");
					
		}
		
		else if (path.equals("/deleteHotels.Hotels")) 
		{
			System.out.println("/deleteHotels.Hotels 요청");
			//로직 처리 
			
			//1. 클라이언트의 파라미터 변수의 값 할당 : seq 
			String HotelsNumber = request.getParameter("HotelsNumber");
			
			//2. 변수의 값을 HotelsDTO에 주입 
			HotelsDTO dto = new HotelsDTO(); 
			dto.setHotelsNumber(HotelsNumber); 
			
			//3. HotelsDAO의 메소드 호출 : deleteHotels(dto) 
			HotelsDAO dao = new HotelsDAO(); 
			
			dao.deleteHotels(dto); 
			
			//4. 뷰 페이지 이동 
			response.sendRedirect("getHotelsList.Hotels"); 

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
