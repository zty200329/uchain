package com.uchain.controller;

import com.uchain.entity.BlogInfo;
import com.uchain.form.BlogForm;
import com.uchain.service.BlogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: LZH
 * @Date: 2020/2/7 下午9:03
 * @Description:
 */
@RestController
@CrossOrigin
@Api(tags = "博客接口")
@RequestMapping("/blog")
@Slf4j
public class BlogController {

    @Autowired
    private BlogService blogService;

    @PostMapping("/addBlog")
    @ApiOperation("新增博客")
    public Object addBlog(@RequestBody BlogForm blogForm){
        return blogService.addBlog(blogForm);
    }

    @PostMapping("/updateBlog")
    @ApiOperation("更新博客")
    public Object updateBlog(@RequestBody BlogInfo blogInfo){
        return blogService.updateBlog(blogInfo);
    }

    @GetMapping("/getOneBlog")
    @ApiOperation("获取单个博客")
    public Object getOneBlog(Integer id){
        return blogService.getOne(id);
    }

    @GetMapping("/getAllBLog")
    @ApiOperation("获取所有博客")
    public Object getAllBlog(Integer pageNum){
        return blogService.getAll(pageNum);
    }

    @PostMapping("/uploadPic")
    @ApiOperation("上传图片")
    public Object uploadPic(MultipartFile file, HttpServletRequest request, HttpServletResponse response){
        return blogService.getPic(file, request, response);
    }

    @PostMapping("/getOneUserAll")
    @ApiOperation("获取某一用户所有博客")
    public Object getSomeoneAll(String name, Integer pageNum){
        return blogService.getByUserName(name, pageNum);
    }

    @PostMapping("/deleteOne")
    @ApiOperation("删除一篇博客")
    public Object deleteOne(Integer id){
        return blogService.deleteOne(id);
    }

    @PostMapping("/getByType")
    @ApiOperation("根据博客类型查询")
    public Object getByType(Integer type, Integer pageNum){
        return blogService.getByType(type, pageNum);
    }


}
