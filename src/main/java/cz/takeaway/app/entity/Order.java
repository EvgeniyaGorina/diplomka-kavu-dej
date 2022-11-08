package cz.takeaway.app.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import cz.takeaway.app.enumetation.OrderStateEnum;

@Entity
@Table(name = "customer_order")
public class Order implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	@Enumerated(EnumType.STRING)
	private OrderStateEnum state;
	
	/*
	 * @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	 * 
	 * @JoinTable(name = "order_item", joinColumns = @JoinColumn(name = "order_id"),
	 * inverseJoinColumns = @JoinColumn(name = "item_id") ) private List<Item> items
	 * = new ArrayList<>();
	 */
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "order_id", referencedColumnName = "id")
	private List<OrderItem> orderItems = new ArrayList<>();
	
	@OneToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    @JsonProperty("cart")
	private Cart cart;
	
	@Column
	@JsonProperty(value = "created")
	@JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
	private Date created;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public OrderStateEnum getState() {
		return state;
	}

	public void setState(OrderStateEnum state) {
		this.state = state;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	@Override
	public int hashCode() {
		return Objects.hash(created, id, state);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(created, other.created) && Objects.equals(id, other.id) && state == other.state;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", state=" + state + ", created=" + created + "]";
	}
	
	
}
