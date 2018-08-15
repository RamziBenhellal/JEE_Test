package servlets.module;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Module;
import dao.Dao;
import dao.DaoException;
import dao.DaoFactory;


@WebServlet("/MDetails")
public class MDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private Dao<Module> moduleDao;
	
	public void init() throws ServletException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.moduleDao = daoFactory.getModuleDao();
    }
	
    public MDetails() {
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
			request.setAttribute("module", moduleDao.find(id));
		} catch (DaoException e) {
			request.setAttribute("error", e.getMessage());
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/module/details.jsp").forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		try {
			moduleDao.delete(id);
			request.setAttribute("modules", moduleDao.all());
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/module/index.jsp").forward(request, response);
	}

}
