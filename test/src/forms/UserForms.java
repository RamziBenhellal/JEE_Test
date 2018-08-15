package forms;

import javax.servlet.http.HttpServletRequest;
import beans.BeanException;
import beans.User;

public class UserForms {

	public String checkLogin(HttpServletRequest request) throws BeanException {

		String username = request.getParameter("username");

		return username;
	}
	
	public User checkRegister(HttpServletRequest request) throws BeanException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String confirm_password = request.getParameter("confirm_password");
		
		if(!password.equals(confirm_password)) {
			throw new BeanException("Check your password again ");
		}

		return new User(username, password,null);
	}
	
	public boolean checkPassword(HttpServletRequest request , User user) throws BeanException {
		try {
		if(request.getParameter("password").equals(user.getPassword())) {
			return true;
		}
		else {
			throw new BeanException("User not Found ");
		}
		}
		catch(Exception e) {
			throw new BeanException("User not Found ");
		}
		
	}
}
