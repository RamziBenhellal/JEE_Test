package forms;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;

import beans.BeanException;
import beans.Class;

public class ClassForms {
public  Class check(HttpServletRequest request) throws BeanException {
		
		String classCode = request.getParameter("classcode");
		Integer studentsNumber = Integer.parseInt(request.getParameter("studentsnumber"));
		String specialty = request.getParameter("specialty");
		if(classCode.split("/").length > 1) {
			return new Class(classCode,studentsNumber,specialty);	
		}
		else {
			return new Class(LocalDate.now().getYear()+"/"+classCode,studentsNumber,specialty);	
		}
	}
}
