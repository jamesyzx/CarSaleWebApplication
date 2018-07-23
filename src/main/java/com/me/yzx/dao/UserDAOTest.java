package com.me.yzx.dao;

import static org.junit.Assert.*;

import java.io.PrintWriter;
import java.sql.Date;
import java.text.DateFormat;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.me.yzx.pojo.Car;
import com.me.yzx.pojo.CarInformation;
import com.me.yzx.pojo.Cart;
import com.me.yzx.pojo.Mark;
import com.me.yzx.pojo.Model;
import com.me.yzx.pojo.Newcar;
import com.me.yzx.pojo.Order;
import com.me.yzx.pojo.OrderRecord;
import com.me.yzx.pojo.UsedCarInformation;
import com.me.yzx.pojo.Usedcar;
import com.me.yzx.pojo.User;

public class UserDAOTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testUserDAO() throws Exception {
	     CartDAO cartdao=new CartDAO();
         	OrderDAO orderdao=new OrderDAO();
		UserDAO userdao=new UserDAO();
		CarDAO cardao=new CarDAO();
//		User u=new User("1","2","5@gmail.com","4",1);
//		userdao.register(u);
//		Mark mark=new Mark("BMW");
//		Model model=new Model("X3",mark);
//		Car car=new Car(3.0,"sedan",35000,"red",model);
//		cardao.registermark(mark);
//		cardao.registermodel(model);
//		cardao.registercar(car);
//	    Cart cart=new Cart();
//		
//	
//		OrderRecord orderRecord=new OrderRecord(100,u,car,"pending");
//		c.register(cart,car,u);
//	    o.register(orderRecord,car,u);
//		for(Object o:cardao.listcar()) {
//			Object[] s =(Object[]) o;
//			
//			System.out.println(s[2].toString());
//			Model m = (Model) s[1];
//			System.out.println(m.getModel());
//	        ArrayList<CarInformation>carlist=new ArrayList<CarInformation>();  
//		for(Object o: cardao.test())
//		{
//			Object[] s =(Object[]) o;
//			System.out.println(s[1].toString());
//		}
	
//	ArrayList<Integer> carid=new ArrayList<Integer>();
//	for(Object i:cardao.test())
//	{
//		System.out.println("dwqjdpoqwj");
//		System.out.println(i.toString());
//		carid.add((Integer.parseInt((i.toString()))));
//	}
//			CarInformation c =new CarInformation();
//			c.setCarid(Integer.parseInt((s[0]).toString()));
//			c.setBodytype((s[3].toString()));
//			c.setColor(s[2].toString());
//			c.setLiter(Double.parseDouble((s[1]).toString()));
//			c.setModel(s[4].toString());
//			c.setMark(s[5].toString());
//			c.setPrice(Integer.parseInt((s[6]).toString()));
//			carlist.add(c);
//		}
//		
//		for(CarInformation c:cardao.listnewcar())
//		{
//			System.out.println(c);
//		}
// ArrayList<Usedcar>newcarlist=new ArrayList<Usedcar>();
//		newcarlist=(ArrayList<Usedcar>) cardao.listusedcarsimple() ;
//		       
//		         System.out.println("dwqdoiqh"+newcarlist.size());
//		          Gson gson = new Gson();
//		          System.out.println("dnwqoidhqoi");
//					String jsonObject = gson.toJson(newcarlist);
//					System.out.println("dnwqoidhqoi");
//					System.out.println(jsonObject);
//					String u = "{\"records\":"+jsonObject + "}";
//					System.out.println("dnwqoidhqoi");
				//System.out.println(cardao.testnew());
//		 User u=userdao.get("2@gmail.com");
//	     Car car=cardao.getbycarid(Integer.parseInt("34"));
//	     System.out.println(u);
//	     System.out.println(car);
//	     cartdao.register(car, u);
	//	 Car car=cardao.getbycarid(Integer.parseInt("37"));
	
//		 User user=userdao.get("yu.zix@husky.neu.edu");
//	     Car car=cardao.getbycarid(Integer.parseInt("39"));
//	   cartdao.register(car, user); 
	   //  cartdao.deletecart(car);
	   //orderdao.updatecarstatus(car.getCarid());
	//	orderdao.register(car, user);
//	orderdao.removeorder(car.getCarid());	
	   // System.out.println(car.getModel().getMark());
		
	//	orderdao.finishordervalidate(Integer.parseInt("25"), "2717060");
	// cardao.registerusedcar("c300", 2016, 1,3.0, "red", "sedan", 15000,171);
	//	User user=userdao.get("yu.zix@husky.neu.edu");
	   Car car=cardao.getbycarid(Integer.parseInt("46"));
//	     System.out.print(user);
		orderdao.updateuseduser(car);
		System.out.println( orderdao.listusedcarorder(171));  
	}
}
