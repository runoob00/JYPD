
	package messages;

	import info.User;

	import java.io.Serializable;

	public class MessagePlaneticketReq extends Message implements Serializable {
		private String FlightsNumber = null;
		private String SurplusGrade = null;
		private String Day = null;

		public MessagePlaneticketReq(String stuNum,String surplusGrade,String day) {
			super(MSG_TYPE.MSG_planeticket_REQ);
			FlightsNumber = stuNum;
			SurplusGrade = surplusGrade;
			Day = day;
			
		}

		public String getFlightsNumber() {
			return FlightsNumber;
		}

		public String getSurplusGrade() {
			return SurplusGrade;
		}

		public String getDay() {
			return Day;
		}

	}



