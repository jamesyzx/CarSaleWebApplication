package com.me.yzx.pojo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cart")
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "orderitemid", unique = true, nullable = false)
	private int orderitemid;

	@Column(name = "price")
	int price;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "userid")
	User user;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "carid")
	Car car;

	public Cart(int price, User user, Car car) {
		this.car = car;
		this.price = price;
		this.user = user;

	}

	public Cart() {

	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getOrderitemid() {
		return orderitemid;
	}

	public void setOrderitemid(int orderitemid) {
		this.orderitemid = orderitemid;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
