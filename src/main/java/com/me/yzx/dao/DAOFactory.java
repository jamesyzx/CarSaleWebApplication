package com.me.yzx.dao;

public class DAOFactory {
	public UserDAO createUserDao() {
		return new UserDAO();
		
	}
	public OrderDAO createOrderDao() {
		return new OrderDAO();
		
	}
	public CarDAO createCarDao() {
		return new CarDAO();
		
	}
	public CartDAO createCartDao() {
		return new CartDAO();
		
	}

}