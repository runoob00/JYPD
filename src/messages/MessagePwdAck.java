
	package messages;

	import info.User;

	import java.io.Serializable;

	public class MessagePwdAck extends Message implements Serializable {
		private String account = null;
		private String Pwd = null;
		private boolean success = false;
		private String failReason = null;
		private User user = null;

		public MessagePwdAck(String stuNum,String money) {
			super(MSG_TYPE.MSG_Pwd_ACK);
			account = stuNum;
			Pwd = money;
		}

		public String getPwd() {
			return Pwd;
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


