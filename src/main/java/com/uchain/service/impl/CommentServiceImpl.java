package com.uchain.service.impl;

import com.uchain.dao.CommentInfoMapper;
import com.uchain.entity.CommentInfo;
import com.uchain.enums.ResultEnum;
import com.uchain.form.CommentForm;
import com.uchain.service.CommentService;
import com.uchain.util.ResultVOUtil;
import com.uchain.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: LZH
 * @Date: 2020/2/18 下午4:55
 * @Description:
 */
@Service
@Slf4j
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentInfoMapper commentInfoMapper;

    @Override
    public Boolean insert(CommentInfo commentInfo) {
        return (commentInfoMapper.insert(commentInfo) == 1);
    }

    @Override
    public Boolean delete(Integer id) {
        return (commentInfoMapper.deleteByPrimaryKey(id) == 1);
    }

    @Override
    public ResultVO getOne(Integer id) {
        return ResultVOUtil.success(commentInfoMapper.selectByPrimaryKey(id));
    }

    @Override
    public ResultVO getOneBlogAll(Integer bid) {
        return ResultVOUtil.success(commentInfoMapper.selectByBlogId(bid));
    }

    @Override
    public ResultVO addOne(CommentForm commentForm) {
        CommentInfo commentInfo = new CommentInfo();
        BeanUtils.copyProperties(commentForm, commentInfo);
        commentInfo.setReplyUserId(commentForm.getReplyUserId());
        if(!insert(commentInfo)){
            return ResultVOUtil.error(ResultEnum.SQL_ERROR);
        }
        return ResultVOUtil.success();
    }

    @Override
    public ResultVO deleteONe(Integer id) {
        if(!delete(id)){
            return ResultVOUtil.error(ResultEnum.SQL_ERROR);
        }
        return ResultVOUtil.success(commentInfoMapper.selectByPrimaryKey(id));
    }
}
