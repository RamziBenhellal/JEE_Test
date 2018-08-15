package beans;


public class Class {
	
	private String classCode;
	private Integer studentsNumber;
	private String specialty;
	
	public Class() {
		// TODO Auto-generated constructor stub
	}
	

	public Class(String classCode, Integer studentsNumber, String specialty) throws BeanException {
		if(classCode.length() != 8) {
			throw new BeanException("Invalid Format for Class Code (Format Exemple :  [ 1S2 ] for first year Science class number 2)");
		}
		else {
		this.classCode = classCode;
		}
		if(studentsNumber < 0 ) {
			throw new BeanException(" Invalid Students Number !! ");
		}
		else {
			this.studentsNumber = studentsNumber;
		}
		this.specialty = specialty;
	}

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public Integer getStudentsNumber() {
		return studentsNumber;
	}

	public void setStudentsNumber(Integer studentNumber) {
		this.studentsNumber = studentNumber;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}
	
	@Override
	public String toString() {
		return this.classCode;
	}
	


}
