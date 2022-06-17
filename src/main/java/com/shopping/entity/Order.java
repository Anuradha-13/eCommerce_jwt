package com.shopping.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "ORDERS")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "orderProducts") 
//JsonIdentityInfo is used when objects have parent child relationship.It is used to indicate that object identity will be used during serialization/de-serialization.

public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	//@ManyToOne(optional = false, fetch = FetchType.LAZY)
	//@JoinColumn(name="user_id")
	private int userId;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dateCreated;

	@JsonManagedReference // forward reference that includes during the serialization process
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "pk.order") //the value of mappedBy is the name of the association-mapping attribute on the owning side.
	@Valid
	private List<OrderProducts> orderProducts = new ArrayList<>();

	
	
	  public Order() {
		super();
		
	}

	public Order(int id, int userId, LocalDate dateCreated, @Valid List<OrderProducts> orderProducts) {
		super();
		this.id = id;
		this.userId = userId;
		this.dateCreated = dateCreated;
		this.orderProducts = orderProducts;
	}

	public int getUserId() { return userId; }
	  
	  public void setUserId(int userId) { this.userId = userId; }
	 
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDate dateCreated) {
		this.dateCreated = dateCreated;
	}

	public List<OrderProducts> getOrderProducts() {
		return orderProducts;
	}

	public void setOrderProducts(List<OrderProducts> orderProducts) {
		this.orderProducts = orderProducts;
	}

	
	@Transient
    public Double getTotalOrderPrice() {
        double sum = 0D;
        List<OrderProducts> orderProducts = getOrderProducts();
        for (OrderProducts op : orderProducts) {
            sum += op.getTotalPrice();
        }

        return sum;
    }
	@Transient
    public int getNumberOfProducts() {
        return this.orderProducts.size();
    }
}
