package forms;


import javax.servlet.http.HttpServletRequest;

import beans.BeanException;
import beans.Module;
import beans.Teacher;
import beans.User;

public class TeacherForms {

public  Teacher check(HttpServletRequest request,User user, Module module,beans.Class m_class) throws BeanException {
		String codeTeacher = user.getCode();
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		Module tModule  = module;
		beans.Class tClass = m_class;
		
		user.setType("teacher");
		
		return new Teacher(codeTeacher, firstname, lastname, tModule, tClass);	
	}
}
