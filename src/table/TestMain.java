package table;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
 * 우리가 사용중인 데이터베이스 제품은 모두 DBMS이다
 * DB(저장소)MS(관리시스템)- 네트워크기반이라서 원격접속이 가능하다.
 * 
 * 데이터베이스 접속 클라이언트
 * sqlplus , toad, 
 * 
 * 현재 사용중인 네트워크 프로토콜은 TCP/IP 기반이므로
 * 원격지의 호스트에 접속하려면 그 호스트에 IP주소를 알아야 한다
 * User계정의 ID/PWD 를 알아야 한다.
 * */
public class TestMain {
	public static void main(String[] args) {
	//1단계 - 오라클을 자바가 제어할 수 있는 코드가 들어있는 jar파일을 메모리에 로드하자
			//이런 데이터베이스형 제어 jar파일을 자바에서는 드라이버
			//드라이버는 DB제조사에서 제공한다.
			//oracle --> 오라클사
			//mysql -->오라클
			//mssql --> MS
			//2단계오라클에 접속하자
			//드라이버 클래스 로드!!
			//희안한게 String형으로 넣으면 됨
		
		Connection con=null;
		PreparedStatement pstmt=null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 로드 성공");
			
			con =  DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "batman","1234");
			if(con!= null){
				System.out.println("접속성공");
				//현재 유저가 보유한 테이블에 insert 시도
				String sql = "insert into company(company_id,brand) values(seq_company.nextval,'nike')";
				//쿼리문 수행을 위해서는 쿼리문을 전담하는 객체를 이용해야한다.
				//이 객체가 바로 preparedStatement 인터페이스 이다.
				pstmt=con.prepareStatement(sql);
				int result = pstmt.executeUpdate();//쿼리문 실행 메서드
				//이쿼리문 수행에 의해 반영된 레코드의 수를 반환해준다
				if(result==1){
					System.out.println("입력성공");
				}else{
					System.out.println("입력실패");
				}
			}else{
				System.out.println("접속실패");
			}
			
		}catch (ClassNotFoundException e) {
			System.out.println("드라이버를 찾을 수 없습니다.");
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			//Stream과 DB연동 작업 후엔 반드시 닫는 처리를 해야한다.
			if(pstmt!=null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con!=null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
}
