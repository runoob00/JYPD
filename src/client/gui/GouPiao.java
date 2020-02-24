package client.gui;

import info.OrderForm;
import info.PlaneTicket;
import info.User;

import java.awt.Color;
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
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import messages.MessageFlightsAck;
import messages.MessageFlightsReq;
import messages.MessageLogin1Ack;
import messages.MessageLogin1Req;
import messages.MessageMoneyAck;
import messages.MessageMoneyReq;
import messages.MessageOrderfrom2Ack;
import messages.MessageOrderfrom2Req;
import messages.MessageOrderfrom3Ack;
import messages.MessageOrderfrom3Req;
import messages.MessagePlaneticket1Ack;
import messages.MessagePlaneticket1Req;
import messages.MessagePlaneticketAck;
import messages.MessagePlaneticketReq;
import server.datebase.JPYDDatabase;

public class GouPiao extends JFrame implements ActionListener{
	JRadioButton a=new JRadioButton("头等座");
	JRadioButton b=new JRadioButton("二等座");
	JButton c=new JButton("确定");
	 JButton d=new JButton("取消");
	 JButton e=new JButton("导入");
	 String [] name= new String[20];
	 int a1;
	 int a4;
	 JTextField txt1= new JTextField();
	 JTextField txt2= new JTextField();

		private String serverIP = "127.0.0.1";
		private int serverPort = 54321;

		private boolean isConnected = false;
		private Socket socket = null;
		private OutputStream os = null;
		private InputStream is = null;
		private static ObjectInputStream ois = null;
		private static ObjectOutputStream oos = null;

