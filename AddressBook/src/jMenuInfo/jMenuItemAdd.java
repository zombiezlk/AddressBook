package jMenuInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class jMenuItemAdd extends JFrame{
	Container cp=getContentPane();
	JPanel panel=new JPanel();	
	JLabel label1=new JLabel("姓名");
	JLabel label2=new JLabel("电话");
	JLabel label3=new JLabel("地址");
	JLabel label4=new JLabel("QQ");
	JTextField textfield1=new JTextField(5);
	JTextField textfield2=new JTextField(11);
	JTextField textfield3=new JTextField(20);
	JTextField textfield4=new JTextField(13);
	JButton button1=new JButton("增加");
	JButton button2=new JButton("取消");
	
	public jMenuItemAdd(){
		jMenuItemAddInit("增加信息");
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
		
		//注册事件监听器
		   button1.addMouseListener(new ML3(this));
		   button2.addMouseListener(new ML4(this));
	}
	
	   //连接数据库访问
    void add(MouseEvent e) throws ClassNotFoundException{
 	   Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
 	   Connection conn = null;
 	   try {
			conn = DriverManager.getConnection("jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ=E:\\web\\数据库\\mdb.mdb");
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
 		  JOptionPane.showMessageDialog(null, "增加失败!"); 		  
 	   }
 	   else{
 		  try{
	 		   //将信息写入数据库
	 			 stmt.executeUpdate(strSQL);
	 			 JOptionPane.showMessageDialog(null, "增加成功!");
	 			 dispose();
	 		   }
	 		   catch(SQLException ex){
	 			   
	 		   }
 		     
 	   }
    }
    
    //退出窗体
    void AddCancel(MouseEvent e){
 	   dispose();
    }
    
    //按钮的监听者类
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
