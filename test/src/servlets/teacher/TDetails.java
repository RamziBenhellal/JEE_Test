package servlets.teacher;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Teacher;
import beans.User;
import dao.Dao;
import dao.DaoException;
import dao.DaoFactory;

/**
 * Servlet implementation class TDetails
 */
@WebServlet("/TDetails")
public class TDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    private Dao<Teacher> teacherDao;
    private Dao<User> userDao;
    
	public void init() throws ServletException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.teacherDao = daoFactory.getTeacherDao();
        this.userDao = daoFactory.getUserDao();
    }
 
    public TDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("user") == null) {
			response.sendRedirect("/test/user/login");
		} else {
			String id = request.getParameter("id");
			try {
				request.setAttribute("teacher", teacherDao.find(id));
			} catch (DaoException e) {
				request.setAttribute("error", e.getMessage());
			}
			this.getServletContext().getRequestDispatcher("/WEB-INF/teacher/details.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		try {
			teacherDao.delete(id);
			userDao.delete(id);
			request.setAttribute("teachers", teacherDao.all());
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/teacher/index.jsp").forward(request, response);
	}

}
