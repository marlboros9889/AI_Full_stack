package com.the703.controller;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.the703.dto.Sboard2Dto;
import com.the703.service.Sboard2Service;
import com.the703.util.UtilPaging;

@Controller
@RequestMapping("/board")
public class Sboard2Controller {
    @Autowired private Sboard2Service service;

    //1. 전체리스트
    @GetMapping("/list")
    public String list(Model model, @RequestParam(value="pageNo", defaultValue="1") int pageNo) {
        model.addAttribute("paging", new UtilPaging(service.selectCnt(), pageNo));
        model.addAttribute("list", service.list10(pageNo));
        return "board/list";
    }

    //2. 글쓰기 폼  GET: /board/write
    @GetMapping("/write")
    public String write_get() { return "board/write"; }

    //3. 글쓰기 기능  POST: /board/write
    @PostMapping("/write")
    public String write_post(MultipartFile file, Sboard2Dto dto, RedirectAttributes rttr) throws IOException {
        String result = "글쓰기 실패";
        if(service.insert(file, dto) > 0) { result = "글쓰기 성공"; }
        rttr.addFlashAttribute("success", result);
        return "redirect:/board/list";
    }

    //4. 상세보기  GET: /board/detail
    @GetMapping("/detail")
    public String detail_get(Model model, Sboard2Dto dto) {
        model.addAttribute("dto", service.detail(dto));
        return "board/detail";
    }

    //5. 수정 폼  GET: /board/edit
    @GetMapping("/edit")
    public String edit_get(Model model, Sboard2Dto dto) {
        model.addAttribute("dto", service.updateForm(dto));
        return "board/edit";
    }

    //6. 수정 기능  POST: /board/edit
    @PostMapping("/edit")
    public String edit_post(MultipartFile file, Sboard2Dto dto, RedirectAttributes rttr) throws IOException {
        String result = "글수정 실패";
        if(service.update(file, dto) > 0) { result = "글수정 성공"; }
        rttr.addFlashAttribute("success", result);
        return "redirect:/board/detail?id=" + dto.getId(); // ← / 추가!
    }

    //7. 삭제 폼  GET: /board/delete
    @GetMapping("/delete")
    public String delete_get(Model model, Sboard2Dto dto) {
        model.addAttribute("dto", service.updateForm(dto)); // ← 추가!
        return "board/delete";
    }

    //8. 삭제 기능  POST: /board/delete
    @PostMapping("/delete")
    public String delete_post(Sboard2Dto dto, RedirectAttributes rttr) {
        String result = "글삭제 실패";
        if(service.delete(dto) > 0) { result = "글삭제 성공"; }
        rttr.addFlashAttribute("success", result);
        return "redirect:/board/list"; // ← redirect:/ 추가!
    }
}