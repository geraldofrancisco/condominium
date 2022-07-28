package com.p2p.condominium.service;

import com.p2p.condominium.dto.AuthRequest;
import com.p2p.condominium.dto.AuthResponse;
import reactor.core.publisher.Mono;

public interface LoginService {
    Mono<AuthResponse> login(AuthRequest request);
}
