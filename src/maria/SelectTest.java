package maria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/*
 * mariadb �� �����Ͽ� ���ڵ带 �ֿܼ� ����
 * ����) DBMS�����簡 �����ϴ� ����̹��� �̸� �غ��ؾ��Ѵ�.
 * */
public class SelectTest {
	String driver="org.mariadb.jdbc.Driver";
	String url="jdbc:mariadb://localhost:3306/db0331";
	String user="root";
	String password="";
	
	
	Connection con =null;//���� ������ ���� �������̽�
	PreparedStatement pstmt=null; //���� ���ఴü
	ResultSet rs;  //�������� select ���ϰ�� �������� �����ͺ��̽��� ���̺�� ������ ��������� ��Ƴ��� ��ü(=ǥ�� ����)
	
	public SelectTest() {
		/*
		 * 1.����̹��� �ε��Ͻÿ�
		 * 2.�����Ͻÿ�
		 * 3.���ϴ� query�� ����
		 * 4.db ���õ� �ڿ� �ݱ�
		 * */
		try {
			Class.forName(driver);
			System.out.println("����̹��ε� ����");
			
			con = DriverManager.getConnection(url, user, password);
			if(con!=null){
				System.out.println("���� ����");
				String sql="select * from member";
				pstmt =  con.prepareStatement(sql);
				
				//���� ������ ��ȯ�Ǵ� ��������� ����
				//��? select���̴ϱ�
				rs = pstmt.executeQuery();
				while(rs.next()){
					String name= rs.getString("name"); //�÷��� �ش��ϴ� ���� ��ȯ
					int age = rs.getInt("age");//���� ��ȯ
					int member_id = rs.getInt("member_id");
					System.out.println(member_id+","+name+","+age);
				}
			}else{
				System.out.println("���� ����");
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
