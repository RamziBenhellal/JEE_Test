package beans;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Student {
	
	private String firstname;
	private String lastname;
	private String serialNumber;
	private LocalDate birthDate;
	private String specialty;
	private Class sClass;
	private LocalDateTime created_at;
	
	public Student() {
		// TODO Auto-generated constructor stub
	}
	
	public Student(String firstname,String lastname,String serialNumber,LocalDate birthDate,String specialty,Class sClass ) throws BeanException {
		if(firstname.length() > 50 || firstname.length() < 2 ){
			throw new BeanException("Invalid First Name ! (50 character maximum, 2 character minimum )");
		}
		else {
			this.firstname = firstname;
		}
		
		if(lastname.length() > 50 || lastname.length() < 2) {
			throw new BeanException("Invalid Last Name ! (50 character maximum, 2 character minimum)");
		}
		else {
			this.lastname = lastname;
		}
		
		if(serialNumber.length() != 12 ) {
			throw new BeanException("Invalid Serial Number ! ");
		}
		else {
			this.serialNumber = serialNumber;
		}
		
		this.birthDate = birthDate;
		this.specialty = specialty;
		this.sClass = sClass;
		
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

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}

	public Class getsClass() {
		return sClass;
	}

	public void setsClass(Class sClass) {
		this.sClass = sClass;
	}
	
	

}
