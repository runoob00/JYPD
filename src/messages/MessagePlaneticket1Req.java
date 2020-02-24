
	package messages;

	import info.User;

	import java.io.Serializable;

	public class MessagePlaneticket1Req extends Message implements Serializable {
		private String FlightsNumber = null;
		private String SurplusSeat = null;
		private String Day = null;

		public MessagePlaneticket1Req(String stuNum,String surplusGrade,String day) {
			super(MSG_TYPE.MSG_planeticket1_REQ);
			FlightsNumber = stuNum;
			SurplusSeat = surplusGrade;
			Day = day;
			
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



