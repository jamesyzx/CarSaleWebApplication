package com.me.yzx.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.me.yzx.dao.DAOFactory;
import com.me.yzx.pojo.Car;
import com.me.yzx.pojo.CarInformation;
import com.me.yzx.pojo.Order;
import com.me.yzx.pojo.UsedCarInformation;
import com.me.yzx.pojo.User;

@Controller
public class ManageController {
	@Autowired
	DAOFactory daoFactory;

	@RequestMapping(value = "/**/manager/manager.htm", method = RequestMethod.GET)
	protected ModelAndView showmanager(HttpServletRequest request, HttpServletResponse response) throws Exception {

		return new ModelAndView("Manager");
	}

	@RequestMapping(value = "/**/cardata.htm", method = RequestMethod.GET)
	protected ModelAndView showcar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();

		ArrayList<CarInformation> carlist = new ArrayList<CarInformation>();
		carlist = (ArrayList<CarInformation>) daoFactory.createCarDao().listallcar();
		session.setAttribute("page", carlist.size());
		System.out.println(carlist.size());
		return new ModelAndView("CheckDatabase", "carlist", carlist);
	}

	@RequestMapping(value = "/**/newcardata.htm", method = RequestMethod.GET)
	protected ModelAndView shownewcar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		ArrayList<CarInformation> newcarlist = new ArrayList<CarInformation>();
		newcarlist = (ArrayList<CarInformation>) daoFactory.createCarDao().listnewcar();
		session.setAttribute("page", newcarlist.size());
		System.out.println(newcarlist.size());
		return new ModelAndView("CheckDatabase", "carlist", newcarlist);
	}

	@RequestMapping(value = "/**/usedcardata.htm", method = RequestMethod.GET)
	protected ModelAndView showusedcar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("dqwiohdoiqhdiqh");
		Map<String, Object> map = new HashMap<String, Object>();
		ArrayList<UsedCarInformation> usedcarlist = new ArrayList<UsedCarInformation>();

		usedcarlist = (ArrayList<UsedCarInformation>) daoFactory.createCarDao().listusedcar();
		System.out.print(usedcarlist);
		map.put("type", "usedcar");
		map.put("carlist", usedcarlist);
		return new ModelAndView("CheckDatabase", "map", map);
	}

	@RequestMapping(value = "/**/carorder.htm", method = RequestMethod.GET)
	protected ModelAndView showcarorder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ArrayList<Order> orderlist = new ArrayList<Order>();

		orderlist = (ArrayList<Order>) daoFactory.createOrderDao().listallorder();

		return new ModelAndView("CheckOrder", "orderlist", orderlist);
	}

	@RequestMapping(value = "/**/finishedorder.htm", method = RequestMethod.GET)
	protected ModelAndView showfinishedorder(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ArrayList<Order> orderlist = new ArrayList<Order>();

		orderlist = (ArrayList<Order>) daoFactory.createOrderDao().listfinishedorder();

		return new ModelAndView("finishedorder", "orderlist", orderlist);
	}

	@RequestMapping(value = "/**/removeorder.htm", method = RequestMethod.GET)
	protected ModelAndView removeorder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ArrayList<Order> orderlist = new ArrayList<Order>();
		Map<String, Object> map = new HashMap<String, Object>();

		String carid = request.getParameter("carid");
		System.out.println("diowhdoiqdoiqhodiq" + carid);
		daoFactory.createOrderDao().removeorder(Integer.parseInt(carid));
		daoFactory.createOrderDao().returncar(Integer.parseInt(carid));
		orderlist = (ArrayList<Order>) daoFactory.createOrderDao().listallorder();

		System.out.println("diowhdoiqdoiqhodiq2222");
		return new ModelAndView("CheckOrder", "orderlist", orderlist);
	}

	@RequestMapping(value = "/**/removeusedorder.htm", method = RequestMethod.GET)
	protected ModelAndView removeusedorder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ArrayList<Order> orderlist = new ArrayList<Order>();
		Map<String, Object> map = new HashMap<String, Object>();

		String carid = request.getParameter("carid");
		String userid = request.getParameter("seller");
		daoFactory.createOrderDao().removeorder(Integer.parseInt(carid));
		daoFactory.createOrderDao().returncar(Integer.parseInt(carid));

		daoFactory.createOrderDao().returnstatus(userid);
		map.put("user", "user");
		return new ModelAndView("user-dashboard", "map", map);
	}

	@RequestMapping(value = "/**/viewproductbypage.htm", method = RequestMethod.GET)
	protected ModelAndView viewproductbypage(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		ArrayList<CarInformation> pagecarlist = new ArrayList<CarInformation>();
		Map<String, Object> map = new HashMap<String, Object>();
		ArrayList<CarInformation> carlist = new ArrayList<CarInformation>();
		carlist = (ArrayList<CarInformation>) daoFactory.createCarDao().listallcar();

		pagecarlist = daoFactory.createCarDao().getdatabypage(carlist,
				Integer.parseInt(request.getParameter("pagenum")));
		session.setAttribute("present", request.getParameter("pagenum"));

		return new ModelAndView("CheckDatabase", "carlist", carlist);

	}

}
