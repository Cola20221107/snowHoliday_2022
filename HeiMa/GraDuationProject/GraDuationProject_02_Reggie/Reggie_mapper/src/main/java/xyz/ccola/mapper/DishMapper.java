package xyz.ccola.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import xyz.ccola.domain.Dish;

/**
 * @ Name: DishMapper
 * @ Author: Cola
 * @ Time: 2022/12/27 19:40
 * @ Description: DishMapper
 */
@Mapper
public interface DishMapper extends BaseMapper<Dish> {
}
