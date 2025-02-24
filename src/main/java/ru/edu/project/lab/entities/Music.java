package ru.edu.project.lab.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name="music")
public class Music {
    @Id
    @GeneratedValue
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @ManyToMany(mappedBy="music")
    private List<Composer> composers;
}
