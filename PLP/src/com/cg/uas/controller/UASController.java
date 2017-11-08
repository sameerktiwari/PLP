package com.cg.uas.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cg.uas.dao.DAOImpl;
import com.cg.uas.entities.Application;
import com.cg.uas.entities.ProgramsOffered;
import com.cg.uas.entities.ProgramsScheduled;
import com.cg.uas.entities.Users;
import com.cg.uas.service.IService;
import com.cg.uas.service.ServiceImpl;

@Controller
public class UASController {
	@Autowired
	private IService service;
	
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
			if(!service.validate(users)){
				return users.getRole();
			}
			else{
				model.addAttribute("msg","Invalid Username or Password");
				return "error";
			}
		}
		
	}
	
	@RequestMapping("/applicant")
	public String applicant(Model model)
	{
		return "applicant";
	}
	
	@RequestMapping("/viewprgrms")
	public String viewprgrms(Model model)
	{
		List<ProgramsScheduled> pList=service.viewProgrammes();
		model.addAttribute("programList",pList);
		ProgramsScheduled programs=new ProgramsScheduled();
		model.addAttribute("ProgramsScheduled",programs);
		return "viewProgram";
	}
	
	@RequestMapping(value="/programDetails",method=RequestMethod.GET)
	public String programDetails(@RequestParam("pName") String pName,@RequestParam("pId") String pId,Model model)
	{
		ProgramsOffered pos=service.getProgramsOffered(pName);
		model.addAttribute("prog",pos);
		model.addAttribute("pId", pId);
		return "programDetail";
	}
	
	@RequestMapping(value="/apply",method=RequestMethod.GET)
	public String apply(@RequestParam("pId") String pId,Model model)
	{
		Application app=new Application();
		model.addAttribute("pId", pId);
		model.addAttribute("Application",app);
		return "application";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(@ModelAttribute("Application") @Valid Application app,Model model,BindingResult result)
	{
		System.out.println(app);
		return "application";
	}
}
