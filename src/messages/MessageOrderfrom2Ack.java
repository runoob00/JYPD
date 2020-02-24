package messages;

import java.io.Serializable;


public class MessageOrderfrom2Ack extends Message implements Serializable{
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
			public MessageOrderfrom2Ack(String a1,String a2,String a3,String a4,String a5,String a6,String a7,String a8,String a9,String a10,String a11,String a12) {
				super(MSG_TYPE.MSG_Orderfrom2_ACK);
				Account  = a1;
				Name = a2;
				ID = a3;
				FlightsNumber = a4;
				Boarding = a5;
				Starting = a6;
				Ending = a7;
				Day = a8;
				DepartureTime = a9;
				ArriverTime =a10;
				TicketPrice = a11;
				Grade = a12;
			}
	        public String getAccount() {
				return Account;
			}
			public String getName() {
				return Name;
			}
			public String getID() {
				return ID;
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
			public String getGrade() {
				return Grade;
			}
			public Object getOrderform() {
				// TODO 自动生成的方法存根
				return null;
			}
			

}
