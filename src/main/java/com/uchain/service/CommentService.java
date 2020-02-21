package com.uchain.service;

import com.uchain.entity.CommentInfo;
import com.uchain.form.CommentForm;
import com.uchain.vo.ResultVO;

/**
 * @Author: LZH
 * @Date: 2020/2/18 下午4:54
 * @Description:
 */
public interface CommentService {

    Boolean insert(CommentInfo commentInfo);

    Boolean delete(Integer id);

    /**
     * 获取单个评论
     *
     * @param id
     * @return
     */
    ResultVO getOne(Integer id);

    /**
     * 获取一个博客所有的评论
     * @param bid
     * @return
     */
    ResultVO getOneBlogAll(Integer bid);

    /**
     * 新建一个评论
     * @param commentForm
     * @return
     */
    ResultVO addOne(CommentForm commentForm);

    /**
     * 删除一个评论
     * @param id
     * @return
     */
    ResultVO deleteONe(Integer id);
}
