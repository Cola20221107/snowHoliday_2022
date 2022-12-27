package xyz.ccola.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xyz.ccola.domain.ShoppingCart;
import xyz.ccola.mapper.ShoppingCartMapper;
import xyz.ccola.service.ShoppingCartService;

/**
 * @ Name: ShoppingCartServiceImpl
 * @ Author: Cola
 * @ Time: 2022/12/27 19:52
 * @ Description: ShoppingCartServiceImpl
 */
@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {
}
