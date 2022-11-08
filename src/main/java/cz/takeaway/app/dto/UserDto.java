package cz.takeaway.app.dto;

import java.util.Date;
import java.util.Objects;

/**
 * @author tomit
 *
 */
public class UserDto {

	private Long id;

	private String firstName;

	private String lastName;

	private Date birthday;
	
	private Long enterpriseId;
	
	private String email;
	
	private String password;
	
	private String role;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	
	public boolean isEmployee() {
		return enterpriseId != null;
	}

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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		return Objects.hash(birthday, enterpriseId, firstName, id, lastName, password, role, email);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDto other = (UserDto) obj;
		return Objects.equals(birthday, other.birthday) && Objects.equals(enterpriseId, other.enterpriseId)
				&& Objects.equals(firstName, other.firstName) && Objects.equals(id, other.id)
				&& Objects.equals(lastName, other.lastName) && Objects.equals(password, other.password)
				&& Objects.equals(role, other.role) && Objects.equals(email, other.email);
	}

	@Override
	public String toString() {
		return "UserDto [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", birthday=" + birthday
				+ ", enterpriseId=" + enterpriseId + ", email=" + email + "]";
	}

	
	
	
	
}
