
	package messages;

	import info.User;

	import java.io.Serializable;

	public class MessageMoneyReq extends Message implements Serializable {
		private String account = null;
		private String Money = null;

		public MessageMoneyReq(String stuNum,String money) {
			super(MSG_TYPE.MSG_Money_REQ);
			account = stuNum;
			Money = money;
		}

		public String getMoney() {
			return Money;
		}

		public String getAccount() {
			return account;
		}


	
	}


