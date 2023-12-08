package app.dll.controller;

import app.dll.entity.Author;
import app.dll.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/author")
public class AuthorRestController {
    private final AuthorService authorService;

    @GetMapping
    public Author get(@RequestParam Integer id) {
        return authorService.get(id);
    }

    @GetMapping("all")
    public List<Author> getAll() {
        return authorService.getAll();
    }

    @PostMapping
    public Author add(@RequestBody Author author) {
        return authorService.add(author);
    }
}
