package com.me.yzx.pojo;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userid", unique = true, nullable = false)
	private int id;
	@Column(name = "userName")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "useremail")
	private String useremail;
	@Column(name = "role")
	private int role;
	@Column(name = "status")
	private int status;

	@OneToMany(mappedBy = "user")
	Set<Cart> cartlist;

	@OneToMany(mappedBy = "user")
	Set<OrderRecord> orderlist;

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public User() {

	}

	public User(String username) {
		this.username = username;

	}

	public User(String username, String password, String useremail, int role, int status) {
		this.username = username;
		this.password = password;
		this.useremail = useremail;
		this.role = role;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUseremail() {
		return useremail;
	}

	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Set<OrderRecord> getOrderlist() {
		return orderlist;
	}

	public void setOrderlist(Set<OrderRecord> orderlist) {
		this.orderlist = orderlist;
	}

	public Set<Cart> getCartlist() {
		return cartlist;
	}

	public void setCartlist(Set<Cart> cartlist) {
		this.cartlist = cartlist;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
