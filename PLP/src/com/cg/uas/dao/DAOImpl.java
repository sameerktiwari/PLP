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
public class DAOImpl implements IDAO{
	
	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public boolean validate(Users user){
		TypedQuery<Users> query=entityManager.createQuery("Select user from Users user where user.loginId=:ploginid and user.password=:ppwd and role=:prole", Users.class);
		System.out.println(user.getLoginId()+""+user.getPassword()+""+user.getRole());
		query.setParameter("ploginid", user.getLoginId());
		query.setParameter("ppwd", user.getPassword());
		query.setParameter("prole", user.getRole());
		List<Users> userList=query.getResultList();
		return userList.isEmpty();
	}
}
