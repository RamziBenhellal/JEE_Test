package servlets.teacher;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Student;
import beans.Teacher;
import beans.User;
import dao.Dao;
import dao.DaoException;
import dao.DaoFactory;


@WebServlet("/MyStudents")
public class MyStudents extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Dao<Teacher> teacherDao;
	private Dao<Student> studentDao;
    
	public void init() throws ServletException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.teacherDao = daoFactory.getTeacherDao();
        this.studentDao = daoFactory.getStudentDao();
    }

    public MyStudents() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("user") == null) {
			response.sendRedirect("/test/user/login");
		} else {
			try {
				Teacher teacher = teacherDao.find(((User)request.getSession().getAttribute("user")).getCode());
				request.setAttribute("students", studentDao.getwhere("classcode",teacher.gettClass().getClassCode()));
			} catch (DaoException e) {
				request.setAttribute("error", e.getMessage());
			}
			this.getServletContext().getRequestDispatcher("/WEB-INF/teacher/mystudents.jsp").forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
