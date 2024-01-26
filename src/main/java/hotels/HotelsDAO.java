package hotels;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import util.JDBCUtil;

public class HotelsDAO 
{
	Connection conn = null; 
	PreparedStatement pstmt = null ; 
	ResultSet rs = null ; 
		
	//sql 쿼리를 상수로 지정함.
	// insert 쿼리 
	private final String Hotels_INSERT = 
		"insert into Hotels (HotelsNumber, HotelsName, HotelsAddress) "
		+ "values (?, ?, ?)";
    
	// DB의 Hotels 테이블의 모든 레코드를 출력 쿼리 : 레코드가 여러개 : dto ==> ArrayList 리턴 
	private final String Hotels_LIST = "select * from Hotels order by HotelsNumber desc" ; 
	
	// DB의 Hotels 테이블의 글 상세 조회 : 레코드 1개  <== dto
	private final String Hotels_GET = "select * from Hotels where HotelsNumber = ?" ; 
	
	// DB의 Hotels 테이블의 업데이트 쿼리 
	private final String Hotels_UPDATE = "update Hotels set HotelsNumber= ?, HotelsName= ?, HotelsAddress= ? where HotelsNumber = ?"; 
	
	// DB의 Hotels 테이블의 레코드를 삭제 
	private final String Hotels_DELETE = "delete Hotels where HotelsNumber = ?";

	
	//insertHotels(HotelsDTO dto) 메소드  : 
	public void insertHotels (HotelsDTO dto) 
	{
		System.out.println("= insertHotels 기능 처리 =");
		
		try 
		{
			
			//conn, pstmt 객체 활성화 
			conn = JDBCUtil.getConnection(); 
			pstmt= conn.prepareStatement(Hotels_INSERT);
			
			//pstmt 객체의 ? 변수의 값 할당. 
			pstmt.setString(1, dto.getHotelsNumber());
			pstmt.setString(2, dto.getHotelsName());
			pstmt.setString(3, dto.getHotelsAddress());

			
			//pstmt 를 실행해서 DB에 저장 
			pstmt.executeUpdate(); 
			
			System.out.println("Hotels 테이블에 값이 잘 insert 되었습니다. ");
			
		}
		
		catch (Exception e) 
		{
			System.out.println("Hotels 테이블에 값이 insert에 실패 했습니다. ");
			e.printStackTrace();
		} 
		
		finally 
		{
			//사용한 객체 제거 
			JDBCUtil.close(pstmt, conn);
		}
	}
	
	// Hotels 테이블의 전체 레코드를 출력 하는 메소드 
	// DB의 레코드 하나를 HotelsDTO에 담는다. 각각의 HotelsDTO 를 ArrayList에 담아서 리턴 
	// rs, pstmt, conn
	public List<HotelsDTO> getHotelsList(HotelsDTO dto) 
	{
		//중요 : ArryList 는 While 블락 밖에서 선언
		//      ArryList에 저장되는  HotelsDTO 선언은 while 블락 내부에서 선언 
		
		List<HotelsDTO> HotelsList = new ArrayList<>(); 
		
		try 
		{
			conn = JDBCUtil.getConnection(); 	//conn 객체 활성화 : Oracle , XE , HR12 , 1234 
			//Hotels_LIST = "select * from Hotels order by seq desc" 
			pstmt = conn.prepareStatement(Hotels_LIST) ; 
			
			// pstmt 를 실행후 rs 에 레코드를 저장 
			rs = pstmt.executeQuery(); 
			
			//System.out.println("DB Select 성공");
			
			// rs의 각 레코드를 HotelsDTO에 저장 ==> 각 각의 DTO를 ArrayList에 저장 
				// if , do ~ while   <===>  while
				//rs.next() : 다음 레코드가 존재하면 true, 커서가 다음레코드로 이동 
				
			while (rs.next()) 
			{
				// HotelsDTO 객체 생성 
				HotelsDTO Hotels = new HotelsDTO();
				    // 루프 블락 내에 선언 
				Hotels.setHotelsNumber(rs.getString("HotelsNumber"));
				Hotels.setHotelsName(rs.getString("HotelsName"));
				Hotels.setHotelsAddress(rs.getString("HotelsAddress"));


				
				// HotelsList : ArrayList의 add 메소드를 사용해서 HotelsDTO를 저장함. 
				HotelsList.add(Hotels); 	
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
			
		return HotelsList ; 
		
	}
	
	// 글 상세 조회 : getHotels(dto) 
	public HotelsDTO getHotels(HotelsDTO dto) 
	{
		System.out.println("getHotels 메소드 호출 성공");
		
		
		HotelsDTO Hotels = new HotelsDTO(); 
		
		try 
		{
			conn = JDBCUtil.getConnection();
			// Hotels_GET = "select * from Hotels where seq = ?"
			pstmt = conn.prepareStatement(Hotels_GET); 
			pstmt.setString(1, dto.getHotelsNumber());
			
			// rs : 레코드 1개 
			rs = pstmt.executeQuery(); 
			
			//rs의 값이 존재할때 
			while ( rs.next()) 
			{
				
				Hotels.setHotelsNumber(rs.getString("HotelsNumber"));
				Hotels.setHotelsName(rs.getString("HotelsName"));
				Hotels.setHotelsAddress(rs.getString("HotelsAddress"));
				
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
				
		return  Hotels; 
	}
	
	// 글 수정 메소드 : updateHotels(dto)
	public void updateHotels(HotelsDTO dto) 
	{
		System.out.println("updateHotels 메소드 호출됨");
		
		try 
		{
			conn = JDBCUtil.getConnection(); 
			//Hotels_UPDATE = "update Hotels set title= ?, write= ? , content = ? where seq = ?"
			pstmt = conn.prepareStatement(Hotels_UPDATE); 
			
			// ? 변수에 값을 할당 
			pstmt.setString(1, dto.getHotelsNumber());
			pstmt.setString(2, dto.getHotelsName());
			pstmt.setString(3, dto.getHotelsAddress());
			pstmt.setString(4, dto.getHotelsNumber());
			
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
	
	// 글 삭제 메소드 : deleteHotels(dto) 
	public void deleteHotels (HotelsDTO dto) 
	{
		
		try 
		{
			conn = JDBCUtil.getConnection(); 
			// Hotels_DELETE = "delete Hotels where seq = ?"
			pstmt = conn.prepareStatement(Hotels_DELETE); 
			
			// ? 변수값 할당. 
			pstmt.setString(1, dto.getHotelsNumber());
			
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
