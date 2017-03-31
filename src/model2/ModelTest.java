package model2;
/*
 * 어떤 어플리케이션을 개발하던 유지보수성이 좋아야 비용이 절감되고
 * 어플리케이션의 품질도 올라간다. 
 * 
 * 특히 디자인과 로직이 섞여있는 GUI가 있는 어플리케이션의 경우에는 
 * 유저들의 눈에 보이지 않는 순수 로직과, 컴포넌트와 같은 디자인 로직이 
 * 마구 섞여있을경우 추후 업무내용이 변경되었을때 대응하기가 힘들어진다.
 * 즉, 유지보수성이 떨어진다.
 * 이러한 문제는 전산의 고질적인 문제였고 개발자 선배들이 이미 경험했던 문제들이다
 * 
 * 로직과 디자인(view)은 분리시켜 개발했더니 유지보수성이 아주 올라갔다는 경험을
 * 가리켜 MVC 패턴이라 한다.
 * JTable은 Swing 컴포넌트 중에서 MVC 패턴이 녹아있는 컴포넌트 이며, 디자인에
 * 해당하는 JTable 과 로직에 해당하는 DB데이터를 분리시키기 위해 Tablemodel
 * 이라는 중간 분리자(controller)를 제공해준다.
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
