package com.me.yzx.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.google.gson.Gson;
import com.me.yzx.pojo.Car;
import com.me.yzx.pojo.CarInformation;
import com.me.yzx.pojo.Cart;
import com.me.yzx.pojo.Mark;
import com.me.yzx.pojo.Model;
import com.me.yzx.pojo.UsedCarInformation;
import com.me.yzx.pojo.Usedcar;
import com.me.yzx.pojo.User;

public class CarDAO extends DAO {

	public CarDAO() {
		super();

	}

	public List<CarInformation> listallcar() {
		int page = 0;
		List<Car> productlist = new ArrayList<Car>();
		ArrayList<CarInformation> carlist = new ArrayList<CarInformation>();
		try {
			Query query = getSession().createQuery("from Car where status=0");
			// select c.carid,c.model from Car c inner join c.model
			productlist = (List<Car>) query.list();

			for (Car s : productlist) {

				CarInformation c = new CarInformation();
				c.setCarid((s.getCarid()));
				c.setBodytype(s.getBodytype());
				c.setColor(s.getColor());
				c.setLiter(s.getLiter());
				c.setModel(s.getModel().getModel());
				c.setMark(s.getModel().getMark().getMark());
				c.setPrice(s.getPrice());
				carlist.add(c);
			}
		} catch (HibernateException e) {
			e.printStackTrace();

		} finally {
			close();
		}
		return carlist;
	}

	public List<CarInformation> listnewcar() {
		ArrayList<CarInformation> carlist = new ArrayList<CarInformation>();
		List<Car> productlist = new ArrayList<Car>();

		try {
			Query query = getSession().createQuery(" from Newcar where status=0");

			productlist = (List<Car>) query.list();

			for (Car s : productlist) {

				CarInformation c = new CarInformation();
				c.setCarid((s.getCarid()));
				c.setBodytype(s.getBodytype());
				c.setColor(s.getColor());
				c.setLiter(s.getLiter());
				c.setModel(s.getModel().getModel());
				c.setMark(s.getModel().getMark().getMark());
				c.setPrice(s.getPrice());
				carlist.add(c);
			}

		} catch (HibernateException e) {
			e.printStackTrace();

		} finally {
			close();
		}
		return carlist;
	}

	public List<UsedCarInformation> listusedcar() {
		ArrayList<UsedCarInformation> carlist = new ArrayList<UsedCarInformation>();
		List<Usedcar> productlist = new ArrayList<Usedcar>();

		try {
			Query query = getSession().createQuery(" from Usedcar where status=0 ");

			productlist = (List<Usedcar>) query.list();

			for (Usedcar s : productlist) {

				UsedCarInformation c = new UsedCarInformation();
				c.setCarid((s.getCarid()));
				c.setBodytype(s.getBodytype());
				c.setColor(s.getColor());
				c.setLiter(s.getLiter());
				c.setModel(s.getModel().getModel());
				c.setMark(s.getModel().getMark().getMark());
				c.setPrice(s.getPrice());
				c.setYear(s.getYear());
				c.setMiles(s.getMiles());
				carlist.add(c);
			}

		} catch (HibernateException e) {
			e.printStackTrace();

		} finally {
			close();
		}
		return carlist;
	}

	public List<CarInformation> getnewcarbycarid(int carid) {
		ArrayList<CarInformation> carlist = new ArrayList<CarInformation>();
		List<Car> productlist = new ArrayList<Car>();

		try {
			Query query = getSession().createQuery(" from Newcar where carid=:carid and status=0");
			query.setInteger("carid", carid);
			productlist = (List<Car>) query.list();

			for (Car s : productlist) {

				CarInformation c = new CarInformation();
				c.setCarid((s.getCarid()));
				c.setBodytype(s.getBodytype());
				c.setColor(s.getColor());
				c.setLiter(s.getLiter());
				c.setModel(s.getModel().getModel());
				c.setMark(s.getModel().getMark().getMark());
				c.setPrice(s.getPrice());
				carlist.add(c);
			}

		} catch (HibernateException e) {
			e.printStackTrace();

		} finally {
			close();
		}
		return carlist;
	}

	public List<UsedCarInformation> getusedcarbycarid(int carid) {
		ArrayList<UsedCarInformation> carlist = new ArrayList<UsedCarInformation>();
		List<Usedcar> productlist = new ArrayList<Usedcar>();

		try {
			Query query = getSession().createQuery(" from Usedcar where carid=:carid");
			query.setInteger("carid", carid);
			productlist = (List<Usedcar>) query.list();

			for (Usedcar s : productlist) {

				UsedCarInformation c = new UsedCarInformation();
				c.setCarid((s.getCarid()));
				c.setBodytype(s.getBodytype());
				c.setColor(s.getColor());
				c.setLiter(s.getLiter());
				c.setModel(s.getModel().getModel());
				c.setMark(s.getModel().getMark().getMark());
				c.setPrice(s.getPrice());
				c.setYear(s.getYear());
				c.setMiles(s.getMiles());
				carlist.add(c);
			}

		} catch (HibernateException e) {
			e.printStackTrace();

		} finally {
			close();
		}
		return carlist;
	}

