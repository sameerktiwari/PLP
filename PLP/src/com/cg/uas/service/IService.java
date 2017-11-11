package com.cg.uas.service;

import java.sql.Date;
import java.util.List;

import com.cg.uas.entities.Application;
import com.cg.uas.entities.Participant;
import com.cg.uas.entities.ProgramsOffered;
import com.cg.uas.entities.ProgramsScheduled;
import com.cg.uas.entities.Users;

public interface IService {

	/**
	 * @param user
	 * @return
	 */
	public boolean validate(Users user);
	/**
	 * @return
	 */
	public List<ProgramsScheduled> viewProgrammes();
	/**
	 * @param pname
	 * @return
	 */
	public ProgramsOffered getProgramsOffered(String pname);
	/**
	 * @param appid
	 * @return
	 */
	public Application getStatus(int appid);
	/**
	 * @param app
	 * @return
	 */
	public Application save(Application app);
	public List<Application> getApplicant(String programId);
	public Application modify(Application application,String status);
	public Application setInterview(Application application, Date dateOfInterview);
	public Participant addParticipant(Participant ppt); 
}
