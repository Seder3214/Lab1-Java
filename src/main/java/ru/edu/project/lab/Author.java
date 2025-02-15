package ru.edu.project.lab;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name="authors")
public class Author {
    @Id
    @GeneratedValue
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;


    @ManyToMany
    @JoinTable(
            name="authors_books",
            joinColumns = @JoinColumn(name="book_id",referencedColumnName ="id"),
            inverseJoinColumns = @JoinColumn(name="author_id",referencedColumnName = "id")
    )
    private List<Book> books;
}
