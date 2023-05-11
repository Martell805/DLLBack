package app.dll.service;

import app.dll.entity.Author;
import app.dll.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final FileService fileService;
    
    public Author get(Integer id) {
        return authorRepository.findById(id).orElseThrow();
    }

    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    public Author add(Author author) {
        return authorRepository.save(author);
    }

    public Author add(Author author, MultipartFile file) throws IOException {
        author = authorRepository.save(author);

        String filename = fileService.saveFile("portrait" + author.getId(), file);

        author.setPortrait(filename);
        author = authorRepository.save(author);

        return author;
    }
}
