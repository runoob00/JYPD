package info;

import java.io.Serializable;

public class Flights implements Serializable{
			// �����
			public String FlightsNumber = new String();
			// �ǻ���
			public String Boarding = new String();
			// ������
			public String Starting = new String();
			// Ŀ�ĵ�
			public String Ending = new String();	
			
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
			public boolean verifyAccount(String FlightsNumber) {
				if (! this.FlightsNumber.equals(FlightsNumber)){
					return false; 
				}
				
				return true;
			}
	
}
