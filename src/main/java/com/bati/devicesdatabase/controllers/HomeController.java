package com.bati.devicesdatabase.controllers;

import java.util.Locale;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles requests for the application home page.
 */
@Controller
//@SessionAttributes(value = {"myBean"})
public class HomeController {
	
//	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "home";
	}
	
	@RequestMapping(value = "/login")
	public String login(@RequestParam Map<String, String> params) {
		return "login";
	}
	
	@RequestMapping(value = "/logout")
	public String looutin(@RequestParam Map<String, String> params) {
		return "login";
	}	
}
