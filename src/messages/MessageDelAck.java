

	package messages;

	import info.PlaneTicket;
import info.User;

import java.io.Serializable;

import messages.Message.MSG_TYPE;

	public class MessageDelAck extends Message implements Serializable {
		private String FlightsNumber = null;
		private String Day = null;
		private String Name = null;

		public MessageDelAck(String stuNum,String money,String name) {
			super(MSG_TYPE.MSG_Del_ACK);
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


		public Object getUser() {
			// TODO 自动生成的方法存根
			return null;
		}

	}



