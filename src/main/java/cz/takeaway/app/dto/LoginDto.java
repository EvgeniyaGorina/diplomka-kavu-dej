package cz.takeaway.app.dto;

import java.util.Objects;

public class LoginDto {

	
	private String email;
	
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoginDto other = (LoginDto) obj;
		return Objects.equals(email, other.email) && Objects.equals(password, other.password);
	}

	@Override
	public String toString() {
		return "LoginDto [email=" + email + ", password=" + password + "]";
	}
	
	
}
