

	package messages;

	import info.PlaneTicket;
import info.User;

import java.io.Serializable;

import messages.Message.MSG_TYPE;

	public class MessageDelReq extends Message implements Serializable {
		private String FlightsNumber = null;
		private String Day = null;
		private String Name = null;

		public MessageDelReq(String stuNum,String money,String name) {
			super(MSG_TYPE.MSG_Del_REQ);
			FlightsNumber = stuNum;
			Day = money;
			Name=name;
		}


		public String getName() {
			return Name;
		}


		public String getFlightsNumber() {
			return FlightsNumber;
		}

		public String getDay() {
			return Day;
		}

	}



