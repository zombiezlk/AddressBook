package jMenuInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class jMenuItemUpdate extends JFrame{
	Container cp=getContentPane();
	JPanel panel=new JPanel();	
	JLabel label1=new JLabel("��������");
	JLabel label2=new JLabel("�绰");
	JLabel label3=new JLabel("��ַ");
	JLabel label4=new JLabel("QQ");
	JTextField textfield1=new JTextField(5);
	JTextField textfield2=new JTextField(11);
	JTextField textfield3=new JTextField(20);
	JTextField textfield4=new JTextField(13);
	JButton button1=new JButton("ȷ��");
	JButton button2=new JButton("����");
	JButton button3=new JButton("ȡ��");
	
	public jMenuItemUpdate(){
		jMenuItemUpdateInit("������Ϣ");
	}
	
	public void jMenuItemUpdateInit(String title){
		this.setTitle(title);
		setVisible(true);
		setSize(800,130);
		cp.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		cp.add(label1);
		cp.add(textfield1);
		cp.add(button1);
		panel.add(label2);
		panel.add(textfield2);
		panel.add(label3);
		panel.add(textfield3);
		panel.add(label4);
		panel.add(textfield4);
		cp.add(panel);
		cp.add(button2);
		cp.add(button3);
		
		//ע���¼�������
		   button1.addMouseListener(new ML5(this));
		   button2.addMouseListener(new ML6(this));
		   button3.addMouseListener(new ML7(this));
	}
	   //�������ݿ����
	void UpdateSearch(MouseEvent e) throws ClassNotFoundException{
 	   Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
 	   Connection conn = null;
 	   try {
			conn = DriverManager.getConnection("jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ=E:\\web\\���ݿ�\\mdb.mdb");
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
 	   String strSQL="select * from AddressBook where NameInfo='"+textfield1.getText().trim()+"'";
 	   Statement stmt = null;
 	   try {
			stmt=conn.createStatement();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
 	   
 	   ResultSet rs = null;
 	   boolean isexist=false;
 	   
 	   try {
			rs=stmt.executeQuery(strSQL);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
 	   try {
			isexist=rs.next();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
 	   
 	   if(!isexist){
 		   JOptionPane.showMessageDialog(null, "��Ϣ������!");
 	   }
 	   else{
 		   try{
 		   //����Ϣ������ʾ�ڶ�Ӧ�ı�����
		   
 		   textfield2.setText(rs.getString("PhoneNumber"));
 		   textfield3.setText(rs.getString("Address"));
 		   textfield4.setText(rs.getString("QQ"));
 		   }
 		   catch(SQLException ex){   
 		   }
 	   }
    }
	
	//�˳�����
    void UpdateCancel (MouseEvent e){
 	   dispose();
    }
    
    //ִ�и��²���
    void update(MouseEvent e) throws ClassNotFoundException{
  	   Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
  	   Connection conn = null;
  	   try {
 			conn = DriverManager.getConnection("jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ=E:\\web\\���ݿ�\\mdb.mdb");
 		} catch (SQLException e2) {
 			// TODO Auto-generated catch block
 			e2.printStackTrace();
 		}
  	   String strSQL="update AddressBook set NameInfo='"+textfield1.getText().trim()+"',";
  	          strSQL=strSQL+"PhoneNumber='"+textfield2.getText().trim()+"',";
              strSQL=strSQL+"Address='"+textfield3.getText().trim()+"',";
              strSQL=strSQL+"QQ='"+textfield4.getText().trim()+"'";
              strSQL=strSQL+"where NameInfo='"+textfield1.getText().trim()+"'";
  	   Statement stmt = null;
  	   try {
 			stmt=conn.createStatement();
 		} catch (SQLException e2) {
 			// TODO Auto-generated catch block
 			e2.printStackTrace();
 		}
  	  
  	   try {
 			stmt.executeUpdate(strSQL);
 		} catch (SQLException e2) {
 			// TODO Auto-generated catch block
 			e2.printStackTrace();
 		}
  	   JOptionPane.showMessageDialog(null, "���³ɹ�!");
  	   dispose();
    }
	
   //��ť�ļ�������
    class ML5 extends java.awt.event.MouseAdapter{
       jMenuItemUpdate adaptee;
 	   ML5(jMenuItemUpdate adaptee){
 		   this.adaptee=adaptee;
 		   }
 	   public void mouseClicked(MouseEvent e){
 		   try {
				adaptee.UpdateSearch(e);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
 	   }
    }
    
    class ML6 extends java.awt.event.MouseAdapter{
       jMenuItemUpdate adaptee;
 	   ML6(jMenuItemUpdate adaptee){
 		   this.adaptee=adaptee;
 		   }
 	   public void mouseClicked(MouseEvent e){
 		   try {
			adaptee.update(e);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
 	   }
    }
    
    class ML7 extends java.awt.event.MouseAdapter{
       jMenuItemUpdate adaptee;
  	   ML7(jMenuItemUpdate adaptee){
  		   this.adaptee=adaptee;
  		   }
  	   public void mouseClicked(MouseEvent e){
  		   adaptee.UpdateCancel(e);
  	   }
     }

    
}
