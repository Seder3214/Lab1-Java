package ru.edu.project.lab.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.edu.project.lab.entities.Composer;

public interface ComposerRepository extends JpaRepository<Composer, Long> {
}
