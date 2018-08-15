package servlets.teacher;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanException;
import beans.Module;
import beans.Teacher;
import beans.User;
import dao.Dao;
import dao.DaoException;
import dao.DaoFactory;
import forms.TeacherForms;

/**
 * Servlet implementation class T_Edit
 */
@WebServlet("/T_Edit")
public class T_Edit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Dao<Teacher> teacherDao;
	 private Dao<beans.Class> classDao;
	 private Dao<Module> moduleDao;
	 private Dao<User> userDao;

	    
	 public void init() throws ServletException {
	  DaoFactory daoFactory = DaoFactory.getInstance();
	  this.teacherDao = daoFactory.getTeacherDao();
	  this.moduleDao = daoFactory.getModuleDao();
	  this.classDao = daoFactory.getClassDao();
	  this.userDao = daoFactory.getUserDao();
	 }      
    public T_Edit() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("user") == null) {
			response.sendRedirect("/test/user/login");
		}else {
			try {
				String id = request.getParameter("id");
				request.setAttribute("teacher", teacherDao.find(id));
				request.setAttribute("user", userDao.find(id));
				request.setAttribute("classes",classDao.all());
				request.setAttribute("modules",moduleDao.all());
			} catch (DaoException e) {
				request.setAttribute("error", e.getMessage());
			}
		this.getServletContext().getRequestDispatcher("/WEB-INF/teacher/edit.jsp").forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TeacherForms moduleForm = new TeacherForms();
		Teacher teacher;
		try {
			beans.Class tClass = classDao.find(request.getParameter("tclass"));
			Module module = moduleDao.find(request.getParameter("module"));
			User user = userDao.find(request.getParameter("username"));
			teacher = moduleForm.check(request,user,module,tClass);
			teacherDao.update(teacher);
			response.sendRedirect("/test/teacher/index");

		} catch (BeanException e) {
			request.setAttribute("error", e.getMessage());
			this.doGet(request, response);
		}
		catch (DaoException e) {
			request.setAttribute("error", e.getMessage());
			this.doGet(request, response);
		}
	}

}
