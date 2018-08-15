package forms;


import javax.servlet.http.HttpServletRequest;

import beans.BeanException;
import beans.Mark;
import beans.Module;
import beans.Student;

public class MarkForms {
public  Mark check(HttpServletRequest request, Student _student , Module _module) throws BeanException {
		try {
		Student student = _student;
		Module module  = _module;
		Float attendance = Float.parseFloat(request.getParameter("attendance"));
		Float test_1 = Float.parseFloat(request.getParameter("test_1"));
		Float test_2 = Float.parseFloat(request.getParameter("test_2"));
		Float exam = Float.parseFloat(request.getParameter("exam"));
		Float total = (attendance + test_1 + test_2 + exam * 3 )/6;
		
		return new Mark(student, module, attendance, test_1, test_2, exam, total);
		}
		catch(NumberFormatException e) {
			new BeanException("Invalid Mark Format (Format is for Exemple : 15.5)");
			return null;
		}
		
		
			
	}
}
