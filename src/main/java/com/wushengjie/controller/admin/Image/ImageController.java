package com.wushengjie.controller.admin.Image;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.GetObjectRequest;
import com.wushengjie.util.AliYunOssUtil;
import com.wushengjie.vo.MdImgResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Created by WU on 2017/7/9.
 */
@Controller
public class ImageController {

    private static final Logger logger = LoggerFactory.getLogger(ImageController.class);

    @Autowired
    private AliYunOssUtil ossUtil;

    @RequestMapping("/imageUpload")
    @ResponseBody
    public MdImgResult imageUpload(@RequestParam(value = "editormd-image-file",required = true) MultipartFile file){
        MdImgResult result = new MdImgResult();
        try {
            String url = ossUtil.updateLoadImg(file);
            result.setSuccess(1);
            result.setUrl(url);
            result.setMessage("上传成功!");
            return result;
        } catch (IOException e) {
            logger.error(e.getMessage());
            return new MdImgResult(0,"","IO异常上传失败！！！");
        }
    }
}
