package app.dll.repository;

import app.dll.entity.BookImage;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookImageRepository extends JpaRepository<BookImage, Integer> {
    List<BookImage> findAllByBookId(Integer bookId);
}