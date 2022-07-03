package com.p2p.condominium.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorConstant {
    public static final String ID_NOT_EXIST = "Id informado não existe";
    public static final String CNPJ_OR_CPF_REQUIRED = "CPF ou CNPJ devem ser informados";

    public static final String REQUEST_CPF_INVALIDO = "O número CPF está inválido";
    public static final String REQUEST_CNPJ_INVALIDO = "O número CNPJ está inválido";

    public static final String REQUEST_IDENTIFICATION_REQUIRED = "A identificação é obrigatória";
    public static final String REQUEST_NAME_REQUIRED = "O nome é obrigatório";
    public static final String REQUEST_EMAIL_REQUIRED = "O email é obrigatório";
    public static final String REQUEST_EMAIL_INVALID = "O formato do email está inválido";
    public static final String REQUEST_ID_REQUIRED = "O id é obrigatório";
    public static final String REQUEST_CONSTRUCTION_COMPANY_REQUIRED = "A construtora é de preenchimento obrigatório";
    public static final String REQUEST_ADDRESS_REQUIRED = "Endereço é obrigatório";

    public static final String STACKHOLDER_ID_NOT_EXIST = "O stackholder informado não existe";

}
