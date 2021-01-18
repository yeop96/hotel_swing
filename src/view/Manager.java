package view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.MemberDAO;
import model.MemberDTO;
import model.ReservationDTO;

public class Manager extends JFrame implements ActionListener, ListSelectionListener{
   private JList<String> list;
   private DefaultListModel<String> model;
   private JLabel room1L, room2L, room3L, room4L, room5L, room6L, room7L, room8L;
   private JButton btnRoom_1 ,btnRoom_2, btnRoom_3 ,btnRoom_4, btnRoom_5 , btnRoom_6, btnRoom_7 , btnRoom_8;
   private RoomInfo room;
   private ImageIcon backImg;
   
   public static void main(String[] args) {
      new Manager().event();
      //관리자 모드로 시작합니다.
   }

   public Manager() {// 생성자 시작
      setTitle("호텔 지배인");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 1200, 550);
      ImageIcon img = new ImageIcon("img/hotel_logo.png");
      this.setIconImage(img.getImage());
//base panel as content pane
//      JPanel basePanel = new JPanel();
//      basePanel.setBorder(new EmptyBorder(5, 5, 5, 5));
//      basePanel.setLayout(null);
      
      //배경이미지 바꾸기
      backImg = new ImageIcon("img/back.jpg");
      //배경 Panel 생성후 컨텐츠페인으로 지정      
      JPanel basePanel = new JPanel() {
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
      
      basePanel.setBorder(new EmptyBorder(5, 5, 5, 5));
      basePanel.setLayout(null);

//JLabel
      room1L = new JLabel("1호실");
      room1L.setBounds(130, 65, 60, 15);
      room2L = new JLabel("2호실");
      room2L.setBounds(355, 65, 60, 15);
      room3L = new JLabel("3호실");
      room3L.setBounds(575, 65, 60, 15);
      room4L = new JLabel("4호실");
      room4L.setBounds(800, 65, 60, 15);
      room5L = new JLabel("5호실");
      room5L.setBounds(130, 272, 60, 15);
      room6L = new JLabel("6호실");
      room6L.setBounds(355, 272, 60, 15);
      room7L = new JLabel("7호실");
      room7L.setBounds(575, 272, 60, 15);
      room8L = new JLabel("8호실");
      room8L.setBounds(800, 272, 60, 15);

      basePanel.add(room1L);
      basePanel.add(room2L);
      basePanel.add(room3L);
      basePanel.add(room4L);
      basePanel.add(room5L);
      basePanel.add(room6L);
      basePanel.add(room7L);
      basePanel.add(room8L);
      
      //ImageIcon
      ImageIcon room1Icon = new ImageIcon("picture/1.jpg");
      ImageIcon room2Icon = new ImageIcon("picture/2.jpg");
      ImageIcon room3Icon = new ImageIcon("picture/3.jpg");
      ImageIcon room4Icon = new ImageIcon("picture/4.jpg");
      ImageIcon room5Icon = new ImageIcon("picture/5.jpg");
      ImageIcon room6Icon = new ImageIcon("picture/6.jpg");
      ImageIcon room7Icon = new ImageIcon("picture/7.jpg");
      ImageIcon room8Icon = new ImageIcon("picture/8.jpg");
    
      //ImageIcon에서 Image를 추출
      Image originImg1 = room1Icon.getImage();
      Image originImg2 = room2Icon.getImage(); 
      Image originImg3 = room3Icon.getImage(); 
      Image originImg4 = room4Icon.getImage(); 
      Image originImg5 = room5Icon.getImage(); 
      Image originImg6 = room6Icon.getImage(); 
      Image originImg7 = room7Icon.getImage(); 
      Image originImg8 = room8Icon.getImage();
      
      //추출된 Image의 크기를 조절하여 새로운 Image객체 생성
      Image changedImg1= originImg1.getScaledInstance(250, 200, Image.SCALE_SMOOTH );
      Image changedImg2= originImg2.getScaledInstance(250, 200, Image.SCALE_SMOOTH );
      Image changedImg3= originImg3.getScaledInstance(250, 200, Image.SCALE_SMOOTH );
      Image changedImg4= originImg4.getScaledInstance(250, 200, Image.SCALE_SMOOTH );
      Image changedImg5= originImg5.getScaledInstance(250, 200, Image.SCALE_SMOOTH );
      Image changedImg6= originImg6.getScaledInstance(250, 200, Image.SCALE_SMOOTH );
      Image changedImg7= originImg7.getScaledInstance(250, 200, Image.SCALE_SMOOTH );
      Image changedImg8= originImg8.getScaledInstance(250, 200, Image.SCALE_SMOOTH );
		
      //새로운 Image로 ImageIcon객체를 생성
      room1Icon = new ImageIcon(changedImg1);
      room2Icon = new ImageIcon(changedImg2);
      room3Icon = new ImageIcon(changedImg3);
      room4Icon = new ImageIcon(changedImg4);
      room5Icon = new ImageIcon(changedImg5);
      room6Icon = new ImageIcon(changedImg6);
      room7Icon = new ImageIcon(changedImg7);
      room8Icon = new ImageIcon(changedImg8);


      btnRoom_1 = new JButton("", room1Icon);
      btnRoom_1.setBounds(40, 90, 215, 125);
      btnRoom_1.setBorderPainted(false); // 외곽선 없애기
      btnRoom_1.setContentAreaFilled(false); // 내용영역 채우기 안함
      btnRoom_1.setFocusPainted(false); // 버튼 눌렀을때 생기는 테두리 사용안함
      btnRoom_1.setOpaque(false); // 투명하게
      basePanel.add(btnRoom_1);

      btnRoom_2 = new JButton("", room2Icon);
      btnRoom_2.setBounds(265, 90, 215, 125);
      btnRoom_2.setBorderPainted(false); // 외곽선 없애기
      btnRoom_2.setContentAreaFilled(false); // 내용영역 채우기 안함
      btnRoom_2.setFocusPainted(false); // 버튼 눌렀을때 생기는 테두리 사용안함
      btnRoom_2.setOpaque(false); // 투명하게
      basePanel.add(btnRoom_2);

      btnRoom_3 = new JButton("", room3Icon);
      btnRoom_3.setBounds(490, 90, 215, 125);
      btnRoom_3.setBorderPainted(false); // 외곽선 없애기
      btnRoom_3.setContentAreaFilled(false); // 내용영역 채우기 안함
      btnRoom_3.setFocusPainted(false); // 버튼 눌렀을때 생기는 테두리 사용안함
      btnRoom_3.setOpaque(false); // 투명하게
      basePanel.add(btnRoom_3);

      btnRoom_4 = new JButton("", room4Icon);
      btnRoom_4.setBounds(715, 90, 215, 125);
      btnRoom_4.setBorderPainted(false); // 외곽선 없애기
      btnRoom_4.setContentAreaFilled(false); // 내용영역 채우기 안함
      btnRoom_4.setFocusPainted(false); // 버튼 눌렀을때 생기는 테두리 사용안함
      btnRoom_4.setOpaque(false); // 투명하게
      basePanel.add(btnRoom_4);

      btnRoom_5 = new JButton("", room5Icon);
      btnRoom_5.setBounds(40, 296, 215, 125);
      btnRoom_5.setBorderPainted(false); // 외곽선 없애기
      btnRoom_5.setContentAreaFilled(false); // 내용영역 채우기 안함
      btnRoom_5.setFocusPainted(false); // 버튼 눌렀을때 생기는 테두리 사용안함
      btnRoom_5.setOpaque(false); // 투명하게
      basePanel.add(btnRoom_5);

      btnRoom_6 = new JButton("", room6Icon);
      btnRoom_6.setBounds(265, 296, 215, 125);
      btnRoom_6.setBorderPainted(false); // 외곽선 없애기
      btnRoom_6.setContentAreaFilled(false); // 내용영역 채우기 안함
      btnRoom_6.setFocusPainted(false); // 버튼 눌렀을때 생기는 테두리 사용안함
      btnRoom_6.setOpaque(false); // 투명하게
      basePanel.add(btnRoom_6);

      btnRoom_7 = new JButton("", room7Icon);
      btnRoom_7.setBounds(490, 296, 215, 125);
      btnRoom_7.setBorderPainted(false); // 외곽선 없애기
      btnRoom_7.setContentAreaFilled(false); // 내용영역 채우기 안함
      btnRoom_7.setFocusPainted(false); // 버튼 눌렀을때 생기는 테두리 사용안함
      btnRoom_7.setOpaque(false); // 투명하게
      basePanel.add(btnRoom_7);

      btnRoom_8 = new JButton("", room8Icon);
      btnRoom_8.setBounds(715, 296, 215, 125);
      btnRoom_8.setBorderPainted(false); // 외곽선 없애기
      btnRoom_8.setContentAreaFilled(false); // 내용영역 채우기 안함
      btnRoom_8.setFocusPainted(false); // 버튼 눌렀을때 생기는 테두리 사용안함
      btnRoom_8.setOpaque(false); // 투명하게
      basePanel.add(btnRoom_8);

//----------------------------------------------------------------
      // JList
      list = new JList<String>(new DefaultListModel<String>());
      model = (DefaultListModel<String>)list.getModel();
         
      list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);   
      ArrayList<MemberDTO> ilist = new ArrayList<MemberDTO>();
      MemberDTO dto = new MemberDTO();
      MemberDAO dao = MemberDAO.getInstance();
      String id = null;
      ilist = dao.getChatId(dto);
      for (MemberDTO data : ilist) {
    	  id = data.getId();
    	  model.addElement(id);
      }
      


