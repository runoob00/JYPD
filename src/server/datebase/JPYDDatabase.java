package server.datebase;

import info.Flights;
import info.OrderForm;
import info.PlaneTicket;
import info.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Vector;

public class JPYDDatabase {
	private static Connection conn = null;

	public JPYDDatabase() {

	}
	// 取得数据库连接，本服务器程序仅使用这一个连接实例
	private static Connection getConnection() {
		if (conn != null) {
			return conn;
		}

		String driver_MySQL = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/JPYD?CharactorEncoding=utf8";
		String account_MySQL = "root";
		String password_MySQL = "root";

		try {
			Class.forName(driver_MySQL);
			conn = DriverManager.getConnection(url, account_MySQL,
					password_MySQL);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("创建数据库连接失败！");
		}
		return conn;
	}

	private static String toSqlString(String str) {
		return new String(" '" + str + "' ");
	}

	// account is stuNum


	public static User userQquery(String account) {
		User user = null;
		String sql = "select * from userlist where Account  = " + toSqlString(account);
		try {
			Statement stmt = getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next() == true) {
				user = new User();
				user.setAccount(rs.getString(1));
				user.setPwd(rs.getString(2));
				user.setName(rs.getString(3));
				user.setID(rs.getString(4));
				user.setMoney(rs.getString(5));
				user.setQuestion(rs.getString(6));
				user.setAnswer(rs.getString(7));
			}
		} catch (SQLException sqle) {
			System.out.println("查询数据出现异常: " + sqle.getMessage());
		}

