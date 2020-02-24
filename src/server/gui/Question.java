package server.gui;

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

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import messages.MessageLogin1Ack;
import messages.MessageLogin1Req;
import messages.MessageMoneyAck;
import messages.MessageMoneyReq;
import messages.MessagePwdAck;
import messages.MessagePwdReq;
import server.datebase.JPYDDatabase;

public class Question extends JFrame implements ActionListener{
	JTextField txt= new JTextField();
	JTextField txt1= new JTextField();
	JTextField txt2= new JTextField();
	JTextField txt3= new JTextField();
	 JButton a=new JButton("下 一 步");
	 JButton b=new JButton("返       回");
	 JButton c=new JButton("确       定");
	 JButton d=new JButton("上 一 步");
	 int [] num = new int[3];

		private String serverIP = "127.0.0.1";
		private int serverPort = 54321;

		private boolean isConnected = false;
		private Socket socket = null;
		private OutputStream os = null;
		private InputStream is = null;
		private static ObjectInputStream ois = null;
		private static ObjectOutputStream oos = null;

	public Question(){
		final JPanel pr= new JPanel();
		final JPanel pr1= new JPanel();
		this.setTitle("忘记密码");
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
		
		JLabel name1 = new JLabel("账    号");
		name1.setAlignmentY(CENTER_ALIGNMENT);
		name1.setBounds(70,50, 50, 30);
		JLabel name2 = new JLabel("问    题");
		name2.setAlignmentY(CENTER_ALIGNMENT);
		name2.setBounds(70,10, 50, 30);
		JLabel name3 = new JLabel("答    案");
		name3.setAlignmentY(CENTER_ALIGNMENT);
		name3.setBounds(70,50, 50, 30);
		JLabel name4 = new JLabel("新密码");
		name4.setAlignmentY(CENTER_ALIGNMENT);
		name4.setBounds(70,90, 50, 30);
		
		txt.setBounds(150,50, 150, 30);
		txt1.setBounds(150,10, 150, 30);
		txt2.setBounds(150,50, 150, 30);
		txt3.setBounds(150,90, 150, 30);
		txt1.setEditable(false);
		a.setBounds(70,110, 100, 30);
		b.setBounds(200,110, 100, 30);
		c.setBounds(70,140, 100, 30);
		d.setBounds(200,140, 100, 30);
		
		pr.add(name1);
		pr.add(txt);
		pr.add(a);
		pr.add(b);
		pr1.add(name2);
		pr1.add(name3);
		pr1.add(name4);
		pr1.add(txt1);
		pr1.add(txt2);
		pr1.add(txt3);
		pr1.add(c);
		pr1.add(d);
		this.add(pr);
		this.add(pr1);
		
		a.addMouseListener(new MouseAdapter(){
			 public void mouseClicked(MouseEvent e){
				 String account = txt.getText();
				 User user = verifyAccount(account);
				 if(user!=null){
				 txt1.setText(user.Question);
				 pr1.setVisible(true);
				 pr.setVisible(false );
				 }
				 d.addMouseListener(new MouseAdapter(){
					 public void mouseClicked(MouseEvent e){
						 pr1.setVisible(false);
						 pr.setVisible(true );
					 }
				 });
			 }
		 });
		 a.addActionListener(this);
		 b.addActionListener(this);
		 c.addActionListener(this);
		 d.addActionListener(this);
		 connectToServer();
	}

	public void actionPerformed(ActionEvent ae){
		 String account = txt.getText();
		 User user = verifyAccount(account);
		if(ae.getSource()==a){
			 if(txt.getText().equals("")){
				 JOptionPane.showMessageDialog(this, "请输入账号");
			 }
		 }
		 if(ae.getSource()==b){
			 this.setVisible(false);
		 }
		 if(ae.getSource()==c){
			 if(txt2.getText().equals(user.Answer)){
				 if(txt3.getText().equals("")){
				 JOptionPane.showMessageDialog(this, "新密码不能为空！");
				 }
				 else{
					 UpPwd(txt.getText(),txt3.getText());
				 JOptionPane.showMessageDialog(this, "修改密码成功！");
				 this.setVisible(false);
				 }
			 }
		 else{
			 JOptionPane.showMessageDialog(this, "答案错误！");
		 }
			 
		 }
		
	 }
	private Object UpPwd(String account,String Pwd){
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
			MessagePwdReq msgLoginReq = new MessagePwdReq(account,Pwd);
			MessagePwdAck msgLoginAck = null;
			try {
				oos.writeObject(msgLoginReq);			
				msgLoginAck = (MessagePwdAck) ois.readObject();;
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
