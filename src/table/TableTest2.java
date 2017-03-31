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
public class TableTest2 extends JFrame{
	JTable table;
	JScrollPane scroll;
	Connection con;
	PreparedStatement pstmt;
	//Statement stmt;
	ResultSet rs; //select���� ��쿡�� �ʿ���
	//��? ����� ��ƾ� �ϹǷ�
	
	ArrayList<String> arr = new ArrayList<String>();
	
	String driver="org.mariadb.jdbc.Driver";
	String url="jdbc:mariadb://localhost:3306/db0331";
	String user="root";
	String password="";

	String[][] data;
	String[] columnName = {
		"member_id","name","age"	
	};

	public TableTest2() {
		//setLayout(new FlowLayout());
		loadData();
		table = new JTable(data,columnName);
		scroll = new JScrollPane(table);
		
		add(scroll);
		setBounds(300,300,500,150);
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
				String sql = "select * from member";
				pstmt =con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				int cnt=0;
				int row=0;
				int column=0;
				while(rs.next()){
					cnt++;
				}
				data = new String[cnt][3];
				while(rs.next()){
					data[row][column] =	rs.getString(column+1);
					data[row][column+1] =	rs.getString(column+2);
					data[row][column+2] =	rs.getString(column+3);
					row++;
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
		new TableTest2();
	}

}
