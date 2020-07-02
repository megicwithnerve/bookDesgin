package com.magician.book.services.impls;

import com.magician.book.dao.BookMapper;
import com.magician.book.dao.ThumbMapper;
import com.magician.book.pojo.Book;
import com.magician.book.pojo.Thumb;
import com.magician.book.services.ThumbService;
import com.magician.book.utils.APIResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class ThumbServiceImpl implements ThumbService {
    @Resource
    ThumbMapper thumbMapper;

    @Resource
    BookMapper bookMapper;

    @Override
    public APIResult getThumbOne(Thumb thumb , Integer status) {

        APIResult apiResult = null;
        Book book = bookMapper.selectByPrimaryKey(thumb.getBookId());
        Thumb thumb1 = thumbMapper.getThumbOne(thumb);

        if(status == 0 && thumb1 != null){

            thumb1.setUpdatetime(new Date());
            thumb1.setThumbStatus(0);
            Integer result = thumbMapper.updateByPrimaryKeySelective(thumb1);
            book.setThumbs(book.getThumbs()-1);
            bookMapper.updateByPrimaryKeySelective(book);
            apiResult = new APIResult("取消点赞",true,200);
        }else {
            if(thumb1 == null && status == 1){
                thumb.setUpdatetime(new Date());
                thumb.setThumbStatus(1);
                Integer result = thumbMapper.insertSelective(thumb);
                book.setThumbs(book.getThumbs()+1);
                bookMapper.updateByPrimaryKeySelective(book);
                apiResult = new APIResult("增加点赞",true,200);
            }else if(thumb1 != null && thumb1.getThumbStatus() == 0){
                thumb1.setThumbStatus(1);
                thumb1.setUpdatetime(new Date());
                thumbMapper.updateByPrimaryKeySelective(thumb1);
                book.setThumbs(book.getThumbs()+1);
                bookMapper.updateByPrimaryKeySelective(book);
                apiResult = new APIResult("增加点赞",true,200);

            }

        }
        apiResult.setData(book.getThumbs());
        return apiResult;
    }

    @Override
    public boolean getThunmbType(Integer bookId, Integer readerId) {
        Thumb thumbDemo = new Thumb();
        if(readerId == null)
            readerId = 0;
        thumbDemo.setReaderId(readerId);
        thumbDemo.setBookId(bookId);
        Thumb thumb = thumbMapper.getThumbOne(thumbDemo);
        if(thumb == null || thumb.getThumbStatus() == 0)
         return false;



        return true;
    }
}