      JScrollPane scrollPane = new JScrollPane(list);
      scrollPane.setBounds(965, 42, 219, 440);
      scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
      basePanel.add(scrollPane);

      Container contentPane = this.getContentPane();
      contentPane.add(basePanel);

      setLocationRelativeTo(null);
      
      setVisible(true);

      System.out.println(model.getSize());
      System.out.println(list.getSelectedIndex());
      list.addListSelectionListener(this);

      
   }// 생성자
   public void event() {
      btnRoom_1.addActionListener(this);
      btnRoom_2.addActionListener(this);
      btnRoom_3.addActionListener(this);
      btnRoom_4.addActionListener(this);
      btnRoom_5.addActionListener(this);
      btnRoom_6.addActionListener(this);
      btnRoom_7.addActionListener(this);
      btnRoom_8.addActionListener(this);      
   }
   @Override
   public void actionPerformed(ActionEvent e) {
      if(e.getSource()==btnRoom_1) {
         String roomNumber = room1L.getText();
         ReservationDTO dto = new ReservationDTO();
         dto.setRoomNum(roomNumber);
         room = new RoomInfo(dto);
         
         
      }else if(e.getSource()==btnRoom_2) {
         String roomNumber = room2L.getText();
         ReservationDTO dto = new ReservationDTO();
         dto.setRoomNum(roomNumber);
         room = new RoomInfo(dto);

      }else if(e.getSource()==btnRoom_3) {
         String roomNumber = room3L.getText();
         ReservationDTO dto = new ReservationDTO();
         dto.setRoomNum(roomNumber);
         room = new RoomInfo(dto);
         
      }else if(e.getSource()==btnRoom_4) {
         String roomNumber = room4L.getText();
         ReservationDTO dto = new ReservationDTO();
         dto.setRoomNum(roomNumber);
         room = new RoomInfo(dto);
         
      }else if(e.getSource()==btnRoom_5) {
         String roomNumber = room5L.getText();
         ReservationDTO dto = new ReservationDTO();
         dto.setRoomNum(roomNumber);
         room = new RoomInfo(dto);
         
      }else if(e.getSource()==btnRoom_6) {
         String roomNumber = room6L.getText();
         ReservationDTO dto = new ReservationDTO();
         dto.setRoomNum(roomNumber);
         room = new RoomInfo(dto);
         
      }else if(e.getSource()==btnRoom_7) {
         String roomNumber = room7L.getText();
         ReservationDTO dto = new ReservationDTO();
         dto.setRoomNum(roomNumber);
         room = new RoomInfo(dto);
         
      }else if(e.getSource()==btnRoom_8) {
         String roomNumber = room8L.getText();
         ReservationDTO dto = new ReservationDTO();
         dto.setRoomNum(roomNumber);
         room = new RoomInfo(dto);
         
      }
      
   }
   

  

   @Override
   public void valueChanged(ListSelectionEvent e) {
      String csLoginId = list.getSelectedValue();
      
      if(list.getSelectedIndex() == -1)
      {
    	  return;
      }
      new ManagerChatClient(csLoginId).managerChatClientController.service();
      dispose();
   }
}