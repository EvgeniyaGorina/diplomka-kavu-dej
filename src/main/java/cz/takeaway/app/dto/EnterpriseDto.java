package cz.takeaway.app.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


public class EnterpriseDto {
	
	private Long id;
	
	@NotEmpty
	@Size(max = 128)
	private String name;
	
	private String about;

	@NotEmpty
	private List<Long> employees = new ArrayList<>();
	
	@NotEmpty
	private List<Long> menus = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public List<Long> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Long> employees) {
		this.employees = employees;
	}

	public List<Long> getMenus() {
		return menus;
	}

	public void setMenus(List<Long> menus) {
		this.menus = menus;
	}

	@Override
	public int hashCode() {
		return Objects.hash(about, employees, id, menus, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EnterpriseDto other = (EnterpriseDto) obj;
		return Objects.equals(about, other.about) && Objects.equals(employees, other.employees)
				&& Objects.equals(id, other.id) && Objects.equals(menus, other.menus)
				&& Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "EnterpriseDto [id=" + id + ", name=" + name + ", about=" + about + ", employees=" + employees
				+ ", menus=" + menus + "]";
	}
	
	

 }
