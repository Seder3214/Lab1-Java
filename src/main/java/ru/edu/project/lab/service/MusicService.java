package ru.edu.project.lab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.edu.project.lab.Repository.ComposerRepository;
import ru.edu.project.lab.Repository.MusicRepository;
import ru.edu.project.lab.entities.Music;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MusicService {
    private final MusicRepository composerRepository;

    public List<Music> findAllMusic(Long id) {
        return composerRepository.findAll();
    }

    public Music findMusicById(Long id) {
        return composerRepository.findById(id).orElseThrow(()-> new RuntimeException("Music not found"));
    }

    public void updateMusic(Music newMusic) {
        Music oldMusic = composerRepository.findById(newMusic.getId()).orElseThrow(()-> new RuntimeException("Music not found"));

        oldMusic.setName(newMusic.getName());

        composerRepository.save(oldMusic);
    }

    public void deleteMusic(Music composer) {
        composerRepository.delete(composer);
    }
}
