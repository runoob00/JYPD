

	package messages;

	import info.User;

	import java.io.Serializable;

	public class MessagePlaneticket2Req extends Message implements Serializable {
		private String FlightsNumber = null;
		private String SurplusSeat = null;
		private String Day = null;

		public MessagePlaneticket2Req() {
			super(MSG_TYPE.MSG_planeticket2_REQ);
			
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

	}



