package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

import model.ReservationDAO;
import model.ReservationDTO;
import view.RoomChoice;
import view.RoomReservation;

public class RoomReservationController implements ActionListener  {

	RoomReservation roomReservation;
	
	public RoomReservationController(RoomReservation roomReservation){
		this.roomReservation = roomReservation;
	}
	
	public int calc(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, 1);
		int lastD = cal.getActualMaximum(Calendar.DATE);
		return lastD;
	}

	public void yearmonthday() {
		int toyear = Integer.parseInt(roomReservation.year);
		int tomonth = Integer.parseInt(roomReservation.month);
		int today = Integer.parseInt(roomReservation.day);
		// 년도
		roomReservation.syeararray = new ArrayList<String>();
		roomReservation.smontharray = new ArrayList<String>();
		roomReservation.sdayarray = new ArrayList<String>();
		for (int i = toyear; i <= toyear + 5; i++) {
			roomReservation.syeararray.add(String.valueOf(i));
		}
		for (int i = tomonth; i <= 12; i++) {
			roomReservation.smontharray.add(String.valueOf(i));
		}
		for (int i = today; i <= calc(toyear, tomonth); i++) {
			roomReservation.sdayarray.add(String.valueOf(i));
		}
		
		roomReservation.smonthStr = roomReservation.smontharray.toArray(new String[roomReservation.smontharray.size()]);
		roomReservation.sdayStr = roomReservation.sdayarray.toArray(new String[roomReservation.sdayarray.size()]);
	}
	public void eyearmonthday() {
		//Calendar oCalendar = Calendar.getInstance(); // 현재 날짜/시간 등의 각종 정보 얻기
		// 현재 날짜
		//int toyear = oCalendar.get(Calendar.YEAR); 
		//int tomonth = oCalendar.get(Calendar.MONTH) + 1;
		//int today = oCalendar.get(Calendar.DAY_OF_MONTH);
		int toyear = Integer.parseInt(roomReservation.year);
		int tomonth = Integer.parseInt(roomReservation.month);
		int today = Integer.parseInt(roomReservation.day);
		// 년도
		roomReservation.eyeararray = new ArrayList<String>();
		roomReservation.emontharray = new ArrayList<String>();
		roomReservation.edayarray = new ArrayList<String>();
		for (int i = toyear; i <= toyear + 5; i++) {
			roomReservation.eyeararray.add(String.valueOf(i));
		}
		for (int i = tomonth; i <= 12; i++) {
			roomReservation.emontharray.add(String.valueOf(i));
		}
		for (int i = today; i <= calc(toyear, tomonth); i++) {
			roomReservation.edayarray.add(String.valueOf(i));
		}
		
		roomReservation.emonthStr = roomReservation.emontharray.toArray(new String[roomReservation.emontharray.size()]);
		roomReservation.edayStr = roomReservation.edayarray.toArray(new String[roomReservation.edayarray.size()]);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == roomReservation.s_monthCB ) {
			Calendar oCalendar = Calendar.getInstance();
			int toyear = oCalendar.get(Calendar.YEAR);
			int tomonth = oCalendar.get(Calendar.MONTH) + 1;
			String m = roomReservation.s_monthCB.getSelectedItem().toString();
			String y = roomReservation.s_yearCB.getSelectedItem().toString();
			roomReservation.sdayarray = new ArrayList<String>();
			int last = calc(Integer.parseInt(y),Integer.parseInt(m));
			
			for (int i = 1; i <= last ; i++) {
				roomReservation.sdayarray.add(String.valueOf(i));
				System.out.println(String.valueOf(i));
			}
			roomReservation.sdayStr = roomReservation.sdayarray.toArray(new String[roomReservation.sdayarray.size()]);
			if(m.equals(String.valueOf(tomonth)) && y.equals(String.valueOf(toyear))) { //현재 날짜
				yearmonthday();
			}
			roomReservation.s_dayCB.setModel(new DefaultComboBoxModel<String>(roomReservation.sdayStr));
		} else if (e.getSource() == roomReservation.s_yearCB) { //
			String y = roomReservation.s_yearCB.getSelectedItem().toString();
			Calendar oCalendar = Calendar.getInstance();
			int toyear = oCalendar.get(Calendar.YEAR);
			roomReservation.smontharray = new ArrayList<String>();

			if (toyear != Integer.parseInt(y)) {//현재년도와 선택년도 다르면
				for (int i = 1; i <= 12; i++) {
					roomReservation.smontharray.add(String.valueOf(i));
				}
				roomReservation.smonthStr = roomReservation.smontharray.toArray(new String[roomReservation.smontharray.size()]);
				System.out.println(roomReservation.smonthStr.toString());
			}else
				yearmonthday();
			roomReservation.s_monthCB.setModel(new DefaultComboBoxModel<String>(roomReservation.smonthStr));
			
		}else if(e.getSource()==roomReservation.e_monthCB) {
			Calendar oCalendar = Calendar.getInstance();
			int toyear = oCalendar.get(Calendar.YEAR);
			int tomonth = oCalendar.get(Calendar.MONTH) + 1;
			String m = roomReservation.e_monthCB.getSelectedItem().toString();
			String y = roomReservation.e_yearCB.getSelectedItem().toString();
			roomReservation.edayarray = new ArrayList<String>();
			int last = calc(Integer.parseInt(y),Integer.parseInt(m));
			
			for (int i = 1; i <= last ; i++) {
				roomReservation.edayarray.add(String.valueOf(i));
				System.out.println(String.valueOf(i));
			}
			roomReservation.edayStr = roomReservation.edayarray.toArray(new String[roomReservation.edayarray.size()]);
			if(m.equals(String.valueOf(tomonth)) && y.equals(String.valueOf(toyear))) { //현재 날짜
				eyearmonthday();
			}
			roomReservation.e_dayCB.setModel(new DefaultComboBoxModel<String>(roomReservation.edayStr));
			
		}else if(e.getSource()==roomReservation.e_yearCB) {
			String y = roomReservation.e_yearCB.getSelectedItem().toString();
			Calendar oCalendar = Calendar.getInstance();
			int toyear = oCalendar.get(Calendar.YEAR);
			roomReservation.emontharray = new ArrayList<String>();

			if (toyear != Integer.parseInt(y)) {//현재년도와 선택년도 다르면
				for (int i = 1; i <= 12; i++) {
					roomReservation.emontharray.add(String.valueOf(i));
				}
				roomReservation.emonthStr = roomReservation.smontharray.toArray(new String[roomReservation.emontharray.size()]);
				System.out.println(roomReservation.emonthStr.toString());
			}else
				eyearmonthday();
			roomReservation.e_monthCB.setModel(new DefaultComboBoxModel<String>(roomReservation.emonthStr));
			
		

		}else if(e.getSource() == roomReservation.e_dayCB) {
			
			
		}else if(e.getSource() == roomReservation.cheakB) {
			
			String startday = (String)roomReservation.s_yearCB.getSelectedItem()+"/"+(String)roomReservation.s_monthCB.getSelectedItem()
			 +"/"+(String)roomReservation.s_dayCB.getSelectedItem();
			String endday =(String)roomReservation.e_yearCB.getSelectedItem()+"/" +(String)roomReservation.e_monthCB.getSelectedItem()
			+"/"+(String)roomReservation.e_dayCB.getSelectedItem();
			
			SimpleDateFormat input = new SimpleDateFormat("yyyy/MM/dd"); //입력방식
			SimpleDateFormat input2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //입력방식 
			//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//출력방식
			Date start = null;
			Date end = null;
			try {
				start = input.parse(startday);
				end = input.parse(endday);
				
			} catch (ParseException e2) {
				e2.printStackTrace();
			}
			
			ArrayList<ReservationDTO> list = new ArrayList<ReservationDTO>();
			ReservationDTO dto = null;
			dto = new ReservationDTO();
			dto.setRoomNum(roomReservation.roomNum); //방정보를 받아 DB에 넣는다----------------------------------------
			int sw = 0;
			ReservationDAO dao = ReservationDAO.getInstance();
			list = dao.daycheak(dto);
			System.out.println("-----------------------------");
			System.out.println(list);
			
			if(list.isEmpty()) { //선택한 날의 예약이 없을때
				JOptionPane.showMessageDialog(null, "예약이 가능한 날짜입니다.");
				roomReservation.payB.setEnabled(true);
			}else {
			for (ReservationDTO cheakdto : list) {
				String sd = cheakdto.getStartday();
				String ed = cheakdto.getEndday();
				
				try {
					
					Date dbstart = input2.parse(sd);
					Date dbend = input2.parse(ed);
					
					int ck = end.compareTo(dbstart);
					int ck3 =dbend.compareTo(end);
					int ck2 = start.compareTo(dbstart);
					int ck4 = dbend.compareTo(start);
					
					if((ck== 1 && ck3 ==1) || (ck2 == 1 && ck4 == 1) || ck2 == 0 || ck3 == 0) {
						JOptionPane.showMessageDialog(null, "이미 예약된 날짜입니다.");
					}else {
						JOptionPane.showMessageDialog(null, "예약이 가능한 날짜입니다.");
						roomReservation.payB.setEnabled(true);
					}
					
				} catch (ParseException e1) { 
					e1.printStackTrace();
				}
				
				
			}
			}
			//숙박기간 금액 산정
		     try{ 
		         SimpleDateFormat format = new SimpleDateFormat("yyyy/mm/dd");
		         java.util.Date FirstDate = format.parse(startday);
		         java.util.Date SecondDate = format.parse(endday);
		         long calDate = FirstDate.getTime() - SecondDate.getTime(); 
		         
		         long calDateDays = calDate / ( 24*60*60*1000); 
		         calDateDays = Math.abs(calDateDays)*Integer.parseInt(roomReservation.price); 
		         
		         roomReservation.totalpayT.setText(Long.toString(calDateDays)+"원");
		         }
		         catch(ParseException e1)
		         {
		        	 e1.printStackTrace();
		         }
			
		}else if(e.getSource() == roomReservation.payB) {
			String id = roomReservation.loginId;
			String startday = (String)roomReservation.s_yearCB.getSelectedItem()+"/"+ (String)roomReservation.s_monthCB.getSelectedItem()
			+"/" +(String)roomReservation.s_dayCB.getSelectedItem();
			String endday =(String)roomReservation.e_yearCB.getSelectedItem() +"/" +(String)roomReservation.e_monthCB.getSelectedItem()
			+"/"+(String)roomReservation.e_dayCB.getSelectedItem();
			
			String name = roomReservation.nameT.getText();
			String phone = roomReservation.telT.getText();
			int comeway=0;
			if(roomReservation.runR.isSelected()) 
				comeway = 0;
            else if(roomReservation.carR.isSelected())
            	comeway = 1;
			Object adult =roomReservation.adultS.getValue();
			Object child =roomReservation.childS.getValue();
			int pay = Integer.parseInt(roomReservation.reserpayT.getText());
			int salepay=Integer.parseInt(roomReservation.salepayT.getText());
			int totalpay = pay;
			String payway =(String)roomReservation.paywayCB.getSelectedItem();
			
			System.out.println(id);
			System.out.println(startday);
			System.out.println(endday);
			System.out.println(roomReservation.roomNum);
			System.out.println(comeway);
			System.out.println(pay);
			System.out.println(adult);
			System.out.println(child);
			System.out.println(pay);
			System.out.println(salepay);
			System.out.println(totalpay);
			System.out.println(payway);
			
			//DTO객체에 저장 
			ReservationDTO dto = null;
			dto = new ReservationDTO();
			dto.setId(id);
			dto.setStartday(startday.toString());
			dto.setEndday(endday.toString());
//			dto.setName(name);
//			dto.setPhone(phone);
			dto.setRoomNum(roomReservation.roomNum);
			dto.setAdult(adult.toString());
			dto.setChild(child.toString());
			dto.setComeway(comeway);
			dto.setPay(pay);
			dto.setSalepay(salepay);
			dto.setTotalpay(totalpay);
			dto.setPayway(payway);
			System.out.println(startday);
			//DB 
			ReservationDAO dao = ReservationDAO.getInstance();
			
			int su = dao.insertreservation(dto);
			
			//System.out.println(term);
			//개인정보분석에 응답텍스트 출력 , 전체목록에 대표로 이름만 셋텍스트 
			if(su==1){
				JOptionPane.showMessageDialog(null, "예약이 완료 되었습니다.");
				new RoomChoice(roomReservation.loginId, roomReservation.name, roomReservation.tel);
				roomReservation.dispose();
			}else {
				JOptionPane.showMessageDialog(null, "예약이 실패 되었습니다.");
			}
		}
		else if(e.getSource() == roomReservation.cancelB)
		{
			new RoomChoice(roomReservation.loginId, roomReservation.name, roomReservation.tel);
			roomReservation.dispose();
		}
	}
}
