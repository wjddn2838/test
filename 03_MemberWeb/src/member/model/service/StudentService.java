package member.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

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

	public Student printOneLogin(String userId, String userPwd) {
		Student student = null;
		Connection conn = null;
		try {
			conn = jdbcTemplate.createConnection();
			student = new StudentDAO().selectOneLogin(conn, userId, userPwd);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
		}
		return student;
	}

	public Student printOneById(String studentId) {
		Student student = null;
		Connection conn = null;
		try {
			conn = jdbcTemplate.createConnection();//연결생성함
			student = new StudentDAO().selectOneById(conn, studentId);//연결 넘겨줌
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
		}
		return student;
	}

	public int deleteStudent(String studentId) {
		int result = 0;
		Connection conn = null;
		try {
			conn = jdbcTemplate.createConnection();
			result = new StudentDAO().deleteMember(conn, studentId);
			if(result>0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
		}
		return result;
	}

	public int modifyStudent(Student student) {
		int result = 0;
		Connection conn = null;
		try {
			conn = jdbcTemplate.createConnection();
			result = new StudentDAO().updateMember(conn, student);
			//성공,실패여부에 따라커밋/롤백
			if(result>0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
		}
		return result;
	}

	public List<Student> printAllList() {
		Connection conn = null;
		List<Student> sList = null;

		try {
			conn = jdbcTemplate.createConnection();
			sList = new StudentDAO().selectAllList(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
		}

		return sList;
	}	
}










