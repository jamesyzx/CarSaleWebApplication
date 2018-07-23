package com.me.yzx.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.me.yzx.pojo.Car;
import com.me.yzx.pojo.User;

public class UserDAO extends DAO {

	public UserDAO() {
	}

	public User get(String userEmail, String password) throws Exception {
		try {
			begin();
			Query q = getSession().createQuery("from User where userEmail = :useremail and password = :password");
			q.setString("useremail", userEmail);
			q.setString("password", password);
			User user = (User) q.uniqueResult();
			commit();
			return user;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not get user " + userEmail, e);
		}
	}

	public User get(String userEmail) {
		try {
			begin();
			Query q = getSession().createQuery("from User where userEmail = :useremail");
			q.setString("useremail", userEmail);
			User user = (User) q.uniqueResult();
			commit();
			return user;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;

	}
	public User getbyuserid(int userid) {
		try {
			begin();
			Query q = getSession().createQuery("from User where userid = :useremail");
			q.setInteger("useremail", userid);
			User user = (User) q.uniqueResult();
			commit();
			return user;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;

	}

	public void removeuser(String userEmail) {
		try {
			begin();
			Query q = getSession().createQuery("delete User where userEmail = :useremail");
			q.setString("useremail", userEmail);
			q.executeUpdate();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public List<User> listalluser() {
		List<User> userlist = new ArrayList<User>();
		try {

			Query q = getSession().createQuery("from User u");
            System.out.println(q);
			userlist = q.list();
		} catch (HibernateException e) {
			e.printStackTrace();

		} finally {
			close();
		}
		return userlist;

	}

	public boolean validate(String userEmail) {
		
		try {
			begin();
			Query q = getSession().createQuery("from User where userEmail = :useremail");
			q.setString("useremail", userEmail);
			User user = (User) q.uniqueResult();
            commit();
			if (user == null) {
				return false;
			} else
				return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;

	}

	public User register(User u) throws Exception {
		try {
			begin();
			System.out.println("inside DAO");
			getSession().save(u);
			commit();
			return u;

		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while creating user: " + e.getMessage());
		}
	}

	public boolean updateUser(String email) throws Exception {
		try {
			begin();
			System.out.println("inside DAO");
			Query q = getSession().createQuery("from User where userEmail = :useremail");
			q.setString("useremail", email);
			User user = (User) q.uniqueResult();
			if (user != null) {
				user.setStatus(1);;
				getSession().update(user);
				commit();
				return true;
			} else {
				return false;
			}

		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while creating user: " + e.getMessage());
		}

	}
	public void validatecodeuser(String email) throws Exception {
		try {
			begin();
			System.out.println("inside DAO");
			Query q = getSession().createQuery("from User where userEmail = :useremail");
			q.setString("useremail", email);
			User user = (User) q.uniqueResult();
			
				user.setStatus(2);
				getSession().update(user);
				commit();
			

		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while creating user: " + e.getMessage());
		}

	}
	public void finishvalidatecodeuser(String email) throws Exception {
		try {
			begin();
			System.out.println("inside DAO");
			Query q = getSession().createQuery("from User where userEmail = :useremail");
			q.setString("useremail", email);
			User user = (User) q.uniqueResult();
			
				user.setStatus(1);
				getSession().update(user);
				commit();
			

		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while creating user: " + e.getMessage());
		}

	}

}