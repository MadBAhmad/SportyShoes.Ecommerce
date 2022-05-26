package com.ecommerce.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecommerce.entity.Admin;
import com.ecommerce.service.AdminService;

@Controller
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@RequestMapping(value = "/loginaction", method = RequestMethod.POST)
	public String loginaction(ModelMap map, javax.servlet.http.HttpServletRequest request,
			@RequestParam(value="admin_id", required = true)String admminId,
			@RequestParam(value="admin_pwd", required = true)String admin_pwd)
	{
		 Admin admin = adminService.authenticate(admminId, admin_pwd);
		 if (admin==null){
			 map.addAttribute("error", "Admin Login Failed!");
			 return "admin/login";
		 }
		 //store admin ID in session
		 HttpSession session = request.getSession();
		 session.setAttribute("admin_id", admin.getID());
		 return "admin/dashboard";
		 
	}
}
