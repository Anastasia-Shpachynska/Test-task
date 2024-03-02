package com.example.test_task_server.data.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterData {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
