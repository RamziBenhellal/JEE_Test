package forms;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;

import beans.BeanException;
import beans.Student;

public class StudentForms {
	
	public  Student check(HttpServletRequest request,beans.Class m_class) throws BeanException {
		
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String serialNumber = request.getParameter("serial");
		LocalDate birthDate = toDate(request.getParameter("birthdate"));
		String specialty = request.getParameter("specialty");
		beans.Class sClass = m_class;
		
		return new Student(firstname, lastname, serialNumber,birthDate,specialty,sClass);	
	}
	
	public LocalDate toDate(String date) {
		String[] dateFormat = date.split("-");
		
		int year = Integer.parseInt(dateFormat[0]);
		int month = Integer.parseInt(dateFormat[1]);
		int day = Integer.parseInt(dateFormat[2]);
		return LocalDate.of(year, month, day);
	}
}
 