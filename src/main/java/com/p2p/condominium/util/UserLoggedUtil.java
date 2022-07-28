package com.p2p.condominium.util;

import com.p2p.condominium.document.UserDocument;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserLoggedUtil {
    public UserDocument getLogged() {
        return (UserDocument) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
