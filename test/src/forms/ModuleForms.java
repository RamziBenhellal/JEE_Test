package forms;


import javax.servlet.http.HttpServletRequest;

import beans.BeanException;
import beans.Module;

public class ModuleForms {
public  Module check(HttpServletRequest request) throws BeanException {
		
		String moduleCode = request.getParameter("modulecode");
		String module = request.getParameter("module");
		Integer coefficient = Integer.parseInt(request.getParameter("coefficient"));
		String specialty = request.getParameter("specialty");
		
		return new Module(moduleCode, module, coefficient, specialty);	
	}
}
