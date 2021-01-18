package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.AbstractBorder;
import javax.swing.border.SoftBevelBorder;

import controller.RoomChoiceController;
import model.ReservationDAO;
import model.ReservationDTO;

public class RoomChoice extends JFrame 
{
	private static final long serialVersionUID = 1L;
	public static final int NUMBER = 8;
	public JButton[] imgBtn;
	public JLabel[] room_NumL;
	public JLabel[] priceL;
	public JLabel[] reservationL;
	public ImageIcon[] image;
	public JComboBox<String> yearCB;
	public JComboBox<String> monthCB;
	public JComboBox<String> dayCB;
	public RoundedButton memberInfo;
	public RoundedButton chat;
	public JLabel main;
	public Socket socket;
	public BufferedReader br;
	public PrintWriter pw;
	public String loginId;
	public String name;
	public String tel;
	public ArrayList<String> yearList;
	public ArrayList<String> monthList;
	public ArrayList<String> dayList;
	public String[] dayStr;
	public String[] monthStr;
	public String[] yearStr;
	public ChatClient cc;
	public int temp;
	public RoomChoiceController roomChoiceContreller;

	public RoomChoice(String loginId, String name, String tel)
	{
		this.loginId = loginId;
		this.name = name;
		this.tel = tel;
		roomChoiceContreller = new RoomChoiceController(this);
		
	    ImageIcon img = new ImageIcon("img/hotel_logo.png");
	    this.setIconImage(img.getImage());
	    
	    this.setTitle("객실 예약");
		
		imgBtn = new JButton[NUMBER];
		room_NumL = new JLabel[NUMBER];
		priceL = new JLabel[NUMBER];
		reservationL = new JLabel[NUMBER];
		image = new ImageIcon[NUMBER];
		
		roomChoiceContreller.yearmonthday();
		
		yearCB = new JComboBox<String>(yearList.toArray(new String[yearList.size()]));
		monthCB = new JComboBox<String>(monthList.toArray(new String[monthList.size()]));
		dayCB = new JComboBox<String>(dayList.toArray(new String[dayList.size()]));
		
		Font font = new Font("Times", Font.BOLD, 12);
		
		JPanel[] pRoom = new JPanel[NUMBER];
		JPanel[] pPrice = new JPanel[NUMBER];
		JPanel[] pReser = new JPanel[NUMBER];
		
		AbstractBorder[] border = new AbstractBorder[NUMBER];
		
		JPanel[] pText = new JPanel[NUMBER];
		JPanel[] pLine = new JPanel[NUMBER];
		
		main = new JLabel("방 정보");
		Font fontText = new Font("Times", Font.BOLD, 25);
		main.setFont(fontText);
		memberInfo = new RoundedButton("회원정보");
		chat = new RoundedButton("관리자문의");
		
		memberInfo.setBackground(Color.PINK);
//		memberInfo.setForeground(Color.WHITE);
		chat.setBackground(Color.PINK);
//		chat.setForeground(Color.WHITE);
		
		JPanel pL = new JPanel(new FlowLayout(FlowLayout.CENTER));
		pL.add(main);
		
		
		JPanel pBtn1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pBtn1.add(memberInfo);
		
		JPanel pBtn2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		pBtn2.add(chat);
		
		JPanel pDate = new JPanel(new FlowLayout(FlowLayout.CENTER));
		pDate.add(yearCB);
		pDate.add(monthCB);
		pDate.add(dayCB);
		
		JPanel pBtnTotal = new JPanel(new GridLayout(1, 2, 5, 5));
		
		pBtnTotal.add(pBtn1);
		pBtnTotal.add(pL);
		pBtnTotal.add(pBtn2);
		
		JPanel pBtnDate = new JPanel(new GridLayout(2, 1, 5, 5));
		pBtnDate.add(pBtnTotal);
		pBtnDate.add(pDate);
		
		
		//JPanel pTotal = new JPanel(new GridLayout(9, 1, 5, 5));
		//배경이미지 바꾸기
	    ImageIcon backImg = new ImageIcon("img/back2.jpg");
	      //배경 Panel 생성후 컨텐츠페인으로 지정      
	      JPanel pTotal = new JPanel(new GridLayout(9, 1, 5, 5)) {
	          public void paintComponent(Graphics g) {
	              // Approach 1: Dispaly image at at full size
	              g.drawImage(backImg.getImage(), 0, 0, null);
	              // Approach 2: Scale image to size of component
	               Dimension d = getSize();
	               g.drawImage(backImg.getImage(), 0, 0, d.width, d.height, null);
	              // Approach 3: Fix the image position in the scroll pane
	              // Point p = scrollPane.getViewport().getViewPosition();
	              // g.drawImage(icon.getImage(), p.x, p.y, null);
	              setOpaque(false); //그림을 표시하게 설정,투명하게 조절
	              super.paintComponent(g);
	          }
	      };
		
		priceL[0] = new JLabel("40000");
		priceL[1] = new JLabel("40000");
		priceL[2] = new JLabel("50000");
		priceL[3] = new JLabel("50000");
		priceL[4] = new JLabel("60000");
		priceL[5] = new JLabel("60000");
		priceL[6] = new JLabel("70000");
		priceL[7] = new JLabel("70000");
		
		for(int i = 0; i < NUMBER; i++)
		{
			//ImageIcon객체를 생성
			image[i] = new ImageIcon("img/" + (i + 1) + ".jpg");
		
			//ImageIcon에서 Image를 추출
			Image originImg = image[i].getImage(); 
			
			//추출된 Image의 크기를 조절하여 새로운 Image객체 생성
			Image changedImg= originImg.getScaledInstance(200, 80, Image.SCALE_SMOOTH );
			
			//새로운 Image로 ImageIcon객체를 생성
			image[i] = new ImageIcon(changedImg);


			
			imgBtn[i] = new JButton(image[i]);
			imgBtn[i].setBorderPainted(false); // 외곽선 없애기
			imgBtn[i].setContentAreaFilled(false); // 내용영역 채우기 안함
			imgBtn[i].setFocusPainted(false); // 버튼 눌렀을때 생기는 테두리 사용안함
			imgBtn[i].setOpaque(false); // 투명하게
			
			room_NumL[i] = new JLabel((i + 1) + "호실");
			room_NumL[i].setFont(font);
			pRoom[i] = new JPanel(new FlowLayout(FlowLayout.CENTER));
			pRoom[i].add(room_NumL[i]);
			
			priceL[i].setFont(font);
			pPrice[i] = new JPanel(new FlowLayout(FlowLayout.CENTER));
			pPrice[i].add(priceL[i]);
			
			reservationL[i] = new JLabel("예약가능");
			reservationL[i].setFont(font);
			pReser[i] = new JPanel(new FlowLayout(FlowLayout.CENTER));
			pReser[i].add(reservationL[i]);
			
			pText[i] = new JPanel(new GridLayout(3, 1, 5, 5));
			pText[i].add(pRoom[i]);
			pText[i].add(pPrice[i]);
			pText[i].add(pReser[i]);
			
			border[i] = new SoftBevelBorder(SoftBevelBorder.RAISED);
			pLine[i] = new JPanel(new GridLayout(1, 2, 5, 5));
			pLine[i].setBorder(border[i]);
			
			pLine[i].add(imgBtn[i]);
			pLine[i].add(pText[i]);
		}
		ArrayList<ReservationDTO> dayList = new ArrayList<ReservationDTO>();
		ReservationDAO dao = new ReservationDAO();
		SimpleDateFormat todayFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date time = new Date();
		
		String todayTime = todayFormat.format(time);
		
		for(int i = 0; i < NUMBER; i++)
		{
			dayList = dao.daycheak(room_NumL[i].getText());
			
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
				} catch (ParseException e2) {
					e2.printStackTrace();
				}
				int compare1 = today.compareTo(start);
				int compare2 = today.compareTo(end);
				
				if(compare1 == 1 && compare2 == -1 || compare1 == 0 || compare2 == 0)
				{
					imgBtn[i].setEnabled(false);
					room_NumL[i].setEnabled(false);
					priceL[i].setEnabled(false);
					reservationL[i].setText("예약완료");
					
					reservationL[i].setEnabled(false);		
				}
			}
		}
		pTotal.add(pBtnDate);
		
		for(int i = 0; i < NUMBER; i++)
		{
			pTotal.add(pLine[i]);		
		}
		JScrollPane scroll = new JScrollPane(pTotal);
		Container c = getContentPane();
		
		c.add(scroll);

		setBounds(100, 80, 500, 950);
		//setBounds(100, 80, 400, 600);
		setLocationRelativeTo(null);
		setVisible(true);
		
		event();
		roomChoiceContreller.service();
		
		
		this.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e)
			{
				pw.println("exit");
				pw.flush();
			}
		});
		
		
	}

	public void event()
	{
		chat.addActionListener(roomChoiceContreller);
		memberInfo.addActionListener(roomChoiceContreller);
		yearCB.addActionListener(roomChoiceContreller);
		monthCB.addActionListener(roomChoiceContreller);
		dayCB.addActionListener(roomChoiceContreller);
		
		for(int i = 0; i < NUMBER; i++)
		{
			imgBtn[i].addActionListener(roomChoiceContreller);
		}
	}
	
}