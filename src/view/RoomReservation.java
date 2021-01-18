package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import controller.RoomReservationController;
import model.MemberDTO;

public class RoomReservation extends JFrame {
	public JLabel startL, endL, hotelL, reserInfoL, nameL, phoneL, people_NumL, carfulL, visitL, payInfoL, reserpayL,
			salepayL, totalpayL, vatL, paywayL, s_yearL, s_monthL, s_dayL, e_yearL, e_monthL, e_dayL;
	public JTextField nameT, telT, reserpayT, salepayT, totalpayT;
	public JRadioButton runR, carR;
	public JComboBox<String> s_yearCB, s_monthCB, s_dayCB, e_yearCB, e_monthCB, e_dayCB;
	public JComboBox<String> paywayCB;
	public RoundedButton payB, cancelB ,cheakB;
	public JSpinner adultS, childS;
	public ArrayList<MemberDTO> mdto;
	public String loginId;

	public ArrayList<String> syeararray;
	public ArrayList<String> smontharray;
	public ArrayList<String> sdayarray;
	public ArrayList<String> eyeararray;
	public ArrayList<String> emontharray;
	public ArrayList<String> edayarray;
	public String[] sdayStr;
	public String[] smonthStr;
	public String[] syearStr;
	public String[] edayStr;
	public String[] emonthStr;
	public String[] eyearStr;
	
	public String id1;
	public String name;
	public String tel;
	public String roomNum;
	public String price;
	public String year;
	public String month;
	public String day;
	RoomReservationController roomReservationController;

	public RoomReservation(String id1, String name, String tel, String roomNum, String price, String year, String month, String day) {
		this.id1 = id1;
		this.name = name;
		this.tel = tel;
		this.roomNum = roomNum;
		this.price = price;
		this.year = year;
		this.month = month;
		this.day = day;
		
		roomReservationController = new RoomReservationController(this);
		
		startL = new JLabel("예약날자");
		endL = new JLabel("퇴실날자");
		hotelL = new JLabel("세종 호텔");
		reserInfoL = new JLabel("예약자 정보");
		nameL = new JLabel("예약자 이름");
		phoneL = new JLabel("휴대폰 번호");
		people_NumL = new JLabel("숙박 인원");
		carfulL = new JLabel("*만8세 이하 어린이 ");
		visitL = new JLabel("방문 방법");
		payInfoL = new JLabel("결제 정보");
		reserpayL = new JLabel("예약금액");
		totalpayL = new JLabel("총 결제금액");
		vatL = new JLabel("(vat)포함");
		salepayL = new JLabel("할인금액");
		paywayL = new JLabel("결제수단");
		s_yearL = new JLabel("년");
		s_monthL = new JLabel("월");
		s_dayL = new JLabel("일");
		e_yearL = new JLabel("년");
		e_monthL = new JLabel("월");
		e_dayL = new JLabel("일");

		nameT = new JTextField();
		telT = new JTextField();
		reserpayT = new JTextField("0");
		salepayT = new JTextField("0");
		totalpayT = new JTextField("0");

		adultS = new JSpinner();
		childS = new JSpinner();
		runR = new JRadioButton("도보");
		carR = new JRadioButton("차량");
		ButtonGroup group = new ButtonGroup();

		payB = new RoundedButton("결제");
		cancelB = new RoundedButton("취소");
		payB.setBackground(Color.PINK);
		cancelB.setBackground(Color.PINK);
		cheakB = new RoundedButton("확인");
		cheakB.setBackground(Color.PINK);
		// ------------------------------------------------------
		roomReservationController.yearmonthday();
		roomReservationController.eyearmonthday();
		// -------------------------------------------------------
		paywayCB = new JComboBox<String>();
		s_yearCB = new JComboBox<String>(syeararray.toArray(new String[syeararray.size()]));
		s_monthCB = new JComboBox<String>(smontharray.toArray(new String[smontharray.size()]));
		s_dayCB = new JComboBox<String>(sdayarray.toArray(new String[sdayarray.size()]));
		e_yearCB = new JComboBox<String>(eyeararray.toArray(new String[eyeararray.size()]));
		e_monthCB = new JComboBox<String>(emontharray.toArray(new String[emontharray.size()]));
		e_dayCB = new JComboBox<String>(edayarray.toArray(new String[edayarray.size()]));
		
		setTitle("숙박 예약");
		ImageIcon img = new ImageIcon("img/hotel_logo.png");
	    this.setIconImage(img.getImage());
	    this.setResizable(false);

		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(400, 800));
		panel.setLayout(null);
		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(0, 0, 385, 800);
		JPanel p1 = new JPanel(null);
		p1.setPreferredSize(new Dimension(500, 400));
		p1.add(scrollPane);
		//array값을 배열로 바꿔 모델로 값을 준다 , 모델값이 동적으로 변동
		
		
		startL.setBounds(26, 60, 73, 15);
		panel.add(startL);
		s_yearCB.setBounds(26, 80, 82, 25);
		panel.add(s_yearCB);
		s_yearL.setBounds(110, 80, 30, 25);
		panel.add(s_yearL);
		s_monthCB.setBounds(125, 80, 66, 25);
		panel.add(s_monthCB);
		s_monthL.setBounds(190, 80, 30, 25);
		panel.add(s_monthL);
		s_dayCB.setBounds(206, 80, 66, 25);
		panel.add(s_dayCB);
		s_dayL.setBounds(270, 80, 30, 25);
		panel.add(s_dayL);
		
