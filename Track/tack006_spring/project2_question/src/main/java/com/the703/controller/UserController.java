package com.the703.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.the703.dto.UserDto;
import com.the703.service.UserService;

@Controller
public class UserController {

	@Autowired  UserService service;
	
	@RequestMapping( "/" )
	public String index() {  return "redirect:/users/login"; }

	 
	///////////////////////////////////////
	@RequestMapping( value="/users/join" , method=RequestMethod.GET  )
	public String join() {  return "users/join"; }

	// #3. 회원가입 처리
	@PostMapping( "/users/join" )
	public String join(UserDto userDto, HttpServletRequest request) {
		userDto.setBip(request.getRemoteAddr());  // 가입 IP 자동 기록
		service.join(userDto);
		return "redirect:/users/login";
	}
	 
	@RequestMapping( value="/users/login" , method=RequestMethod.GET  )
	public String login() {  return "users/login"; }

	// #1. 이메일 중복검사 (ajax)
	@ResponseBody
	@PostMapping("/users/checkEmail")
	public int checkEmail(@RequestParam("email") String email) {
		return service.checkEmail(email);
	}

	// #2. 닉네임 중복검사 (ajax)
	@ResponseBody
	@PostMapping("/users/checkNickname")
	public int checkNickname(@RequestParam("nickname") String nickname) {
		return service.checkNickname(nickname);
	}

	// #4. 마이페이지
	@GetMapping("/users/mypage")
	public String mypage(Authentication authentication, Model model) {
		String email = authentication.getName(); // Security Context에서 로그인 이메일
		UserDto userDto = service.myInfo(email);
		model.addAttribute("userDto", userDto);
		return "users/mypage";
	}

	// #5. 로그아웃은 Spring Security가 처리 (/users/logout)
	// SecurityConfig에서 .logout().logoutUrl("/users/logout") 설정되어 있으면 별도 컨트롤러 불필요
}