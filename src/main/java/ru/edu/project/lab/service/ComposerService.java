package ru.edu.project.lab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.edu.project.lab.baseresponse.ErrorType;
import ru.edu.project.lab.entities.Composer;
import ru.edu.project.lab.Repository.ComposerRepository;
import ru.edu.project.lab.dto.ComposerDto;
import ru.edu.project.lab.exception.PenzGtuException;
import ru.edu.project.lab.service.mapper.ComposerMapper;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ComposerService {
    private final ComposerRepository composerRepository;
    private final ComposerMapper composerMapper;


    public List<ComposerDto> findAllComposers() {

        return composerMapper.toListDto(composerRepository.findAll());
    }

    public ComposerDto findComposerById(long id) {
        Composer composer = composerRepository.findById(id)
                .orElseThrow(()-> new PenzGtuException(ErrorType.NOT_FOUND,"Error finding composer by id"));
        return composerMapper.toDto(composer);
    }

    public void saveComposer(ComposerDto composerDto) {
        Composer composer = composerMapper.toEntity(composerDto);

        composerRepository.save(composer);

    }

    public void updateComposer(ComposerDto newComposer) {
        Composer oldComposer = composerRepository.findById(newComposer.getId())
                .orElseThrow(()-> new PenzGtuException(ErrorType.NOT_FOUND,"Error finding composer by id"));
        oldComposer.setName(newComposer.getName());

        composerRepository.save(oldComposer);
    }

    public void deleteComposerById(long id) {
        if (!composerRepository.existsById(id)) {
            throw new PenzGtuException(ErrorType.NOT_FOUND,"Error finding composer by id");
        }

        composerRepository.deleteById(id);

    }
}
