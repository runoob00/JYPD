
	package messages;
	import info.PlaneTicket;
import info.User;

import java.io.Serializable;
import java.util.Vector;

	public class MessagePlaneticket3Ack extends Message implements Serializable {
		   private String FlightsNumber =null;
		   private String Boarding = null;
		   private String Starting =null;
		   private String Ending = null;
			private String Day =null;
			private String DepartureTime = null;
			private String ArriverTime = null;
			private String TicketPrice = null;
			private String Seat = null;
			private String Grade =null;
			private String SurplusSeat  = null;
			private String SurplusGrade =null;
		public MessagePlaneticket3Ack(String flightsNumber  , String board  ,String start  , String end,String day   , String departureTime   ,
				String arriverTime   , String ticketPrice ,String seat   , String grade   ,String surplusSeat   , String surplusGrade  ) {
			super(MSG_TYPE.MSG_planeticket3_ACK);
			 FlightsNumber =flightsNumber;
			 Boarding = board;
			 Starting =start;
			 Ending = end;
			Day =day;
			DepartureTime = departureTime;
			ArriverTime = arriverTime;
			TicketPrice = ticketPrice;
			Seat = seat;
			Grade =grade;
			SurplusSeat  = surplusSeat;
			SurplusGrade =surplusGrade;
		}
		public String getFlightsNumber() {
			return FlightsNumber;
		}
		public String getBoarding() {
			return Boarding;
		}
		public String getStarting() {
			return Starting;
		}
		public String getEnding() {
			return Ending;
		}
		public String getDay() {
			return Day;
		}
		public String getDepartureTime() {
			return DepartureTime;
		}
		public String getArriverTime() {
			return ArriverTime;
		}
		public String getTicketPrice() {
			return TicketPrice;
		}
		public String getSeat() {
			return Seat;
		}
		public String getGrade() {
			return Grade;
		}
		public String getSurplusSeat() {
			return SurplusSeat;
		}
		public String getSurplusGrade() {
			return SurplusGrade;
		}
		public Object getUser() {
			// TODO 自动生成的方法存根
			return null;
		}
	}




