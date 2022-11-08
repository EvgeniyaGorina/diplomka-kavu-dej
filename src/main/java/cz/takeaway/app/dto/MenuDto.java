package cz.takeaway.app.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.constraints.NotNull;

public class MenuDto {

	private Long id;
	
	@NotNull
	private String name;
	
	private List<Long> items = new ArrayList<Long>();

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

	public List<Long> getItems() {
		return items;
	}

	public void setItems(List<Long> items) {
		this.items = items;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, items, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MenuDto other = (MenuDto) obj;
		return Objects.equals(id, other.id) && Objects.equals(items, other.items) && Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "MenuDto [id=" + id + ", name=" + name + ", items=" + items + "]";
	}
	
	
}
