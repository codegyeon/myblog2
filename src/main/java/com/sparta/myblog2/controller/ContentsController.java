package com.sparta.myblog2.controller;

import com.sparta.myblog2.models.UserRoleEnum;
import com.sparta.myblog2.security.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ContentsController {

    //메인 페이지
    @GetMapping("/")
    public String home(Model model ,UserDetailsImpl userDetails ) {
        try {
            model.addAttribute("username", userDetails.getUsername());
        }
       catch (NullPointerException e){
           model.addAttribute("username", "ok");
       }

        return "index";
    }

    //글쓰기 페이지
    @GetMapping("/write")
    public String writepage(){
        return "write";
    }

    //상세페이지
    @GetMapping("/detail/{id}")
    public String detailpage(@PathVariable Long id){
        return "detail";
    }



}
