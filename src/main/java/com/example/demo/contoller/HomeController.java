package com.example.demo.contoller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class HomeController {

    @GetMapping("/")
    public String login() {
        return "home";
    }
}
