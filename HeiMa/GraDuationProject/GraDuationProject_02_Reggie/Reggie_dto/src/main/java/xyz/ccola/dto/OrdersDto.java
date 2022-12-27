package xyz.ccola.dto;

import lombok.Data;
import xyz.ccola.domain.OrderDetail;
import xyz.ccola.domain.Orders;

import java.util.List;

/**
 * @ Name: OrdersDto
 * @ Author: Cola
 * @ Time: 2022/12/27 17:45
 * @ Description: OrdersDto
 */
@Data
public class OrdersDto extends Orders {
    private String userName;

    private String phone;

    private String address;

    private String consignee;

    private List<OrderDetail> orderDetails;
}
