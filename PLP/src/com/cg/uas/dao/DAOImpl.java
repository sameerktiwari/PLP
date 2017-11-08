package com.cg.uas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cg.uas.entities.Users;


@Repository
@Transactional
public class DAOImpl {
	
	@PersistenceContext
	EntityManager entityManager;
	
	public boolean validate(Users user){
		TypedQuery<Users> query=entityManager.createQuery("Select user from users user where user.loginId=:loginid and user.password=:pwd and role=:role", Users.class);
		query.setParameter("loginid", user.getLoginId());
		query.setParameter("pwd", user.getPassword());
		query.setParameter("role", user.getRole());
		List<Users> userList=query.getResultList();
		return userList.isEmpty();
	}
}
