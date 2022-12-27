package xyz.ccola.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xyz.ccola.domain.OrderDetail;
import xyz.ccola.mapper.OrderDetailMapper;
import xyz.ccola.service.OrderDetailService;

/**
 * @ Name: OrderDetailServiceImpl
 * @ Author: Cola
 * @ Time: 2022/12/27 19:46
 * @ Description: OrderDetailServiceImpl
 */
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {
}
