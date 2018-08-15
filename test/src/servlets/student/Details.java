package servlets.student;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Mark;
import beans.Module;
import beans.Student;
import dao.DaoException;
import dao.DaoFactory;
import dao.Dao;



@WebServlet("/Details")
public class Details extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Dao<Student> studentDao;
	private Dao<Module> moduleDao;
	private Dao<Mark> markDao;
    
	public void init() throws ServletException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.studentDao = daoFactory.getStudentDao();
        this.moduleDao = daoFactory.getModuleDao();
        this.markDao = daoFactory.getMarkDao();
    }

    public Details() {
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
			request.setAttribute("student", studentDao.find(id));
			request.setAttribute("modules",moduleDao.getwhere("specialty",studentDao.find(id).getSpecialty()));
			request.setAttribute("marks",markDao.getwhere("serialnumber",id));
		} catch (DaoException e) {
			request.setAttribute("error", e.getMessage());
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/student/details.jsp").forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		try {
			studentDao.delete(id);
			request.setAttribute("students", studentDao.all());
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/student/index.jsp").forward(request, response);
	}
	
	

}
