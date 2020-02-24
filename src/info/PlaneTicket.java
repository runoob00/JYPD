package info;

import java.io.Serializable;

public class PlaneTicket implements Serializable{

	   private Flights flights = new Flights(); 
	// 航班号
	   private String FlightsNumber = new String();
				// 登机口
	   private String Boarding = new String();
				// 出发地
	   private String Starting = new String();
				// 目的地
	   private String Ending = new String();
	    // 日期
		private String Day = new String();
		// 出发时间
		private String DepartureTime = new String();
		// 抵达时间
		private String ArriverTime = new String();
		// 票价
		private String TicketPrice = new String();
		// 头等舱位数量
		private String Seat = new String();
		//经济舱位数量
		private String Grade = new String();
		// 头等舱位剩余数量
		private String SurplusSeat  = new String();
		//经济舱位剩余数量
		private String SurplusGrade = new String();
		
		public PlaneTicket(String FlightsNumber) {
			this.FlightsNumber = FlightsNumber;
		}
		public void setFlights(String FlightsNumber,String Boarding,String Starting,String Ending) {
			if(this.flights==null){
				this.flights = new Flights();
			}
			this.flights.FlightsNumber = FlightsNumber;
			this.flights.Boarding = Boarding;
			this.flights.Starting = Starting;
			this.flights.Ending = Ending;
		}
		
		public Flights getFlights() {
			return flights;
		}
		public String getFlightsNumber() {
			return FlightsNumber;
		}
		public void setFlightsNumber(String flightsNumber) {
			FlightsNumber = flightsNumber;
		}
		public String getBoarding() {
			return Boarding;
		}
		public void setBoarding(String boarding) {
			Boarding = boarding;
		}
		public String getStarting() {
			return Starting;
		}
		public void setStarting(String starting) {
			Starting = starting;
		}
		public String getEnding() {
			return Ending;
		}
		public void setEnding(String ending) {
			Ending = ending;
		}
		public String getDay() {
			return Day;
		}
		public void setDay(String day) {
			Day = day;
		}
		public void setFlights(Flights flights,String FlightsNumber,String Boarding,
				String Starting ,String Ending ) {
			this.flights = flights;
			this.flights.FlightsNumber=FlightsNumber;
			this.flights.Boarding=Boarding;
			this.flights.Starting=Starting;
			this.flights.Ending=Ending;
		}
		public String getDepartureTime() {
			return DepartureTime;
		}
		public void setDepartureTime(String departureTime) {
			DepartureTime = departureTime;
		}
		public String getArriverTime() {
			return ArriverTime;
		}
		public void setArriverTime(String arriverTime) {
			ArriverTime = arriverTime;
		}
		public String getTicketPrice() {
			return TicketPrice;
		}
		public void setTicketPrice(String ticketPrice) {
			TicketPrice = ticketPrice;
		}
		public String getSeat() {
			return Seat;
		}
		public void setSeat(String seat) {
			Seat = seat;
		}
		public String getGrade() {
			return Grade;
		}
		public void setGrade(String grade) {
			Grade = grade;
		}
		public String getSurplusSeat() {
			return SurplusSeat;
		}
		public void setSurplusSeat(String surplusSeat) {
			SurplusSeat = surplusSeat;
		}
		public String getSurplusGrade() {
			return SurplusGrade;
		}
		public void setSurplusGrade(String surplusGrade) {
			SurplusGrade = surplusGrade;
		}
		public  boolean verifyAccount(String Day1) {
			if (! this.Day.equals(Day1)){
				return false; 
			}
			
			return true;
		}
}
