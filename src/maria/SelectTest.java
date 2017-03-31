package maria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/*
 * mariadb 를 연동하여 레코드를 콘솔에 찍어보자
 * 주의) DBMS제조사가 제공하는 드라이버를 미리 준비해야한다.
 * */
public class SelectTest {
	String driver="org.mariadb.jdbc.Driver";
	String url="jdbc:mariadb://localhost:3306/db0331";
	String user="root";
	String password="";
	
	
	Connection con =null;//접속 정보를 가진 인터페이스
	PreparedStatement pstmt=null; //쿼리 수행객체
	ResultSet rs;  //쿼리문이 select 문일경우 원격지의 데이터베이스의 테이블과 동일한 결과집합을 담아놓는 객체(=표와 같다)
	
	public SelectTest() {
		/*
		 * 1.드라이버를 로드하시오
		 * 2.접속하시오
		 * 3.원하는 query문 실행
		 * 4.db 관련된 자원 닫기
		 * */
		try {
			Class.forName(driver);
			System.out.println("드라이버로드 성공");
			
			con = DriverManager.getConnection(url, user, password);
			if(con!=null){
				System.out.println("접속 성공");
				String sql="select * from member";
				pstmt =  con.prepareStatement(sql);
				
				//쿼리 수행후 반환되는 결과집합을 받자
				//왜? select문이니까
				rs = pstmt.executeQuery();
				while(rs.next()){
					String name= rs.getString("name"); //컬럼에 해당하는 값을 반환
					int age = rs.getInt("age");//나이 반환
					int member_id = rs.getInt("member_id");
					System.out.println(member_id+","+name+","+age);
				}
			}else{
				System.out.println("접속 실패");
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(con!=null){
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	public static void main(String[] args) {
		new SelectTest();
	}
}
