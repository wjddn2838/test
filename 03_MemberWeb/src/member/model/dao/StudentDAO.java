package member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import common.JDBCTemplate;
import member.model.vo.Student;

public class StudentDAO {
	
	public StudentDAO() {}

	public int insertStudent(Connection conn, Student student) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "INSERT INTO STUDENT VALUES(?,?,?,?,?,?,?,?,?,DEFAULT)";
		try {
			pstmt = conn.prepareStatement(query);//연결을 통해서 pstmt 객체 생성
			pstmt.setString(1, student.getStudentId());//위치홀더 작성시 써야함 무조건 앞에는 위치홀더 위치 뒤에는 
			pstmt.setString(2, student.getStudentPwd());
			pstmt.setString(3, student.getStudentName());
			pstmt.setString(4, student.getStudentGender());
			pstmt.setInt(5, student.getStudentAge());
			pstmt.setString(6, student.getStudentEmail());
			pstmt.setString(7, student.getStudentPhone());
			pstmt.setString(8, student.getStudentAddress());
			pstmt.setString(9, student.getStudentHobby());
			//쿼리문 실행 메소드 
			result = pstmt.executeUpdate();//쿼리문 실행 메소드
			//리턴값이 쿼리문이 성공한 행의 갯수
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public Student selectOneLogin(Connection conn, String userId, String userPwd) {
		//쿼리문 실행을 위해 무엇을 준비하나
		//연결을 통해 생성하는 = Statement    이거와 PreparedStatement의 차이 
		//PreparedStatement는 위치홀더 사용 Statement는 쿼리문 바로 사용
		//쿼리문 실행 결과를 받기 위해 = ResultSet
		Statement stmt = null;
		ResultSet rset = null;
		Student student = null;
		String qur = "SELECT * FROM STUDENT WHERE STUDENT_ID ='"+userId+"'AND STUDENT_PWD ='"+userPwd+"'";
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(qur);
			if(rset.next()) {
				student = new Student();
//				student.setStudentId(studentId);
				student.setStudentId(rset.getString("STUDENT_ID"));
				student.setStudentName(rset.getString("STUDENT_NAME"));
				student.setEnrollDate(rset.getDate("ENROLL_DATE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(stmt);
		}
		return student;
	}

	public Student selectOneById(Connection conn, String studentId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Student student = null;
		String qur = "SELECT * FROM STUDENT WHERE STUDENT_ID = ?";
		
		try {
			pstmt = conn.prepareStatement(qur);
			pstmt.setString(1, studentId);
			//쿼리문 실행
			rset = pstmt.executeQuery();
			//결과값 받아서 student객체에 셋팅하기
			if(rset.next()) {
				student = new Student();
				student.setStudentId(rset.getString("STUDENT_ID"));
				student.setStudentPwd(rset.getString("STUDENT_PWD"));
				student.setStudentName(rset.getString("STUDENT_NAME"));
				student.setStudentGender(rset.getString("STUDENT_GENDER"));
				student.setStudentAge(rset.getInt("STUDENT_AGE")); //int형
				student.setStudentEmail(rset.getString("STUDENT_EMAIL"));
				student.setStudentPhone(rset.getString("STUDENT_PHONE"));
				student.setStudentAddress(rset.getString("STUDENT_ADDRESS"));
				student.setStudentHobby(rset.getString("STUDENT_HOBBY"));
				student.setEnrollDate(rset.getDate("ENROLL_DATE"));//date형
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}		
		return student;
	}

	public int deleteMember(Connection conn, String studentId) {
		PreparedStatement pstmt = null;
		int result = 0;
		String qur = "DELETE FROM STUDENT WHERE STUDENT_ID = ?";
		
		try {
			pstmt = conn.prepareStatement(qur);
			pstmt.setString(1, studentId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int updateMember(Connection conn, Student student) {
		int result = 0;
		//PreparedStatement 객체
		//쿼리문 작성
		//쿼리문 실행
		//자원해제
		return result;
	}
}
