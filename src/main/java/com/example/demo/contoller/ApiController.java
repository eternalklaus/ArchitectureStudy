package com.example.demo.contoller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"API 정보를 제공하는 Controller"})
@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class ApiController {

    @GetMapping("/todolist")
    ResponseEntity<String> test(){
        return ResponseEntity.ok("todolist");
    }
}
