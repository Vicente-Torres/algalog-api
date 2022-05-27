package com.algaworks.algalog.model.entity;

import com.algaworks.algalog.util.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.Instant;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Occurrence extends BaseEntity<Long> {

    private String description;

    @ManyToOne
    private Delivery delivery;

    @JsonProperty("dataRegistro")
    private Instant registerDate;

}
