package beans;

public class Teacher {
	
	private String codeTeacher;
	private String firstname;
	private String lastname;
	private Module module;
	private Class tClass;
	
	
	public Teacher() {

	}
	
	public Teacher(String codeTeacher, String firstname, String lastname, Module module,Class tClass) throws BeanException {
		this.codeTeacher = codeTeacher;
		this.firstname = firstname;
		this.lastname = lastname;
		this.module = module;
		this.tClass = tClass;
	}

	public String getCodeTeacher() {
		return codeTeacher;
	}
	public void setCodeTeacher(String codeTeacher) {
		this.codeTeacher = codeTeacher;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLasname(String lastname) {
		this.lastname = lastname;
	}
	public Module getModule() {
		return module;
	}
	public void setModule(Module module) {
		this.module = module;
	}

	public Class gettClass() {
		return tClass;
	}

	public void settClass(Class tClass) {
		this.tClass = tClass;
	}
	
	

}
