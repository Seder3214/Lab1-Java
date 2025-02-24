package ru.edu.project.lab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.edu.project.lab.entities.Composer;
import ru.edu.project.lab.Repository.ComposerRepository;
import ru.edu.project.lab.dto.ComposerDto;
import ru.edu.project.lab.service.mapper.ComposerMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ComposerService {
    private final ComposerRepository composerRepository;
    private final ComposerMapper composerMapper;

    public List<ComposerDto> findAllAuthors() {

        return composerMapper.toListDto(composerRepository.findAll());
    }

    public ComposerDto findAuthorById(long id) {
        Composer composer = composerRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Author not found"));
        return composerMapper.toDto(composer);
    }

    public void saveAuthor(ComposerDto composerDto) {
        Composer composer = composerMapper.toEntity(composerDto);

        composerRepository.save(composer);

    }

    public void updateAuthor(ComposerDto newComposer) {
        Composer oldComposer = composerRepository.findById(newComposer.getId())
                .orElseThrow(()-> new RuntimeException("Author not found"));

        oldComposer.setName(newComposer.getName());

        composerRepository.save(oldComposer);
    }

    public void deleteAuthorById(long id) {
        composerRepository.deleteById(id);
    }
}
