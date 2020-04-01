package com.ms.project.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER_DETAILS")
public class UserDetailsDo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "USER_EMAIL_ID", length = 50, nullable = false)
	private String loggedInUserEmailId;

	@Column(name = "USER_NAME", length = 30, nullable = false)
	private String loggedInUserName;

	@Column(name = "USER_ID", length = 20)
	private String loggedInUserId;

	@Column(name = "USER_PASSWORD", length = 16)
	private String password;

	@Column(name = "USER_ROLES", length = 25)
	private String loggedInUserRoles;

	@Column(name = "USER_STATUS", length = 8)
	private String userStatus;

	/**
	 * @return the loggedInUserEmailId
	 */
	public String getLoggedInUserEmailId() {
		return loggedInUserEmailId;
	}

	/**
	 * @param loggedInUserEmailId the loggedInUserEmailId to set
	 */
	public void setLoggedInUserEmailId(String loggedInUserEmailId) {
		this.loggedInUserEmailId = loggedInUserEmailId;
	}

	/**
	 * @return the loggedInUserName
	 */
	public String getLoggedInUserName() {
		return loggedInUserName;
	}

	/**
	 * @param loggedInUserName the loggedInUserName to set
	 */
	public void setLoggedInUserName(String loggedInUserName) {
		this.loggedInUserName = loggedInUserName;
	}

	/**
	 * @return the loggedInUserId
	 */
	public String getLoggedInUserId() {
		return loggedInUserId;
	}

	/**
	 * @param loggedInUserId the loggedInUserId to set
	 */
	public void setLoggedInUserId(String loggedInUserId) {
		this.loggedInUserId = loggedInUserId;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the loggedInUserRoles
	 */
	public String getLoggedInUserRoles() {
		return loggedInUserRoles;
	}

	/**
	 * @param loggedInUserRoles the loggedInUserRoles to set
	 */
	public void setLoggedInUserRoles(String loggedInUserRoles) {
		this.loggedInUserRoles = loggedInUserRoles;
	}

	/**
	 * @return the userStatus
	 */
	public String getUserStatus() {
		return userStatus;
	}

	/**
	 * @param userStatus the userStatus to set
	 */
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
}