package app.dll.model;

import app.dll.entity.Author;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDTO {
    private Integer id;
    private String name;
    private String description;

    public AuthorDTO(Author author) {
        id = author.getId();
        name = author.getName();
        description = author.getDescription();
    }
}
