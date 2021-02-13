package member.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}