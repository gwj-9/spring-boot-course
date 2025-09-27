package top.gwj.dto;

import lombok.Data;

@Data
public class BookCreateDTO {
    private String title;
    private String author;
    private String isbn;
    private String category;
    private Integer stock;
}