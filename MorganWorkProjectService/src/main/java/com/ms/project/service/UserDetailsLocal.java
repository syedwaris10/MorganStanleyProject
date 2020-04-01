package com.ms.project.service;

import java.util.List;

import com.ms.project.dto.MessageDto;
import com.ms.project.dto.UserDetailsDto;

public interface UserDetailsLocal {

	public MessageDto getUserDetails(UserDetailsDto userDetailsDto);

	public MessageDto createNewUser(List<UserDetailsDto> userDetailsDtoList);

	public MessageDto updateUser(List<UserDetailsDto> userDetailsDtoList);
	
	public MessageDto authenticateUser(UserDetailsDto userDetailsDto);
}
