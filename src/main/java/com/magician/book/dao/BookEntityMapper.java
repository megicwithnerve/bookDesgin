package com.magician.book.dao;

import com.magician.book.pojo.BookEntity;

import java.util.List;
import java.util.Map;

public interface BookEntityMapper {

    List<BookEntity> getBookEntitylist(Integer writerId,String writerName,String bookName,String bookKind, Integer chapterTime, Integer clicks, Integer thumbs, Integer offset, Integer limit);

    BookEntity getBookEntityById(Integer bookId,Integer offset,Integer limit);
}
