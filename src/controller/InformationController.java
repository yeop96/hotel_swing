package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.InformationDAO;
import model.MemberDTO;
import model.ReservationDTO;
import view.Information;
import view.LoginFrame;
import view.RoomChoice;

public class InformationController implements ActionListener{
	private ArrayList<ReservationDTO> arrayList;
	Information information;
	public int i = 0;
	
	public InformationController(Information information){
		this.information = information;
	}
	
	public void getReservationData(ReservationDTO dto) {
		// Information 1st tab
		// 로그인 한 사람의 예약데이터를 dto에서 textField로 뿌리기
		try
		{
			String startDay = dto.getStartday();
			String year1 = startDay.substring(0, 4);
			String month1 = startDay.substring(5, 7);
			String day1 = startDay.substring(8, 10);
			information.check_in_dateT1.setText(year1);
			information.check_in_dateT2.setText(month1);
			information.check_in_dateT3.setText(day1);
			String year11 = startDay.substring(2, 4);
			information.aaa = year11 + "/" + month1 + "/" + day1;
			System.out.println("startDay : " + startDay);
			System.out.println("year1 : " + year1);
			System.out.println("month1 : " + month1);
			System.out.println("day1 : " + day1);

			String endDay = dto.getEndday();
			String year2 = endDay.substring(0, 4);
			String month2 = endDay.substring(5, 7);
			String day2 = endDay.substring(8, 10);
			information.check_out_dateT1.setText(year2);
			information.check_out_dateT2.setText(month2);
			information.check_out_dateT3.setText(day2);
			System.out.println();
			System.out.println("endDay : " + endDay);
			System.out.println("year2 : " + year2);
			System.out.println("month2 : " + month2);
			System.out.println("day2 : " + day2);

			String roomNum = dto.getRoomNum();
			// String temp_roomNum = roomNum.substring(1, 4);
			information.room_NumT.setText(roomNum);
			information.adultT.setText(dto.getAdult());
			information.childrenT.setText(dto.getChild());
			information.paywayT.setText(dto.getPayway());
			int temp = dto.getTotalpay();
			String temp_String = Integer.toString(temp);
			information.priceT.setText(temp_String);

			arrayList = information.dao.sortReservationByRoomNum(roomNum);
			System.out.println("arrayList size = " + arrayList.size());
		} 
		catch (NullPointerException e) 
		{
			System.out.println("예약현황이없음");
		}
		
	}

