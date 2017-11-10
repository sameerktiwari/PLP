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
	
	/* (non-Javadoc)
	 * @see com.cg.uas.service.IService#validate(com.cg.uas.entities.Users)
	 */
	@Override
	public boolean validate(Users user){
		return dao.validate(user);
	}
	
	/* (non-Javadoc)
	 * @see com.cg.uas.service.IService#viewProgrammes()
	 */
	@Override
	public List<ProgramsScheduled> viewProgrammes(){
		return dao.viewProgrammes();
	}

	/* (non-Javadoc)
	 * @see com.cg.uas.service.IService#getProgramsOffered(java.lang.String)
	 */
	@Override
	public ProgramsOffered getProgramsOffered(String pname) {
		return dao.getProgramsOffered(pname);
	}

	/* (non-Javadoc)
	 * @see com.cg.uas.service.IService#getStatus(int)
	 */
	@Override
	public Application getStatus(int appid) {
	return dao.getStatus(appid);
	}

	/* (non-Javadoc)
	 * @see com.cg.uas.service.IService#save(com.cg.uas.entities.Application)
	 */
	@Override
	public Application save(Application app) {
		
		return dao.save(app);
	}

	@Override
	public List<Application> getApplicant(String programId) {
	
		return dao.getApplicant(programId);
	}

	@Override
	public Application modify(Application application,String status) {
		
		return dao.modify(application,status);
	}
}
