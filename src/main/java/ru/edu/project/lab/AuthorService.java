package ru.edu.project.lab;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;

    public List<Author> findAllAuthors() {
        return authorRepository.findAll();
    }

    public Author findAuthorById(long id) {
        return authorRepository.findById(id).orElseThrow(()-> new RuntimeException("Author not found"));
    }

    public void saveAuthor(Author author) {
        authorRepository.save(author);
    }

    public void updateAuthor(Author newAuthor) {
        Author oldAuthor = authorRepository.findById(newAuthor.getId())
                .orElseThrow(()-> new RuntimeException("Author not found"));

        oldAuthor.setName(newAuthor.getName());

        authorRepository.save(oldAuthor);
    }

    public void deleteAuthorById(long id) {
        authorRepository.deleteById(id);
    }
}
