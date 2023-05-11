package app.dll.service;

import app.dll.entity.Book;
import app.dll.entity.BookImage;
import app.dll.repository.BookImageRepository;
import app.dll.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final BookImageRepository bookImageRepository;
    private final FileService fileService;

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
        book.setApproved(false);
        return bookRepository.save(book);
    }

    public Book add(Book book, MultipartFile img1, MultipartFile img2, MultipartFile img3) throws IOException {
        book = add(book);

        bookImageRepository.saveAll(List.of(
                new BookImage(0, book.getId(), fileService.saveFile("img" + book.getId() + "_1", img1)),
                new BookImage(0, book.getId(), fileService.saveFile("img" + book.getId() + "_2", img2)),
                new BookImage(0, book.getId(), fileService.saveFile("img" + book.getId() + "_3", img3))
        ));


        return book;
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
