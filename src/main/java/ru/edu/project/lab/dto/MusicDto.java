
package ru.edu.project.lab.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@Schema(description="Информация о песне.")
public class MusicDto {
    @JsonProperty("id")
    @Schema(description = "ID песни в БД", example = "123")
    private Long id;

    @JsonProperty("name")
    @NotBlank
    @Schema(description = "Название песни", example = "music1")
    private String name;

    @JsonProperty("composer")
    @Schema(description = "Имя композитора песни")
    private List<String> composer;
}
