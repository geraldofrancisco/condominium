package com.p2p.condominium.document;

import com.p2p.condominium.enums.PermissionEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "usuario")
public class UserDocument implements UserDetails {
    @Id
    private String id;

    @Field("nome")
    private String name;

    @Field("email")
    private String email;

    @Field("senha")
    private String password;

    @Field("ativo")
    @Builder.Default
    private boolean enable = true;

    @Field("permissoes")
    private List<PermissionEnum> claims;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.claims.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.name()))
                .collect(Collectors.toSet());
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enable;
    }
}
