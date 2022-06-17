package com.p2p.condominium.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PhoneDTO {
    private Integer code;
    private Integer number;
    private Boolean active;
}
