package xyz.ccola.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.ccola.service.OrderDetailService;

/**
 * @ Name: OrderDetailController
 * @ Author: Cola
 * @ Time: 2022/12/27 23:58
 * @ Description: OrderDetailController 订单明细
 */
@Slf4j
@RestController
@RequestMapping("/orderDetail")
public class OrderDetailController {
    @Autowired
    private OrderDetailService orderDetailService;
}
