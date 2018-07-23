package com.me.yzx.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.me.yzx.pdf.PdfReportView;
import com.me.yzx.pojo.User;

@Controller
@RequestMapping("/report.pdf")
public class ReportController {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView createReport() {
		// Normally User will come from the database
		// User user = userdao.get();
		User user = new User();

		// will be passed to the View Page
		Map<String, User> model = new HashMap<String, User>();
		model.put("user", user);

		return new ModelAndView(new PdfReportView(), model);
	}

}
