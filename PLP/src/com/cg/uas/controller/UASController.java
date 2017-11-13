package com.cg.uas.controller;

import java.sql.Date;
import java.time.LocalDate;
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

import com.cg.uas.entities.Application;
import com.cg.uas.entities.Participant;
import com.cg.uas.entities.ProgramsOffered;
import com.cg.uas.entities.ProgramsScheduled;
import com.cg.uas.entities.Users;
import com.cg.uas.exception.UniversityException;
import com.cg.uas.service.IService;

@Controller
public class UASController {
	@Autowired
	private IService service;

	@RequestMapping("/login")
	public String login(@RequestParam("role") String role, Model model) {
		Users users = new Users();
		model.addAttribute("users", users);
		users.setRole(role);
		return "login";
	}

	@RequestMapping("/validate")
	public String validate(Model model, @ModelAttribute("users") Users users,
			BindingResult result) {
		if (result.hasErrors()) {
			return "login";
		} else {

			if (!service.validate(users)) {
				return users.getRole();
			} else {
				model.addAttribute("msg", "Invalid Username or Password");
				return "error";
			}

		}

	}

	@RequestMapping("/applicant")
	public String applicant(Model model) {
		return "applicant";
	}

	@RequestMapping("/viewprgrms")
	public String viewprgrms(Model model) {

		try {
			List<ProgramsScheduled> programsScheduled = service
					.viewProgrammes();
			model.addAttribute("programList", programsScheduled);
			ProgramsScheduled programs = new ProgramsScheduled();
			model.addAttribute("ProgramsScheduled", programs);
			return "viewProgram";
		} catch (UniversityException e) {
			model.addAttribute("msg", e.getMessage());
			return "error";

		}
	}

	@RequestMapping(value = "/programDetails", method = RequestMethod.GET)
	public String programDetails(@RequestParam("pName") String pName,
			@RequestParam("pId") String pId, Model model) {
		try {
			ProgramsOffered pos = service.getProgramsOffered(pName);
			model.addAttribute("prog", pos);
			model.addAttribute("pId", pId);
			return "programDetail";
		} catch (UniversityException e) {
			model.addAttribute("msg", e.getMessage());
			return "error";
		}
	}

