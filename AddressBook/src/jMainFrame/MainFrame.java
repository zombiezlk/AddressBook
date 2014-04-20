package jMainFrame;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import jMenuSystem.*;
import jMenuInfo.*;

public class MainFrame extends JFrame{
	//创建JMenuBar,JMenu,JMenuItem的对象,用于构建菜单结构
	Object row[][]= new Object[0][4];
	String[] tableHeadName = {"姓名","电话", "地址","QQ"}; 
	DefaultTableModel tableModel = new DefaultTableModel(row,tableHeadName);
	JTable jTable=new JTable(tableModel);
	JScrollPane jScrollPane=new JScrollPane(jTable);
	JMenuBar jMenuBar=new JMenuBar();
	JMenu jMenuSystem=new JMenu();
	JMenu jMenuInfo=new JMenu();
	JMenuItem jMenuItemUserLogin=new JMenuItem();
	JMenuItem jMenuItemExit=new JMenuItem();	
	static JMenuItem jMenuItemUserManage=new JMenuItem();
	static JMenuItem jMenuItemAdd=new JMenuItem();
	static JMenuItem jMenuItemDelete=new JMenuItem();
	static JMenuItem jMenuItemQuery=new JMenuItem();
	static JMenuItem jMenuItemUpdate=new JMenuItem();
	
