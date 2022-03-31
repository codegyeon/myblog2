package com.sparta.myblog2.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparta.myblog2.dto.SignupRequestDto;
import com.sparta.myblog2.service.KakaoUserService;
import com.sparta.myblog2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;



@RequiredArgsConstructor
@Controller
public class ContentsUserController {
    private final UserService userService;
    private final KakaoUserService kakaoUserService;

    //로그인페이지 연결
    @GetMapping("/login")
    public String loginpage(){
        return "login";
    }

    //회원가입 페이지 연결
    @GetMapping("/signup")
    public String signuppage(){
        return "signup";
    }

    // 회원 가입 요청 처리
    @PostMapping("/signup")
    public String registerUser(@ModelAttribute @Valid SignupRequestDto requestDto , Errors errors, Model model )  {

        if (errors.hasErrors()) {
            System.out.println(errors);
            /* 유효성 통과 못한 필드와 메시지를 핸들링 */
            Map<String, String> validatorResult = userService.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                model.addAttribute("keys", validatorResult.get(key));
                System.out.println(validatorResult.get(key));
            }
            return "signup";
        }
        if(!requestDto.getPassword().equals(requestDto.getChekpassword())){
            model.addAttribute("keys" , "비밀번호가 다릅니다..");
            return "signup";
        }
        if(requestDto.getUsername().equals(requestDto.getPassword())){
            model.addAttribute("keys","아이디와 비밀번호는 같을 수 없습니다.");
            return "signup";
        }

            /* 회원가입 페이지로 다시 리턴 */


        userService.registerUser(requestDto);
        model.addAttribute("keys","회원가입 성공!");
        //out.println("<script>alert('회원가입에 성공하셨습니다'); </script>");
        //out.flush();

        //ContentsAlert.alert(response,"회원가입에 성공하셨습니다");
        return "redirect:/login";


    }
    @GetMapping("/user/kakao/callback")
    public String kakaoLogin(@RequestParam String code) throws JsonProcessingException {
// authorizedCode: 카카오 서버로부터 받은 인가 코드
        kakaoUserService.kakaoLogin(code);

        return "redirect:/";
    }
}
