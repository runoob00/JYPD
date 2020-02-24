package messages;

import java.io.Serializable;

import messages.Message.MSG_TYPE;

public class MessageOrderfromReq extends Message implements Serializable{
	private String account = null;
	public MessageOrderfromReq(String account) {
		super(MSG_TYPE.MSG_Orderfrom_REQ);
		this.account = account;
		
	}
	public String getAccount() {
		return account;
	}

}
