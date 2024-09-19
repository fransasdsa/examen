// src/main/java/com/upeu/auth/model/LoginRequest.java

package com.upeu.auth.model;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
