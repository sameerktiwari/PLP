package com.cg.uas.service;

import java.util.List;

import com.cg.uas.entities.Application;
import com.cg.uas.entities.ProgramsOffered;
import com.cg.uas.entities.ProgramsScheduled;
import com.cg.uas.entities.Users;

public interface IService {

	public boolean validate(Users user);
	public List<ProgramsScheduled> viewProgrammes();
	public ProgramsOffered getProgramsOffered(String pname);
	public Application getStatus(int appid);
	public Application save(Application app);
}
