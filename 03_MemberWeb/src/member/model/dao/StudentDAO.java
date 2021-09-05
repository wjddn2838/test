package member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

}
