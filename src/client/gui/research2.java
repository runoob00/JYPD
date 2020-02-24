package client.gui;

import info.PlaneTicket;

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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import messages.MessageChaxunAck;
import messages.MessageChaxunReq;
public class research2 extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1416918350623631163L;
	private JTable tableResult = null;
	DefaultTableModel tableModelDefault = null;
	JPanel middle = new JPanel();
	JComboBox City1 = new JComboBox();
	JComboBox City2 = new JComboBox();
	JComboBox Year1 = new JComboBox();
	JComboBox Month1 = new JComboBox();
	JComboBox Day1 = new JComboBox();
	JButton a=new JButton("查询");
	
	JButton c=new JButton("——年—月—日");

	JButton e=new JButton("购     票");
	JButton f=new JButton("订     单");
	JButton g=new JButton("返     回");
	String [] city= new String [2];
	String [] next= new String [3];
	String [] now= new String [3];
	String [] last= new String [3];

			private String serverIP = "127.0.0.1";
			private int serverPort = 54321;

			private boolean isConnected = false;
			private Socket socket = null;
			private OutputStream os = null;
			private InputStream is = null;
			private static ObjectInputStream ois = null;
			private static ObjectOutputStream oos = null;
	
	public research2(){
		
		
		createResultTable();
		JScrollPane scrollPane = new JScrollPane(tableResult);
		middle.setBounds(20,180,450, 260);
		middle.add(scrollPane);
		this.setTitle("购票");
		this.setSize(490, 600);
		this.setLocation(300, 50);
		this.setLayout(null);

		String path = "789.jpg";
		ImageIcon background = new ImageIcon(path);
		JLabel beijing = new JLabel(background);
		beijing.setBounds(0, 0, this.getWidth(), this.getHeight()-35);
		JPanel imagePanel = (JPanel) this.getContentPane();
		imagePanel.setOpaque(false);
		this.getLayeredPane().add(beijing, new Integer(Integer.MIN_VALUE));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		City1.setModel(new DefaultComboBoxModel(new String[] {"请选择城市","北京",
				"广州","上海"}));
		City1.setBounds(80, 60, 100, 30);
		City2.setModel(new DefaultComboBoxModel(new String[] {"请选择城市","北京",
				"广州","上海"}));
		City2.setBounds(250,60, 100, 30);
		Year1.setModel(new DefaultComboBoxModel(new String[] {"","2015",
		"2016","2017","2018"}));
        Year1.setBounds(80,100, 60, 30);
        Month1.setModel(new DefaultComboBoxModel(new String[] {"","01","02","03","04","05","06","07","08",
				"09","10","11","12"}));
		Month1.setBounds(160,100, 50, 30);
		Day1.setModel(new DefaultComboBoxModel(new String[]{"","01", "02", "03", "04", "05", "06","07", "08", "09", "10", "11", "12","13",
	    		"14", "15", "16", "17", "18","19", "20", "21", "22", "23", "24","25", "26", "27", "28", "29", "30","31" }));
		Day1.setBounds(240,100, 50, 30);
		
		JLabel name1 = new JLabel("出发城市");
		name1.setAlignmentY(CENTER_ALIGNMENT);
		name1.setBounds(20, 50, 100, 50);
		JLabel name2 = new JLabel("去往城市");
		name2.setAlignmentY(CENTER_ALIGNMENT);
		name2.setBounds(190, 50, 100, 50);
		JLabel name3 = new JLabel("出发日期");
		name3.setAlignmentY(CENTER_ALIGNMENT);
		name3.setBounds(20, 90, 100, 50);
		JLabel name4 = new JLabel("年");
		name4.setAlignmentY(CENTER_ALIGNMENT);
		name4.setBounds(140, 90, 20, 50);
		JLabel name5 = new JLabel("月");
		name5.setAlignmentY(CENTER_ALIGNMENT);
		name5.setBounds(220, 90, 20, 50);
		JLabel name6 = new JLabel("日");
		name6.setAlignmentY(CENTER_ALIGNMENT);
		name6.setBounds(300, 90, 20, 50);
		JLabel name7 = new JLabel("登   陆");
		name7.setAlignmentY(CENTER_ALIGNMENT);
		name7.setForeground(Color.black);
		name7.setBounds(360, 10, 50, 25);
		
	
		c.setEnabled(false);
	
		
		a.setBounds(350, 100, 80, 25);

		c.setBounds(20,150,450, 30);

		e.setBounds(20,450,80, 20);
		f.setBounds(170,450,80, 20);
		g.setBounds(320,450,80, 20);
		
		
		this.add(City1);
		this.add(City2);
		this.add(Year1);
		this.add(Month1);
		this.add(Day1);
		this.add(middle);
		this.add(name1);
		this.add(name2);
		this.add(name3);
		this.add(name4);
		this.add(name5);
		this.add(name6);
		this.add(name7);
		this.add(a);
		
		this.add(c);

		this.add(e);
		this.add(f);
		this.add(g);
		
		
		a.addActionListener(this);
		
		c.addActionListener(this);
		
		e.addActionListener(this);
		f.addActionListener(this);
		g.addActionListener(this);
		
		 name7.addMouseListener(new MouseAdapter(){
			 public void mouseClicked(MouseEvent e){
				 new login();
				 //找到当前窗口并关闭
					Component cmp= e.getComponent();
	            	while(!(cmp instanceof JFrame ) || cmp.getParent() !=null ){
	            	 cmp = cmp.getParent();
	            	}
	            	((JFrame)cmp).dispose();
	            	
			 }
		 });
		
		c.addMouseListener(new MouseAdapter(){
			 public void mouseClicked(MouseEvent e){
				 
					c.setEnabled(false);
					
			 }
		 });
		
		 connectToServer();
	}
	 public void actionPerformed(ActionEvent ae) {
		 if(ae.getSource()==g){
			 new Main();
		 }
		 if(ae.getSource()==a){
			 String bo1 =(String) Year1.getSelectedItem();
			 String bo2 =(String) Month1.getSelectedItem();
			 String bo3 =(String) Day1.getSelectedItem();
			 String bo4=bo1+bo2+bo3;
			 city[0]=(String) City1.getSelectedItem();
			 city[1]=(String) City2.getSelectedItem();
			 Vector<PlaneTicket> searchResult=verifyFlightsNumber( city[0],city[1],bo4);
			 updateResultTable(searchResult);
			 
			 if( city[0].equals("请选择城市")){
				 JOptionPane.showMessageDialog(this, "请选择出发城市！");
			 }
			 else if(city[1].equals("请选择城市")){
				 JOptionPane.showMessageDialog(this, "请选择去往城市！");
			 }
			 else if(bo1.equals("")||bo2.equals("")||bo3.equals("")){
				 JOptionPane.showMessageDialog(this, "请选择日期！");
			 }
			 else if((Integer.parseInt(bo2)<=10)||(bo3.equals("01"))){
			int boo1=Integer.parseInt(bo2)-1;
			int boo2=31;
			int boo3=Integer.parseInt(bo3)+1;
			
			 c.setText(bo1+"年"+bo2+"月"+bo3+"日");
			
			 last[0]=String.valueOf(boo2);
			 last[1]="0"+String.valueOf(boo1);
			 last[2]=bo1;
			 next[0]="0"+String.valueOf(boo3);
			 next[1]=bo2;
			 next[2]=bo1;
			 }
			 else if((Integer.parseInt(bo2)>10)||(bo3.equals("01"))){
				 int boo1=Integer.parseInt(bo2)-1;
					int boo2=31;
					int boo3=Integer.parseInt(bo3)+1;
					
					 c.setText(bo1+"年"+bo2+"月"+bo3+"日");
				
					 last[0]=String.valueOf(boo2);
					 last[1]=String.valueOf(boo1);
					 last[2]=bo1;
					 next[0]="0"+String.valueOf(boo3);
					 next[1]=bo2;
					 next[2]=bo1;
			 }
			 else if((Integer.parseInt(bo2)<10)||(bo3.equals("31"))){
				 int boo1=Integer.parseInt(bo3)-1;
					int boo2=Integer.parseInt(bo2)+1;
					int boo3=01;
					if(boo1<10){
						last[0]="0"+String.valueOf(boo1);
						last[1]=bo2;
						last[2]=bo1;
						
					}
					else{
						last[0]=String.valueOf(boo1);
						last[1]=bo2;
						last[2]=bo1;
						
					}
					 c.setText(bo1+"年"+bo2+"月"+bo3+"日");
					
					 next[0]="0"+String.valueOf(boo3);
					 next[1]="0"+String.valueOf(boo2);
					 next[2]=bo1;
			 }
			 else if((Integer.parseInt(bo1)<9)){
				 int boo1=Integer.parseInt(bo2)-1;
				 int boo2=Integer.parseInt(bo3)+1;
				
				 c.setText(bo1+"年"+bo2+"月"+bo3+"日");
				
				 last[0]="0"+String.valueOf(boo1);
				 last[1]=bo2;
				 last[2]=bo1;
				 next[0]="0"+String.valueOf(boo2);
				 next[1]=bo2;
				 next[2]=bo1;
			 }
			 else if((Integer.parseInt(bo1)<=10)){
				 int boo1=Integer.parseInt(bo2)-1;
				 int boo2=Integer.parseInt(bo3)+1;
				
				 c.setText(bo1+"年"+bo2+"月"+bo3+"日");
				
				 last[0]="0"+String.valueOf(boo1);
				 last[1]=bo2;
				 last[2]=bo1;
				 next[0]=String.valueOf(boo2);
				 next[1]=bo2;
				 next[2]=bo1;
			 }
			 else if((Integer.parseInt(bo1)>10)){
				 int boo1=Integer.parseInt(bo2)-1;
				 int boo2=Integer.parseInt(bo3)+1;
				
				 c.setText(bo1+"年"+bo2+"月"+bo3+"日");
				
				 last[0]=String.valueOf(boo1);
				 last[1]=bo2;
				 last[2]=bo1;
				 next[0]="0"+String.valueOf(boo2);
				 next[1]=bo2;
				 next[2]=bo1;
			 }
			 if((bo2.equals("01"))&&(bo3.equals("01"))){
				 int boo1=Integer.parseInt(bo1)-1;
				
				 c.setText(bo1+"年"+bo2+"月"+bo3+"日");
				
				 last[0]="31";
				 last[1]="12";
				 last[2]=String.valueOf(boo1);
				 next[0]="02";
				 next[1]=bo2;
				 next[2]=bo1;
			 }
			 else if((bo2.equals("12"))&&(bo3.equals("31"))){
				 int boo1=Integer.parseInt(bo1)+1;
				
				 c.setText(bo1+"年"+bo2+"月"+bo3+"日");
				 
				 last[0]="30";
				 last[1]="12";
				 last[2]=bo1;
				 next[0]="01";
				 next[1]="01";
				 next[2]=String.valueOf(boo1);
			 }
			 now [2]=bo1;
			 now [1]=bo2;
			 now [0]=bo3;
		 }
		
		 if(ae.getSource()==c){
			 String bo4=now[2]+now[1]+now[0];
			 Vector<PlaneTicket> searchResult=verifyFlightsNumber( city[0],city[1],bo4);
			 updateResultTable(searchResult);
		 }
		
		 if(ae.getSource()==e){
			 JOptionPane.showMessageDialog(this, "请先登录再购票！");
		 }
		 if(ae.getSource()==f){
			 new DingDan1();
			 this.dispose();
		 }
	 }
	 private void createResultTable() {
			if (tableResult != null)
				return;

			Object[][] data = {};
			  Object[] columnTitle = {"航班号","出发地" ,"目的地", "日期" , "出发时间", "抵达时间", "头等舱", "经济舱"};  
			tableModelDefault = new DefaultTableModel(data, columnTitle);
			tableResult = new JTable(tableModelDefault);
			// 设置为单选
			tableResult.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}
		public static void main(String[] args) {
			research2 pr= new research2();
			  pr.setVisible(true);

		}
		
		 private void updateResultTable(Vector<PlaneTicket> searchResult) {
				if (tableResult == null || tableModelDefault == null)
					return;
				tableModelDefault.setRowCount(0);
				for (int i = 1; i <= searchResult.size(); i++) {
					
					PlaneTicket planeTicket = searchResult.get(i-1);
					int a=Integer.parseInt(planeTicket.getTicketPrice())*2;
					Object data[] = { planeTicket.getFlightsNumber(),planeTicket.getStarting(),planeTicket.getEnding(),planeTicket.getDay(),
							planeTicket.getDepartureTime(),planeTicket.getArriverTime(),
							"$"+String.valueOf(a)+"/"+planeTicket.getSeat(),
							"$"+planeTicket.getTicketPrice()+"/"+planeTicket.getGrade()};
			
					tableModelDefault.addRow(data);
				}
		 }
		 private Vector<PlaneTicket> verifyFlightsNumber(String Starting,String Ending,String Day) {
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
				MessageChaxunReq msgChaxunReq=new MessageChaxunReq(Starting,Ending,Day);
				MessageChaxunAck msgChaxunAck = null;
				try {
					oos.writeObject(msgChaxunReq);			
					msgChaxunAck = (MessageChaxunAck) ois.readObject();;
				} catch (IOException | ClassNotFoundException e) {
					e.printStackTrace();
				}finally{
				}		
				
				return (msgChaxunAck != null ? msgChaxunAck.getPlaneTicket() : null);
			}
		 public void shuanfa(String bo1,String bo2,String bo3){
			  if((Integer.parseInt(bo2)<=10)||(bo3.equals("01"))){
					int boo1=Integer.parseInt(bo2)-1;
					int boo2=31;
					int boo3=Integer.parseInt(bo3)+1;
					
					 c.setText(bo1+"年"+bo2+"月"+bo3+"日");
					
					 last[0]=String.valueOf(boo2);
					 last[1]="0"+String.valueOf(boo1);
					 last[2]=bo1;
					 next[0]="0"+String.valueOf(boo3);
					 next[1]=bo2;
					 next[2]=bo1;
					 }
					 else if((Integer.parseInt(bo2)>10)||(bo3.equals("01"))){
						 int boo1=Integer.parseInt(bo2)-1;
							int boo2=31;
							int boo3=Integer.parseInt(bo3)+1;
							 
							 c.setText(bo1+"年"+bo2+"月"+bo3+"日");
							
							 last[0]=String.valueOf(boo2);
							 last[1]=String.valueOf(boo1);
							 last[2]=bo1;
							 next[0]="0"+String.valueOf(boo3);
							 next[1]=bo2;
							 next[2]=bo1;
					 }
					 else if((Integer.parseInt(bo2)<10)||(bo3.equals("31"))){
						 int boo1=Integer.parseInt(bo3)-1;
							int boo2=Integer.parseInt(bo2)+1;
							int boo3=01;
							if(boo1<10){
								last[0]="0"+String.valueOf(boo1);
								last[1]=bo2;
								last[2]=bo1;
								
							}
							else{
								last[0]=String.valueOf(boo1);
								last[1]=bo2;
								last[2]=bo1;
								
							}
							 c.setText(bo1+"年"+bo2+"月"+bo3+"日");
						
							 next[0]="0"+String.valueOf(boo3);
							 next[1]="0"+String.valueOf(boo2);
							 next[2]=bo1;
					 }
					 else if((Integer.parseInt(bo1)<9)){
						 int boo1=Integer.parseInt(bo2)-1;
						 int boo2=Integer.parseInt(bo3)+1;
						
						 c.setText(bo1+"年"+bo2+"月"+bo3+"日");
						
						 last[0]="0"+String.valueOf(boo1);
						 last[1]=bo2;
						 last[2]=bo1;
						 next[0]="0"+String.valueOf(boo2);
						 next[1]=bo2;
						 next[2]=bo1;
					 }
					 else if((Integer.parseInt(bo1)<=10)){
						 int boo1=Integer.parseInt(bo2)-1;
						 int boo2=Integer.parseInt(bo3)+1;
						
						 c.setText(bo1+"年"+bo2+"月"+bo3+"日");
						 
						 last[0]="0"+String.valueOf(boo1);
						 last[1]=bo2;
						 last[2]=bo1;
						 next[0]=String.valueOf(boo2);
						 next[1]=bo2;
						 next[2]=bo1;
					 }
					 else if((Integer.parseInt(bo1)>10)){
						 int boo1=Integer.parseInt(bo2)-1;
						 int boo2=Integer.parseInt(bo3)+1;
						 
						 c.setText(bo1+"年"+bo2+"月"+bo3+"日");
						
						 last[0]=String.valueOf(boo1);
						 last[1]=bo2;
						 last[2]=bo1;
						 next[0]="0"+String.valueOf(boo2);
						 next[1]=bo2;
						 next[2]=bo1;
					 }
					 if((bo2.equals("01"))&&(bo3.equals("01"))){
						 int boo1=Integer.parseInt(bo1)-1;
						
						 c.setText(bo1+"年"+bo2+"月"+bo3+"日");
						
						 last[0]="31";
						 last[1]="12";
						 last[2]=String.valueOf(boo1);
						 next[0]="02";
						 next[1]=bo2;
						 next[2]=bo1;
					 }
					 else if((bo2.equals("12"))&&(bo3.equals("31"))){
						 int boo1=Integer.parseInt(bo1)+1;
						
						 c.setText(bo1+"年"+bo2+"月"+bo3+"日");
						
						 last[0]="30";
						 last[1]="12";
						 last[2]=bo1;
						 next[0]="01";
						 next[1]="01";
						 next[2]=String.valueOf(boo1);
					 }
					 now [2]=bo1;
					 now [1]=bo2;
					 now [0]=bo3;
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

