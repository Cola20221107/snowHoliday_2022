package xyz.ccola.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xyz.ccola.domain.Orders;
import xyz.ccola.mapper.OrdersMapper;
import xyz.ccola.service.OrdersService;

/**
 * @ Name: OrdersServiceImpl
 * @ Author: Cola
 * @ Time: 2022/12/27 19:47
 * @ Description: OrdersServiceImpl
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {
}
