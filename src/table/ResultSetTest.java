package table;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * 레코드 결과를 배열로 받을때의 단점
 * 레코드의 총 갯수를 알수가 없다.
 * 
 * */
//Java Data Base Connectivity //자바의 데이터 베이스 연동 기술
public class ResultSetTest {
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String user="batman";
	String password="1234";
	
	Connection con; //접속시도하는 객체 아님, 접속한 이후 그 결과를 담는 객체
	PreparedStatement pstmt;
	ResultSet rs;
	
	//레코드셋 객체를 이용하여 총 레코드 수 알아맞추기
	public ResultSetTest() {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,user,password);
			if(con!=null){
				String sql = "select * from company";
				//1.rs의 커서를 전방향,후방향등으로 자유롭게 움직이거나 한꺼번에 건너뛰게 하려면
				//스크롤가능한 상수옵션을 부여해야한다.
				//2.select문에 결과집합을 대상으로 단지 보기만할거면 read only로 가면된다.
				//수정을 가할거면 undatable
				//근데 내 경험상 select문에 의한 레코드는 읽기위함이다.
				//
				pstmt = con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				rs = pstmt.executeQuery();
				rs.last();
				int  row = rs.getRow();//현재 커서가 가리키는 레코드 번호 //즉 레코드의 위치
				System.out.println(row);
			}	
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new ResultSetTest();
	}

}
