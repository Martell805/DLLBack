package app.dll.controller;

import app.dll.entity.Author;
import app.dll.entity.Book;
import app.dll.entity.User;
import app.dll.service.AuthorService;
import app.dll.service.BookService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping()
@RequiredArgsConstructor
public class ModerPageController {
    private final BookService bookService;
    private final AuthorService authorService;

    @GetMapping("approve")
    public String approve(@RequestParam Integer id, RedirectAttributes attributes) {
        bookService.approve(id);

        attributes.addAttribute("id", id);

        return "redirect:/book";
    }

    @GetMapping("add-author")
    public String addAuthor(Model model) {
        model.addAttribute("author", new Author());
        return "add_author";
    }

    @PostMapping("add-author")
    public String addAuthorSubmit(@ModelAttribute Author author) {
        authorService.add(author);

        return "redirect:/index";
    }

    @GetMapping("add-book")
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        return "add_book";
    }

    @PostMapping("add-book")
    public String addBookSubmit(@ModelAttribute Book book) {
        bookService.add(book);

        return "redirect:/index";
    }
}
