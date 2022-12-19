# 毕业设计之黑马 Movie

> Author: Cola  
> Time: 2022/12/19 23:36  
> To: 记录学习黑马程序员毕业设计之黑马 Movie

## 一、项目概述

### 1.1 项目介绍

> 本项目通过 `SpringBoot+Mybatusplus+ElmUI`实现简单的影视管理功能，可以实现影视内容的管理，且影视数据采用阿里云的 `OSS存储技术`播放器为 `阿里云VOD`

### 1.2 项目环境与技术说明

> yml 中的阿里云 accessKeyId 为付费资源需自行进行申请  TODO 如有疑问请留言 qq 1203952894 备注来意

> 项目环境：

| 工具                              | 版本号   |
| --------------------------------- | -------- |
| IntelliJ IDEA （UltimateEdition） | 2022.2.3 |
| Maven                             | 3.6.0    |
| Mysql                             | 5.7      |
| Tomcat                            | 9.X      |

> 技术说明：

| 技术        | 说明                                                         |
| ----------- | ------------------------------------------------------------ |
| SpringBoot  | Java 快速构建 Web 服务框架，[Spring Boot](https://spring.io/projects/spring-boot) |
| MyBatisPlus | MyBatis 的增强版本，「在 MyBatis 的基础上只做增强不做改变，为简化开发、提高效率而生。」[MyBatis-Plus (baomidou.com)](https://baomidou.com/) |
| ElmUI       | 开箱即用的前端页面 [Element - 网站快速成型工具](https://element.eleme.cn/#/zh-CN) |
| 阿里云 OSS  | OSS可用于图片、音视频、日志等海量文件的存储。各种终端设备、Web网站程序、移动应用可以直接向OSS写入或读取数据。OSS支持流式写入和文件写入两种方式。[对象存储OSS_云存储服务_企业数据管理_存储-阿里云 (aliyun.com)](https://www.aliyun.com/product/oss?spm=5176.21213303.5694434980.1.653853c9LIBJqv&scm=20140722.S_card@@产品@@218843.S_card0.ID_card@@产品@@218843-RL_OSS-OR_ser-V_2-P0_0) |
| TODO        | TODO 播放器                                                  |



### 1.3 前提准备

> 导入数据库相关：(此处仅作结构说明，可直接通过项目 sql目录下的  sql 脚本 导入)

```sql
create table tb_category
(
    id   int         not null comment '主键'
        primary key,
    name varchar(20) null comment '栏目名'
);

create table tb_member
(
    id       int auto_increment comment '主键'
        primary key,
    username varchar(30) null comment '用户名',
    password varchar(30) null comment '密码',
    phone    varchar(11) null comment '手机号'
);

create table tb_movie
(
    id          int auto_increment comment '主键'
        primary key,
    name        varchar(100)           null comment '电影名称',
    cid         int                    null comment '分类id',
    description varchar(512)           null comment '内容描述',
    keyword     varchar(50)            null comment '关键字，用来搜索',
    director    varchar(100)           null comment '导演姓名',
    actor       varchar(200)           null comment '主演',
    image       varchar(200)           null comment '缩略图',
    type        varchar(2) default '2' null comment '视频类型:1.免费视频  2.付费视频',
    url         varchar(256)           null comment '播放连接',
    play_id     varchar(100)           null comment '播放id,阿里云提供'
);

create table tb_user
(
    id       int         not null comment '主键'
        primary key,
    username varchar(30) null comment '账户',
    password varchar(32) null comment '密码'
);


```

![image-20221219235711674](./assets/image-20221219235711674.png)



## 二、具体实现

> 采用 Maven 聚合项目以 `GraDuationProject_01_HeiMaMovie`模块作为 ROOT 模块进行聚合管理。

![image-20221219235929693](./assets/image-20221219235929693.png)

> 目录说明：
>
> - `movie_common`用于存放常用工具类
> - `movie_domain` 数据持久层
> - `movie_mapper` mapper 接口
> - `movie_service` 业务层
> - `movie_web_manager` 管理员模块
> - `movie_web_portal` 用户模块

![image-20221220000229405](./assets/image-20221220000229405.png)

### 2.1 movie_common 模块

> 目录说明：
>
> - `JwtUtil` JsonWebToken 工具类用于授权和鉴权
> - `OSSConfig 和 VodConfig` 阿里云 OSS 和 Vod 技术配置类
> - `OSSTemplate 和 VodTemplate` 相关技术的接口类

![image-20221220000723435](./assets/image-20221220000723435.png)

> 具体实现

```java
package xyz.ccola.common;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Map;

/**
 * @ Name: common
 * @ Author: Cola
 * @ Time: 2022/12/19 17:43
 * @ Description: JwtUtil
 * JSON Web Token 用于管理员权限鉴定
 */
@Slf4j
public class JwtUtil {

    /**
     *  Token 生成
     * @param claims  载荷体 Payload
     * @return 根据 载荷体 以及盐值生成的 Token
     */
    public static String createToken(Map claims) {
        log.info("正在生成 Token {}",claims);
        return Jwts.builder()
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))//设置有效时间为1天
                .setClaims(claims) //设置响应数据体
                .signWith(SignatureAlgorithm.HS256, "ColaAndXiaoEr") //设置加密方法和加密盐
                .compact();
    }

    /**
     * Token 解析
     * @param token 需要鉴定的 Token
     * @return 以 Map 形式返回相应 Token 中的载荷体 Payload
     */
    public static Map parseToken(String token) {
        log.info("正在解析 Token {}",token);
        return Jwts.parser().setSigningKey("ColaAndXiaoEr")
                .parseClaimsJws(token)
                .getBody();
    }


}

```

```java
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

```

```java
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

```

```java
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


```

```java
package xyz.ccola.common;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;

/**
 * @ Name: VodTemplate
 * @ Author: Cola
 * @ Time: 2022/12/19 17:46
 * @ Description: VodTemplate
 */
@Component
@Slf4j
public class VodTemplate {
    @Autowired
    private VodConfig vodConfig;

    /**
     * 初始化 Vod 客户端对象
     * @return Vod 对象
     */
    public DefaultAcsClient initVodClient() {
        DefaultProfile profile = DefaultProfile.getProfile(
                vodConfig.getRegionId(),
                vodConfig.getAccessKeyId(),
                vodConfig.getAccessKeySecret());
        log.info("成功获取 Vod 客户端对象");

        return new DefaultAcsClient(profile);
    }

    /**
     * 上传视频
     * @param fileName 文件名
     * @param inputStream 输入流
     * @return 存储库中文件的 id
     */
    public String uploadVideo(String fileName, InputStream inputStream) {
        String title = fileName.substring(0, fileName.lastIndexOf("."));
        UploadStreamRequest request = new UploadStreamRequest(
                vodConfig.getAccessKeyId(),
                vodConfig.getAccessKeySecret(),
                title,
                fileName,
                inputStream);
        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadStreamResponse response = uploader.uploadStream(request);
        String videoId = response.getVideoId();
        log.info("成功上传视频并获取到视频 id {}",videoId);
        return videoId;
    }

    /**
     * 获取播放凭证 默认有效期为 2h 防止盗链
     * @param videoId 存储库中文件的 id
     * @return 播放凭证
     * @throws Exception 异常
     */
    public GetVideoPlayAuthResponse getVideoPlayAuth(String videoId) throws Exception {
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        request.setVideoId(videoId);
        log.info("成功获取到 {} 的播放凭证",videoId);
        return initVodClient().getAcsResponse(request);
    }
}

```

### 2.2 movie_domain 模块

> 目录说明：数据持久层

![image-20221220000910622](./assets/image-20221220000910622.png)

> 具体实现

```java
package xyz.ccola.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ Name: Category
 * @ Author: Cola
 * @ Time: 2022/12/19 17:16
 * @ Description: Category
 */
@TableName("tb_category")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category implements Serializable {
    /*
    主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /*
    栏目名称
     */
    private String name;
}

```

```java
package xyz.ccola.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ Name: Member
 * @ Author: Cola
 * @ Time: 2022/12/19 17:16
 * @ Description: Member
 */
@TableName("tb_member")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member implements Serializable {
    /*
    主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /*
    用户名
     */
    private String username;
    /*
    密码
     */
    private String password;
    /*
    手机号
     */
    private String phone;
}

```

```java
package xyz.ccola.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ Name: Movie
 * @ Author: Cola
 * @ Time: 2022/12/19 17:15
 * @ Description: Movie
 */
@TableName("tb_movie")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie implements Serializable {
    /*
       主键
        */
    @TableId(type = IdType.AUTO)
    private Integer id;//主键
    /*
    影视名
     */
    private String name;
    /*
    所属栏目
     */
    private Integer cid;
    /*
    描述信息
     */
    private String description;
    /*
    关键字
     */
    private String keyword;
    /*
    导演
     */
    private String director;
    /*
    演员
     */
    private String actor;
    /*
    封面图
     */
    private String image;
    /*
    视频类型
        1. 免费
        2. 付费
     */
    private String type;

    /*
    播放地址
     */
    private String url;

    /*
    播放 id
     */
    private String playId;
    /*
    播放秘钥
     */
    @TableField(exist = false)
    private String playAuth;
}

```

```java
package xyz.ccola.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ Name: User
 * @ Author: Cola
 * @ Time: 2022/12/19 17:15
 * @ Description: User
 */
@TableName("tb_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    /*
    主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /*
    用户名
     */
    private String username;
    /*
    密码
     */
    private String password;
}

```

### 2.1 movie_mapper 模块

> 目录说明：mapper 层

![image-20221220001057172](./assets/image-20221220001057172.png)

> 具体实现

```java
package xyz.ccola.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import xyz.ccola.domain.Category;

/**
 * @ Name: CategoryMapper
 * @ Author: Cola
 * @ Time: 2022/12/19 17:19
 * @ Description: CategoryMapper
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
}

```

```java
package xyz.ccola.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import xyz.ccola.domain.Member;

/**
 * @ Name: MemberMapper
 * @ Author: Cola
 * @ Time: 2022/12/19 17:19
 * @ Description: MemberMapper
 */
@Mapper
public interface MemberMapper extends BaseMapper<Member> {
}

```

```java
package xyz.ccola.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import xyz.ccola.domain.Movie;

/**
 * @ Name: MovieMapper
 * @ Author: Cola
 * @ Time: 2022/12/19 17:18
 * @ Description: MovieMapper
 */
@Mapper
public interface MovieMapper extends BaseMapper<Movie> {
}

```

```java
package xyz.ccola.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import xyz.ccola.domain.User;

/**
 * @ Name: UserMapper
 * @ Author: Cola
 * @ Time: 2022/12/19 17:18
 * @ Description: UserMapper
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}

```



### 2.1 movie_service 模块

> 目录说明：服务层

![image-20221220001203644](./assets/image-20221220001203644.png)

```java
package xyz.ccola.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;
import xyz.ccola.domain.Category;

/**
 * @ Name: CategoryService
 * @ Author: Cola
 * @ Time: 2022/12/19 17:22
 * @ Description: CategoryService
 */
@Service
public interface CategoryService extends IService<Category> {
}

```

```java
package xyz.ccola.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xyz.ccola.domain.Category;
import xyz.ccola.mapper.CategoryMapper;
import xyz.ccola.service.CategoryService;

/**
 * @ Name: CategoryServiceImpl
 * @ Author: Cola
 * @ Time: 2022/12/19 17:31
 * @ Description: CategoryServiceImpl
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

}

```

```java
package xyz.ccola.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;
import xyz.ccola.domain.Member;

/**
 * @ Name: MemberService
 * @ Author: Cola
 * @ Time: 2022/12/19 17:22
 * @ Description: MemberService
 */
@Service
public interface MemberService extends IService<Member> {
    /**
     * 根据 id 批量删除
     * @param ids ids
     * @return count
     */
    void deleteByIds(Integer[] ids);
}

```

```java
package xyz.ccola.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.ccola.domain.Member;
import xyz.ccola.mapper.MemberMapper;
import xyz.ccola.service.MemberService;

/**
 * @ Name: MemberServiceImpl
 * @ Author: Cola
 * @ Time: 2022/12/19 17:29
 * @ Description: MemberServiceImpl
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    @Autowired
    private MemberMapper memberMapper;

    @Override
    @Transactional
    public void deleteByIds(Integer[] ids) {
        for (Integer id : ids) {
            memberMapper.deleteById(id);
        }
    }
}

```

```java
package xyz.ccola.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;
import xyz.ccola.domain.Movie;
import xyz.ccola.domain.User;

import java.util.List;

/**
 * @ Name: MovieService
 * @ Author: Cola
 * @ Time: 2022/12/19 17:22
 * @ Description: MovieService
 */
@Service
public interface MovieService extends IService<Movie> {
    /**
     * 根据 id 批量删除
     * @param ids ids
     */
    void deleteByIds(Integer[] ids);

    /**
     * 根据类型 cid 查询
     * @param cid 分类 id
     * @return Movie 集
     */
    List<Movie> findByCid(Integer cid);
}

```
```java
package xyz.ccola.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.ccola.domain.Movie;
import xyz.ccola.mapper.MovieMapper;
import xyz.ccola.service.MovieService;

import java.util.List;

/**
 * @ Name: MovieServiceImpl
 * @ Author: Cola
 * @ Time: 2022/12/19 17:30
 * @ Description: MovieServiceImpl
 */
@Service
public class MovieServiceImpl extends ServiceImpl<MovieMapper, Movie> implements MovieService {
    @Autowired
    private MovieMapper movieMapper;

    @Override
    @Transactional
    public void deleteByIds(Integer[] ids) {
        for (Integer id : ids) {
            movieMapper.deleteById(id);
        }
    }

    @Override
    public List<Movie> findByCid(Integer cid) {
        QueryWrapper<Movie> wrapper = new QueryWrapper<>();
        wrapper.eq("cid", cid);
        return movieMapper.selectList(wrapper);
    }
}

```
```java
package xyz.ccola.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;
import xyz.ccola.domain.User;

/**
 * @ Name: UserService
 * @ Author: Cola
 * @ Time: 2022/12/19 17:21
 * @ Description: UserService
 */
@Service
public interface UserService extends IService<User> {
    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @return User
     */
    User login(String username, String password);
}

```

```java
package xyz.ccola.service.impl;

import com.aliyun.vod.upload.common.MD5Util;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.ccola.domain.User;
import xyz.ccola.mapper.UserMapper;
import xyz.ccola.service.UserService;

/**
 * @ Name: UserServiceImpl
 * @ Author: Cola
 * @ Time: 2022/12/19 17:27
 * @ Description: UserServiceImpl
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String username, String password) {

        /*
        将 password 加密后查询
         */
        String passwordWithMd5 = MD5Util.md5(password);
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<User>();
        wrapper.eq(User::getUsername,username);
        wrapper.eq(User::getPassword,passwordWithMd5);

        return userMapper.selectOne(wrapper);
    }
}

```

### 2.1 movie_web_manager 模块

> 目录说明：
>
> - `ManagerConfig` 拦截器用于拦截请求与静态资源的放行
> - `controller` 控制层
> - `LoginInterceptor` 拦截器用于请求的权限鉴定
> - `ManagerApplication` 启动类
> - `static` 静态资源目录
> - `application.yml` 配置文件

![image-20221220001339723](./assets/image-20221220001339723.png)

> 具体实现

> ManagerConfig 拦截器用于拦截请求与静态资源的放行

```java
package xyz.ccola.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import xyz.ccola.interceptor.LoginInterceptor;

import java.util.ArrayList;

/**
 * @ Name: ManagerConfig
 * @ Author: Cola
 * @ Time: 2022/12/19 17:49
 * @ Description: ManagerConfig
 */
@Configuration
public class ManagerConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;

    /**
     * 拦截器
     * @param registry 注册中心
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /*
        放行静态资源
         */
        ArrayList<String> urls = new ArrayList<>();
        urls.add("/**/*.js");
        urls.add("/**/*.css");
        urls.add("/**/*.jpg");
        urls.add("/**/*.png");
        urls.add("/**/*.html");
        /*
        放行必要请求
         */
        urls.add("/admin/user/login");
        urls.add("/admin/user/logout");

        /*
        注册
         */
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/admin/**")
                .excludePathPatterns(urls);
    }
}


```



> 控制层

```java
package xyz.ccola.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.ccola.domain.Member;
import xyz.ccola.service.MemberService;

import java.util.List;

/**
 * @ Name: MemberController
 * @ Author: Cola
 * @ Time: 2022/12/19 17:49
 * @ Description: MemberController
 */
@RestController
@RequestMapping("/admin/member")
@Slf4j
public class MemberController {

    @Autowired
    MemberService memberService;

    /**
     * 查找全部
     * @return Member 集
     */
    @RequestMapping("/findAll")
    public List<Member> findAll(){
        log.info("成功访问到 /admin/member/findAll");
        return memberService.list(null);
    }

    /**
     * 根据 id 查询
     * @param id id
     * @return Member
     */
    @RequestMapping("/findById")
    public Member findById(@RequestParam Integer id){
        log.info("成功访问到 /admin/member/findById");
        return memberService.getById(id);
    }

    @RequestMapping("/save")
    public boolean save(@RequestBody Member member){
        log.info("成功访问到 /admin/member/save");
        if(member.getId() == null){
            log.info("正在进行添加操作");
            return memberService.save(member);
        }else {
            log.info("正在进行更新操作");
            return memberService.updateById(member);
        }
    }

    @RequestMapping("/deleteById")
    public boolean deleteById(@RequestParam Integer id){
        log.info("成功访问到 /admin/member/deleteById");
        return memberService.removeById(id);
    }

    @RequestMapping("/deleteByIds")
    public void deleteByIds(@RequestParam Integer[] ids){
        log.info("成功访问到 /admin/member/deleteByIds");
        memberService.deleteByIds(ids);
    }




}

```

```java
package xyz.ccola.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.ccola.domain.Movie;
import xyz.ccola.service.MovieService;

import java.util.List;

/**
 * @ Name: MovieController
 * @ Author: Cola
 * @ Time: 2022/12/19 17:35
 * @ Description: MovieController
 */
@RestController
@RequestMapping("/admin/movie")
@Slf4j
public class MovieController {

    @Autowired
    private MovieService movieService;

    /**
     * 查询所有
     * @return Movie 集
     */
    @RequestMapping("/findAll")
    public List<Movie> findAll(){
        log.info("成功访问到 /admin/movie/findAll");
       return movieService.list(null);
    }

    /**
     * 保存 Movie
     * @param movie movie
     * @return count
     */
    @RequestMapping("/save")
    public Boolean insert(@RequestBody Movie movie){
        log.info("成功访问到 /admin/movie/save");
        if(movie.getId() == null){
            log.info("正在进行添加操作");
            return movieService.save(movie);

        }else {
            log.info("正在进行更新操作");
            return movieService.updateById(movie);
        }

    }

    /**
     * 根据 id 查询
     * @param id id
     * @return Movie
     */
    @RequestMapping("/findById")
    public Movie findById(Integer id){
        log.info("成功访问到 /admin/movie/findById");
        return movieService.getById(id);
    }


    /**
     * 根据 id 删除
     * @param id id
     * @return count
     */
    @RequestMapping("/deleteById")
    public Boolean deleteById(Integer id){
        log.info("成功访问到 /admin/movie/deleteById");
        return movieService.removeById(id);
    }

    /**
     * 根据 id 批量删除
     * @param ids ids
     */
    @RequestMapping("/deleteByIds")
    public void deleteByIds(Integer[] ids){
        log.info("成功访问到 /admin/movie/deleteByIds");
        movieService.deleteByIds(ids);
    }
}

```

```java
package xyz.ccola.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import xyz.ccola.common.OssTemplate;
import xyz.ccola.common.VodTemplate;

import java.io.IOException;

/**
 * @ Name: UploadController
 * @ Author: Cola
 * @ Time: 2022/12/19 17:51
 * @ Description: UploadController
 */
@RestController
@RequestMapping("/admin/upload")
@Slf4j
public class UploadController {
    @Autowired
    private OssTemplate ossTemplate;
    @Autowired
    private VodTemplate vodTemplate;

    /**
     * 图片上传
     * @param uploadImage Image
     * @return ImagePath
     * @throws IOException IOException
     */
    @RequestMapping("/image")
    public String uploadImage(MultipartFile uploadImage) throws IOException {
        log.info("成功访问到 /admin/upload/image");
        String imagePath = ossTemplate.upload(uploadImage.getOriginalFilename(), uploadImage.getInputStream());
        return imagePath;
    }


    /**
     * 视频上传
     * @param uploadVideo uploadVideo
     * @return playId
     * @throws IOException IOException
     */
    @RequestMapping("/video")
    public String uploadVideo(MultipartFile uploadVideo) throws IOException {
        log.info("成功访问到 /admin/upload/video");
        String playId = vodTemplate.uploadVideo(uploadVideo.getOriginalFilename(), uploadVideo.getInputStream());

        return playId;
    }


}

```

```java
package xyz.ccola.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.ccola.common.JwtUtil;
import xyz.ccola.domain.User;
import xyz.ccola.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ Name: UserController
 * @ Author: Cola
 * @ Time: 2022/12/19 17:38
 * @ Description: UserController
 */
@RestController
@RequestMapping("/admin/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @return Map
     */
    @RequestMapping("/login")
    public Map<String, String> login(String username, String password) {
        log.info("成功访问到 /admin/user/login");
        User user = userService.login(username, password);

        Map<String, String> map = new HashMap<>();
        if (user == null) {
            map.put("code", "0");
        } else {
            HashMap<String, Object> tokenMap = new HashMap<>();
            tokenMap.put("id", user.getId());
            String token = JwtUtil.createToken(tokenMap);

            map.put("code", "1");
            map.put("token", token);
        }

        return map;
    }

    @RequestMapping("/findAll")
    List<User> findAll(){
        log.info("成功访问到 /admin/user/findAll");
        return  userService.list(null);
    }


}

```



> interceptor 拦截

```java
package xyz.ccola.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import xyz.ccola.common.JwtUtil;
import xyz.ccola.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @ Name: LoginInterceptor
 * @ Author: Cola
 * @ Time: 2022/12/19 17:48
 * @ Description: LoginInterceptor
 */
@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private UserService userService;

    /**
     * 拦截请求后进行 Token 校验
     * @param request 请求
     * @param response 响应
     * @param handler handler
     * @return 鉴权结果
     * @throws Exception 异常
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("正在解析 Token");
        /*
        获取请求体中的 token 并解析
         */
        String token = request.getHeader("token");

        try {
            Map map = JwtUtil.parseToken(token);
            Object id = map.get("id");
            log.info("解析通过");
            return true;
        } catch (Exception e) {
           /*
           解析失败设置 401 状态码
            */
            response.setStatus(401);
            log.info("权限不足请检查 Token");
            return false;
        }
    }
}

```



> ManagerApplication 启动类

```java
package xyz.ccola;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ Name: ManagerApplication
 * @ Author: Cola
 * @ Time: 2022/12/19 17:04
 * @ Description: ManagerApplication
 */
@SpringBootApplication
public class ManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManagerApplication.class);
    }
}

```



> application.yml 配置文件

> ==注意此处需要为 mysql url 追加参数 useUnicode=true&characterEncoding=utf-8 否则插入中文数据乱码==

```yaml
# 配置服务端口
server:
  port: 8080
# 数据源配置
spring:
  datasource:
    driver-class-name: com.mysql.jdbc.Driver
    url: jdbc:mysql://43.143.184.147:3306/heima_movie?useUnicode=true&characterEncoding=utf-8
    username: root
    password: root
  servlet:
    multipart:
      enabled: true
      max-file-size: 2000MB
      max-request-size: 2000MB
# Mybatis 配置
mybatis-plus:
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl # 开启标准日志输出
    map-underscore-to-camel-case: true # 开启自动驼峰转换

# 阿里配置
al:
  oss: #存储
    endpoint: oss-cn-zhangjiakou.aliyuncs.com
    accessKeyId:
    accessKeySecret:
    bucketName: heima-movie-cola
    url: https://heima-movie-cola.oss-cn-zhangjiakou.aliyuncs.com
  vod: # 视频
    regionId: cn-shanghai
    accessKeyId:
    accessKeySecret:
```

### 2.1 movie_web_portal 模块

> 目录说明：

![image-20221220001843391](./assets/image-20221220001843391.png)

```java
package xyz.ccola.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.ccola.domain.Category;
import xyz.ccola.service.CategoryService;

import java.util.List;

/**
 * @ Name: CategoryController
 * @ Author: Cola
 * @ Time: 2022/12/19 17:55
 * @ Description: CategoryController
 */
@RestController
@RequestMapping("/portal/category")
@Slf4j
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 查询所有 Category
     * @return Category 集
     */
    @RequestMapping("/findAll")
    public List<Category> findAll(){
        log.info("成功访问到 /portal/category/findAll");
        return categoryService.list(null);
    }
}

```

```java
package xyz.ccola.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.ccola.common.VodTemplate;
import xyz.ccola.domain.Movie;
import xyz.ccola.service.MovieService;

import java.util.List;

/**
 * @ Name: MovieFontController
 * @ Author: Cola
 * @ Time: 2022/12/19 17:56
 * @ Description: MovieFontController
 */
@RestController
@RequestMapping("/portal/movie")
@Slf4j
public class MovieFontController {
    @Autowired
    private MovieService movieService;
    @Autowired
    private VodTemplate vodTemplate;

    /**
     * 根据分类标识 cid 查询
     * @param cid 分类 id
     * @return Movie 集
     */
    @RequestMapping("/findByCid")
    public List<Movie> movieList(Integer cid) {
        log.info("成功访问到 /portal/category/findByCid");
        return movieService.findByCid(cid);
    }

    /**
     * 根据 id 查询 movie 并获取播放密钥
     * @param id id
     * @return Movie
     * @throws Exception 异常
     */
    @RequestMapping("/findById")
    public Movie findById(Integer id) throws Exception {
        log.info("成功访问到 /portal/category/findById");
        Movie movie = movieService.getById(id);

        /*
        获取播放密钥
         */
        if (movie != null && StringUtils.equals(movie.getType(), "2")) {//收费视频
            String playAuth = vodTemplate.getVideoPlayAuth(movie.getPlayId()).getPlayAuth();
            movie.setPlayAuth(playAuth);
        }

        return movie;
    }
}

```

```java
package xyz.ccola;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ Name: PortalApplication
 * @ Author: Cola
 * @ Time: 2022/12/19 17:54
 * @ Description: PortalApplication
 */
@SpringBootApplication
public class PortalApplication {
    public static void main(String[] args) {
        SpringApplication.run(PortalApplication.class);
    }
}

```

```yaml
# 服务器端口
server:
  port: 8081

#数据源配置
spring:
  datasource:
    driver-class-name: com.mysql.jdbc.Driver
    url: jdbc:mysql://43.143.184.147:3306/heima_movie?useUnicode=true&characterEncoding=utf-8
    username: root
    password: root

# mybatis简单配置
mybatis-plus:
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl # 开启标准日志输出
    map-underscore-to-camel-case: true # 开启自动驼峰转换

# 阿里配置
al:
  vod: # 视频
    regionId: cn-shanghai
    accessKeyId:
    accessKeySecret:

```



## 三、测试

### 3.1 工具类采用 Junit 进行测试

> common 中JwtUtilTest类测试

```java
package xyz.ccola;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import xyz.ccola.common.JwtUtil;

import java.util.HashMap;
import java.util.Map;


/** 
* JwtUtil Tester. 
* 
* @author <Authors name> Cola
* @since <pre>12月 19, 2022</pre> 
* @version 1.0 
*/
@Slf4j
public class JwtUtilTest { 

@Before
public void before() throws Exception {
    log.info("开始测试 ");
} 

@After
public void after() throws Exception {
    log.info("测试结束 ");
} 

/** 
* 
* Method: createToken(Map claims) 
* 
*/ 
@Test
public void testCreateToken() throws Exception {
    Map map = new HashMap();
    map.put("code",1);
    map.put("name","Cola");
    String token = JwtUtil.createToken(map);
    System.out.println("生成的 Token 为：\n"+token);
    log.info("testCreateToken 测试通过");
} 

/** 
* 
* Method: parseToken(String token) 
* 上一步生成的 Token eyJhbGciOiJIUzI1NiJ9.eyJjb2RlIjoxLCJuYW1lIjoiQ29sYSJ9.r6KmDU7MNHuqJENdX0lKKy8v3MpOi3OwHMomueJgMOA
*/ 
@Test
public void testParseToken() throws Exception {
  try {
      Map map = JwtUtil.parseToken("eyJhbGciOiJIUzI1NiJ9.eyJjb2RlIjoxLCJuYW1lIjoiQ2" +
              "9sYSJ9.r6KmDU7MNHuqJENdX0lKKy8v3MpOi3OwHMomueJgMOA");
      map.forEach((key, value) -> {
          System.out.println(key + ":"+value);
      });
      log.info("testParseToken 方法测试通过");
  }catch (Exception e){
      log.error("Token 解析失败 ",e);
  }

}


} 

```

![image-20221220002823035](./assets/image-20221220002823035.png)

> common 中OssTemplateTest类测试

```java
package xyz.ccola;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import xyz.ccola.common.OssTemplate;

/** 
* OssTemplate Tester. 
* 
* @author Cola
* @since <pre>12月 19, 2022</pre> 
* @version 1.0 
*/
@Slf4j
public class OssTemplateTest { 

@Before
public void before() throws Exception {
    log.info("开始测试 ");
} 

@After
public void after() throws Exception {
    log.info("测试结束 ");
} 

/** 
* 
* Method: upload(String fileName, InputStream inputStream) 
* 
*/ 
@Test
public void testUpload() throws Exception { 
//TODO: URL testUpload
} 

/** 
* 
* Method: getContentType(String FilenameExtension) 
* 
*/ 
@Test
public void testGetContentType() throws Exception {
    OssTemplate ossTemplate = new OssTemplate();
    String[] images = new  String[]{
            ".bmp",
            ".gif",
            ".jpeg",".jpg",".png",
            ".aaa"
    };
    for (String image : images) {
        System.out.println(ossTemplate.getContentType(image));
    }
    log.info("testGetContentType 测试通过");
} 


} 

```



![image-20221220002926940](./assets/image-20221220002926940.png)

### 3.2 控制类采用 Tomcat + PostMan 进行联调

#### 3.2.1 管理员模块

> MemberController 接口测试

![image-20221220004220877](./assets/image-20221220004220877.png)



![image-20221220004349632](./assets/image-20221220004349632.png)



> MovieController 接口测试

![image-20221220004446607](./assets/image-20221220004446607.png)

![image-20221220004452842](./assets/image-20221220004452842.png)

> UploadController 接口测试

![image-20221220004540114](./assets/image-20221220004540114.png)

![image-20221220004628199](./assets/image-20221220004628199.png)

![image-20221220004640716](./assets/image-20221220004640716.png)

> UserController 接口测试

![image-20221220004731440](./assets/image-20221220004731440.png)

![image-20221220004801555](./assets/image-20221220004801555.png)



#### 3.2.2 用户模块

> CategoryController 接口测试

![image-20221220004911319](./assets/image-20221220004911319.png)

![image-20221220004958792](./assets/image-20221220004958792.png)

> MovieFontController 接口测试

![image-20221220005122715](./assets/image-20221220005122715.png)



![image-20221220005044090](./assets/image-20221220005044090.png)

### 3.3 效果测试

![image-20221220005236623](./assets/image-20221220005236623.png)

![image-20221220005228711](./assets/image-20221220005228711.png)

![image-20221220005314004](./assets/image-20221220005314004.png)

![image-20221220005324489](./assets/image-20221220005324489.png)

## 四、收获

### 4.1 EmlUI 中 upload 组件请求头的设置

> 问题描述，使用 JWT 进行权限鉴定过程中，发现原有的 `token.js`只能为 `axios` 请求追加请求头，导致上传操作一直 `401` 鉴权失败

```js
//请求拦截
axios.interceptors.request.use(
    config => {
        console.log(localStorage.getItem("token"));
        config.headers.token = localStorage.getItem("token"); // 在请求头中携带token
        console.log(config);
        return config;
    },
    error => {
        return Promise.reject(error);
    }
);
//响应拦截
axios.interceptors.response.use(
    response => {
        if (response) {
            return response
        }
    },
    error => {
        //当响应码为401时,给出页面提示,并且清空token,刷新页面
        if (error.response) {
            if (error.response.status == 401) {
                localStorage.removeItem("token");
                window.top.location.href = "./login.html";
            }
        }
        return Promise.reject(error.response.data)
    }
);


```

> 前端通过 `axios` 发送请求可以通过 `token.js` 拦截并设置请求头，但 `eml`的 `upload` 控件需要在 `data` 中手动设置请求头

![image-20221220002449164](./assets/image-20221220002449164.png)

![image-20221220002459540](./assets/image-20221220002459540.png)

### 4.2 使用 `Sl4j` 结合配置文件实现彩色日志的输出

> 引入依赖

```xml
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>jul-to-slf4j</artifactId>
        </dependency>
```

> 在类路径下加载 `logback.xml`文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration scan="true" scanPeriod="60 seconds" debug="false">
    <property name="APP_NAME" value="MY_APP_NAME" />
    <property name="LOG_DIR" value="logs" />
    <property name="FILE_LOG_PATTERN" value="%d{yyyy-MM-dd HH:mm:ss.SSS}  %-5level [%thread] %logger{15} - %msg%n" />
    <!-- 彩色日志格式 -->
    <property name="CONSOLE_LOG_PATTERN" value="%d{yyyy-MM-dd HH:mm:ss.SSS} %highlight(%-5level) %boldYellow([%thread])  %cyan(%logger{15}) %msg%n"/>

    <contextName>${APP_NAME}</contextName>

    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>${CONSOLE_LOG_PATTERN}</pattern>
        </encoder>
    </appender>

    <appender name="FILE" class="ch.qos.logback.core.FileAppender">
        <file>${LOG_DIR}/logFile.log</file>
        <append>true</append>
        <encoder>
            <pattern>${FILE_LOG_PATTERN}</pattern>
        </encoder>
    </appender>

    <appender name="RollingFile" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <fileNamePattern>${LOG_DIR}/dayLogFile.%d{yyyy-MM-dd}.log</fileNamePattern>
            <maxHistory>30</maxHistory>
        </rollingPolicy>
        <encoder>
            <pattern>${FILE_LOG_PATTERN}</pattern>
        </encoder>
    </appender>

    <!-- 使用root的appender-ref -->
    <logger name="com.example.Logger1" level="DEBUG" additivity="true">
    </logger>

    <!-- 不使用root的appender-ref -->
    <logger name="com.example.Logger2" level="DEBUG" additivity="false">
    </logger>

    <logger name="com.example.Logger3" level="DEBUG" additivity="false">
        <appender-ref ref="STDOUT"/>
    </logger>

    <root level="DEBUG">
        <appender-ref ref="STDOUT" />
        <appender-ref ref="FILE" />
        <appender-ref ref="RollingFile" />
    </root>
</configuration>
```

> 输出技巧：

> 对于 INFO 级别带参数日志的输出一般采用占位符 `{}`的形式，节省服务器资源，提高性能

```java
log.info("参数为 {}",exa)
```

> 对于 DEBUG 级别的日志输出一般先对其进行判断，看其是否处于 DEBUG 模式

```java
        if(log.isDebugEnabled()){
            log.debug("DEBUG");
        }
```

> 对于异常的处理一般采用 info 进行处理，避免使用 `e.printStackTrace()` 打印具体堆栈信息，防止溢出

```java
catch (Exception e){
      log.error("Token 解析失败 ",e);
  }
```



