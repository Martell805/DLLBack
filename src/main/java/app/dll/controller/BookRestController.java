package app.dll.controller;

import app.dll.entity.Book;
import app.dll.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/book")
public class BookRestController {
    private final BookService bookService;
    
    @GetMapping
    public Book get(@RequestParam Integer id) {
        return bookService.get(id);
    }

    @GetMapping("all")
    public List<Book> getAll() {
        return bookService.getAllApproved();
    }

    @PostMapping
    public Book add(@RequestBody Book book) {
        return bookService.add(book);
    }

    @GetMapping("all/moderate")
    public List<Book> getAllModerate() {
        return bookService.getAllNotApproved();
    }

    @PatchMapping("approve")
    public Book approve(@RequestParam Integer id) {
        return bookService.approve(id);
    }
}
