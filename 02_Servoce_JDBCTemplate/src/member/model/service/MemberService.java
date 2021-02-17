package member.model.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import member.model.dao.MemberDao;
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
 * DAO 의 할일 및순서.
 * 3.preparedStatement 객체 생성(미완성 쿼리)
 * 3-1. ? 값대입
 * 4. 실행  : DML(executeUpdate) -> int ,DQL(executeQuery) ->ResultSet
 * * 5.자원반납(생성역순 rset -pstmt -conn)  <===자원반납은 서비스 DAO둘다에서한다.
 * 
*/

public class MemberService {
	
	private MemberDao memberDao =new MemberDao();
	
	
	 /* 	Service 의 할일 및 순서.
	 *  * 1.DriverClass등록(최초1회)
	 * 2.Connection 객체 생성 url, user ,password
	 * 2-1. 자동커밋 false 설정
	 * --Dao요청코드----------------------
	 * 4-1. ResultSet -> java 객체 옮겨담기.
	 * 6.트랜잭션처리(커밋)(DML만) COMMIT/ROLLBACK
	 * 7.자원반납(생성역순 rset -pstmt -conn)
	 * 
	 */ 
	public List<Member> selectAll(){
		String driverClass ="oracle.jdbc.OracleDriver";
		String url ="jdbc:oracle:thin:@localhost:1521:xe";
		String user ="student";
		String password="student";
		Connection conn =null;
		List<Member> list =null;

// 	Service 의 할일 및 순서.
//  1.DriverClass등록(최초1회)
		try {
			Class.forName(driverClass);

			
//2.Connection 객체 생성 url, user ,password
			conn =DriverManager.getConnection(url,user,password);
			
			
//2-1. 자동커밋 false 설정
			conn.setAutoCommit(false);

			
//--Dao요청코드----------------------
			//Connection 객체 전달 제일중요한부분이다.
			list =memberDao.selectAll(conn);
			//4-1. ResultSet -> java 객체 옮겨담기.

			
//6.트랜잭션처리(커밋)(DML만) COMMIT/ROLLBACK
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
 catch (SQLException e) {
			e.printStackTrace();
//7.자원반납(생성역순 rset -pstmt -conn)
		}finally{
			try {
				if(conn !=null)
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		
		
		
		
	 return null;
	}
}
