
	package messages;

	import info.Flights;
import info.PlaneTicket;
import info.User;

import java.io.Serializable;

import messages.Message.MSG_TYPE;

	public class MessageFlightsAck extends Message implements Serializable {
		private String FlightsNumber = null;
		private String Day = null;
		private PlaneTicket planeTicket = null;

		public MessageFlightsAck(String stuNum,String money) {
			super(MSG_TYPE.MSG_Flights_ACK);
			FlightsNumber = stuNum;
			Day = money;
		}


		public String getFlightsNumber() {
			return FlightsNumber;
		}

		public String getDay() {
			return Day;
		}


		public PlaneTicket getPlaneTicket() {
			return planeTicket;
		}


		public void setPlaneTicket(PlaneTicket planeTicket) {
			this.planeTicket = planeTicket;
		}

		
	}



