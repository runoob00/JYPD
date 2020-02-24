package server.gui;

import info.Flights;
import info.PlaneTicket;

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
import java.util.ArrayList;
import java.util.Vector;

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
import messages.MessageFlightsAck;
import messages.MessageFlightsReq;
import messages.MessageLoginAck;
import messages.MessageLoginReq;
import messages.MessageMoneyAck;
import messages.MessageMoneyReq;
import messages.MessagePlaneticket2Ack;
import messages.MessagePlaneticket2Req;
import messages.MessagePlaneticket3Ack;
import messages.MessagePlaneticket3Req;
import server.datebase.JPYDDatabase;

public class jiPiao extends JFrame implements ActionListener{
	JComboBox City1 = new JComboBox();
	JComboBox Year1 = new JComboBox();
	JComboBox Month1 = new JComboBox();
	JComboBox Day1 = new JComboBox();
	JComboBox Time1 = new JComboBox();
	JComboBox Time2 = new JComboBox();
	JComboBox Time3 = new JComboBox();
	JComboBox Time4 = new JComboBox();
	
	JTextField txt= new JTextField();
	JTextField txt1= new JTextField();
	JTextField txt2= new JTextField();
	JTextField txt3= new JTextField();
	JTextField txt4= new JTextField();
	JTextField txt5= new JTextField();
	JTextField txt6= new JTextField();
	JButton a=new JButton("导入飞机信息");
	JButton b=new JButton("确定");
	JButton c=new JButton("增加航班信息");
	JButton d=new JButton("新增航班");
	ArrayList<String> List = new ArrayList();

 private String serverIP = "127.0.0.1";
	private int serverPort = 54321;
	

