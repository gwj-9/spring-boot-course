package top.gwj.vo;

import lombok.Data;

import java.util.Date;

@Data
public class BookVO {
    private Long id;
    private String title;
    private String author;
    private String isbn;
    private String category;
    private Integer stock;
    private Date createTime;
    private Date updateTime;
}