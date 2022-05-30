package com.algaworks.algalog.model.dto.response;

import com.algaworks.algalog.model.embeddable.RecipientEmbeddable;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecipientResponse {

    @JsonProperty("nome")
    private String name;

    @JsonProperty("rua")
    private String street;

    @JsonProperty("numero")
    private String number;

    @JsonProperty("Bairro")
    private String district;

    @JsonProperty("complemento")
    private String complement;

    public static RecipientResponse toDTO(RecipientEmbeddable recipient) {
        var recipientResponse = new RecipientResponse();
        recipientResponse.setName(recipient.getName());
        recipientResponse.setStreet(recipient.getAddressEmbeddable().getStreet());
        recipientResponse.setNumber(recipient.getAddressEmbeddable().getNumber());
        recipientResponse.setDistrict(recipient.getAddressEmbeddable().getDistrict());
        recipientResponse.setComplement(recipient.getAddressEmbeddable().getComplement());

        return recipientResponse;
    }

}
