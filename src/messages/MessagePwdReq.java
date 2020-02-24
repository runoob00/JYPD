
	package messages;

	import info.User;

	import java.io.Serializable;

	public class MessagePwdReq extends Message implements Serializable {
		private String account = null;
		private String Pwd = null;

		public MessagePwdReq(String stuNum,String money) {
			super(MSG_TYPE.MSG_Pwd_REQ);
			account = stuNum;
			Pwd = money;
		}

		public String getPwd() {
			return Pwd;
		}

		public String getAccount() {
			return account;
		}
	}


