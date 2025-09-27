package top.gwj.dto;

import lombok.Data;

@Data
public class BookPageQuery {
    private Integer pageNum = 1;   // 页码
    private Integer pageSize = 10; // 每页数量
    private String keyword;        // 搜索关键词（标题/作者等）
    private String category;       // 分类筛选
}