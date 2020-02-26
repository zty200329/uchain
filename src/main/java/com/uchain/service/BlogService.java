package com.uchain.service;

import com.uchain.entity.BlogInfo;
import com.uchain.form.BlogForm;
import com.uchain.vo.PicVO;
import com.uchain.vo.ResultVO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: LZH
 * @Date: 2020/2/7 下午7:56
 * @Description:
 */
public interface BlogService {

    /**
     * 新建一篇博客
     *
     * @return
     */
    Boolean insert(BlogInfo blogInfo);

    /**
     * 删除一篇博客
     *
     * @param id
     * @return
     */
    Boolean delete(Integer id);

    /**
     * 更新一篇博客
     *
     * @param blogInfo
     * @return
     */
    Boolean update(BlogInfo blogInfo);

    /**
     * 新增博客
     * @param blogForm
     * @return
     */
    ResultVO addBlog(BlogForm blogForm);

    /**
     * 获取单个博客
     *
     * @param id
     * @return
     */
    ResultVO getOne(Integer id);

    /**
     * 通过用户名查询所有他发表的博客
     *
     * @param userName
     * @return
     */
    ResultVO getByUserName(String userName, Integer pageNum);

    /**
     * 获取所有博客
     *
     * @return
     */
    ResultVO getAll(Integer pageNum);

    /**
     * 接受前端传来的图片
     * @return
     */
    PicVO getPic(MultipartFile file, HttpServletRequest request, HttpServletResponse response);

    /**
     * 删除置顶博客
     */
    ResultVO deleteOne(Integer id);

    /**
     * 更新博客
     * @param blogInfo
     * @return
     */
    ResultVO updateBlog(BlogInfo blogInfo);

    /**
     * 查询所有同类型的博客
     * @param type
     * @return
     */
    ResultVO getByType(Integer type, Integer pageNum);

}
