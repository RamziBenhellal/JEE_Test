package beans;

public class Mark {

	private Student student;
	private Module module;
	private Float attendance;
	private Float test_1;
	private Float test_2;
	private Float exam;
	private Float total;
	
	public Mark() {
		
	}

	public Mark(Student student, Module module, Float attendance, Float test_1, Float test_2,Float exam, Float total) throws BeanException  {
		this.student = student;
		this.module = module;
		this.attendance = attendance;
		this.test_1 = test_1;
		this.test_2 = test_2;
		this.exam = exam;
		this.total = total;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public Float getAttendance() {
		return attendance;
	}

	public void setAttendance(Float attendance) {
		this.attendance = attendance;
	}

	public Float getTest_1() {
		return test_1;
	}

	public void setTest_1(Float test_1) {
		this.test_1 = test_1;
	}

	public Float getTest_2() {
		return test_2;
	}

	public void setTest_2(Float test_2) {
		this.test_2 = test_2;
	}
	
	public Float getExam() {
		return exam;
	}

	public void setExam(Float exam) {
		this.exam = exam;
	}

	public Float getTotal() {
		return total;
	}

	public void setTotal(Float total) {
		this.total = total;
	}
	
	
}
