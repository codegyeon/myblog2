package com.sparta.myblog2.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequestDto {

    //닉네임은 최소 3자 이상, 알파벳 대소문자(a~z, A~Z), 숫자(0~9)로 구성하기
    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-zA-Z0-9-_]{3,10}$" , message = "아이디는 최소 3자 이상입니다.")
    private String username;

    // 최소 4자 이상이며, 닉네임과 같은 값이 포함된 경우 회원가입에 실패
    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Size(min = 4, message = "비밀번호는 최소 4자에서 최대 16자 입니다.")
    private String password;

    private String email;

    @NotBlank(message = "2차 비밀번호는 필수 입력 값입니다.")
    private String chekpassword;

}
