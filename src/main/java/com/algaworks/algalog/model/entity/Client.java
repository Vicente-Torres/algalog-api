package com.algaworks.algalog.model.entity;

import com.algaworks.algalog.util.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@NoArgsConstructor
public class Client extends BaseEntity<Long> {

    @Getter
    @NotBlank
    @Size(max = 11)
    @JsonProperty("telefone")
    @Pattern(regexp = "\\d{11}")
    private String phone;

    @Email
    @Getter
    @Size(max = 100)
    @JsonProperty("e-mail")
    private String email;

    @NotBlank
    @Size(max = 60)
    @JsonProperty("nome")
    private String name;

}
