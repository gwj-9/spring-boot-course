package top.gwj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import top.gwj.entity.Book;
import top.gwj.mapper.BookMapper;
import top.gwj.service.BookService;
import top.gwj.vo.BookVO;

@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {

    @Override
    public BookVO getBookById(Long id) {
        Book book = getById(id);
        return convertToVO(book);
    }

    @Override
    public boolean isIsbnExists(String isbn) {
        LambdaQueryWrapper<Book> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Book::getIsbn, isbn).eq(Book::getDeleted, 0);
        return count(wrapper) > 0;
    }

    @Override
    public BookVO adjustStock(Long id, Integer delta) {
        Book book = getById(id);
        book.setStock(book.getStock() + delta);
        updateById(book);
        return convertToVO(book);
    }

    @Override
    public BookVO restoreBook(Long id) {
        Book book = new Book();
        book.setId(id);
        book.setDeleted(0); // 逻辑恢复
        updateById(book);
        return getBookById(id);
    }


    @Override
    public BookVO convertToVO(Book book) {
        BookVO vo = new BookVO();
        BeanUtils.copyProperties(book, vo);
        return vo;
    }


}