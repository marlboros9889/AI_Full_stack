package com.marinboy.dao;

import com.marinboy.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AuthDao {
    UserDto findByCredentials(@Param("username") String username, @Param("password") String password);
}
