package client.gui;

import info.OrderForm;
import info.PlaneTicket;
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
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import messages.MessageOrderfrom3Ack;
import messages.MessageOrderfrom3Req;
import server.datebase.JPYDDatabase;

public class piaomess extends JFrame implements ActionListener{

	private String serverIP = "127.0.0.1";
	private int serverPort = 54321;

	private boolean isConnected = false;
	private Socket socket = null;
	private OutputStream os = null;
	private InputStream is = null;
	private static ObjectInputStream ois = null;
	private static ObjectOutputStream oos = null;

	public void Piao (String FlightsNumber,String day,String Name){
		OrderForm orderform=verifyName(FlightsNumber,day,Name);
	    JPanel p1=new JPanel();
		   p1.setBorder(BorderFactory.createTitledBorder("订票信息")); 
		   p1.setSize(460,260);
		   p1.setLocation(10,0);	
		   p1.setLayout(null);
		   JPanel p2=new JPanel();  
		   p2.setLocation(320,150);
		   p2.setSize(100,100);
		   p2.setLayout(null);
		   p2.setVisible(true);
		   
		   String path = "1.jpg";
			ImageIcon background = new ImageIcon(path);
			JLabel beijing = new JLabel(background);
			beijing.setBounds(0, 0, p2.getWidth(), p2.getHeight());

		
	this.setTitle("飞机票信息");
	this.setSize(500,300);
	this.setLocation(450, 130);
	this.setLayout(null);
	this.setVisible(true);
	
			 
	JLabel name1 = new JLabel("航    班  ：");
	name1.setAlignmentY(CENTER_ALIGNMENT);
	name1.setBounds(20,20, 60, 30);
	JLabel name11 = new JLabel(orderform.getFlightsNumber());
	name11.setAlignmentY(CENTER_ALIGNMENT);
	name11.setBounds(80,20, 100, 30);
	JLabel name2 = new JLabel("日    期  ：");
	name2.setAlignmentY(CENTER_ALIGNMENT);
	name2.setBounds(170,20, 60, 30);
	JLabel name22 = new JLabel(orderform.getDay());
	name22.setAlignmentY(CENTER_ALIGNMENT);
	name22.setBounds(230,20, 100, 30);
	JLabel name3 = new JLabel("座    位  ：");
	name3.setAlignmentY(CENTER_ALIGNMENT);
	name3.setBounds(320,20, 60, 30);
	JLabel name33 = new JLabel(orderform.getGrade());
	name33.setAlignmentY(CENTER_ALIGNMENT);
	name33.setBounds(380,20, 100, 30);
	JLabel name4 = new JLabel("出发地  ：");
	name4.setAlignmentY(CENTER_ALIGNMENT);
	name4.setBounds(20,70, 60, 30);
	JLabel name44 = new JLabel(orderform.getStarting());
	name44.setAlignmentY(CENTER_ALIGNMENT);
	name44.setBounds(80,70, 100, 30);
	JLabel name5 = new JLabel("目的地  ：");
	name5.setAlignmentY(CENTER_ALIGNMENT);
	name5.setBounds(170,70, 60, 30);
	JLabel name55 = new JLabel(orderform.getEnding());
	name55.setAlignmentY(CENTER_ALIGNMENT);
	name55.setBounds(230,70, 100, 30);
	JLabel name6 = new JLabel("登机口  ：");
	name6.setAlignmentY(CENTER_ALIGNMENT);
	name6.setBounds(320,70, 100, 30);
	JLabel name66 = new JLabel(orderform.getBoarding());
	name66.setAlignmentY(CENTER_ALIGNMENT);
	name66.setBounds(380,70, 100, 30);
	JLabel name7 = new JLabel("出发时间：");
	name7.setAlignmentY(CENTER_ALIGNMENT);
	name7.setBounds(20,120, 100, 30);
	JLabel name77 = new JLabel(orderform.getArriverTime());
	name77.setAlignmentY(CENTER_ALIGNMENT);
	name77.setBounds(80,120, 100, 30);
	JLabel name8 = new JLabel("抵达时间：");
	name8.setAlignmentY(CENTER_ALIGNMENT);
	name8.setBounds(170,120, 100, 30);
	JLabel name88 = new JLabel(orderform.getDepartureTime());
	name88.setAlignmentY(CENTER_ALIGNMENT);
	name88.setBounds(230,120, 100, 30);
	JLabel name9 = new JLabel("票    价  ：");
	name9.setAlignmentY(CENTER_ALIGNMENT);
	name9.setBounds(320,120, 100, 30);
	JLabel name99 = new JLabel(orderform.getTicketPrice());
	name99.setAlignmentY(CENTER_ALIGNMENT);
	name99.setBounds(380,120, 100, 30);
	JLabel name10 = new JLabel("姓     名 ：");
	name10.setAlignmentY(CENTER_ALIGNMENT);
	name10.setBounds(20,170, 100, 30);
	JLabel name1010 = new JLabel(orderform.getName());
	name1010.setAlignmentY(CENTER_ALIGNMENT);
	name1010.setBounds(80,170, 100, 30);
	JLabel name0 = new JLabel("身份证  ：");
	name0.setAlignmentY(CENTER_ALIGNMENT);
	name0.setBounds(170,170, 100, 30);
	JLabel name00 = new JLabel(orderform.getID());
	name00.setAlignmentY(CENTER_ALIGNMENT);
	name00.setBounds(230,170, 80, 30);
	
	p1.add(name1);
	p1.add(name2);
	p1.add(name3);
	p1.add(name4);
	p1.add(name5);
	p1.add(name6);
	p1.add(name7);
	p1.add(name8);
	p1.add(name9);
	p1.add(name10);
	p1.add(name0);
	p1.add(name11);
	p1.add(name22);
	p1.add(name33);
	p1.add(name44);
	p1.add(name55);
	p1.add(name66);
	p1.add(name77);
	p1.add(name88);
	p1.add(name99);
	p1.add(name1010);
	p1.add(name00);
	p1.add(p2);
	p2.add(beijing);
	this.add(p1);
	 connectToServer();
}
	public void actionPerformed(ActionEvent ae){
		 
	}
	
	 private OrderForm verifyName(String FlightsNumber,String Day,String Name) {
		 if (!isConnected) {
				int maxRetry = 3, j = 0;
				while (j++ < maxRetry) {
					if(connectToServer())
						break; // 连接成功
				}
			}
			

			if (!isConnected)		{
				JOptionPane.showMessageDialog(this, "Failed to connect to server: " + serverIP);
				return null;
			}

			// 与服务器通信，验证用户
			MessageOrderfrom3Req msgLoginReq = new MessageOrderfrom3Req(FlightsNumber,Day,Name);
			MessageOrderfrom3Ack msgLoginAck = null;
			try {
				oos.writeObject(msgLoginReq);			
				msgLoginAck = (MessageOrderfrom3Ack) ois.readObject();;
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}finally{
			}		
			
			return (msgLoginAck != null ? msgLoginAck.getOrderForm() : null);
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
