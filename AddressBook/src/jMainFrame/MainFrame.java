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
	//����JMenuBar,JMenu,JMenuItem�Ķ���,���ڹ����˵��ṹ
	Object row[][]= new Object[0][4];
	String[] tableHeadName = {"����","�绰", "��ַ","QQ"}; 
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
	
	//��������
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
		//�趨�����С
		this.setSize(new Dimension(800,500));
		
		//�趨����ɼ�
		this.setVisible(true);
		
		//�趨�����˵������˵����text��title����
		this.setTitle("ͨѶ¼");
		jMenuSystem.setText("��¼����");
		jMenuItemUserLogin.setText("�û���¼");
		jMenuItemExit.setText("�˳�");
		jMenuItemUserManage.setText("�û�����");
		jMenuInfo.setText("��Ϣ����");
		jMenuItemAdd.setText("������Ϣ");
		jMenuItemDelete.setText("ɾ����Ϣ");
		jMenuItemQuery.setText("��ѯ��Ϣ");
		jMenuItemUpdate.setText("�޸���Ϣ");
		
		//ע���������¼�������,���¼���������ע�ᵽ��Ӧ�����ϲſ�ʵ�ֶԸ��ؼ����¼���Ӧ
		this.addWindowListener(new MainFrame_this_windowAdapter(this));
		jMenuItemUserLogin.addActionListener(new MainFrame_jMenuItemUserLogin_actionAdapter(this));
		jMenuItemExit.addActionListener(new MainFrame_jMenuItemExit_actionAdapter(this));
		jMenuItemUserManage.addActionListener(new MainFrame_jMenuItemUserManage_actionAdapter(this));
		jMenuItemAdd.addActionListener(new MainFrame_jMenuItemAdd_actionAdapter(this));
		jMenuItemDelete.addActionListener(new MainFrame_jMenuItemDelete_actionAdapter(this));
		jMenuItemQuery.addActionListener(new MainFrame_jMenuItemQuery_actionAdapter(this));
		jMenuItemUpdate.addActionListener(new MainFrame_jMenuItemUpdate_actionAdapter(this));
		
		//�������˵������˵�����ӵ���������������
	    jMenuBar.add(jMenuSystem);
	    jMenuBar.add(jMenuInfo);
	    jMenuSystem.add(jMenuItemUserLogin);
	    jMenuSystem.add(jMenuItemUserManage);
	    jMenuSystem.add(jMenuItemExit);
	    jMenuInfo.add(jMenuItemAdd);
	    jMenuInfo.add(jMenuItemQuery);	    
	    jMenuInfo.add(jMenuItemDelete);
	    jMenuInfo.add(jMenuItemUpdate);
	    
	    //�趨������Ĳ˵�ΪjMenuBar
	    this.setJMenuBar(jMenuBar);
	    this.getContentPane().add(jScrollPane,BorderLayout.CENTER);
	}
	   //����Ȩ��,��Ϊ��ͨ�û�,����ֻ�в鿴ͨѶ¼��Ȩ��;��Ϊ����Ա,��ӵ������Ȩ��
	   public static void setEnable(boolean a,boolean b,boolean c,boolean d,boolean e){
		jMenuItemAdd.setEnabled(a);
		jMenuItemDelete.setEnabled(b);
		jMenuItemUpdate.setEnabled(c);
		jMenuItemUserManage.setEnabled(d);
		jMenuItemQuery.setEnabled(e);
	   }
	   
	   //�ڱ������ʾͨѶ¼
	   public void showInTable() throws ClassNotFoundException, SQLException{
		   Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		   Connection conn = null;
		   conn = DriverManager.getConnection("jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ=E:\\web\\���ݿ�\\mdb.mdb");
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
	   
       //�ڴ����ʱ,���趨����Ȩ��Ϊfalse,ÿһ������Ա�����ȵ�¼�ſ��Խ�����һ������
	   public void this_windowOpend(WindowEvent e) throws ClassNotFoundException, SQLException{
		   setEnable(false,false,false,false,false);
		   showInTable();
		   UserLogin subfrm=new UserLogin();
	   }
	   
	   //�򿪵�¼����.�û���ʹ�ù�����Ҳ����ע����¼
	   public void jMenuItemUserLogin_actionPerformed(ActionEvent e){
		   UserLogin subfrm=new UserLogin();
	   }
	   
	   //�˳�Ӧ�ó���
	   public void jMenuItemExit_actionPerformed(ActionEvent e){
		   System.exit(0);
	   }
	   
	   //���û��������
	   public void jMenuItemUserManage_actionPerformed(ActionEvent e){
		   UserManage subfrm=new UserManage();
	   }
	   //�����ͨѶ��Ϣ����
	   public void jMenuItemAdd_actionPerformed(ActionEvent e){
		   jMenuItemAdd subfrm=new jMenuItemAdd();	
	   }
	   
	   //��ɾ��ͨѶ��Ϣ����
	   public void jMenuItemDelete_actionPerformed(ActionEvent e){
		   jMenuItemDelete subfrm=new jMenuItemDelete();		
	   }
	   
	   //�򿪸�����Ϣ����
	   public void jMenuItemUpdate_actionPerformed(ActionEvent e){
		   jMenuItemUpdate subfrm=new jMenuItemUpdate();		   
	   }
	   
	   //�򿪲�ѯͨѶ��Ϣ����
	   public void jMenuItemQuery_actionPerformed(ActionEvent e){
		   jMenuItemQuery subfrm=new jMenuItemQuery();		
	   }
	   
}

//���˵���ļ�������
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

//������ļ�����
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