package com.p2p.condominium.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class  AuthRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
