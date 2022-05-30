package com.algaworks.algalog.util;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity<I extends Number> {

    @Id
    @NotNull(groups = ValidationGroups.EntityId.class)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private I id;

}