package cz.takeaway.app.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import cz.takeaway.app.enumetation.SizeEnum;

@Entity
@Table
public class Calculation implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	@Enumerated(EnumType.STRING)
	private SizeEnum size;
	
	@Column
	private Float price;
	
	@Column
	private String currency;
	
	@Column
	private Integer prepTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SizeEnum getSize() {
		return size;
	}

	public void setSize(SizeEnum size) {
		this.size = size;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Integer getPrepTime() {
		return prepTime;
	}

	public void setPrepTime(Integer prepTime) {
		this.prepTime = prepTime;
	}

	@Override
	public int hashCode() {
		return Objects.hash(currency, id, prepTime, price, size);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Calculation other = (Calculation) obj;
		return Objects.equals(currency, other.currency) && Objects.equals(id, other.id)
				&& Objects.equals(prepTime, other.prepTime) && Objects.equals(price, other.price) && size == other.size;
	}

	@Override
	public String toString() {
		return "Calculation [id=" + id + ", size=" + size + ", price=" + price + ", currency=" + currency
				+ ", prepTime=" + prepTime + "]";
	}

	




	
	
}
