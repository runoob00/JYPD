


	package messages;

	import info.PlaneTicket;
import info.User;

import java.io.Serializable;

import messages.Message.MSG_TYPE;

	public class MessageHangBanReq extends Message implements Serializable {
		private String FlightsNumber = null;
		private String Board = null;
		private String Start = null;
		private String End = null;

		public MessageHangBanReq(String flightsNumber  ,String board  ,String start ,String end) {
			super(MSG_TYPE.MSG_HangBan_REQ);
			FlightsNumber = flightsNumber;
			Board = board;
			Start=start;
			End=end;
		}



		public String getBoard() {
			return Board;
		}



		public String getStart() {
			return Start;
		}



		public String getEnd() {
			return End;
		}



		public String getFlightsNumber() {
			return FlightsNumber;
		}

	

		public Object getUser() {
			// TODO 自动生成的方法存根
			return null;
		}

	}



