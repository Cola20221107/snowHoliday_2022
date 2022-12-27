package xyz.ccola.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import xyz.ccola.domain.OrderDetail;

/**
 * @ Name: OrderDetailMapper
 * @ Author: Cola
 * @ Time: 2022/12/27 19:45
 * @ Description: OrderDetailMapper
 */
@Mapper
public interface OrderDetailMapper extends BaseMapper<OrderDetail> {
}
