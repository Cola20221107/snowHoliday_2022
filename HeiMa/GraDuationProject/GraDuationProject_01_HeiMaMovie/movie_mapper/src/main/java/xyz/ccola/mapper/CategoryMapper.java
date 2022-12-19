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