	private boolean isConnected = false;
	private Socket socket = null;
	private OutputStream os = null;
	private InputStream is = null;
	private static ObjectInputStream ois = null;
	private static ObjectOutputStream oos = null;

	
	private Vector<PlaneTicket> searchResult = new Vector<PlaneTicket>();
	public jiPiao (){
		this.setTitle("飞机信息");
		this.setSize(450, 600);
		this.setLocation(300, 50);
		this.setLayout(null);
		
		String path = "背景2.jpg";
		ImageIcon background = new ImageIcon(path);
		JLabel beijing = new JLabel(background);
		beijing.setBounds(0, 0, this.getWidth(), this.getHeight()-35);
		JPanel imagePanel = (JPanel) this.getContentPane();
		imagePanel.setOpaque(false);
		this.getLayeredPane().add(beijing, new Integer(Integer.MIN_VALUE));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		
		City1.setBounds(250, 40, 80, 30);
		Year1.setModel(new DefaultComboBoxModel(new String[] {"","2015",
		"2016","2017","2018"}));
        Year1.setBounds(70,170, 60, 30);
        Month1.setModel(new DefaultComboBoxModel(new String[] {"","01","02","03","04","05","06","07","08",
				"09","10","11","12"}));
		Month1.setBounds(155,170, 50, 30);
		Day1.setModel(new DefaultComboBoxModel(new String[]{"","01", "02", "03", "04", "05", "06","07", "08", "09", "10", "11", "12","13",
	    		"14", "15", "16", "17", "18","19", "20", "21", "22", "23", "24","25", "26", "27", "28", "29", "30","31" }));
		Day1.setBounds(235,170, 50, 30);
		Time1.setModel(new DefaultComboBoxModel(new String[] {"00","01","02","03","04","05","06","07","08",
					"09","10","11","12","13","14","15","16","17","18","19","20","21","22","23"}));
		Time1.setBounds(85, 220, 50, 30);
		Time2.setModel(new DefaultComboBoxModel(new String[]{"00","01", "02", "03", "04", "05", "06","07", "08", "09", "10", "11", "12","13",
		    		"14", "15", "16", "17", "18","19", "20", "21", "22", "23", "24","25", "26", "27", "28", "29","30", "31","32","33","34","35",
		    		"36","37","38","39","40","41","42","43","44","45","46","47","48","49","50","51","52","53","54","55","56","57","58","59" }));
		Time2.setBounds(150, 220, 50, 30);
		Time3.setModel(new DefaultComboBoxModel(new String[] {"00","01","02","03","04","05","06","07","08",
				"09","10","11","12","13","14","15","16","17","18","19","20","21","22","23"}));
		Time3.setBounds(85, 270, 50, 30);
		Time4.setModel(new DefaultComboBoxModel(new String[]{"00","01", "02", "03", "04", "05", "06","07", "08", "09", "10", "11", "12","13",
	    		"14", "15", "16", "17", "18","19", "20", "21", "22", "23", "24","25", "26", "27", "28", "29","30", "31","32","33","34","35",
	    		"36","37","38","39","40","41","42","43","44","45","46","47","48","49","50","51","52","53","54","55","56","57","58","59" }));
		Time4.setBounds(150, 270, 50, 30);
		
		JLabel name = new JLabel("请选择航班");
		name.setAlignmentY(CENTER_ALIGNMENT);
		name.setBounds(180, 40, 100, 30);
		JLabel name1 = new JLabel("航班号");
		name1.setAlignmentY(CENTER_ALIGNMENT);
		name1.setBounds(25,80, 100, 50);
		JLabel name2 = new JLabel("登机口");
		name2.setAlignmentY(CENTER_ALIGNMENT);
		name2.setBounds(195, 80, 100, 50);
		JLabel name3 = new JLabel("出发地");
		name3.setAlignmentY(CENTER_ALIGNMENT);
		name3.setBounds(25, 120, 100, 50);
		JLabel name4 = new JLabel("目的地");
		name4.setAlignmentY(CENTER_ALIGNMENT);
		name4.setBounds(195, 120, 100, 50);
		JLabel name5 = new JLabel("日期");
		name5.setAlignmentY(CENTER_ALIGNMENT);
		name5.setBounds(25, 160, 100, 50);
		JLabel name6 = new JLabel("年");
		name6.setAlignmentY(CENTER_ALIGNMENT);
		name6.setBounds(135, 160, 20, 50);
		JLabel name7 = new JLabel("月");
		name7.setAlignmentY(CENTER_ALIGNMENT);
		name7.setBounds(210, 160, 20, 50);
		JLabel name8 = new JLabel("日");
		name8.setAlignmentY(CENTER_ALIGNMENT);
		name8.setBounds(295, 160, 20, 50);
		JLabel name9 = new JLabel("票价");
		name9.setAlignmentY(CENTER_ALIGNMENT);
		name9.setBounds(25, 300, 100, 50);
		JLabel name10 = new JLabel("元");
		name10.setAlignmentY(CENTER_ALIGNMENT);
		name10.setBounds(180, 300, 100, 50);
		JLabel name11 = new JLabel("出发时间");
		name11.setAlignmentY(CENTER_ALIGNMENT);
		name11.setBounds(25, 210, 100, 50);
		JLabel name12 = new JLabel("抵达时间");
		name12.setAlignmentY(CENTER_ALIGNMENT);
		name12.setBounds(25, 260, 100, 50);
		JLabel name13 = new JLabel("头等座数量");
		name13.setAlignmentY(CENTER_ALIGNMENT);
		name13.setBounds(25, 340, 100, 50);
		JLabel name14 = new JLabel("二等座数量");
		name14.setAlignmentY(CENTER_ALIGNMENT);
		name14.setBounds(220, 340, 100, 50);
		JLabel name15 = new JLabel(":");
		name15.setAlignmentY(CENTER_ALIGNMENT);
		name15.setBounds(140, 210, 50, 50);
		JLabel name16 = new JLabel(":");
		name16.setAlignmentY(CENTER_ALIGNMENT);
		name16.setBounds(140, 260, 50, 50);
		
		txt.setEditable(false);
		txt1.setEditable(false);
		txt2.setEditable(false);
		txt3.setEditable(false);
		a.setBounds(25, 40, 130, 30);
		b.setBounds(340, 40, 80, 30);
		c.setBounds(55, 450, 130, 30);
		d.setBounds(210, 450, 130, 30);
		
		txt.setBounds(70, 90, 85, 30);
		txt1.setBounds(235, 90, 85, 30);
		txt2.setBounds(70, 130, 85, 30);
		txt3.setBounds(235, 130, 85, 30);
		txt4.setBounds(70, 310, 100, 30);
		txt5.setBounds(100, 350, 100, 30);
		txt6.setBounds(290, 350, 100, 30);
		this.add(City1);
		this.add(Year1);
		this.add(Month1);
		this.add(Day1);
		this.add(Time1);
		this.add(Time2);
		this.add(Time3);
		this.add(Time4);
		this.add(name);
		this.add(name1);
		this.add(name2);
		this.add(name3);
		this.add(name4);
		this.add(name5);
		this.add(name6);
		this.add(name7);
		this.add(name8);
		this.add(name9);
		this.add(name10);
		this.add(name11);
		this.add(name12);
		this.add(name13);
		this.add(name14);
		this.add(name15);
		this.add(name16);
		this.add(txt);
		this.add(txt1);
		this.add(txt2);
		this.add(txt3);
		this.add(txt4);
		this.add(txt5);
		this.add(txt6);
		this.add(a);
		this.add(b);
		this.add(c);
		this.add(d);
		
		a.addActionListener(this);
		b.addActionListener(this);
		c.addActionListener(this);
		d.addActionListener(this);
		
		a.addMouseListener(new MouseAdapter(){
			 public void mouseClicked(MouseEvent e){
				 int z=0;
				 Vector<PlaneTicket> searchResult =PlaneTicketSearch();
				 updateResultTable(searchResult);
				 for(int i=0;i<List.size();i++){
					 z++;
				 }
				String [] city= new String[z];
				 for(int i=0;i<List.size();i++){
					 String city1=(String)List.get(i);
					 city[i]=city1;
					 z++;
				 }
				 City1.setModel(new DefaultComboBoxModel(city));
			 }
		});
		connectToServer() ;
	}
	
