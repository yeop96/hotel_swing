package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.InformationController;
import model.InformationDAO;
import model.MemberDTO;
import model.ReservationDTO;

public class Information extends JFrame{

	public JPanel contentPane;
	public JLabel topL, idL, passL, passL1, nameL, birthL, phoneL, emailL, addressL;
	public JLabel reservInfoL, reservInfoL2, check_in_dateL, check_out_dateL, yearL1, yearL2, monthL1, monthL2, dayL1,
			dayL2, room_NumL, room_Num_TagL, people_NumL, adultL, childrenL, personNum1L, personNum2L, payL, priceL;

	public JTextField idT, nameT, birthT, phoneT, emailT, addressT;
	public JPasswordField password1, password2;
	public JTextField check_in_dateT1;
	public JTextField check_in_dateT2;
	public JTextField check_in_dateT3;
	public JTextField check_out_dateT1;
	public JTextField check_out_dateT2;
	public JTextField check_out_dateT3;
	public JTextField room_NumT;
	public JTextField people_NumT;
	public JTextField adultT;
	public JTextField childrenT;
	public JTextField paywayT;
	public JTextField priceT;

	public JButton accountCloseB, editB, confirmB, cancelB, toHomeB, reservChangeB, lastReservB, nextReservB,
			changeConfirmB, cancelReservB;
	public JLabel wonL;
	public InformationDAO dao = InformationDAO.getInstance();
	public int i = 0;
	public ArrayList<ReservationDTO> arrayList;
	public String loginId;
	public String name;
	public String tel;
	public String aaa;
	InformationController informationController;
	
	public Information(String loginId, String name, String tel) {
		this.loginId = loginId;
		this.name = name;
		this.tel = tel;
		
		informationController = new InformationController(this);

		ImageIcon img = new ImageIcon("img/hotel_logo.png");
	    this.setIconImage(img.getImage());
	    
	    this.setTitle("마이 페이지");
	    
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane pane = new JTabbedPane();

		ImageIcon tab1Icon = new ImageIcon("informationTabIcon/tab1Icon.png");
		ImageIcon tab2Icon = new ImageIcon("informationTabIcon/tab2Icon.png");
		ImageIcon tab3Icon = new ImageIcon("informationTabIcon/tab3Icon.png");

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 384, 561);
		contentPane.add(tabbedPane);

		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("예약정보", tab1Icon, tabbedPane_1, null);
		tabbedPane_1.setLayout(new BorderLayout());
		JPanel basePanel1 = new JPanel();
		basePanel1.setLayout(null);
		// 예약정보
		// 회원님의 예약 현황입니다.
		reservInfoL = new JLabel("회원님의 예약 현황입니다.");
		reservInfoL.setBounds(120, 12, 200, 25);

		check_in_dateL = new JLabel("체크 인 날짜 : ");
		check_in_dateL.setBounds(10, 50, 90, 25);

		check_in_dateT1 = new JTextField(5);
		check_in_dateT1.setBounds(110, 50, 40, 25);
		yearL1 = new JLabel("년");
		yearL1.setBounds(155, 50, 15, 25);

		check_in_dateT2 = new JTextField(5);
		check_in_dateT2.setBounds(175, 50, 25, 25);
		monthL1 = new JLabel("월");
		monthL1.setBounds(205, 50, 15, 25);

		check_in_dateT3 = new JTextField(5);
		check_in_dateT3.setBounds(225, 50, 25, 25);
		dayL1 = new JLabel("일");
		dayL1.setBounds(255, 50, 40, 25);

		check_out_dateL = new JLabel("체크 아웃 날짜 : ");
		check_out_dateL.setBounds(10, 100, 100, 25);

		check_out_dateT1 = new JTextField(10);
		check_out_dateT1.setBounds(110, 100, 40, 25);
		yearL2 = new JLabel("년");
		yearL2.setBounds(155, 100, 15, 25);

		check_out_dateT2 = new JTextField(10);
		check_out_dateT2.setBounds(175, 100, 25, 25);
		monthL2 = new JLabel("월");
		monthL2.setBounds(205, 100, 15, 25);

		check_out_dateT3 = new JTextField(10);
		check_out_dateT3.setBounds(225, 100, 25, 25);
		dayL2 = new JLabel("일");
		dayL2.setBounds(255, 100, 40, 25);

