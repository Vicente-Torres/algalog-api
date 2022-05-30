package com.algaworks.algalog.model.dto.response;

import com.algaworks.algalog.model.entity.Delivery;
import com.algaworks.algalog.model.eum.DeliveryStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class DeliveryResponse {

    private Long id;

    private DeliveryStatus status;

    @JsonProperty("nomeCliente")
    private String clientName;

    @JsonProperty("destinatario")
    private RecipientResponse recipientResponse;

    @JsonProperty("taxa")
    private BigDecimal fee;

    @JsonProperty("dataPedido")
    private Instant requestDate;

    @JsonProperty("dataFinalização")
    private Instant finishedDate;

    public static DeliveryResponse toDTO(Delivery delivery) {
        var deliveryResponse = new DeliveryResponse();
        deliveryResponse.setId(delivery.getId());
        deliveryResponse.setFee(delivery.getFee());
        deliveryResponse.setStatus(delivery.getStatus());
        deliveryResponse.setRequestDate(delivery.getRequestDate());
        deliveryResponse.setFinishedDate(delivery.getFinishedDate());
        deliveryResponse.setClientName(delivery.getClient().getName());
        deliveryResponse.setRecipientResponse(RecipientResponse.toDTO(delivery.getRecipient()));

        return deliveryResponse;
    }

    public static List<DeliveryResponse> toDTOCollection(List<Delivery> deliveries) {
        return deliveries.stream().map(delivery -> toDTO(delivery)).toList();
    }

}
