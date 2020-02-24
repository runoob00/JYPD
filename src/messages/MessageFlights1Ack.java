
	package messages;

	import info.Flights;
import info.PlaneTicket;
import info.User;

import java.io.Serializable;

import messages.Message.MSG_TYPE;

	public class MessageFlights1Ack extends Message implements Serializable {
		private String FlightsNumber = null;
		private Flights flights = null;

		public MessageFlights1Ack(String stuNum) {
			super(MSG_TYPE.MSG_Flights1_ACK);
			FlightsNumber = stuNum;
		}


		public String getFlightsNumber() {
			return FlightsNumber;
		}


		public Flights getFlights() {
			return flights;
		}


		public void setFlights(Flights flights) {
			this.flights = flights;
		}

	
	}



