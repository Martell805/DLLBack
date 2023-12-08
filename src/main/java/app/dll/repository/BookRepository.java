package app.dll.repository;

import app.dll.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findAllByApproved(Boolean approved);
    List<Book> findAllByAuthorId(Integer id);
}