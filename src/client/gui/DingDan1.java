package client.gui;

import info.OrderForm;
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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import messages.MessageDelAck;
import messages.MessageDelReq;
import messages.MessageLogin1Ack;
import messages.MessageLogin1Req;
import messages.MessageLoginAck;
import messages.MessageLoginReq;
import messages.MessageMoneyAck;
import messages.MessageMoneyReq;
import messages.MessageOrderfrom1Ack;
import messages.MessageOrderfrom1Req;
import messages.MessageOrderfromAck;
import messages.MessageOrderfromReq;
import server.datebase.JPYDDatabase;

	public class DingDan1 extends JFrame implements ActionListener{
		private JTable tableResult = null;
		DefaultTableModel tableModelDefault = null;
		JPanel middle = new JPanel();
		JTextField txt= new JTextField();
		JPasswordField txt1= new JPasswordField();
		JTextField txt2= new JTextField();
		JButton a=new JButton("��     Ʊ");
		JButton b=new JButton("��     ��");
		JButton c=new JButton("��     ��");
		JButton d=new JButton("��ѯ");
		JButton e=new JButton("��ѯ");
		String [] num= new String [3];

		private String serverIP = "127.0.0.1";
		private int serverPort = 54321;

		private boolean isConnected = false;
		private Socket socket = null;
		private OutputStream os = null;
		private InputStream is = null;
		private static ObjectInputStream ois = null;
		private static ObjectOutputStream oos = null;

		public void DingDan1(final String Account,String Name){
			User user = verifyAccount(Account);
			num[1]=Account;
			num[0]=Name;
			num[2]=user.getPwd();
			
			createResultTable();
			JScrollPane scrollPane = new JScrollPane(tableResult);
			middle.setBounds(20,160,450, 300);
			middle.add(scrollPane);

			this.setTitle("����1");
			this.setSize(550, 600);
			this.setLocation(300, 50);
			this.setLayout(null);
			
			String path = "����2.jpg";
			ImageIcon background = new ImageIcon(path);
			JLabel beijing = new JLabel(background);
			beijing.setBounds(0, 0, this.getWidth(), this.getHeight()-35);
			JPanel imagePanel = (JPanel) this.getContentPane();
			imagePanel.setOpaque(false);
			this.getLayeredPane().add(beijing, new Integer(Integer.MIN_VALUE));
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setVisible(true);			
			
			
			JLabel name0 = new JLabel("�˳���¼");
			name0.setAlignmentY(CENTER_ALIGNMENT);
			name0.setForeground(Color.white);
			name0.setBounds(410, 10, 80, 25);
			JLabel name1 = new JLabel("���˺Ų�ѯ");
			name1.setAlignmentY(CENTER_ALIGNMENT);
			name1.setBounds(20, 50, 80, 25);
			JLabel name2 = new JLabel("���˿Ͳ�ѯ");
			name2.setAlignmentY(CENTER_ALIGNMENT);
			name2.setBounds(20, 100, 80, 25);
			JLabel name3 = new JLabel("�˺ţ�");
			name3.setAlignmentY(CENTER_ALIGNMENT);
			name3.setBounds(100, 50, 50, 25);
			JLabel name4 = new JLabel("���룺");
			name4.setAlignmentY(CENTER_ALIGNMENT);
			name4.setBounds(280, 50, 50, 25);
			JLabel name5 = new JLabel("������");
			name5.setAlignmentY(CENTER_ALIGNMENT);
			name5.setBounds(100, 100, 50, 25);
			JLabel name6 = new JLabel("��   ��");
			name6.setAlignmentY(CENTER_ALIGNMENT);
			name6.setForeground(Color.white);
			name6.setBounds(470, 10, 100, 25);
			
			a.setBounds(20,480,80, 20);
			b.setBounds(170,480,80, 20);
			c.setBounds(320,480,80, 20);
			d.setBounds(440,50,80, 20);
			e.setBounds(280, 100, 80, 20);
			
			txt.setBounds(140,50,100,25);
			 txt1.setBounds(320,50,100,25);
			 txt1.setEchoChar('*'); 
			 txt2.setBounds(140,100,100,25);
			 
			 txt.setText(num[1]);
			 txt1.setText(num[2]);
			 txt2.setText(num[0]);
			
			this.add(name0);
			this.add(name1);
			this.add(name2);
			this.add(name3);
			this.add(name4);
			this.add(name5);
			this.add(name6);
			this.add(txt);
			this.add(txt1);
			this.add(txt2);
			this.add(middle);
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
			
			txt.addMouseListener(new MouseAdapter() {
	           	 public void mouseClicked(MouseEvent e) {
	           	 txt.setText("");}
	           	   });
			txt1.addMouseListener(new MouseAdapter() {
	           	 public void mouseClicked(MouseEvent e) {
	           	 txt1.setText("");}
	           	   });
			 txt2.addMouseListener(new MouseAdapter() {
	           	 public void mouseClicked(MouseEvent e) {
	           	 txt2.setText("");}
	           	   });
			 name6.addMouseListener(new MouseAdapter(){
				 public void mouseClicked(MouseEvent e){
					 Money  f2=new Money();
						f2.Money(Account);
		            	
				 }
			 });
			 name0.addMouseListener(new MouseAdapter(){
				 public void mouseClicked(MouseEvent e){
					 new DingDan1();
					//�ҵ���ǰ���ڲ��ر�
						Component cmp= e.getComponent();
		            	while(!(cmp instanceof JFrame ) || cmp.getParent() !=null ){
		            	 cmp = cmp.getParent();
		            	}
		            	((JFrame)cmp).dispose();	
				 }
			 });
			 connectToServer();
		}
		 public void actionPerformed(ActionEvent ae){
			 if(ae.getSource()==a){
				 String Name=(String) tableResult.getValueAt(tableResult.getSelectedRow(), 0);
				 String FlightsNumber=(String) tableResult.getValueAt(tableResult.getSelectedRow(), 1);
				 String DAY=(String) tableResult.getValueAt(tableResult.getSelectedRow(), 4);
				 piaomess  f1=new piaomess();
				f1.Piao(FlightsNumber,DAY,Name);
			 }
			 if(ae.getSource()==b){
				 String Name=(String) tableResult.getValueAt(tableResult.getSelectedRow(), 0);
				 String FlightsNumber=(String) tableResult.getValueAt(tableResult.getSelectedRow(), 1);
				 String DAY=(String) tableResult.getValueAt(tableResult.getSelectedRow(), 4);
				 String State=(String) tableResult.getValueAt(tableResult.getSelectedRow(), 7);
				 if(State.equals("δ����")){
					 UpDel( FlightsNumber, DAY, Name);
					 JOptionPane.showMessageDialog(this, "�˶��ɹ�,�˿��3~5�յ��ˣ�");
				 }
				 
			 }
			 if(ae.getSource()==c){
				 resecher3  f1=new resecher3();
					f1.ChaXun3(num[0],num[1]);
					this.dispose();
			 }
			 if(ae.getSource()==d){
				 String account = txt.getText();
					String password = new String(txt1.getPassword());
					
					User user = verifyAccount(account, password);
					if(txt.getText().equals("")){
						JOptionPane.showMessageDialog(this, "�˺Ų���Ϊ��");
					}
					else if( user != null){
						Vector<OrderForm>  searchResult=verifyFlightsNumber(user.getAccount());
						 updateResultTable(searchResult);
						}
					
					else{
						JOptionPane.showMessageDialog(this, "�˺Ż��������");
						txt1.setText(null);
				
			 }
			 }
			 if(ae.getSource()==e){
				 String NAME = txt2.getText();
				 Vector<OrderForm>  searchResult=verifyFlightsNumber1(NAME);
				 updateResultTable(searchResult);
			 }
			 
		 }
		 private void createResultTable() {
				if (tableResult != null)
					return;

				Object[][] data = {};
				  Object[] columnTitle = {"����"  ,"�����","������", "Ŀ�ĵ�" , " ����", "�ǻ���", "��λ", "����״̬"};  
				tableModelDefault = new DefaultTableModel(data, columnTitle);
				tableResult = new JTable(tableModelDefault);
				// ����Ϊ��ѡ
				tableResult.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			}
		 private User verifyAccount(String account, String password) {
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
				MessageLoginReq msgLoginReq = new MessageLoginReq(account, password);
				MessageLoginAck msgLoginAck = null;
				try {
					oos.writeObject(msgLoginReq);			
					msgLoginAck = (MessageLoginAck) ois.readObject();;
				} catch (IOException | ClassNotFoundException e) {
					e.printStackTrace();
				}finally{
				}		
				
				return (msgLoginAck != null ? msgLoginAck.getUser() : null);
			}
		 private void updateResultTable(Vector<OrderForm> searchResult) {
				if (tableResult == null || tableModelDefault == null)
					return;
				tableModelDefault.setRowCount(0);
				for (int i = 1; i <= searchResult.size(); i++) {
					
					OrderForm orderform = searchResult.get(i-1);
					Object data[] = {orderform.getName(),orderform.getFlightsNumber(),orderform.getStarting(),orderform.getEnding(),
							orderform.getDay(),orderform.getBoarding(),orderform.getGrade(),orderform.getState()};
			
					tableModelDefault.addRow(data);
				}
		 }
		 private Vector<OrderForm> verifyFlightsNumber(String Account  ) {
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
				MessageOrderfromReq msgOrderfromReq = new MessageOrderfromReq(Account);
				MessageOrderfromAck msgOrderfromAck = null;
				try {
					oos.writeObject(msgOrderfromReq);			
					msgOrderfromAck = (MessageOrderfromAck) ois.readObject();;
				} catch (IOException | ClassNotFoundException e) {
					e.printStackTrace();
				}finally{
				}		
				
				return (msgOrderfromAck != null ? msgOrderfromAck.getOrderForm() : null);
			}
		 private Vector<OrderForm> verifyFlightsNumber1(String Name  ) {
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
				MessageOrderfrom1Req msgOrderfromReq = new MessageOrderfrom1Req(Name);
				MessageOrderfrom1Ack msgOrderfromAck = null;
				try {
					oos.writeObject(msgOrderfromReq);			
					msgOrderfromAck = (MessageOrderfrom1Ack) ois.readObject();;
				} catch (IOException | ClassNotFoundException e) {
					e.printStackTrace();
				}finally{
				}		
				
				return (msgOrderfromAck != null ? msgOrderfromAck.getOrderForm() : null);
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
		 private Object UpDel(String FlightsNumber,String Day,String Name){
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
				MessageDelReq msgLoginReq = new MessageDelReq( FlightsNumber, Day, Name);
				MessageDelAck msgLoginAck = null;
				try {
					oos.writeObject(msgLoginReq);			
					msgLoginAck = (MessageDelAck) ois.readObject();;
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
