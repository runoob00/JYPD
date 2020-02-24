package info;

import java.io.Serializable;

public class User implements Serializable {
	// 账号
	private String Account  = new String();
	// 密码
	private String Pwd = new String();
	// 姓名
	private String Name = new String();
	// 身份证
	private String ID = new String();
	// 余额
	private String Money = new String();
	// 问题
	public String Question= new String();
	// 答案
	public String Answer= new String();
	public User(){
		
	}
	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getAccount() {
		return Account;
	}
	public void setAccount(String account) {
		Account = account;
	}
	public String getPwd() {
		return Pwd;
	}

	public void setPwd(String pwd) {
		Pwd = pwd;
	}
	
	public String getMoney() {
		return Money;
	}
	public void setMoney(String money) {
		Money = money;
	}
	public String getQuestion() {
		return Question;
	}
	public void setQuestion(String question) {
		Question = question;
	}
	public String getAnswer() {
		return Answer;
	}
	public void setAnswer(String answer) {
		Answer = answer;
	}
	public boolean verifyPwd(String pwd) {
		if (! this.Pwd.equals(pwd)){
			return false; 
		}
		
		return true;
	}
	public boolean verifyAccount(String account) {
		if (! this.Account.equals(account)){
			return false; 
		}
		
		return true;
	}

	

}

