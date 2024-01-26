package test;

import java.util.ArrayList;
import java.util.List;

import Airplan.AirplanDAO;
import Airplan.AirplanDTO;

public class Airplan_Test 
{
	public static void main(String[] args) 
	{
		//1. DTO 객체를 생성후 값 입력 
		AirplanDTO dto = new AirplanDTO(); 
						
		// dto 에 setter를 사용해서 값을 입력 
		dto.setReservationNumber("예약번호1");
		dto.setSerialNumber("번호1");
		dto.setCustomerNumber("이름1");
		dto.setNumberOfPeople(1);
		dto.setPaymentMethod("결재 종류1");

		//2. DAO 객체에 insertAirplan(dto) 
		AirplanDAO dao = new AirplanDAO(); 
		dao.insertAirplan(dto);
						
						
						
		//1. DTO 객체를 생성후 값 입력 
		AirplanDTO dto1 = new AirplanDTO(); 
								
		// dto 에 setter를 사용해서 값을 입력 
		dto1.setReservationNumber("예약번호2");
		dto1.setSerialNumber("번호2");
		dto1.setCustomerNumber("이름2");
		dto1.setNumberOfPeople(2);
		dto1.setPaymentMethod("결재 종류2");
						
		//2. DAO 객체에 insertAirplan(dto) 
		AirplanDAO dao1 = new AirplanDAO();		
		dao1.insertAirplan(dto1);

				
		System.out.println("====================================================");
				
				
		// AirplanDAO의 getAirplanList(dto) 메소드 테스트 
				
		// 1. AirplanDTO 객체 생성
		AirplanDTO dto2 = new AirplanDTO(); 
				
		// 2. AirplanDAO 객체 생성 후 메소드 호출 
		AirplanDAO dao2 = new AirplanDAO();
				
		//ArrayList 선언 : <AirplanDTO> 객체가 각 방에 들어 있음. 
		List<AirplanDTO> AirplanList = new ArrayList<>();
				
		//AirplanList DB의 각 레코드를 DTO에 담아서 저장 
		AirplanList = dao2.getAirplanList(dto2); 
				
		//ArrayList : AirplanList 의 각방의 AirplanDTO 객체를 순환하면서 객체를 출력 
		// FOR 문을 사용해서 출력 
		System.out.println("===========FOR 문으로 출력 ===========");
		for (int i = 0 ; i < AirplanList.size(); i++) 
		{
			System.out.println(AirplanList.get(i));
		}
				
		System.out.println("===========Enhanced For 문으로 출력 =====================");
		for (AirplanDTO b : AirplanList) 
		{   // AirplanList : ArrayList 의 각방의 저장된것을 b 변수로 끄집어내서 출력
			System.out.println(b);
		}
				
				
		System.out.println("====================================================");
				
		//1.  AirplanDTO 에 title, write, content, seq 값을 
		AirplanDTO dto4 = new AirplanDTO();
		dto4.setSerialNumber("번호3");
		dto4.setCustomerNumber("이름3");
		dto4.setNumberOfPeople(3);
		dto4.setPaymentMethod("결재 종류2");
		dto4.setReservationNumber("예약번호2");
				
		//2. AirplanDAO의 updateAirplan(dto) 호출 
		AirplanDAO dao4 = new AirplanDAO(); 
				
		dao4.updateAirplan(dto4);
				
		System.out.println("====================================================");
				
		//1. dto 에 조회할 id 값만 할당후 dao.getAirplan(dto) 
		AirplanDTO dto3 = new AirplanDTO(); 
		dto3.setReservationNumber("예약번호2");
				
		//2. dao 메소드 호출 getAirplan(dto) 
		AirplanDAO dao3 = new AirplanDAO(); 
				
		//리턴으로 돌려 받는 변수 선언 
		AirplanDTO Airplan = new AirplanDTO(); 
				
		Airplan = dao3.getAirplan(dto3); 
				
				
		System.out.println(Airplan);
		
		System.out.println("====================================================");
				
		//1. AirplanDTO 에 id 의 값을 할당 
		AirplanDTO dto5 = new AirplanDTO();
		dto5.setReservationNumber("예약번호1");
				
		//2. AirplanDAO 의 deleteAirplan(dto)  
		AirplanDAO dao5 = new AirplanDAO(); 
		dao5.deleteAirplan(dto5);
				
		//1. AirplanDTO 에 id 의 값을 할당 
		AirplanDTO dto6 = new AirplanDTO();
		dto6.setReservationNumber("예약번호2");
				
		//2. AirplanDAO 의 deleteAirplan(dto)  
		AirplanDAO dao6 = new AirplanDAO(); 
		dao6.deleteAirplan(dto6);
				
		System.out.println("====================================================");
			
	}
}
