package view;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import model.MemberDAO;
import model.MemberDTO;
import view.RoomChoice;
import view.RoundedButton;

public class LoginFrame extends JFrame implements ActionListener {
	ImageIcon i = new ImageIcon("img/room.png");
	Image im = i.getImage();
	private RoundedButton loginB, memberB;
	private RoundedButton searchID, searchPW;
	private JLabel loginL, PasswordL;
	private JTextField loginT;
	private JPasswordField passwordT;
	private ArrayList<MemberDTO> arrayList;

	public LoginFrame() {
		// 컴퍼넌트 생성
		loginL = new JLabel("ID");
		PasswordL = new JLabel("PW");
		loginB = new RoundedButton("로그인");
		loginB.setBackground(Color.PINK);
		memberB = new RoundedButton("회원가입");
		memberB.setBackground(Color.PINK);
		searchID = new RoundedButton("아이디 찾기");
		searchID.setBackground(Color.PINK);
		searchPW = new RoundedButton("비밀번호 찾기");
		searchPW.setBackground(Color.PINK);
		loginT = new JTextField(10);
		passwordT = new JPasswordField(10);

		this.setTitle("Login");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		MyPanel panel = new MyPanel();
		panel.setLayout(null);
		loginL.setBounds(130, 305, 20, 10);
		loginB.setBounds(80, 450, 115, 30);
		memberB.setBounds(205, 450, 115, 30);
		searchID.setBounds(80, 490, 115, 30);
		searchPW.setBounds(205, 490, 115, 30);
		loginL.setBounds(130, 305, 20, 10);
		loginT.setBounds(160, 300, 100, 20);
		PasswordL.setBounds(130, 335, 20, 10);
		passwordT.setBounds(160, 330, 100, 20);

		Container c = getContentPane();
		c.add(panel);
		panel.add(loginL);
		panel.add(loginB);
		panel.add(memberB);
		panel.add(searchID);
		panel.add(searchPW);
		panel.add(loginL);
		panel.add(PasswordL);
		panel.add(loginT);
		panel.add(passwordT);

		setSize(400, 600);
		setVisible(true);
		event();
	}

	public void event() {
		loginB.addActionListener(this);
		memberB.addActionListener(this);
		searchID.addActionListener(this);
		searchPW.addActionListener(this);
		loginT.addActionListener(this);
		passwordT.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == loginB || e.getSource() == passwordT || e.getSource() == loginT) {
			String id = loginT.getText();
			String pw = new String(passwordT.getPassword());

			MemberDTO dto = new MemberDTO();

			dto.setId(id);
			dto.setPw(pw);

			MemberDAO dao = MemberDAO.getInstance();
			MemberDTO loginData = dao.login(dto);

			String idC = loginData.getId();
			String pwC = loginData.getPw();

			if (id.length() == 0 || pw.length() == 0) {
				JOptionPane.showMessageDialog(this, "아이디와 비밀번호를 입력해주세요");
			} else if (id.equals(idC) && pw.equals(pwC)) {
				JOptionPane.showMessageDialog(this, "로그인");
				dto.setId(idC);
				ArrayList<MemberDTO> arrayList = dao.loginInfo(dto);

				for (MemberDTO data : arrayList) {
					System.out.println(data.getId());
					System.out.println(data.getPw());
					System.out.println(data.getName());
					System.out.println(data.getBirth());
					System.out.println(data.getEmail());
					System.out.println(data.getTel());
					System.out.println(data.getAddress1());

					//new RoomChoice(idC, data.getName(), data.getTel());
					RoomChoice aa = new RoomChoice(idC, data.getName(), data.getTel());
				}

				// setVisible(false);
				dispose();

			} else {
				JOptionPane.showMessageDialog(this, "아이디나 비밀번호가 틀렸습니다.");
			}

		} else if (e.getSource() == memberB) {
			MemberJoin join = new MemberJoin();
		} else if (e.getSource() == searchID) {
			String email = JOptionPane.showInputDialog(this, "이메일을 입력하세요", null, JOptionPane.PLAIN_MESSAGE);
			if (email != null) {
				String tel = JOptionPane.showInputDialog(this, "휴대폰 번호를 입력하세요", null, JOptionPane.PLAIN_MESSAGE);
				MemberDTO dto = new MemberDTO();
				dto.setEmail(email);
				dto.setTel(tel);

				MemberDAO dao = MemberDAO.getInstance();
				String id = null;
				id = dao.getID(dto);
				if (tel == null) {
					JOptionPane.showMessageDialog(this, "정보가 입력 되지 않았습니다.");
				} else if (id != null) {
					JOptionPane.showMessageDialog(this, "아이디 : " + id);
				} else if (id == null) {
					JOptionPane.showMessageDialog(this, "정보가 일치하지 않습니다.");
				}
			} else {
				JOptionPane.showMessageDialog(this, "정보가 입력 되지 않았습니다.");
			}

		} else if (e.getSource() == searchPW) {
			String id = JOptionPane.showInputDialog(this, "아이디를 입력하세요", null, JOptionPane.PLAIN_MESSAGE);
			if (id != null) {
				String email = JOptionPane.showInputDialog(this, "이메일을 입력하세요", null, JOptionPane.PLAIN_MESSAGE);
				if (email != null) {
					String tel = JOptionPane.showInputDialog(this, "핸드폰 번호를 입력하세요", null, JOptionPane.PLAIN_MESSAGE);

					MemberDTO dto = new MemberDTO();
					dto.setId(id);
					dto.setEmail(email);
					dto.setTel(tel);

					MemberDAO dao = MemberDAO.getInstance();
					String pw = null;
					pw = dao.getPW(dto);
					if (tel == null) {
						JOptionPane.showMessageDialog(this, "정보가 입력 되지 않았습니다.");
					} else if (pw != null) {
						JOptionPane.showMessageDialog(this, "패스워드 : " + pw);
					} else if (pw == null) {
						JOptionPane.showMessageDialog(this, "정보가 일치하지 않습니다.");
					}
				} else {
					JOptionPane.showMessageDialog(this, "정보가 입력 되지 않았습니다.");
				}
			} else {
				JOptionPane.showMessageDialog(this, "정보가 입력 되지 않았습니다.");
			}
		}
	}

	class MyPanel extends JPanel {
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(im, 0, 0, getWidth(), getHeight(), this);
		}
	}

	
}