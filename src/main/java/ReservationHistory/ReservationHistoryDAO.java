package ReservationHistory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import util.JDBCUtil;

public class ReservationHistoryDAO 
{
	Connection conn = null; 
	PreparedStatement pstmt = null ; 
	ResultSet rs = null ; 
		
	//sql 쿼리를 상수로 지정함.
	// insert 쿼리 
	private final String ReservationHistory_INSERT = 
		"insert into ReservationHistory (ReservationNumber, SerialNumber, CustomerNumber, ReservationStartDate, ReservationEndDate, NumberOfPeople, PaymentMethod) "
		+ "values (?, ?, ?, ?, ?, ?, ?)";
    
	// DB의 ReservationHistoryDAO 테이블의 모든 레코드를 출력 쿼리 : 레코드가 여러개 : dto ==> ArrayList 리턴 
	private final String ReservationHistory_LIST = "select * from ReservationHistory order by ReservationNumber desc" ; 
	
	// DB의 ReservationHistoryDAO 테이블의 글 상세 조회 : 레코드 1개  <== dto
	private final String ReservationHistory_GET = "select * from ReservationHistory where ReservationNumber = ?" ; 
	
	// DB의 ReservationHistoryDAO 테이블의 업데이트 쿼리 
	private final String ReservationHistory_UPDATE = "update ReservationHistory set SerialNumber= ?, CustomerNumber= ?, NumberOfPeople= ?, PaymentMethod= ? where ReservationNumber = ?"; 
	
	// DB의 ReservationHistoryDAO 테이블의 레코드를 삭제 
	private final String ReservationHistory_DELETE = "delete ReservationHistory where ReservationNumber = ?";

	
	//insertReservationHistoryDAO(ReservationHistoryDAODTO dto) 메소드  : 
	public void insertReservationHistory (ReservationHistoryDTO dto) 
	{
		System.out.println("= insertReservationHistory 기능 처리 =");
		
		try 
		{
			
			//conn, pstmt 객체 활성화 
			conn = JDBCUtil.getConnection(); 
			pstmt= conn.prepareStatement(ReservationHistory_INSERT);
			
			//pstmt 객체의 ? 변수의 값 할당. 
			pstmt.setString(1, dto.getReservationNumber());
			pstmt.setString(2, dto.getSerialNumber());
			pstmt.setString(3, dto.getCustomerNumber());
			pstmt.setString(4, dto.getReservationStartDate());
			pstmt.setString(5, dto.getReservationEndDate());
			pstmt.setInt(6, dto.getNumberOfPeople());
			pstmt.setString(7, dto.getPaymentMethod());

			
			//pstmt 를 실행해서 DB에 저장 
			pstmt.executeUpdate(); 
			
			System.out.println("ReservationHistory 테이블에 값이 잘 insert 되었습니다. ");
			
		}
		
		catch (Exception e) 
		{
			System.out.println("ReservationHistory 테이블에 값이 insert에 실패 했습니다. ");
			e.printStackTrace();
		} 
		
		finally 
		{
			//사용한 객체 제거 
			JDBCUtil.close(pstmt, conn);
		}
	}
	
	// ReservationHistoryDAO 테이블의 전체 레코드를 출력 하는 메소드 
	// DB의 레코드 하나를 ReservationHistoryDAODTO에 담는다. 각각의 ReservationHistoryDAODTO 를 ArrayList에 담아서 리턴 
	// rs, pstmt, conn
	public List<ReservationHistoryDTO> getReservationHistoryList(ReservationHistoryDTO dto) 
	{
		//중요 : ArryList 는 While 블락 밖에서 선언
		//      ArryList에 저장되는  ReservationHistoryDAODTO 선언은 while 블락 내부에서 선언 
		
		List<ReservationHistoryDTO> ReservationHistoryList = new ArrayList<>(); 
		
		try 
		{
			conn = JDBCUtil.getConnection(); 	//conn 객체 활성화 : Oracle , XE , HR12 , 1234 
			//ReservationHistoryDAO_LIST = "select * from ReservationHistoryDAO order by seq desc" 
			pstmt = conn.prepareStatement(ReservationHistory_LIST) ; 
			
			// pstmt 를 실행후 rs 에 레코드를 저장 
			rs = pstmt.executeQuery(); 
			
			//System.out.println("DB Select 성공");
			
			// rs의 각 레코드를 ReservationHistoryDAODTO에 저장 ==> 각 각의 DTO를 ArrayList에 저장 
				// if , do ~ while   <===>  while
				//rs.next() : 다음 레코드가 존재하면 true, 커서가 다음레코드로 이동 
				
			while (rs.next()) 
			{
				// ReservationHistoryDAODTO 객체 생성 
				ReservationHistoryDTO ReservationHistory = new ReservationHistoryDTO();
				    // 루프 블락 내에 선언 
				ReservationHistory.setReservationNumber(rs.getString("ReservationNumber"));
				ReservationHistory.setSerialNumber(rs.getString("SerialNumber"));
				ReservationHistory.setCustomerNumber(rs.getString("CustomerNumber"));
				ReservationHistory.setReservationStartDate("ReservationStartDate");
				ReservationHistory.setReservationEndDate("ReservationEndDate");
				ReservationHistory.setNumberOfPeople(rs.getInt("NumberOfPeople"));
				ReservationHistory.setPaymentMethod(rs.getString("PaymentMethod"));


				
				// ReservationHistoryDAOList : ArrayList의 add 메소드를 사용해서 ReservationHistoryDAODTO를 저장함. 
				ReservationHistoryList.add(ReservationHistory); 	
			}
			
					
		}
		
		catch (Exception e) 
		{
			System.out.println("DB Select 실패");
			e.printStackTrace();     // 실패 할 경우 콘솔에 오류 정보 출력 
		}
		
		finally 
		{
			//사용한 객체 반납 : rs, pstmt, conn 
			JDBCUtil.close(rs, pstmt, conn);
		}
			
		return ReservationHistoryList ; 
		
	}
	
