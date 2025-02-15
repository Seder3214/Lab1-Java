package ru.edu.project.lab;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository authorRepository;

    public List<Book> findAllBooks(Long id) {
        return authorRepository.findAll();
    }

    public Book findBookById(long id) {
        return authorRepository.findById(id).orElseThrow(()-> new RuntimeException("Book not found"));
    }

    public void updateBook(Book newBook) {
        Book oldBook = authorRepository.findById(newBook.getId())
                .orElseThrow(()-> new RuntimeException("Book not found"));

        oldBook.setName(newBook.getName());

        authorRepository.save(oldBook);
    }

    public void deleteBook(Book author) {
        authorRepository.delete(author);
    }
}
