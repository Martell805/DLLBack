package app.dll.controller;

import app.dll.entity.Author;
import app.dll.entity.Book;
import app.dll.service.AuthorService;
import app.dll.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.io.IOException;

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
    public String addAuthor(Model model, Authentication authentication) {
        if (!authentication.isAuthenticated()) {
            return "redirect:/login";
        }

        model.addAttribute("author", new Author());
        return "add_author";
    }

    @PostMapping("add-author")
    public String addAuthorSubmit(@ModelAttribute Author author, MultipartFile file, Authentication authentication) throws IOException {
        if (!authentication.isAuthenticated()) {
            return "redirect:/login";
        }

        authorService.add(author, file);

        return "redirect:/index";
    }

    @GetMapping("add-book")
    public String addBook(Model model, Authentication authentication) {
        if (!authentication.isAuthenticated()) {
            return "redirect:/login";
        }

        model.addAttribute("book", new Book());
        return "add_book";
    }

    @PostMapping("add-book")
    public String addBookSubmit(@ModelAttribute Book book, MultipartFile img1, MultipartFile img2, MultipartFile img3, Authentication authentication) throws IOException {
        if (!authentication.isAuthenticated()) {
            return "redirect:/login";
        }

        bookService.add(book, img1, img2, img3);

        return "redirect:/index";
    }
}
