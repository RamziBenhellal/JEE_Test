package servlets.module;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanException;
import beans.Module;
import dao.Dao;
import dao.DaoException;
import dao.DaoFactory;
import forms.ModuleForms;


@WebServlet("/MCreate")
public class MCreate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private Dao<Module> moduleDao;
	
	public void init() throws ServletException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.moduleDao = daoFactory.getModuleDao();
    }
	
    public MCreate() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("user") == null) {
			response.sendRedirect("/test/user/login");
		}else {
		this.getServletContext().getRequestDispatcher("/WEB-INF/module/create.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ModuleForms moduleForm = new ModuleForms();
		Module module;
		try {
			module = moduleForm.check(request);
			moduleDao.add(module);
			response.sendRedirect("/test/module/index");

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
