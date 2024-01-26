package BusinessInformation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import util.JDBCUtil;

public class BusinessInformationDAO 
{
	Connection conn = null; 
	PreparedStatement pstmt = null ; 
	ResultSet rs = null ; 
		
	//sql 쿼리를 상수로 지정함.
	// insert 쿼리 
	private final String BusinessInformation_INSERT = 
		"insert into BusinessInformation (SerialNumber, BusinessAddress, RoomType, RoomCount, Fee) "
		+ "values (?, ?, ?, ?, ?)";
    
	// DB의 BusinessInformation 테이블의 모든 레코드를 출력 쿼리 : 레코드가 여러개 : dto ==> ArrayList 리턴 
	private final String BusinessInformation_LIST = "select * from BusinessInformation order by SerialNumber desc" ; 
	
	// DB의 BusinessInformation 테이블의 글 상세 조회 : 레코드 1개  <== dto
	private final String BusinessInformation_GET = "select * from BusinessInformation where SerialNumber = ?" ; 
	
	// DB의 BusinessInformation 테이블의 업데이트 쿼리 
	private final String BusinessInformation_UPDATE = "update BusinessInformation set SerialNumber= ?, BusinessAddress= ?, RoomType= ?, RoomCount= ?, Fee= ? where SerialNumber = ?"; 
	
	// DB의 BusinessInformation 테이블의 레코드를 삭제 
	private final String BusinessInformation_DELETE = "delete BusinessInformation where SerialNumber = ?";

	
	//insertBusinessInformation(BusinessInformationDTO dto) 메소드  : 
	public void insertBusinessInformation (BusinessInformationDTO dto) 
	{
		System.out.println("= insertBusinessInformation 기능 처리 =");
		
		try 
		{
			
			//conn, pstmt 객체 활성화 
			conn = JDBCUtil.getConnection(); 
			pstmt= conn.prepareStatement(BusinessInformation_INSERT);
			
			//pstmt 객체의 ? 변수의 값 할당. 
			pstmt.setString(1, dto.getSerialNumber());
			pstmt.setString(2, dto.getBusinessAddress());
			pstmt.setString(3, dto.getRoomType());
			pstmt.setInt(4, dto.getRoomCount());
			pstmt.setInt(5, dto.getFee());

			
			//pstmt 를 실행해서 DB에 저장 
			pstmt.executeUpdate(); 
			
			System.out.println("BusinessInformation 테이블에 값이 잘 insert 되었습니다. ");
			
		}
		
		catch (Exception e) 
		{
			System.out.println("BusinessInformation 테이블에 값이 insert에 실패 했습니다. ");
			e.printStackTrace();
		} 
		
		finally 
		{
			//사용한 객체 제거 
			JDBCUtil.close(pstmt, conn);
		}
	}
	
	// BusinessInformation 테이블의 전체 레코드를 출력 하는 메소드 
	// DB의 레코드 하나를 BusinessInformationDTO에 담는다. 각각의 BusinessInformationDTO 를 ArrayList에 담아서 리턴 
	// rs, pstmt, conn
	public List<BusinessInformationDTO> getBusinessInformationList(BusinessInformationDTO dto) 
	{
		//중요 : ArryList 는 While 블락 밖에서 선언
		//      ArryList에 저장되는  BusinessInformationDTO 선언은 while 블락 내부에서 선언 
		
		List<BusinessInformationDTO> BusinessInformationList = new ArrayList<>(); 
		
		try 
		{
			conn = JDBCUtil.getConnection(); 	//conn 객체 활성화 : Oracle , XE , HR12 , 1234 
			//BusinessInformation_LIST = "select * from BusinessInformation order by seq desc" 
			pstmt = conn.prepareStatement(BusinessInformation_LIST) ; 
			
			// pstmt 를 실행후 rs 에 레코드를 저장 
			rs = pstmt.executeQuery(); 
			
			//System.out.println("DB Select 성공");
			
			// rs의 각 레코드를 BusinessInformationDTO에 저장 ==> 각 각의 DTO를 ArrayList에 저장 
				// if , do ~ while   <===>  while
				//rs.next() : 다음 레코드가 존재하면 true, 커서가 다음레코드로 이동 
				
			while (rs.next()) 
			{
				// BusinessInformationDTO 객체 생성 
				BusinessInformationDTO BusinessInformation = new BusinessInformationDTO();
				    // 루프 블락 내에 선언 
				BusinessInformation.setSerialNumber(rs.getString("SerialNumber"));
				BusinessInformation.setBusinessAddress(rs.getString("BusinessAddress"));
				BusinessInformation.setRoomType(rs.getString("RoomType"));
				BusinessInformation.setRoomCount(rs.getInt("RoomCount"));
				BusinessInformation.setFee(rs.getInt("Fee"));

				
				// BusinessInformationList : ArrayList의 add 메소드를 사용해서 BusinessInformationDTO를 저장함. 
				BusinessInformationList.add(BusinessInformation); 	
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
			
		return BusinessInformationList ; 
		
	}
	
	// 글 상세 조회 : getBusinessInformation(dto) 
	public BusinessInformationDTO getBusinessInformation(BusinessInformationDTO dto) 
	{
		System.out.println("getBusinessInformation 메소드 호출 성공");
		
		
		BusinessInformationDTO BusinessInformation = new BusinessInformationDTO(); 
		
		try 
		{
			conn = JDBCUtil.getConnection();
			// BusinessInformation_GET = "select * from BusinessInformation where seq = ?"
			pstmt = conn.prepareStatement(BusinessInformation_GET); 
			pstmt.setString(1, dto.getSerialNumber());
			
			// rs : 레코드 1개 
			rs = pstmt.executeQuery(); 
			
			//rs의 값이 존재할때 
			while ( rs.next()) 
			{
				
				BusinessInformation.setSerialNumber(rs.getString("SerialNumber"));
				BusinessInformation.setBusinessAddress(rs.getString("BusinessAddress"));
				BusinessInformation.setRoomType(rs.getString("RoomType"));
				BusinessInformation.setRoomCount(rs.getInt("RoomCount"));
				BusinessInformation.setFee(rs.getInt("Fee"));
				
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
				
		return  BusinessInformation; 
	}
	
	// 글 수정 메소드 : updateBusinessInformation(dto)
	public void updateBusinessInformation(BusinessInformationDTO dto) 
	{
		System.out.println("updateBusinessInformation 메소드 호출됨");
		
		try 
		{
			conn = JDBCUtil.getConnection(); 
			//BusinessInformation_UPDATE = "update BusinessInformation set title= ?, write= ? , content = ? where seq = ?"
			pstmt = conn.prepareStatement(BusinessInformation_UPDATE); 
			
			// ? 변수에 값을 할당 
			pstmt.setString(1, dto.getSerialNumber());
			pstmt.setString(2, dto.getBusinessAddress());
			pstmt.setString(3, dto.getRoomType());
			pstmt.setInt(4, dto.getRoomCount());
			pstmt.setInt(5, dto.getFee());
			pstmt.setString(6, dto.getSerialNumber());
			
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
	
	// 글 삭제 메소드 : deleteBusinessInformation(dto) 
	public void deleteBusinessInformation (BusinessInformationDTO dto) 
	{
		
		try 
		{
			conn = JDBCUtil.getConnection(); 
			// BusinessInformation_DELETE = "delete BusinessInformation where seq = ?"
			pstmt = conn.prepareStatement(BusinessInformation_DELETE); 
			
			// ? 변수값 할당. 
			pstmt.setString(1, dto.getSerialNumber());
			
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
