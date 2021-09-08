package member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.StudentService;
import member.model.vo.Student;

/**
 * Servlet implementation class ModifyServlet
 */
@WebServlet("/member/modify")
public class ModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//userId, userPwd,userEmail,userPhone,userAddress,userHobby
		String userId = request.getParameter("user-id");
		String userPwd = request.getParameter("user-pwd");
		String userEmail = request.getParameter("user-email");
		String userPhone = request.getParameter("user-phone");
		String userAddress = request.getParameter("user-address");
		String userHobby = request.getParameter("user-hobby");
		Student student = new Student(userId, userPwd, userEmail, userPhone, userAddress, userHobby);
		int result = new StudentService().modifyStudent(student);
		if(result>0) {
			//성공하면 메인페이지로 이동
			response.sendRedirect("/index.jsp");
		}else {
			//실패하면 실패페이지로 이동
			response.sendRedirect("/member/studentError.html");
		}
	}

}
