package table;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * ���ڵ� ����� �迭�� �������� ����
 * ���ڵ��� �� ������ �˼��� ����.
 * 
 * */
//Java Data Base Connectivity //�ڹ��� ������ ���̽� ���� ���
public class ResultSetTest {
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String user="batman";
	String password="1234";
	
	Connection con; //���ӽõ��ϴ� ��ü �ƴ�, ������ ���� �� ����� ��� ��ü
	PreparedStatement pstmt;
	ResultSet rs;
	
	//���ڵ�� ��ü�� �̿��Ͽ� �� ���ڵ� �� �˾Ƹ��߱�
	public ResultSetTest() {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,user,password);
			if(con!=null){
				String sql = "select * from company";
				//1.rs�� Ŀ���� ������,�Ĺ�������� �����Ӱ� �����̰ų� �Ѳ����� �ǳʶٰ� �Ϸ���
				//��ũ�Ѱ����� ����ɼ��� �ο��ؾ��Ѵ�.
				//2.select���� ��������� ������� ���� ���⸸�ҰŸ� read only�� ����ȴ�.
				//������ ���ҰŸ� undatable
				//�ٵ� �� ����� select���� ���� ���ڵ�� �б������̴�.
				//
				pstmt = con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				rs = pstmt.executeQuery();
				rs.last();
				int  row = rs.getRow();//���� Ŀ���� ����Ű�� ���ڵ� ��ȣ //�� ���ڵ��� ��ġ
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
