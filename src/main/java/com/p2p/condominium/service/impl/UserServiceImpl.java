package com.p2p.condominium.service.impl;

import com.p2p.condominium.document.UserDocument;
import com.p2p.condominium.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

import static com.p2p.condominium.enums.PermissionEnum.ROLE_ADMINISTRATOR;
import static com.p2p.condominium.enums.PermissionEnum.ROLE_MANAGER;
import static java.util.List.of;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private PasswordEncoder passwordEncoder;

    private Map<String, UserDocument> data;

    @PostConstruct
    public void init() {
        data = new HashMap<>();
        var user = UserDocument.builder()
                .name("Raimunda Conselho Francisco")
                .email("ray@gmail.com")
                .password(this.passwordEncoder.encode("senha@123"))
                .claims(of(ROLE_MANAGER))
                .build();
        var admin = UserDocument.builder()
                .name("Geraldo Francisco Conselho")
                .email("geraldof.neto2016@gmail.com")
                .password(this.passwordEncoder.encode("senha@123"))
                .claims(of(ROLE_ADMINISTRATOR))
                .build();

        //username:passwowrd -> user:user
        data.put("ray@gmail.com", user);

        //username:passwowrd -> admin:admin
        data.put("geraldof.neto2016@gmail.com", admin);
    }


    @Override
    public Mono<UserDocument> findByUsername(String username) {
        return Mono.justOrEmpty(data.get(username));
    }
}
