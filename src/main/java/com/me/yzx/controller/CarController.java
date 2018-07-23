package com.me.yzx.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.me.yzx.dao.DAOFactory;
import com.me.yzx.pdf.PdfReportView;
import com.me.yzx.pojo.Car;
import com.me.yzx.pojo.CarInformation;
import com.me.yzx.pojo.Model;
import com.me.yzx.pojo.UsedCarInformation;
import com.me.yzx.pojo.User;

@Controller
public class CarController {
	@Autowired
	DAOFactory daoFactory;

	@RequestMapping(value = "/**/searchcar.htm", method = RequestMethod.GET)
	protected ModelAndView searchcar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ArrayList<CarInformation> carlist = new ArrayList<CarInformation>();
		daoFactory.createCarDao().getbymark("");
		return new ModelAndView("user-dashboard", "userlist", carlist);
	}

	@RequestMapping(value = "/**/buynewcar.htm", method = RequestMethod.GET)
	protected ModelAndView buynewcar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", "newcar");
		return new ModelAndView("user-dashboard", "map", map);
	}

	@RequestMapping(value = "/**/buyusedcar.htm", method = RequestMethod.GET)
	protected ModelAndView buyusedcar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", "usedcar");
		return new ModelAndView("user-dashboard", "map", map);
	}

	@RequestMapping(value = "/**/sellcar.htm", method = RequestMethod.GET)
	protected ModelAndView sellcar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", "sellcar");
		return new ModelAndView("user-dashboard", "map", map);
	}

	@RequestMapping(value = "/**/addtocartnewcar.htm", method = RequestMethod.GET)
	protected ModelAndView addtocart(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		ArrayList<CarInformation> carlist = new ArrayList<CarInformation>();
		String username = (String) (session.getAttribute("username"));
		String userEmail = (String) session.getAttribute("userEmail");
		session.setAttribute("username", username);
		session.setAttribute("useremail", userEmail);
		String carid = request.getParameter("id");
		System.out.println("email" + userEmail);
		System.out.println("username" + username);

		User user = daoFactory.createUserDao().get(userEmail);
		System.out.println("user" + user.getId());
		Car car = daoFactory.createCarDao().getbycarid(Integer.parseInt(carid));
		session.setAttribute("carid", carid);
		daoFactory.createCartDao().register(car, user);
		carlist = (ArrayList<CarInformation>) daoFactory.createCarDao().getnewcarbycarid(Integer.parseInt(carid));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("carlist", carlist);
		map.put("type", "newcar");
		return new ModelAndView("buycar", "map", map);
	}

	@RequestMapping(value = "/**/addtocart.htm", method = RequestMethod.GET)
	protected ModelAndView addtocartusecar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		ArrayList<UsedCarInformation> carlist = new ArrayList<UsedCarInformation>();
		String username = (String) (session.getAttribute("username"));
		String userEmail = (String) session.getAttribute("userEmail");
		session.setAttribute("username", username);
		session.setAttribute("useremail", userEmail);
		String carid = request.getParameter("id");
		User user = daoFactory.createUserDao().get(userEmail);
		Car car = daoFactory.createCarDao().getbycarid(Integer.parseInt(carid));
		session.setAttribute("carid", carid);
		System.out.println("dwqiodhqoidq" + user);
		daoFactory.createCartDao().register(car, user);
		carlist = (ArrayList<UsedCarInformation>) daoFactory.createCarDao().getusedcarbycarid(Integer.parseInt(carid));
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println(carlist);
		map.put("carlist", carlist);
		map.put("type", "usedcar");
		return new ModelAndView("buycar", "map", map);
	}

	@RequestMapping(value = "/**/notbuycar.htm", method = RequestMethod.GET)
	protected ModelAndView notbuycar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.print(request.getParameter("id"));
		map.put("user", "user");
		return new ModelAndView("user-dashboard", "map", map);
	}

}
