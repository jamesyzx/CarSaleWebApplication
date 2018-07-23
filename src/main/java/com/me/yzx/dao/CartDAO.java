package com.me.yzx.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.me.yzx.pojo.Car;
import com.me.yzx.pojo.Cart;
import com.me.yzx.pojo.Newcar;
import com.me.yzx.pojo.Usedcar;
import com.me.yzx.pojo.User;



public class CartDAO extends DAO{
	 public Cart register(Car car, User user)
				throws Exception {
			try {
				begin();
				System.out.println("inside DAO");
             
				Cart cart=new Cart();
				cart.setCar(car);
				cart.setPrice(car.getPrice());
				cart.setUser(user);
				getSession().save(cart);
				commit();
				
				
				return cart;

			} catch (Exception e) {
				rollback();
				throw new Exception("Exception while creating user: " + e.getMessage());
			}
		}
	 public void deletecart(Car car)
				throws Exception {
			try {
				begin();
				Query q = getSession().createQuery("delete Cart where carid = :carid");
				q.setInteger("carid", car.getCarid());
				
				q.executeUpdate();
				commit();
			} catch (Exception e) {
				
				throw new Exception("Exception while creating user: " + e.getMessage());
			}
		}
	 
	

	 

}
