package Airplane;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.JDBCUtil;

public class AirplaneDAO 
{
	Connection conn = null; 
	PreparedStatement pstmt = null ; 
	ResultSet rs = null ; 
		
	//sql 쿼리를 상수로 지정함.
	// insert 쿼리 
	private final String Airplane_INSERT = 
		"insert into Airplane (SerialNumber, AirplaneName, Capacity, DepartureCity, DestinationCity)"
		+ "values (?, ?, ?, ?, ?)";
    
	// DB의 Airplane 테이블의 모든 레코드를 출력 쿼리 : 레코드가 여러개 : dto ==> ArrayList 리턴 
	private final String Airplane_LIST = "select * from Airplane order by SerialNumber desc" ; 
	
	// DB의 Airplane 테이블의 글 상세 조회 : 레코드 1개  <== dto
	private final String Airplane_GET = "select * from Airplane where SerialNumber = ?" ; 
	
	// DB의 Airplane 테이블의 업데이트 쿼리 
	private final String Airplane_UPDATE = "update Airplane set AirplaneName = ?, Capacity = ?, DepartureCity = ?, DestinationCity = ? where SerialNumber = ?"; 
	
	// DB의 Airplane 테이블의 레코드를 삭제 
	private final String Airplane_DELETE = "delete Airplane where SerialNumber = ?";
	
	
	//insertAirplane(AirplaneDTO dto) 메소드  : 
	public void insertAirplane (AirplaneDTO dto) 
	{
		System.out.println("= insertAirplane 기능 처리 =");
		
		try 
		{
			
			//conn, pstmt 객체 활성화 
			conn = JDBCUtil.getConnection() ; 
			pstmt= conn.prepareStatement(Airplane_INSERT); 
			
			//pstmt 객체의 ? 변수의 값 할당. 
			pstmt.setString(1, dto.getSerialNumber());
			pstmt.setString(2, dto.getAirplaneName());
			pstmt.setInt(3, dto.getCapacity());
			pstmt.setString(4, dto.getDepartureCity());
			pstmt.setString(5, dto.getDestinationCity());
			
			//pstmt 를 실행해서 DB에 저장 
			pstmt.executeUpdate(); 
			
			System.out.println("Airplane 테이블에 값이 잘 insert 되었습니다. ");
			
		}
		
		catch (Exception e) 
		{
			System.out.println("Airplane 테이블에 값이 insert에 실패 했습니다. ");
			e.printStackTrace();
		} 
		
		finally 
		{
			//사용한 객체 제거 
			JDBCUtil.close(pstmt, conn);
		}
	}
	
	// Airplane 테이블의 전체 레코드를 출력 하는 메소드 
	// DB의 레코드 하나를 AirplaneDTO에 담는다. 각각의 AirplaneDTO 를 ArrayList에 담아서 리턴 
	// rs, pstmt, conn
	public List<AirplaneDTO> getAirplaneList(AirplaneDTO dto) 
	{
		//중요 : ArryList 는 While 블락 밖에서 선언
		//      ArryList에 저장되는  AirplaneDTO 선언은 while 블락 내부에서 선언 
		
		List<AirplaneDTO> AirplaneList = new ArrayList<>(); 
		
		try 
		{
			conn = JDBCUtil.getConnection(); 	//conn 객체 활성화 : Oracle , XE , HR12 , 1234 
			//Airplane_LIST = "select * from Airplane order by seq desc" 
			pstmt = conn.prepareStatement(Airplane_LIST) ; 
			
			// pstmt 를 실행후 rs 에 레코드를 저장 
			rs = pstmt.executeQuery(); 
			
			//System.out.println("DB Select 성공");
			
			// rs의 각 레코드를 AirplaneDTO에 저장 ==> 각 각의 DTO를 ArrayList에 저장 
				// if , do ~ while   <===>  while
				//rs.next() : 다음 레코드가 존재하면 true, 커서가 다음레코드로 이동 
				
			while (rs.next()) 
			{
				// AirplaneDTO 객체 생성 
				AirplaneDTO Airplane = new AirplaneDTO();
				    // 루프 블락 내에 선언 
				Airplane.setSerialNumber(rs.getString("SerialNumber"));
				Airplane.setAirplaneName(rs.getString("AirplaneName"));
				Airplane.setCapacity(rs.getInt("Capacity"));
				Airplane.setDepartureCity(rs.getString("DepartureCity"));
				Airplane.setDestinationCity(rs.getString("DestinationCity"));
				
				
				// AirplaneList : ArrayList의 add 메소드를 사용해서 AirplaneDTO를 저장함. 
				AirplaneList.add(Airplane); 	
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
			
		return AirplaneList ; 
		
	}
	
	// 글 상세 조회 : getAirplane(dto) 
	public AirplaneDTO getAirplane(AirplaneDTO dto) 
	{
		System.out.println("getAirplane 메소드 호출 성공");
		
		AirplaneDTO Airplane = new AirplaneDTO(); 
		
		try 
		{
			conn = JDBCUtil.getConnection();
			// Airplane_GET = "select * from Airplane where seq = ?"
			pstmt = conn.prepareStatement(Airplane_GET); 
			pstmt.setString(1, dto.getSerialNumber());
			
			// rs : 레코드 1개 
			rs = pstmt.executeQuery(); 
			
			//rs의 값이 존재할때 
			while ( rs.next()) 
			{
				
				Airplane.setSerialNumber(rs.getString("SerialNumber"));
				Airplane.setAirplaneName(rs.getString("AirplaneName"));
				Airplane.setCapacity(rs.getInt("Capacity"));
				Airplane.setDepartureCity(rs.getString("DepartureCity"));
				Airplane.setDestinationCity(rs.getString("DestinationCity"));
				
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
				
		return  Airplane; 
	}
	
	// 글 수정 메소드 : updateAirplane(dto)
	public void updateAirplane(AirplaneDTO dto) 
	{
		System.out.println("updateAirplane 메소드 호출됨");
		
		try 
		{
			conn = JDBCUtil.getConnection(); 
			//Airplane_UPDATE = "update Airplane set title= ?, write= ? , content = ? where seq = ?"
			pstmt = conn.prepareStatement(Airplane_UPDATE); 
			
			// ? 변수에 값을 할당 
			pstmt.setString(1, dto.getAirplaneName());
			pstmt.setInt(2, dto.getCapacity());
			pstmt.setString(3, dto.getDepartureCity());
			pstmt.setString(4, dto.getDestinationCity());
			pstmt.setString(5, dto.getSerialNumber());
			
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
	
	// 글 삭제 메소드 : deleteAirplane(dto) 
	public void deleteAirplane (AirplaneDTO dto) 
	{
		
		try 
		{
			conn = JDBCUtil.getConnection(); 
			// Airplane_DELETE = "delete Airplane where seq = ?"
			pstmt = conn.prepareStatement(Airplane_DELETE); 
			
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
