package check;

import java.awt.*; // ���J java.awt ���O�w�̪��Ҧ����O
import java.awt.event.*;
import java.util.*;

public class program extends Frame implements ItemListener , ActionListener
{
 static String dbname="nfc";
 static Calendar calendar = new GregorianCalendar();  //�إߤ@�� Calendar
 static Frame frm=new Frame();
 static Label lab1=new Label();
 static Label lab2=new Label();
 static Label lab3=new Label();
 static Label lab4=new Label();
 static Label lab5=new Label();
 static Label lab6=new Label();
 static Label lab7=new Label();
 static TextArea ta1=new TextArea();
 static Checkbox ckb1=new Checkbox(""); 
 static TextField txf1=new TextField(); 
 static Button btn=new Button("�M��"); 
 static Button btn1=new Button("ñ��");
 static String c="insert into checkout(serial,year,month,day,hr,min,sec) VALUES(?,?,?,?,?,?,?)";
 static String d = "select * from checkout where serial ="; 

 public static void main(String args[])
  {
	jdbc db = new jdbc(dbname,"root","50150"); 
	txf1.requestFocus();
	program frm = new program();
	frm.setVisible(true); 
	frm.setLayout(null); // ���������t�m
    frm.setSize(600,600); // �]�w����������
    //frm.setBackground(new Color(255,102,204));   // �]�w�I���C��
    frm.setLocation(500,300); // �]�w��������m
    frm.add(lab1); // �N���Ҫ��� lab �[�J������
    frm.add(lab2);
    frm.add(lab3);
    frm.add(lab4);
    frm.add(lab5);
    frm.add(lab6);
    frm.add(lab7);
    frm.add(ta1);
    frm.add(txf1);
    frm.add(btn);
    frm.add(btn1);
   // frm.add(ckb1); 
    frm.addWindowListener(new WindowAdapter(){
	   public void windowClosing(WindowEvent e){System.exit(0);}}); 
    txf1.requestFocus();
    lab1.setText("�{�b�ɶ�:"); 
    lab2.setText("����ɶ���..."); 
    lab3.setText("�ثe�s������Ʈw:");
    lab4.setText(dbname);
    lab5.setText("Ū�����d��:");
    lab6.setText("����");
    lab1.setLocation(10,50);
    lab1.setSize(100,20); 
    lab2.setLocation(110,50);
    lab2.setSize(500,20); 
    lab3.setLocation(10,100);
    lab3.setSize(160,20);
    lab4.setLocation(200,100);
    lab4.setSize(150,20);
    lab5.setLocation(10,150);
    lab5.setSize(100,20);
    lab6.setLocation(10,250);
    lab6.setSize(100,30);
    lab7.setLocation(10,280);
    lab7.setSize(500,30);
    ta1.setLocation(10,310);
    ta1.setSize(500,200);
    lab1.setFont(new Font("Serief",Font.BOLD,18)); 
    lab2.setFont(new Font("Serief",Font.BOLD,18)); 
    lab3.setFont(new Font("Serief",Font.BOLD,18));
    lab4.setFont(new Font("Serief",Font.BOLD,18));
    lab5.setFont(new Font("Serief",Font.BOLD,18));
    lab6.setFont(new Font("Serief",Font.BOLD,18));
    lab7.setFont(new Font("Serief",Font.BOLD,20));
    ta1.setFont(new Font("Serief",Font.BOLD,20));
    txf1.setFont(new Font("Serief",Font.BOLD,20));
    btn.setFont(new Font("Serief",Font.BOLD,20));
    btn1.setFont(new Font("Serief",Font.BOLD,20));
    btn.setForeground(Color.red);
    txf1.setBounds(120,150,200,30); 
    btn.setBounds(360,150,60,30);
    btn1.setBounds(150,200,60,60);
    ckb1.setBounds(160,270,10,10);
    btn.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        	txf1.setText(" ");
        	txf1.requestFocus();
                }});
    btn1.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        	if(txf1.getText().length()>10)
        	{
        		lab7.setText("�d�����Ů榡!");
        		lab7.setForeground(Color.BLUE);
        	}
        	else{
        	db.insertTable(c,txf1.getText(),calendar.get(Calendar.YEAR),(calendar.get(Calendar.MONTH)+1),
        			calendar.get(Calendar.DATE),calendar.get(Calendar.HOUR_OF_DAY),
        			calendar.get(Calendar.MINUTE),calendar.get(Calendar.SECOND)
        			);
        	lab7.setText("    �W��ñ��ɶ�");
        	ta1.setText(db.SelectTable(d,txf1.getText()));
        	
        	
        	}
                }});
    while(true)
    {
    	 setTime();
    	 lab2.setText(calendar.get(Calendar.YEAR)+"�~"+(calendar.get(Calendar.MONTH)+1)+"��"+
    			 calendar.get(Calendar.DATE)+"��"+ calendar.get(Calendar.HOUR_OF_DAY)+"��"+
    			 calendar.get(Calendar.MINUTE)+"��"+calendar.get(Calendar.SECOND)+"��");
    
  }
     	
  }
 
 public void actionPerformed(ActionEvent e)
 {

	    
 }

 
 
 private static void setTime()  //�]�w�t�ήɶ�
 {
     calendar.setTimeInMillis( System.currentTimeMillis() );
 }

@Override
public void itemStateChanged(ItemEvent arg0) {
	// TODO Auto-generated method stub
	
}
 }

