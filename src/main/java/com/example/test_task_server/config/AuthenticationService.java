package com.example.test_task_server.config;

import com.example.test_task_server.data.request.LoginData;
import com.example.test_task_server.data.request.RegisterData;
import com.example.test_task_server.data.response.AuthenticationData;
import com.example.test_task_server.entity.Users;
import com.example.test_task_server.repository.UserRepository;
import com.example.test_task_server.type.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationData register(RegisterData request) {
        var user = Users.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationData.builder().token(jwtToken).roleEnum(user.getRole()).build();
    }

    public AuthenticationData authenticate(LoginData request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationData.builder().token(jwtToken).roleEnum(user.getRole()).build();
    }
}