	public void GouPiao (String account,String FlightsNumber,String day){
	this.setTitle("飞机购票");
	this.setSize(300,530);
	this.setLocation(500, 130);
	this.setLayout(null);
	this.setVisible(true);
	
	this.getContentPane().setBackground(Color.white);
	
	name[0]="0";
	User user = verifyAccount(account);
	name[1]=user.getAccount();
	name[2]=user.getName();
	name[3]=user.getID();
	name[16]=user.getMoney();
	
	 PlaneTicket planeTicket =verifyFlightsNumber(FlightsNumber,day);
	 name[4]=planeTicket.getFlightsNumber();
	 name[5]=planeTicket.getStarting();
	 name[6]=planeTicket.getEnding();
	 name[7]=planeTicket.getDepartureTime();
	 name[8]=planeTicket.getArriverTime();
	 name[9]=planeTicket.getTicketPrice();
	 name[10]=planeTicket.getSeat();
	 name[11]=planeTicket.getGrade();
	 name[12]=planeTicket.getSurplusSeat();
	 name[13]=planeTicket.getSurplusGrade();
	 name[14]=day;
	 name[15]=planeTicket.getBoarding();
	 a1=Integer.parseInt(planeTicket.getTicketPrice())*2;
	 a4=Integer.parseInt(planeTicket.getTicketPrice());
	 int a2=Integer.parseInt( name[10])-Integer.parseInt( name[12])+1;
	 int a3=Integer.parseInt( name[11])-Integer.parseInt( name[13])+1;
	 name[17]="v"+String.valueOf(a2);
	 name[18]=String.valueOf(a3);
			 
	JLabel name1 = new JLabel("航    班  ：");
	name1.setAlignmentY(CENTER_ALIGNMENT);
	name1.setBounds(50,10, 60, 30);
	JLabel name11 = new JLabel(name[4]);
	name11.setAlignmentY(CENTER_ALIGNMENT);
	name11.setBounds(120,10, 100, 30);
	JLabel name2 = new JLabel("地    点  ：");
	name2.setAlignmentY(CENTER_ALIGNMENT);
	name2.setBounds(50,60, 100, 30);
	JLabel name22 = new JLabel(name[5]+" >>>> "+name[6]);
	name22.setAlignmentY(CENTER_ALIGNMENT);
	name22.setBounds(120,60, 100, 30);
	JLabel name3 = new JLabel("起    飞  ：");
	name3.setAlignmentY(CENTER_ALIGNMENT);
	name3.setBounds(50,110, 100, 30);
	JLabel name33 = new JLabel(name[7]);
	name33.setAlignmentY(CENTER_ALIGNMENT);
	name33.setBounds(120,110, 100, 30);
	JLabel name4 = new JLabel("抵    达  ：");
	name4.setAlignmentY(CENTER_ALIGNMENT);
	name4.setBounds(50,160, 100, 30);
	JLabel name44 = new JLabel(name[8]);
	name44.setAlignmentY(CENTER_ALIGNMENT);
	name44.setBounds(120,160, 100, 30);
	JLabel name5 = new JLabel("票    价  ：");
	name5.setAlignmentY(CENTER_ALIGNMENT);
	name5.setBounds(50,210, 100, 30);
	JLabel name55 = new JLabel("$"+a1+"/$"+ name[9]);
	name55.setAlignmentY(CENTER_ALIGNMENT);
	name55.setBounds(120,210, 100, 30);
	JLabel name6 = new JLabel("旅    客  ：");
	name6.setAlignmentY(CENTER_ALIGNMENT);
	name6.setBounds(50,260, 100, 30);
	JLabel name7 = new JLabel("身份证  ：");
	name7.setAlignmentY(CENTER_ALIGNMENT);
	name7.setBounds(50,310, 100, 30);
	JLabel name8 = new JLabel("座     位  ：");
	name8.setAlignmentY(CENTER_ALIGNMENT);
	name8.setBounds(50,360, 100, 30);
	JLabel name88 = new JLabel();
	name88.setAlignmentY(CENTER_ALIGNMENT);
	name88.setBounds(120,360, 100, 30);
	if((Integer.parseInt( name[12])>0)&&(Integer.parseInt( name[13])>0)){
		name88.setText("v"+a2+"/"+a3);
	}
	else if((Integer.parseInt( name[12])==0)&&(Integer.parseInt( name[13])>0)){
		name88.setText("已售完/"+a3);
		}
	else if((Integer.parseInt( name[12])>0)&&(Integer.parseInt( name[13])==0)){
		name88.setText("v"+a2+"/已售完");
		}
	else if((Integer.parseInt( name[12])==0)&&(Integer.parseInt( name[13])==0)){
		name88.setText("已售完/已售完");
			}
	
	txt1.setBounds(120,260, 100, 30);
	txt2.setBounds(120,310, 100, 30);
	a.setBounds(50,400,80,30);
	b.setBounds(150,400,80,30);
	c.setBounds(50,440,60,30);
	d.setBounds(190,440,60,30);
	e.setBounds(120,440,60,30);
	
	ButtonGroup group = new ButtonGroup();//单选按钮组
	group.add(a);
	group.add(b);
	
	this.add(name1);
	this.add(name2);
	this.add(name3);
	this.add(name4);
	this.add(name5);
	this.add(name6);
	this.add(name7);
	this.add(name8);
	this.add(name11);
	this.add(name22);
	this.add(name33);
	this.add(name44);
	this.add(name55);
	this.add(txt1);
	this.add(txt2);
	this.add(name88);
	this.add(a);
	this.add(b);
	this.add(c);
	this.add(d);
	this.add(e);
	a.addActionListener(this);
	b.addActionListener(this);
	c.addActionListener(this);
	d.addActionListener(this);
	e.addActionListener(this);
	txt1.addMouseListener(new MouseAdapter() {
      	 public void mouseClicked(MouseEvent e) {
      	 txt1.setText("");}
      	   });
	 txt2.addMouseListener(new MouseAdapter() {
      	 public void mouseClicked(MouseEvent e) {
      	 txt2.setText("");}
      	   });
	 connectToServer();
}
	public void actionPerformed(ActionEvent ae){
		 if(ae.getSource()==a){
			 name[0]="1";
		 }
		 if(ae.getSource()==b){
			 name[0]="2";
		 }
		 if(ae.getSource()==c){
			 OrderForm orderform=verifyName( name[4],name[14],txt1.getText());
			 if(orderform!=null){
				 JOptionPane.showMessageDialog(this, "该乘客已购买过本次航班的机票！");
			 }
			 else if(txt1.getText().equals("")){
				 JOptionPane.showMessageDialog(this, "请输入旅客！");
			 }
			 else if(txt2.getText().equals("")){
				 JOptionPane.showMessageDialog(this, "请输入身份证！");
			 }
			 else if(name[0].equals("0")){
				 JOptionPane.showMessageDialog(this, "请选择要买票的类型！");
			 }
			 else  if(name[0].equals("1")){
					if((Integer.parseInt( name[12])>0)){
						if(a1<Integer.parseInt(name[16])){
						int a5=Integer.parseInt(name[16])-a1;
						name[12]=String.valueOf(Integer.parseInt(name[12])-1);
						 JOptionPane.showMessageDialog(this, "购票成功");
						 this.dispose();
						 UpPlaneticket1(name[4],name[12 ],name[14]);
						 UpMoney(name[1],String.valueOf(a5));
						 UpOrderform(name[1],txt1.getText(),txt2.getText(),name[4],name[15],name[5],name[6],name[14],name[7],name[8],String.valueOf(a1),name[17]);
						 piaomess  f1=new piaomess();
							f1.Piao(name[4],name[14],txt1.getText());
						}
						else{
							JOptionPane.showMessageDialog(this, "余额不足！");
						}
					}
					else if((Integer.parseInt( name[12])<=0)){
						JOptionPane.showMessageDialog(this, "头等座已售完！");
						}
			 }
          else  if(name[0].equals("2")){
        	  if((Integer.parseInt( name[13])>0)){
        		  if(a4<Integer.parseInt(name[16])){
        			  int a6=Integer.parseInt(name[16])-a4;
					 name[13]=String.valueOf(Integer.parseInt(name[13])-1);
					 JOptionPane.showMessageDialog(this, "购票成功");
					 this.dispose();
					 UpPlaneticket(name[4],name[13 ],name[14]);
					 UpMoney(name[1],String.valueOf(a6));
					 UpOrderform(name[1],txt1.getText(),txt2.getText(),name[4],name[15],name[5],name[6],name[14],name[7],name[8],String.valueOf(a4),name[18]);
					 piaomess  f1=new piaomess();
						f1.Piao(name[4],name[14],txt1.getText());
					 
        		  }
					else{
						JOptionPane.showMessageDialog(this, "余额不足！");
					}
				}
				else if((Integer.parseInt( name[13])<=0)){
					JOptionPane.showMessageDialog(this, "二等座已售完！");
					}
			 }
			 
		 }
		 if(ae.getSource()==d){
			 this.dispose();
		 }
		 if(ae.getSource()==e){
			 txt1.setText(name[2]);
			 txt2.setText(name[3]);
		 }
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
	 private Object UpPlaneticket(String FlightsNumber,String SurplusGrade,String Day){
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
			MessagePlaneticketReq msgLoginReq = new MessagePlaneticketReq(FlightsNumber,SurplusGrade,Day);
			MessagePlaneticketAck msgLoginAck = null;
			try {
				oos.writeObject(msgLoginReq);			
				msgLoginAck = (MessagePlaneticketAck) ois.readObject();;
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}finally{
			}		
			
			return (msgLoginAck != null ? msgLoginAck.getUser() : null);	
			
	 }
	 private Object UpPlaneticket1(String FlightsNumber,String getSurplusSeat,String Day){
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
			MessagePlaneticket1Req msgLoginReq = new MessagePlaneticket1Req(FlightsNumber,getSurplusSeat,Day);
			MessagePlaneticket1Ack msgLoginAck = null;
			try {
				oos.writeObject(msgLoginReq);			
				msgLoginAck = (MessagePlaneticket1Ack) ois.readObject();;
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}finally{
			}		
			
			return (msgLoginAck != null ? msgLoginAck.getUser() : null);	
			
	 }
	 private Object UpOrderform(String Account,String Name,String ID,String FlightsNumber  ,String Board  ,String Start  ,
				String End ,String Day ,String DepartureTime  ,String ArriverTime ,String TicketPrice ,String Grade){
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
			MessageOrderfrom2Req msgLoginReq = new MessageOrderfrom2Req(Account,Name,ID, FlightsNumber  , Board  , Start  ,
					 End , Day , DepartureTime  , ArriverTime , TicketPrice , Grade);
			MessageOrderfrom2Ack msgLoginAck = null;
			try {
				oos.writeObject(msgLoginReq);			
				msgLoginAck = (MessageOrderfrom2Ack) ois.readObject();
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}finally{
			}		
			
			return  (msgLoginAck != null ?msgLoginAck.getOrderform(): null);
			
	 }
	 private PlaneTicket verifyFlightsNumber(String FlightsNumber,String Day) {
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
			MessageFlightsReq msgLoginReq = new MessageFlightsReq(FlightsNumber,Day);
			MessageFlightsAck msgLoginAck = null;
			try {
				oos.writeObject(msgLoginReq);			
				msgLoginAck = (MessageFlightsAck) ois.readObject();;
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}finally{
			}		
			
			return (msgLoginAck != null ? msgLoginAck.getPlaneTicket() : null);
		}
	 private OrderForm verifyName(String FlightsNumber,String Day,String Name) {
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
	public static void main(String[] args) {
		GouPiao pr= new GouPiao();
		  pr.setVisible(true);

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
