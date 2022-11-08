package cz.takeaway.app.dto;

import java.util.Objects;

public class CartDto {
	
	private Long id;
	
	private String payment;
	
	private Long user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public Long getUser() {
		return user;
	}

	public void setUser(Long user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		return Objects.hash(payment, user, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CartDto other = (CartDto) obj;
		return Objects.equals(payment, other.payment) && Objects.equals(user, other.user)
				&& Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "CartDto [id=" + id + ", payment=" + payment + ", user=" + user + "]";
	}
	
	

}
