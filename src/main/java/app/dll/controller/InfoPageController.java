package app.dll.controller;

import app.dll.entity.Author;
import app.dll.entity.Book;
import app.dll.entity.BookImage;
import app.dll.service.AuthorService;
import app.dll.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping()
@RequiredArgsConstructor
public class InfoPageController {
    private final BookService bookService;
    private final AuthorService authorService;

    @GetMapping("/book")
    public String book(@RequestParam Integer id, Model model) {
        Book book = bookService.get(id);
        Author author = authorService.get(book.getAuthorId());
        List<BookImage> img = bookService.getAllImages(id);

        model.addAttribute("book", book);
        model.addAttribute("img", img);
        model.addAttribute("author", author);

        return "book";
    }

    @GetMapping("/author")
    public String author(@RequestParam Integer id, Model model) {
        Author author = authorService.get(id);
        List<Book> books = bookService.getAllByAuthorId(id);

        model.addAttribute("author", author);
        model.addAttribute("books", books);

        return "author";
    }

    @GetMapping("/book-gallery")
    public String bookGallery(Model model) {
        List<Book> books = bookService.getAllApproved();
        List<Author> authors = new ArrayList<>();
        List<BookImage> bookImages = new ArrayList<>();

        books.forEach(book -> {
            authors.add(authorService.get(book.getAuthorId()));
            bookImages.add(bookService.getAllImages(book.getId()).get(0));
        });

        model.addAttribute("books", books);
        model.addAttribute("authors", authors);
        model.addAttribute("bookImages", bookImages);

        return "book_gallery";
    }
}
