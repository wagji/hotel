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
import customer.CustomerDTO;
import customer.CustomerDAO;


//http://localhost:8081/HOTEL/*.customer
@WebServlet ("*.customer")
public class CustomerController extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerController() 
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
		
		System.out.println(" .customer 요청을 처리하는 controller 입니다. ");
		
		//	URL : http://localhost:8181/JSP_MVC_M2/my.customer
		// 	URI : /JSP_MVC_M2/my.customer
		
		String uri = request.getRequestURI(); 
		System.out.println(uri);
		
		// 
		String path = uri.substring(uri.lastIndexOf("/")); 
		System.out.println(path);
		System.out.println("====================");
		
		
		if (path.equals("/insertCustomer.customer")) 
		{
			System.out.println("/insertCustomer.customer 요청");
			//로직 처리 
			
			//1. 클라이언트의 넘어오는 변수가 잘 들어 오는지 확인  (클라이언트 요청) 
			String CustomerNumber = request.getParameter("CustomerNumber"); 
			String CustomerName = request.getParameter("CustomerName");
			String PhoneNumber = request.getParameter("PhoneNumber");
			String ResidentRegistrationNumber = request.getParameter("ResidentRegistrationNumber");
			String Email = request.getParameter("Email");
			String Password = request.getParameter("Password");
			String Address = request.getParameter("Address");
 
			/*
			System.out.println("title : " + title);
			System.out.println("write : " + write);
			System.out.println("content : " + content);
			*/ 
			
			//2. 클라이언트에서 넘어오는 변수의 값을 DTO에 Setter 주입 
			CustomerDTO dto = new CustomerDTO(); 
			dto.setCustomerNumber(CustomerNumber);
			dto.setCustomerName(CustomerName);
			dto.setPhoneNumber(PhoneNumber);
			dto.setResidentRegistrationNumber(ResidentRegistrationNumber);
			dto.setEmail(Email);
			dto.setPassword(Password);
			dto.setAddress(Address);
			
			//3. DAO 에 insertCustomer (dto)
			CustomerDAO dao = new CustomerDAO(); 
			dao.insertCustomer(dto); 			//insert 성공 
			
			//System.out.println("DB 저장 성공 ");
			
			//비즈니스 로직 완료 : DTO , DAO   
			
			// 4. 뷰 페이지 전송 : 값을 insertCustomer 후 DB의 전체 레코드를 출력 페이지로 이동 
				// 클라이언트가 getCustomerList.customer 요청을 새롭게 요청함. 
				//주의 : http://localhost:8181/getCustomerList.customer   <== 오류 
				    //  http://localhost:8181/JSP_MVC_M2/getCustomerList.customer   <== 정상  
			response.sendRedirect("getCustomerList.customer"); 
			
					
		}
		
		else if (path.equals("/getCustomerList.customer")) 
		{		// DB의 레코드를 출력 하는 페이지 
			System.out.println("/getCustomerList.customer 요청");
			//로직 처리 
			
			//1. CustomerDTO 객체 생성 
			CustomerDTO dto = new CustomerDTO(); 
			
			//2. CustomerDAO 객체의 getCustomerList(dto) 
			CustomerDAO dao = new CustomerDAO (); 
			
			//리턴 받을 변수 선언 
			List<CustomerDTO> CustomerList = new ArrayList<>(); 
			
			//CustomerList : DB의 Customer 테이블의 레코드를 dto 로 저장후 ArrayList 내의 각 방에 저장된 상태 
			CustomerList = dao.getCustomerList(dto); 
			
			//CustomerList 클라이언트 view 페이지로 전송 : Session 변수에 담아서 client 뷰페이지로 전송
			//client 의 session 정보를 가져와서 session 변수에 할당. 
			HttpSession session = request.getSession(); 
			
			//세션에 CustomerList 를 추가
			session.setAttribute("CustomerList", CustomerList); 
			
			//클라이언트 뷰 페이지 
			response.sendRedirect("getCustomerList.jsp"); 
			
			
		}
		
		else if (path.equals("/getCustomer.customer")) 
		{
			System.out.println("/getCustomer.customer 요청");
			//로직 처리 
			
			//1. clinet 넘어오는 파라미터 seq 변수의 값을 읽어서 dto에 저장후 dao.getCustomer(dto) 
			//http://localhost:8181/JSP_MVC_M2/getCustomer.customer?seq=5 
			
			String CustomerNumber = request.getParameter("Customernumber"); 
			
			//2. dto에 seq 값을 setter 주입 
			CustomerDTO dto = new CustomerDTO(); 
			dto.setCustomerNumber(CustomerNumber);
			
			//3. DAO의 getCustomer(dto) 호출후 리턴 값을 받아서 저장 
			CustomerDAO dao = new CustomerDAO(); 
			
			//리턴값을 받을 DTO 선언 
			CustomerDTO Customer = new CustomerDTO(); 
			Customer = dao.getCustomer(dto); 
			
			//4. 세션 변수에 저장후 뷰 페이지로 전송 , 
			HttpSession session = request.getSession(); 
			
			session.setAttribute("Customer", Customer); 
			
			//5. 뷰 페이지 
			response.sendRedirect("getCustomer.jsp"); 
			
			
		}
		
		else if (path.equals("/updateCustomer.customer")) 
		{
			System.out.println("/updateCustomer.customer 요청");
			//로직 처리 
			//1. 클라이언트의 파라미터의 변수를 받아서 새로운 변수에 저장 
			String CustomerNumber = request.getParameter("Customernumber"); 
			String CustomerName = request.getParameter("CustomerName");
			String PhoneNumber = request.getParameter("PhoneNumber");
			String ResidentRegistrationNumber = request.getParameter("ResidentRegistrationNumber");
			String Email = request.getParameter("Email");
			String Password = request.getParameter("Password");
			String Address = request.getParameter("Address");
			
			// 변수값 출력 :
			/*
			System.out.println(title);
			System.out.println(write);
			System.out.println(content);
			System.out.println(seq);
			*/ 
			
			//2. 변수를 CustomerDTO에 setter 주입 
			CustomerDTO dto = new CustomerDTO(); 
			dto.setCustomerNumber(CustomerNumber);
			dto.setCustomerName(CustomerName);
			dto.setPhoneNumber(PhoneNumber);
			dto.setResidentRegistrationNumber(ResidentRegistrationNumber);
			dto.setEmail(Email);
			dto.setPassword(Password);
			dto.setAddress(Address);
			
			//3. CustomerDAO 에 updateCustomer(dto)  
			CustomerDAO dao = new CustomerDAO (); 
			dao.updateCustomer(dto); 
			
			//4. 뷰 페이지로 이동 ( 업데이트 후 리스트 페이지로 이동  
			response.sendRedirect("getCustomerList.customer");
					
		}
		
		else if (path.equals("/deleteCustomer.customer")) 
		{
			System.out.println("/deleteCustomer.customer 요청");
			//로직 처리 
			
			//1. 클라이언트의 파라미터 변수의 값 할당 : seq 
			String CustomerNumber = request.getParameter("CustomerNumber");
			
			//2. 변수의 값을 CustomerDTO에 주입 
			CustomerDTO dto = new CustomerDTO(); 
			dto.setCustomerNumber(CustomerNumber); 
			
			//3. CustomerDAO의 메소드 호출 : deleteCustomer(dto) 
			CustomerDAO dao = new CustomerDAO(); 
			
			dao.deleteCustomer(dto); 
			
			//4. 뷰 페이지 이동 
			response.sendRedirect("getCustomerList.customer"); 

		}
		
		else if (path.equals("/login.customer")) 
		{
			System.out.println("login.customer 요청 처리");
			
			//1. client에서 넘긴 파라미터 변수값을 받아서 변수에 저장 
			String CustomerNumber = request.getParameter("CustomerNumber"); 
			String password = request.getParameter("password"); 
			
			//2. 넘겨받은 값을 MembersDTO에 저장
			CustomerDTO dto = new CustomerDTO(); 
			dto.setCustomerNumber(CustomerNumber); 
			dto.setPassword(password); 
			
			//3. MembersDAO의 login(dto) 
			CustomerDAO dao = new CustomerDAO(); 
			
			//리턴 받을 MembersDTO 선언 
			CustomerDTO user = new CustomerDTO(); 
			
			user = dao.login(dto); 
			//user 가 null 경우 : 인증 실패 , 그렇지 않을 경우 인증 성공 
			
			
			if (user == null) 
			{	//인증 실패
				System.out.println("인증 실패 했습니다.");
				response.sendRedirect("insertCustomer.jsp"); 
				
			}
			
			
			else 
			{ // 인증 성공 
				// 세션의 변수의 값을 할당 하고 view 페이지로 전송 
				System.out.println("인증 성공 했습니다. ");
				HttpSession session = request.getSession(); 
				session.setAttribute("CustomerNumber", user.getCustomerNumber());
				session.setAttribute("CustomerName", user.getCustomerName()); 
				
				response.sendRedirect("index.jsp"); 
			}
		}
		
		
		else if (path.equals("/logout.customer")) 
		{
			System.out.println("/logout.customer 요청 처리 ");
			
			//1 사용자 세션 변수의 모든 값을 삭제함. 
			HttpSession session = request.getSession(); 
			
			//세션 변수에 담긴 모든 변수의 값을 삭제 
			session.invalidate(); 
			
			//뷰페이지로 이동 (처음 페이지로 이동)
			response.sendRedirect("http://localhost:8081/HOTEL"); 
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
