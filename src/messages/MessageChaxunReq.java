package messages;

import java.io.Serializable;


public class MessageChaxunReq extends Message implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2781553869112729356L;
	private String Starting=null;
	private String Ending=null;
	private String Day=null;
	public MessageChaxunReq(String Starting, String Ending, String Day) {
		super(MSG_TYPE.MSG_Chaxun_REQ);
		this.Starting = Starting;
		this.Ending = Ending;
		this.Day = Day;
		
	}
	public String getStarting() {
		return Starting;
	}
	public void setStarting(String starting) {
		Starting = starting;
	}
	public String getEnding() {
		return Ending;
	}
	public void setEnding(String ending) {
		Ending = ending;
	}
	public String getDay() {
		return Day;
	}
	public void setDay(String day) {
		Day = day;
	}
}
