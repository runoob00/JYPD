
	package messages;

	import info.User;

import java.io.Serializable;

	public class MessageUserReq extends Message implements Serializable {
		private String Account = null;
		private String Pwd = null;
		private String Name = null;
		private String ID = null;
		private String Question = null;
		private String Answer = null;
		public MessageUserReq(String account,String pwd,String name  ,String iD  ,String question ,String answer ) {
			super(MSG_TYPE.MSG_User_REQ);
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

		

	
	}


