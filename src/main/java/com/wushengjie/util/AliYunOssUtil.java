package com.wushengjie.util;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URL;
import java.util.Date;
import java.util.Random;

/**
 * Created by WU on 2017/7/9.
 * 阿里云OSS工具类
 */
@Component
public class AliYunOssUtil {

    @Autowired
    private PropertyUtil propertyUtil;

    public OSSClient getOssClientInstance(){
         return new OSSClient(propertyUtil.getEndPoint(), propertyUtil.getAccesskeyId(), propertyUtil.getAccesskeySecret());
    }

    /**
     * 销毁
     */
    public void destory(OSSClient ossClient) {
        ossClient.shutdown();
    }


    /**
     * 上传图片并返回url
     */
    public String updateLoadImg(MultipartFile file) throws IOException{
        if (file == null || file.getSize() <= 0) {
            throw new RuntimeException("图片不能为空");
        }
        String name = uploadImg2Oss(file);
        String imgUrl = getImgUrl(name);
        return imgUrl;
    }

    /**
     * 上传图片
     *
     * @param url
     */
    private void uploadImg2Oss(String url) throws IOException {
        File fileOnServer = new File(url);
        FileInputStream fin;
        fin = new FileInputStream(fileOnServer);
        String[] split = url.split("/");
        this.uploadFile2OSS(fin, split[split.length - 1]);
    }


    /**
     * 上传图片
     *
     * @param file
     */
    private String uploadImg2Oss(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String substring = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
        Random random = new Random();
        String name = random.nextInt(10000) + System.currentTimeMillis() + substring;
        InputStream inputStream = file.getInputStream();
        this.uploadFile2OSS(inputStream, name);
        return name;
    }

    /**
     * 获得图片路径
     *
     * @param fileUrl
     * @return
     */
    public String getImgUrl(String fileUrl) {
        if (!StringUtils.isEmpty(fileUrl)) {
            String[] split = fileUrl.split("/");
            return this.getUnSignUrl(split[split.length - 1]);
        }
        return null;
    }

    /**
     * 上传到OSS服务器  如果同名文件会覆盖服务器上的
     *
     * @param instream 文件流
     * @param fileName 文件名称 包括后缀名
     * @return 出错返回"" ,唯一MD5数字签名
     */
    private String uploadFile2OSS(InputStream instream, String fileName) throws IOException {
        OSSClient ossClient = getOssClientInstance();
        String ret = "";
        try {
            //创建上传Object的Metadata
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(instream.available());
            objectMetadata.setCacheControl("no-cache");
            objectMetadata.setHeader("Pragma", "no-cache");
            objectMetadata.setContentType(getcontentType(fileName.substring(fileName.lastIndexOf("."))));
            objectMetadata.setContentDisposition("inline;filename=" + fileName);
            //上传文件
            PutObjectResult putResult = ossClient.putObject(propertyUtil.getBucketName(), propertyUtil.getDir() + fileName, instream, objectMetadata);
            ret = putResult.getETag();
         } finally {
            if (instream != null) {
                instream.close();
                destory(ossClient);
            }
        }
        return ret;
    }

    /**
     * Description: 判断OSS服务文件上传时文件的contentType
     *
     * @param FilenameExtension 文件后缀
     * @return String
     */
    private static String getcontentType(String FilenameExtension) {
        if (FilenameExtension.equalsIgnoreCase("bmp")) {
            return "image/bmp";
        }
        if (FilenameExtension.equalsIgnoreCase("gif")) {
            return "image/gif";
        }
        if (FilenameExtension.equalsIgnoreCase("jpeg") ||
                FilenameExtension.equalsIgnoreCase("jpg") ||
                FilenameExtension.equalsIgnoreCase("png")) {
            return "image/jpeg";
        }
        if (FilenameExtension.equalsIgnoreCase("html")) {
            return "text/html";
        }
        if (FilenameExtension.equalsIgnoreCase("txt")) {
            return "text/plain";
        }
        if (FilenameExtension.equalsIgnoreCase("vsd")) {
            return "application/vnd.visio";
        }
        if (FilenameExtension.equalsIgnoreCase("pptx") ||
                FilenameExtension.equalsIgnoreCase("ppt")) {
            return "application/vnd.ms-powerpoint";
        }
        if (FilenameExtension.equalsIgnoreCase("docx") ||
                FilenameExtension.equalsIgnoreCase("doc")) {
            return "application/msword";
        }
        if (FilenameExtension.equalsIgnoreCase("xml")) {
            return "text/xml";
        }
        return "image/jpeg";
    }


    /**
     * 获得未签名url链接
     *
     * @param key
     * @return
     */
    public String getUnSignUrl(String key) {
        return "http://"+ propertyUtil.getBucketName() + "." + propertyUtil.getEndPoint() + "/" + propertyUtil.getDir() + key;
    }

    /**
     * 获得签名后url链接
     *
     * @param key
     * @return
     */
    public String getSignUrl(String key) {
        OSSClient ossClient = getOssClientInstance();
        // 设置URL过期时间为10年  3600l* 1000*24*365*10
        Date expiration = new Date(new Date().getTime() + 3600l * 1000 * 24 * 365 * 10);
        // 生成URL
        URL url = ossClient.generatePresignedUrl(propertyUtil.getBucketName(), key, expiration);
        destory(ossClient);
        if (url != null) {
            return url.toString();
        }
        return null;
    }
}