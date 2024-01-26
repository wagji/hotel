package customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import util.JDBCUtil;

public class CustomerDAO 
{
	Connection conn = null; 
	PreparedStatement pstmt = null ; 
	ResultSet rs = null ; 
		
	//sql 쿼리를 상수로 지정함.
	// insert 쿼리 
	private final String customer_INSERT = 
		"insert into customer (CustomerNumber, CustomerName, PhoneNumber, ResidentRegistrationNumber, Email, Password, Address) "
		+ "values (?, ?, ?, ?, ?, ?, ?)";
    
	// DB의 customer 테이블의 모든 레코드를 출력 쿼리 : 레코드가 여러개 : dto ==> ArrayList 리턴 
	private final String customer_LIST = "select * from customer order by CustomerNumber desc" ; 
	
	// DB의 customer 테이블의 글 상세 조회 : 레코드 1개  <== dto
	private final String customer_GET = "select * from customer where CustomerNumber = ?" ; 
	
	// DB의 customer 테이블의 업데이트 쿼리 
	private final String customer_UPDATE = "update customer set CustomerNumber= ?, CustomerName= ?, PhoneNumber= ?, ResidentRegistrationNumber= ?, Email= ?, Password= ?, Address = ? where CustomerNumber = ?"; 
	
	// DB의 customer 테이블의 레코드를 삭제 
	private final String customer_DELETE = "delete customer where CustomerNumber = ?";

	private final String customer_LOGIN = "select * from customer where CustomerNumber = ? and password = ?";
	
	//insertcustomer(CustomerDTO dto) 메소드  : 
	public void insertCustomer (CustomerDTO dto) 
	{
		System.out.println("= insertcustomer 기능 처리 =");
		
		try 
		{
			
			//conn, pstmt 객체 활성화 
			conn = JDBCUtil.getConnection(); 
			pstmt= conn.prepareStatement(customer_INSERT);
			
			//pstmt 객체의 ? 변수의 값 할당. 
			pstmt.setString(1, dto.getCustomerNumber());
			pstmt.setString(2, dto.getCustomerName());
			pstmt.setString(3, dto.getPhoneNumber());
			pstmt.setString(4, dto.getResidentRegistrationNumber());
			pstmt.setString(5, dto.getEmail());
			pstmt.setString(6, dto.getPassword());
			pstmt.setString(7, dto.getAddress());
			
			//pstmt 를 실행해서 DB에 저장 
			pstmt.executeUpdate(); 
			
			System.out.println("customer 테이블에 값이 잘 insert 되었습니다. ");
			
		}
		
		catch (Exception e) 
		{
			System.out.println("customer 테이블에 값이 insert에 실패 했습니다. ");
			e.printStackTrace();
		} 
		
		finally 
		{
			//사용한 객체 제거 
			JDBCUtil.close(pstmt, conn);
		}
	}
	
