package top.gwj.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import top.gwj.dto.BookCreateDTO;
import top.gwj.dto.BookPageQuery;
import top.gwj.dto.BookUpdateDTO;
import top.gwj.dto.StockAdjustDTO;
import top.gwj.entity.Book;
import top.gwj.service.BookService;
import top.gwj.vo.BookVO;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // 新增图书
    @PostMapping
    public BookVO createBook(@RequestBody BookCreateDTO dto) {
        Book book = new Book();
        BeanUtils.copyProperties(dto, book);
        bookService.save(book);
        return bookService.getBookById(book.getId());
    }

    // 更新图书信息
    @PutMapping("/{id}")
    public BookVO updateBook(@PathVariable Long id, @RequestBody BookUpdateDTO dto) {
        Book book = bookService.getById(id);
        BeanUtils.copyProperties(dto, book);
        bookService.updateById(book);
        return bookService.getBookById(id);
    }

    // 库存调整
    @PatchMapping("/{id}/stock/adjust")
    public BookVO adjustStock(@PathVariable Long id, @RequestBody StockAdjustDTO dto) {
        return bookService.adjustStock(id, dto.getDelta());
    }

    // 按 ID 查询
    @GetMapping("/{id}")
    public BookVO getBook(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    // ISBN 唯一性检查
    @GetMapping("/exists/isbn/{isbn}")
    public Map<String, Boolean> checkIsbn(@PathVariable String isbn) {
        Map<String, Boolean> result = new HashMap<>();
        result.put("exists", bookService.isIsbnExists(isbn));
        return result;
    }

    // 分页查询
    @GetMapping("/page")
    public Page<BookVO> pageQuery(BookPageQuery query) {
        Page<Book> page = new Page<>(query.getPageNum(), query.getPageSize());
        LambdaQueryWrapper<Book> wrapper = new LambdaQueryWrapper<>();

        // 条件过滤（示例：关键词、分类）
        if (query.getKeyword() != null) {
            wrapper.like(Book::getTitle, query.getKeyword())
                    .or().like(Book::getAuthor, query.getKeyword());
        }
        if (query.getCategory() != null) {
            wrapper.eq(Book::getCategory, query.getCategory());
        }

        Page<Book> resultPage = bookService.page(page, wrapper);
        Page<BookVO> voPage = new Page<>();
        BeanUtils.copyProperties(resultPage, voPage);
        voPage.setRecords(resultPage.getRecords().stream()
                .map(book -> bookService.convertToVO(book))
                .collect(java.util.stream.Collectors.toList()));
        return voPage;
    }

    // 逻辑删除
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.removeById(id); // MyBatis Plus 自动逻辑删除
    }

    // 逻辑恢复
    @PutMapping("/{id}/restore")
    public BookVO restoreBook(@PathVariable Long id) {
        return bookService.restoreBook(id);
    }
}