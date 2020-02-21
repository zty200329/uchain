package com.uchain.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.uchain.dao.BlogInfoMapper;
import com.uchain.dto.BlogDTO;
import com.uchain.dto.VisitedDTO;
import com.uchain.entity.BlogInfo;
import com.uchain.enums.ResultEnum;
import com.uchain.form.BlogForm;
import com.uchain.service.BlogService;
import com.uchain.util.FileUtil;
import com.uchain.util.RedisUtil;
import com.uchain.util.ResultVOUtil;
import com.uchain.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: LZH
 * @Date: 2020/2/7 下午8:07
 * @Description:
 */
@Slf4j
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogInfoMapper blogInfoMapper;

    @Autowired
    private RedisUtil redisUtil;


    @Override
    public Boolean insert(BlogInfo blogInfo) {
        return (blogInfoMapper.insert(blogInfo) == 1);
    }

    @Override
    public Boolean delete(Integer id) {
        return (blogInfoMapper.deleteByPrimaryKey(id) == 1);
    }

    @Override
    public Boolean update(BlogInfo blogInfo) {
        return (blogInfoMapper.updateByPrimaryKey(blogInfo) == 1);
    }

    @Override
    public ResultVO addBlog(BlogForm blogForm) {
        if(blogInfoMapper.selectByArticleTittle(blogForm.getArticleTittle()) != null){
            return ResultVOUtil.error(ResultEnum.PROJECT_ALREADY_EXIST);
        }
            BlogInfo blogInfo = new BlogInfo();
            BeanUtils.copyProperties(blogForm, blogInfo);
            blogInfo.setIstop(0);
            blogInfo.setArticleLike(0);
            VisitedDTO visitedDTO = new VisitedDTO();
            visitedDTO.setAmount(0);
            if (insert(blogInfo)) {
                visitedDTO.setBid(blogForm.getArticleTittle());
                redisUtil.set(visitedDTO.getBid(),visitedDTO.getAmount().toString());
                return ResultVOUtil.success(blogInfo);
            } else {
                return ResultVOUtil.error(ResultEnum.SQL_ERROR);
            }
    }

    @Override
    public ResultVO getOne(Integer id) {
        BlogInfo blogInfo = blogInfoMapper.selectByPrimaryKey(id);
        if(blogInfo == null){
            return ResultVOUtil.error(ResultEnum.ARTICLE_IS_NOT_EXIST);
        }
        BlogDTO blogDTO = new BlogDTO();
        BeanUtils.copyProperties(blogInfo, blogDTO);
        redisUtil.incrBy(blogInfo.getArticleTittle(),1);
        blogDTO.setArticleVisited(Integer.parseInt(redisUtil.get(blogInfo.getArticleTittle())));
        return ResultVOUtil.success(blogDTO);
    }

    @Override
    public ResultVO getByUserName(String userName, Integer pageNum) {
        //调用PageHelper中的方法,设置页面页数与每页数据个数
        PageHelper.startPage(pageNum, 12);

        List<BlogInfo> blogList = blogInfoMapper.selectByArticleOwner(userName);
        if(blogList == null){
            return ResultVOUtil.error(ResultEnum.USER_ARTICLE_NULL);
        }
        List<BlogDTO> blogDTOList = new ArrayList<>();
        BlogDTO blogDTO = null;
        for (BlogInfo blogInfo: blogList) {
            blogDTO = new BlogDTO();
            BeanUtils.copyProperties(blogInfo, blogDTO);
            blogDTO.setArticleVisited(Integer.parseInt(redisUtil.get(blogInfo.getArticleTittle())));
            blogDTOList.add(blogDTO);
        }

        PageInfo<BlogDTO> pageInfo = new PageInfo<>(blogDTOList);
        return ResultVOUtil.success(pageInfo);
    }

    @Override
    public ResultVO getAll(Integer pageNum) {
        //调用PageHelper中的方法,设置页面页数与每页数据个数
        PageHelper.startPage(pageNum, 12);

        //从数据库中查询数据
        List<BlogInfo> blogList = blogInfoMapper.selectAll();
        List<BlogDTO> blogDTOList = new ArrayList<>();
        BlogDTO blogDTO = null;
        for (BlogInfo blogInfo: blogList) {
            blogDTO = new BlogDTO();
            BeanUtils.copyProperties(blogInfo, blogDTO);
            blogDTOList.add(blogDTO);
        }
        //TODO，将数据存入PageInfo中
        PageInfo<BlogDTO> pageInfo = new PageInfo<>(blogDTOList);
        return ResultVOUtil.success(pageInfo);
    }

    @Override
    public ResultVO getPic(MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        String filePath = "/home/lzh/IdeaProjects/blog/pic";
        String url = "http:47.96.184.129:8087/";
        if(FileUtil.uploadPic(filePath, file, request, response)){
            url += file.getOriginalFilename();
            return ResultVOUtil.success(url);
        }else {
            return ResultVOUtil.error(ResultEnum.PRO_UPLOAD_ERROR);
        }
    }

    @Override
    public ResultVO deleteOne(Integer id) {
        if(!delete(id)){
            return ResultVOUtil.error(ResultEnum.ARTICLE_IS_NOT_EXIST);
        }
        redisUtil.delete(blogInfoMapper.selectByPrimaryKey(id).getArticleTittle());
        return (getOne(id));
    }

    @Override
    public ResultVO updateBlog(BlogInfo blogInfo) {
        if(blogInfoMapper.updateByPrimaryKey(blogInfo) == 1){
            return ResultVOUtil.success(blogInfo);
        }
        else {
            return ResultVOUtil.error(ResultEnum.SQL_ERROR);
        }
    }

    @Override
    public ResultVO getByType(Integer type, Integer pageNum) {
        //调用PageHelper中的方法,设置页面页数与每页数据个数
        PageHelper.startPage(pageNum, 12);

        List<BlogInfo> blogInfoList = blogInfoMapper.selectByArticleType(type);
        BlogDTO blogDTO =  null;
        List<BlogDTO> blogDTOList = new ArrayList<>();
        for (BlogInfo bloginfo: blogInfoList) {
            blogDTO = new BlogDTO();
            BeanUtils.copyProperties(bloginfo, blogDTO);
            blogDTO.setArticleVisited(Integer.parseInt(redisUtil.get(bloginfo.getArticleTittle())));
            blogDTOList.add(blogDTO);
        }
        PageInfo<BlogDTO> pageInfo = new PageInfo<>(blogDTOList);
        return ResultVOUtil.success(pageInfo);
    }

}
