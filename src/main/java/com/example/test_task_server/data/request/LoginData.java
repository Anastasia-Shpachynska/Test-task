package com.example.test_task_server.data.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginData {
    private String email;
    private String password;
}
