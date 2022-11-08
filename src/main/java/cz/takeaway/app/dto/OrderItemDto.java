package cz.takeaway.app.dto;

import java.util.Objects;

public class OrderItemDto {
	
	private Long id;

	private Long item;

	private String size;	
	
	private Float price;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getItem() {
		return item;
	}

	public void setItem(Long item) {
		this.item = item;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
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
		OrderItemDto other = (OrderItemDto) obj;
		return Objects.equals(id, other.id) && Objects.equals(item, other.item) && Objects.equals(price, other.price)
				&& Objects.equals(size, other.size);
	}

	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", item=" + item + ", size=" + size + ", price=" + price + "]";
	}


	
}
