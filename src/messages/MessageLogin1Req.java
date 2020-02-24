package messages;

import java.io.Serializable;

public class MessageLogin1Req extends Message implements Serializable {
	private String account = null;

	public MessageLogin1Req(String account) {
		super(MSG_TYPE.MSG_LOGIN1_REQ);
		this.account = account;
	}

	public String getAccount() {
		return account;
	}

}
