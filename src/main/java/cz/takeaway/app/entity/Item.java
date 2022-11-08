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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table
public class Item implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	@Column
	private String name;

	@Column
	private String picture;

	@Column
	@Size(max = 255)
	private String description;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "item_id", referencedColumnName = "id")
	private List<Calculation> calc = new ArrayList<>();

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

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Calculation> getCalc() {
		return calc;
	}

	public void setCalc(List<Calculation> calc) {
		this.calc = calc;
	}

	@Override
	public int hashCode() {
		return Objects.hash(calc, description, id, name, picture);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		return Objects.equals(calc, other.calc) && Objects.equals(description, other.description)
				&& Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(picture, other.picture);
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", picture=" + picture + ", description=" + description + "]";
	}

}
