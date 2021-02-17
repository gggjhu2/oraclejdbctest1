package member.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import member.model.vo.Member;

////DAO
////			Data Access Object
////			DB(데이터)에 접근하는 클래스라는뜼==>데이터에접근하는거는 DAO가 전부처리하도록
////			설계해야하는것이중요하다.
////
////		1.드라이버 클래스 등록(최초1회 필요)
////		2.Connection 객체 생성  <==이때필요한것 (url ,user ,pssword) 
////		3.자동 커밋여부 설정  <<==(디펄트는 셋오토커밋트루로 설정이되어 자동커밋이된다_=>우리는false로수동커밋설정)
////		4.prepaedStatement객체 생성. =>이떄필요한것이 =>미완성쿼리 (값대입이안된상태의 쿼리) 및 값대입
////		5.Statement 객체 실행 (DB에 쿼리 요청)
////		6.응답에대한 처리 DML 이냐 DQL이냐에따라 달라진다.
////			-DML :INT리턴
/////			-DQL ResultSet을 리턴 ->자바 객체로 번환과정필요.
////		7.트랜잭션처리 (DML 일격우만 한다)
////		8.사용자원 반납.(생성의 역순)
//
//public class MemberDao {
//
//	public int insertMember(Member member) {
//		// DAO 작성
////
////	1.드라이버 클래스 등록(최초1회 필요)
//		String driverClass = "oracle.jdbc.OracleDriver";
//		try {
//			Class.forName(driverClass); // <==체크드익셉션으로서 예외처리를 강제한다.
//			
//			
////	2.Connection 객체 생성  <==이때필요한것 ( url ,user , password)
//			// db드라이버타입 @ 아이피도메인 :포트번호 : db이름
//			String url = "jdbc:oracle:thin:@localhost:1521:xe";
//			String user = "student"; // <==대소문자구분 x
//			String password = "student";// <==대소문자 구분 o
//			Connection conn = DriverManager.getConnection(url, user, password);
//			// -->이것역시 sql 디비 관련 최상위 예외를 던진다.
//
//			
////	3.자동 커밋여부 설정  <<==(디펄트는 셋오토커밋트루로 설정이되어 자동커밋이된다_=>우리는false로수동커밋설정)
//		 conn.setAutoCommit(false); //<==DQL을 우리가 직접 할수있게 자동커밋을 해제한다.
//			
//			
////	4.prepaedStatement객체 생성. =>이떄필요한것이 =>미완성쿼리 (값대입이안된상태의 쿼리) 및 값대입
//		 String sql ="insert int member values(?,?,?,?,?,?,?,?,?,default)";
//		 									//이부분에 값이 들어가있지않아서 이부분을 미완성쿼리라고한다.
//		 PreparedStatement pstmt =conn.prepareStatement(sql);
//		 pstmt.setString(1,member.getMemberId());
//		 pstmt.setString(2,member.getPassword());
//		 pstmt.setString(3,member.getMemberName());
//		 pstmt.setString(4,member.getGender());
//		 pstmt.setInt(5,member.getAge());
//		 pstmt.setString(6,member.getEmail());
//		 pstmt.setString(7,member.getPhone());
//		 pstmt.setString(8,member.getAddress());
//		 pstmt.setString(9,member.getHobby());
//		 
//		 
////	5.Statement 객체 실행 (DB에 쿼리 요청)
////	6.응답에대한 처리 DML 이냐 DQL이냐에따라 달라진다.
//		 int result =pstmt.executeUpdate(); //<=dml 일떄 update , dql일때 는딴거
//		 	//1이 들어오면 정상처리된거다.
//
//		 
//		 //		-DML :INT리턴
/////			-DQL ResultSet을 리턴 ->자바 객체로 번환과정필요.
////	7.트랜잭션처리 (DML 일격우만 한다)
//		 if(result >0)
//			 conn.commit();
//		 else 
//			 conn.rollback();
//		 
//		 
//		 
//		 //--==---------예외 처리 캐치문============
//		} catch (ClassNotFoundException e) {
//			// ojdbc6.jar 익셉션 발생시 프로젝트 연동실패했다는것을 알수있따.
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		
//		finally {
////	8.사용자원 반납.(생성의 역순)
//			pstmt.close();
//			
//			conn.close();
//	}
//		
//		return result;
//
//}
//}
//=====================================지역변수 변경으로 위치변경해서 새로다시 자리를잡아보겠다.
public class MemberDao {
	String driverClass = "oracle.jdbc.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String user = "student";
	String password = "student";
	
