package top.gwj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.gwj.entity.Book;
import top.gwj.vo.BookVO;

public interface BookService extends IService<Book> {
    BookVO getBookById(Long id);
    boolean isIsbnExists(String isbn);
    BookVO adjustStock(Long id, Integer delta);
    BookVO restoreBook(Long id);
    BookVO convertToVO(Book book);
}