		return user;
	}
	public static User userQquery1(String account) {
		User user = null;
		String sql = "select * from userlist1 where Account  = " + toSqlString(account);
		try {
			Statement stmt = getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next() == true) {
				user = new User();
				user.setAccount(rs.getString(1));
				user.setPwd(rs.getString(2));
				user.setName(rs.getString(3));
				user.setID(rs.getString(4));
				user.setMoney(rs.getString(5));
				user.setQuestion(rs.getString(6));
				user.setAnswer(rs.getString(7));
			}
		} catch (SQLException sqle) {
			System.out.println("查询数据出现异常: " + sqle.getMessage());
		}

		return user;
	}
	public static  void Money(String Account,String Money) {
		String sql = "update userlist set Money ='"+Money+"'where Account="+Account;
		try {
			Statement stmt = getConnection().createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException sqle) {
			System.out.println("查询数据出现异常: " + sqle.getMessage());
		}
	}
	public static  void Pwd(String Account,String Pwd) {
		String sql ="update userlist set Pwd ='"+Pwd+"'where Account="+Account;
		try {
			Statement stmt = getConnection().createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException sqle) {
			System.out.println("查询数据出现异常: " + sqle.getMessage());
		}
	}
	public static  void User(String Account,String Pwd,String Name  ,String ID  ,String Question ,String Answer ) {
		String sql ="insert into userlist values('"+Account  +"','"+Pwd  +"','"+Name  
				+"','"+ID+"','"+"0"+"','"+Question +"','"+Answer +"')";
		try {
			Statement stmt = getConnection().createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException sqle) {
			System.out.println("查询数据出现异常: " + sqle.getMessage());
		}
	}
	public static  void User1(String Account,String Pwd,String Name  ,String ID  ,String Question ,String Answer ) {
		String sql ="insert into userlist1 values('"+Account  +"','"+Pwd  +"','"+Name  
				+"','"+ID+"','"+"0"+"','"+Question +"','"+Answer +"')";
		try {
			Statement stmt = getConnection().createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException sqle) {
			System.out.println("查询数据出现异常: " + sqle.getMessage());
		}
	}
	
	public static Flights flightsQquery(String FlightsNumber) {
		Flights flights = null;
		String sql = "select * from Flights where FlightsNumber  = " + toSqlString(FlightsNumber);
		try {
			Statement stmt = getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next() == true) {
				flights = new Flights();
				flights.setFlightsNumber(rs.getString(1));
				flights.setBoarding(rs.getString(2));
				flights.setStarting(rs.getString(3));
				flights.setEnding(rs.getString(4));
				
			}
		} catch (SQLException sqle) {
			System.out.println("查询数据出现异常: " + sqle.getMessage());
		}

		return flights;
	}
	
	public static Vector<PlaneTicket> PlaneTicketQquery() {
		Vector<PlaneTicket> result = new Vector<PlaneTicket>();
		PlaneTicket planeTicket = null;
		String sql = "select * from Flights";
		Calendar.getInstance();
		try {
			Statement stmt = getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next() == true)  {
				planeTicket = new PlaneTicket(null);
				planeTicket.setFlightsNumber(rs.getString(1));
				planeTicket.setBoarding(rs.getString(2));
				planeTicket.setStarting(rs.getString(3));
				planeTicket.setEnding(rs.getString(4));
				result.add(planeTicket);
			}
		} catch (SQLException sqle) {
			System.out.println("[shuttleQquery]查询数据出现异常: " + sqle.getMessage());
		}

		return result;
	}
	public static PlaneTicket planeticketQquery(String FlightsNumber,String Day) {
		PlaneTicket planeticke = null;
		String sql = "select * from planeticket  where FlightsNumber  = " + toSqlString(FlightsNumber)
				+"and Day ="+toSqlString(Day);
		try {
			Statement stmt = getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next() == true) {
				planeticke = new PlaneTicket(null);
				planeticke.setFlightsNumber(rs.getString(1));
				planeticke.setBoarding(rs.getString(2));
				planeticke.setStarting(rs.getString(3));
				planeticke.setEnding(rs.getString(4));
				planeticke.setDay (rs.getString(5));
				planeticke.setDepartureTime(rs.getString(6));
				planeticke.setArriverTime(rs.getString(7));
				planeticke.setTicketPrice(rs.getString(8));
				planeticke.setSeat(rs.getString(9));
				planeticke.setGrade(rs.getString(10));
				planeticke.setSurplusSeat (rs.getString(11));
				planeticke.setSurplusGrade(rs.getString(12));
				
			}
		} catch (SQLException sqle) {
			System.out.println("查询数据出现异常: " + sqle.getMessage());
		}

		return planeticke;
	}
	
	
	public static Vector<PlaneTicket> PlaneTicketQquery1(String Starting,String Ending,String Day) {
		Vector<PlaneTicket> result = new Vector<PlaneTicket>();
		PlaneTicket planeTicket = null;
		String sql = "select * from planeticket  where Start  = " + toSqlString(Starting)+"and End ="+toSqlString(Ending)
				+"and Day ="+toSqlString(Day);
		Calendar.getInstance();
		try {
			Statement stmt = getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next() == true)  {
				planeTicket = new PlaneTicket(null);
				planeTicket.setFlightsNumber(rs.getString(1));
				planeTicket.setBoarding(rs.getString(2));
				planeTicket.setStarting(rs.getString(3));
				planeTicket.setEnding(rs.getString(4));
				planeTicket.setDay (rs.getString(5));
				planeTicket.setDepartureTime(rs.getString(6));
				planeTicket.setArriverTime(rs.getString(7));
				planeTicket.setTicketPrice(rs.getString(8));
				planeTicket.setSeat(rs.getString(9));
				planeTicket.setGrade(rs.getString(10));
				planeTicket.setSurplusSeat (rs.getString(11));
				planeTicket.setSurplusGrade(rs.getString(12));
				result.add(planeTicket);
			}
		} catch (SQLException sqle) {
			System.out.println("[shuttleQquery]查询数据出现异常: " + sqle.getMessage());
		}

		return result;
	}
	public static  void planeticket(String FlightsNumber,String SurplusGrade,String Day) {
		String sql = "update planeticket  set SurplusGrade  ='"+SurplusGrade+"'where FlightsNumber='"+
				FlightsNumber+"'and Day='"+Day+"'";
		try {
			Statement stmt = getConnection().createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException sqle) {
			System.out.println("查询数据出现异常: " + sqle.getMessage());
		}
	}
	public static  void planeticket1(String FlightsNumber,String SurplusSeat,String Day) {
		String sql = "update planeticket  set SurplusSeat  ='"+SurplusSeat+"'where FlightsNumber='"+
				FlightsNumber+"'and Day='"+Day+"'";
		try {
			Statement stmt = getConnection().createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException sqle) {
			System.out.println("查询数据出现异常: " + sqle.getMessage());
		}
	}
	public static OrderForm orderformQquery(String FlightsNumber,String Day,String Name) {
		OrderForm orderform = null;
		String sql = "select * from orderform  where FlightsNumber  = " + toSqlString(FlightsNumber)
				+"and Day ="+toSqlString(Day)+"and Name ="+toSqlString(Name);
		try {
			Statement stmt = getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next() == true) {
				orderform = new OrderForm(null);
				orderform.setAccount(rs.getString(1));
				orderform.setName(rs.getString(2));
				orderform.setID(rs.getString(3));			
				orderform.setFlightsNumber(rs.getString(4));
				orderform.setBoarding(rs.getString(5));
				orderform.setStarting(rs.getString(6));
				orderform.setEnding(rs.getString(7));
				orderform.setDay (rs.getString(8));
				orderform.setDepartureTime(rs.getString(9));
				orderform.setArriverTime(rs.getString(10));
				orderform.setTicketPrice(rs.getString(11));
				orderform.setGrade(rs.getString(12));
				orderform.setState(rs.getString(13));
				
			}
		} catch (SQLException sqle) {
			System.out.println("查询数据出现异常: " + sqle.getMessage());
		}

		return orderform;
	}
	public static Vector<OrderForm> OrderFormtQquery1(String account) {
		Vector<OrderForm> result = new Vector<OrderForm>();
		OrderForm orderform = null;
		String sql = "select * from orderform  where Account    = " + toSqlString(account);
		Calendar.getInstance();
		try {
			Statement stmt = getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next() == true)  {
				orderform = new OrderForm(null);
				orderform.setAccount(rs.getString(1));
				orderform.setName(rs.getString(2));
				orderform.setID(rs.getString(3));			
				orderform.setFlightsNumber(rs.getString(4));
				orderform.setBoarding(rs.getString(5));
				orderform.setStarting(rs.getString(6));
				orderform.setEnding(rs.getString(7));
				orderform.setDay (rs.getString(8));
				orderform.setDepartureTime(rs.getString(9));
				orderform.setArriverTime(rs.getString(10));
				orderform.setTicketPrice(rs.getString(11));
				orderform.setGrade(rs.getString(12));
				orderform.setState(rs.getString(13));
				result.add(orderform);
			}
		} catch (SQLException sqle) {
			System.out.println("[shuttleQquery]查询数据出现异常: " + sqle.getMessage());
		}

		return result;
	}
	public static Vector<OrderForm> OrderFormtQquery2(String name) {
		Vector<OrderForm> result = new Vector<OrderForm>();
		OrderForm orderform = null;
		String sql = "select * from orderform  where Name    = " + toSqlString(name);
		Calendar.getInstance();
		try {
			Statement stmt = getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next() == true)  {
				orderform = new OrderForm(null);
				orderform.setAccount(rs.getString(1));
				orderform.setName(rs.getString(2));
				orderform.setID(rs.getString(3));			
				orderform.setFlightsNumber(rs.getString(4));
				orderform.setBoarding(rs.getString(5));
				orderform.setStarting(rs.getString(6));
				orderform.setEnding(rs.getString(7));
				orderform.setDay (rs.getString(8));
				orderform.setDepartureTime(rs.getString(9));
				orderform.setArriverTime(rs.getString(10));
				orderform.setTicketPrice(rs.getString(11));
				orderform.setGrade(rs.getString(12));
				orderform.setState(rs.getString(13));
				result.add(orderform);
			}
		} catch (SQLException sqle) {
			System.out.println("[shuttleQquery]查询数据出现异常: " + sqle.getMessage());
		}

		return result;
	}
	public static  void OrderForm(String Account,String Name,String ID,String FlightsNumber  ,String Board  ,String Start  ,
			String End ,String Day ,String DepartureTime  ,String ArriverTime ,String TicketPrice ,String Grade) {
		String sql ="insert into orderform values('"+Account+"','"+Name+"','"+ID+"','"+FlightsNumber+"','"+Board+"','"+Start+"','"+
			End +"','"+Day +"','"+DepartureTime +"','"+ArriverTime +"','"+TicketPrice +"','"+Grade +"','未出行')";
		try {
			Statement stmt = getConnection().createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException sqle) {
			System.out.println("查询数据出现异常: " + sqle.getMessage());
		}
	}
	public static void OrderForm (String FlightsNumber,String Day,String Name) {
		String sql = "DELETE FROM orderform WHERE Name='"+Name+"'and FlightsNumber='"+FlightsNumber+
				"'and Day ='"+Day +"'";
		try {
			Statement stmt = getConnection().createStatement();
			stmt.executeUpdate(sql);
			
		} catch (SQLException sqle) {
			System.out.println("查询数据出现异常: " + sqle.getMessage());
		}

	}

	public static void HangBan(String flightsNumber, String board,
			String start, String end) {
		String sql ="insert into flights  values("+"'"+flightsNumber+"','"+board
				+"','"+start+"','"+end+"')";
		try {
			Statement stmt = getConnection().createStatement();
			stmt.executeUpdate(sql);
			
		} catch (SQLException sqle) {
			System.out.println("查询数据出现异常: " + sqle.getMessage());
		}
		
	}
	public static void Planeticket(String FlightsNumber  , String Board  ,String Start  , String End,String Day   , String DepartureTime   ,
			String ArriverTime   , String TicketPrice ,String Seat   , String Grade   ,String SurplusSeat   , String SurplusGrade  ) {
		String sql =("insert into planeticket  values("+"'"+FlightsNumber  +"','"+ Board+"','"+Start
				+"','"+End+"','"+Day+"','"+DepartureTime+"','"+ArriverTime+"','"+TicketPrice+"','"+Seat+"','"+Grade
				+"','"+SurplusSeat+"','"+SurplusGrade+"')");
		try {
			Statement stmt = getConnection().createStatement();
			stmt.executeUpdate(sql);
			
		} catch (SQLException sqle) {
			System.out.println("查询数据出现异常: " + sqle.getMessage());
		}
		
	}
}
