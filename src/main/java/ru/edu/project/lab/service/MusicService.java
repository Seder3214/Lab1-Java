package ru.edu.project.lab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.edu.project.lab.entities.Music;
import ru.edu.project.lab.Repository.MusicRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MusicService {
    private final MusicRepository composerRepository;

    public List<Music> findAllBooks(Long id) {
        return composerRepository.findAll();
    }

    public Music findBookById(long id) {
        return composerRepository.findById(id).orElseThrow(()-> new RuntimeException("Book not found"));
    }

    public void updateBook(Music newMusic) {
        Music oldMusic = composerRepository.findById(newMusic.getId())
                .orElseThrow(()-> new RuntimeException("Book not found"));

        oldMusic.setName(newMusic.getName());

        composerRepository.save(oldMusic);
    }

    public void deleteBook(Music author) {
        composerRepository.delete(author);
    }
}
