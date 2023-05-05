package app.dll.entity;

import app.dll.model.AuthorDTO;
import com.fasterxml.jackson.annotation.JsonGetter;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;
    private Boolean approved;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @JsonGetter("author")
    public AuthorDTO getAuthor() {
        return new AuthorDTO(author);
    }
}
