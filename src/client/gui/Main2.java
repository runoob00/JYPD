package client.gui;

import info.User;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import messages.MessageLogin1Ack;
import messages.MessageLogin1Req;
import server.datebase.JPYDDatabase;

public class Main2 extends JFrame implements ActionListener{
	String [] city= new String[8];
	JButton a=new JButton("��      ��");
	JButton b=new JButton("˫      ��");
	JButton c=new JButton("��      ѯ");
	JComboBox City1 = new JComboBox();
	JComboBox City2 = new JComboBox();
	JComboBox Year1 = new JComboBox();
	JComboBox Year2 = new JComboBox();
	JComboBox Month1 = new JComboBox();
	JComboBox Month2 = new JComboBox();
	JComboBox Day1 = new JComboBox();
	JComboBox Day2 = new JComboBox();	

	private String serverIP = "127.0.0.1";
	private int serverPort = 54321;

	private boolean isConnected = false;
	private Socket socket = null;
	private OutputStream os = null;
	private InputStream is = null;
	private static ObjectInputStream ois = null;
	private static ObjectOutputStream oos = null;

	public void JieMian1(final String account ){
		city[5]="1";
		
		final JPanel pr= new JPanel();
		User user = verifyAccount(account);
		city[6]=user.getName();
		city[7]=user.getAccount();
		this.setTitle("��ƱԤ��");
		this.setSize(420, 600);
		this.setLocation(300, 50);
		this.setLayout(null);
		pr.setSize(750,600);
		pr.setLayout(null);
		pr.setVisible(false );
		
		String path = "������1.jpg";
		ImageIcon background = new ImageIcon(path);
		JLabel beijing = new JLabel(background);
		beijing.setBounds(0, 0, this.getWidth(), this.getHeight()-35);
		JLabel beijing1 = new JLabel(background);
		beijing1.setBounds(0, 0, this.getWidth(), this.getHeight()-35);
		JPanel imagePanel = (JPanel) this.getContentPane();
		imagePanel.setOpaque(false);
		this.getLayeredPane().add(beijing, new Integer(Integer.MIN_VALUE));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		City1.setModel(new DefaultComboBoxModel(new String[] {"��ѡ�����","����",
				"����","�Ϻ�"}));
		City1.setBounds(80,180, 200, 30);
		City2.setModel(new DefaultComboBoxModel(new String[] {"��ѡ�����","����",
				"����","�Ϻ�"}));
		City2.setBounds(80,240, 200, 30);
		Year1.setModel(new DefaultComboBoxModel(new String[] {"","2015",
				"2016","2017","2018"}));
		Year1.setBounds(80,300, 70, 30);
		Year2.setModel(new DefaultComboBoxModel(new String[] {"","2015",
		"2016","2017","2018"}));
        Year2.setBounds(80,360, 70, 30);
		Month1.setModel(new DefaultComboBoxModel(new String[] {"","01","02","03","04","05","06","07","08",
				"09","10","11","12"}));
		Month1.setBounds(175,300, 50, 30);
		Month2.setModel(new DefaultComboBoxModel(new String[] {"","01","02","03","04","05","06","07","08",
				"09","10","11","12"}));
		Month2.setBounds(175,360, 50, 30);
		Day1.setModel(new DefaultComboBoxModel(new String[]{"","01", "02", "03", "04", "05", "06","07", "08", "09", "10", "11", "12","13",
	    		"14", "15", "16", "17", "18","19", "20", "21", "22", "23", "24","25", "26", "27", "28", "29", "30","31" }));
		Day1.setBounds(250,300, 50, 30);
		Day2.setModel(new DefaultComboBoxModel(new String[]{"","01", "02", "03", "04", "05", "06","07", "08", "09", "10", "11", "12","13",
	    		"14", "15", "16", "17", "18","19", "20", "21", "22", "23", "24","25", "26", "27", "28", "29", "30","31" }));
		Day2.setBounds(250,360, 50, 30);
		
		JLabel name = new JLabel(user.getName());
		name.setAlignmentY(CENTER_ALIGNMENT);
		name.setForeground(Color.blue);
		name.setBounds(600, 10, 50, 25);
		JLabel name0 = new JLabel("��   ��");
		name0.setAlignmentY(CENTER_ALIGNMENT);
		name0.setForeground(Color.blue);
		name0.setBounds(670, 10, 100, 25);
		JLabel name1 = new JLabel("��������");
		name1.setAlignmentY(CENTER_ALIGNMENT);
		name1.setBounds(80, 140, 100, 50);
		JLabel name2 = new JLabel("ȥ������");
		name2.setAlignmentY(CENTER_ALIGNMENT);
		name2.setBounds(80, 200, 100, 50);
		JLabel name3 = new JLabel("��������");
		name3.setAlignmentY(CENTER_ALIGNMENT);
		name3.setBounds(80, 260, 100, 50);
		JLabel name4 = new JLabel("��������");
		name4.setAlignmentY(CENTER_ALIGNMENT);
		name4.setBounds(80, 320, 100, 50);
		JLabel name5 = new JLabel("��");
		name5.setAlignmentY(CENTER_ALIGNMENT);
		name5.setBounds(155,300, 50, 30);
		JLabel name6 = new JLabel("��");
		name6.setAlignmentY(CENTER_ALIGNMENT);
		name6.setBounds(230, 300, 50, 30);
		JLabel name7 = new JLabel("��");
		name7.setAlignmentY(CENTER_ALIGNMENT);
		name7.setBounds(305, 300, 50, 30);
		JLabel name8 = new JLabel("��");
		name8.setAlignmentY(CENTER_ALIGNMENT);
		name8.setBounds(155,360, 50, 30);
		JLabel name9 = new JLabel("��");
		name9.setAlignmentY(CENTER_ALIGNMENT);
		name9.setBounds(230, 360, 50, 30);
		JLabel name10 = new JLabel("��");
		name10.setAlignmentY(CENTER_ALIGNMENT);
		name10.setBounds(305, 360, 50, 30);
		 
		 
		 
		a.setBounds(90, 100, 90, 30);
		b.setBounds(200, 100, 90, 30);
		c.setBounds(150, 420, 100, 30);
		a.setEnabled(false);
		 
		this.add(a);
		this.add(b);
		this.add(c);
		this.add(City1);
		this.add(City2);
		this.add(name);
		this.add(name0);
		this.add(name1);
		this.add(name2);
		this.add(name3);
		this.add(name5);
		this.add(name6);
		this.add(name7);
		this.add(Year1);
		this.add(Month1);
		this.add(Day1);
		pr.add(Year2);
		pr.add(Month2);
		pr.add(Day2);
		pr.add(name4);
		pr.add(name8);
		pr.add(name9);
		pr.add(name10);
		pr.add(beijing1);
		this.add(pr);
		
		a.addActionListener(this);
		 b.addActionListener(this);
		c.addActionListener(this);
		
		 name.addMouseListener(new MouseAdapter(){
			 public void mouseClicked(MouseEvent e){
				 DingDan1  f1=new DingDan1();
					f1.DingDan1(city[7],city[6]);
				 //�ҵ���ǰ���ڲ��ر�
					Component cmp= e.getComponent();
	            	while(!(cmp instanceof JFrame ) || cmp.getParent() !=null ){
	            	 cmp = cmp.getParent();
	            	}
	            	((JFrame)cmp).dispose();
	            	
			 }
		 });
		 name0.addMouseListener(new MouseAdapter(){
			 public void mouseClicked(MouseEvent e){
				 Money  f2=new Money();
					f2.Money(account);
	            	
			 }
		 });
		 a.addMouseListener(new MouseAdapter(){
			 public void mouseClicked(MouseEvent e){
				 city[5]="1";
				 pr.setVisible(false);
				 a.setEnabled(false);
				 b.setEnabled(true);
			 }
		 });
		 b.addMouseListener(new MouseAdapter(){
			 public void mouseClicked(MouseEvent e){
				 city[5]="2";
				 pr.setVisible(true);
				 a.setEnabled(true);
				 b.setEnabled(false);
			 }
		 });
		 connectToServer();
	}
	 public void actionPerformed(ActionEvent ae) {
		 if(ae.getSource()==c){

			 String bo1 =(String) Year1.getSelectedItem();
			 String bo2 =(String) Month1.getSelectedItem();
			 String bo3 =(String) Day1.getSelectedItem();
			 String bo4=(String) Year2.getSelectedItem();
			 String bo5 =(String) Month2.getSelectedItem();
			 String bo6 =(String) Day2.getSelectedItem();
			 String bo7 =(String) City1.getSelectedItem();
			 String bo8 =(String) City2.getSelectedItem();
			 if(city[5].equals("2")){
			 if(bo7.equals("��ѡ�����")){
				 JOptionPane.showMessageDialog(this, "��ѡ��������У�");
			 }
			 else if(bo8.equals("��ѡ�����")){
				 JOptionPane.showMessageDialog(this, "��ѡ��ȥ�����У�");
			 }
			 else if(bo1.equals("")||bo2.equals("")||bo3.equals("")){
				 JOptionPane.showMessageDialog(this, "��ѡ�����ڣ�");
			 }
			 else if(bo4.equals("")||bo5.equals("")||bo6.equals("")){
				 JOptionPane.showMessageDialog(this, "��ѡ�����ڣ�");
			 }
			 else{
				 research1  f2=new research1();
					f2.ChaXun1(bo7,bo8,bo1,bo2,bo3,bo4,bo5,bo6,city[6],city[7]);
					this.dispose();
			 }
			 }
			 if(city[5].equals("1")){
				 if(bo7.equals("��ѡ�����")){
					 JOptionPane.showMessageDialog(this, "��ѡ��������У�");
				 }
				 else if(bo8.equals("��ѡ�����")){
					 JOptionPane.showMessageDialog(this, "��ѡ��ȥ�����У�");
				 }
				 else if(bo1.equals("")||bo2.equals("")||bo3.equals("")){
					 JOptionPane.showMessageDialog(this, "��ѡ�����ڣ�");
				 }
				 else{
					 research1  f2=new research1();
						f2.ChaXun1(bo7,bo8,bo1,bo2,bo3,bo4,bo5,bo6,city[6],city[7]);
						this.dispose();
				 }
				 }
			 
		 }
		
	 }
	 private User verifyAccount(String account) {
		 if (!isConnected) {
				int maxRetry = 3, j = 0;
				while (j++ < maxRetry) {
					if(connectToServer())
						break; // ���ӳɹ�
				}
			}
			
			// ���û����������������ӣ���ʾ����
			if (!isConnected)		{
				JOptionPane.showMessageDialog(this, "Failed to connect to server: " + serverIP);
				return null;
			}

			// �������ͨ�ţ���֤�û�
			MessageLogin1Req msgLoginReq = new MessageLogin1Req(account);
			MessageLogin1Ack msgLoginAck = null;
			try {
				oos.writeObject(msgLoginReq);			
				msgLoginAck = (MessageLogin1Ack) ois.readObject();;
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}finally{
			}		
			
			return (msgLoginAck != null ? msgLoginAck.getUser() : null);
		}
	 private boolean connectToServer() {
			// Socket��δ��ʼ�����ڶ˿ڴ����ӣ�ȡ���������������
			try {
				socket = new Socket(serverIP, serverPort);
				socket.setSoTimeout(3000); // ���������û�з�ӳ������3000����
				os = socket.getOutputStream();
				is = socket.getInputStream();
				oos = new ObjectOutputStream(os);
				ois = new ObjectInputStream(is);

				isConnected = true;
			} catch (IOException e) {
				isConnected = false;
			} finally {
			}

			return isConnected;
		}
}
