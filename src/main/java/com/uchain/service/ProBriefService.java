package com.uchain.service;


import com.uchain.form.ProBriefForm;
import com.uchain.vo.ResultVO;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author: zty
 * @Date: 2020/1/10 22:22
 */

public interface ProBriefService {
    /**
     * 上传一个项目
     * @return
     */
    ResultVO uploadProBrief(ProBriefForm proBriefForm, MultipartFile file, BindingResult bindingResult);

    Boolean isProExist(String proName);

    ResultVO downloadPro(Integer id, HttpServletResponse response);

    ResultVO delProBrief(Integer id);

    ResultVO getGroupPro(String typeId);

    ResultVO getAllPro();
}