	public void getMemberData(MemberDTO dto) {// Information 3rd tab
		// 로그인 한 사람의 데이터를 dto 에서 textField로 뿌리기
		information.idT.setText(dto.getId());
		information.password1.setText(dto.getPw());
		information.password2.setText(dto.getPwCheck());
		information.nameT.setText(dto.getName());
		information.birthT.setText(dto.getBirth());
		information.phoneT.setText(dto.getTel());
		information.emailT.setText(dto.getEmail());
		information.addressT.setText(dto.getAddress1());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == information.accountCloseB) {
			int reply = JOptionPane.showConfirmDialog(null, "정말로 탈퇴하시겠습니까?", "회원 탈퇴", JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.YES_OPTION) {
				new InformationDAO().deleteAccount(information.loginId);
				JOptionPane.showMessageDialog(null, "계정삭제되었습니다.");
				information.idT.setText("");
				information.password1.setText("");
				information.password2.setText("");
				information.nameT.setText("");
				information.birthT.setText("");
				information.phoneT.setText("");
				information.emailT.setText("");
				information.addressT.setText("");
				// setVisible(false);
				// 로그인 화면으로
				new LoginFrame();
				information.dispose();

			}
		} else if (e.getSource() == information.editB) {// 수정 버튼
			information.password1.setEditable(true);
			information.password2.setEditable(true);
			information.nameT.setEditable(true);
			information.birthT.setEditable(true);
			information.phoneT.setEditable(true);
			information.emailT.setEditable(true);
			information.addressT.setEditable(true);

		} else if (e.getSource() == information.confirmB) {// 확인버튼
			String pw = new String(information.password1.getPassword());
			String pwCheck = new String(information.password2.getPassword());
			String name = information.nameT.getText();
			String birth = information.birthT.getText();
			String tel = information.phoneT.getText();
			String email = information.emailT.getText();
			String address = information.addressT.getText();

			if (name.length() == 0 || name == null) {
				JOptionPane.showMessageDialog(null, "이름을 입력해주세요");
				return;
			} else if (birth.length() == 0 || birth == null) {
				JOptionPane.showMessageDialog(null, "생년월일을 입력해주세요");
				return;
			} else if (!(validationDate(birth))) {
				JOptionPane.showMessageDialog(null, "유효한 날짜를 정확히 입력해주세요");
				return;
			} else if (tel.length() == 0 || tel == null) {
				JOptionPane.showMessageDialog(null, "전화번호를 입력해주세요");
				return;
			} else if (email.length() == 0 || email == null) {
				JOptionPane.showMessageDialog(null, "이메일 주소를 입력해주세요");
				return;
			} else if (address.length() == 0 || address == null) {
				JOptionPane.showMessageDialog(null, "주소를 입력해주세요");
				return;
			}

			MemberDTO dto = new MemberDTO();
			dto.setPw(pw);
			dto.setPwCheck(pwCheck);
			dto.setName(name);
			dto.setBirth(birth);
			dto.setTel(tel);
			dto.setEmail(email);
			dto.setAddress1(address);

			// DB에 update
			InformationDAO dao = InformationDAO.getInstance();
			int su = dao.updateMember(dto, information.loginId);

			// 회원정보 수정이 완료되었습니다.
			JOptionPane.showMessageDialog(null, "회원정보 수정이 완료되었습니다.");

			// TextField 비활성화
			information.password1.setEditable(false);
			information.password2.setEditable(false);
			information.nameT.setEditable(false);
			information.birthT.setEditable(false);
			information.phoneT.setEditable(false);
			information.emailT.setEditable(false);
			information.addressT.setEditable(false);

		} else if (e.getSource() == information.cancelB) {
			information.password1.setEditable(false);
			information.password2.setEditable(false);
			information.nameT.setEditable(false);
			information.birthT.setEditable(false);
			information.phoneT.setEditable(false);
			information.emailT.setEditable(false);
			information.addressT.setEditable(false);

		} else if (e.getSource() == information.toHomeB) {

			new RoomChoice(information.loginId, information.name, information.tel);
			information.dispose();
			information.reservChangeB.setEnabled(false);
			information.lastReservB.setEnabled(true);
			information.nextReservB.setEnabled(true);
			information.changeConfirmB.setEnabled(true);
			information.cancelReservB.setEnabled(true);

			information.check_in_dateT1.setEditable(true);
			information.check_in_dateT2.setEditable(true);
			information.check_in_dateT3.setEditable(true);
			information.check_out_dateT1.setEditable(true);
			information.check_out_dateT2.setEditable(true);
			information.check_out_dateT3.setEditable(true);
			information.adultT.setEditable(false);
			information.childrenT.setEditable(false);
			information.paywayT.setEditable(false);
			information.priceT.setEditable(false);

		} else if (e.getSource() == information.reservChangeB) {
			information.reservChangeB.setEnabled(false);
			information.lastReservB.setEnabled(true);
			information.nextReservB.setEnabled(true);
			information.changeConfirmB.setEnabled(true);
			information.cancelReservB.setEnabled(true);

			information.check_in_dateT1.setEditable(true);
			information.check_in_dateT2.setEditable(true);
			information.check_in_dateT3.setEditable(true);
			information.check_out_dateT1.setEditable(true);
			information.check_out_dateT2.setEditable(true);
			information.check_out_dateT3.setEditable(true);
			information.adultT.setEditable(false);
			information.childrenT.setEditable(false);
			information.paywayT.setEditable(false);
			information.priceT.setEditable(false);

		} else if (e.getSource() == information.lastReservB) {
			
			try
			{
				i = i - 1;
				if (i <= 0) {
					JOptionPane.showMessageDialog(null, "예약된 정보가 더 이상 없습니다.");
					i = 0;
				}
				ReservationDTO rdto = arrayList.get(i);

				// 로그인 한 사람의 예약데이터를 dto에서 textField로 뿌리기
				String startDay = rdto.getStartday();
				String year1 = startDay.substring(0, 4);
				String month1 = startDay.substring(5, 7);
				String day1 = startDay.substring(8, 10);
				information.check_in_dateT1.setText(year1);
				information.check_in_dateT2.setText(month1);
				information.check_in_dateT3.setText(day1);
				System.out.println("startDay : " + startDay);
				System.out.println("year1 : " + year1);
				System.out.println("month1 : " + month1);
				System.out.println("day1 : " + day1);

				String endDay = rdto.getEndday();
				String year2 = endDay.substring(0, 4);
				String month2 = endDay.substring(5, 7);
				String day2 = endDay.substring(8, 10);
				information.check_out_dateT1.setText(year2);
				information.check_out_dateT2.setText(month2);
				information.check_out_dateT3.setText(day2);
				System.out.println();
				System.out.println("endDay : " + endDay);
				System.out.println("year2 : " + year2);
				System.out.println("month2 : " + month2);
				System.out.println("day2 : " + day2);
			} 
			catch (NullPointerException e42) 
			{
			}
			

		} else if (e.getSource() == information.nextReservB) {
			try {
			
			if (arrayList.size() == i) {
				JOptionPane.showMessageDialog(null, "예약된 정보가 더 이상 없습니다.");
				return;
			} else {
				ReservationDTO rdto = arrayList.get(i);
				// 로그인 한 사람의 예약데이터를 dto에서 textField로 뿌리기
				String startDay = rdto.getStartday();
				String year1 = startDay.substring(0, 4);
				String month1 = startDay.substring(5, 7);
				String day1 = startDay.substring(8, 10);
				information.check_in_dateT1.setText(year1);
				information.check_in_dateT2.setText(month1);
				information.check_in_dateT3.setText(day1);
				System.out.println("startDay : " + startDay);
				System.out.println("year1 : " + year1);
				System.out.println("month1 : " + month1);
				System.out.println("day1 : " + day1);

				String endDay = rdto.getEndday();
				String year2 = endDay.substring(0, 4);
				String month2 = endDay.substring(5, 7);
				String day2 = endDay.substring(8, 10);
				information.check_out_dateT1.setText(year2);
				information.check_out_dateT2.setText(month2);
				information.check_out_dateT3.setText(day2);
				System.out.println();
				System.out.println("endDay : " + endDay);
				System.out.println("year2 : " + year2);
				System.out.println("month2 : " + month2);
				System.out.println("day2 : " + day2);
			}
			i++;
			
			}
			catch(NullPointerException e3){
				System.out.println("없음");
			}
		} else if (e.getSource() == information.changeConfirmB) {
			information.reservChangeB.setEnabled(true);
			information.lastReservB.setEnabled(false);
			information.nextReservB.setEnabled(false);
			information.changeConfirmB.setEnabled(false);
			information.cancelReservB.setEnabled(false);

		} else if (e.getSource() == information.cancelReservB) {
			int reply = JOptionPane.showConfirmDialog(null,
					"예약을 취소하시겠습니까?\n\n예약취소 시 예약당일 밤 12시까지 취소 시에는 수수료가 부과되지 않으며,\n이후 부터는 취소 수수료(결제액의 10%)가 부과됩니다.\n\n환불 및 취소는 객실 예약 시 선택한 결제수단으로만 가능합니다.\n\n신용카드 : 예매취소 3~5일 후 해당 카드사에서 승인 내역 취소\n무통장입금 : 반드시 예매자 본인 명의의 계좌로만 환불가능, \n환불신청일로부터 10일 이내 지정계좌로 환불 조치.",
					"예약 취소", JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.YES_OPTION) {
				
				new InformationDAO().deleteReservationData(information.loginId, information.aaa);
				JOptionPane.showMessageDialog(null, "예약이 취소되었습니다.");

				// 메인 화면으로
				new RoomChoice(information.loginId, information.name, information.tel);
				information.dispose();
			}

		}
	}

	public boolean validationDate(String checkDate) {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMdd");
			dateFormat.setLenient(false);
			dateFormat.parse(checkDate);
			return true;
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}

	}// validationDate()
}
