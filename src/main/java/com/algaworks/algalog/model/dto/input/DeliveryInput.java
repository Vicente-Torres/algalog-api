package com.algaworks.algalog.model.dto.input;


import com.algaworks.algalog.model.entity.Delivery;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
public class DeliveryInput {

    @NotNull
    @JsonProperty("idCliente")
    private Long clientId;

    @NotNull
    @JsonProperty("taxa")
    private BigDecimal fee;

    @NotNull
    @JsonProperty("destinatario")
    private RecipientInput recipient;

    public static Delivery toEntity(DeliveryInput deliveryInput) {
        var delivery = new Delivery();
        delivery.getClient().setId(delivery.getId());
        delivery.setFee(deliveryInput.getFee());
        delivery.setRecipient(deliveryInput.getRecipient());

        return delivery;
    }
}
