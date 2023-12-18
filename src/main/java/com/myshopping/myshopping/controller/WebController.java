package com.myshopping.myshopping.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class WebController {

	@RequestMapping(path="/signup",method = RequestMethod.GET)
	public ModelAndView signupPage() 
	{
		return new ModelAndView("User.html");
	}
	@RequestMapping(path="/login",method = RequestMethod.GET)
	public ModelAndView loginUser() {
		return new ModelAndView("Login.html");
	}
	@RequestMapping(path="/homepage",method = RequestMethod.GET)
	public ModelAndView homePage() {
		return new ModelAndView("Home.html");
	}
	@RequestMapping(path="/dashboard",method = RequestMethod.GET)
	public ModelAndView dashboard() {
		return new ModelAndView("Dashboard.html");
	}
	
	@RequestMapping(path="/cart",method = RequestMethod.GET)
	public ModelAndView cartPage() {
		return new ModelAndView("Cart");
	}
	@RequestMapping(path="/myorder",method = RequestMethod.GET)
	public ModelAndView myorderPage() {
		return new ModelAndView("Myorder");
	}
	@RequestMapping(path="/myprofile",method = RequestMethod.GET)
	public String myProfilePage() {
		return "Myprofile.html";
	}
	
	@RequestMapping(path="/changepassword",method = RequestMethod.GET)
	public String changePassword() {
		return "Changepassword.html";
	}
	
	@RequestMapping(path="/forgetpassword",method = RequestMethod.GET)
	public String forgetPassword() {
		return "Forgetpassword.html";
	}
}
