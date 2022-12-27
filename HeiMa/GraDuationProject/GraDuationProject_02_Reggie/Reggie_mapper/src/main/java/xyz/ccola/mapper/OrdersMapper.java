package xyz.ccola.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import xyz.ccola.domain.Orders;

/**
 * @ Name: OrdersMapper
 * @ Author: Cola
 * @ Time: 2022/12/27 19:46
 * @ Description: OrdersMapper
 */
@Mapper
public interface OrdersMapper extends BaseMapper<Orders> {
}
