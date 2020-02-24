package client;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import messages.Message;
import messages.MessageExample;

public class ClientDummy {
	public static void main(String[] args) {
		new ClientDummy();
	} 
	// ����Server IPΪ����IP
	private String serverIP = "172.27.35.60";
	private int serverPort = 54321;
	// ��¼�Ƿ��Ѿ������˵���������TCP socket����
	private boolean isConnected = false;
	private Socket socket = null;
	private OutputStream os = null;
	private InputStream is = null;
	private static ObjectInputStream ois = null;
	private static ObjectOutputStream oos = null;
	public ClientDummy() {
		if (connectToServer()) {
			System.out.println("sendMessageExample: " + sendMessageExample());
		} else {
			System.out.println("Failed to connect to server " + serverIP + "/"
					+ serverIP);
		}

		try {
			Thread.sleep(1000); // ��1000������˳�
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		disconnectFromServer();
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
	private void disconnectFromServer(){
		if(socket != null){
			try {				
				socket.shutdownOutput();
				socket.shutdownInput();
				socket.close();
				socket = null;
				oos = null; 
				ois = null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	private static synchronized boolean sendMessage(Message msg) {
		try {
			oos.writeObject(msg);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	private static synchronized Message receiveMessage() {
		try {
			return (Message) ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	private boolean sendMessageExample() {
		MessageExample msgSnt = new MessageExample();
		msgSnt.setData("Hello");
		
		if (sendMessage(msgSnt)) {
			// ���շ��������ص���Ϣ����ʾ����
			MessageExample msgRev = (MessageExample) receiveMessage();
			if (msgRev != null) {
				System.out.println("Message Received: " + msgRev.getData());
				return true;
			}
		}
		return false;
	}
}
