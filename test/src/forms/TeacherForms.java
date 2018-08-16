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
		
		if(!module.getSpecialty().equals(m_class.getSpecialty())) {
			throw new BeanException("The module speciality and class speciality are not equivalent");
		}
		
		user.setType("teacher");
		
		return new Teacher(codeTeacher, firstname, lastname, tModule, tClass);	
	}
}
