package xyz.ccola.common;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @ Name: VodConfig
 * @ Author: Cola
 * @ Time: 2022/12/19 17:46
 * @ Description: VodConfig OSSConfig 阿里云 Vod 服务配置类
 */
@Configuration
@ConfigurationProperties("al.vod")
@Data
public class VodConfig {
    /*
    区域
     */
    private String regionId;
    /*
    用户凭证
     */
    private String accessKeyId;
    private String accessKeySecret;
}

