package info;

import java.io.Serializable;

public class PlaneTicket implements Serializable{

	   private Flights flights = new Flights(); 
	// �����
	   private String FlightsNumber = new String();
				// �ǻ���
	   private String Boarding = new String();
				// ������
	   private String Starting = new String();
				// Ŀ�ĵ�
	   private String Ending = new String();
	    // ����
		private String Day = new String();
		// ����ʱ��
		private String DepartureTime = new String();
		// �ִ�ʱ��
		private String ArriverTime = new String();
		// Ʊ��
		private String TicketPrice = new String();
		// ͷ�Ȳ�λ����
		private String Seat = new String();
		//���ò�λ����
		private String Grade = new String();
		// ͷ�Ȳ�λʣ������
		private String SurplusSeat  = new String();
		//���ò�λʣ������
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
