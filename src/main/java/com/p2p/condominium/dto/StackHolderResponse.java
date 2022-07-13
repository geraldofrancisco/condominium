package com.p2p.condominium.dto;

import com.p2p.condominium.enums.TypePersonEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

import static com.p2p.condominium.constant.StackholderConstant.ADDRESS;
import static com.p2p.condominium.constant.StackholderConstant.EMAIL;
import static com.p2p.condominium.constant.StackholderConstant.ID;
import static com.p2p.condominium.constant.StackholderConstant.IDENTIFICATION;
import static com.p2p.condominium.constant.StackholderConstant.NAME;
import static com.p2p.condominium.constant.StackholderConstant.PHONES;
import static com.p2p.condominium.constant.StackholderConstant.TYPE_PERSON;

@Builder
@Data
public class StackHolderResponse {
    @Schema(description = ID)
    private String id;

    @Schema(description = IDENTIFICATION)
    private String identification;

    @Schema(description = NAME)
    private String name;

    @Schema(description = EMAIL)
    private String email;

    @Schema(description = PHONES)
    private List<Phone> phones;

    @Schema(description = TYPE_PERSON)
    private TypePersonEnum typePersonEnum;

    @Schema(description = ADDRESS)
    private AddressResponse address;
}
