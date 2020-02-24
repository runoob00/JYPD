package messages;

import java.io.Serializable;

public abstract class Message implements Serializable {
   public enum MSG_TYPE {
      MSG_UNKOWN, MSG_EXAMPLE,
      MSG_LOGIN_REQ, MSG_LOGIN_ACK, 
      MSG_LOGIN1_REQ, MSG_LOGIN1_ACK, 
      MSG_LOGIN2_REQ, MSG_LOGIN2_ACK, 
      MSG_LOGIN3_REQ, MSG_LOGIN3_ACK, 
      MSG_User_REQ, MSG_User_ACK, 
      MSG_User1_REQ, MSG_User1_ACK, 
      MSG_Money_REQ, MSG_Money_ACK, 
      MSG_Pwd_REQ, MSG_Pwd_ACK, 
      MSG_planeticket_REQ, MSG_planeticket_ACK, 
      MSG_planeticket1_REQ, MSG_planeticket1_ACK, 
      MSG_planeticket2_REQ, MSG_planeticket2_ACK, 
      MSG_planeticket3_REQ, MSG_planeticket3_ACK, 
      MSG_Chaxun_REQ,  MSG_Chaxun_ACK,
      MSG_Flights_REQ,  MSG_Flights_ACK,
      MSG_Flights1_REQ,  MSG_Flights1_ACK,
      MSG_Orderfrom_REQ,  MSG_Orderfrom_ACK,
      MSG_Orderfrom1_REQ,  MSG_Orderfrom1_ACK,
      MSG_Orderfrom2_REQ,  MSG_Orderfrom2_ACK,
      MSG_Orderfrom3_REQ,  MSG_Orderfrom3_ACK,
      MSG_Del_REQ, MSG_Del_ACK, 
      MSG_HangBan_REQ, MSG_HangBan_ACK, 
   }
   
   public MSG_TYPE msgType = MSG_TYPE.MSG_UNKOWN;
   
   public Message(MSG_TYPE mt) {
      msgType = mt;
   }
}