package com.p2p.condominium.security;

import com.p2p.condominium.repository.UserRepository;
import com.p2p.condominium.util.JWTUtil;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

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
                /*
                TODO: Trocar username por objeto usuário logado quando retornar do banco e remover o ultimo map
                .flatMap(valid -> this.userRepository.findByEmail(username))
                .map(user -> Mono.just(new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities())))*/
                .map(valid -> {
                    var claims = jwtUtil.getAllClaimsFromToken(authToken);
                    var rolesMap = (List<String>) claims.get("role", List.class);
                    return new UsernamePasswordAuthenticationToken(
                            username,
                            null,
                            rolesMap.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList())
                    );
                });
    }


}
