package hh.bookstore18.domain;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

/**
 * Note! This class is not an entity class. Class is used when creating new
 * application user.
 * 
 * ApplicationUser entity is used when user will be saved to db.
 */

public class UserRegistration { 
	
	@NotEmpty
	@Size(min = 1, max = 30)
	private String firstname = "" ; 
	
	@NotEmpty
	@Size(min = 1, max = 30)
	private String lastname = "" ; 
	
	@NotEmpty
	@Size(min = 4, max = 30)
	private String username = "";

	@NotEmpty
	@Size(min = 4, max = 30)
	private String password = "";

	@NotEmpty
	@Size(min = 4, max = 30)
	private String passwordCheck = "";

	@NotEmpty
	//private String role = "USER"; //muista vaihtaa myös UserRegistrationControllerissa
	private String role = "";

	public UserRegistration() {
		super();
	}

	public UserRegistration(@NotEmpty @Size(min = 1, max = 30) String firstname,
			@NotEmpty @Size(min = 1, max = 30) String lastname, @NotEmpty @Size(min = 4, max = 30) String username,
			@NotEmpty @Size(min = 4, max = 30) String password, @NotEmpty @Size(min = 4, max = 30) String passwordCheck,
			@NotEmpty String role) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.passwordCheck = passwordCheck;
		this.role = role;
	}

	public UserRegistration(@NotEmpty @Size(min = 4, max = 30) String username,
			@NotEmpty @Size(min = 4, max = 30) String password,
			@NotEmpty @Size(min = 4, max = 30) String passwordCheck) {
		super();
		this.username = username;
		this.password = password;
		this.passwordCheck = passwordCheck;
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

	public String getPasswordCheck() {
		return passwordCheck;
	}

	public void setPasswordCheck(String passwordCheck) {
		this.passwordCheck = passwordCheck;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserRegistration [firstname=" + firstname + ", lastname=" + lastname + ", username=" + username
				+ ", password=" + password + ", passwordCheck=" + passwordCheck + ", role=" + role + "]";
	}
	
	


}
