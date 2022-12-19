package xyz.ccola.common;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @ Name: OSSConfig
 * @ Author: Cola
 * @ Time: 2022/12/19 17:44
 * @ Description: OSSConfig 阿里云 Oss 服务配置类
 */
@Configuration
@ConfigurationProperties("al.oss")
@Data
public class OSSConfig {
    /*
    服务对应的区域
     */
    private String endpoint;
    /*
    用户凭证
     */
    private String accessKeyId;
    private String accessKeySecret;
    /*
    存储库名称
     */
    private String bucketName;
    /*
    url 对应资源 前缀
     */
    private String url;
}
