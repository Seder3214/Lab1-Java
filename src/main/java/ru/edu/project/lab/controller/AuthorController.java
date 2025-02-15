package ru.edu.project.lab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.edu.project.lab.Author;
import ru.edu.project.lab.AuthorService;

import java.util.List;
@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping
    public List<Author> findAll() {
        return authorService.findAllAuthors();
    }

    @GetMapping("/author/{id}")
    public Author getById(@PathVariable Long id) {
        return authorService.findAuthorById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createAuthor(@RequestBody Author author) {
        authorService.saveAuthor(author);
    }

    @PutMapping("/author/")
    public void updateAuthor(@RequestBody Author author) {
        authorService.updateAuthor(author);
    }

    @DeleteMapping("/author/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAuthor(@PathVariable long id) {
        authorService.deleteAuthorById(id);
    }
}

