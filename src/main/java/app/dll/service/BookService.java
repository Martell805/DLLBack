package app.dll.service;

import app.dll.entity.Book;
import app.dll.entity.BookImage;
import app.dll.repository.BookImageRepository;
import app.dll.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final BookImageRepository bookImageRepository;

    public Book get(Integer id) {
        return bookRepository.findById(id).orElseThrow();
    }

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public List<Book> getAllByAuthorId(Integer id) {
        return bookRepository.findAllByAuthorId(id);
    }

    public List<BookImage> getAllImages(Integer id) {
        return bookImageRepository.findAllByBookId(id);
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
