package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTemplate {

	// 싱글톤 패턴 적용
	private static JDBCTemplate instance;
	
	public JDBCTemplate() {
		// 드라이버 등록 
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	// 싱글톤 패턴 - 메소드
	public static JDBCTemplate getConnection() {
		if(instance == null) {
			instance = new JDBCTemplate();
		}
		return instance;
	}
	
	// 연결생성
	public Connection createConnection() throws SQLException {
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String user = "KHHUMAN";
		String pwd = "KHHUMAN";
		Connection conn = DriverManager.getConnection(url, user, pwd);
		conn.setAutoCommit(false);//자동으로 커밋을 꺼둔 상태
		return conn;
	}
	
	// 연결닫기
	public static void close(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) {
				conn.close();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 커밋
	public static void commit(Connection conn) {
		try {
			conn.commit();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	// 롤백
	public static void rollback(Connection conn) {
		try {
			conn.rollback();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 자원해제  오버로딩으로 같은 메소드로 사용가능
	public static void close(ResultSet rset) {
		try {
			if(rset != null && !rset.isClosed()) {				
			rset.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Statement stmt) {
		try {
			if(stmt != null && !stmt.isClosed()) {				
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
