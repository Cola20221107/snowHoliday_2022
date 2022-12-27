package xyz.ccola.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import xyz.ccola.domain.ShoppingCart;

/**
 * @ Name: ShoppingCartMapper
 * @ Author: Cola
 * @ Time: 2022/12/27 19:51
 * @ Description: ShoppingCartMapper
 */
@Mapper
public interface ShoppingCartMapper extends BaseMapper<ShoppingCart> {
}
