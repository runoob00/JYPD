
	package messages;

	import info.User;

	import java.io.Serializable;

	public class MessagePlaneticket1Ack extends Message implements Serializable {
		private String FlightsNumber = null;
		private String SurplusSeat = null;
		private String Day = null;

		public MessagePlaneticket1Ack(String stuNum,String surplusSeat,String day) {
			super(MSG_TYPE.MSG_planeticket1_ACK);
			FlightsNumber = stuNum;
			SurplusSeat = surplusSeat;
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

		public Object getUser() {
			// TODO 自动生成的方法存根
			return null;
		}

	}



