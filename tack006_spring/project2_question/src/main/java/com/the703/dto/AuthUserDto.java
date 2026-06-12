package com.the703.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;

import lombok.Data;

@Data
public class AuthUserDto {
	private String email;
	private String bpass;
	private List<AuthDto> authList;
	
	public List<GrantedAuthority>
		getAuthorities(){
		List<GrantedAuthority>list = new ArrayList<>();
		if(authList != null) {
			for(AuthDto a: authList) {
				list.add(new SimpleGrantedAuthority(a.getAuth()));
			}
		}
		return list;
	}
	
	public String getPassword() {return bpass;}
	
	public String getUsername() {return email; }
	
	public boolean isAccountNonExpired() {return true;}
	
	public boolean isCredentialsNonExpired() { return true; }
	
	public boolean isAccountNonLocked() {return true;}
	
	public boolean isEnabled() {return true;}
	
	
	
	
	
	
	
	
}

