package jMenuInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class jMenuItemAdd extends JFrame{
	Container cp=getContentPane();
	JPanel panel=new JPanel();	
	JLabel label1=new JLabel("����");
	JLabel label2=new JLabel("�绰");
	JLabel label3=new JLabel("��ַ");
	JLabel label4=new JLabel("QQ");
	JTextField textfield1=new JTextField(5);
	JTextField textfield2=new JTextField(11);
	JTextField textfield3=new JTextField(20);
	JTextField textfield4=new JTextField(13);
	JButton button1=new JButton("����");
	JButton button2=new JButton("ȡ��");
	
	public jMenuItemAdd(){
		jMenuItemAddInit("������Ϣ");
	}
	
	public void jMenuItemAddInit(String title){
		this.setTitle(title);
		setVisible(true);
		setSize(800,130);
		cp.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		cp.add(label1);
		cp.add(textfield1);
		panel.add(label2);
		panel.add(textfield2);
		panel.add(label3);
		panel.add(textfield3);
		panel.add(label4);
		panel.add(textfield4);
		cp.add(panel);
		cp.add(button1);
		cp.add(button2);
		
		//ע���¼�������
		   button1.addMouseListener(new ML3(this));
		   button2.addMouseListener(new ML4(this));
	}
	
	   //�������ݿ����
    void add(MouseEvent e) throws ClassNotFoundException{
 	   Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
 	   Connection conn = null;
 	   try {
			conn = DriverManager.getConnection("jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ=E:\\web\\���ݿ�\\mdb.mdb");
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
 	   String strSQL="insert into AddressBook (NameInfo,PhoneNumber,Address,QQ) values ('"+textfield1.getText().trim()+"',";
 	          strSQL=strSQL+"'"+textfield2.getText().trim()+"',";
 	          strSQL=strSQL+"'"+textfield3.getText().trim()+"',";
 	          strSQL=strSQL+"'"+textfield4.getText().trim()+"')";
 	   
 	   Statement stmt = null;
 	   try {
			stmt=conn.createStatement();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
 	   
 	   if(textfield1.getText().trim().equals("")&&textfield2.getText().trim().equals("")&&textfield3.getText().trim().equals("")&&textfield4.getText().trim().equals("")){
 		  JOptionPane.showMessageDialog(null, "����ʧ��!"); 		  
 	   }
 	   else{
 		  try{
	 		   //����Ϣд�����ݿ�
	 			 stmt.executeUpdate(strSQL);
	 			 JOptionPane.showMessageDialog(null, "���ӳɹ�!");
	 			 dispose();
	 		   }
	 		   catch(SQLException ex){
	 			   
	 		   }
 		     
 	   }
    }
    
    //�˳�����
    void AddCancel(MouseEvent e){
 	   dispose();
    }
    
    //��ť�ļ�������
    class ML3 extends java.awt.event.MouseAdapter{
       jMenuItemAdd adaptee;
 	   ML3(jMenuItemAdd adaptee){
 		   this.adaptee=adaptee;
 		   }
 	   public void mouseClicked(MouseEvent e){
 		   try {
				adaptee.add(e);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
 	   }
    }
    
    class ML4 extends java.awt.event.MouseAdapter{
    	jMenuItemAdd adaptee;
 	   ML4(jMenuItemAdd adaptee){
 		   this.adaptee=adaptee;
 		   }
 	   public void mouseClicked(MouseEvent e){
 		   adaptee.AddCancel(e);
 	   }
    }


}
