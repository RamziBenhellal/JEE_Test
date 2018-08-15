package servlets.m_class;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanException;
import dao.Dao;
import dao.DaoException;
import dao.DaoFactory;
import forms.ClassForms;


@WebServlet("/CEdit")
public class CEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private Dao<beans.Class> classDao;

	public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.classDao = daoFactory.getClassDao();
	}
  
    public CEdit() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("user") == null) {
			response.sendRedirect("/test/user/login");
		} else {
			String id = request.getParameter("id");
			try {
				request.setAttribute("c", classDao.find(id));
			} catch (DaoException e) {
				request.setAttribute("error", e.getMessage());
			}
			this.getServletContext().getRequestDispatcher("/WEB-INF/class/edit.jsp").forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ClassForms classForm = new ClassForms();
		beans.Class m_class;
		try {
			m_class = classForm.check(request);
			// open database module
			classDao.update(m_class);
			response.sendRedirect("/test/class/index");
		} catch (DaoException e) {
			request.setAttribute("error", e.getMessage());
			this.doGet(request, response);
		} catch (BeanException e) {
			request.setAttribute("error", e.getMessage());
			this.doGet(request, response);
		}

	}

}
