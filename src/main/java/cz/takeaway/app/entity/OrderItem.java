package cz.takeaway.app.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import cz.takeaway.app.enumetation.SizeEnum;

@Entity
@Table(name = "customer_order_item")
public class OrderItem implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="item_id", nullable=true)
    @JsonProperty("item")
	private Item item;
	
	@Column
	@Enumerated(EnumType.STRING)
	private SizeEnum size;	
	
	@Column
	private Float price;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
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

	@Override
	public int hashCode() {
		return Objects.hash(id, item, price, size);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItem other = (OrderItem) obj;
		return Objects.equals(id, other.id) && Objects.equals(item, other.item) && Objects.equals(price, other.price)
				&& size == other.size;
	}

	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", item=" + item.getId() + ", size=" + size + ", price=" + price + "]";
	}
	
	
	
}
