package com.the703.service;

import org.springframework.stereotype.Service;

import com.the703.dto.AuthUserDto;
import com.the703.dto.UserDto;

@Service

public interface UserService {

	public     AuthUserDto    readAuth( String email );

	int checkEmail(String email);
	int checkNickname(String nickname);

	int join(UserDto userDto);

	UserDto myInfo(String email);
	
	
}

