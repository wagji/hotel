package test;

import java.util.ArrayList;
import java.util.List;

import ReservationHistory.ReservationHistoryDAO;
import ReservationHistory.ReservationHistoryDTO;

public class ReservationHistory_Test 
{

	public static void main(String[] args) 
	{
		//1. DTO 객체를 생성후 값 입력 
		ReservationHistoryDTO dto = new ReservationHistoryDTO(); 
				
		// dto 에 setter를 사용해서 값을 입력 
		dto.setReservationNumber("예약번호1");
		dto.setSerialNumber("번호1");
		dto.setCustomerNumber("이름1");
		dto.setNumberOfPeople(1);
		dto.setPaymentMethod("결재 종류1");

		//2. DAO 객체에 insertReservationHistory(dto) 
		ReservationHistoryDAO dao = new ReservationHistoryDAO(); 
		dao.insertReservationHistory(dto);
				
				
				
		//1. DTO 객체를 생성후 값 입력 
		ReservationHistoryDTO dto1 = new ReservationHistoryDTO(); 
						
		// dto 에 setter를 사용해서 값을 입력 
		dto1.setReservationNumber("예약번호2");
		dto1.setSerialNumber("번호2");
		dto1.setCustomerNumber("이름2");
		dto1.setNumberOfPeople(2);
		dto1.setPaymentMethod("결재 종류2");
				
		//2. DAO 객체에 insertReservationHistory(dto) 
		ReservationHistoryDAO dao1 = new ReservationHistoryDAO();		
		dao1.insertReservationHistory(dto1);

		
		System.out.println("====================================================");
		
		
		// ReservationHistoryDAO의 getReservationHistoryList(dto) 메소드 테스트 
		
		// 1. ReservationHistoryDTO 객체 생성
		ReservationHistoryDTO dto2 = new ReservationHistoryDTO(); 
		
		// 2. ReservationHistoryDAO 객체 생성 후 메소드 호출 
		ReservationHistoryDAO dao2 = new ReservationHistoryDAO();
		
		//ArrayList 선언 : <ReservationHistoryDTO> 객체가 각 방에 들어 있음. 
		List<ReservationHistoryDTO> ReservationHistoryList = new ArrayList<>();
		
		//ReservationHistoryList DB의 각 레코드를 DTO에 담아서 저장 
		ReservationHistoryList = dao2.getReservationHistoryList(dto2); 
		
		//ArrayList : ReservationHistoryList 의 각방의 ReservationHistoryDTO 객체를 순환하면서 객체를 출력 
		// FOR 문을 사용해서 출력 
		System.out.println("===========FOR 문으로 출력 ===========");
		for (int i = 0 ; i < ReservationHistoryList.size(); i++) {
			System.out.println(ReservationHistoryList.get(i));
		}
		
		System.out.println("===========Enhanced For 문으로 출력 =====================");
		for (ReservationHistoryDTO b : ReservationHistoryList) 
		{   // ReservationHistoryList : ArrayList 의 각방의 저장된것을 b 변수로 끄집어내서 출력
			System.out.println(b);
		}
		
		
		System.out.println("====================================================");
		
		//1.  ReservationHistoryDTO 에 title, write, content, seq 값을 
		ReservationHistoryDTO dto4 = new ReservationHistoryDTO();
		dto4.setSerialNumber("번호3");
		dto4.setCustomerNumber("이름3");
		dto4.setNumberOfPeople(3);
		dto4.setPaymentMethod("결재 종류2");
		dto4.setReservationNumber("예약번호2");
		
		//2. ReservationHistoryDAO의 updateReservationHistory(dto) 호출 
		ReservationHistoryDAO dao4 = new ReservationHistoryDAO(); 
		
		dao4.updateReservationHistory(dto4);
		
		System.out.println("====================================================");
		
		//1. dto 에 조회할 id 값만 할당후 dao.getReservationHistory(dto) 
		ReservationHistoryDTO dto3 = new ReservationHistoryDTO(); 
		dto3.setReservationNumber("예약번호2");
		
		//2. dao 메소드 호출 getReservationHistory(dto) 
		ReservationHistoryDAO dao3 = new ReservationHistoryDAO(); 
		
		//리턴으로 돌려 받는 변수 선언 
		ReservationHistoryDTO ReservationHistory = new ReservationHistoryDTO(); 
		
		ReservationHistory = dao3.getReservationHistory(dto3); 
		
		
		System.out.println(ReservationHistory);
		
		System.out.println("====================================================");
		
		//1. ReservationHistoryDTO 에 id 의 값을 할당 
		ReservationHistoryDTO dto5 = new ReservationHistoryDTO();
		dto5.setReservationNumber("예약번호1");
		
		//2. ReservationHistoryDAO 의 deleteReservationHistory(dto)  
		ReservationHistoryDAO dao5 = new ReservationHistoryDAO(); 
		dao5.deleteReservationHistory(dto5);
		
		//1. ReservationHistoryDTO 에 id 의 값을 할당 
		ReservationHistoryDTO dto6 = new ReservationHistoryDTO();
		dto6.setReservationNumber("예약번호2");
		
		//2. ReservationHistoryDAO 의 deleteReservationHistory(dto)  
		ReservationHistoryDAO dao6 = new ReservationHistoryDAO(); 
		dao6.deleteReservationHistory(dto6);
		
		System.out.println("====================================================");
	}

}
