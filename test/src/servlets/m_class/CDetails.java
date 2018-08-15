package servlets.m_class;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Student;
import beans.Teacher;
import dao.Dao;
import dao.DaoException;
import dao.DaoFactory;


@WebServlet("/CDetails")
public class CDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private Dao<beans.Class> classDao;
	private Dao<Teacher> teacherDao;
	private Dao<Student> studentDao;

	public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.classDao = daoFactory.getClassDao();
		this.teacherDao = daoFactory.getTeacherDao();
		this.studentDao = daoFactory.getStudentDao();
	}

 
    public CDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("user") == null) {
			response.sendRedirect("/test/user/login");
		}
		else {
		String id = request.getParameter("id");
		try {
	
			request.setAttribute("c", classDao.find(id));
			request.setAttribute("teachers", teacherDao.getwhere("classcode", classDao.find(id).getClassCode()));
			request.setAttribute("students", studentDao.getwhere("classcode", classDao.find(id).toString()));
		} catch (DaoException e) {
			request.setAttribute("error", e.getMessage());
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/class/details.jsp").forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		try {
			classDao.delete(id);
			request.setAttribute("classes", classDao.all());
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/class/index.jsp").forward(request, response);
	}

}