	 public void actionPerformed(ActionEvent ae) {
		 if(ae.getSource()==b){
			 String bo1 =(String) City1.getSelectedItem();
			 Flights flights = verifyFlightsNumber(bo1);
		 }
		 if(ae.getSource()==c){
			 String bo1 =(String) Year1.getSelectedItem();
			 String bo2 =(String) Month1.getSelectedItem();
			 String bo3 =(String) Day1.getSelectedItem();
			 String bo4=bo1+bo2+bo3;
			 String time1 =(String) Time1.getSelectedItem();
			 String time2 =(String) Time2.getSelectedItem();
			 String time3 =(String) Time3.getSelectedItem();
			 String time4 =(String) Time4.getSelectedItem();
			 String time5=time1+time2;
			 String time6=time3+time4;
			 String time7=time1+":"+time2;
			 String time8=time3+":"+time4;
			 PlaneTicket planeticket = verifyFlightsNumber1(txt.getText(),bo4);
			 if(txt.getText().equals("")||txt1.getText().equals("")||txt2.getText().equals("")||txt3.getText().equals("")){
				 JOptionPane.showMessageDialog(this, "请导入飞机信息！");
			 } 
			 else if(bo1.equals("")||bo2.equals("")||bo3.equals("")){
				 JOptionPane.showMessageDialog(this, "请选择日期！");
			 }
			 else if(planeticket!=null){
				 JOptionPane.showMessageDialog(this, "已有该飞机当天航班信息！");
				}
			 else if(txt4.getText().equals("")){
				 JOptionPane.showMessageDialog(this, "请输入票价！");
			 }
			 else if(Integer.parseInt(time5)>=Integer.parseInt(time6)){
				 JOptionPane.showMessageDialog(this, "请输入正确的时间");
			 }
			 else if(txt5.getText().equals("")){
				 JOptionPane.showMessageDialog(this, "请输入头等舱数量！");
			 }
			 else if(txt6.getText().equals("")){
				 JOptionPane.showMessageDialog(this, "请输入经济舱数量！");
			 }
			 else{
				 UpPlaneticket(txt.getText(),txt1.getText(),txt2.getText(),txt3.getText(),bo4,time7,time8,txt4.getText(),txt5.getText(),txt6.getText(),txt5.getText(),txt6.getText());
	
				 JOptionPane.showMessageDialog(this, "导入航班信息成功！");
			 }
		 }
		 if(ae.getSource()==d){
			 new planadd();
		 }
		 
	 }
	 public static void main(String[] args) {
		 jiPiao pr= new jiPiao();
			  pr.setVisible(true);

		}
	 private Vector<PlaneTicket> PlaneTicketSearch() {
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
			MessagePlaneticket2Req msgLoginReq = new MessagePlaneticket2Req( );
			MessagePlaneticket2Ack msgLoginAck = null;
			try {
				oos.writeObject(msgLoginReq);			
				msgLoginAck = (MessagePlaneticket2Ack) ois.readObject();;
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}finally{
			}		
			
			return (msgLoginAck != null ? msgLoginAck.getPlaneTicket() : null);	
		}
	 private void updateResultTable(Vector<PlaneTicket> searchResult) {
		 List.clear();
			for (int i = 1; i <= searchResult.size(); i++) {
				PlaneTicket planeTicket = searchResult.get(i-1);
				List.add(planeTicket.getFlightsNumber());
			}

	 }
	 private Flights verifyFlightsNumber(String FlightsNumber) {
		 Flights flights = verifyFlightsNumber1(FlightsNumber) ;
		 txt.setText(flights.getFlightsNumber());
		 txt1.setText(flights.getBoarding());
		 txt2.setText(flights.getStarting());
		 txt3.setText(flights.getEnding());
			return flights;
		}
	 private Flights verifyFlightsNumber1(String FlightsNumber) {
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
	 private PlaneTicket verifyFlightsNumber1(String FlightsNumber,String DAY) {
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
			MessageFlightsReq msgLoginReq = new MessageFlightsReq(FlightsNumber, DAY);
			MessageFlightsAck msgLoginAck = null;
			try {
				oos.writeObject(msgLoginReq);			
				msgLoginAck = (MessageFlightsAck) ois.readObject();
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}finally{
			}		
			
			return (msgLoginAck != null ? msgLoginAck.getPlaneTicket() : null);
	 }
	 
	 private Object UpPlaneticket(String FlightsNumber  , String Board  ,String Start  , String End,String Day   , String DepartureTime   ,
				String ArriverTime   , String TicketPrice ,String Seat   , String Grade   ,String SurplusSeat   , String SurplusGrade  ){
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
			MessagePlaneticket3Req msgLoginReq = new MessagePlaneticket3Req( FlightsNumber  ,  Board  , Start  ,  End, Day   ,  DepartureTime   ,
					 ArriverTime   ,  TicketPrice , Seat   ,  Grade   , SurplusSeat   ,  SurplusGrade );
			MessagePlaneticket3Ack msgLoginAck = null;
			try {
				oos.writeObject(msgLoginReq);			
				msgLoginAck = (MessagePlaneticket3Ack) ois.readObject();
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
