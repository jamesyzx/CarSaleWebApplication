package com.me.yzx.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.me.yzx.dao.DAOFactory;
import com.me.yzx.pojo.User;

@Controller
public class AdminController {
	@Autowired
	DAOFactory daoFactory;

	@RequestMapping(value = "/**/admin/admin.htm", method = RequestMethod.GET)
	protected ModelAndView showmanager(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ArrayList<User> userlist = new ArrayList<User>();
		userlist = (ArrayList<User>) (daoFactory.createUserDao().listalluser());
		System.out.print(userlist);
		return new ModelAndView("Admin", "userlist", userlist);
	}

	@RequestMapping(value = "/**/removeuser.htm", method = RequestMethod.GET)
	protected String listalluser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ArrayList<User> userlist = new ArrayList<User>();
		System.out.println("fewdhewoifhewihfewo");
		System.out.println(request.getParameter("user"));

		String r = request.getParameter("user");
		if (r != null) {
			daoFactory.createUserDao().removeuser(r);
		}
		userlist = (ArrayList<User>) (daoFactory.createUserDao().listalluser());

		return "redirect:/admin/admin.htm";
	}

	@RequestMapping(value = "/**/adminnewuser.htm", method = RequestMethod.GET)
	protected ModelAndView createnewuser(String username, String password, String email, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ArrayList<User> userlist = new ArrayList<User>();

		if (!daoFactory.createUserDao().validate(username)) {
			System.out.println(username);
			System.out.println(password);
			User u = new User();
			u.setPassword(password);
			u.setRole(1);
			u.setStatus(1);
			u.setUseremail(email);
			u.setUsername(username);
			daoFactory.createUserDao().register(u);
		}
		userlist = (ArrayList<User>) (daoFactory.createUserDao().listalluser());
		return new ModelAndView("Admin", "userlist", userlist);
	}

}