	public Car getbycarid(int carid) throws Exception {
		try {

			Query q = getSession().createQuery("from Car where carid = :carid");
			q.setInteger("carid", carid);
			Car car = (Car) q.uniqueResult();

			return car;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not get user " + carid, e);
		}
	}

	public List<CarInformation> getbymark(String mark) throws Exception {
		CarDAO c = new CarDAO();
		ArrayList<CarInformation> carlist = new ArrayList<CarInformation>();
		for (CarInformation car : c.listallcar()) {
			if (car.getMark().equals(mark))
				carlist.add(car);
		}

		return carlist;
	}

	public List<Usedcar> listusedcarsimple() {

		List<Usedcar> productlist = new ArrayList<Usedcar>();

		try {
			Query query = getSession().createQuery("from Usedcar");

			productlist = (List<Usedcar>) query.list();

		} catch (HibernateException e) {
			e.printStackTrace();

		} finally {
			close();
		}
		return productlist;
	}

	public List<Car> listcarbypage(int first, int max) {
		int page = 0;
		List<Car> productlist = new ArrayList<Car>();

		try {
			Query query = getSession()
					.createQuery("from Car p order by  p.mark desc, p.model desc,p.color asc, p.carid asc ");
			query.setFirstResult(first);
			query.setMaxResults(max);

			productlist = (List<Car>) query.list();
			if (productlist != null) {
				page = productlist.size();
				System.out.println("total results" + page);

			}
		} catch (HibernateException e) {
			e.printStackTrace();

		} finally {
			close();
		}
		return productlist;
	}

	public Car registercar(Car c) throws Exception {
		try {
			begin();
			System.out.println("inside DAO");

			getSession().save(c);
			commit();

			return c;

		} catch (Exception e) {
			rollback();
			throw new Exception("Exception while creating user: " + e.getMessage());
		}
	}

	public List<CarInformation> listcarbymarkandmodel(String mark, String model) {
		ArrayList<CarInformation> carlist = new ArrayList<CarInformation>();
		List<Car> productlist = new ArrayList<Car>();

		try {
			Query query = getSession()
					.createQuery(" from Car c where c.model.mark.mark=:mark and c.model.model=:model and status=0");
			query.setString("mark", mark);
			query.setString("model", model);
			productlist = (List<Car>) query.list();

			for (Car s : productlist) {

				CarInformation c = new CarInformation();
				c.setCarid((s.getCarid()));
				c.setBodytype(s.getBodytype());
				c.setColor(s.getColor());
				c.setLiter(s.getLiter());
				c.setModel(s.getModel().getModel());
				c.setMark(s.getModel().getMark().getMark());
				c.setPrice(s.getPrice());
				carlist.add(c);
			}

		} catch (HibernateException e) {
			e.printStackTrace();

		} finally {
			close();
		}
		return carlist;
	}

	public String listallmark() {

		String jsonObject = null;
		try {
			Query query = getSession().createQuery("select mark from Mark where status=0");
			Gson gson = new Gson();
			jsonObject = gson.toJson(query.list());

		} catch (HibernateException e) {
			e.printStackTrace();

		} finally {
			close();
		}
		return jsonObject;
	}

	public String listmodelbymark(String mark) {
		String jsonObject = null;
		Gson gson = new Gson();
		try {
			Query query = getSession().createQuery("select m.model from Model m where m.mark.mark=:mark and status=0");
			query.setString("mark", mark);
			jsonObject = gson.toJson(query.list());

		} catch (HibernateException e) {
			e.printStackTrace();

		} finally {
			close();
		}
		return jsonObject;
	}

	public String findcarbymodelandmark(String mark, String model) {
		String jsonObject = null;
		Gson gson = new Gson();
		try {
			Query query = getSession().createQuery(
					"select c.carid,c.color,c.price,c.bodytype,c.model.model, c.model.mark.mark from Car c where c.model.mark.mark=:mark and c.model.model=:model and c.status=0");
			query.setString("mark", mark);
			query.setString("model", model);
			jsonObject = gson.toJson(query.list());

		} catch (HibernateException e) {
			e.printStackTrace();

		} finally {
			close();
		}
		return jsonObject;
	}

	public String findnewcarbymodelandmark(String mark, String model) {
		String a = "";
		String jsonObject = null;
		Gson gson = new Gson();
		try {
			Query query = getSession().createQuery(
					"select c.carid,c.color,c.price,c.bodytype,c.model.model, c.model.mark.mark from Newcar c where c.model.mark.mark=:mark and c.model.model=:model and c.status=0");
			query.setString("mark", mark);
			query.setString("model", model);
			// System.out.println(query.list());
			jsonObject = gson.toJson(query.list());
			StringBuffer bf = new StringBuffer();
			// System.out.println(jsonObject);
			// System.out.println("
			// [{\"carid\":\"+s[0].toString()+\",\"mark\":\"+s[5].toString()+\",\"model\":\"+s[4].toString()+\",\"color\":\"+s[1].toString()+\",\"bodytype\":\"+s[3].toString()+\",\"price\":\"+s[2].toString()+\"}]");
			for (Object q : query.list()) {
				Object[] s = (Object[]) q;
				bf.append("[{\"carid\":\"" + s[0].toString() + "\",\"mark\":\"" + s[5].toString() + "\",\"model\":\""
						+ s[4].toString() + "\",\"color\":\"" + s[1].toString() + "\",\"bodytype\":\"" + s[3].toString()
						+ "\",\"price\":\"" + s[2].toString() + "\"}]");
			}
			a = bf.toString();

			System.out.println(a.replaceAll("\\]\\[", ","));
		} catch (HibernateException e) {
			e.printStackTrace();

		} finally {
			close();
		}
		return a.replaceAll("\\]\\[", ",");
	}

