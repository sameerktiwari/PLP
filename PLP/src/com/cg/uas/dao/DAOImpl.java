package com.cg.uas.dao;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cg.uas.entities.Application;
import com.cg.uas.entities.Participant;
import com.cg.uas.entities.ProgramsOffered;
import com.cg.uas.entities.ProgramsScheduled;
import com.cg.uas.entities.Users;
import com.cg.uas.exception.UniversityException;

@Repository
@Transactional
public class DAOImpl implements IDAO {

	@PersistenceContext
	EntityManager entityManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cg.uas.dao.IDAO#validate(com.cg.uas.entities.Users)
	 */
	@Override
	public boolean validate(Users user) {
		TypedQuery<Users> query = entityManager
				.createQuery(
						"Select user from Users user where user.loginId=:ploginid and user.password=:ppwd and role=:prole",
						Users.class);
		query.setParameter("ploginid", user.getLoginId());
		query.setParameter("ppwd", user.getPassword());
		query.setParameter("prole", user.getRole());
		List<Users> users = query.getResultList();
		return users.isEmpty();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cg.uas.dao.IDAO#viewProgrammes()
	 */
	@Override
	public List<ProgramsScheduled> viewProgrammes() throws UniversityException {
		TypedQuery<ProgramsScheduled> query = entityManager.createQuery(
				"Select programs from ProgramsScheduled programs",
				ProgramsScheduled.class);
		if(query.getResultList().isEmpty())
			throw new UniversityException("No Programmes available");
		return query.getResultList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cg.uas.dao.IDAO#getProgramsOffered(java.lang.String)
	 */
	@Override
	public ProgramsOffered getProgramsOffered(String pname) throws UniversityException {

		String qStr = "SELECT e FROM ProgramsOffered e WHERE e.programName='"
				+ pname + "'";
		TypedQuery<ProgramsOffered> query = entityManager.createQuery(qStr,
				ProgramsOffered.class);
		ProgramsOffered programs = query.getSingleResult();
		if(programs.equals(null))
			throw new UniversityException("Incorrect Program Name or Programme Not Available");
		return programs;

	}

	/* (non-Javadoc)
	 * @see com.cg.uas.dao.IDAO#getStatus(int)
	 */
	@Override
	public Application getStatus(int appid) throws UniversityException {
    try
      {
		String qStr = "SELECT e FROM Application e WHERE e.applicationId=:pappid";
		TypedQuery<Application> query = entityManager.createQuery(qStr,
				Application.class);
		query.setParameter("pappid", appid);
		Application app = query.getSingleResult();
		return app;
       }
catch(Exception e)
{
		
		throw new UniversityException(e.getMessage());
	  
	}
	}

	/* (non-Javadoc)
	 * @see com.cg.uas.dao.IDAO#save(com.cg.uas.entities.Application)
	 */
	@Override
	public Application save(Application app) throws UniversityException {

		try
		{
		entityManager.persist(app);
		entityManager.flush(); // required to reflect changes on database
		return app;
		}
		catch(Exception e)
		{
			throw new UniversityException("Problem in persisting");
		}
	}

	/* (non-Javadoc)
	 * @see com.cg.uas.dao.IDAO#getApplicant(java.lang.String)
	 */
	@Override
	public List<Application> getApplicant(String programId) throws UniversityException {

		String qStr = "SELECT e FROM Application e WHERE e.scheduledProgramId=:pappid";
		TypedQuery<Application> query = entityManager.createQuery(qStr,
				Application.class);
		query.setParameter("pappid", programId);
		List<Application> app = query.getResultList();
		if(app.isEmpty())
			throw new UniversityException("No Applicant with thte given id");
		return app;
	}

	/* (non-Javadoc)
	 * @see com.cg.uas.dao.IDAO#modify(com.cg.uas.entities.Application, java.lang.String)
	 */
	@Override
	public Application modify(Application application, String status) throws UniversityException {
		try
		{
		application.setStatus(status);
		application = entityManager.merge(application);
		entityManager.flush(); // required to reflect changes on database
        return application;
		}
		catch(Exception e)
		{
			throw new UniversityException("Problem in Updating status");
		}
	}
	
	@Override
	public Application setInterview(Application application, Date dateOfInterview) throws UniversityException {
		try
		{
		application.setDateOfInterview(dateOfInterview);
		application = entityManager.merge(application);
		entityManager.flush(); // required to reflect changes on database
		return application;
		}
		catch(Exception e)
		{
			throw new UniversityException("Problem in Updating Date of Interview");
		}
	}
	
	@Override
	public Participant addParticipant(Participant ppt) throws UniversityException {

		try
		{
		entityManager.persist(ppt);
		entityManager.flush(); // required to reflect changes on database
        return ppt;
		}
		catch(Exception e)
		{
			throw new UniversityException("Problem in persisting participant");
		}
		
	}
}
