package com.me.yzx.pojo;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "car")
@Inheritance(strategy = InheritanceType.JOINED)
public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "carid", unique = true, nullable = false)
	int carid;
	@Column(name = "liter")
	double liter;
	@Column(name = "bodytype")
	String bodytype;
	@Column(name = "color")
	String color;
	@Column(name = "sellerid")
	int sellerid;
	@Column(name = "price")
	int price;
	@Column(name = "status")
	int status;
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "modelid")
	Model model;
	@OneToMany(mappedBy = "car")
	Set<Cart> cartlist;
	@OneToMany(mappedBy = "car")
	Set<OrderRecord> orderlist;

	public Car() {

	}

	public Set<OrderRecord> getOrderlist() {
		return orderlist;
	}

	public void setOrderlist(Set<OrderRecord> orderlist) {
		this.orderlist = orderlist;
	}

	public int getSellerid() {
		return sellerid;
	}

	public void setSellerid(int sellerid) {
		this.sellerid = sellerid;
	}

	public Set<Cart> getCartlist() {
		return cartlist;
	}

	public void setCartlist(Set<Cart> cartlist) {
		this.cartlist = cartlist;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Car(int carid) {
		this.carid = carid;
	}

	public Car(double d, String bodytype, int price, String color, Model model) {
		this.liter = d;
		this.bodytype = bodytype;
		this.color = color;
		this.model = model;
		this.price = price;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getCarid() {
		return carid;
	}

	public void setCarid(int carid) {
		this.carid = carid;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public double getLiter() {
		return liter;
	}

	public void setLiter(double liter) {
		this.liter = liter;
	}

	public String getBodytype() {
		return bodytype;
	}

	public void setBodytype(String bodytype) {
		this.bodytype = bodytype;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

}