		room_NumL = new JLabel("객실 번호 : ");
		room_NumL.setBounds(10, 150, 85, 25);
		room_Num_TagL = new JLabel("호실");
		room_Num_TagL.setBounds(160, 150, 85, 25);
		room_NumT = new JTextField(3);
		room_NumT.setBounds(110, 150, 45, 25);

		people_NumL = new JLabel("예약 인원 : ");
		people_NumL.setBounds(10, 200, 85, 25);

		adultL = new JLabel("성    인 : ");
		adultL.setBounds(75, 225, 85, 25);
		childrenL = new JLabel("어린이 : ");
		childrenL.setBounds(75, 255, 85, 25);

		adultT = new JTextField(5);
		adultT.setBounds(140, 225, 45, 25);
		childrenT = new JTextField(5);
		childrenT.setBounds(140, 255, 45, 25);

		personNum1L = new JLabel(" 명");
		personNum1L.setBounds(190, 225, 85, 25);
		personNum2L = new JLabel(" 명");
		personNum2L.setBounds(190, 255, 85, 25);

		payL = new JLabel("결제 정보 : ");
		payL.setBounds(10, 310, 85, 25);
		paywayT = new JTextField(10);
		paywayT.setBounds(120, 310, 100, 25);

		priceL = new JLabel("가격 : ");
		priceL.setBounds(10, 360, 85, 25);
		priceT = new JTextField(10);
		priceT.setBounds(120, 360, 85, 25);
		wonL = new JLabel("원");
		wonL.setBounds(210, 360, 85, 25);
		reservChangeB = new JButton("예약 변경 및 취소");
		reservChangeB.setBounds(40, 400, 300, 25);
		lastReservB = new JButton("이전");
		lastReservB.setBounds(40, 425, 90, 25);
		nextReservB = new JButton("다음예약정보");
		nextReservB.setBounds(130, 425, 120, 25);
		changeConfirmB = new JButton("변경완료");
		changeConfirmB.setBounds(250, 425, 90, 25);
		cancelReservB = new JButton("예약취소");
		cancelReservB.setBounds(40, 450, 300, 25);

		check_in_dateT1.setEditable(false);
		check_in_dateT2.setEditable(false);
		check_in_dateT3.setEditable(false);
		check_out_dateT1.setEditable(false);
		check_out_dateT2.setEditable(false);
		check_out_dateT3.setEditable(false);
		room_NumT.setEditable(false);
		adultT.setEditable(false);
		childrenT.setEditable(false);
		paywayT.setEditable(false);
		priceT.setEditable(false);

		lastReservB.setEnabled(false);
		nextReservB.setEnabled(false);
		changeConfirmB.setEnabled(false);
		cancelReservB.setEnabled(false);

//         -------------------------------------

		//
//         // component panel to base panel
		basePanel1.add(reservInfoL);
		basePanel1.add(check_in_dateL);
		basePanel1.add(check_in_dateT1);
		basePanel1.add(check_in_dateT2);
		basePanel1.add(check_in_dateT3);
		basePanel1.add(yearL1);
		basePanel1.add(monthL1);
		basePanel1.add(dayL1);

		basePanel1.add(check_out_dateL);
		basePanel1.add(check_out_dateT1);
		basePanel1.add(check_out_dateT2);
		basePanel1.add(check_out_dateT3);
		basePanel1.add(yearL2);
		basePanel1.add(monthL2);
		basePanel1.add(dayL2);

		basePanel1.add(room_NumL);
		basePanel1.add(room_NumT);
		basePanel1.add(room_Num_TagL);

		basePanel1.add(people_NumL);
		basePanel1.add(adultL);
		basePanel1.add(adultT);
		basePanel1.add(childrenL);
		basePanel1.add(childrenT);

		basePanel1.add(personNum1L);
		basePanel1.add(personNum2L);

		basePanel1.add(payL);
		basePanel1.add(paywayT);
		basePanel1.add(priceL);
		basePanel1.add(priceT);
		basePanel1.add(wonL);
		basePanel1.add(reservChangeB);
		basePanel1.add(lastReservB);
		basePanel1.add(nextReservB);
		basePanel1.add(changeConfirmB);
		basePanel1.add(cancelReservB);

		// base panel 을 탭 패널에 붙이기
		tabbedPane_1.add(basePanel1);


		// 회원 정보 탭
		JTabbedPane tabbedPane_3 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("회원정보", tab3Icon, tabbedPane_3, null);
		tabbedPane_3.setLayout(new BorderLayout());

