package com.cg.uas.dao;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cg.uas.entities.Application;
import com.cg.uas.entities.ProgramsOffered;
import com.cg.uas.entities.ProgramsScheduled;
import com.cg.uas.entities.Users;

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
		List<Users> userList = query.getResultList();
		return userList.isEmpty();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cg.uas.dao.IDAO#viewProgrammes()
	 */
	@Override
	public List<ProgramsScheduled> viewProgrammes() {
		TypedQuery<ProgramsScheduled> query = entityManager.createQuery(
				"Select programs from ProgramsScheduled programs",
				ProgramsScheduled.class);

		return query.getResultList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cg.uas.dao.IDAO#getProgramsOffered(java.lang.String)
	 */
	@Override
	public ProgramsOffered getProgramsOffered(String pname) {

		String qStr = "SELECT e FROM ProgramsOffered e WHERE e.programName='"
				+ pname + "'";
		TypedQuery<ProgramsOffered> query = entityManager.createQuery(qStr,
				ProgramsOffered.class);
		ProgramsOffered programs = query.getSingleResult();
		return programs;

	}

	/* (non-Javadoc)
	 * @see com.cg.uas.dao.IDAO#getStatus(int)
	 */
	@Override
	public Application getStatus(int appid) {

		String qStr = "SELECT e FROM Application e WHERE e.applicationId=:pappid";
		TypedQuery<Application> query = entityManager.createQuery(qStr,
				Application.class);
		query.setParameter("pappid", appid);
		Application app = query.getSingleResult();
		return app;

	}

	/* (non-Javadoc)
	 * @see com.cg.uas.dao.IDAO#save(com.cg.uas.entities.Application)
	 */
	@Override
	public Application save(Application app) {

		entityManager.persist(app);
		entityManager.flush(); // required to reflect changes on database

		return app;
	}

	/* (non-Javadoc)
	 * @see com.cg.uas.dao.IDAO#getApplicant(java.lang.String)
	 */
	@Override
	public List<Application> getApplicant(String programId) {

		String qStr = "SELECT e FROM Application e WHERE e.scheduledProgramId=:pappid";
		TypedQuery<Application> query = entityManager.createQuery(qStr,
				Application.class);
		query.setParameter("pappid", programId);
		List<Application> app = query.getResultList();
		return app;
	}

	/* (non-Javadoc)
	 * @see com.cg.uas.dao.IDAO#modify(com.cg.uas.entities.Application, java.lang.String)
	 */
	@Override
	public Application modify(Application application, String status) {
		application.setStatus(status);
		application = entityManager.merge(application);
		entityManager.flush(); // required to reflect changes on database

		return application;
	}
	
	@Override
	public Application setInterview(Application application, Date dateOfInterview) {
		application.setDateOfInterview(dateOfInterview);
		application = entityManager.merge(application);
		entityManager.flush(); // required to reflect changes on database
		return application;
	}
}
