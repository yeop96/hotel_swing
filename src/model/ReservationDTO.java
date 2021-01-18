package model;

public class ReservationDTO {
	private String id;
	private String startday;
	private String endday;
	private String roomNum;
	private String adult;
	private String child;
	private int comeway;
	private int pay; //방의 가격
	private int salepay; //할인 가격 0
	private int totalpay;
	private String payway;
	
	//setter 
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setAdult(String adult) {
		this.adult = adult;
	}
	public void setChild(String child) {
		this.child = child;
	}
	public void setStartday(String startday) {
		this.startday = startday;
	}
	public void setEndday(String endday) {
		this.endday = endday;
	}

	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}
	public void setComeway(int comeway) {
		this.comeway = comeway;
	}
	public void setPay(int pay) {
		this.pay = pay;
	}
	public void setSalepay(int salepay) {
		this.salepay = salepay;
	}
	public void setTotalpay(int totalpay) {
		this.totalpay = totalpay;
	}
	public void setPayway(String payway) {
		this.payway = payway;
	}
	
	//getter
	
	public String getId() {
		return id;
	}
	public String getAdult() {
		return adult;
	}
	public String getChild() {
		return child;
	}
	public String getStartday() {
		return startday;
	}
	public String getEndday() {
		return endday;
	}

	public String getRoomNum() {
		return roomNum;
	}
	public int getComeway() {
		return comeway;
	}
	public int getPay() {
		return pay;
	}
	public int getSalepay() {
		return salepay;
	}
	public int getTotalpay() {
		return totalpay;
	}
	public String getPayway() {
		return payway;
	}
	
	 @Override
	   public String toString() {
	      return id;
	   }

}
