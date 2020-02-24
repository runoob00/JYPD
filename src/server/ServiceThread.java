package server;

import info.Flights;
import info.OrderForm;
import info.PlaneTicket;
import info.User;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Vector;

import server.datebase.JPYDDatabase;
import messages.Message;
import messages.MessageChaxunAck;
import messages.MessageChaxunReq;
import messages.MessageDelAck;
import messages.MessageDelReq;
import messages.MessageExample;
import messages.MessageFlights1Ack;
import messages.MessageFlights1Req;
import messages.MessageFlightsAck;
import messages.MessageFlightsReq;
import messages.MessageHangBanAck;
import messages.MessageHangBanReq;
import messages.MessageLogin1Ack;
import messages.MessageLogin1Req;
import messages.MessageLogin2Ack;
import messages.MessageLogin2Req;
import messages.MessageLogin3Ack;
import messages.MessageLogin3Req;
import messages.MessageLoginAck;
import messages.MessageLoginReq;
import messages.MessageMoneyAck;
import messages.MessageMoneyReq;
import messages.MessageOrderfrom1Ack;
import messages.MessageOrderfrom1Req;
import messages.MessageOrderfrom2Ack;
import messages.MessageOrderfrom2Req;
import messages.MessageOrderfrom3Ack;
import messages.MessageOrderfrom3Req;
import messages.MessageOrderfromAck;
import messages.MessageOrderfromReq;
import messages.MessagePlaneticket1Ack;
import messages.MessagePlaneticket1Req;
import messages.MessagePlaneticket2Ack;
import messages.MessagePlaneticket2Req;
import messages.MessagePlaneticket3Ack;
import messages.MessagePlaneticket3Req;
import messages.MessagePlaneticketAck;
import messages.MessagePlaneticketReq;
import messages.MessagePwdAck;
import messages.MessagePwdReq;
import messages.MessageUser1Ack;
import messages.MessageUser1Req;
import messages.MessageUserAck;
import messages.MessageUserReq;

public class ServiceThread extends Thread {
	private Socket socket = null;
	private ObjectOutputStream oos = null;

	public ServiceThread(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		handleClient();
		closeClient();
	}

