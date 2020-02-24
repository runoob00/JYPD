package messages;

import java.io.Serializable;

public class MessageLogin2Req extends Message implements Serializable {
	private String account = null;
	private String pwd = null;

	public MessageLogin2Req(String account, String pwd) {
		super(MSG_TYPE.MSG_LOGIN2_REQ);
		this.account = account;
		this.pwd = pwd;
	}

	public String getAccount() {
		return account;
	}

	public boolean verify(String account, String pwd) {
		if (this.account.equals(account) && this.pwd.equals(pwd))
			return true;

		return false;
	}

	public boolean verify(String pwd) {
		if (this.pwd.equals(pwd))
			return true;

		return false;
	}
}
