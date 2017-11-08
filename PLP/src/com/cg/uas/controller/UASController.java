package com.cg.uas.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cg.uas.dao.DAOImpl;
import com.cg.uas.entities.Users;

@Controller
public class UASController {
	private DAOImpl dao=new DAOImpl();
	
	@RequestMapping("/login")
	public String login(@RequestParam("role") String role,Model model)
	{
		Users users=new Users();
		model.addAttribute("users",users);
		users.setRole(role);
		return "login";
	}
	
	@RequestMapping("/validate")
	public String validate(Model model,@ModelAttribute("users") Users users,BindingResult result)
	{
		if(result.hasErrors()){
			return "login";
		}
		else{
			
		}
	}
}
