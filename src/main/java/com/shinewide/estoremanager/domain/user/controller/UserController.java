package com.shinewide.estoremanager.domain.user.controller;


import com.shinewide.estoremanager.domain.user.dto.AuthResponseDto;
import com.shinewide.estoremanager.domain.user.dto.UserRequestDto;
import com.shinewide.estoremanager.domain.user.service.UserService;
import com.shinewide.estoremanager.security.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> reister(@RequestBody @Valid UserRequestDto requestDto){
        userService.register(requestDto);
        return ResponseEntity.ok("회원가입 성공");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody @Valid UserRequestDto requestDto) {
        AuthResponseDto response = userService.login(requestDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/me")
    public ResponseEntity<UserDetailsImpl> getCurrentUser(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok(userDetails);
    }
}
