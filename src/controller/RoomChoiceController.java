package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;

import model.ReservationDAO;
import model.ReservationDTO;
import view.ChatClient;
import view.Information;
import view.RoomChoice;
import view.RoomReservation;

public class RoomChoiceController implements ActionListener, Runnable {

	RoomChoice roomChoice;
	
	public RoomChoiceController(RoomChoice roomChoice){
		this.roomChoice = roomChoice;
	}
	
	
	
	public int calc(int year, int month)
	{
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, 1);
		int lastD = cal.getActualMaximum(Calendar.DATE);
		return lastD;
	}
	public void yearmonthday()
	{
		Calendar oCalendar = Calendar.getInstance(); // 현재 날짜/시간 등의 각종 정보 얻기
		// 현재 날짜
		int toyear = oCalendar.get(Calendar.YEAR); 
		int tomonth = oCalendar.get(Calendar.MONTH) + 1;
		int today = oCalendar.get(Calendar.DAY_OF_MONTH);
		// 년도
		roomChoice.yearList = new ArrayList<String>();
		roomChoice.monthList = new ArrayList<String>();
		roomChoice.dayList = new ArrayList<String>();
		for (int i = toyear; i <= toyear + 5; i++) {
			roomChoice.yearList.add(String.valueOf(i));
		}
		for (int i = tomonth; i <= 12; i++) {
			roomChoice.monthList.add(String.valueOf(i));
		}
		for (int i = today; i <= calc(toyear, tomonth); i++) {
			roomChoice.dayList.add(String.valueOf(i));
		}
		
		roomChoice.monthStr = roomChoice.monthList.toArray(new String[roomChoice.monthList.size()]);
		roomChoice.dayStr = roomChoice.dayList.toArray(new String[roomChoice.dayList.size()]);
	}
	
	public void service()
	{
		
		try
		{
			roomChoice.socket = new Socket("127.0.0.1", 9200);
			roomChoice.br = new BufferedReader(new InputStreamReader(roomChoice.socket.getInputStream()));
			roomChoice.pw = new PrintWriter(new OutputStreamWriter(roomChoice.socket.getOutputStream()));
		} 
		catch (UnknownHostException e) 
		{
			System.out.println("서버를 찾을 수 없습니다");
			e.printStackTrace();
			System.exit(0);
		} 
		catch (IOException e) 
		{
			System.out.println("서버 연결이 안되었습니다");
			e.printStackTrace();
			System.exit(0);
		}
		
		Thread t = new Thread(this);
		t.start();
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == roomChoice.chat)
		{
			if(roomChoice.temp == 0)
			{
				roomChoice.cc = new ChatClient(roomChoice.loginId, roomChoice.name, roomChoice.tel);
				roomChoice.cc.chatClientController.service();
				roomChoice.dispose();
			}
			else
			{
				roomChoice.cc.setVisible(true);
			}
			roomChoice.temp++;
		}
		else if(e.getSource() == roomChoice.memberInfo)
		{
			new Information(roomChoice.loginId, roomChoice.name, roomChoice.tel);
			roomChoice.dispose();
		}
		else if (e.getSource() == roomChoice.monthCB )
		{
			Calendar oCalendar = Calendar.getInstance();
			int toyear = oCalendar.get(Calendar.YEAR);
			int tomonth = oCalendar.get(Calendar.MONTH) + 1;
			String m = roomChoice.monthCB.getSelectedItem().toString();
			String y = roomChoice.yearCB.getSelectedItem().toString();
			roomChoice.dayList = new ArrayList<String>();
			int last = calc(Integer.parseInt(y),Integer.parseInt(m));
			
			for (int i = 1; i <= last ; i++) 
			{
				roomChoice.dayList.add(String.valueOf(i));
				System.out.println(String.valueOf(i));
			}
			roomChoice.dayStr = roomChoice.dayList.toArray(new String[roomChoice.dayList.size()]);
			
			if(m.equals(String.valueOf(tomonth)) && y.equals(String.valueOf(toyear))) 
			{ //현재 날짜
				yearmonthday();
			}
			roomChoice.dayCB.setModel(new DefaultComboBoxModel<String>(roomChoice.dayStr));
		} 
		else if (e.getSource() == roomChoice.yearCB)
		{ //
			String y = roomChoice.yearCB.getSelectedItem().toString();
			Calendar oCalendar = Calendar.getInstance();
			int toyear = oCalendar.get(Calendar.YEAR);
			roomChoice.monthList = new ArrayList<String>();

			if (toyear != Integer.parseInt(y))
			{//현재년도와 선택년도 다르면
				for (int i = 1; i <= 12; i++) 
				{
					roomChoice.monthList.add(String.valueOf(i));
				}
				roomChoice.monthStr = roomChoice.monthList.toArray(new String[roomChoice.monthList.size()]);
			}
			else
				yearmonthday();
			roomChoice.monthCB.setModel(new DefaultComboBoxModel<String>(roomChoice.monthStr));
		}
		else if(e.getSource() == roomChoice.dayCB)
		{
			for(int i = 0; i < roomChoice.NUMBER; i++)
			{
				roomChoice.imgBtn[i].setEnabled(true);
				roomChoice.room_NumL[i].setEnabled(true);
				roomChoice.priceL[i].setEnabled(true);
				roomChoice.reservationL[i].setText("예약가능");
				roomChoice.reservationL[i].setEnabled(true);	
			}
			int year = Integer.parseInt(roomChoice.yearCB.getSelectedItem().toString());
			int month = Integer.parseInt(roomChoice.monthCB.getSelectedItem().toString());
			int day = Integer.parseInt(roomChoice.dayCB.getSelectedItem().toString());
			
			ArrayList<ReservationDTO> dayList = new ArrayList<ReservationDTO>();
			ReservationDAO dao = new ReservationDAO();
			DateTimeFormatter todayFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime date = LocalDateTime.of(year, month, day, 00, 00, 00);
			
			String todayTime = todayFormat.format(date);
			
			for(int i = 0; i < roomChoice.NUMBER; i++)
			{
				dayList = dao.daycheak(roomChoice.room_NumL[i].getText());
				
				for(ReservationDTO dto : dayList)
				{
					SimpleDateFormat input2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					
					Date today = null;
					Date start = null;
					Date end = null;
					
					try {
						today = input2.parse(todayTime);
						start = input2.parse(dto.getStartday());
						end = input2.parse(dto.getEndday());
					} 
					catch (ParseException e2)
					{
						e2.printStackTrace();
					}
					int compare1 = today.compareTo(start);
					int compare2 = today.compareTo(end);
					
					if(compare1 == 1 && compare2 == -1 || compare1 == 0)
					{
						roomChoice.imgBtn[i].setEnabled(false);
						roomChoice.room_NumL[i].setEnabled(false);
						roomChoice.priceL[i].setEnabled(false);
						roomChoice.reservationL[i].setText("예약완료");
						roomChoice.reservationL[i].setEnabled(false);		
					}
				}
			}
		}
		
		String room = null;
		for(int i = 0; i < roomChoice.NUMBER; i++)
		{
			if(e.getSource() == roomChoice.imgBtn[i])
			{
				room = roomChoice.room_NumL[i].getText();
				
				roomChoice.pw.println(room);
				roomChoice.pw.flush();
				
				new RoomReservation(roomChoice.loginId, roomChoice.name, roomChoice.tel, room, roomChoice.priceL[i].getText(), roomChoice.yearCB.getSelectedItem().toString(), roomChoice.monthCB.getSelectedItem().toString(), roomChoice.dayCB.getSelectedItem().toString());
				roomChoice.dispose();
			}
		}
	}
	@Override
	public void run()
	{
		String room = null;
		
		while(true)
		{
			try 
			{
				room = roomChoice.br.readLine();

				if(room == null || room.toLowerCase().equals("exit"))
				{
					roomChoice.br.close();
					roomChoice.pw.close();
					roomChoice.socket.close();
					
					System.exit(0);
					break;
				}
				
				for(int i = 0; i < roomChoice.NUMBER; i++)
				{
					if(roomChoice.room_NumL[i].getText().equals(room))
					{
						roomChoice.imgBtn[i].setEnabled(false);
						roomChoice.room_NumL[i].setEnabled(false);
						roomChoice.priceL[i].setEnabled(false);
						roomChoice.reservationL[i].setText("예약완료");
						roomChoice.reservationL[i].setEnabled(false);
					}
				}
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}

}
