package table;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
 * �츮�� ������� �����ͺ��̽� ��ǰ�� ��� DBMS�̴�
 * DB(�����)MS(�����ý���)- ��Ʈ��ũ����̶� ���������� �����ϴ�.
 * 
 * �����ͺ��̽� ���� Ŭ���̾�Ʈ
 * sqlplus , toad, 
 * 
 * ���� ������� ��Ʈ��ũ ���������� TCP/IP ����̹Ƿ�
 * �������� ȣ��Ʈ�� �����Ϸ��� �� ȣ��Ʈ�� IP�ּҸ� �˾ƾ� �Ѵ�
 * User������ ID/PWD �� �˾ƾ� �Ѵ�.
 * */
public class TestMain {
	public static void main(String[] args) {
	//1�ܰ� - ����Ŭ�� �ڹٰ� ������ �� �ִ� �ڵ尡 ����ִ� jar������ �޸𸮿� �ε�����
			//�̷� �����ͺ��̽��� ���� jar������ �ڹٿ����� ����̹�
			//����̹��� DB�����翡�� �����Ѵ�.
			//oracle --> ����Ŭ��
			//mysql -->����Ŭ
			//mssql --> MS
			//2�ܰ����Ŭ�� ��������
			//����̹� Ŭ���� �ε�!!
			//����Ѱ� String������ ������ ��
		
		Connection con=null;
		PreparedStatement pstmt=null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("����̹� �ε� ����");
			
			con =  DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "batman","1234");
			if(con!= null){
				System.out.println("���Ӽ���");
				//���� ������ ������ ���̺� insert �õ�
				String sql = "insert into company(company_id,brand) values(seq_company.nextval,'nike')";
				//������ ������ ���ؼ��� �������� �����ϴ� ��ü�� �̿��ؾ��Ѵ�.
				//�� ��ü�� �ٷ� preparedStatement �������̽� �̴�.
				pstmt=con.prepareStatement(sql);
				int result = pstmt.executeUpdate();//������ ���� �޼���
				//�������� ���࿡ ���� �ݿ��� ���ڵ��� ���� ��ȯ���ش�
				if(result==1){
					System.out.println("�Է¼���");
				}else{
					System.out.println("�Է½���");
				}
			}else{
				System.out.println("���ӽ���");
			}
			
		}catch (ClassNotFoundException e) {
			System.out.println("����̹��� ã�� �� �����ϴ�.");
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			//Stream�� DB���� �۾� �Ŀ� �ݵ�� �ݴ� ó���� �ؾ��Ѵ�.
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
