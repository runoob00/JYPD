package server.gui;

import info.Flights;
import info.User;

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

import messages.MessageFlights1Ack;
import messages.MessageFlights1Req;
import messages.MessageHangBanAck;
import messages.MessageHangBanReq;
import client.gui.login;
import server.datebase.JPYDDatabase;
public class planadd extends JFrame implements ActionListener{
	JTextField txt= new JTextField();
	JTextField txt1= new JTextField();
	JComboBox City1 = new JComboBox();
	JComboBox City2 = new JComboBox();
	JButton a=new JButton("增加航班");
	 JButton b=new JButton("返       回");

		 private String serverIP = "127.0.0.1";
			private int serverPort = 54321;
			

			private boolean isConnected = false;
			private Socket socket = null;
			private OutputStream os = null;
			private InputStream is = null;
			private static ObjectInputStream ois = null;
			private static ObjectOutputStream oos = null;
	

	public planadd(){
		this.setTitle("航班");
		this.setSize(400,220);
		this.setLocation(500, 250);
		this.setLayout(null);
		this.setVisible(true);
		
		JLabel name1 = new JLabel("航班号");
		name1.setAlignmentY(CENTER_ALIGNMENT);
		name1.setBounds(70,5, 50, 30);
		JLabel name2 = new JLabel("登机口");
		name2.setAlignmentY(CENTER_ALIGNMENT);
		name2.setBounds(70,40, 50, 30);
		JLabel name3 = new JLabel("出发地");
		name3.setAlignmentY(CENTER_ALIGNMENT);
		name3.setBounds(70,75, 50, 30);
		JLabel name4 = new JLabel("目的地");
		name4.setAlignmentY(CENTER_ALIGNMENT);
		name4.setBounds(70,110, 50, 30);
		
		City1.setModel(new DefaultComboBoxModel(new String[] {"请选择城市","北京",
				"广州","上海"}));
		City1.setBounds(150,75, 150, 30);
		City2.setModel(new DefaultComboBoxModel(new String[] {"请选择城市","北京",
				"广州","上海"}));
		City2.setBounds(150,110, 150, 30);
		
		txt.setBounds(150,5, 150, 30);
		txt1.setBounds(150,40, 150, 30);
		a.setBounds(70,145, 100, 30);
		b.setBounds(200,145, 100, 30);
		
		this.add(name1);
		this.add(name2);
		this.add(name3);
		this.add(name4);
		this.add(txt);
		this.add(txt1);
		this.add(City1);
		this.add(City2);
		this.add(a);
		this.add(b);
		
		a.addActionListener(this);
		b.addActionListener(this);
		 connectToServer();
	}
	 public void actionPerformed(ActionEvent ae) {
		 if(ae.getSource()==a){
			 String bo1 =(String) City1.getSelectedItem();
			 String bo2 =(String) City2.getSelectedItem();
			 Flights flights = verifyFlightsNumber(txt.getText());
			 if(flights!=null){
				 
					JOptionPane.showMessageDialog(this, "已存在该航班");
				 }
			 else if(txt.getText().equals("")){
					 JOptionPane.showMessageDialog(this, "航班号没输入，请检查");
				 } 
			 else if(txt1.getText().equals("")){
				 JOptionPane.showMessageDialog(this, "登机口没输入，请检查");
			 } 
			 else if(bo1.equals("请选择城市")){
				 JOptionPane.showMessageDialog(this, "请选择出发地");
			 }
			 else if(bo2.equals("请选择城市")){
				 JOptionPane.showMessageDialog(this, "请选择目的地");
			 }
			 else if(bo2.equals(bo1)){
				 JOptionPane.showMessageDialog(this, "出发地和目的地相同，请重新选择！");
			 }
			 else {
				 UpHangBan(txt.getText(),txt1.getText(),bo1,bo2);
				 JOptionPane.showMessageDialog(this, "增加航班成功");
				 this.setVisible(false);
			 }
		 }
		 if(ae.getSource()==b){
			 this.setVisible(false);
		 }
	 }
		public static void main(String[] args) {
			planadd pr= new planadd();
			  pr.setVisible(true);

		}
		 private Flights verifyFlightsNumber(String FlightsNumber) {
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
				MessageFlights1Req msgLoginReq = new MessageFlights1Req( FlightsNumber);
				MessageFlights1Ack msgLoginAck = null;
				try {
					oos.writeObject(msgLoginReq);			
					msgLoginAck = (MessageFlights1Ack) ois.readObject();;
				} catch (IOException | ClassNotFoundException e) {
					e.printStackTrace();
				}finally{
				}		
				
				return (msgLoginAck != null ? msgLoginAck.getFlights() : null);	
		 }
		 private Object UpHangBan(String flightsNumber  ,String board  ,String start ,String end){
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
				MessageHangBanReq msgLoginReq = new MessageHangBanReq( flightsNumber, board, start,end);
				MessageHangBanAck msgLoginAck = null;
				try {
					oos.writeObject(msgLoginReq);			
					msgLoginAck = (MessageHangBanAck) ois.readObject();;
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
