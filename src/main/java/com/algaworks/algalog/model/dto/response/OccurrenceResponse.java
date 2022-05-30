package com.algaworks.algalog.model.dto.response;

import com.algaworks.algalog.model.entity.Occurrence;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OccurrenceResponse {

    private Long id;

    @JsonProperty("descricao")
    private String description;

    @JsonProperty("dataRegistro")
    private Instant registerDate;

    public static OccurrenceResponse toDTO(Occurrence occurrence) {
        var response = new OccurrenceResponse(occurrence.getId(), occurrence.getDescription(), occurrence.getRegisterDate());
        return response;
    }

    public static List<OccurrenceResponse> toDTOCollection(List<Occurrence> occurrences) {
        return occurrences.stream().map(occurrence -> toDTO(occurrence)).toList();
    }

}