	// 글 상세 조회 : getReservationHistoryDAO(dto) 
	public ReservationHistoryDTO getReservationHistory(ReservationHistoryDTO dto) 
	{
		System.out.println("getReservationHistory 메소드 호출 성공");
		
		
		ReservationHistoryDTO ReservationHistory = new ReservationHistoryDTO(); 
		
		try 
		{
			conn = JDBCUtil.getConnection();
			// ReservationHistoryDAO_GET = "select * from ReservationHistoryDAO where seq = ?"
			pstmt = conn.prepareStatement(ReservationHistory_GET); 
			pstmt.setString(1, dto.getReservationNumber());
			
			// rs : 레코드 1개 
			rs = pstmt.executeQuery(); 
			
			//rs의 값이 존재할때 
			while ( rs.next()) 
			{
				
				ReservationHistory.setReservationNumber(rs.getString("ReservationNumber"));
				ReservationHistory.setSerialNumber(rs.getString("SerialNumber"));
				ReservationHistory.setCustomerNumber(rs.getString("CustomerNumber"));
				ReservationHistory.setReservationStartDate("ReservationStartDate");
				ReservationHistory.setReservationEndDate("ReservationEndDate");
				ReservationHistory.setNumberOfPeople(rs.getInt("NumberOfPeople"));
				ReservationHistory.setPaymentMethod(rs.getString("PaymentMethod"));
				
				System.out.println("RS 의 레코드를 dto 저장 성공 ");
			}
			
		}
		
		catch (Exception e) 
		{
			System.out.println("글 상세조회 실패  ");
			e.printStackTrace();
		}
		
		finally 
		{
			JDBCUtil.close(rs, pstmt, conn);
		}
				
		return  ReservationHistory; 
	}
	
	// 글 수정 메소드 : updateReservationHistoryDAO(dto)
	public void updateReservationHistory(ReservationHistoryDTO dto) 
	{
		System.out.println("updateReservationHistory 메소드 호출됨");
		
		try 
		{
			conn = JDBCUtil.getConnection(); 
			//ReservationHistoryDAO_UPDATE = "update ReservationHistoryDAO set title= ?, write= ? , content = ? where seq = ?"
			pstmt = conn.prepareStatement(ReservationHistory_UPDATE); 
			
			// ? 변수에 값을 할당 
			pstmt.setString(1, dto.getReservationNumber());
			pstmt.setString(2, dto.getSerialNumber());
			pstmt.setString(3, dto.getCustomerNumber());
			pstmt.setString(4, dto.getReservationStartDate());
			pstmt.setString(5, dto.getReservationEndDate());
			pstmt.setInt(6, dto.getNumberOfPeople());
			pstmt.setString(7, dto.getPaymentMethod());
			
			//쿼리를 실행
			pstmt.executeUpdate(); 		//insert, update, delete 구문일때 실행 
			
			System.out.println("DB 업테이트 성공 ");
			
		}
		
		catch (Exception e) 
		{
			System.out.println("DB 업테이트 실패 ");
			e.printStackTrace();
			
		}
		
		finally 
		{
			JDBCUtil.close(pstmt, conn);
		}
		
	}
	
	// 글 삭제 메소드 : deleteReservationHistoryDAO(dto) 
	public void deleteReservationHistory (ReservationHistoryDTO dto) 
	{
		
		try 
		{
			conn = JDBCUtil.getConnection(); 
			// ReservationHistoryDAO_DELETE = "delete ReservationHistoryDAO where seq = ?"
			pstmt = conn.prepareStatement(ReservationHistory_DELETE); 
			
			// ? 변수값 할당. 
			pstmt.setString(1, dto.getReservationNumber());
			
			// 쿼리 실행 
			pstmt.executeUpdate();   // insert, update, delete 
			
			System.out.println("DB의 레코드 삭제 성공");
			
		}
		
		catch (Exception e) 
		{
			System.out.println("DB의 레코드 삭제 실패");
			e.printStackTrace();
			
		}
		
		finally 
		{
			JDBCUtil.close(pstmt, conn);
		}
		
	}
}
