package servlets.teacher;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Teacher;
import dao.Dao;
import dao.DaoException;
import dao.DaoFactory;


@WebServlet("/TIndex")
public class TIndex extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private Dao<Teacher> teacherDao;
    
	public void init() throws ServletException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.teacherDao = daoFactory.getTeacherDao();
    }

    public TIndex() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("user") == null) {
			response.sendRedirect("/test/user/login");
		} else {
			try {
				request.setAttribute("teachers", teacherDao.all());
			} catch (DaoException e) {
				request.setAttribute("error", e.getMessage());
			}
			this.getServletContext().getRequestDispatcher("/WEB-INF/teacher/index.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
