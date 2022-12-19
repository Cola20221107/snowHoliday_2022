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
