package xyz.ccola.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xyz.ccola.domain.Category;
import xyz.ccola.mapper.CategoryMapper;
import xyz.ccola.service.CategoryService;

/**
 * @ Name: CategoryServiceImpl
 * @ Author: Cola
 * @ Time: 2022/12/27 19:34
 * @ Description: CategoryServiceImpl
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
}