		endL.setBounds(26, 110, 73, 15);
		panel.add(endL);
		e_yearCB.setBounds(26, 130, 82, 25);
		panel.add(e_yearCB);
		e_yearL.setBounds(110, 130, 30, 25);
		panel.add(e_yearL);
		e_monthCB.setBounds(125, 130, 66, 25);
		panel.add(e_monthCB);
		e_monthL.setBounds(190, 130, 30, 25);
		panel.add(e_monthL);
		e_dayCB.setBounds(206, 130, 66, 25);
		panel.add(e_dayCB);
		e_dayL.setBounds(270, 130, 30, 25);
		panel.add(e_dayL);
		cheakB.setBounds(310, 130, 45, 25);
		panel.add(cheakB);
		

		hotelL.setFont(new Font("굴림", Font.BOLD, 23));
		hotelL.setBounds(145, 20, 112, 40);
		panel.add(hotelL);

		reserInfoL.setFont(new Font("굴림", Font.BOLD, 15));
		reserInfoL.setBounds(26, 170, 94, 21);
		panel.add(reserInfoL);

		nameL.setBounds(26, 206, 73, 15);
		panel.add(nameL);

		nameT.setBounds(26, 233, 116, 21);
		panel.add(nameT);
		nameT.setColumns(10);
		nameT.setText(name);
		nameT.setEditable(false);

		phoneL.setBounds(26, 264, 73, 15);
		panel.add(phoneL);

		telT.setBounds(26, 289, 116, 21);
		panel.add(telT);
		telT.setColumns(10);
		telT.setText(tel);
		telT.setEditable(false);

		people_NumL.setBounds(26, 320, 116, 21);
		panel.add(people_NumL);

		JLabel lblNewLabel = new JLabel("성인");
		lblNewLabel.setBounds(26, 350, 42, 15);
		panel.add(lblNewLabel);

		adultS.setBounds(58, 347, 34, 22);
		panel.add(adultS);

		JLabel lblNewLabel_8 = new JLabel("어린이");
		lblNewLabel_8.setBounds(100, 350, 42, 15);
		panel.add(lblNewLabel_8);

		childS.setBounds(144, 347, 34, 22);
		panel.add(childS);

		carfulL.setFont(new Font("굴림", Font.ITALIC, 10));
		carfulL.setBounds(185, 350, 126, 15);
		panel.add(carfulL);

		visitL.setBounds(26, 389, 57, 15);
		panel.add(visitL);

		runR.setBounds(36, 410, 63, 23);
		panel.add(runR);

		carR.setBounds(110, 410, 63, 23);
		panel.add(carR);
		group.add(runR);
		group.add(carR);

		payInfoL.setFont(new Font("굴림", Font.BOLD, 15));
		payInfoL.setBounds(26, 450, 94, 21);
		panel.add(payInfoL);

		reserpayL.setBounds(26, 481, 57, 15);
		panel.add(reserpayL);

		reserpayT.setBounds(210, 475, 116, 21);
		panel.add(reserpayT);
		reserpayT.setColumns(10);
		reserpayT.setText(price);
		reserpayT.setEditable(false);

		salepayL.setBounds(26, 506, 57, 15);
		panel.add(salepayL);

		salepayT.setBounds(210, 503, 116, 21);
		panel.add(salepayT);
		salepayT.setColumns(10);
		salepayT.setEditable(false);

		totalpayL.setBounds(26, 534, 73, 15);
		panel.add(totalpayL);

		vatL.setFont(new Font("굴림", Font.ITALIC, 11));
		vatL.setBounds(100, 535, 57, 15);
		panel.add(vatL);

		totalpayT.setBounds(210, 531, 116, 21);
		panel.add(totalpayT);
		totalpayT.setColumns(10);
		totalpayT.setEditable(false);

		paywayL.setFont(new Font("굴림", Font.BOLD, 15));
		paywayL.setBounds(26, 594, 73, 21);
		panel.add(paywayL);

		paywayCB.setModel(new DefaultComboBoxModel<String>(new String[] { "현금결제", "간편 계좌 이체", "신용/체크카드", "토스", "세종페이" }));
		paywayCB.setBounds(26, 625, 285, 21);
		panel.add(paywayCB);

		payB.setFont(new Font("굴림", Font.PLAIN, 14));
		payB.setBounds(26, 700, 130, 23);
		payB.setEnabled(false);
		panel.add(payB);
		cancelB.setFont(new Font("굴림", Font.PLAIN, 14));
		cancelB.setBounds(195, 700, 130, 23);
		panel.add(cancelB);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 444, 384, 140);
		panel.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 587, 372, 103);
		panel.add(separator_1);

		setContentPane(p1);
		 loginId =id1;
		event();
		setBounds(100, 100, 400, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setLocationRelativeTo(null);
		setVisible(true);
	}

	public void event() {
		payB.addActionListener(roomReservationController);
		cancelB.addActionListener(roomReservationController);
		cheakB.addActionListener(roomReservationController);
		s_monthCB.addActionListener(roomReservationController);
		s_yearCB.addActionListener(roomReservationController);
		e_monthCB.addActionListener(roomReservationController);
		e_yearCB.addActionListener(roomReservationController);
		e_dayCB.addActionListener(roomReservationController);
	}
	
	


}
