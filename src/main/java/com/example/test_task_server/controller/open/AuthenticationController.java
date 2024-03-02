package com.example.test_task_server.controller.open;

import com.example.test_task_server.config.AuthenticationService;
import com.example.test_task_server.data.request.LoginData;
import com.example.test_task_server.data.request.RegisterData;
import com.example.test_task_server.data.response.AuthenticationData;
import com.example.test_task_server.data.response.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/open/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<ResponseData<AuthenticationData>> register(
            @RequestBody RegisterData data
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseData<>(authenticationService.register(data)));
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseData<AuthenticationData>> login(
            @RequestBody LoginData data
    ) {
        return ResponseEntity.ok(new ResponseData<>(authenticationService.authenticate(data)));
    }
}
