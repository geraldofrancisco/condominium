package com.p2p.condominium.constant;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
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

    /**
     * STACKHOLDER
     * */
    public static final String STACKHOLDER_ID_NOT_EXIST = "O stackholder informado não existe";
    public static final String STACKHOLDER_PHYSICAL_PERSON_ID_NOT_EXIST = "A pessoa informada não existe";
    public static final String STACKHOLDER_LEGAL_PERSON_ID_NOT_EXIST = "A empresa informada não existe";
    public static final String STACKHOLDER_IN_USE_CONDOMINIUM = "Não é possivel excluir o stackholder pois ele está em uso em condomínio";
    public static final String STACKHOLDER_IN_USE_APARTMENT = "Não é possivel excluir o stackholder pois ele está em uso em apartamento";

    /**
     * BUILDING
     * */
    public static final String BUILDING_CONDOMINIUM_REQUIRED = "O condomínio no qual o prédio pertence é obrigatório";
    public static final String BUILDING_NAME_CONDOMINIUM_REQUIRED = "O nome do prédio é obrigatório";
    public static final String BUILDING_NUMBER_OF_FLOORS_REQUIRED = "A quantidade de andares é obrigatória";
    public static final String BUILDING_NUMBER_OF_FLOORS_SIZE = "A quantidade de andares deve estar entre 1 e 100";
    public static final String BUILDING_NUMBER_OF_APARTAMENTS_PER_FLOOR_REQUIRED = "A quantidade de apartamentos é obrigatória";
    public static final String BUILDING_NUMBER_OF_APARTAMENTS_PER_FLOOR_SIZE = "A quantidade de apartamentos deve estar entre 1 e 10";
    public static final String BUILDING_NUMBER_OF_PARKING_APARTMENT_REQUIRED = "A quantidade de vagas é obrigatória";
    public static final String BUILDING_ID_NOT_EXIST = "O prédio informado não existe";
    public static final String BUILDING_EXISTS_APARTMENTS_NOT_DELETE = "O prédio informado contém apartamentos, por isso não pode ser excluído";

    /**
     * CONDOMINIUM
     * */
    public static final String CONDOMINIUM_EXISTS_BUILDING_NOT_DELETE = "O condomínio informado contém prédios, por isso não pode ser excluído";

    /**
     * APARTMENT
     * */
    public static final String APARTMENT_ID_NOT_EXIST = "O apartamento informado não existe";
    public static final String APARTMENT_BUILDING_REQUIRED = "O prédio deve ser informado";
    public static final String APARTMENT_FLOOR_REQUIRED = "O andar deve ser informado";
    public static final String APARTMENT_FLOOR_MIN = "O numero do andar deve superior a 1";
    public static final String APARTMENT_FLOOR_CROWDED = "Não é possível salvar outro apartamento nesse prédio e andar";
    public static final String APARTMENT_FLOOR_MAX = "Não é possível salvar nesse andar, limite de andares já alcançado";
    public static final String APARTMENT_NUMBER_REQUIRED = "O numero do apartamento deve ser informado";
    public static final String APARTMENT_NUMBER_MIN = "O numero do apartamento deve superior a 1";
    public static final String APARTMENT_DONT_INSERT = "Não é possível inserir o apartamento pois ele já está cadastrado";
    public static final String APARTMENT_BUILDING_NOT_EXISTS = "Não existe o prédio informado";
}
