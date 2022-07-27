package com.p2p.condominium.service;

import com.p2p.condominium.document.UserDocument;
import reactor.core.publisher.Mono;

public interface UserService {
    Mono<UserDocument> findByUsername(String username);
}
