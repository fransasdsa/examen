// src/main/java/com/upeu/auth/model/LoginResponse.java

package com.upeu.auth.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String token;
}