	private void closeClient() {
		if (socket != null) {
			try {
				socket.shutdownOutput();
				socket.shutdownInput();
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void handleClient() {
		OutputStream os = null;
		InputStream is = null;
		ObjectInputStream ois = null;

		try {
			// 创建对象数据流
			os = socket.getOutputStream();
			is = socket.getInputStream();
			oos = new ObjectOutputStream(os);
			ois = new ObjectInputStream(is);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return;
		}
		// 处理来自客户端的消息对象
		while (true) {
			// 从对象输入数据流中读取数据对象
			Message msgReceived = null;
			try {
				msgReceived = (Message) ois.readObject();
			} catch (ClassNotFoundException e) {
				System.out.println(e.getMessage());
				continue;
			} catch (IOException e) { // Socket 出错，停止对次Socket的处理
				System.out.println(e.getMessage());
				return;
			}

			// 按消息类型处理
			switch (msgReceived.msgType) {
			case MSG_EXAMPLE:
				handleMsgExample((MessageExample) msgReceived, oos);
				break;
			case MSG_LOGIN_REQ:
				handleLoginRequest((MessageLoginReq) msgReceived, oos);
				break;
			case MSG_LOGIN1_REQ:
				handleLogin1Request((MessageLogin1Req) msgReceived, oos);
				break;
			case MSG_LOGIN2_REQ:
				handleLogin2Request((MessageLogin2Req) msgReceived, oos);
				break;
			case MSG_LOGIN3_REQ:
				handleLogin3Request((MessageLogin3Req) msgReceived, oos);
				break;
			case MSG_User_REQ:
				handleUserRequest((MessageUserReq) msgReceived, oos);
				break;
			case MSG_User1_REQ:
				handleUser1Request((MessageUser1Req) msgReceived, oos);
				break;
			case MSG_Money_REQ:
				handleMoneyRequest((MessageMoneyReq) msgReceived, oos);
				break;
			case MSG_Pwd_REQ:
				handlePwdRequest((MessagePwdReq) msgReceived, oos);
				break;
			case MSG_planeticket_REQ:
				handlePlaneticketRequest((MessagePlaneticketReq) msgReceived, oos);
				break;
			case MSG_planeticket1_REQ:
				handlePlaneticket1Request((MessagePlaneticket1Req) msgReceived, oos);
				break;
			case MSG_planeticket2_REQ:
				handlePlaneticket2Request((MessagePlaneticket2Req) msgReceived, oos);
				break;
			case MSG_planeticket3_REQ:
				handlePlaneticket3Request((MessagePlaneticket3Req) msgReceived, oos);
				break;
			case MSG_Chaxun_REQ:
				handleChaxunRequest((MessageChaxunReq) msgReceived, oos);
				break;
			case MSG_Flights_REQ:
				handleFlightsRequest((MessageFlightsReq) msgReceived, oos);
				break;
			case MSG_Flights1_REQ:
				handleFlights1Request((MessageFlights1Req) msgReceived, oos);
				break;
			case MSG_Orderfrom_REQ:
				handleOrderformRequest((MessageOrderfromReq) msgReceived, oos);
				break;
			case MSG_Orderfrom1_REQ:
				handleOrderform1Request((MessageOrderfrom1Req) msgReceived, oos);
				break;
			case MSG_Orderfrom2_REQ:
				handleOrderform2Request((MessageOrderfrom2Req) msgReceived, oos);
				break;
			case MSG_Orderfrom3_REQ:
				handleOrderform3Request((MessageOrderfrom3Req) msgReceived, oos);
				break;
			case MSG_Del_REQ:
				handleDelRequest((MessageDelReq) msgReceived, oos);
				break;
			case MSG_HangBan_REQ:
				handleHangBanRequest((MessageHangBanReq) msgReceived, oos);
				break;
			default:
				System.out.println("Uknown message received: "
						+ msgReceived.msgType);
			}
		}
	}



	



	private boolean sendMessage(Message msg, ObjectOutputStream oos) {
		try {
			oos.writeObject(msg);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	private void handleMsgExample(MessageExample msgExample,
			ObjectOutputStream oos) {
		String data = msgExample.getData();
		System.out.println(data);
		msgExample.setData("Example Message Received: " + data);
		sendMessage(msgExample, oos);
	}
	private void handleLoginRequest(MessageLoginReq msgLogin,
			ObjectOutputStream oos) {
		boolean verifyPassed = false;
		User user = JPYDDatabase.userQquery(msgLogin.getAccount());

		MessageLoginAck msgLoginAck = new MessageLoginAck(msgLogin.getAccount());
		
		if (!(msgLogin.getAccount().equals("admin"))
				&& user.getName().equals(msgLogin.getAccount())
				&& msgLogin.verify(user.getPwd())) {
			verifyPassed = true;
			msgLoginAck.setFailReason("用户信息正确");
		} else {
			msgLoginAck.setFailReason("用户信息不正确");
		}
		System.out.println("Account: " + msgLogin.getAccount()
				+ " Verification " + verifyPassed);

		msgLoginAck.setLoginResult(verifyPassed);
		msgLoginAck.setUser(user);

		sendMessage(msgLoginAck, oos);
	}
	private void handleLogin2Request(MessageLogin2Req msgLogin,
			ObjectOutputStream oos) {
		boolean verifyPassed = false;
		User user = JPYDDatabase.userQquery1(msgLogin.getAccount());

		MessageLogin2Ack msgLoginAck = new MessageLogin2Ack(msgLogin.getAccount());
		// 登陆成功的条件：用户帐号存在，并且不是管理员, 密码正确
		if (!(msgLogin.getAccount().equals("admin"))
				&& user.getAccount().equals(msgLogin.getAccount())
				&& msgLogin.verify(user.getPwd())) {
			verifyPassed = true;
			msgLoginAck.setFailReason("用户信息正确！");
		} else {
			msgLoginAck.setFailReason("用户信息不正确！");
		}
		if(verifyPassed == false){
			user = null;
		}
		msgLoginAck.setLoginResult(verifyPassed);
		msgLoginAck.setUser(user);

		sendMessage(msgLoginAck, oos);
	}
	private void handleLogin3Request(MessageLogin3Req msgLogin,
			ObjectOutputStream oos) {
		boolean verifyPassed = true;
		User user = JPYDDatabase.userQquery1(msgLogin.getAccount());
		MessageLogin3Ack msgLoginAck = new MessageLogin3Ack(msgLogin.getAccount());
		msgLoginAck.setLoginResult(verifyPassed);
		msgLoginAck.setUser(user);

		sendMessage(msgLoginAck, oos);
	}
	private void handleLogin1Request(MessageLogin1Req msgLogin,
			ObjectOutputStream oos) {
		boolean verifyPassed = true;
		User user = JPYDDatabase.userQquery(msgLogin.getAccount());
		MessageLogin1Ack msgLoginAck = new MessageLogin1Ack(msgLogin.getAccount());
		msgLoginAck.setLoginResult(verifyPassed);
		msgLoginAck.setUser(user);

		sendMessage(msgLoginAck, oos);
	}

	private void handleMoneyRequest(MessageMoneyReq msgReceived,
			ObjectOutputStream oos2) {
		boolean verifyPassed = true;
		JPYDDatabase.Money(msgReceived.getAccount(),msgReceived.getMoney());
		MessageMoneyAck msgLoginAck = new MessageMoneyAck(msgReceived.getAccount(),msgReceived.getMoney());
		msgLoginAck.setLoginResult(verifyPassed);

		sendMessage(msgLoginAck, oos);
	}
	private void handleDelRequest(MessageDelReq msgReceived,
			ObjectOutputStream oos2) {
		JPYDDatabase.OrderForm(msgReceived.getFlightsNumber(),msgReceived.getDay(),msgReceived.getName());
		MessageDelAck msgLoginAck = new MessageDelAck(msgReceived.getFlightsNumber(),msgReceived.getDay(),msgReceived.getName());
		
		sendMessage(msgLoginAck, oos);
	}

	private void handleHangBanRequest(MessageHangBanReq msgReceived,
			ObjectOutputStream oos2) {
		JPYDDatabase.HangBan(msgReceived.getFlightsNumber(),msgReceived.getBoard(),msgReceived.getStart(),msgReceived.getEnd());
		MessageHangBanAck msgLoginAck = new MessageHangBanAck(msgReceived.getFlightsNumber(),msgReceived.getBoard(),msgReceived.getStart(),msgReceived.getEnd());
		
		sendMessage(msgLoginAck, oos);
		
	}
	private void handleUserRequest(MessageUserReq msgReceived,
			ObjectOutputStream oos2) {
		boolean verifyPassed = true;
		JPYDDatabase.User(msgReceived.getAccount(),msgReceived.getPwd(),msgReceived.getName(),msgReceived.getID(),msgReceived.getQuestion(),msgReceived.getAnswer());
		MessageUserAck msgLoginAck = new MessageUserAck(msgReceived.getAccount(),msgReceived.getPwd(),msgReceived.getName(),msgReceived.getID(),msgReceived.getQuestion(),msgReceived.getAnswer());
		msgLoginAck.setLoginResult(verifyPassed);

		sendMessage(msgLoginAck, oos);
	}
	private void handleUser1Request(MessageUser1Req msgReceived,
			ObjectOutputStream oos2) {
		boolean verifyPassed = true;
		JPYDDatabase.User1(msgReceived.getAccount(),msgReceived.getPwd(),msgReceived.getName(),msgReceived.getID(),msgReceived.getQuestion(),msgReceived.getAnswer());
		MessageUser1Ack msgLoginAck = new MessageUser1Ack(msgReceived.getAccount(),msgReceived.getPwd(),msgReceived.getName(),msgReceived.getID(),msgReceived.getQuestion(),msgReceived.getAnswer());
		msgLoginAck.setLoginResult(verifyPassed);

		sendMessage(msgLoginAck, oos);
	}
	private void handlePwdRequest(MessagePwdReq msgReceived,
			ObjectOutputStream oos2) {
		boolean verifyPassed = true;
		JPYDDatabase.Pwd(msgReceived.getAccount(),msgReceived.getPwd());
		MessagePwdAck msgLoginAck = new MessagePwdAck(msgReceived.getAccount(),msgReceived.getPwd());
		msgLoginAck.setLoginResult(verifyPassed);

		sendMessage(msgLoginAck, oos);
	}



	private void handlePlaneticketRequest(MessagePlaneticketReq msgReceived,
			ObjectOutputStream oos2) {
		JPYDDatabase.planeticket(msgReceived.getFlightsNumber(),msgReceived.getSurplusGrade(),msgReceived.getDay());
		MessagePlaneticketAck msgLoginAck = new MessagePlaneticketAck(msgReceived.getFlightsNumber(),msgReceived.getSurplusGrade(),msgReceived.getDay());
		sendMessage(msgLoginAck, oos);
	}
	private void handlePlaneticket1Request(MessagePlaneticket1Req msgReceived,
			ObjectOutputStream oos2) {
		JPYDDatabase.planeticket1(msgReceived.getFlightsNumber(),msgReceived.getSurplusSeat(),msgReceived.getDay());
		MessagePlaneticket1Ack msgLoginAck = new MessagePlaneticket1Ack(msgReceived.getFlightsNumber(),msgReceived.getSurplusSeat(),msgReceived.getDay());
		sendMessage(msgLoginAck, oos);
	}
	private void handleChaxunRequest(MessageChaxunReq msgReceived,
			ObjectOutputStream oos2) {
		boolean verifyPassed = true;
		 Vector<PlaneTicket> planeTicket = JPYDDatabase.PlaneTicketQquery1(msgReceived.getStarting(),msgReceived.getEnding(),msgReceived.getDay());
		 MessageChaxunAck msgChaxunAck= new MessageChaxunAck(msgReceived.getStarting());
		 
		 msgChaxunAck.setLoginResult(verifyPassed);
		 msgChaxunAck.setPlaneTicket(planeTicket);
		 sendMessage(msgChaxunAck, oos);
	}

	private void handlePlaneticket2Request(MessagePlaneticket2Req msgReceived,
			ObjectOutputStream oos2) {
		 Vector<PlaneTicket> planeTicket = JPYDDatabase.PlaneTicketQquery();
		 MessagePlaneticket2Ack msgChaxunAck= new MessagePlaneticket2Ack();
		 
		 msgChaxunAck.setPlaneTicket(planeTicket);
		 sendMessage(msgChaxunAck, oos);
	}

	private void handlePlaneticket3Request(MessagePlaneticket3Req msgReceived,
			ObjectOutputStream oos2) {
		JPYDDatabase.Planeticket(msgReceived.getFlightsNumber(),msgReceived.getBoarding(),msgReceived.getStarting(),msgReceived.getEnding(),msgReceived.getDay(),
				msgReceived.getDepartureTime(),msgReceived.getArriverTime(),msgReceived.getTicketPrice(),msgReceived.getSeat(),msgReceived.getGrade(),
				msgReceived.getSurplusSeat(),msgReceived.getSurplusGrade());
		MessagePlaneticket3Ack msgChaxunAck= new MessagePlaneticket3Ack(msgReceived.getFlightsNumber(),msgReceived.getBoarding(),msgReceived.getStarting(),msgReceived.getEnding(),msgReceived.getDay(),
				msgReceived.getDepartureTime(),msgReceived.getArriverTime(),msgReceived.getTicketPrice(),msgReceived.getSeat(),msgReceived.getGrade(),
				msgReceived.getSurplusSeat(),msgReceived.getSurplusGrade());
		 sendMessage(msgChaxunAck, oos);
	}


	private void handleFlightsRequest(MessageFlightsReq msgReceived,
			ObjectOutputStream oos2) {
		PlaneTicket planeTicket = JPYDDatabase.planeticketQquery(msgReceived.getFlightsNumber(),msgReceived.getDay());
		MessageFlightsAck msgFlightsAck= new MessageFlightsAck(msgReceived.getFlightsNumber(),msgReceived.getDay());
		msgFlightsAck.setPlaneTicket(planeTicket);
		 sendMessage(msgFlightsAck, oos);
	}
	private void handleFlights1Request(MessageFlights1Req msgReceived,
			ObjectOutputStream oos2) {
		Flights flights = JPYDDatabase.flightsQquery(msgReceived.getFlightsNumber());
		MessageFlights1Ack msgFlightsAck= new MessageFlights1Ack(msgReceived.getFlightsNumber());
		msgFlightsAck.setFlights(flights);
		 sendMessage(msgFlightsAck, oos);
	}

	private void handleOrderformRequest(MessageOrderfromReq msgReceived,
			ObjectOutputStream oos2) {
		boolean verifyPassed = true;
		 Vector<OrderForm> orderform = JPYDDatabase.OrderFormtQquery1(msgReceived.getAccount());
		 MessageOrderfromAck msgOrderfromAck= new MessageOrderfromAck(msgReceived.getAccount());
		 msgOrderfromAck.setLoginResult(verifyPassed);
		 msgOrderfromAck.setOrderForm(orderform);
		 sendMessage(msgOrderfromAck, oos);
	}
	private void handleOrderform1Request(MessageOrderfrom1Req msgReceived,
			ObjectOutputStream oos2) {
		boolean verifyPassed = true;
		 Vector<OrderForm> orderform = JPYDDatabase.OrderFormtQquery2(msgReceived.getName());
		 MessageOrderfrom1Ack msgOrderfrom1Ack= new MessageOrderfrom1Ack(msgReceived.getName());
		 msgOrderfrom1Ack.setLoginResult(verifyPassed);
		 msgOrderfrom1Ack.setOrderForm(orderform);
		 sendMessage(msgOrderfrom1Ack, oos);
	}
	private void handleOrderform2Request(MessageOrderfrom2Req msgReceived,
			ObjectOutputStream oos2) {
		JPYDDatabase.OrderForm(msgReceived.getAccount(), msgReceived.getName(), msgReceived.getID(), msgReceived.getFlightsNumber(), msgReceived.getBoarding(), msgReceived.getStarting(), msgReceived.getEnding(), msgReceived.getDay(), msgReceived.getDepartureTime(), msgReceived.getArriverTime(), msgReceived.getTicketPrice(), msgReceived.getGrade());
		 MessageOrderfrom2Ack msgOrderfrom1Ack= new MessageOrderfrom2Ack(msgReceived.getAccount(), msgReceived.getName(), msgReceived.getID(), msgReceived.getFlightsNumber(), msgReceived.getBoarding(), msgReceived.getStarting(), msgReceived.getEnding(), msgReceived.getDay(), msgReceived.getDepartureTime(), msgReceived.getArriverTime(), msgReceived.getTicketPrice(), msgReceived.getGrade());
		 sendMessage(msgOrderfrom1Ack, oos);
	}
	private void handleOrderform3Request(MessageOrderfrom3Req msgReceived,
			ObjectOutputStream oos2) {
		OrderForm orderForm = JPYDDatabase.orderformQquery(msgReceived.getFlightsNumber(),msgReceived.getDay(),msgReceived.getName());
		 MessageOrderfrom3Ack msgOrderfrom1Ack= new MessageOrderfrom3Ack(msgReceived.getFlightsNumber(),msgReceived.getDay(),msgReceived.getName());
		 msgOrderfrom1Ack.setOrderForm(orderForm);
		 sendMessage(msgOrderfrom1Ack, oos);
	}

}
