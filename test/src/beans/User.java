package beans;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class User {
    
	private String code;
	private String username;
	private String password;
	private String type;
	private LocalDateTime created_at;
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(String username, String password, String type) throws BeanException {
		this.code = LocalDate.now().getYear()+"0000"+username.length()+LocalDateTime.now().getSecond();
		if(username.length() > 50 || username.length() < 2) {
			throw new BeanException("Invalid Username ! (50 character maximum, 2 character minimum )");
		}
		else {
			this.username = username;
		}
		if(password.length() > 20 || password.length() < 6) {
			throw new BeanException("Invalid Username ! (20 character maximum, 6 character minimum )");
		}
		else {
			this.password = password;
		}
		this.type = type;
	}

	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	
	
}
