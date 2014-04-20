package jMenuInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class jMenuItemDelete extends JFrame{
	Container cp=getContentPane();
	JPanel panel=new JPanel();	
	JLabel label1=new JLabel("输入姓名");
	JLabel label2=new JLabel("电话");
	JLabel label3=new JLabel("地址");
	JLabel label4=new JLabel("QQ");
	JTextField textfield1=new JTextField(5);
	JTextField textfield2=new JTextField(11);
	JTextField textfield3=new JTextField(20);
	JTextField textfield4=new JTextField(13);
	JButton button1=new JButton("确定");
	JButton button2=new JButton("删除");
	JButton button3=new JButton("取消");

	public jMenuItemDelete(){
		jMenuItemDeleteInit("删除信息");
	}
	
	public void jMenuItemDeleteInit(String title){
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
		
		//注册事件监听器
		   button1.addMouseListener(new ML8(this));
		   button2.addMouseListener(new ML9(this));
		   button3.addMouseListener(new ML0(this));
	}
	
	   //连接数据库访问
		void DeleteSearch(MouseEvent e) throws ClassNotFoundException{
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
	    void DeleteCancel (MouseEvent e){
	 	   dispose();
	    }
	    
	    //执行更新操作
	    void delete(MouseEvent e) throws ClassNotFoundException{
	  	   Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	  	   Connection conn = null;
	  	   try {
	 			conn = DriverManager.getConnection("jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ=E:\\web\\数据库\\mdb.mdb");
	 		} catch (SQLException e2) {
	 			// TODO Auto-generated catch block
	 			e2.printStackTrace();
	 		}
	  	   String strSQL="delete from AddressBook where NameInfo='"+textfield1.getText().trim()+"'";     
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
	  	   JOptionPane.showMessageDialog(null, "删除成功!");
	  	   dispose();
	    }
	    
	    //按钮的监听者类
	    class ML8 extends java.awt.event.MouseAdapter{
	       jMenuItemDelete adaptee;
	 	   ML8(jMenuItemDelete adaptee){
	 		   this.adaptee=adaptee;
	 		   }
	 	   public void mouseClicked(MouseEvent e){
	 		   try {
					adaptee.DeleteSearch(e);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	 	   }
	    }
	    
	    class ML9 extends java.awt.event.MouseAdapter{
	       jMenuItemDelete adaptee;
	 	   ML9(jMenuItemDelete adaptee){
	 		   this.adaptee=adaptee;
	 		   }
	 	   public void mouseClicked(MouseEvent e){
	 		   try {
				adaptee.delete(e);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	 	   }
	    }
	    
	    class ML0 extends java.awt.event.MouseAdapter{
	       jMenuItemDelete adaptee;
	  	   ML0(jMenuItemDelete adaptee){
	  		   this.adaptee=adaptee;
	  		   }
	  	   public void mouseClicked(MouseEvent e){
	  		   adaptee.DeleteCancel(e);
	  	   }
	     }

}
