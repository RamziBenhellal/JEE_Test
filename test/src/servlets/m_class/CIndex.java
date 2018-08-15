package servlets.m_class;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;
import dao.DaoException;
import dao.DaoFactory;

@WebServlet("/CIndex")
public class CIndex extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private Dao<beans.Class> classDao;
    
	public void init() throws ServletException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.classDao = daoFactory.getClassDao();
    }
	
    public CIndex() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("user") == null) {
			response.sendRedirect("/test/user/login");
		}
		else {
		try {
			request.setAttribute("classes", classDao.all());
		} 
		catch (DaoException e) {
			request.setAttribute("error", e.getMessage());
		}
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/class/index.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
