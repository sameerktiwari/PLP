package com.cg.uas.service;

import java.util.List;

import com.cg.uas.entities.ProgramsScheduled;
import com.cg.uas.entities.Users;

public interface IService {

	public boolean validate(Users user);
	public List<ProgramsScheduled> viewProgrammes();
}
