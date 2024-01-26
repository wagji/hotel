package test;

import java.util.ArrayList;
import java.util.List;

import hotels.HotelsDAO;
import hotels.HotelsDTO;

public class Hotels_Test 
{

	public static void main(String[] args) 
	{
		//1. DTO 객체를 생성후 값 입력 
		HotelsDTO dto = new HotelsDTO(); 
				
		// dto 에 setter를 사용해서 값을 입력 
		dto.setHotelsNumber("번호1");
		dto.setHotelsName("이름1");
		dto.setHotelsAddress("주소1");

		//2. DAO 객체에 insertHotels(dto) 
		HotelsDAO dao = new HotelsDAO(); 
		dao.insertHotels(dto);
				
				
				
		//1. DTO 객체를 생성후 값 입력 
		HotelsDTO dto1 = new HotelsDTO(); 
						
		// dto 에 setter를 사용해서 값을 입력 
		dto1.setHotelsNumber("번호2");
		dto1.setHotelsName("이름2");
		dto1.setHotelsAddress("주소2");
				
		//2. DAO 객체에 insertHotels(dto) 
		HotelsDAO dao1 = new HotelsDAO();		
		dao1.insertHotels(dto1);

		
		System.out.println("====================================================");
		
		
		// HotelsDAO의 getHotelsList(dto) 메소드 테스트 
		
		// 1. HotelsDTO 객체 생성
		HotelsDTO dto2 = new HotelsDTO(); 
		
		// 2. HotelsDAO 객체 생성 후 메소드 호출 
		HotelsDAO dao2 = new HotelsDAO();
		
		//ArrayList 선언 : <HotelsDTO> 객체가 각 방에 들어 있음. 
		List<HotelsDTO> HotelsList = new ArrayList<>();
		
		//HotelsList DB의 각 레코드를 DTO에 담아서 저장 
		HotelsList = dao2.getHotelsList(dto2); 
		
		//ArrayList : HotelsList 의 각방의 HotelsDTO 객체를 순환하면서 객체를 출력 
		// FOR 문을 사용해서 출력 
		System.out.println("===========FOR 문으로 출력 ===========");
		for (int i = 0 ; i < HotelsList.size(); i++) {
			System.out.println(HotelsList.get(i));
		}
		
		System.out.println("===========Enhanced For 문으로 출력 =====================");
		for (HotelsDTO b : HotelsList) 
		{   // HotelsList : ArrayList 의 각방의 저장된것을 b 변수로 끄집어내서 출력
			System.out.println(b);
		}
		
		
		System.out.println("====================================================");
		
		//1.  HotelsDTO 에 title, write, content, seq 값을 
		HotelsDTO dto4 = new HotelsDTO(); 
		dto4.setHotelsName("이름3");
		dto4.setHotelsAddress("주소3");
		dto4.setHotelsNumber("번호2");
		
		//2. HotelsDAO의 updateHotels(dto) 호출 
		HotelsDAO dao4 = new HotelsDAO(); 
		
		dao4.updateHotels(dto4);
		
		
		System.out.println("====================================================");
		
		//1. dto 에 조회할 id 값만 할당후 dao.getHotels(dto) 
		HotelsDTO dto3 = new HotelsDTO(); 
		dto3.setHotelsNumber("번호2");
		
		//2. dao 메소드 호출 getHotels(dto) 
		HotelsDAO dao3 = new HotelsDAO(); 
		
		//리턴으로 돌려 받는 변수 선언 
		HotelsDTO Hotels = new HotelsDTO(); 
		
		Hotels = dao3.getHotels(dto3); 
		
		
		System.out.println(Hotels);
		
		
		System.out.println("====================================================");

		
		//1. HotelsDTO 에 id 의 값을 할당 
		HotelsDTO dto5 = new HotelsDTO();
		dto5.setHotelsNumber("번호1");
		
		//2. HotelsDAO 의 deleteHotels(dto)  
		HotelsDAO dao5 = new HotelsDAO(); 
		dao5.deleteHotels(dto5);
		
		//1. HotelsDTO 에 id 의 값을 할당 
		HotelsDTO dto6 = new HotelsDTO();
		dto6.setHotelsNumber("번호2");
		
		//2. HotelsDAO 의 deleteHotels(dto)  
		HotelsDAO dao6 = new HotelsDAO(); 
		dao6.deleteHotels(dto6);
		
		System.out.println("====================================================");

	}

}
