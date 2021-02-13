package member.controller;

import member.model.dao.MemberDao;
import member.model.vo.Member;


/*		--CONTROLLER-- <=중요하다.
 * 		MVC패턴에서 제일중요한 역할을하는것은 CONTROLLER이다.
 * 		==
 * 		MVC 패턴의 시작점이자 전체흐름을 제어하는 역할을한다.

 *		VIEW 단으로부터 요청을 받아서 DAO에 다시요청 .
 *		->그결과를 VIEW 단에 다시 전달해주는 역할을한다.
 */

public class MemberController {
	private MemberDao memberDao =new MemberDao();

	
	
	public int insertMember(Member member) {
//			int memberDAO.insertMember(member);//<==DML명령어는 몇행인지 정수형을 리턴한다.
		return memberDao.insertMember(member);
	}
	
}
