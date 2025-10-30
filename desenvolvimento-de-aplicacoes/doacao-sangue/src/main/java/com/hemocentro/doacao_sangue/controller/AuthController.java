package com.hemocentro.doacao_sangue.controller;

import com.hemocentro.doacao_sangue.infrastructure.entity.request.LoginRequest;
import com.hemocentro.doacao_sangue.infrastructure.entity.response.LoginResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/auth")
public class AuthController {
  @PostMapping("/login")
  public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
    if ("gabi@gabi.com".equals(request.email()) && "gabi".equals(request.senha())) {
      return ResponseEntity.
        ok(new LoginResponse("Login bem-sucedido!", "mocked-jwt-token" + UUID.randomUUID()));
    }

    return ResponseEntity
      .status(HttpStatus.UNAUTHORIZED)
      .body(new LoginResponse("Email ou senha inválidos", null));
  }
}
