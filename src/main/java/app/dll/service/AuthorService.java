package app.dll.service;

import app.dll.entity.Author;
import app.dll.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;
    
    public Author get(Integer id) {
        return authorRepository.findById(id).orElseThrow();
    }

    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    public Author add(Author author) {
        return authorRepository.save(author);
    }
}
