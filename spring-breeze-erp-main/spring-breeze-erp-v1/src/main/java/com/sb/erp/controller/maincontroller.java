package com.sb.erp.controller; // 본인의 패키지 경로로 수정

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        return "home"; // WEB-INF/views/home.jsp 파일을 찾으러 갑니다.
    }
}