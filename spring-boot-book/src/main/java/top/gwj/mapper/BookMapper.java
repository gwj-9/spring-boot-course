package top.gwj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.gwj.entity.Book;

@Mapper
public interface BookMapper extends BaseMapper<Book> {
    // 继承 BaseMapper，默认实现 CRUD
}