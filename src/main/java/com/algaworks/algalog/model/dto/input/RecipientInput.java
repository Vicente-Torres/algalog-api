package com.algaworks.algalog.model.dto.input;

import com.algaworks.algalog.model.embeddable.RecipientEmbeddable;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class RecipientInput {

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

    public static RecipientEmbeddable toEntity(RecipientInput recipientInput) {
        var recipient = new RecipientEmbeddable();
        recipient.setName(recipient.getName());
        recipient.getAddressEmbeddable().setStreet(recipientInput.getStreet());
        recipient.getAddressEmbeddable().setNumber(recipientInput.getNumber());
        recipient.getAddressEmbeddable().setDistrict(recipientInput.getDistrict());
        recipient.getAddressEmbeddable().setComplement(recipientInput.getComplement());
        return recipient;
    }

}
