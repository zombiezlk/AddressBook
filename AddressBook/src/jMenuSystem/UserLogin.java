package jMenuSystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import jMainFrame.MainFrame;

public class UserLogin extends JFrame{
	   Container cp=getContentPane();
	   JPanel panel1=new JPanel();
	   JPanel panel2=new JPanel();
	   JPanel panel3=new JPanel();
	   JLabel label1=new JLabel("�û���");
	   JLabel label2=new JLabel("����");
	   JButton button1=new JButton("ȷ��");
	   JButton button2=new JButton("ȡ��");
	   JComboBox combobox=new JComboBox();
	   JPasswordField  passwordfield=new JPasswordField(8);	   
   public UserLogin(){
	   UserLoginInit("�û���¼");
   }
   
   public void UserLoginInit(String title){
	   this.setTitle(title);
	   panel1.add(label1);
	   panel1.add(combobox);
	   panel1.setLayout(new FlowLayout(FlowLayout.CENTER,20,10));
	   panel3.setLayout(new FlowLayout(FlowLayout.CENTER,20,10));
	   combobox.addItem("��ͨ�û�");
	   combobox.addItem("����Ա");
	   panel3.add(label2);
	   panel3.add(passwordfield);
	   panel2.add(button1);
	   panel2.add(button2);
	   cp.add(panel1,BorderLayout.NORTH);
	   cp.add(panel3,BorderLayout.CENTER);
	   cp.add(panel2,BorderLayout.SOUTH);
	   setVisible(true);
	   setSize(500,200);
	   
	   //ע���¼�������
	   button1.addMouseListener(new AL1(this));
	   button2.addMouseListener(new AL2(this));
   }
       //�������ݿ����
	   void confirm(MouseEvent e) throws SQLException{
		   try {
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			  }    	
		 
			Connection conn = null;
			try {
				conn = DriverManager.getConnection("jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ=E:\\web\\���ݿ�\\mdb.mdb");
			} catch (SQLException e2) {
			
				e2.printStackTrace();
			}
			String strSQL1="select Password from UserInfo where UserName='����Ա'";
			String strSQL2="select Password from UserInfo where UserName='��ͨ�û�'";
			Statement stmt1 = null,stmt2 = null;
			ResultSet rs1;
			String password_str1;
			ResultSet rs2;
			String password_str2;
			try {
			     stmt1=conn.createStatement();
			} catch (SQLException e1) {
			
				e1.printStackTrace();
			}
			try {
			     stmt2=conn.createStatement();
			} catch (SQLException e1) {
			
				e1.printStackTrace();
			}
			   rs1=stmt1.executeQuery(strSQL1);
			   rs1.next();
			   password_str1=rs1.getString("Password");
			   rs2=stmt2.executeQuery(strSQL2);
			   rs2.next();
			   password_str2=rs2.getString("Password");
			   String password=new String(passwordfield.getPassword());
			   //�������ݿ��û����Ƿ�ƥ��
			   if(combobox.getSelectedItem()=="����Ա"){
				   if(password.equals(password_str1)){
					   MainFrame.setEnable(true,true,true,true,true);
					   dispose();
				   }
				   else{
					   JOptionPane.showMessageDialog(null, "�������");
				   }   
			   }
			   
			   else{
				   if(password.equals(password_str2)){
					   MainFrame.setEnable(UserManage.add,UserManage.delete,UserManage.update,false,UserManage.query);
					   dispose();
				   }
				   else{
					   JOptionPane.showMessageDialog(null, "�������");
				   }
				
			}
		       
		         
	   }
	
       void cancel(MouseEvent e){
    	   dispose();
       }
   
   //��ť�ļ�������
   class AL1 extends java.awt.event.MouseAdapter{
	   UserLogin adaptee;
	   AL1(UserLogin adaptee){
	   this.adaptee=adaptee;
	   }
	   public void mouseClicked(MouseEvent e){
		   try {
			adaptee.confirm(e);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	   }
   }
   
   class AL2 extends java.awt.event.MouseAdapter{
	   UserLogin adaptee;
	   AL2(UserLogin adaptee){
	   this.adaptee=adaptee;
	   }
	   public void mouseClicked(MouseEvent e){
		   adaptee.cancel(e);
	   }
   }
}