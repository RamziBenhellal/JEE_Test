package servlets.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanException;
import beans.User;
import dao.Dao;
import dao.DaoException;
import dao.DaoFactory;
import forms.UserForms;


@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Dao<User> userDao;
	
	public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.userDao = daoFactory.getUserDao();
	}
       

    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/user/register.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserForms userForm = new UserForms();
		User user = null;
		try {
			user = userForm.checkRegister(request);
			userDao.add(user);
		} catch (BeanException e) {
			request.setAttribute("error", e.getMessage());
			doGet(request, response);
		} catch (DaoException e) {
			request.setAttribute("error", e.getMessage());
			doGet(request, response);
		}
		response.sendRedirect("/test/user/login");
	}

}
