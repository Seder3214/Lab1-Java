
package ru.edu.project.lab.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
@Data
@Builder
@Schema(description="Информация о композиторе.")
public class ComposerDto {
    @JsonProperty("id")
    @Schema(description = "ID композитора в БД", example = "123")
    private Long id;

    @JsonProperty("name")
    @NotBlank
    @Schema(description = "Имя композитора", example = "Juice WRLD")
    private String name;

    @JsonProperty("music")
    @Schema(description = "Названия песен композитора")
    private List<String> music;
}
