package com.p2p.condominium.rest;

import com.p2p.condominium.dto.AuthRequest;
import com.p2p.condominium.dto.AuthResponse;
import com.p2p.condominium.service.LoginService;
import com.p2p.condominium.service.UserService;
import com.p2p.condominium.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import static com.p2p.condominium.constant.ControllerConstant.V1_LOGIN;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@RestController
@RequestMapping(V1_LOGIN)
@RequiredArgsConstructor
@Validated
public class LoginController {
    private final LoginService service;

    @PostMapping
    public Mono<AuthResponse> login(@RequestBody AuthRequest request) {
        return service.login(request);
    }
}
