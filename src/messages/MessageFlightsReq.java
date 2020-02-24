
	package messages;

	import info.PlaneTicket;
import java.io.Serializable;

	public class MessageFlightsReq extends Message implements Serializable {
		private String FlightsNumber = null;
		private String Day = null;
		private PlaneTicket planeTicket = null;

		public MessageFlightsReq(String stuNum,String money) {
			super(MSG_TYPE.MSG_Flights_REQ);
			FlightsNumber = stuNum;
			Day = money;
		}

		public String getFlightsNumber() {
			return FlightsNumber;
		}

		public String getDay() {
			return Day;
		}
		public void setPlaneTicket(PlaneTicket planeTicket) {
			this.planeTicket = planeTicket;
		}

		public PlaneTicket getPlaneTicket() {
			return planeTicket;
		}
	}