	@RequestMapping("/apply")
	public String apply(@RequestParam("pId") String pId, Model model) {
		Application app = new Application();
		model.addAttribute("pId", pId);
		model.addAttribute("Application", app);
		return "application";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@ModelAttribute("Application") @Valid Application app,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("Application", app);
			return "application";
		} else {
			try {
				Application ap = service.save(app);
				model.addAttribute("applicant", ap);
				return "success";
			} catch (UniversityException e) {
				model.addAttribute("msg", e.getMessage());
				return "error";
			}
		}

	}

	@RequestMapping("/viewstatus")
	public String viewstatus() {
		return "viewStatus";
	}

	@RequestMapping("/getStatus")
	public String getStatus(@RequestParam("appId") String appId, Model model) {
		try {
			Application app = service.getStatus(Integer.parseInt(appId));
			model.addAttribute("applicant", app);
			return "viewStatus";

		} catch (NumberFormatException e) {
			model.addAttribute("msg", e.getMessage());
			return "error";
		} catch (UniversityException e) {
			model.addAttribute("msg", e.getMessage());
			return "error";
		}
	}

	@RequestMapping("/viewapps")
	public String viewapps(Model model) {
		try {
			List<ProgramsScheduled> programsScheduled = service
					.viewProgrammes();
			model.addAttribute("programList", programsScheduled);
			ProgramsScheduled programs = new ProgramsScheduled();
			model.addAttribute("ProgramsScheduled", programs);
			return "viewProgramForMAC";
		} catch (UniversityException e) {
			model.addAttribute("msg", e.getMessage());
			return "error";
		}
	}

	@RequestMapping("/viewApplications")
	public String viewapplications(
			Model model,
			@ModelAttribute("ProgramsScheduled") ProgramsScheduled ProgramsScheduled,
			BindingResult result) {

		try {
			List<Application> applications = service
					.getApplicant(ProgramsScheduled.getScheduledProgrammeId());
			model.addAttribute("appList", applications);
			if (applications.isEmpty())
				throw new UniversityException("No applications submitted");
			Application app = new Application();
			model.addAttribute("Application", app);
			return "viewApplications";
		} catch (UniversityException e) {
			model.addAttribute("msg", e.getMessage());
			return "error";
		}
	}

	@RequestMapping("/viewApplication")
	public String viewapplication(Model model,
			@ModelAttribute("Application") Application app, BindingResult result) {
		model.addAttribute("applicant", app);
		return "viewApplication";
	}

	@RequestMapping("/updateStatus")
	public String updateStatus(@RequestParam("appId") int appId,
			@RequestParam("status") String status, Model model) {
		try {
			Application app = service.getStatus(appId);
			if (("Pending").equals(app.getStatus())) {
				if (("Accepted").equals(status)) {
					model.addAttribute("showDOI", "y");
					model.addAttribute("applicant", app);
					Application application = new Application();
					model.addAttribute("Application", application);
					return "viewApplication";
				} else if (("Rejected").equals(status)) {
					app = service.modify(app, status);
					model.addAttribute("msg", "Application " + appId
							+ " rejected");
					model.addAttribute("applicant", app);
					return "viewApplication";
				} else {
					model.addAttribute("msg", "Not Applicable");
					model.addAttribute("applicant", app);
					return "viewApplication";
				}
			} else if (("Accepted").equals(app.getStatus())) {
				if (("Confirmed").equals(status)) {
					if (app.getDateOfInterview().before(
							Date.valueOf(LocalDate.now()))) {
						Participant ppt = new Participant(app.getEmail(),
								app.getApplicationId(), Integer.parseInt(app
										.getScheduledProgramId()));
						service.addParticipant(ppt);
						app = service.modify(app, status);
						model.addAttribute("applicant", app);
						model.addAttribute("msg", "Applicant Confirmed");
						model.addAttribute("applicant", app);
						return "viewApplication";
					} else {
						model.addAttribute("msg", "Pending Interview Results");
						model.addAttribute("applicant", app);
						return "viewApplication";
					}
				} else if (("Rejected").equals(status)) {
					if (app.getDateOfInterview().before(
							Date.valueOf(LocalDate.now()))) {
						app = service.modify(app, status);
						model.addAttribute("msg", "Application " + appId
								+ " rejected");
						model.addAttribute("applicant", app);
						return "viewApplication";
					} else {
						model.addAttribute("msg", "Pending Interview Results");
						model.addAttribute("applicant", app);
						return "viewApplication";
					}
				} else {
					model.addAttribute("msg", "Not Applicable");
					model.addAttribute("applicant", app);
					return "viewApplication";
				}
			} else {
				model.addAttribute("msg", "Not Applicable");
				model.addAttribute("applicant", app);
				return "viewApplication";
			}
		} catch (UniversityException e) {
			model.addAttribute("msg", e.getMessage());
			return "error";
		}
	}

	@RequestMapping(value = "/setInterview", method = RequestMethod.POST)
	public String setInterview(Model model,
			@ModelAttribute("Application") @Valid Application app,
			BindingResult result) {
		try {
			if (result.hasErrors()) {
				model.addAttribute("applicant", app);
				model.addAttribute("showDOI", "y");
				return "viewApplication";
			} else {

				app = service.modify(app, "Accepted");
				model.addAttribute("applicant", app);
				model.addAttribute("msg",
						"Application " + app.getApplicationId()
								+ " accepted and Interview Scheduled");
				model.addAttribute("applicant", app);
				return "viewApplication";
			}
		} catch (UniversityException e) {
			model.addAttribute("msg", e.getMessage());
			return "error";

		}
	}
	
	@RequestMapping("/viewAdminPrgrms")
	public String viewAdminPrgrms(Model model) {
		try {
			List<ProgramsScheduled> programsScheduled = service
					.viewProgrammes();
			model.addAttribute("programList", programsScheduled);
			ProgramsScheduled programs = new ProgramsScheduled();
			model.addAttribute("ProgramsScheduled", programs);
			return "viewProgramForAdmin";
		} catch (UniversityException e) {
			model.addAttribute("msg", e.getMessage());
			return "error";

		}
	}
	
	@RequestMapping("/updatePrgrm")
	public String updatePrgrm(@RequestParam("pId") String pId,Model model) {
		try {
			ProgramsScheduled programsScheduled = service.getProgram(pId);
			model.addAttribute("prog", programsScheduled);
			ProgramsScheduled program = new ProgramsScheduled();
			model.addAttribute("ProgramsScheduled", program);
			return "updateProgram";
		} catch (UniversityException e) {
			model.addAttribute("msg", e.getMessage());
			return "error";

		}
	}
}
