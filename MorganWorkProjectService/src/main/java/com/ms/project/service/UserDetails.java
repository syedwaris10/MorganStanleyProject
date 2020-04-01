package com.ms.project.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.project.constants.Roles;
import com.ms.project.dao.UserDetailsDaoLocal;
import com.ms.project.dto.MessageDto;
import com.ms.project.dto.UserDetailsDto;
import com.ms.project.exceptions.InvalidInputFault;
import com.ms.project.exceptions.NoResultFault;
import com.ms.project.util.CommonUtils;
import com.ms.project.util.ServiceUtil;

@Service
public class UserDetails implements UserDetailsLocal {

	@Autowired
	private UserDetailsDaoLocal userDetailsDaoLocal;

	@Override
	public MessageDto getUserDetails(UserDetailsDto userDetailsDto) {
		try {
			if (!ServiceUtil.isEmpty(userDetailsDto)) {
				String emailId = userDetailsDto.getLoggedInUserEmailId();
				if (CommonUtils.isEmailValid(emailId)) {
					userDetailsDto = userDetailsDaoLocal.getUserDetails(emailId);
				} else {
					throw new InvalidInputFault("Please enter email Id.");
				}
			} else {
				throw new InvalidInputFault("Please enter email Id.");
			}
		} catch (NoResultFault | InvalidInputFault e) {
			return CommonUtils.getFailedMessage(null, e.getMessage());
		}
		return CommonUtils.getSuccessMessage(userDetailsDto);
	}

	@Override
	public MessageDto createNewUser(List<UserDetailsDto> userDetailsDtoList) {
		if (!ServiceUtil.isEmpty(userDetailsDtoList)) {
			try {
				for (UserDetailsDto dto : userDetailsDtoList) {
					if (!ServiceUtil.isEmpty(dto)) {
						String pwd = dto.getPassword();
						if (!ServiceUtil.isEmpty(pwd)) {
							if (CommonUtils.isPasswordValid(pwd)) {
								String encryptPassword = CommonUtils.encodeToBase64(dto.getPassword());
								dto.setPassword(encryptPassword);
								validateRoles(dto);
								userDetailsDaoLocal.createNewUser(dto);
							} else {
								throw new InvalidInputFault(
										"Password should be of 8 to 16 chars and it should contains alphabets and digits.");
							}
						} else {
							throw new InvalidInputFault("Please enter valid password.");
						}

					}
				}
			} catch (Exception e) {
				return CommonUtils.getFailedMessage(null, e.getMessage());
			}
		}
		return CommonUtils.getSuccessMessage(null);
	}

	private void validateRoles(UserDetailsDto dto) throws InvalidInputFault {

		if (!ServiceUtil.isEmpty(dto.getLoggedInUserRoles())) {
			if (!dto.getLoggedInUserRoles().equals(Roles.L1Appoval.name())
					|| !dto.getLoggedInUserRoles().equals(Roles.L2Approval.name())
					|| !dto.getLoggedInUserRoles().equals(Roles.L3Approval.name())) {
				throw new InvalidInputFault("Please Enter correct roles.");
			}
		} else {
			throw new InvalidInputFault("Roles cannot be empty.");
		}

	}

	@Override
	public MessageDto updateUser(List<UserDetailsDto> userDetailsDtoList) {
		if (!ServiceUtil.isEmpty(userDetailsDtoList)) {
			try {
				for (UserDetailsDto dto : userDetailsDtoList) {
					if (!ServiceUtil.isEmpty(dto)) {
						String emailId = dto.getLoggedInUserEmailId();
						if (ServiceUtil.isEmpty(emailId)) {
							throw new InvalidInputFault("Please enter email Id.");
						}
						String pwd = dto.getPassword();
						if (!ServiceUtil.isEmpty(pwd)) {
							if (CommonUtils.isPasswordValid(pwd)) {
								String encryptPassword = CommonUtils.encodeToBase64(dto.getPassword());
								dto.setPassword(encryptPassword);
							} else {
								throw new InvalidInputFault(
										"Password should be of minium 8 chars and it should contains alphabets and digits.");
							}
						}

						userDetailsDaoLocal.updateUser(dto);
					}
				}
			} catch (Exception e) {
				return CommonUtils.getFailedMessage(null, e.getMessage());
			}
		}
		return CommonUtils.getSuccessMessage(null);
	}

	@Override
	public MessageDto authenticateUser(UserDetailsDto userDetailsDto) {
		try {
			if (!ServiceUtil.isEmpty(userDetailsDto)) {
				String emailId = userDetailsDto.getLoggedInUserEmailId();
				String password = userDetailsDto.getPassword();
				if (CommonUtils.isEmailValid(emailId) && CommonUtils.isPasswordValid(password)) {
					userDetailsDto = userDetailsDaoLocal.authenticateUser(userDetailsDto);
				} else {
					throw new InvalidInputFault("Invalid userId or password.");
				}
			} else {
				throw new InvalidInputFault("Please enter email Id.");
			}
		} catch (NoResultFault | InvalidInputFault | UnsupportedEncodingException e) {
			return CommonUtils.getFailedMessage(null, e.getMessage());
		}
		return CommonUtils.getSuccessMessage(userDetailsDto);
	}

}
