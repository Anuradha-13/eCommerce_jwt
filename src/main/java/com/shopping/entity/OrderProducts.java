package com.shopping.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class OrderProducts {

	@EmbeddedId
	@JsonIgnore
	private OrderProductsPK pk;

	private int quantity;

	public OrderProducts() {
		super();

	}

	public OrderProducts(Order order, Product product, Integer quantity) {
		pk = new OrderProductsPK();
		pk.setOrder(order);
		pk.setProduct(product);
		this.quantity = quantity;
		
	}

	//@Transient is used to indicate that a field/method is not to be persisted or ignore fields to save in the database.
	
	@Transient
	public Product getProduct() {
		return this.pk.getProduct();
	}

	@Transient
	public Double getTotalPrice() {
		return getProduct().getPrice() * getQuantity();
	}

	public OrderProductsPK getPk() {
		return pk;
	}

	public void setPk(OrderProductsPK pk) {
		this.pk = pk;
	}


	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pk == null) ? 0 : pk.hashCode());
		result = prime * result + quantity;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderProducts other = (OrderProducts) obj;
		if (pk == null) {
			if (other.pk != null)
				return false;
		} else if (!pk.equals(other.pk))
			return false;
		if (quantity != other.quantity)
			return false;
		return true;
	}

			
}
