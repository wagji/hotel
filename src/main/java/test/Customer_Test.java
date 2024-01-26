package test;

import java.util.ArrayList;
import java.util.List;
import customer.CustomerDAO;
import customer.CustomerDTO;


public class Customer_Test 
{

	public static void main(String[] args) 
	{
		//1. DTO 객체를 생성후 값 입력 
		CustomerDTO dto = new CustomerDTO(); 
				
		// dto 에 setter를 사용해서 값을 입력 
		dto.setCustomerNumber("번호1");
		dto.setCustomerName("이름1");
		dto.setPhoneNumber("전화번호1");
		dto.setResidentRegistrationNumber("주민등록번호1");
		dto.setEmail("이메일1");
		dto.setPassword("비밀번호1");
		dto.setAddress("주소1");

		//2. DAO 객체에 insertCustomer(dto) 
		CustomerDAO dao = new CustomerDAO(); 
		dao.insertCustomer(dto);
				
				
				
		//1. DTO 객체를 생성후 값 입력 
		CustomerDTO dto1 = new CustomerDTO(); 
						
		// dto 에 setter를 사용해서 값을 입력 
		dto1.setCustomerNumber("번호2");
		dto1.setCustomerName("이름2");
		dto1.setPhoneNumber("전화번호2");
		dto1.setResidentRegistrationNumber("주민등록번호2");
		dto1.setEmail("이메일2");
		dto1.setPassword("비밀번호2");
		dto1.setAddress("주소2");
				
		//2. DAO 객체에 insertCustomer(dto) 
		CustomerDAO dao1 = new CustomerDAO();		
		dao1.insertCustomer(dto1);
		
		System.out.println("====================================================");
		
		// 1. MembersDTO : id, password 
		CustomerDTO dto8 = new CustomerDTO(); 
		dto8.setCustomerNumber("번호2");
		dto8.setPassword("비밀번호2");
		
		// 2. MembersDAO : login (dto)  리턴된 값이 null 이라면 인증 실패, 그렇지 않으면 인증 성공 
		CustomerDAO dao8 = new CustomerDAO(); 
		
		// 리턴 받을 MembersDTO 선언 
		CustomerDTO user = new CustomerDTO(); 
		
		user = dao8.login(dto8); 
		
		if ( user != null) 
		{
			System.out.println("인증 성공함. ");
			System.out.println(user);
		}
		
		else 
		{
			System.out.println("인증 실패. ");
			System.out.println(user);
		}

		
		System.out.println("====================================================");
		
		
		// CustomerDAO의 getCustomerList(dto) 메소드 테스트 
		
		// 1. CustomerDTO 객체 생성
		CustomerDTO dto2 = new CustomerDTO(); 
		
		// 2. CustomerDAO 객체 생성 후 메소드 호출 
		CustomerDAO dao2 = new CustomerDAO();
		
		//ArrayList 선언 : <CustomerDTO> 객체가 각 방에 들어 있음. 
		List<CustomerDTO> CustomerList = new ArrayList<>();
		
		//CustomerList DB의 각 레코드를 DTO에 담아서 저장 
		CustomerList = dao2.getCustomerList(dto2); 
		
		//ArrayList : CustomerList 의 각방의 CustomerDTO 객체를 순환하면서 객체를 출력 
		// FOR 문을 사용해서 출력 
		System.out.println("===========FOR 문으로 출력 ===========");
		for (int i = 0 ; i < CustomerList.size(); i++) {
			System.out.println(CustomerList.get(i));
		}
		
		System.out.println("===========Enhanced For 문으로 출력 =====================");
		for (CustomerDTO b : CustomerList) 
		{   // CustomerList : ArrayList 의 각방의 저장된것을 b 변수로 끄집어내서 출력
			System.out.println(b);
		}
		
		System.out.println("====================================================");
		
		//1.  CustomerDTO 에 title, write, content, seq 값을 
		CustomerDTO dto4 = new CustomerDTO(); 
		dto4.setCustomerNumber("번호2");
		dto4.setCustomerName("이름3");
		dto4.setPhoneNumber("전화번호3");
		dto4.setResidentRegistrationNumber("주민등록번호3");
		dto4.setEmail("이메일3");
		dto4.setPassword("비밀번호3");
		dto4.setAddress("주소3");
		
		//2. CustomerDAO의 updateCustomer(dto) 호출 
		CustomerDAO dao4 = new CustomerDAO(); 
		
		dao4.updateCustomer(dto4);
		
		System.out.println("====================================================");
		
		System.out.println("====================================================");
		
		//1. dto 에 조회할 id 값만 할당후 dao.getCustomer(dto) 
		CustomerDTO dto3 = new CustomerDTO(); 
		dto3.setCustomerNumber("번호2");
		
		//2. dao 메소드 호출 getCustomer(dto) 
		CustomerDAO dao3 = new CustomerDAO(); 
		
		//리턴으로 돌려 받는 변수 선언 
		CustomerDTO Customer = new CustomerDTO(); 
		
		Customer = dao3.getCustomer(dto3); 
		
		
		System.out.println(Customer);
		
		System.out.println("====================================================");
		
		//1. CustomerDTO 에 id 의 값을 할당 
		CustomerDTO dto5 = new CustomerDTO();
		dto5.setCustomerNumber("번호1");
		
		//2. CustomerDAO 의 deleteCustomer(dto)  
		CustomerDAO dao5 = new CustomerDAO(); 
		dao5.deleteCustomer(dto5);
		
		//1. CustomerDTO 에 id 의 값을 할당 
		CustomerDTO dto6 = new CustomerDTO();
		dto6.setCustomerNumber("번호2");
		
		//2. CustomerDAO 의 deleteCustomer(dto)  
		CustomerDAO dao6 = new CustomerDAO(); 
		dao6.deleteCustomer(dto6);
		
		System.out.println("====================================================");
		
		
		
		
	}

}
