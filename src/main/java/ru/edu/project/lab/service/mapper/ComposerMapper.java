package ru.edu.project.lab.service.mapper;

import org.springframework.stereotype.Service;
import ru.edu.project.lab.dto.ComposerDto;
import ru.edu.project.lab.entities.Composer;
import ru.edu.project.lab.entities.Music;

import java.util.List;
@Service
public class ComposerMapper {

    public List<ComposerDto> toListDto(List<Composer> composers) {
        return composers.stream().map(this::toDto).toList();
    }

    public ComposerDto toDto(Composer composer) {
        return ComposerDto.builder()
                .id(composer.getId())
                .name(composer.getName())
                .music(composer.getMusic().stream()
                        .map(Music::getName)
                        .toList())
                .build();
    }

    public Composer toEntity(ComposerDto composerDto) {
        Composer composer = new Composer();

        composer.setId(composer.getId());
        composer.setName(composerDto.getName());

        return composer;
    }
}
