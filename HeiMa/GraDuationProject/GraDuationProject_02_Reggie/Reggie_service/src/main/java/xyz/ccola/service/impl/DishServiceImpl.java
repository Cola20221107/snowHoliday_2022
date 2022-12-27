package xyz.ccola.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xyz.ccola.domain.Dish;
import xyz.ccola.mapper.DishMapper;
import xyz.ccola.service.DishService;

/**
 * @ Name: DishServiceImpl
 * @ Author: Cola
 * @ Time: 2022/12/27 19:41
 * @ Description: DishServiceImpl
 */
@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {
}
