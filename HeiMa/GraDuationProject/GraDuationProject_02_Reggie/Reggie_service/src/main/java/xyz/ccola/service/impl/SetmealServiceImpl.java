package xyz.ccola.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xyz.ccola.domain.Setmeal;
import xyz.ccola.mapper.SetmealMapper;
import xyz.ccola.service.SetmealService;

/**
 * @ Name: SetmealServiceImpl
 * @ Author: Cola
 * @ Time: 2022/12/27 19:49
 * @ Description: SetmealServiceImpl
 */
@Service
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {
}
