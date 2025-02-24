package ru.edu.project.lab.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity

@Table(name="composers")
public class Composer {
    @Id
    @GeneratedValue
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;


    @ManyToMany
    @JoinTable(
            name="composers_musics",
            joinColumns = @JoinColumn(name="music_id",referencedColumnName ="id"),
            inverseJoinColumns = @JoinColumn(name="composer_id",referencedColumnName = "id")
    )
    private List<Music> music;
}