		// 프로필 확인 및 수정
		topL = new JLabel("프 로 필   확 인   및   수 정");
		topL.setBounds(120, 12, 150, 25);
		idL = new JLabel("아     이     디 : ");
		idL.setBounds(10, 50, 85, 25);
		passL = new JLabel("비  밀  번  호 : ");
		passL.setBounds(10, 100, 85, 25);
		passL1 = new JLabel("비  번   확  인 : ");
		passL1.setBounds(10, 150, 85, 25);
		nameL = new JLabel("이             름 : ");
		nameL.setBounds(10, 200, 85, 25);
		birthL = new JLabel("생  년  월  일 : ");
		birthL.setBounds(10, 250, 85, 25);
		phoneL = new JLabel("전  화  번  호 : ");
		phoneL.setBounds(10, 300, 85, 25);

		emailL = new JLabel("이     메     일 : ");
		emailL.setBounds(10, 350, 85, 25);
		addressL = new JLabel("주             소 : ");
		addressL.setBounds(10, 400, 85, 25);
		// 결제방법 추가

		idT = new JTextField(15);
		idT.setBounds(100, 50, 150, 25);
		idT.setEditable(false);
		password1 = new JPasswordField(10);
		password1.setBounds(100, 100, 150, 25);
		password1.setEditable(false);
		password2 = new JPasswordField(10);
		password2.setBounds(100, 150, 150, 25);
		password2.setEditable(false);

		nameT = new JTextField(15);
		nameT.setBounds(100, 200, 150, 25);
		nameT.setEditable(false);
		birthT = new JTextField(15);
		birthT.setBounds(100, 250, 150, 25);
		birthT.setEditable(false);
		phoneT = new JTextField(15);
		phoneT.setBounds(100, 300, 150, 25);
		phoneT.setEditable(false);
		emailT = new JTextField(20);
		emailT.setBounds(100, 350, 200, 25);
		emailT.setEditable(false);
		addressT = new JTextField(15);
		addressT.setBounds(100, 400, 280, 25);
		addressT.setEditable(false);

		JPanel basePanel3 = new JPanel();
		basePanel3.setLayout(null);
		basePanel3.add(topL);

		basePanel3.add(idL);
		basePanel3.add(idT);
		basePanel3.add(passL);
		basePanel3.add(password1);
		basePanel3.add(passL1);
		basePanel3.add(password2);
		basePanel3.add(nameL);
		basePanel3.add(nameT);

		basePanel3.add(birthL);
		basePanel3.add(birthT);

		basePanel3.add(phoneL);
		basePanel3.add(phoneT);

		basePanel3.add(emailL);
		basePanel3.add(emailT);

		basePanel3.add(addressL);
		basePanel3.add(addressT);

		JPanel btmPanel = new JPanel(new GridLayout(2, 1));
		btmPanel.setBounds(15, 443, 350, 60);

		accountCloseB = new JButton("회원탈퇴");
		editB = new JButton("수정");
		confirmB = new JButton("확인");
		cancelB = new JButton("취소");
		toHomeB = new JButton("홈 화면");

		JPanel bp1 = new JPanel(new GridLayout(1, 4));
		bp1.add(accountCloseB);
		bp1.add(editB);
		bp1.add(confirmB);
		bp1.add(cancelB);
		JPanel bp2 = new JPanel(new GridLayout(1, 1));
		bp2.add(toHomeB);

		btmPanel.add(bp1);
		btmPanel.add(bp2);
		basePanel3.add(btmPanel);

		// base panel 을 탭 패널에 붙이기
		tabbedPane_3.add(basePanel3);

		// 실행하자마자 DB에서 로그인 한 사람의 레코드를 꺼내서 회원 정보 패널에 뿌리기
		MemberDTO dto = dao.selectMemberData(loginId);
		informationController.getMemberData(dto);

		ReservationDTO rdto = dao.selectReservationData(loginId);
		informationController.getReservationData(rdto);

		accountCloseB.addActionListener(informationController);
		editB.addActionListener(informationController);
		confirmB.addActionListener(informationController);
		cancelB.addActionListener(informationController);
		toHomeB.addActionListener(informationController);
		reservChangeB.addActionListener(informationController);
		lastReservB.addActionListener(informationController);
		nextReservB.addActionListener(informationController);
		changeConfirmB.addActionListener(informationController);
		cancelReservB.addActionListener(informationController);

	}


}// class