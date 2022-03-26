package com.algaworks.algalog.util;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity<I extends Number> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private I id;

}