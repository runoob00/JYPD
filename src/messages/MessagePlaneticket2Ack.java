
	package messages;

	import info.PlaneTicket;
import info.User;

import java.io.Serializable;
import java.util.Vector;

	public class MessagePlaneticket2Ack extends Message implements Serializable {
		private String FlightsNumber = null;
		private String SurplusSeat = null;
		private String Day = null;
		private Vector<PlaneTicket> planeTicket=null;

		public Vector<PlaneTicket> getPlaneTicket() {
			return planeTicket;
		}

		public void setPlaneTicket(Vector<PlaneTicket> planeTicket2) {
			this.planeTicket = planeTicket2;
		}

		public MessagePlaneticket2Ack() {
			super(MSG_TYPE.MSG_planeticket2_ACK);
			
		}

		public String getFlightsNumber() {
			return FlightsNumber;
		}

		public String getSurplusSeat() {
			return SurplusSeat;
		}
		public String getDay() {
			return Day;
		}

		public Object getUser() {
			// TODO 自动生成的方法存根
			return null;
		}

	}