	public String findUsedcarbymodelandmark(String mark, String model) {
		String a = "";
		StringBuffer jsonObject = null;
		Gson gson = new Gson();
		try {
			Query query = getSession().createQuery(
					"select c.carid,c.color,c.price,c.bodytype,c.model.model, c.model.mark.mark ,c.year,c.miles from Usedcar c where c.model.mark.mark=:mark and c.model.model=:model and c.status=0");
			// [{"carid":"33","mark":"Benz","model":"c300","color":"red","bodytype":"sedan","price":"20000","year":"20000","miles":"20000"}]
			query.setString("mark", mark);
			System.out.println(
					"[{\"carid\":\"33\",\"mark\":\"Benz\",\"model\":\"c300\",\"color\":\"red\",\"bodytype\":\"sedan\",\"price\":\"20000\",\"year\":\"20000\",\"miles\":\"20000\"}]");
			query.setString("model", model);
			StringBuffer bf = new StringBuffer();
			for (Object q : query.list()) {
				Object[] s = (Object[]) q;
				bf.append("[{\"carid\":\"" + s[0].toString() + "\",\"mark\":\"" + s[5].toString() + "\",\"model\":\""
						+ s[4].toString() + "\",\"color\":\"" + s[1].toString() + "\",\"bodytype\":\"" + s[3].toString()
						+ "\",\"price\":\"" + s[2].toString() + "\",\"year\":\"" + s[6].toString() + "\",\"miles\":\""
						+ s[7].toString() + "\"}]");
			}
			a = bf.toString();

			System.out.println(a.replaceAll("\\]\\[", ","));
		} catch (HibernateException e) {
			e.printStackTrace();

		} finally {
			close();
		}
		return a.replaceAll("\\]\\[", ",");
	}

	public String searchcar() {
		String a = null;

		String jsonObject = null;
		try {
			Query query = getSession().createQuery("from Mark");
			// Gson gson = new Gson();
			// jsonObject=gson.toJson(query.list());
			// query.setString("mark", mark);
			a = query.list().toString();

		} catch (HibernateException e) {
			e.printStackTrace();

		} finally {
			close();
		}
		return a;
	}

	public void registermodel(String model, String mark) throws Exception {
		try {

			begin();
			System.out.println("inside DAO");
			Query q = getSession().createQuery("from Mark where mark = :mark");
			q.setString("mark", mark);
			Mark m = (Mark) q.uniqueResult();
			if (m != null) {
				Model mo = new Model(model, m);
				getSession().save(mo);
				commit();

			} else {
				Mark mar = new Mark(mark);
				Model mo = new Model(model, mar);
				getSession().save(mo);
				commit();

			}

		} catch (Exception e) {
			rollback();
			throw new Exception("Exception while creating user: " + e.getMessage());
		}
	}

	public Usedcar registerusedcar(String model,  int year, int miles,

			double liter, String color, String bodytype, int price,int userid) throws Exception {
		try {
			begin();
			   System.out.print("222222");
			Query q1 = getSession().createQuery("from Model m where m.model =:model");
			 System.out.print("111111");
			q1.setString("model", model);
			 System.out.print("144444");
			
			Model mo=(Model) q1.uniqueResult();
            System.out.print("111111");
			Usedcar car=new Usedcar();
            car.setModel(mo);
            car.setBodytype(bodytype);
            car.setColor(color);
            car.setMiles(miles);
            car.setYear(year);
            car.setPrice(price);
            car.setLiter(liter);
            car.setStatus(0);
            car.setSellerid(userid);
            System.out.print("33333");
			getSession().save(car);
			   System.out.print("444444");
			commit();

			return car;

		} catch (Exception e) {
			rollback();
			throw new Exception("Exception while creating user: " + e.getMessage());
		}
	}

	public Mark registermark(Mark c) throws Exception {
		try {
			begin();
			System.out.println("inside DAO");

			getSession().save(c);
			commit();

			return c;

		} catch (Exception e) {
			rollback();
			throw new Exception("Exception while creating user: " + e.getMessage());
		}
	}
	public ArrayList<CarInformation> getdatabypage(ArrayList<CarInformation> salelist, int page){
		ArrayList<CarInformation> newsalelist=new ArrayList<CarInformation>();
		for(int i=0;i<2;i++)
		{
			newsalelist.add(salelist.get((page-1)*2+i)); 
		}
		 return newsalelist;
	
	 }

}