	public int insertMember(Member member) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "insert into member values(?, ?, ?, ?, ?, ?, ?, ?, ?, default)";
		int result = 0;
		
		try {
//		 * 1. 드라이버클래스 등록(최초1회)
			Class.forName(driverClass);
			
//		 * 2. Connection객체 생성(url, user, password)
			//:port:oracle version
			conn = DriverManager.getConnection(url,user,password);
			
//		 * 3. 자동커밋여부 설정(DML) true(기본값) / false -> app에서 직접 트랜잭션 제어
			conn.setAutoCommit(false);
			
//		 * 4. PreparedStatement 객생성(미완성 쿼리) 및 값대입
			//값이 들어갈 자리를 물음표(?)로 
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, member.getGender());
			pstmt.setInt(5, member.getAge());
			pstmt.setString(6, member.getEmail());
			pstmt.setString(7, member.getPhone());
			pstmt.setString(8, member.getAddress());
			pstmt.setString(9, member.getHobby());
			
//		 * 5. Statement 객체 실행. 
//		 * 6. 응답처리 DML : int리턴, DQL : ResultSet리턴 -> 자바객체로 전환
			result = pstmt.executeUpdate(); //dml인 경우
			//정상적으로 리턴됐으면 1리턴 or 오류발생
			//executeQuery() : DQL인 경우
			
//		 * 7. 트랜잭션처리(DML)
			if(result > 0)
				conn.commit();
			else
				conn.rollback();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
//		 * 8. 자원반납(생성의 역순)
			try {
				if(pstmt !=null)
			pstmt.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			try {
				if(conn !=null)
			conn.close();	
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
			
		}
	
		return result;
	}

