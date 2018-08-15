package servlets.student;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Student;
import dao.DaoException;
import dao.DaoFactory;
import dao.Dao;


@WebServlet("/Index")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Dao<Student> studentDao;
     
	public void init() throws ServletException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.studentDao = daoFactory.getStudentDao();
    }
	
    public Index() {
    	super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getSession().getAttribute("user") == null) {
			response.sendRedirect("/test/user/login");
		}
		else {
		try {
			request.setAttribute("students", studentDao.all());
		} 
		catch (DaoException e) {
			request.setAttribute("error", e.getMessage());
		}
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/student/index.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
