package servlets.m_class;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanException;
import beans.Class;
import dao.Dao;
import dao.DaoException;
import dao.DaoFactory;
import forms.ClassForms;


@WebServlet("/CCreate")
public class CCreate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Dao<beans.Class> classDao;

	public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.classDao = daoFactory.getClassDao();
	}

	public CCreate() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	   throws ServletException, IOException {
		if(request.getSession().getAttribute("user") == null) {
			response.sendRedirect("/test/user/login");
		}else {
		this.getServletContext().getRequestDispatcher("/WEB-INF/class/create.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ClassForms classForms = new ClassForms();
		Class m_class;
		try {
			m_class = classForms.check(request);
			System.out.println(m_class.getStudentsNumber());
			classDao.add(m_class);
			response.sendRedirect("/test/class/index");

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
