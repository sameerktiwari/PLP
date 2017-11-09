package com.cg.uas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.uas.dao.DAOImpl;
import com.cg.uas.dao.IDAO;
import com.cg.uas.entities.Application;
import com.cg.uas.entities.ProgramsOffered;
import com.cg.uas.entities.ProgramsScheduled;
import com.cg.uas.entities.Users;

@Service
public class ServiceImpl implements IService{
	@Autowired
	IDAO dao;
	
	@Override
	public boolean validate(Users user){
		return dao.validate(user);
	}
	
	@Override
	public List<ProgramsScheduled> viewProgrammes(){
		return dao.viewProgrammes();
	}

	@Override
	public ProgramsOffered getProgramsOffered(String pname) {
		return dao.getProgramsOffered(pname);
	}

	@Override
	public Application getStatus(int appid) {
	return dao.getStatus(appid);
	}

	@Override
	public Application save(Application app) {
		
		return dao.save(app);
	}

	@Override
	public List<Application> getApplicant(String programId) {
	
		return dao.getApplicant(programId);
	}
}
