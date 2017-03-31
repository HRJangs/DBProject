package table;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/*
 * Swing�� ������Ʈ�� �����ͺ��̽��� ��������� �ð�ȭ �ϱ⿡ ����ȭ�� ������Ʈ�� �ִµ�
 * �װ� �ٷ� JTable�̴�
 * 
 * 
 * ���ڵ��� ������ ���� �迭ũ�⸦ �����غ���
 * */
public class TableTest extends JFrame{
	JTable table;
	JScrollPane scroll;
	Connection con;
	PreparedStatement pstmt;
	//Statement stmt;
	ResultSet rs; //select���� ��쿡�� �ʿ���
	//��? ����� ��ƾ� �ϹǷ�
	
	ArrayList<String> arr = new ArrayList<String>();
	
	String driver="oracle.jdbc.driver.OracleDriver";
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String user="batman";
	String password="1234";

	String[][] data;
	String[] columnName = {
		"empno","ename","job","mgr","hiredate","sal","comm","deptno"	
	};

	public TableTest() {
		//setLayout(new FlowLayout());
		loadData();
		table = new JTable(data,columnName);
		scroll = new JScrollPane(table);
		
		add(scroll);
		setBounds(500,300,800,400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	//���ڵ� ä���ֱ�
	//���̺��� �����ϱ� ����, mariadb�����Ͽ� 
	//member���̺��� ���ڵ带 �������迭�� ��Ƴ���
	//�� jtable�� �������� �μ��� �������迭�� ���Ǵϱ�
	public void loadData(){
		try {
			/*
			 * 1.����̹� �ε�
			 * 2.����(drivermanager)
			 * 3.������ ������
			 * 4.��� �ݾƶ�
			 * */
			Class.forName(driver);
			con =DriverManager.getConnection(url, user, password);
			if(con!=null){
				System.out.println("���Ӽ���");
				String sql = "select * from emp";
				pstmt =con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				rs = pstmt.executeQuery();
			
				//rs�� last()�� ������ ��ġ�� ����
				rs.last();
				int row = rs.getRow();
				//rs���󺹱�
				rs.beforeFirst();
				
				data = new String[row][columnName.length];
				int rowindex=0;
				while(rs.next()){
					for(int i =0;i<columnName.length;i++){
						data[rowindex][i] =rs.getString(i+1);
					}
					rowindex++;
				}
			}else{
				System.out.println("���� ����");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
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
		new TableTest();
	}
}
