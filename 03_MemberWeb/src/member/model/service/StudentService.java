package member.model.service;

import java.sql.Connection;
import java.sql.SQLException;

import common.JDBCTemplate;
import member.model.dao.StudentDAO;
import member.model.vo.Student;

public class StudentService {

	private JDBCTemplate jdbcTemplate;	
	
	public StudentService() {
		jdbcTemplate = JDBCTemplate.getConnection();
		//생성자에서 JDBCTemplate 객체를 만들어준다 -> 드라이버 등록이 됨 -> 연결 생성 준비 완료
	}
	
	public int registerStudent(Student student) {
		int result = 0;
		//연결 생성
		Connection conn = null;
		
		try {
			conn = jdbcTemplate.createConnection();
			result = new StudentDAO().insertStudent(conn, student);
			
			if(result >0) { //성공 여부에 따라 Commit, Rollback해줘야함.
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn); //DB 연결해제
		}
		return result;
	}	
}
