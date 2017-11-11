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
import com.cg.uas.utility.QueryMapper;
import com.cg.uas.exception.UniversityException;
import org.apache.log4j.*;

@Repository
@Transactional
public class DAOImpl implements IDAO {
	
	private static Logger logger= Logger.getLogger(com.cg.uas.dao.DAOImpl.class);
	@PersistenceContext
	EntityManager entityManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cg.uas.dao.IDAO#validate(com.cg.uas.entities.Users)
	 */
	@Override
	public boolean validate(Users user) {
		TypedQuery<Users> query = entityManager.createQuery(QueryMapper.query1,
				Users.class);
		query.setParameter("ploginid", user.getLoginId());
		query.setParameter("ppwd", user.getPassword());
		query.setParameter("prole", user.getRole());
		List<Users> users = query.getResultList();
		logger.info("Logged in successfully");
		return users.isEmpty();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cg.uas.dao.IDAO#viewProgrammes()
	 */
	@Override
	public List<ProgramsScheduled> viewProgrammes() throws UniversityException {
		try {
			TypedQuery<ProgramsScheduled> query = entityManager.createQuery(
					QueryMapper.query2, ProgramsScheduled.class);
			 logger.info("Retrieved Programs Scheduled");
			return query.getResultList();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new UniversityException("No Programmes available");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cg.uas.dao.IDAO#getProgramsOffered(java.lang.String)
	 */
	@Override
	public ProgramsOffered getProgramsOffered(String pname)
			throws UniversityException {

		try {
			TypedQuery<ProgramsOffered> query = entityManager.createQuery(
					QueryMapper.query3, ProgramsOffered.class);
			query.setParameter("pname", pname);
			ProgramsOffered programs = query.getSingleResult();
			logger.info("Retrieved Programs Offered");
			return programs;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new UniversityException(
					"Incorrect Program Name or Programme Not Available");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cg.uas.dao.IDAO#getStatus(int)
	 */
	@Override
	public Application getStatus(int appid) throws UniversityException {
		try {
			TypedQuery<Application> query = entityManager.createQuery(
					QueryMapper.query4, Application.class);
			query.setParameter("pappid", appid);
			Application app = query.getSingleResult();
			logger.info("Retrieved application status");
			return app;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new UniversityException(e.getMessage());

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cg.uas.dao.IDAO#save(com.cg.uas.entities.Application)
	 */
	@Override
	public Application save(Application app) throws UniversityException {

		try {
			entityManager.persist(app);
			entityManager.flush(); // required to reflect changes on database
			logger.info("Application submitted");
			return app;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new UniversityException("Problem in persisting");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cg.uas.dao.IDAO#getApplicant(java.lang.String)
	 */
	@Override
	public List<Application> getApplicant(String programId)
			throws UniversityException {

		try {
			TypedQuery<Application> query = entityManager.createQuery(
					QueryMapper.query5, Application.class);
			query.setParameter("pappid", programId);
			List<Application> applications = query.getResultList();
			return applications;
		} catch (Exception e) {
			throw new UniversityException("No Applicant with thte given id");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cg.uas.dao.IDAO#modify(com.cg.uas.entities.Application,
	 * java.lang.String)
	 */
	@Override
	public Application modify(Application application, String status)
			throws UniversityException {
		try {
			application.setStatus(status);
			application = entityManager.merge(application);
			entityManager.flush(); // required to reflect changes on database
			return application;
		} catch (Exception e) {
			throw new UniversityException("Problem in Updating status");
		}
	}

	/* (non-Javadoc)
	 * @see com.cg.uas.dao.IDAO#setInterview(com.cg.uas.entities.Application, java.sql.Date)
	 */
	@Override
	public Application setInterview(Application application,
			Date dateOfInterview) throws UniversityException {
		try {
			application.setDateOfInterview(dateOfInterview);
			application = entityManager.merge(application);
			entityManager.flush(); // required to reflect changes on database
			return application;
		} catch (Exception e) {
			throw new UniversityException(
					"Problem in Updating Date of Interview");
		}
	}

	/* (non-Javadoc)
	 * @see com.cg.uas.dao.IDAO#addParticipant(com.cg.uas.entities.Participant)
	 */
	@Override
	public Participant addParticipant(Participant ppt)
			throws UniversityException {

		try {
			entityManager.persist(ppt);
			entityManager.flush(); // required to reflect changes on database
			return ppt;
		} catch (Exception e) {
			throw new UniversityException("Problem in persisting participant");
		}

	}
}
