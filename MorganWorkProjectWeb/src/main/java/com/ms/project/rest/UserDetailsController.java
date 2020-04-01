package com.ms.project.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ms.project.dto.MessageDto;
import com.ms.project.dto.UserDetailsDto;
import com.ms.project.service.UserDetailsLocal;

@RestController
@RequestMapping(value = "/user", produces = "application/json")
public class UserDetailsController {

	@Autowired
	private UserDetailsLocal userDetailsLocal;

	@RequestMapping(value = "/getUser", method = RequestMethod.POST)
	public MessageDto getUserDetails(@RequestBody UserDetailsDto userDetailsDto) {
		return userDetailsLocal.getUserDetails(userDetailsDto);
	}

	@RequestMapping(value = "/authenticateUser", method = RequestMethod.POST)
	public MessageDto authenticate(@RequestBody UserDetailsDto userDetailsDto) {
		return userDetailsLocal.authenticateUser(userDetailsDto);
	}

	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	public MessageDto createNewUser(@RequestBody List<UserDetailsDto> userDetailsDtoList) {
		return userDetailsLocal.createNewUser(userDetailsDtoList);
	}

	@RequestMapping(value = "/updateUsers", method = RequestMethod.PUT)
	public MessageDto updateUser(@RequestBody List<UserDetailsDto> userDetailsDtoList) {
		return userDetailsLocal.updateUser(userDetailsDtoList);
	}
}
