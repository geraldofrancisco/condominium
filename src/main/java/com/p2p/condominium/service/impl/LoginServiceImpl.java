package com.p2p.condominium.service.impl;

import com.p2p.condominium.dto.AuthRequest;
import com.p2p.condominium.dto.AuthResponse;
import com.p2p.condominium.exception.BusinessException;
import com.p2p.condominium.repository.UserRepository;
import com.p2p.condominium.service.LoginService;
import com.p2p.condominium.util.JWTUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static com.p2p.condominium.constant.ErrorConstant.USER_NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Service
@AllArgsConstructor
public class LoginServiceImpl implements LoginService {

    private JWTUtil jwtUtil;
    private PasswordEncoder passwordEncoder;
    private UserRepository repository;

    @Override
    public Mono<AuthResponse> login(AuthRequest request) {
        return this.repository.findByEmail(request.getUsername())
                .filter(user -> passwordEncoder.matches(request.getPassword(), user.getPassword()))
                .flatMap(user -> Mono.just(AuthResponse.builder().token(jwtUtil.generateToken(user)).build()))
                .switchIfEmpty(Mono.error(new BusinessException(USER_NOT_FOUND, UNAUTHORIZED)));
    }
}
