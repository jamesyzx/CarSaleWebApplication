package com.me.yzx.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.captcha.botdetect.web.servlet.Captcha;
import com.me.yzx.dao.DAOFactory;
import com.me.yzx.pdf.PdfReportView;
import com.me.yzx.pojo.Car;
import com.me.yzx.pojo.Order;
import com.me.yzx.pojo.UsedCarInformation;
import com.me.yzx.pojo.User;

/**
 * Handles requests for the application home page.
 */
@Controller
public class UserController {
	@Autowired
	DAOFactory daoFactory;

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@RequestMapping(value = "/**/login.htm", method = RequestMethod.GET)
	public String showLoginForm() {

		return "user-login";
	}

	@RequestMapping(value = "/**/backtohome.htm", method = RequestMethod.POST)
	public String backtohome() {

		return "user-login";
	}

	@RequestMapping(value = "/user/logout.htm", method = RequestMethod.GET)
	public ModelAndView logoutshowLoginForm(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();

		return new ModelAndView("redirect:/user/login.htm");
	}

	@RequestMapping(value = "/user/login.htm", method = RequestMethod.POST)
	public ModelAndView handleLoginForm(HttpServletRequest request, ModelMap map, Locale locale) {
		HttpSession session = request.getSession();
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		Map<String, Object> map1 = new HashMap<String, Object>();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		try {
			User u = daoFactory.createUserDao().get(username, password);

			if (u != null && u.getStatus() == 3) {
				session.setAttribute("username", u.getUsername());
				session.setAttribute("userEmail", u.getUseremail());
				ArrayList<Order> orderlist = new ArrayList<Order>();
				System.out.println(u.getId());
				orderlist = (ArrayList<Order>) daoFactory.createOrderDao().listusedcarorder(u.getId());
				System.out.println("dhwqiodhqiwhdq" + orderlist);
				map1.put("user", "checkusedcar");
				map1.put("orderlist", orderlist);
				return new ModelAndView("user-dashboard", "map", map1);
			} else if (u != null && u.getStatus() == 2) {
				session.setAttribute("username", u.getUsername());
				session.setAttribute("userEmail", u.getUseremail());
				map1.put("user", "validate");
				return new ModelAndView("user-dashboard", "map", map1);
			} else if (u != null && u.getStatus() == 1) {
				session.setAttribute("username", u.getUsername());
				session.setAttribute("userEmail", u.getUseremail());

				map1.put("user", "user");
				return new ModelAndView("user-dashboard", "map", map1);
			} else if (u != null && u.getStatus() == 0) {
				session.setAttribute("username", u.getUsername());
				session.setAttribute("userEmail", u.getUseremail());
				map.addAttribute("errorMessage", "Please activate your account to login!");
				return new ModelAndView("error");
			} else {
				map.addAttribute("errorMessage", "Invalid username/password!");
				return new ModelAndView("error");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	@RequestMapping(value = "/user/create.htm", method = RequestMethod.GET)
	public String showCreateForm() {

		return "user-create-form";
	}

	@RequestMapping(value = "/**/orderchangestatus.htm", method = RequestMethod.GET)
	protected ModelAndView orderchange(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		ArrayList<Order> orderlist = new ArrayList<Order>();
		Map<String, Object> map = new HashMap<String, Object>();

		String carid = request.getParameter("carid");
		String userid = request.getParameter("userid");
		String orderid = request.getParameter("orderid");
		daoFactory.createOrderDao().changestatus(Integer.parseInt(carid));

		User user = daoFactory.createUserDao().getbyuserid(Integer.parseInt(userid));
		daoFactory.createUserDao().validatecodeuser(user.getUseremail());
		orderlist = (ArrayList<Order>) daoFactory.createOrderDao().listallorder();

		try {

			Random rand = new Random();
			int randomNum1 = rand.nextInt(5000000);
			daoFactory.createOrderDao().addordervalidate(Integer.parseInt(carid), String.valueOf(randomNum1));
			try {

				sendEmailfinish(user.getUseremail(),
						"Congratulation! you buy the car, and you can carry your car in dealer now ! " + " "
								+ "validate information:" + " " + randomNum1 + " " + "OrderID:" + " " + orderid);

			} catch (Exception e) {
				System.out.println("Email cannot be sent");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ModelAndView("CheckOrder", "orderlist", orderlist);
	}

	@RequestMapping(value = "/**/buycarnow.htm", method = RequestMethod.GET)
	protected ModelAndView buycarnow(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		ArrayList<UsedCarInformation> carlist = new ArrayList<UsedCarInformation>();
		String username = (String) (session.getAttribute("username"));
		String userEmail = (String) session.getAttribute("useremail");
		session.setAttribute("username", username);
		session.setAttribute("useremail", userEmail);
		String carid = (String) session.getAttribute("carid");
		System.out.println("dwqiodqiwodq" + carid);
		User user = daoFactory.createUserDao().get(userEmail);
		Car car = daoFactory.createCarDao().getbycarid(Integer.parseInt(carid));
		daoFactory.createCartDao().deletecart(car);
		session.setAttribute("mark", car.getModel().getMark());
		session.setAttribute("model", car.getModel());
		daoFactory.createOrderDao().register(car, user);
		daoFactory.createOrderDao().updatecarstatus(car.getCarid());
		daoFactory.createOrderDao().updateuseduser(car);
		User userseller = daoFactory.createUserDao().getbyuserid(car.getSellerid());
		try {

			sendEmailfinish(userseller.getUseremail(),
					"Some People want to buy your, please login to approve the order ");

		} catch (Exception e) {
			System.out.println("Email cannot be sent");
		}

		Map<String, User> model = new HashMap<String, User>();
		model.put("user", user);

		return new ModelAndView(new PdfReportView(), model);

	}

	@RequestMapping(value = "/**/sellusercarform.htm", method = RequestMethod.GET)
	protected ModelAndView caruserform(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Map<String, Object> map = new HashMap<String, Object>();
		String modelmark = request.getParameter("model");
		System.out.println("dqwidjqpdjqw" + modelmark);
		String bodytype = request.getParameter("bodytype");
		String color = request.getParameter("color");
		String year = request.getParameter("year");
		String price = request.getParameter("price");
		String miles = request.getParameter("miles");
		String liter = request.getParameter("liter");
		String useremail = (String) session.getAttribute("userEmail");
		User user = daoFactory.createUserDao().get(useremail);
		System.out.println(bodytype);
		System.out.println(color);
		System.out.println(year);
		System.out.println(miles);
		System.out.println(liter);
		System.out.println(useremail);
		JSONObject jsonObject = new JSONObject(modelmark);
		String modelget = jsonObject.getString("model");
		System.out.println(modelget);

		daoFactory.createCarDao().registerusedcar(modelget, Integer.parseInt(year), Integer.parseInt(miles),
				Double.parseDouble(liter), color, bodytype, Integer.parseInt(price), user.getId());
		try {

			sendEmailfinish(user.getUseremail(),
					"Congratulation! Your car has been in Search Board,if some people want to buy, we will contact with you!  ");

		} catch (Exception e) {
			System.out.println("Email cannot be sent");
		}

		map.put("user", "user");
		return new ModelAndView("user-dashboard", "map", map);
	}

	@RequestMapping(value = "/**/orderusedchangestatus.htm", method = RequestMethod.GET)
	protected ModelAndView orderusedchange(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		ArrayList<Order> orderlist = new ArrayList<Order>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user", "user");
		String userid = request.getParameter("userid");
		String carid = request.getParameter("carid");
		String sellerid = request.getParameter("seller");
		String orderid = request.getParameter("orderid");
		daoFactory.createOrderDao().changestatus(Integer.parseInt(carid));

		daoFactory.createOrderDao().returnstatus(sellerid);
		User userseller = daoFactory.createUserDao().getbyuserid(Integer.parseInt(sellerid));
		User user = daoFactory.createUserDao().getbyuserid(Integer.parseInt(userid));
		daoFactory.createUserDao().validatecodeuser(user.getUseremail());

		try {

			Random rand = new Random();
			int randomNum1 = rand.nextInt(5000000);
			daoFactory.createOrderDao().addordervalidate(Integer.parseInt(carid), String.valueOf(randomNum1));

			try {

				sendEmailfinish(userseller.getUseremail(),
						"Congratulation! you have been sold the car! " + "OrderID:" + " " + orderid);

			} catch (Exception e) {
				System.out.println("Email cannot be sent");
			}
			try {

				sendEmailfinish(user.getUseremail(),
						"Congratulation! you buy the car, and you can carry your car in dealer now ! " + " "
								+ "validate information:" + " " + randomNum1 + " " + "OrderID:" + " " + orderid);

			} catch (Exception e) {
				System.out.println("Email cannot be sent");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ModelAndView("user-dashboard", "map", map);
	}

	@RequestMapping(value = "/user/create.htm", method = RequestMethod.POST)
	public String handleCreateForm(HttpServletRequest request, ModelMap map) {
		Captcha captcha = Captcha.load(request, "CaptchaObject");
		String captchaCode = request.getParameter("captchaCode");
		HttpSession session = request.getSession();
		String useremail = request.getParameter("useremail");
		String password = request.getParameter("password");
		String username = request.getParameter("username");
		if (daoFactory.createUserDao().validate(useremail)) {
			map.addAttribute("errorMessage", "Email has been registered!");
			return "user-create-form";
		} else {
			if (captcha.validate(captchaCode)) {

				User user = new User();
				user.setUseremail(useremail);
				user.setPassword(password);
				user.setUsername(username);
				user.setStatus(0);
				user.setRole(1);

				try {
					User u = daoFactory.createUserDao().register(user);
					Random rand = new Random();
					int randomNum1 = rand.nextInt(5000000);
					int randomNum2 = rand.nextInt(5000000);
					try {
						String str = "http://localhost:8080/lab10/user/validateemail.htm?email=" + useremail + "&key1="
								+ randomNum1 + "&key2=" + randomNum2;
						session.setAttribute("key1", randomNum1);
						session.setAttribute("key2", randomNum2);
						sendEmail(useremail, "Click on this link to activate your account : " + str);
					} catch (Exception e) {
						System.out.println("Email cannot be sent");
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				map.addAttribute("errorMessage", "Invalid Captcha!");
				return "user-create-form";
			}
		}

		return "user-created";
	}

	@RequestMapping(value = "/user/forgotpassword.htm", method = RequestMethod.GET)
	public String getForgotPasswordForm(HttpServletRequest request) {

		return "forgot-password";
	}

	@RequestMapping(value = "/user/forgotpassword.htm", method = RequestMethod.POST)
	public String handleForgotPasswordForm(HttpServletRequest request) {

		String useremail = request.getParameter("useremail");
		Captcha captcha = Captcha.load(request, "CaptchaObject");
		String captchaCode = request.getParameter("captchaCode");

		if (captcha.validate(captchaCode)) {
			User user = daoFactory.createUserDao().get(useremail);
			sendEmail(useremail, "Your password is : " + user.getPassword());
			return "forgot-password-success";
		} else {
			request.setAttribute("captchamsg", "Captcha not valid");
			return "forgot-password";
		}
	}

	@RequestMapping(value = "user/resendemail.htm", method = RequestMethod.POST)
	public String resendEmail(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String useremail = request.getParameter("username");
		Random rand = new Random();
		int randomNum1 = rand.nextInt(5000000);
		int randomNum2 = rand.nextInt(5000000);
		try {
			String str = "http://localhost:8080/lab10/user/validateemail.htm?email=" + useremail + "&key1=" + randomNum1
					+ "&key2=" + randomNum2;
			session.setAttribute("key1", randomNum1);
			session.setAttribute("key2", randomNum2);
			sendEmail(useremail, "Click on this link to activate your account : " + str);
		} catch (Exception e) {
			System.out.println("Email cannot be sent");
		}

		return "user-created";
	}

	public void sendEmail(String useremail, String message) {
		try {
			Email email = new SimpleEmail();
			email.setHostName("smtp.googlemail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("contactapplication2018@gmail.com", "springmvc"));

			email.setSSLOnConnect(true);
			email.setFrom("no-reply@msis.neu.edu"); // This user email does not
													// exist
			email.setSubject("Password Reminder");
			email.setMsg(message); // Retrieve email from the DAO and send this
			email.addTo(useremail);
			email.send();
		} catch (EmailException e) {
			System.out.println("Email cannot be sent");
		}
	}

	public void sendEmailfinish(String useremail, String message) {
		try {
			Email email = new SimpleEmail();
			email.setHostName("smtp.googlemail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("contactapplication2018@gmail.com", "springmvc"));

			email.setSSLOnConnect(true);
			email.setFrom("no-reply@msis.neu.edu"); // This user email does not
													// exist
			email.setSubject("Car Dealer Information");
			email.setMsg(message); // Retrieve email from the DAO and send this
			email.addTo(useremail);
			email.send();
		} catch (EmailException e) {
			System.out.println("Email cannot be sent");
		}
	}

	@RequestMapping(value = "user/validateemail.htm", method = RequestMethod.GET)
	public String validateEmail(HttpServletRequest request, ModelMap map) {

		// The user will be sent the following link when the use registers
		// This is the format of the email
		// http://hostname:8080/lab10/user/validateemail.htm?email=useremail&key1=<random_number>&key2=<body
		// of the email that when user registers>
		HttpSession session = request.getSession();
		String email = request.getParameter("email");
		int key1 = Integer.parseInt(request.getParameter("key1"));
		int key2 = Integer.parseInt(request.getParameter("key2"));
		System.out.println(session.getAttribute("key1"));
		System.out.println(session.getAttribute("key2"));

		if ((Integer) (session.getAttribute("key1")) == key1 && ((Integer) session.getAttribute("key2")) == key2) {
			try {
				System.out.println("HI________");
				boolean updateStatus = daoFactory.createUserDao().updateUser(email);
				if (updateStatus) {
					return "user-login";
				} else {

					return "error";
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			map.addAttribute("errorMessage", "Link expired , generate new link");
			map.addAttribute("resendLink", true);
			return "error";
		}

		return "user-login";

	}

}
