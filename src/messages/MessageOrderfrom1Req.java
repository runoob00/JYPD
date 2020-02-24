package messages;

import java.io.Serializable;

import messages.Message.MSG_TYPE;

public class MessageOrderfrom1Req extends Message implements Serializable{
	private String Name = null;
	public MessageOrderfrom1Req(String Name) {
		super(MSG_TYPE.MSG_Orderfrom1_REQ);
		this.Name = Name;
		
	}
	public String getName() {
		return Name;
	}

}
