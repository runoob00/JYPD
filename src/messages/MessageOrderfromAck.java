package messages;

import java.io.Serializable;
import java.util.Vector;

import info.OrderForm;


public class MessageOrderfromAck extends Message implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2132114345139808929L;
	private String account = null;
	private boolean success = false;
	private String failReason = null;
	private Vector<OrderForm> orderForm = null;
	public MessageOrderfromAck(String stuNum) {
		super(MSG_TYPE.MSG_Orderfrom_ACK);
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

	public void setOrderForm(Vector<OrderForm> orderform2) {
		this.orderForm = orderform2;
	}

	public Vector<OrderForm> getOrderForm() {
		return orderForm;
	}
}
