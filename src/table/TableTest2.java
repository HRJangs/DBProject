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
 * Swing의 컴포넌트중 데이터베이스의 결과집합을 시각화 하기에 최적화된 컴포넌트가 있는데
 * 그게 바로 JTable이다
 * 
 * 
 * 레코드의 갯수의 따라 배열크기를 지정해보자
 * */
public class TableTest2 extends JFrame{
	JTable table;
	JScrollPane scroll;
	Connection con;
	PreparedStatement pstmt;
	//Statement stmt;
	ResultSet rs; //select문일 경우에만 필요함
	//왜? 결과를 담아야 하므로
	
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
	//레코드 채워넣기
	//테이블을 생성하기 전에, mariadb연동하여 
	//member테이블의 레코드를 이차원배열에 담아놓자
	//왜 jtable의 생성자의 인수로 이차원배열이 사용되니까
	public void loadData(){
		try {
			/*
			 * 1.드라이버 로드
			 * 2.접속(drivermanager)
			 * 3.쿼리문 날려라
			 * 4.디비 닫아라
			 * */
			Class.forName(driver);
			con =DriverManager.getConnection(url, user, password);
			if(con!=null){
				System.out.println("접속성공");
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
				System.out.println("접속 실패");
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
