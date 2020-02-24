
	package messages;

	import info.Flights;
import info.PlaneTicket;

import java.io.Serializable;

	public class MessageFlights1Req extends Message implements Serializable {
		private String FlightsNumber = null;
		private String Day = null;
		private Flights flights = null;

		public MessageFlights1Req(String stuNum) {
			super(MSG_TYPE.MSG_Flights1_REQ);
			FlightsNumber = stuNum;
		}

		public String getFlightsNumber() {
			return FlightsNumber;
		}

		public String getDay() {
			return Day;
		}
		public void setFlights(Flights flights) {
			this.flights = flights;
		}

		public Flights getFlights() {
			return flights;
		}
	}




