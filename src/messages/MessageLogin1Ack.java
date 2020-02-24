package messages;

import info.User;

import java.io.Serializable;

public class MessageLogin1Ack extends Message implements Serializable {
	private String account = null;
	private boolean success = false;
	private String failReason = null;
	private User user = null;

	public MessageLogin1Ack(String stuNum) {
		super(MSG_TYPE.MSG_LOGIN1_ACK);
		account = stuNum;
	}

	public String getAccount() {
		return account;
	}


	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public void setLoginResult(boolean result) {
		success = result;
	}

	public boolean getLoginResult() {
		return success;
	}

	public void setFailReason(String reason) {
		failReason = reason;
	}

	public String getFailReason() {
		return failReason;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}
}
