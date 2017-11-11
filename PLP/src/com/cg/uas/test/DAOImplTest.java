package com.cg.uas.test;

import static org.junit.Assert.*;

import java.sql.Date;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cg.uas.dao.DAOImpl;
import com.cg.uas.dao.IDAO;
import com.cg.uas.entities.Application;
import com.cg.uas.entities.Users;
import com.cg.uas.exception.UniversityException;

public class DAOImplTest {
	private DAOImpl dao;
	
	@Before
	public void setUp() throws Exception {
		dao=new DAOImpl();
	}

	@After
	public void tearDown() throws Exception {
		dao=null;
	}

	@Test
	public void testValidate() {
		Users user=new Users("akash","akash","mac");
		assertEquals(false, dao.validate(user));
	}
	
	@Test
	public void testGetStatus() throws UniversityException{
		Application application=new Application("Sameer Tiwari", Date.valueOf("1995-04-02"), "Bachelor of Technology", 88, "Successful IT engineer", "sameerkt@gmail.com", "1001","Pending");
		application=dao.save(application);
		assertEquals("Pending", dao.modify(application, "Accepted").getStatus());
	}
}
