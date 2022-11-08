package cz.takeaway.app.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class OrderDto {

	private Long id;
	
	private String state;
	
	private List<OrderItemDto> items = new ArrayList<>();
	
	private Long cartId;
	
	private Date created;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<OrderItemDto> getItems() {
		return items;
	}

	public void setItems(List<OrderItemDto> items) {
		this.items = items;
	}

	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}
	

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cartId, id, items, state);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderDto other = (OrderDto) obj;
		return Objects.equals(cartId, other.cartId) && Objects.equals(id, other.id)
				&& Objects.equals(items, other.items) && Objects.equals(state, other.state);
	}

	@Override
	public String toString() {
		return "OrderDto [id=" + id + ", state=" + state + ", items=" + items + ", cartId=" + cartId + "]";
	}
	
	
}
