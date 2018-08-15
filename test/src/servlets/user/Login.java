package servlets.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.BeanException;
import beans.User;
import dao.Dao;
import dao.DaoException;
import dao.DaoFactory;
import forms.UserForms;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Dao<User> userDao;

	public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.userDao = daoFactory.getUserDao();
	}

	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("username")) {
					request.setAttribute("cookieusername", cookie.getValue());
				}
			}
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/user/login.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UserForms userForms = new UserForms();
		User user = null;
		
		String id;
		try {
			id = userForms.checkLogin(request);
			user = userDao.find(id);
			
			
			if(userForms.checkPassword(request, user)) {
				
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
			
				
				
				Cookie cookie = new Cookie("username", user.getUsername());
				cookie.setMaxAge(60 * 60 * 24);
				response.addCookie(cookie);
				
				response.sendRedirect("/test/home");
			}
			
		} catch (BeanException e) {
			request.setAttribute("error", e.getMessage());
			doGet(request, response);
			
		} catch (DaoException e) {
			request.setAttribute("error", e.getMessage());
			doGet(request, response);
		}
		
	}

}
