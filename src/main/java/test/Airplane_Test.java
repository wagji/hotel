package test;

import java.util.ArrayList;
import java.util.List;

import Airplane.AirplaneDAO;
import Airplane.AirplaneDTO;

public class Airplane_Test 
{

	public static void main(String[] args) 
	{
		//1. DTO 객체를 생성후 값 입력 
		AirplaneDTO dto = new AirplaneDTO(); 
								
		// dto 에 setter를 사용해서 값을 입력 
		dto.setSerialNumber("번호1");
		dto.setAirplaneName("비행기 이름1");
		dto.setCapacity(1);
		dto.setDepartureCity("출발 도시1");
		dto.setDestinationCity("목적지 도시1");

		//2. DAO 객체에 insertAirplane(dto) 
		AirplaneDAO dao = new AirplaneDAO(); 
		dao.insertAirplane(dto);
								
				
		//1. DTO 객체를 생성후 값 입력 
		AirplaneDTO dto1 = new AirplaneDTO(); 
										
		// dto 에 setter를 사용해서 값을 입력 
		dto1.setSerialNumber("번호2");
		dto1.setAirplaneName("비행기 이름2");
		dto1.setCapacity(2);
		dto1.setDepartureCity("출발 도시2");
		dto1.setDestinationCity("목적지 도시2");
								
		//2. DAO 객체에 insertAirplane(dto) 
		AirplaneDAO dao1 = new AirplaneDAO();		
		dao1.insertAirplane(dto1);

						
		System.out.println("====================================================");
						
						
		// AirplaneDAO의 getAirplaneList(dto) 메소드 테스트 
						
		// 1. AirplaneDTO 객체 생성
		AirplaneDTO dto2 = new AirplaneDTO(); 
						
		// 2. AirplaneDAO 객체 생성 후 메소드 호출 
		AirplaneDAO dao2 = new AirplaneDAO();
						
		//ArrayList 선언 : <AirplaneDTO> 객체가 각 방에 들어 있음. 
		List<AirplaneDTO> AirplaneList = new ArrayList<>();
						
		//AirplaneList DB의 각 레코드를 DTO에 담아서 저장 
		AirplaneList = dao2.getAirplaneList(dto2); 
						
		//ArrayList : AirplaneList 의 각방의 AirplaneDTO 객체를 순환하면서 객체를 출력 
		// FOR 문을 사용해서 출력 
		System.out.println("===========FOR 문으로 출력 ===========");
		for (int i = 0 ; i < AirplaneList.size(); i++) 
		{
			System.out.println(AirplaneList.get(i));
		}
						
		System.out.println("===========Enhanced For 문으로 출력 =====================");
		for (AirplaneDTO b : AirplaneList) 
		{   // AirplaneList : ArrayList 의 각방의 저장된것을 b 변수로 끄집어내서 출력
			System.out.println(b);
		}
						
						
		System.out.println("====================================================");
						
		//1.  AirplaneDTO 에 title, write, content, seq 값을 
		AirplaneDTO dto4 = new AirplaneDTO();
		dto4.setSerialNumber("번호2");
		dto4.setAirplaneName("비행기 이름3");
		dto4.setCapacity(3);
		dto4.setDepartureCity("출발 도시3");
		dto4.setDestinationCity("목적지 도시3");
						
		//2. AirplaneDAO의 updateAirplane(dto) 호출 
		AirplaneDAO dao4 = new AirplaneDAO(); 
						
		dao4.updateAirplane(dto4);
						
		System.out.println("====================================================");
						
		//1. dto 에 조회할 id 값만 할당후 dao.getAirplane(dto) 
		AirplaneDTO dto3 = new AirplaneDTO(); 
		dto3.setSerialNumber("번호2");
						
		//2. dao 메소드 호출 getAirplane(dto) 
		AirplaneDAO dao3 = new AirplaneDAO(); 
						
		//리턴으로 돌려 받는 변수 선언 
		AirplaneDTO Airplane = new AirplaneDTO(); 
						
		Airplane = dao3.getAirplane(dto3); 
						
						
		System.out.println(Airplane);
				
		System.out.println("====================================================");
						
		//1. AirplaneDTO 에 id 의 값을 할당 
		AirplaneDTO dto5 = new AirplaneDTO();
		dto5.setSerialNumber("번호1");
						
		//2. AirplaneDAO 의 deleteAirplane(dto)  
		AirplaneDAO dao5 = new AirplaneDAO(); 
		dao5.deleteAirplane(dto5);
						
		//1. AirplaneDTO 에 id 의 값을 할당 
		AirplaneDTO dto6 = new AirplaneDTO();
		dto6.setSerialNumber("번호2");
						
		//2. AirplaneDAO 의 deleteAirplane(dto)  
		AirplaneDAO dao6 = new AirplaneDAO(); 
		dao6.deleteAirplane(dto6);
						
		System.out.println("====================================================");
				
	}

}
