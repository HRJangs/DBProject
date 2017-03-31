package model2;
/*
 * � ���ø����̼��� �����ϴ� ������������ ���ƾ� ����� �����ǰ�
 * ���ø����̼��� ǰ���� �ö󰣴�. 
 * 
 * Ư�� �����ΰ� ������ �����ִ� GUI�� �ִ� ���ø����̼��� ��쿡�� 
 * �������� ���� ������ �ʴ� ���� ������, ������Ʈ�� ���� ������ ������ 
 * ���� ����������� ���� ���������� ����Ǿ����� �����ϱⰡ ���������.
 * ��, ������������ ��������.
 * �̷��� ������ ������ �������� �������� ������ ������� �̹� �����ߴ� �������̴�
 * 
 * ������ ������(view)�� �и����� �����ߴ��� ������������ ���� �ö󰬴ٴ� ������
 * ������ MVC �����̶� �Ѵ�.
 * JTable�� Swing ������Ʈ �߿��� MVC ������ ����ִ� ������Ʈ �̸�, �����ο�
 * �ش��ϴ� JTable �� ������ �ش��ϴ� DB�����͸� �и���Ű�� ���� Tablemodel
 * �̶�� �߰� �и���(controller)�� �������ش�.
 * */

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ModelTest extends JFrame{
	
	JTable table;
	JScrollPane scroll;
	
	public ModelTest(){
		table =new JTable(new OracleModel());
		scroll = new JScrollPane(table);
		add(scroll);
		pack();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		new ModelTest();
	}
}
