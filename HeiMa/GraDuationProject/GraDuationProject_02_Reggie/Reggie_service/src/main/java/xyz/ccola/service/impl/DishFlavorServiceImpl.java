package xyz.ccola.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xyz.ccola.domain.DishFlavor;
import xyz.ccola.mapper.DishFlavorMapper;
import xyz.ccola.service.DishFlavorService;

/**
 * @ Name: DishFlavorServiceImpl
 * @ Author: Cola
 * @ Time: 2022/12/27 19:43
 * @ Description: DishFlavorServiceImpl
 */
@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor> implements DishFlavorService {
}
