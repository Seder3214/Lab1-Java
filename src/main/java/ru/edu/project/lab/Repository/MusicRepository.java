package ru.edu.project.lab.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.edu.project.lab.entities.Music;

public interface MusicRepository extends JpaRepository<Music, Long> {
}
