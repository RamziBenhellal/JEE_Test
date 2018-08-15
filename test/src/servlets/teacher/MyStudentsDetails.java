package servlets.teacher;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanException;
import beans.Mark;
import beans.Module;
import beans.Student;
import beans.Teacher;
import beans.User;
import dao.Dao;
import dao.DaoException;
import dao.DaoFactory;
import forms.MarkForms;


@WebServlet("/MyStudentsDetails")
public class MyStudentsDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Dao<Student> studentDao;
	private Dao<Teacher> teacherDao;
	private Dao<Module> moduleDao;
	private Dao<Mark> markDao;
    
	public void init() throws ServletException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.studentDao = daoFactory.getStudentDao();
        this.teacherDao = daoFactory.getTeacherDao();
        this.moduleDao = daoFactory.getModuleDao();
        this.markDao = daoFactory.getMarkDao();
    }   

    public MyStudentsDetails() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("user") == null) {
			response.sendRedirect("/test/user/login");
		} else {
			String id = request.getParameter("id");
			try {
				Teacher teacher = teacherDao.find(((User)request.getSession().getAttribute("user")).getCode());
				request.setAttribute("student", studentDao.find(id));
				request.setAttribute("module", moduleDao.find(teacher.getModule().toString()));
				request.setAttribute("marks", markDao.getwhere("serialnumber",studentDao.find(id).getSerialNumber(),"modulecode",moduleDao.find(teacher.getModule().toString()).toString()));
			} catch (DaoException e) {
				request.setAttribute("error", e.getMessage());
			}
			this.getServletContext().getRequestDispatcher("/WEB-INF/teacher/mystudentsdetails.jsp").forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MarkForms markForm = new MarkForms();
		Mark mark;
		try {
			Student student = studentDao.find(request.getParameter("id_1"));
			Module module = moduleDao.find(request.getParameter("id_2"));
	
			mark = markForm.check(request, student, module);
			markDao.add(mark);
			response.sendRedirect("/test/teacher/mystudents");

		}
		catch(NullPointerException e) {
			request.setAttribute("error", e.getMessage());
			this.doGet(request, response);
		}
		catch (BeanException e) {
			request.setAttribute("error", e.getMessage());
			this.doGet(request, response);
		}
		catch (DaoException e) {
			request.setAttribute("error", e.getMessage());
			this.doGet(request, response);
		}
	}

}
