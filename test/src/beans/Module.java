package beans;

public class Module {
	
	private String moduleCode;
	private String module;
	private Integer coefficient;
	private String specialty;
	
	public Module() {

	}
	
	public Module(String moduleCode, String module, Integer coefficient, String specialty) throws BeanException {
 		this.moduleCode = moduleCode;
		this.module = module;
		this.coefficient = coefficient;
		this.specialty = specialty;
	}

    		
	public String getModuleCode() {
		return moduleCode;
	}
	public void setModuleCode(String codeModule) {
		this.moduleCode = codeModule;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public Integer getCoefficient() {
		return coefficient;
	}
	public void setCoefficient(Integer coefficient) {
		this.coefficient = coefficient;
	}
	public String getSpecialty() {
		return specialty;
	}
	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}
	
	@Override
	public String toString() {
		return moduleCode;
	}
	
	


}
