package messages;

import java.io.Serializable;

public class MessageLogin3Req extends Message implements Serializable {
	private String account = null;

	public MessageLogin3Req(String account) {
		super(MSG_TYPE.MSG_LOGIN3_REQ);
		this.account = account;
	}

	public String getAccount() {
		return account;
	}

}
