package com.p2p.condominium.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import static com.p2p.condominium.constant.PhoneConstant.ACTIVE;
import static com.p2p.condominium.constant.PhoneConstant.CODE;
import static com.p2p.condominium.constant.PhoneConstant.NUMBER;

@Builder
@Data
public class Phone {
    @Schema(description = CODE)
    private Integer code;

    @Schema(description = NUMBER)
    private Integer number;

    @Schema(description = ACTIVE)
    private Boolean active;
}
