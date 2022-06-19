package com.p2p.condominium.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorConstant {
    public static final String ID_NOT_EXIST = "Id does not exist";
    public static final String CNPJ_OR_CPF_REQUIRED = "CPF or CNPJ must be filled in";
}
