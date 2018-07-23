package com.me.yzx.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.me.yzx.dao.DAOFactory;
import com.me.yzx.pojo.Mark;

@Controller
public class AjaxController {
	@Autowired
	DAOFactory daoFactory;
	ArrayList<String> courseList;

	public AjaxController() {

	}

	@RequestMapping(value = "/**/searchcarbyall.htm", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String searchcarbyall(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("diowqhdiqhdoiq");
		PrintWriter out = response.getWriter();
		System.out.println(daoFactory.createCarDao().searchcar());
		out.print(daoFactory.createCarDao().searchcar());

		return null;

	}

	@RequestMapping(value = "/**/searchcar.htm", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String searchcar(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String mark = request.getParameter("mark");
		String model = request.getParameter("model");
		String jsonObject = "";
		System.out.println(mark);
		System.out.println(model);
		jsonObject = daoFactory.createCarDao().findnewcarbymodelandmark(mark, model);
		String u = "{\"records\":" + jsonObject + "}";
		PrintWriter out = response.getWriter();
		out.print(u);
		System.out.println(u);

		return null;

	}

	@RequestMapping(value = "/**/searchusedcar.htm", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String searchusedcar(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String mark = request.getParameter("mark");
		String model = request.getParameter("model");
		String jsonObject = "";
		System.out.println(mark);
		System.out.println(model);
		jsonObject = daoFactory.createCarDao().findUsedcarbymodelandmark(mark, model);
		String u = "{\"records\":" + jsonObject + "}";
		PrintWriter out = response.getWriter();
		out.print(u);
		System.out.println(u);

		return null;

	}

	@RequestMapping(value = "/**/checkvalidate.htm", method = RequestMethod.GET)
	@ResponseBody
	public String checkvalidate(HttpServletRequest request, HttpServletResponse response)
			throws NumberFormatException, Exception {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		String query = request.getParameter("validatecode");
		String orderid = request.getParameter("orderid");
		String email = (String) session.getAttribute("userEmail");
		System.out.println(query);
		System.out.println(orderid);

		if ((daoFactory.createOrderDao().finishordervalidate(Integer.parseInt(orderid), query))) {
			daoFactory.createUserDao().finishvalidatecodeuser(email);
			System.out.println("122222");
			out.println("Congratulation!");
		} else {
			System.out.println("133331");
			out.println("ValidateCode and OrderId Mismatch!");
		}
		return null;

	}

	@RequestMapping(value = "/**/buynewcar.htm", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String buynewcar(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String mark = request.getParameter("mark");

		System.out.println(mark);

		// PrintWriter out=response.getWriter();
		// out.print(u);

		return null;

	}

	@RequestMapping(value = "/**/validatename.htm", method = RequestMethod.GET)
	public String checkname(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		String q = request.getParameter("username");
		System.out.print(q);
		if (!daoFactory.createUserDao().validate(q)) {
			out.println("Email valid");
		} else {

			out.println("Email has been registered");
		}

		return null;
	}
}
