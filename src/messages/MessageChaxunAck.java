package messages;

import info.PlaneTicket;
import info.User;

import java.io.Serializable;
import java.util.Vector;


public class MessageChaxunAck extends Message implements Serializable {
	private String Starting=null;
	private String Ending=null;
	private String Day=null;
	private boolean success = false;
	private String failReason = null;
	private Vector<PlaneTicket> planeTicket = null;
	public MessageChaxunAck(String starting) {
		super(MSG_TYPE.MSG_Chaxun_ACK);
		Starting = starting;
	}
	public String getStarting() {
		return Starting;
	}

	public String getEnding() {
		return Ending;
	}

	public String getDay() {
		return Day;
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

	public void setPlaneTicket(Vector<PlaneTicket> planeTicket2) {
		this.planeTicket = planeTicket2;
	}

	public Vector<PlaneTicket> getPlaneTicket() {
		return planeTicket;
	}

}
