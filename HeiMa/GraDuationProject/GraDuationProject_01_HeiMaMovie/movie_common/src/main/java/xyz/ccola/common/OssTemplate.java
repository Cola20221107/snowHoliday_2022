package xyz.ccola.common;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.ObjectMetadata;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ Name: OssTemplate
 * @ Author: Cola
 * @ Time: 2022/12/19 17:45
 * @ Description: OssTemplate
 */
@Component
@Slf4j
public class OssTemplate {
    @Autowired
    private OSSConfig ossConfig;

    /**
     * 文件上传
     * @param fileName 文件名称
     * @param inputStream 输入流
     * @return 图片 Url 路径
     */
    public String upload(String fileName, InputStream inputStream) {
        /*
        创建 OSS 连接对象
         */
        OSS ossClient = new OSSClientBuilder().build(
                ossConfig.getEndpoint(),
                ossConfig.getAccessKeyId(),
                ossConfig.getAccessKeySecret());
        log.info("成功获取 OSS 连接对象 {}",ossClient);

        /*
        设置包含文件后缀在内的完整路径 objectName
        images/2020/11/11/*.jpg
         */
        String objectName = "images/" + new SimpleDateFormat("yyyy/MM/dd").format(new Date())
                + "/" + System.currentTimeMillis() + fileName.substring(fileName.lastIndexOf("."));
        /*
        设置访问资源的请求头 meta 解决访问资源路径直接下载的问题
         */
        ObjectMetadata meta = new ObjectMetadata();
        meta.setContentType(getContentType(fileName.substring(fileName.lastIndexOf("."))));
        ossClient.putObject(ossConfig.getBucketName(), objectName, inputStream, meta);
        log.info("生成文件访问路径{}并重写访问资源请求头",objectName);
        /*
        关闭 OSS 连接对象
         */
        ossClient.shutdown();
        log.info("成功关闭 OSS 连接对象");
        return ossConfig.getUrl() + "/" + objectName;
    }

    /**
     * 获取文件类型后缀
     * @param FilenameExtension 文件后缀
     * @return 相应的后缀
     */
    public String getContentType(String FilenameExtension) {
        if (FilenameExtension.equalsIgnoreCase(".bmp")) {
            return "image/bmp";
        }
        if (FilenameExtension.equalsIgnoreCase(".gif")) {
            return "image/gif";
        }
        if (FilenameExtension.equalsIgnoreCase(".jpeg") ||
                FilenameExtension.equalsIgnoreCase(".jpg") ||
                FilenameExtension.equalsIgnoreCase(".png")) {
            return "image/jpg";
        }
        return "image/jpg";
    }
}
