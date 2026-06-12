package com.the703.dao;

import com.the703.dto.AuthUserDto;
import com.the703.dto.UserDto;

@Mapper
public interface UserMapper {

    AuthUserDto readAuth(String email);

    // #1, #2 중복검사
    int checkEmail(String email);
    int checkNickname(String nickname);

    // #3 회원가입
    int insertUser(UserDto userDto);
    int insertAuth(String email);

    // #4 마이페이지
    UserDto selectUserByEmail(String email);
}