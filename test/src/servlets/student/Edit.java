package servlets.student;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanException;
import beans.Student;
import dao.Dao;
import dao.DaoException;
import dao.DaoFactory;
import forms.StudentForms;

/**
 * Servlet implementation class Edit
 */
@WebServlet("/Edit")
public class Edit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Dao<Student> studentDao;
	private Dao<beans.Class> classDao;

	public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.studentDao = daoFactory.getStudentDao();
		this.classDao = daoFactory.getClassDao();
	}

	public Edit() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		if (request.getSession().getAttribute("user") == null) {
			response.sendRedirect("/test/user/login");
		} else {
			String id = request.getParameter("id");
			try {
				request.setAttribute("student", studentDao.find(id));
				request.setAttribute("classes",classDao.all());
			} catch (DaoException e) {
				request.setAttribute("error", e.getMessage());
			}
			this.getServletContext().getRequestDispatcher("/WEB-INF/student/edit.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		StudentForms studentForm = new StudentForms();
		Student student;
		try {
			beans.Class sClass = classDao.find(request.getParameter("sclass"));
			student = studentForm.check(request,sClass);
			// open database module
			studentDao.update(student);
			response.sendRedirect("/test/student/index");
		} catch (DaoException e) {
			request.setAttribute("error", e.getMessage());
			this.doGet(request, response);
		} catch (BeanException e) {
			request.setAttribute("error", e.getMessage());
			this.doGet(request, response);
		}

	}

}
