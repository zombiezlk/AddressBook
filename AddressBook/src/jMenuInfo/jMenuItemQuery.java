package jMenuInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class jMenuItemQuery extends JFrame{
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
	JButton button1=new JButton("查询");
	JButton button2=new JButton("取消");
	
	public jMenuItemQuery(){
		jMenuItemQueryInit("查询信息");
	}
	
	public void jMenuItemQueryInit(String title){
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
		   button1.addMouseListener(new ML1(this));
		   button2.addMouseListener(new ML2(this));
	}
	
	   //连接数据库访问
	       void query(MouseEvent e) throws ClassNotFoundException{
	    	   Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	    	   Connection conn = null;
	    	   try {
				conn = DriverManager.getConnection("jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ=E:\\web\\数据库\\mdb.mdb");
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
	    		   JOptionPane.showMessageDialog(null, "信息不存在!");
	    	   }
	    	   else{
	    		   try{
	    		   //将信息集中显示在对应文本框中
	   		   
	    		   textfield2.setText(rs.getString("PhoneNumber"));
	    		   textfield3.setText(rs.getString("Address"));
	    		   textfield4.setText(rs.getString("QQ"));
	    		   }
	    		   catch(SQLException ex){
	    			   
	    		   }
	    	   }
	       }
	       
	       //退出窗体
	       void QueryCancel(MouseEvent e){
	    	   dispose();
	       }
	       
	       //按钮的监听者类
	       class ML1 extends java.awt.event.MouseAdapter{
	    	   jMenuItemQuery adaptee;
	    	   ML1(jMenuItemQuery adaptee){
	    		   this.adaptee=adaptee;
	    		   }
	    	   public void mouseClicked(MouseEvent e){
	    		   try {
					adaptee.query(e);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    	   }
	       }
	       
	       class ML2 extends java.awt.event.MouseAdapter{
	    	   jMenuItemQuery adaptee;
	    	   ML2(jMenuItemQuery adaptee){
	    		   this.adaptee=adaptee;
	    		   }
	    	   public void mouseClicked(MouseEvent e){
	    		   adaptee.QueryCancel(e);
	    	   }
	       }

}