	public List<Member> selectALL() {
		Connection conn =null;
		PreparedStatement pstmt =null;
		//리절트셋 인터페이스
		ResultSet rset =null;
		String sql ="select * from member order by enroll_date desc";
		List<Member> list =null;
		
		//DQL을 실행해본다.
	try {
//	1.드라이버 클래스 등록(최초1회 필요)  ==>위에서했지만 SELECTALL이먼저호출될경우를 대비해한번더 써준다.
		Class.forName(driverClass);
		
//	2.Connection 객체 생성  <==이때필요한것 (url ,user ,pssword) =>dql 단순조회
		conn =DriverManager.getConnection(url,user,password);
		
//	3.자동 커밋여부 설정  <<==(디펄트는 셋오토커밋트루로 설정이되어 자동커밋이된다_=>우리는false로수동커밋설정)
		//dql이기떄문에 필요없어서 작성안할것이다.

//	4.prepaedStatement객체 생성. =>이떄필요한것이 =>미완성쿼리 (값대입이안된상태의 쿼리) 및 값대입
		pstmt =conn.prepareStatement(sql); //=>이sql은 채워넣을값이없기에 ???이런식으로 만들어줄필요가없다.
		
//	5.Statement 객체 실행 (DB에 쿼리 요청)
 		rset =pstmt.executeQuery();
		
//	6.응답에대한 처리 DML 이냐 DQL이냐에따라 달라진다.==>dql은여기가 까다롭다.
//		-DML :INT리턴
//			-DQL ResultSet을 리턴 ->자바 객체로 번환과정필요.
			//다음행 존재여부 리턴, 커서가 다음행에 접근.
		list=new ArrayList<>();
		while(rset.next()) {
			//컬럼명은 대소문자를 구분하지 않는다.
			String memberId =rset.getString("member_id");
			String password =rset.getString("password");
			String memberName=rset.getString("member_name");
			String gender =rset.getString("gender");
			int age =rset.getInt("age");
			String email=rset.getString("email");
			String phone=rset.getString("phone");
			String address=rset.getString("address");
			String hobby=rset.getString("hobby");
			Date enrollDate=rset.getDate("enroll_Date");
			
			Member member =new Member(memberId, password, memberName,
					gender, age, email, phone, address, hobby, enrollDate);
			list.add(member);
		}
		
//	7.트랜잭션처리 (DML 일격우만 한다) ==>DQL이기떄문에 트렌젝션은 안해도된다.
		
		
	} catch (ClassNotFoundException e) {

		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	
		
		//자원반납시작은 파이널리다
	}finally {
		//	8.사용자원 반납.(생성의 역순)
		 try {
			 if(rset !=null)
			rset.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 try {
			 if(pstmt !=null)
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 try {
			 if(conn !=null)
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		//이부분을 수정안해주면 안된다 너무 중요하다 참고로나도 이문제때문에 고생했다.ㅠ.ㅜ
		return list;
	}

	
	//dql이다 쿼리를 뭐라고써야될까? 위에작성한 구문을 복사해서 수정 해사용한다.
	public Member selectOne(String memberId) {
		Connection conn =null;
		PreparedStatement pstmt =null;
		//리절트셋 인터페이스
		ResultSet rset =null;
		//이부분은 수정해준다
//		String sql ="select * from member order by enroll_date desc";
		String sql ="select * from member where member_id =?";
		
		//이부분도수정해준다
//		List<Member> list =null;
		Member member =null;
		
		
		//DQL을 실행해본다.
	try {
//	1.드라이버 클래스 등록(최초1회 필요)  ==>위에서했지만 SELECTALL이먼저호출될경우를 대비해한번더 써준다.
		Class.forName(driverClass);
		
//	2.Connection 객체 생성  <==이때필요한것 (url ,user ,pssword) =>dql 단순조회
		conn =DriverManager.getConnection(url,user,password);
		
//	3.자동 커밋여부 설정  <<==(디펄트는 셋오토커밋트루로 설정이되어 자동커밋이된다_=>우리는false로수동커밋설정)
		//dql이기떄문에 필요없어서 작성안할것이다.

//	4.prepaedStatement객체 생성. =>이떄필요한것이 =>미완성쿼리 (값대입이안된상태의 쿼리) 및 값대입
		//이부분수정해준다
//		pstmt =conn.prepareStatement(sql); //=>이sql은 채워넣을값이없기에 ???이런식으로 만들어줄필요가없다.
		pstmt =conn.prepareStatement(sql); 
		pstmt.setNString(1, memberId);
//	5.Statement 객체 실행 (DB에 쿼리 요청)
 		rset =pstmt.executeQuery();
		
//	6.응답에대한 처리 DML 이냐 DQL이냐에따라 달라진다.==>dql은여기가 까다롭다.
//		-DML :INT리턴
//			-DQL ResultSet을 리턴 ->자바 객체로 번환과정필요.
			//다음행 존재여부 리턴, 커서가 다음행에 접근.
 		
 		//이부분은필요없는행이다
//		list=new ArrayList<>();
 		
 		
		while(rset.next()) {
			//컬럼명은 대소문자를 구분하지 않는다.
			
					//멤버아이디가위에있기때문에오류가나서 수정해준다,매개변수가 선언되어있기에 다시선언할수없는것이다.
//			String memberId =rset.getString("member_id");
			memberId =rset.getString("member_id");
			
			
			String password =rset.getString("password");
			String memberName=rset.getString("member_name");
			String gender =rset.getString("gender");
			int age =rset.getInt("age");
			String email=rset.getString("email");
			String phone=rset.getString("phone");
			String address=rset.getString("address");
			String hobby=rset.getString("hobby");
			Date enrollDate=rset.getDate("enroll_Date");
			
			//이부분도 위에서 매개변수에 선언이되어있기떄문에 선언없이 값대입만해주는것으로 수정해준다
//			Member member =new Member(memberId, password, memberName,
//					gender, age, email, phone, address, hobby, enrollDate);
			member =new Member(memberId, password, memberName,
					gender, age, email, phone, address, hobby, enrollDate);
		}
		
//	7.트랜잭션처리 (DML 일격우만 한다) ==>DQL이기떄문에 트렌젝션은 안해도된다.
		
		
	} catch (ClassNotFoundException e) {

		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	
		
		//자원반납시작은 파이널리다
	}finally {
		//	8.사용자원 반납.(생성의 역순)
		 try {
			 if(rset !=null)
			rset.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 try {
			 if(pstmt !=null)
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 try {
			 if(conn !=null)
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		//이부분을 수정안해주면 안된다 너무 중요하다 참고로나도 이문제때문에 고생했다.ㅠ.ㅜ
		return member;
	}
	}
