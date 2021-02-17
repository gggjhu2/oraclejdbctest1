package member.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import member.model.vo.Member;
/*기존 dao에서 하던 업무 순서
 * 1.DriverClass등록(최초1회)
 * 2.Connection 객체 생성 url, user ,password
 * 2-1. 자동커밋 false 설정
 * 3.preparedStatement 객체 생성(미완성 쿼리)
 * 3-1. ? 값대입
 * 4. 실행  : DML(executeUpdate) -> int ,DQL(executeQuery) ->ResultSet
 * 4-1. ResultSet -> java 객체 옮겨담기.
 * 5.트랜잭션처리(커밋)(DML만) COMMIT/ROLLBACK
 * 6.자원반납(생성역순 rset -pstmt -conn)
 * 기존 dao에서 너무많은 기능을 처리하는점을 service에게 업무분담을하기위해 다음과같이 역할을나눈다.
 * 
 * ================================>
 * 	Service 의 할일 및 순서.
 *  * 1.DriverClass등록(최초1회)
 * 2.Connection 객체 생성 url, user ,password
 * 2-1. 자동커밋 false 설정
 * --Dao요청코드----------------------
 * 4-1. ResultSet -> java 객체 옮겨담기.
 * 6.트랜잭션처리(커밋)(DML만) COMMIT/ROLLBACK
 * 7.자원반납(생성역순 rset -pstmt -conn)
 * 
 * 
 * ****DAO**** 의 할일 및순서.
 * 3.preparedStatement 객체 생성(미완성 쿼리)
 * 3-1. ? 값대입
 * 4. 실행  : DML(executeUpdate) -> int ,DQL(executeQuery) ->ResultSet
 * * 5.자원반납(생성역순 rset -pstmt -conn)  <===자원반납은 서비스 DAO둘다에서한다.
 * 
*/


public class MemberDao {
	
	public List<Member> selectAll(Connection conn) {
	PreparedStatement pstmt =null;
	ResultSet rset =null;
	String sql ="select * from member order by enroll_date desc";
	List<Member>list =null;
	
//DAO 의 할일 및순서.
//3.preparedStatement 객체 생성(미완성 쿼리)
	try {
		pstmt =conn.prepareStatement(sql);
//3-1. ? 값대입
		
		
//4. 실행  : DML(executeUpdate) -> int ,DQL(executeQuery) ->ResultSet
		rset =pstmt.executeQuery();
//4-1. ResultSet => Java 객체 옮겨담기
		list =new ArrayList<>();
		while(rset.next()) {
			String memberId =rset.getString("member_id");
			String password=rset.getString("password");
			String memberName =rset.getString("member_name");
			String gender =rset.getString("gender");
			int age =rset.getInt("age");
			String email =rset.getString("email");
			String phone =rset.getString("phone");
			String address =rset.getString("address");
			String hobby =rset.getString("hobby");
			Date enrollDate=rset.getDate("enroll_date");
			Member member =new Member(memberId, password, memberName, gender, age, email, phone, address, hobby, enrollDate);
			list.add(member);
		}
		
		
		
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	finally {
		//5.자원반납(생성역순 rset -pstmt -conn)  <===자원반납은 서비스 DAO둘다에서한다.
		try {
			if(rset !=null)
			rset.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(pstmt !=null)
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		return list;
	}
	
	

}
