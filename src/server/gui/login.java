package server.gui;

import info.User;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import messages.MessageLogin1Ack;
import messages.MessageLogin1Req;
import messages.MessageLogin2Ack;
import messages.MessageLogin2Req;
import messages.MessageLoginAck;
import messages.MessageLoginReq;
import server.datebase.JPYDDatabase;

public class login extends JFrame implements ActionListener{
	JTextField txt= new JTextField();
	JPasswordField txt1= new JPasswordField();
	 JButton a=new JButton("登      录");
	 JButton b=new JButton("退      出");

	 private String serverIP = "127.0.0.1";
		private int serverPort = 54321;
		

		private boolean isConnected = false;
		private Socket socket = null;
		private OutputStream os = null;
		private InputStream is = null;
		private static ObjectInputStream ois = null;
		private static ObjectOutputStream oos = null;

	public login(){
		this.setTitle("管理员登陆");
		this.setSize(600, 450);
		this.setLocation(300, 50);
		this.setLayout(null);
		JLabel title = new JLabel("欢迎登陆");
		JLabel title1 = new JLabel("管理员");
		title.setBounds(235,100,100,100);
		title.setFont(new Font (Font.DIALOG, Font.BOLD, 20));
		title1.setBounds(255,160,100,50);
		title1.setFont(new Font (Font.DIALOG, Font.BOLD, 15));

		JPanel imagePanel = (JPanel) this.getContentPane();
		imagePanel.setOpaque(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		 JLabel name1 = new JLabel("账   号");
		 name1.setAlignmentY(CENTER_ALIGNMENT);
		 name1.setBounds(180,220, 50, 25);
		 JLabel name2 = new JLabel("密   码");
		 name2.setAlignmentY(CENTER_ALIGNMENT);
		 name2.setBounds(180, 260, 50, 25);
		 JLabel name3 = new JLabel("注册账号");
		 name3.setAlignmentY(CENTER_ALIGNMENT);
		 name3.setForeground(Color.black);
		 name3.setBounds(335, 220, 100, 25);
		 JLabel name4 = new JLabel("忘记密码");
		 name4.setAlignmentY(CENTER_ALIGNMENT);
		 name4.setForeground(Color.black);
		 name4.setBounds(335, 260, 100, 25);
		 
		 txt.setBounds(230,220,100,25);
		 txt1.setBounds(230,260,100,25);
		 txt1.setEchoChar('*'); 
		 a.setBounds(180,300,90,25);
		 b.setBounds(280,300,90,25);
		 this.add(name1);
		 this.add(name2);
		 this.add(name3);
		 this.add(name4);
		 this.add(txt);
		 this.add(txt1);
		 this.add(a);
		 this.add(b);
		 this.add(title);
		 this.add(title1);
		 a.addActionListener(this);
		 b.addActionListener(this);
		 
		//标签事件触发 
		 name3.addMouseListener(new MouseAdapter(){
			 public void mouseClicked(MouseEvent e){
				 new zhuce();
				 //找到当前窗口并关闭
					Component cmp= e.getComponent();
	            	while(!(cmp instanceof JFrame ) || cmp.getParent() !=null ){
	            	 cmp = cmp.getParent();
	            	}
	            	((JFrame)cmp).dispose();
	            	
			 } 
		 });
		 name4.addMouseListener(new MouseAdapter(){
			 public void mouseClicked(MouseEvent e){
				 new Question();
			 }
		 });
		 connectToServer();
	}
	 public void actionPerformed(ActionEvent ae) {
		 if(ae.getSource()==a){
			 String account = txt.getText();
				String password = new String(txt1.getPassword());
				
				User user = verifyAccount(account, password);
				if(txt.getText().equals("")){
					JOptionPane.showMessageDialog(this, "账号不能为空");
				}
				else if( user != null){
					new jiPiao();
					// 关闭当前窗口
					this.dispose();}
				
				else{
					JOptionPane.showMessageDialog(this, "账号或密码错误");
					txt1.setText(null);
			
		 }
		 }
		 if(ae.getSource()==b){
			 this.setVisible(false);
			 this.dispose();
		 }
	 }
	 
	 private User verifyAccount(String account, String password) {
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
			MessageLogin2Req msgLoginReq = new MessageLogin2Req(account, password);
			MessageLogin2Ack msgLoginAck = null;
			try {
				oos.writeObject(msgLoginReq);			
				msgLoginAck = (MessageLogin2Ack) ois.readObject();
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
			
	public static void main(String[] args) {
		login pr= new login();
		  pr.setVisible(true);

	}
}
