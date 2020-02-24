package server.gui;

import info.User;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import messages.MessageLogin1Ack;
import messages.MessageLogin1Req;
import messages.MessageLogin3Ack;
import messages.MessageLogin3Req;
import messages.MessageMoneyAck;
import messages.MessageMoneyReq;
import messages.MessageUser1Ack;
import messages.MessageUser1Req;
import messages.MessageUserAck;
import messages.MessageUserReq;
import server.datebase.JPYDDatabase;

public class zhuce extends JFrame implements ActionListener{
	JTextField txt0= new JTextField();
	JTextField txt1= new JTextField();
	JTextField txt2= new JTextField();
	JTextField txt3= new JTextField();
	JTextField txt4= new JTextField();
	JButton a=new JButton("ȷ��ע��");
	JButton b=new JButton("���ص�¼");
	JComboBox question = new JComboBox();

	private String serverIP = "127.0.0.1";
	private int serverPort = 54321;

	private boolean isConnected = false;
	private Socket socket = null;
	private OutputStream os = null;
	private InputStream is = null;
	private static ObjectInputStream ois = null;
	private static ObjectOutputStream oos = null;

	public zhuce(){
		this.setTitle("ע��");
		this.setSize(750, 600);
		this.setLocation(300, 50);
		this.setLayout(null);
		
		String path = "������.jpg";
		ImageIcon background = new ImageIcon(path);
		JLabel beijing = new JLabel(background);
		beijing.setBounds(0, 0, this.getWidth(), this.getHeight()-35);
		JPanel imagePanel = (JPanel) this.getContentPane();
		imagePanel.setOpaque(false);
		this.getLayeredPane().add(beijing, new Integer(Integer.MIN_VALUE));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		question.setModel(new DefaultComboBoxModel(new String[] {"","�ҵ������ǣ�",
				"�ҵ��ֻ����ǣ�","�Ҵ�ѧ��ѧ���ǣ�","����"}));
		question.setBounds(300,340, 200, 30);
		 
		 JLabel name0 = new JLabel("��            ��");
		 name0.setAlignmentY(CENTER_ALIGNMENT);
		 name0.setBounds(200,100, 100, 30);
		 JLabel name1 = new JLabel("��            ��");
		 name1.setAlignmentY(CENTER_ALIGNMENT);
		 name1.setBounds(200,160, 100, 30);
		 JLabel name2 = new JLabel("��            ��");
		 name2.setAlignmentY(CENTER_ALIGNMENT);
		 name2.setBounds(200, 220, 100, 30);
		 JLabel name3 = new JLabel("��    ��    ֤");
		 name3.setAlignmentY(CENTER_ALIGNMENT);
		 name3.setBounds(200, 280, 100, 30);
		 JLabel name4 = new JLabel("��ѡ������");
		 name4.setAlignmentY(CENTER_ALIGNMENT);
		 name4.setBounds(200, 340, 100, 30);
		 JLabel name5 = new JLabel("��               ��");
		 name5.setAlignmentY(CENTER_ALIGNMENT);
		 name5.setBounds(200, 400, 100, 30);
		 
		 txt0.setBounds(300,100, 200, 30);
		 txt1.setBounds(300,160, 200, 30);
		 txt2.setBounds(300,220, 200, 30);
		 txt3.setBounds(300,280, 200, 30);
		 txt4.setBounds(300,400, 200, 30);
		 a.setBounds(220,470, 100, 30);
		 b.setBounds(380,470, 100, 30);
		 
		 this.add(name0);
		 this.add(name1);
		 this.add(name2);
		 this.add(name3);
		 this.add(name4);
		 this.add(name5);
		 this.add(txt0);
		 this.add(txt1);
		 this.add(txt2);
		 this.add(txt3);
		 this.add(txt4);
		 this.add(question);
		 this.add(a);
		 this.add(b);
		 
		 a.addActionListener(this);
		 b.addActionListener(this);
		 question.addActionListener(this);
		 connectToServer();
	}
	 public void actionPerformed(ActionEvent ae) {
		 if(ae.getSource()==a){
			 String bo1 =(String) question.getSelectedItem();
			 String account = txt0.getText();
			 User user = verifyAccount(account);
			 if(user!=null){
				JOptionPane.showMessageDialog(this, "�����˺��ظ�");
			 }
			 else if(txt0.getText().equals("")){
				 JOptionPane.showMessageDialog(this, "�˺�������������");
			 } 
			 else if(txt1.getText().equals("")){
				 JOptionPane.showMessageDialog(this, "����������������");
			 }
			 else if(txt2.getText().equals("")){
				 JOptionPane.showMessageDialog(this, "����������������");
			 } 
			 else if(txt3.getText().length() != 18||txt3.getText().equals("")){
				 JOptionPane.showMessageDialog(this, "���֤��������");
			 }
			 else if(bo1.equals("")){
				 JOptionPane.showMessageDialog(this, "�����ѡ��");
			 }
			 else if(txt4.getText().equals("")){
				 JOptionPane.showMessageDialog(this, "��������������");
			 } 
			 else {
				 UpUser( txt0.getText(), txt1.getText(), txt2.getText()  , txt3.getText()  , bo1 , txt4.getText() );
				
				 JOptionPane.showMessageDialog(this, "ע��ɹ�");
				 new login();
				 this.setVisible(false);
			 }
		 }
		 
		
		 
		 if(ae.getSource()==b){
			 new login();
			 this.setVisible(false);
		 }
	 }
	 private Object UpUser(String Account,String Pwd,String Name  ,String ID  ,String Question ,String Answer ){
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
			MessageUser1Req msgLoginReq = new MessageUser1Req(Account,Pwd,Name,ID,Question,Answer);
			MessageUser1Ack msgLoginAck = null;
			try {
				oos.writeObject(msgLoginReq);			
				msgLoginAck = (MessageUser1Ack) ois.readObject();;
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}finally{
			}		
			
			return (msgLoginAck != null ? msgLoginAck.getUser() : null);	
			
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
			MessageLogin3Req msgLoginReq = new MessageLogin3Req(account);
			MessageLogin3Ack msgLoginAck = null;
			try {
				oos.writeObject(msgLoginReq);			
				msgLoginAck = (MessageLogin3Ack) ois.readObject();;
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
