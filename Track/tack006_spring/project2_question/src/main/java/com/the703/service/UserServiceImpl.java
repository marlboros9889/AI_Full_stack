package com.the703.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.the703.dao.UserMapper;
import com.the703.dto.AuthUserDto;
import com.the703.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {

	@Autowired UserMapper mapper;
	@Autowired PasswordEncoder passwordEncoder;

	@Override
	public AuthUserDto readAuth(String email) {
		return mapper.readAuth(email);
	}

	@Override
	public int checkEmail(String email) {
		return mapper.checkEmail(email);
	}

	@Override
	public int checkNickname(String nickname) {
		return mapper.checkNickname(nickname);
	}

	@Override
	@Transactional
	public int join(UserDto userDto) {
		// 비밀번호 암호화
		userDto.setBpass(passwordEncoder.encode(userDto.getBpass()));
		int result = mapper.insertUser(userDto);
		// 일반멤버 권한(ROLE_MEMBER) 부여
		mapper.insertAuth(userDto.getEmail());
		return result;
	}

	@Override
	public UserDto myInfo(String email) {
		return mapper.selectUserByEmail(email);
	}
}