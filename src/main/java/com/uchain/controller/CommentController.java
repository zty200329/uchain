package com.uchain.controller;

import com.uchain.form.CommentForm;
import com.uchain.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: LZH
 * @Date: 2020/2/18 下午5:55
 * @Description:
 */
@RestController
@CrossOrigin
@Api(tags = "博客接口")
@RequestMapping("/comment")
@Slf4j
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/addComment")
    @ApiOperation("新增评论")
    public Object addComment(@RequestBody CommentForm commentForm){
        return commentService.addOne(commentForm);
    }

    @PostMapping("/deleteComment")
    @ApiOperation("删除一条评论")
    public Object deleteComment(Integer id){
        return commentService.deleteONe(id);
    }

    @PostMapping("/getBlogComment")
    @ApiOperation("获取一个博客的所有评论")
    public Object getBlogComment(Integer bid){
        return commentService.getOneBlogAll(bid);
    }

    @GetMapping("/getAComment")
    @ApiOperation("获取单个评论")
    public Object getAComment(Integer id){
        return commentService.getOne(id);
    }
}
