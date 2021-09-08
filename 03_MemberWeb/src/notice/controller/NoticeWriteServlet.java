package notice.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import notice.model.service.NoticeService;
import notice.model.vo.Notice;


/**
 * Servlet implementation class NoticeWriteServlet
 */
@WebServlet("/notice/write")
public class NoticeWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 페이지 이동용
		request.getRequestDispatcher("/WEB-INF/views/notice/noticeWrite.html").forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String subject = request.getParameter("notice-sub");
		String contents = request.getParameter("notice-content");
		HttpSession session = request.getSession();
		String writerId = (String)session.getAttribute("userId");
		//Notice 객체에 정보 셋팅
		Notice notice = new Notice();
		notice.setNoticeSubject(subject);
		notice.setNoticeContents(contents);
		notice.setWriterId(writerId);
		//Service에 넘김
		int result = new NoticeService().registerNotice(notice);
		//결과여부에 따라서 페이지 이동
		if(result>0) {
			//작성성공 후에 noticeList.jsp를 보고싶음
			//그렇다고 noriceList.jsp로 이동하는 것이 아니라
			//이미 noticeList.jsp를 보여주도록 하는 서블릿을 요청해서 볼 수 있도록
			response.sendRedirect("/notice/list");
		}else {
			request.getRequestDispatcher("/WEB-INF/views/notice/serviceFailed.html").forward(request, response);
		}
	}
}
