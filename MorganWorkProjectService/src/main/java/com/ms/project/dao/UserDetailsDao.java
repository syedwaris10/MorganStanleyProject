package com.ms.project.dao;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ms.project.constants.UserStatus;
import com.ms.project.dto.UserDetailsDto;
import com.ms.project.entity.UserDetailsDo;
import com.ms.project.exceptions.InvalidInputFault;
import com.ms.project.exceptions.NoResultFault;
import com.ms.project.util.CommonUtils;
import com.ms.project.util.ServiceUtil;

/**
 * 
 * @author Syed.Waris
 *
 */
@Repository("UserDetailsDao")
public class UserDetailsDao implements UserDetailsDaoLocal {
	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public UserDetailsDto getUserDetails(String emailId) throws NoResultFault {
		// UserDetailsDo entity = (UserDetailsDo)
		// getSession().get(UserDetailsDo.class, emailId);
		Query query = getSession().createQuery(
				"Select u from UserDetailsDo u Where u.userStatus = :userStatus and u.loggedInUserEmailId = :emailId");
		query.setParameter("userStatus", UserStatus.Active.name());
		query.setParameter("emailId", emailId);
		List<UserDetailsDo> list = (List<UserDetailsDo>) query.getResultList();
		if (!ServiceUtil.isEmpty(list)) {
			return exportDto(list.get(0));
		} else {
			throw new NoResultFault("User Does not exists!");
		}
	}

	public Session getSession() {
		try {
			return sessionFactory.getCurrentSession();
		} catch (HibernateException e) {

			// System.err.println("[BaseDao]" + e.getMessage());
			return sessionFactory.openSession();
		}
	}

	@Transactional
	@Override
	public void createNewUser(UserDetailsDto userDetailsDto) {
		getSession().persist(importDto(userDetailsDto));
	}

	@Transactional
	@Override
	public void updateUser(UserDetailsDto userDetailsDto) throws InvalidInputFault {
		if (!ServiceUtil.isEmpty(userDetailsDto)) {
			Session session = getSession();
			UserDetailsDo entity = (UserDetailsDo) session.get(UserDetailsDo.class,
					userDetailsDto.getLoggedInUserEmailId());
			if (!ServiceUtil.isEmpty(entity)) {
				setUpdatedEntity(entity, userDetailsDto);
				session.merge(entity);
			} else {
				throw new InvalidInputFault("Invalid Email Id.");
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public UserDetailsDto authenticateUser(UserDetailsDto dto) throws UnsupportedEncodingException, NoResultFault {
		Query query = getSession().createQuery(
				"Select u from UserDetailsDo u Where u.userStatus = :userStatus and u.loggedInUserEmailId = :emailId and u.password = :password");
		query.setParameter("userStatus", UserStatus.Active.name());
		query.setParameter("emailId", dto.getLoggedInUserEmailId());
		query.setParameter("password", CommonUtils.encodeToBase64(dto.getPassword()));
		List<UserDetailsDo> list = (List<UserDetailsDo>) query.getResultList();
		if (!ServiceUtil.isEmpty(list)) {
			return exportDto(list.get(0));
		} else {
			throw new NoResultFault("Invalid Username or password.");
		}
	}

	private void setUpdatedEntity(UserDetailsDo entity, UserDetailsDto dto) {
		if (!ServiceUtil.isEmpty(dto.getLoggedInUserEmailId())) {
			entity.setLoggedInUserEmailId(dto.getLoggedInUserEmailId());
		}
		if (!ServiceUtil.isEmpty(dto.getLoggedInUserName())) {
			entity.setLoggedInUserName(dto.getLoggedInUserName());
		}
		if (!ServiceUtil.isEmpty(dto.getLoggedInUserRoles())) {
			entity.setLoggedInUserRoles(dto.getLoggedInUserRoles());
		}
		if (!ServiceUtil.isEmpty(dto.getPassword())) {
			entity.setPassword(dto.getPassword());
		}
		if (!ServiceUtil.isEmpty(dto.getUserStatus())) {
			entity.setUserStatus(dto.getUserStatus());
		}
	}

	public UserDetailsDo importDto(UserDetailsDto dto) {
		UserDetailsDo entity = new UserDetailsDo();
		entity.setLoggedInUserEmailId(dto.getLoggedInUserEmailId());
		entity.setLoggedInUserName(dto.getLoggedInUserName());
		entity.setLoggedInUserRoles(dto.getLoggedInUserRoles());
		entity.setPassword(dto.getPassword());
		entity.setUserStatus(dto.getUserStatus());
		return entity;
	}

	public UserDetailsDto exportDto(UserDetailsDo entity) {
		UserDetailsDto dto = new UserDetailsDto();
		dto.setLoggedInUserEmailId(entity.getLoggedInUserEmailId());
		dto.setLoggedInUserName(entity.getLoggedInUserName());
		dto.setLoggedInUserRoles(entity.getLoggedInUserRoles());
		dto.setUserStatus(entity.getUserStatus());
		return dto;
	}

}
