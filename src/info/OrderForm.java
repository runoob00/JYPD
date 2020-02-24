package info;

import java.io.Serializable;

public class OrderForm implements Serializable{
	    // 账号
		private String Account  = new String();
		// 姓名
		private String Name = new String();
		// 身份证
		private String ID = new String();
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
		//舱位
		private String Grade = new String();
		//状态
		private String state = new String();
		public OrderForm(){
			
		}
		public OrderForm(String Name) {
			this.Name=Name;
		}
		public String getAccount() {
			return Account;
		}
		public void setAccount(String account) {
			Account = account;
		}
		public String getName() {
			return Name;
		}
		public void setName(String name) {
			Name = name;
		}
		public String getID() {
			return ID;
		}
		public void setID(String iD) {
			ID = iD;
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
		public String getGrade() {
			return Grade;
		}
		public void setGrade(String grade) {
			Grade = grade;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
}
