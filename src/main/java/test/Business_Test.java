package test;

import java.util.ArrayList;
import java.util.List;

import BusinessInformation.BusinessInformationDAO;
import BusinessInformation.BusinessInformationDTO;

public class Business_Test {

	public static void main(String[] args) 
	{
			//1. DTO 객체를 생성후 값 입력 
			BusinessInformationDTO dto = new BusinessInformationDTO(); 
					
			// dto 에 setter를 사용해서 값을 입력 
			dto.setSerialNumber("번호1");
			dto.setBusinessAddress("이름1");
			dto.setRoomType("방 종류1");
			dto.setRoomCount(1);
			dto.setFee(1);

			//2. DAO 객체에 insertBusinessInformation(dto) 
			BusinessInformationDAO dao = new BusinessInformationDAO(); 
			dao.insertBusinessInformation(dto);
					
					
					
			//1. DTO 객체를 생성후 값 입력 
			BusinessInformationDTO dto1 = new BusinessInformationDTO(); 
							
			// dto 에 setter를 사용해서 값을 입력 
			dto1.setSerialNumber("번호2");
			dto1.setBusinessAddress("이름2");
			dto1.setRoomType("방 종류2");
			dto1.setRoomCount(2);
			dto1.setFee(2);
					
			//2. DAO 객체에 insertBusinessInformation(dto) 
			BusinessInformationDAO dao1 = new BusinessInformationDAO();		
			dao1.insertBusinessInformation(dto1);

			
			System.out.println("====================================================");
			
			
			// BusinessInformationDAO의 getBusinessInformationList(dto) 메소드 테스트 
			
			// 1. BusinessInformationDTO 객체 생성
			BusinessInformationDTO dto2 = new BusinessInformationDTO(); 
			
			// 2. BusinessInformationDAO 객체 생성 후 메소드 호출 
			BusinessInformationDAO dao2 = new BusinessInformationDAO();
			
			//ArrayList 선언 : <BusinessInformationDTO> 객체가 각 방에 들어 있음. 
			List<BusinessInformationDTO> BusinessInformationList = new ArrayList<>();
			
			//BusinessInformationList DB의 각 레코드를 DTO에 담아서 저장 
			BusinessInformationList = dao2.getBusinessInformationList(dto2); 
			
			//ArrayList : BusinessInformationList 의 각방의 BusinessInformationDTO 객체를 순환하면서 객체를 출력 
			// FOR 문을 사용해서 출력 
			System.out.println("===========FOR 문으로 출력 ===========");
			for (int i = 0 ; i < BusinessInformationList.size(); i++) {
				System.out.println(BusinessInformationList.get(i));
			}
			
			System.out.println("===========Enhanced For 문으로 출력 =====================");
			for (BusinessInformationDTO b : BusinessInformationList) 
			{   // BusinessInformationList : ArrayList 의 각방의 저장된것을 b 변수로 끄집어내서 출력
				System.out.println(b);
			}
			
			
			System.out.println("====================================================");
			
			//1.  BusinessInformationDTO 에 title, write, content, seq 값을 
			BusinessInformationDTO dto4 = new BusinessInformationDTO();
			dto4.setBusinessAddress("이름3");
			dto4.setRoomType("방 종류3");
			dto4.setRoomCount(3);
			dto4.setFee(3);
			dto4.setSerialNumber("번호2");
			
			//2. BusinessInformationDAO의 updateBusinessInformation(dto) 호출 
			BusinessInformationDAO dao4 = new BusinessInformationDAO(); 
			
			dao4.updateBusinessInformation(dto4);
			
			System.out.println("====================================================");
			
			//1. dto 에 조회할 id 값만 할당후 dao.getBusinessInformation(dto) 
			BusinessInformationDTO dto3 = new BusinessInformationDTO(); 
			dto3.setSerialNumber("번호2");
			
			//2. dao 메소드 호출 getBusinessInformation(dto) 
			BusinessInformationDAO dao3 = new BusinessInformationDAO(); 
			
			//리턴으로 돌려 받는 변수 선언 
			BusinessInformationDTO BusinessInformation = new BusinessInformationDTO(); 
			
			BusinessInformation = dao3.getBusinessInformation(dto3); 
			
			
			System.out.println(BusinessInformation);
			
			System.out.println("====================================================");
			
			//1. BusinessInformationDTO 에 id 의 값을 할당 
			BusinessInformationDTO dto5 = new BusinessInformationDTO();
			dto5.setSerialNumber("번호1");
			
			//2. BusinessInformationDAO 의 deleteBusinessInformation(dto)  
			BusinessInformationDAO dao5 = new BusinessInformationDAO(); 
			dao5.deleteBusinessInformation(dto5);
			
			//1. BusinessInformationDTO 에 id 의 값을 할당 
			BusinessInformationDTO dto6 = new BusinessInformationDTO();
			dto6.setSerialNumber("번호2");
			
			//2. BusinessInformationDAO 의 deleteBusinessInformation(dto)  
			BusinessInformationDAO dao6 = new BusinessInformationDAO(); 
			dao6.deleteBusinessInformation(dto6);
			
			System.out.println("====================================================");
	}
 
}
