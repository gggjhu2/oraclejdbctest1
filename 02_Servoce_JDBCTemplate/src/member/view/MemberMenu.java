package member.view;

import java.util.List;
import java.util.Scanner;

import member.controller.MemberController;
import member.model.vo.Member;

public class MemberMenu {

	private Scanner sc= new Scanner(System.in);
	private MemberController memberController =new MemberController();;
		
	public void mainMenu() {
		String menu = "========회원 관리 프로그램========\n"
				+ "1.회원 전체 조회\n"
				+ "2.회원 아이디 조회\n"
				+ "3.회원 이름 조회\n"
				+ "4.회원 가입\n"
				+ "5.회원 회원 정보변경\n"
				+ "6.회원 탈퇴\n"
				+ "0.프로그램 끝내기\n"
				+ "============================\n"
				+ "선택 :";
		do {

			System.out.println(menu);
//			int chice =sc.nextInt();//=>숫자를입력하지않으면바로 오류가나기떄문에 스트링으로바까준다
			String choice =sc.next();
			List<Member> list =null;
			
			
			switch(choice) {
			case "1" : 
				list =memberController.selectAll();
				displayMemberList(list);
				break;
			case "2" : break;
			case "3" : break;
			case "4" : break;
			case "5" : break;
			case "6" : break;
			case "0" :
				System.out.print("정말 끝내시겠습니까(y/n) :");
				if(sc.next().charAt(0)=='y')
					return;
				break;
			default :
				System.out.println("잘못 입력하셨습니다.");
			}
				
		}while (true) ; 
	}

	
	//n행의 회원정보 출력
	private void displayMemberList(List<Member> list) {
		if(list !=null && !list.isEmpty()) {
			System.out.println("==============================================");
			for(int i = 0 ; i < list.size(); i++)
				System.out.println((i+1) +" : "+list.get(i));
			System.out.println("==============================================");
			
		}else {
			System.out.println(">>>조회된 회원 저보가 없습니다.");
		}
		
	}


}
	
