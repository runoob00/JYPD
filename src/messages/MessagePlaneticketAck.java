
	package messages;

	import info.User;

	import java.io.Serializable;

	public class MessagePlaneticketAck extends Message implements Serializable {
		private String FlightsNumber = null;
		private String SurplusGrade = null;
		private String Day = null;

		public MessagePlaneticketAck(String stuNum,String surplusGrade,String day) {
			super(MSG_TYPE.MSG_planeticket_ACK);
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

		public void setFlightsNumber(String flightsNumber) {
			FlightsNumber = flightsNumber;
		}

		public void setSurplusGrade(String surplusGrade) {
			SurplusGrade = surplusGrade;
		}

		public void setDay(String day) {
			Day = day;
		}

		public String getDay() {
			return Day;
		}

		public Object getUser() {
			// TODO 自动生成的方法存根
			return null;
		}

	}



