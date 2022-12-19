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
