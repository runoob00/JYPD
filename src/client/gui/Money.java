package client.gui;

import info.OrderForm;
import info.User;

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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import messages.MessageLogin1Ack;
import messages.MessageLogin1Req;
import messages.MessageMoneyAck;
import messages.MessageMoneyReq;
import server.datebase.JPYDDatabase;

public class Money extends JFrame implements ActionListener{
     String [] num= new String[5];
     int [] mon= new int [3];
	 JButton a=new JButton("充       值");
	 JButton b=new JButton("退       出");
	 JButton c=new JButton("确       定");
	 JButton d=new JButton("返       回");
	 JTextField txt= new JTextField();

		private String serverIP = "127.0.0.1";
		private int serverPort = 54321;

		private boolean isConnected = false;
		private Socket socket = null;
		private OutputStream os = null;
		private InputStream is = null;
		private static ObjectInputStream ois = null;
		private static ObjectOutputStream oos = null;

	public void Money(String account){
		final JPanel pr= new JPanel();
		final JPanel pr1= new JPanel();
		this.setTitle("充值");
		this.setSize(400,220);
		this.setLocation(500, 250);
		this.setLayout(null);
		this.setVisible(true);
		pr.setSize(400,220);
		pr.setLayout(null);
		pr.setVisible(true);
		pr1.setSize(400,220);
		pr1.setLayout(null);
		pr1.setVisible(false );
		
		JLabel name1 = new JLabel("姓    名   ：");
		name1.setAlignmentY(CENTER_ALIGNMENT);
		name1.setBounds(100,20, 100, 30);
		JLabel name2 = new JLabel("余    额   ：");
		name2.setAlignmentY(CENTER_ALIGNMENT);
		name2.setBounds(100,70, 100, 30);
		JLabel name3 = new JLabel();
		name3.setAlignmentY(CENTER_ALIGNMENT);
		name3.setBounds(200,20, 100, 30);
		final JLabel name4 = new JLabel();
		name4.setAlignmentY(CENTER_ALIGNMENT);
		name4.setBounds(200,70, 100, 30);
		JLabel name5 = new JLabel("余     额   ：");
		name5.setAlignmentY(CENTER_ALIGNMENT);
		name5.setBounds(100,20, 100, 30);
		JLabel name6 = new JLabel("充值金额 ：");
		name6.setAlignmentY(CENTER_ALIGNMENT);
		name6.setBounds(100,70, 100, 30);
		final JLabel name7 = new JLabel();
		name7.setAlignmentY(CENTER_ALIGNMENT);
		name7.setBounds(200,20, 100, 30);
		JLabel name8 = new JLabel("元");
		name8.setAlignmentY(CENTER_ALIGNMENT);
		name8.setBounds(250,20, 100, 30);
		JLabel name9 = new JLabel("元");
		name9.setAlignmentY(CENTER_ALIGNMENT);
		name9.setBounds(250,70, 100, 30);

		a.setBounds(70,120, 100, 30);
		b.setBounds(220,120, 100, 30);
		c.setBounds(70,120, 100, 30);
		d.setBounds(220,120, 100, 30);
		txt.setBounds(180,70, 50, 30);
		
		pr.add(name1);
		pr.add(name2);
		pr.add(name3);
		pr.add(name4);
		pr.add(a);
		pr.add(b);
		pr1.add(name5);
		pr1.add(name6);
		pr1.add(name7);
		pr1.add(name8);
		pr1.add(txt);
		pr1.add(c);
		pr1.add(d);
		this.add(name9);
		this.add(pr);
		this.add(pr1);
		
		 User user = verifyAccount(account);
		 num[0]=user.getName();
		 num[1]=user.getAccount();
		 num[2]=user.getMoney();
		 name3.setText(num[0]);
		 name4.setText(num[2]);
		 name7.setText(num[2]);
		
		a.addActionListener(this);
		b.addActionListener(this);
		a.addMouseListener(new MouseAdapter(){
			 public void mouseClicked(MouseEvent e){
				 pr.setVisible(false);
				 pr1.setVisible(true);
	}
		});
		c.addMouseListener(new MouseAdapter(){
			 public void mouseClicked(MouseEvent e){
				 mon[0]= Integer.parseInt( num[2]); 
				 mon[1]=Integer.parseInt(txt.getText())+mon[0];
				 num[3]= String.valueOf( mon[1]);
				 UpMoney(num[1],num[3]);
				  User user = verifyAccount(num[1]);
				  if(Integer.parseInt(user.getMoney())==mon[1]){
						 mon[0]=mon[1];
						 num[2]=num[3];
						 name4.setText(num[2]);
						 name7.setText(num[2]);
					 }
					 pr.setVisible(true);
					 pr1.setVisible(false);
					 txt.setText(null);
				
	}
		});
		d.addMouseListener(new MouseAdapter(){
			 public void mouseClicked(MouseEvent e){
				 pr.setVisible(true);
				 pr1.setVisible(false);
	}
		});
		
		 connectToServer();
	}
	public void actionPerformed(ActionEvent ae){
		if(ae.getSource()==b){
			 this.dispose();
		}
			
		}
	 private Object UpMoney(String account,String Money){
		 if (!isConnected) {
				int maxRetry = 3, j = 0;
				while (j++ < maxRetry) {
					if(connectToServer())
						break; // 连接成功
				}
			}
			
			// 如果没有与服务器建立连接，提示错误
			if (!isConnected)		{
				JOptionPane.showMessageDialog(this, "Failed to connect to server: " + serverIP);
				return null;
			}

			// 与服务器通信，验证用户
			MessageMoneyReq msgLoginReq = new MessageMoneyReq(account,Money);
			MessageMoneyAck msgLoginAck = null;
			try {
				oos.writeObject(msgLoginReq);			
				msgLoginAck = (MessageMoneyAck) ois.readObject();;
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
						break; // 连接成功
				}
			}
			
			// 如果没有与服务器建立连接，提示错误
			if (!isConnected)		{
				JOptionPane.showMessageDialog(this, "Failed to connect to server: " + serverIP);
				return null;
			}

			// 与服务器通信，验证用户
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
			// Socket尚未初始化，在端口打开连接，取出对象输入输出流
			try {
				socket = new Socket(serverIP, serverPort);
				socket.setSoTimeout(3000); // 如果服务器没有反映，尝试3000毫秒
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
