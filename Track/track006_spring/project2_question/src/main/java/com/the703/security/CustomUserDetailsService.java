package com.the703.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.the703.dao.UserMapper;
import com.the703.dto.AuthUserDto;
 
public class CustomUserDetailsService   implements UserDetailsService{

	@Autowired  UserMapper mapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    
	    AuthUserDto dto = mapper.readAuth(username); // email, bpass, auth(s)
	    
	    // 수정된 부분: null일 경우 예외를 발생시킵니다.
	    if (dto == null) {
	        throw new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다: " + username);
	    }
	    
	    return new CustomUser(dto);
	}

}
