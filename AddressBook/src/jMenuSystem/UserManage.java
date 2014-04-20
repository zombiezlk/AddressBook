package jMenuSystem;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class UserManage extends JFrame{
	Container cp=getContentPane();
	JLabel label=new JLabel("������ͨ�û�Ȩ��");
	JCheckBox checkbox1=new JCheckBox("������Ϣ");
	JCheckBox checkbox2=new JCheckBox("��ѯ��Ϣ");
	JCheckBox checkbox3=new JCheckBox("ɾ����Ϣ");
	JCheckBox checkbox4=new JCheckBox("�޸���Ϣ");
	JButton button=new JButton("ȷ��");
	public static boolean query=false,delete=false,update=false,add=true;
  public UserManage(){
	  UserManageInit("�û�����");
  }
  
  public void UserManageInit(String title){
	  this.setTitle(title);
	  cp.setLayout(new FlowLayout());
	  cp.add(label);
	  cp.add(checkbox1);
	  cp.add(checkbox2);
	  cp.add(checkbox3);
	  cp.add(checkbox4);
	  cp.add(button);
	  setVisible(true);
	  setSize(200,180);
	  
	  //ע���¼�������
	   checkbox1.addItemListener(new IL1());
	   checkbox2.addItemListener(new IL2());
	   checkbox3.addItemListener(new IL3());
	   checkbox4.addItemListener(new IL4());
	   button.addMouseListener(new ML());
  }
     
     //��Ӧ��Ȩ���޸��¼�
     void AddRight(ItemEvent e){
    	 add=true;
     }
     
     void QueryRight(ItemEvent e){
    	 query=true;
     }
     
     void DeleteRight(ItemEvent e){
    	 delete=true;
     }
     
     void UpdateRight(ItemEvent e){
    	 update=true;
     }
     
     void UserManageConfirm(MouseEvent e){
    	 dispose();
     }
     
     //��ѡ��Ͱ�ť�ļ�������
     class IL1 implements ItemListener{
    	 public void itemStateChanged(ItemEvent e){
    		 AddRight(e);
    	 }
     }
     
     class IL2 implements ItemListener{
    	 public void itemStateChanged(ItemEvent e){
    		 QueryRight(e);
    	 }
     }
     
     class IL3 implements ItemListener{
    	 public void itemStateChanged(ItemEvent e){
    		 DeleteRight(e);
    	 }
     }
     
     class IL4 implements ItemListener{
    	 public void itemStateChanged(ItemEvent e){
    		 UpdateRight(e);
    	 }
     }
     
     class ML extends MouseAdapter{
    	 public void mouseClicked(MouseEvent e){
    		 UserManageConfirm(e);
  	   }
     }
}
