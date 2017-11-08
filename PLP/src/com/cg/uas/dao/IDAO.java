package com.cg.uas.dao;

import java.util.List;

import com.cg.uas.entities.ProgramsScheduled;
import com.cg.uas.entities.Users;

public interface IDAO {

	public boolean validate(Users user);
	public List<ProgramsScheduled> viewProgrammes();
}
