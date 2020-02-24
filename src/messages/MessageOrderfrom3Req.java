package messages;

import java.io.Serializable;

import info.OrderForm;


public class MessageOrderfrom3Req extends Message implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2132114345139808929L;
	private String FlightsNumber = null;
	private String Day = null;
	private String Name = null;
	private OrderForm orderForm = null;
	public MessageOrderfrom3Req(String flightsNumber,String day,String name) {
		super(MSG_TYPE.MSG_Orderfrom3_REQ);
		FlightsNumber = flightsNumber;
		Day = day;
		Name = name;
	}
	public String getFlightsNumber() {
		return FlightsNumber;
	}

	public String getDay() {
		return Day;
	}

	public String getName() {
		return Name;
	}
	public OrderForm getOrderForm() {
		return orderForm;
	}
	public void setOrderForm(OrderForm orderForm) {
		this.orderForm = orderForm;
	}


}
