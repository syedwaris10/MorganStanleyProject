package com.ms.project.dao;

import java.io.UnsupportedEncodingException;

import com.ms.project.dto.UserDetailsDto;
import com.ms.project.exceptions.InvalidInputFault;
import com.ms.project.exceptions.NoResultFault;

public interface UserDetailsDaoLocal {

	public UserDetailsDto getUserDetails(String emailId) throws NoResultFault;

	public void createNewUser(UserDetailsDto userDetailsDto);

	public void updateUser(UserDetailsDto userDetailsDto) throws InvalidInputFault;

	public UserDetailsDto authenticateUser(UserDetailsDto dto) throws UnsupportedEncodingException, NoResultFault;
}
