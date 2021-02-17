package member.view;

import java.util.List;
import java.util.Scanner;

import member.controller.MemberController;
import member.model.vo.Member;




public class MemberMenu {
		Scanner sc =new Scanner(System.in);
	private MemberController memberController =new MemberController();
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
		while(true) {
			System.out.print(menu);
			int choice =sc.nextInt();
			Member member =null;
			int result=0;
			String msg =null;
			List<Member>list = null;
			String memberId=null;
			switch(choice) 
			{
			
				//전체회원조회
			case 1: 
				list =memberController.selectALL();
				displayMemberList(list);
				break;
				
				//회원조회.
			case 2: 
				memberId =inputMemberId();
				member =memberController.selectOne(memberId);
				displayMember(member);
				break;
			case 3: break;
			
				//신규회원가입
			case 4:
				//1.신규 회원 정보 입력==>member객체로만드는과정.
				member =intputMember();
				System.out.println("신규회원 확인 : "+member);
				//2.controller에 회원가입 메소드 호출 =>회원가입메소드호출->DML리턴은 int리턴(처리된행의개수)
				//-->리턴된수로 정상처리유무를 확인.
				result =memberController.insertMember(member);
				//3.int에 따른 분기처리
				msg= result >0 ?"회원가입성공!" : "회원가입 실패!";
				displayMSG(msg); //<===사용자 피드백을보내는 메소드
				
				
				break;
			case 5: break;
			case 6: break;
			case 0: System.out.print("정말로 끝내시겠습니까?y/n): ");
			if(sc.next().charAt(0)=='y')
				return; //현재 메소드(mainMenu)를 호출한곳
			//==>run의 		new MemberMenu().mainMenu(); 의 .뒤에 로간다.
				break;
			default :
				System.out.println("잘못 입력하셨습니다.");
			}
			
		} 
	}
	
	
	//DB에서 조회한 1명의 회원 출력 0명일수도있음
	private void displayMember(Member member) {
		if(member ==null)
			System.out.println(">>>>>조회된 회원이 없습니다.");
		else {
			System.out.println("*************************************************");
			System.out.println(member);
			System.out.println("*************************************************");
			
		}
	}


	//조회할 회원아이디 입력
	private String inputMemberId() {
		System.out.println("조회할 아이디 입력 : ");
		return sc.next();
	}

	//DB에서 조회된 회원객체 N개를 출력
	private void displayMemberList(List<Member> list) {
		//list 로넘어온값이 0이거나 null일경우를 대비해 분기처리를 작성해준다
		if(list ==null || list.isEmpty()) {
			System.out.println(">>>>조회된 행이 없습니다.");
		}
		else {
			System.out.println("*********************************************************");
			for(Member m :list) {
				System.out.println(m);
			}
			System.out.println("**********************************************************");
		}
		
	}
	/*
	 * DML처리결과 통보용
	 * 
	 * */
	private void displayMSG(String msg) {
		System.out.println(">>>처리결과:");
	}
	
	
	
		//1.신규 회원 정보 입력==>member객체로만드는과정. 
	private Member intputMember() {
		System.out.println("새로운 회원 정보를 입력하세요.");
		Member member =new Member();
		
		System.out.print("아이디 : ");
		member.setMemberId(sc.next());//<===공백없는 문자열
		
		System.out.print("이름 : ");
		member.setMemberName(sc.next());
		
		System.out.print("비밀번호 : ");
		member.setPassword(sc.next());
		
		System.out.print("나이 : ");
		member.setMemberName(sc.next());
		
		System.out.print("성별(M/F) : ");
		member.setGender(String.valueOf(sc.next().toUpperCase().charAt(0)));
		
		System.out.print("이메일 : ");
		member.setEmail(sc.next());
		
		System.out.print("전화번호(-빼고입력해주세요): ");
		member.setPhone(sc.next());
		
		System.out.print(sc.nextLine());  //<======버퍼개행문자날리기용
		System.out.print("주소: ");
		member.setAddress(sc.nextLine());
		
		System.out.print("취미(,로나열할것): ");
		member.setHobby(sc.nextLine());
		
		
			
//		return null;  ==>이부분이 널로되어있었다..ㅠ.ㅜ
		return member;
	}
	
	
}