	//创建窗体
	public MainFrame(){
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
		try{
			Init();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	private void Init()throws Exception{
		//设定窗体大小
		this.setSize(new Dimension(800,500));
		
		//设定窗体可见
		this.setVisible(true);
		
		//设定各个菜单栏及菜单项的text或title属性
		this.setTitle("通讯录");
		jMenuSystem.setText("登录管理");
		jMenuItemUserLogin.setText("用户登录");
		jMenuItemExit.setText("退出");
		jMenuItemUserManage.setText("用户管理");
		jMenuInfo.setText("信息管理");
		jMenuItemAdd.setText("增加信息");
		jMenuItemDelete.setText("删除信息");
		jMenuItemQuery.setText("查询信息");
		jMenuItemUpdate.setText("修改信息");
		
		//注册各对象的事件监听器,将事件监听者类注册到相应对象上才可实现对各控件的事件响应
		this.addWindowListener(new MainFrame_this_windowAdapter(this));
		jMenuItemUserLogin.addActionListener(new MainFrame_jMenuItemUserLogin_actionAdapter(this));
		jMenuItemExit.addActionListener(new MainFrame_jMenuItemExit_actionAdapter(this));
		jMenuItemUserManage.addActionListener(new MainFrame_jMenuItemUserManage_actionAdapter(this));
		jMenuItemAdd.addActionListener(new MainFrame_jMenuItemAdd_actionAdapter(this));
		jMenuItemDelete.addActionListener(new MainFrame_jMenuItemDelete_actionAdapter(this));
		jMenuItemQuery.addActionListener(new MainFrame_jMenuItemQuery_actionAdapter(this));
		jMenuItemUpdate.addActionListener(new MainFrame_jMenuItemUpdate_actionAdapter(this));
		
		//将各个菜单栏及菜单项添加到各自所属容器中
	    jMenuBar.add(jMenuSystem);
	    jMenuBar.add(jMenuInfo);
	    jMenuSystem.add(jMenuItemUserLogin);
	    jMenuSystem.add(jMenuItemUserManage);
	    jMenuSystem.add(jMenuItemExit);
	    jMenuInfo.add(jMenuItemAdd);
	    jMenuInfo.add(jMenuItemQuery);	    
	    jMenuInfo.add(jMenuItemDelete);
	    jMenuInfo.add(jMenuItemUpdate);
	    
	    //设定主窗体的菜单为jMenuBar
	    this.setJMenuBar(jMenuBar);
	    this.getContentPane().add(jScrollPane,BorderLayout.CENTER);
	}
	   //设置权限,若为普通用户,则其只有查看通讯录的权限;若为管理员,则拥有所有权限
	   public static void setEnable(boolean a,boolean b,boolean c,boolean d,boolean e){
		jMenuItemAdd.setEnabled(a);
		jMenuItemDelete.setEnabled(b);
		jMenuItemUpdate.setEnabled(c);
		jMenuItemUserManage.setEnabled(d);
		jMenuItemQuery.setEnabled(e);
	   }
	   
	   //在表格中显示通讯录
	   public void showInTable() throws ClassNotFoundException, SQLException{
		   Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		   Connection conn = null;
		   conn = DriverManager.getConnection("jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ=E:\\web\\数据库\\mdb.mdb");
		   String strSQL="select * from AddressBook";
		   Statement stmt = null;
		   stmt=conn.createStatement();
		   ResultSet rs = null;
		   rs=stmt.executeQuery(strSQL);
		   rs.next();
		   for(int i=0;i<rs.getRow();i++){
			   String[] arr=new String[4];
			    arr[0]=rs.getString("NameInfo");
			    arr[1]=rs.getString("PhoneNumber");
			    arr[2]=rs.getString("Address");
			    arr[3]=rs.getString("QQ");
			    rs.next();
			    tableModel.addRow(arr);
		   }
		  
	   }
	   
       //在窗体打开时,先设定所有权限为false,每一个操作员必须先登录才可以进行下一步操作
	   public void this_windowOpend(WindowEvent e) throws ClassNotFoundException, SQLException{
		   setEnable(false,false,false,false,false);
		   showInTable();
		   UserLogin subfrm=new UserLogin();
	   }
	   
	   //打开登录窗体.用户在使用过程中也可以注销登录
	   public void jMenuItemUserLogin_actionPerformed(ActionEvent e){
		   UserLogin subfrm=new UserLogin();
	   }
	   
	   //退出应用程序
	   public void jMenuItemExit_actionPerformed(ActionEvent e){
		   System.exit(0);
	   }
	   
	   //打开用户管理界面
	   public void jMenuItemUserManage_actionPerformed(ActionEvent e){
		   UserManage subfrm=new UserManage();
	   }
	   //打开添加通讯信息窗体
	   public void jMenuItemAdd_actionPerformed(ActionEvent e){
		   jMenuItemAdd subfrm=new jMenuItemAdd();	
	   }
	   
	   //打开删除通讯信息窗体
	   public void jMenuItemDelete_actionPerformed(ActionEvent e){
		   jMenuItemDelete subfrm=new jMenuItemDelete();		
	   }
	   
	   //打开更新信息窗体
	   public void jMenuItemUpdate_actionPerformed(ActionEvent e){
		   jMenuItemUpdate subfrm=new jMenuItemUpdate();		   
	   }
	   
	   //打开查询通讯信息窗体
	   public void jMenuItemQuery_actionPerformed(ActionEvent e){
		   jMenuItemQuery subfrm=new jMenuItemQuery();		
	   }
	   
}

//各菜单项的监听者类
class MainFrame_jMenuItemUserLogin_actionAdapter implements java.awt.event.ActionListener{
	  MainFrame adaptee;
	  MainFrame_jMenuItemUserLogin_actionAdapter(MainFrame adaptee){
		  this.adaptee=adaptee;
	  }
public void actionPerformed(ActionEvent e){
	  adaptee.jMenuItemUserLogin_actionPerformed(e);
}
}

class  MainFrame_jMenuItemExit_actionAdapter implements java.awt.event.ActionListener{
   MainFrame adaptee;
   MainFrame_jMenuItemExit_actionAdapter(MainFrame adaptee){
	  this.adaptee=adaptee;
   }
public void actionPerformed(ActionEvent e){
    adaptee.jMenuItemExit_actionPerformed(e);
}
}

class  MainFrame_jMenuItemUserManage_actionAdapter implements java.awt.event.ActionListener{
	   MainFrame adaptee;
	   MainFrame_jMenuItemUserManage_actionAdapter(MainFrame adaptee){
		  this.adaptee=adaptee;
	   }
	public void actionPerformed(ActionEvent e){
	    adaptee.jMenuItemUserManage_actionPerformed(e);
	}
	}

class MainFrame_jMenuItemAdd_actionAdapter implements java.awt.event.ActionListener{
  MainFrame adaptee;
  MainFrame_jMenuItemAdd_actionAdapter(MainFrame adaptee){
    this.adaptee=adaptee;
  }
public void actionPerformed(ActionEvent e){
   adaptee.jMenuItemAdd_actionPerformed(e);
}
}

class MainFrame_jMenuItemDelete_actionAdapter implements java.awt.event.ActionListener{
  MainFrame adaptee;
  MainFrame_jMenuItemDelete_actionAdapter(MainFrame adaptee){
    this.adaptee=adaptee;
  }
public void actionPerformed(ActionEvent e){
   adaptee.jMenuItemDelete_actionPerformed(e);
}
}

class MainFrame_jMenuItemQuery_actionAdapter implements java.awt.event.ActionListener{
  MainFrame adaptee;
  MainFrame_jMenuItemQuery_actionAdapter(MainFrame adaptee){
    this.adaptee=adaptee;
  }
public void actionPerformed(ActionEvent e){
   adaptee.jMenuItemQuery_actionPerformed(e);
}
}

class MainFrame_jMenuItemUpdate_actionAdapter implements java.awt.event.ActionListener{
  MainFrame adaptee;
  MainFrame_jMenuItemUpdate_actionAdapter(MainFrame adaptee){
    this.adaptee=adaptee;
  }
public void actionPerformed(ActionEvent e){
   adaptee.jMenuItemUpdate_actionPerformed(e);
}
}

//主窗体的监听类
class MainFrame_this_windowAdapter extends java.awt.event.WindowAdapter{
 MainFrame adaptee;
 MainFrame_this_windowAdapter(MainFrame adaptee){
        this.adaptee=adaptee;
      }
 public void windowOpened(WindowEvent e){
	      try {
			adaptee.this_windowOpend(e);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
 }
}