package com.me.yzx.pojo;

import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "orderrecord")
public class OrderRecord {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "orderid", unique = true, nullable = false)
	private int orderid;

	@Column(name = "price")
	int price;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date", updatable = false)
	@org.hibernate.annotations.CreationTimestamp
	Date date;

	@Column(name = "status")
	String status;
	@Column(name = "seller")
	String seller;
	@Column(name = "validate")
	String validate;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "username")
	User user;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "carid")
	Car car;

	public OrderRecord(int price, User user, Car car, String status) {
		this.car = car;
		this.price = price;
		this.user = user;
		this.status = status;

	}

	public OrderRecord() {

	}

	public int getPrice() {
		return price;
	}

	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}

	public String getValidate() {
		return validate;
	}

	public void setValidate(String validate) {
		this.validate = validate;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
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
