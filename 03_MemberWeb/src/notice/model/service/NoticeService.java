package notice.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.jsp.tagext.PageData;

import common.JDBCTemplate;
import notice.model.dao.NoticeDAO;
import notice.model.vo.Notice;
import notice.model.vo.PageDate;

public class NoticeService {

	private JDBCTemplate jdbcTemplate;
	
	public NoticeService() {
		jdbcTemplate = JDBCTemplate.getConnection();
	}
	
	public int registerNotice(Notice notice) {
		int result = 0;
		Connection conn = null;
		
		try {
			conn = jdbcTemplate.createConnection();
			result = new NoticeDAO().insertNotice(conn,notice);
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

	public PageDate printAllNotice(int currentPage) {
//		List<Notice> nList = null;
//		String pageNavi = null;
		//리턴이 2개가 안되서 class를 하난 만들어서 
		PageDate pd = new PageDate();
		Connection conn = null;
		NoticeDAO nDAO = new NoticeDAO();
		try {
			conn = jdbcTemplate.createConnection();
			pd.setNoticeList(nDAO.selectAllNotice(conn, currentPage));
			pd.setPageNavi(nDAO.getPageNavi(conn, currentPage));
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
		}
		return pd;
	}

	public Notice printOneByNo(int noticeNo) {
		Notice noticeOne = null;
		Connection conn = null;
		
		try {
			conn = jdbcTemplate.createConnection();
			noticeOne = new NoticeDAO().selectOneByNo(conn, noticeNo);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
		}
		return noticeOne;
	}

	public int removeNotice(int noticeNo) {
		//서비스가 하는 역할이 무엇?
		//1.연결을 만듦
		//2.커밋/롤백함
		int result = 0;
		Connection conn = null;
		try {
			conn = jdbcTemplate.createConnection();
			result = new NoticeDAO().deleteNotice(conn,noticeNo);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
		}
		return result;
	}

	public int modifyNotice(Notice notice) {
		int result = 0;
		Connection conn = null;
		try {
			conn = jdbcTemplate.createConnection();
			result = new NoticeDAO().updateNotice(conn, notice);
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

}
