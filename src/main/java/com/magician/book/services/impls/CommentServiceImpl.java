package com.magician.book.services.impls;

import com.magician.book.dao.CommentMapper;
import com.magician.book.pojo.Comment;
import com.magician.book.pojo.CommentEntity;
import com.magician.book.services.CommentServices;
import com.magician.book.utils.APIResult;
import com.magician.book.utils.Pager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentServices {

    @Resource
    CommentMapper commentMapper;

    @Override
    public Pager getBookComment(Integer bookId, Integer indexpage, Integer pagesize) {

        List<CommentEntity> list = commentMapper.selectCommentEntity(bookId,null,null);
        Pager pager = new Pager(list.size(),indexpage,pagesize);
        List<CommentEntity> result = commentMapper.selectCommentEntity(bookId,pager.getBeginrows(),pagesize);

        pager.setData(result);
        return pager;
    }

    @Override
    public APIResult delBookComment(Integer bookId, Integer commentId, Integer writerId, Integer type) {
        APIResult apiResult;
        Integer result = commentMapper.deleteByPrimaryKey(commentId);
        if(result == 0){
            apiResult = new APIResult("删除失败",false,500,null);
        }else {
            apiResult = new APIResult("删除成功",true,200,null);
        }

        return apiResult;
    }

    @Override
    public APIResult insertBookComment(Comment comment) {

        APIResult apiResult = null;
        comment.setCreatetime(new Date());
        if(comment.getParentId()==null){
            comment.setParentId(0);
        }
        Integer result = commentMapper.insertSelective(comment);
        if(result == 0){
            apiResult = new APIResult("发布失败",false,500,null);
        }else {
            apiResult = new APIResult("发布成功",true,200,comment);
        }

        return apiResult;
    }
}
