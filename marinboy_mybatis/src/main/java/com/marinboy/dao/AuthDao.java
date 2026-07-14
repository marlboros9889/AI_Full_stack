package com.marinboy.dao;

import com.marinboy.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/** 사용자 로그인 정보를 Oracle에서 조회하는 MyBatis DAO입니다. */
@Mapper
public interface AuthDao {
    // 아이디와 비밀번호가 모두 일치하는 사용자 정보만 반환합니다.
    UserDto findByCredentials(@Param("username") String username, @Param("password") String password);
}
