package app.dll.service;

import app.dll.entity.Book;
import app.dll.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public Book get(Integer id) {
        return bookRepository.findById(id).orElseThrow();
    }

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public Book add(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> getAllApproved() {
        return bookRepository.findAllByApproved(true);
    }

    public List<Book> getAllNotApproved() {
        return bookRepository.findAllByApproved(false);
    }

    public Book approve(Integer id) {
        Book book = get(id);

        book.setApproved(true);

        return bookRepository.save(book);
    }
}
