package cz.takeaway.app.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table
public class Enterprise implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	private String name;
	
	@Column
	private String about;
	
	@NotEmpty
	@OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "enterprise_id", referencedColumnName = "id")
	private List<User> employees = new ArrayList<>();
	
	@NotEmpty
	@OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "enterprise_id", referencedColumnName = "id")
	private List<Menu> menus = new ArrayList<>();
	


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

	public List<User> getEmployees() {
		return employees;
	}

	public void setEmployees(List<User> employees) {
		this.employees = employees;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	@Override
	public int hashCode() {
		return Objects.hash(about, id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Enterprise other = (Enterprise) obj;
		return Objects.equals(about, other.about) && Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "Enterprise [id=" + id + ", name=" + name + ", about=" + about + "]";
	}
	
	
	
	
	

	

	
}
