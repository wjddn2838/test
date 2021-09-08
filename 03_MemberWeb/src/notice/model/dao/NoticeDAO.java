package notice.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import notice.model.vo.Notice;

public class NoticeDAO {

	public int insertNotice(Connection conn, Notice notice) {
		PreparedStatement pstmt = null;
		int result = 0;
		String qur = "INSERT INTO NOTICE VALUES(SEQ_NOTICE.NEXTVAL,?,?,?,DEFAULT)";
		
		try {
			pstmt = conn.prepareStatement(qur);
			pstmt.setString(1, notice.getNoticeSubject());
			pstmt.setString(2, notice.getNoticeContents());
			pstmt.setString(3, notice.getWriterId());
			//쿼리문 실행
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public List<Notice> selectAllNotice(Connection conn, int currentPage) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String qur = "SELECT * FROM(SELECT ROW_NUMBER() OVER(ORDER BY NOTICE_NO DESC) AS NUM, NOTICE_NO, NOTICE_SUBJECT, NOTICE_CONTENTS, WRITER_ID, REG_DATE FROM NOTICE)WHERE NUM BETWEEN ? AND ?";
		List<Notice> nList = null;
		try {
			pstmt = conn.prepareStatement(qur);//연결을 통해 statement 객체 생성
			int viewCountPerPage = 10;
			int start = currentPage*viewCountPerPage - 9;
			int end = currentPage * viewCountPerPage;
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();//쿼리문 실행후 rset으로 결과값 받기
			nList = new ArrayList<Notice>();
			while(rset.next()) {//rset에 데이터가 없을때 
				Notice notice = new Notice();
				notice.setNoticeNo(rset.getInt("NOTICE_NO"));
				notice.setNoticeSubject(rset.getString("NOTICE_SUBJECT"));
				notice.setNoticeContents(rset.getString("NOTICE_CONTENTS"));
				notice.setWriterId(rset.getString("WRITER_ID"));
				notice.setRegDate(rset.getDate("REG_DATE"));
				nList.add(notice);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return nList;
	}
	public String getPageNavi(Connection conn, int currentPage) {
		//1 2 3 4 5
		//1 2 3 4 5 6 7 8 9 10
		//1 2 3 4 5 6 7 8 9 10 11 12 13 14 15
		// 1페이지 당 10개씩보여주는데 125개의 게시물
		// 13페이지가 있어야함
		// 1 2 3 4 5
		// 6 7 8 9 10
		int pageCountPerView = 5;
		int viewTotalCount = totalCount(conn);
		int viewCountPerPage = 10;
		int pageTotalCount = 0;
		int pageTotalCountMod = viewTotalCount / viewCountPerPage;
		if(pageTotalCountMod > 0) {
			pageTotalCount = viewTotalCount / viewCountPerPage + 1;
		}else {
			pageTotalCount = viewTotalCount / viewCountPerPage;
		}
		// 1. -> 1 2 3 4 5
		// 2. -> 1 2 3 4 5
		// 3. -> 1 2 3 4 5
		// ...
		// 6. -> 6 7 8 9 10
		// 7. -> 6 7 8 9 10
		// 8. -> 6 7 8 9 10
		int startNavi = ((currentPage - 1) / pageCountPerView) * pageCountPerView + 1;
		int endNavi = startNavi + pageCountPerView - 1;
		boolean needPrev = true;
		boolean needNext = true;
		if(startNavi == 1) {
			needPrev = false;
		}
		if(endNavi == pageTotalCount) {
			needNext = false;
		}
		StringBuilder sb = new StringBuilder();//텍스트가 쌓아주는거?
		if(needPrev) {
			sb.append("<a href='/notice/list?currentPage="+ (startNavi-1) +"'> [이전] </a>");
		}
		for(int i = startNavi; i<=endNavi;i++) {
			if(i==currentPage) {
				sb.append(i);
			}else {
				sb.append("<a href='/notice/list?currentPage="+ i +"'>"+ i +"</a>");
				
			}
		}
		if(needNext) {
			sb.append("<a href='/notice/list?currentPage="+ (endNavi+1) +"'> [다음] </a>");
		}
		return sb.toString();
	}
	//게시물 갯수를 알기위해 아래 메소드 필요
	public int totalCount(Connection conn) {
		int totalValue = 0;
		Statement stmt = null;
		ResultSet rset = null;
		String qur = "SELECT COUNT(*) AS TOTALCOUNT FROM NOTICE";
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(qur);
			if(rset.next()) {
				totalValue = rset.getInt("TOTALCOUNT");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(stmt);
		}
		return totalValue;
	}
	
	public Notice selectOneByNo(Connection conn, int noticeNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Notice noticeOne = null;
		String qur = "SELECT * FROM NOTICE WHERE NOTICE_NO = ?";
		try {
			pstmt = conn.prepareStatement(qur);
			pstmt.setInt(1, noticeNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				noticeOne = new Notice();
				noticeOne.setNoticeNo(rset.getInt("NOTICE_NO"));
				noticeOne.setNoticeSubject(rset.getString("NOTICE_SUBJECT"));
				noticeOne.setNoticeContents(rset.getString("NOTICE_CONTENTS"));
				noticeOne.setWriterId(rset.getString("WRITER_ID"));
				noticeOne.setRegDate(rset.getDate("REG_DATE"));	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return noticeOne;
	}

	public int deleteNotice(Connection conn, int noticeNo) {
		//DAO(Store)가 하는역할은
		//1.쿼리문 실행
		//2.결과값 받기
		PreparedStatement pstmt = null;
		int result = 0;
		String qur = "DELETE FROM NOTICE WHERE NOTICE_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(qur);
			pstmt.setInt(1, noticeNo);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int updateNotice(Connection conn, Notice notice) {
		PreparedStatement pstmt = null;
		int result = 0;
		String qur = "UPDATE NOTICE SET NOTICE_SUBJECT = ?,NOTICE_CONTENTS = ? WHERE NOTICE_NO = ?";
		try {
			pstmt = conn.prepareStatement(qur);
			pstmt.setString(1, notice.getNoticeSubject());
			pstmt.setString(2, notice.getNoticeContents());
			pstmt.setInt(3, notice.getNoticeNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	
	
	
	
	
	
	
	
}
