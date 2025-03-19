package com.shinewide.estoremanager.service;

import com.shinewide.estoremanager.domain.User;
import com.shinewide.estoremanager.dto.AuthResponseDto;
import com.shinewide.estoremanager.dto.UserRequestDto;
import com.shinewide.estoremanager.repository.UserRepository;
import com.shinewide.estoremanager.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

import static com.shinewide.estoremanager.domain.User.*;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void register(UserRequestDto requestDto){
        String encodedPassword = passwordEncoder.encode(requestDto.getPassword());
        Set<Role> roles = new HashSet<>();
        roles.add(Role.USER);
        User user = new User(null, requestDto.getEmail(), encodedPassword, roles);
        userRepository.save(user);
    }

    public AuthResponseDto login(UserRequestDto requestDto){
        User user = userRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("이메일 또는 비밀번호가 올바르지 않습니다."));

        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("이메일 또는 비밀번호가 올바르지 않습니다.");
        }

        String token = "Bearer " + jwtTokenProvider.generateToken(user.getEmail());
        return new AuthResponseDto(token);

    }

}
