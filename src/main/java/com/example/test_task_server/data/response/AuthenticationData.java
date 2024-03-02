package com.example.test_task_server.data.response;

import com.example.test_task_server.type.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationData {

    private String token;
    private Role roleEnum;
}
