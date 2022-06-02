package com.sparta.week4hw.controller;


import com.sparta.week4hw.dto.SignupRequestDto;
import com.sparta.week4hw.model.UserInfo;
import com.sparta.week4hw.repository.UserInfoRepository;
import com.sparta.week4hw.security.UserDetailsImpl;
import com.sparta.week4hw.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class userController {
    private final UserService userService;
    private final UserInfoRepository userInfoRepository;

    @GetMapping("/user/signup")
    public String signUp(){
        return "loginSuccess";
    }

    @GetMapping("/user/test")
    public void showUserInfo(){
        List<UserInfo> user = userInfoRepository.findAll();
        System.out.println(user);
    }

    @PostMapping("/user/signup")
    public String registerUser(SignupRequestDto requestDto) {
        userService.registerUser(requestDto);
        return "redirect:/user/login";
    }

    @GetMapping("/user/login/success")
    public String success(){
        return "loginSuccess";
    }

    @GetMapping("/user/login")
    public String login(){
        return "login";
    }

    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        model.addAttribute("username", userDetails.getUsername());
        System.out.println(userDetails.getUsername());
        return "index";
    }
}