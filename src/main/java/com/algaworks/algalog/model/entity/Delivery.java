package com.algaworks.algalog.model.entity;

import com.algaworks.algalog.model.embeddable.RecipientEmbeddable;
import com.algaworks.algalog.model.eum.DeliveryStatus;
import com.algaworks.algalog.util.BaseEntity;
import com.algaworks.algalog.util.ValidationGroups;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;
// TODO adicionar as mensagens de validação que estão faltando e remover as que não serão mais utilizadas

@Getter
@Entity
public class Delivery extends BaseEntity<Long> {

    @Valid //TODO como esta sendo utilizado dto na camada da api, verificar necessidade de validações
    @Setter
    @NotNull
    @ManyToOne
    @JsonProperty("cliente")
    @ConvertGroup(from = Default.class, to = ValidationGroups.EntityId.class)
    private Client client;

    @Valid
    @Setter
    @NotNull
    @Embedded
    @JsonProperty("destinatario")
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "recipient_name")),
            @AttributeOverride(name = "addressEmbeddable.street", column = @Column(name = "recipient_street")),
            @AttributeOverride(name = "addressEmbeddable.district", column = @Column(name = "recipient_district")),
            @AttributeOverride(name = "addressEmbeddable.number", column = @Column(name = "recipient_house_number")),
            @AttributeOverride(name = "addressEmbeddable.complement", column = @Column(name = "recipient_address_complement"))
    })
    private RecipientEmbeddable recipient;

    @Setter
    @NotNull(message = "bateu aq")
    @JsonProperty("taxa")
    private BigDecimal fee;

    @OneToMany(mappedBy = "delivery", cascade = CascadeType.ALL)
    private List<Occurrence> occurrences = new ArrayList<>();

    @Setter
    @Enumerated(EnumType.STRING)
    @JsonProperty(value = "status", access = READ_ONLY)
    private DeliveryStatus status;

    @Setter
    @JsonProperty(value = "dataPedido", access = READ_ONLY)
    private Instant requestDate;

    @Setter
    @JsonProperty(value = "dataFinalizacao", access = READ_ONLY)
    private Instant finishedDate;

}