	// customer 테이블의 전체 레코드를 출력 하는 메소드 
	// DB의 레코드 하나를 CustomerDTO에 담는다. 각각의 CustomerDTO 를 ArrayList에 담아서 리턴 
	// rs, pstmt, conn
	public List<CustomerDTO> getCustomerList(CustomerDTO dto) 
	{
		//중요 : ArryList 는 While 블락 밖에서 선언
		//      ArryList에 저장되는  CustomerDTO 선언은 while 블락 내부에서 선언 
		
		List<CustomerDTO> customerList = new ArrayList<>(); 
		
		try 
		{
			conn = JDBCUtil.getConnection(); 	//conn 객체 활성화 : Oracle , XE , HR12 , 1234 
			//customer_LIST = "select * from customer order by seq desc" 
			pstmt = conn.prepareStatement(customer_LIST) ; 
			
			// pstmt 를 실행후 rs 에 레코드를 저장 
			rs = pstmt.executeQuery(); 
			
			//System.out.println("DB Select 성공");
			
			// rs의 각 레코드를 CustomerDTO에 저장 ==> 각 각의 DTO를 ArrayList에 저장 
				// if , do ~ while   <===>  while
				//rs.next() : 다음 레코드가 존재하면 true, 커서가 다음레코드로 이동 
				
			while (rs.next()) {
				// CustomerDTO 객체 생성 
				CustomerDTO customer = new CustomerDTO();
				    // 루프 블락 내에 선언 
				customer.setCustomerNumber(rs.getString("CustomerNumber"));
				customer.setCustomerName(rs.getString("CustomerName"));
				customer.setPhoneNumber(rs.getString("PhoneNumber"));
				customer.setResidentRegistrationNumber(rs.getString("ResidentRegistrationNumber"));
				customer.setEmail(rs.getString("Email"));
				customer.setPassword(rs.getString("Password"));
				customer.setAddress(rs.getString("Address"));

				
				// customerList : ArrayList의 add 메소드를 사용해서 CustomerDTO를 저장함. 
				customerList.add(customer); 	
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
			
		return customerList ; 
		
	}
	
	// 글 상세 조회 : getcustomer(dto) 
	public CustomerDTO getCustomer(CustomerDTO dto) 
	{
		System.out.println("getcustomer 메소드 호출 성공");
		
		
		CustomerDTO customer = new CustomerDTO(); 
		
		try 
		{
			conn = JDBCUtil.getConnection();
			// customer_GET = "select * from customer where seq = ?"
			pstmt = conn.prepareStatement(customer_GET); 
			pstmt.setString(1, dto.getCustomerNumber());
			
			// rs : 레코드 1개 
			rs = pstmt.executeQuery(); 
			
			//rs의 값이 존재할때 
			while ( rs.next()) 
			{
				
				customer.setCustomerNumber(rs.getString("CustomerNumber"));
				customer.setCustomerName(rs.getString("CustomerName"));
				customer.setPhoneNumber(rs.getString("PhoneNumber"));
				customer.setResidentRegistrationNumber(rs.getString("ResidentRegistrationNumber"));
				customer.setEmail(rs.getString("Email"));
				customer.setPassword(rs.getString("Password"));
				customer.setAddress(rs.getString("Address"));
				
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
				
		return  customer; 
	}
	
	// 글 수정 메소드 : updatecustomer(dto)
	public void updateCustomer(CustomerDTO dto) 
	{
		System.out.println("updatecustomer 메소드 호출됨");
		
		try 
		{
			conn = JDBCUtil.getConnection(); 
			//customer_UPDATE = "update customer set title= ?, write= ? , content = ? where seq = ?"
			pstmt = conn.prepareStatement(customer_UPDATE); 
			
			// ? 변수에 값을 할당 
			pstmt.setString(1, dto.getCustomerNumber());
			pstmt.setString(2, dto.getCustomerName());
			pstmt.setString(3, dto.getPhoneNumber());
			pstmt.setString(4, dto.getResidentRegistrationNumber());
			pstmt.setString(5, dto.getEmail());
			pstmt.setString(6, dto.getPassword());
			pstmt.setString(7, dto.getAddress());
			pstmt.setString(8, dto.getCustomerNumber());
			
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
	
	// 글 삭제 메소드 : deletecustomer(dto) 
	public void deleteCustomer (CustomerDTO dto) 
	{
		
		try 
		{
			conn = JDBCUtil.getConnection(); 
			// customer_DELETE = "delete customer where seq = ?"
			pstmt = conn.prepareStatement(customer_DELETE); 
			
			// ? 변수값 할당. 
			pstmt.setString(1, dto.getCustomerNumber());
			
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
	
	//메소드 - 로그인
	public CustomerDTO login(CustomerDTO dto) 
	{
		System.out.println("login 메소드 호출");
		
		//리턴으로 돌려줄 UserDTO  <== null, dto 
		CustomerDTO customer = null; 
		
		try
		{
			conn = JDBCUtil.getConnection(); 
			// USERS_LOIN = "select * from users where id = ? and password = ?"
			pstmt = conn.prepareStatement(customer_LOGIN); 
			
			// ? 변수의 값할당 
			pstmt.setString(1, dto.getCustomerNumber());
			pstmt.setString(2, dto.getPassword());
			
			// pstmt 실행후 rs 로 쿼리한 레코드 저장 
			rs = pstmt.executeQuery();   //select 
			
			// rs의 값이 존재할때 인증 성공 : 레코드 1개가 출력 
			while ( rs.next()) 
			{
				// 리턴으로 돌려줄 dto 선언 
				customer = new CustomerDTO(); 
				
				customer.setCustomerNumber(rs.getString("CustomerNumber"));
				customer.setCustomerName(rs.getString("CustomerName"));
				customer.setPhoneNumber(rs.getString("PhoneNumber"));
				customer.setResidentRegistrationNumber(rs.getString("ResidentRegistrationNumber"));
				customer.setEmail(rs.getString("Email"));
				customer.setPassword(rs.getString("Password"));
				customer.setAddress(rs.getString("Address"));
				
				System.out.println(" - 인증 성공 : 해당 ID와 Password가 DB에 존재합니다.  ");				
			}
					
		}
		
		catch (Exception e) 
		{
			System.out.println("인증시 문제가 발생 했습니다. ");
			e.printStackTrace();
		}
		
		finally 
		{
			JDBCUtil.close(rs, pstmt, conn);
		}
		
		return customer; 
	}
	
	//메소드 - 로그아웃
		public CustomerDTO logout(CustomerDTO dto) 
		{
			System.out.println("logout 메소드 호출");
			
			//리턴으로 돌려줄 UserDTO  <== null, dto 
			CustomerDTO customer = null; 
			
			try
			{
				conn = JDBCUtil.getConnection(); 
				// USERS_LOIN = "select * from users where id = ? and password = ?"
				pstmt = conn.prepareStatement(customer_LOGIN); 
				
				// ? 변수의 값할당 
				pstmt.setString(1, dto.getCustomerNumber());
				pstmt.setString(2, dto.getPassword());
				
				// pstmt 실행후 rs 로 쿼리한 레코드 저장 
				rs = pstmt.executeQuery();   //select 
				
				// rs의 값이 존재할때 인증 성공 : 레코드 1개가 출력 
				while ( rs.next()) 
				{
					// 리턴으로 돌려줄 dto 선언 
					customer = new CustomerDTO(); 
					
					customer.setCustomerNumber(rs.getString("CustomerNumber"));
					customer.setCustomerName(rs.getString("CustomerName"));
					customer.setPhoneNumber(rs.getString("PhoneNumber"));
					customer.setResidentRegistrationNumber(rs.getString("ResidentRegistrationNumber"));
					customer.setEmail(rs.getString("Email"));
					customer.setPassword(rs.getString("Password"));
					customer.setAddress(rs.getString("Address"));
					
					System.out.println(" - 인증 성공 : 해당 ID와 Password가 DB에 존재합니다.  ");				
				}
						
			}
			
			catch (Exception e) 
			{
				System.out.println("인증시 문제가 발생 했습니다. ");
				e.printStackTrace();
			}
			
			finally 
			{
				JDBCUtil.close(rs, pstmt, conn);
			}
			
			return customer; 
		}
	
}
