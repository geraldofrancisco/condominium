package com.p2p.condominium.document;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@ToString
@Data
@Document(collection = "despesa")
public class ExpenseDocument extends BaseDocument {
    @Id
    private String id;

    @Field("condominio")
    private String condominium;

    @Field("prestadorServico")
    private String serviceProvider;

    @Field("dataVencimento")
    private LocalDate dueDate;

    @Field("valor")
    private BigDecimal amount;

    @Field("boletoBancario")
    private String bankSlip;

    @Field("comprovantePagamento")
    private String paymentVoucher;

    @Field("dataPagamento")
    private LocalDate paymentDate;

    @Field("descricao")
    private String description;
}
