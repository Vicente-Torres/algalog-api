package com.algaworks.algalog.model.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Problem {

    private Integer status;

    @JsonProperty("dataHora")
    private LocalDateTime dateTime;

    @JsonProperty("titulo")
    private String title;

    @JsonProperty("mensagens")
    private List<String> messages;

    public Problem(Integer status, LocalDateTime dateTime, String title) {
        this.status = status;
        this.dateTime = dateTime;
        this.title = title;
    }

}
