

	package messages;

	import info.User;

import java.io.Serializable;

	public class MessageUserAck extends Message implements Serializable {
		private String Account = null;
		private String Pwd = null;
		private String Name = null;
		private String ID = null;
		private String Question = null;
		private String Answer = null;
		private boolean success = false;
		private String failReason = null;
		private User user = null;

		public MessageUserAck(String account,String pwd,String name  ,String iD  ,String question ,String answer ) {
			super(MSG_TYPE.MSG_User_ACK);
			Account=account;
			Pwd=pwd;
			Name=name;
			ID=iD;
			Question=question;
			Answer=answer;
			
			
		}

	

		public String getAccount() {
			return Account;
		}



		public String getPwd() {
			return Pwd;
		}



		public String getName() {
			return Name;
		}



		public String getID() {
			return ID;
		}



		public String getQuestion() {
			return Question;
		}



		public String getAnswer() {
			return Answer;
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



