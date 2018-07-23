package com.me.yzx.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.me.yzx.pojo.Car;
import com.me.yzx.pojo.Order;
import com.me.yzx.pojo.OrderRecord;
import com.me.yzx.pojo.UsedCarInformation;
import com.me.yzx.pojo.Usedcar;
import com.me.yzx.pojo.User;

public class OrderDAO extends DAO{
	 public void removeorder(int carid)
				throws Exception {
			try {
				begin();
				Query q = getSession().createQuery("delete OrderRecord where carid = :carid");
				q.setInteger("carid", carid);
				
				q.executeUpdate();
				commit();
			} catch (Exception e) {
				
				throw new Exception("Exception while creating user: " + e.getMessage());
			}
		}
	
	 public OrderRecord register(Car car,User user)
				throws Exception {
			try {
				begin();
				System.out.println("inside DAO");
				OrderRecord c =new OrderRecord();
				c.setCar(car);
				c.setPrice(car.getPrice());
				c.setUser(user);
				c.setStatus("pending");
				if(car.getSellerid()==0)
				{c.setSeller("dealer");}
				else
					c.setSeller(String.valueOf(car.getSellerid()));
				getSession().save(c);
				commit();
				return c;

			} catch (HibernateException e) {
				rollback();
				throw new Exception("Exception while creating user: " + e.getMessage());
			}
		}
	 public List<Order> listusedcarorder(int seller) {
			ArrayList<Order> carlist = new ArrayList<Order>();
			List<OrderRecord> productlist = new ArrayList<OrderRecord>();

			try {
				Query query = getSession().createQuery(" from OrderRecord where status='pending' and seller=:seller");
                query.setString("seller", String.valueOf(seller));
				productlist = (List<OrderRecord>) query.list();


				for (OrderRecord s : productlist) {
					
					Order c = new Order();
					c.setCarid(s.getCar().getCarid());
					c.setDate(s.getDate());
					c.setOrderid(s.getOrderid());
					c.setPrice(s.getCar().getPrice());
					c.setUserid(s.getUser().getId());
					c.setStatus(s.getStatus());
					c.setSeller(s.getSeller());
					carlist.add(c);
				}

			} catch (HibernateException e) {
				e.printStackTrace();

			} finally {
				close();
			}
			return carlist;
		}
	 public List<Order> listallorder() {
			ArrayList<Order> carlist = new ArrayList<Order>();
			List<OrderRecord> productlist = new ArrayList<OrderRecord>();

			try {
				Query query = getSession().createQuery(" from OrderRecord where status='pending' and seller='dealer'");

				productlist = (List<OrderRecord>) query.list();


				for (OrderRecord s : productlist) {
					
					Order c = new Order();
					c.setCarid(s.getCar().getCarid());
					c.setDate(s.getDate());
					c.setOrderid(s.getOrderid());
					c.setPrice(s.getCar().getPrice());
					c.setUserid(s.getUser().getId());
					c.setStatus(s.getStatus());
					c.setSeller(s.getSeller());
					carlist.add(c);
				}

			} catch (HibernateException e) {
				e.printStackTrace();

			} finally {
				close();
			}
			return carlist;
		}
	 public List<Order> listfinishedorder() {
			ArrayList<Order> carlist = new ArrayList<Order>();
			List<OrderRecord> productlist = new ArrayList<OrderRecord>();

			try {
				Query query = getSession().createQuery(" from OrderRecord where status='Finished' or status='SOLD'");

				productlist = (List<OrderRecord>) query.list();


				for (OrderRecord s : productlist) {
					
					Order c = new Order();
					c.setCarid(s.getCar().getCarid());
					c.setDate(s.getDate());
					c.setOrderid(s.getOrderid());
					c.setPrice(s.getCar().getPrice());
					c.setUserid(s.getUser().getId());
					c.setStatus(s.getStatus());
					c.setSeller(s.getSeller());
					carlist.add(c);
				}

			} catch (HibernateException e) {
				e.printStackTrace();

			} finally {
				close();
			}
			return carlist;
		}
	 public OrderRecord addordervalidate(int carid,String validatecode) throws Exception {
			try {
				begin();
				Query q = getSession().createQuery("from OrderRecord where carid = :carid ");
				q.setInteger("carid", carid);
				
				OrderRecord o =  (OrderRecord) q.uniqueResult();
				o.setValidate(validatecode);
				getSession().save(o);
				commit();
				return o;
			} catch (HibernateException e) {
				rollback();
				throw new Exception("Could not get user " + carid, e);
			}
		}

	 public OrderRecord changestatus(int carid) throws Exception {
			try {
				begin();
				Query q = getSession().createQuery("from OrderRecord where carid = :carid ");
				q.setInteger("carid", carid);
				
				OrderRecord o =  (OrderRecord) q.uniqueResult();
				o.setStatus("Finished");
				getSession().save(o);
				commit();
				return o;
			} catch (HibernateException e) {
				rollback();
				throw new Exception("Could not get user " + carid, e);
			}
		}
	 public void returncar(int carid) throws Exception {
			try {
				begin();
				Query q = getSession().createQuery("from Car where carid = :carid ");
				q.setInteger("carid", carid);
				
				Car car =  (Car) q.uniqueResult();
				car.setStatus(0);
				getSession().save(car);
				commit();
				
			} catch (HibernateException e) {
				rollback();
				throw new Exception("Could not get user " + carid, e);
			}
		}
	 public boolean finishordervalidate(int orderid,String validatecode) throws Exception {
			try {
				begin();
				Query q = getSession().createQuery("from OrderRecord where orderid = :orderid ");
				q.setInteger("orderid", orderid);
				
				OrderRecord o =  (OrderRecord) q.uniqueResult();
				if(o!=null)
				{
					if(o.getValidate().equals(validatecode))
						
					{
					o.setStatus("SOLD");
					getSession().save(o);
					commit();
					return true;
					}
					else {
				    commit();
					return false;
					}
					
				}else {
					commit();
					return false;
				}
			} catch (HibernateException e) {
				rollback();
				throw new Exception("Could not get user " + orderid, e);
			}
		}
	 public void updatecarstatus(int carid) throws Exception {
			try {
				begin();
				System.out.println("inside DAO");
				Query q = getSession().createQuery("from Car where carid = :carid");
				q.setInteger("carid", carid);
				Car car = (Car) q.uniqueResult();
				
					car.setStatus(1);;
					getSession().update(car);
					commit();
				

			} catch (HibernateException e) {
				rollback();
				throw new Exception("Exception while creating user: " + e.getMessage());
			}

		}

	public void updateuseduser(Car car) throws Exception {
		try {
			begin();
		
			

			if(car.getSellerid()!=0)
			{
				Query q = getSession().createQuery("from User where userid = :userid");

			q.setInteger("userid", car.getSellerid());
			User user = (User) q.uniqueResult();
				System.out.println(user.getId());
				
				user.setStatus(3);
				getSession().update(user);
				commit();
			}

		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while creating user: " + e.getMessage());
		}
		
	}

	public void returnstatus(String userid) throws Exception {
		
		try {
			begin();
			System.out.println("inside DAO");
			Query q = getSession().createQuery("from User where userid = :userid");
			q.setInteger("userid", Integer.parseInt(userid));
			User user = (User) q.uniqueResult();
			
				user.setStatus(1);;
				getSession().update(user);
				commit();
			

		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while creating user: " + e.getMessage());
		}
	}

	}
	 
	 



