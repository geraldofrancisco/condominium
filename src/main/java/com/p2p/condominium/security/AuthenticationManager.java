package com.p2p.condominium.security;

import com.p2p.condominium.repository.UserRepository;
import com.p2p.condominium.util.JWTUtil;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import reactor.core.publisher.Mono;

@Configuration
@AllArgsConstructor
public class AuthenticationManager implements ReactiveAuthenticationManager {

    private JWTUtil jwtUtil;

    private UserRepository userRepository;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        var authToken = authentication.getCredentials().toString();
        var username = jwtUtil.getUsernameFromToken(authToken);
        return Mono.just(jwtUtil.validateToken(authToken))
                .filter(valid -> valid)
                .switchIfEmpty(Mono.empty())
                .flatMap(valid -> this.userRepository.findByEmail(username))
                .flatMap(user -> {
                    var auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(auth);
                    return Mono.just(auth);
                });
    }
}
