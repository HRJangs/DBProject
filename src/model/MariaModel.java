package model;

import javax.swing.table.AbstractTableModel;

/*
 * �� ��ü�� JTable�� �����ڿ��� �䱸�ϴ�
 * ��Ʈ�ѷ� ��ü�̴�.
 * �� ��ü�� ������ �����ΰ� ������ �и������ִ� 
 * �߰��� ����
 * */
public class MariaModel extends AbstractTableModel{
	
	String driver ="oracle.jdbc.driver.OracleDriver";
	String url="jdbc:oracle:thin:@localhost:XE";
	String user="batman";
	String password="1234";
	
	String[][] data = new String[3][3];
	
	public MariaModel() {
		data[0][0]="û����";
		data[0][1]="�����ٳ�";
		data[0][2]="2000";
		
		data[1][0]="�����";
		data[1][1]="���";
		data[1][2]="5000";
		
		data[2][0]="ġ��";
		data[2][1]="����Ű";
		data[2][2]="30000";
	}
	//���ڵ��� ������ ��ȯ
	public int getRowCount() {
		return data[0].length;
	}

	//�÷��� ������ ��ȯ
	public int getColumnCount() {
		return data.length;
	}
	//Ư����ġ�� ���� ��ȯ
	public Object getValueAt(int rowIndex, int columnIndex) {
		return data[rowIndex][columnIndex];
	}
	
}
