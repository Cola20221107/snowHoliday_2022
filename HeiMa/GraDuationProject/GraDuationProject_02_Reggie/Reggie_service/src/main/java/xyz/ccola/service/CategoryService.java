package xyz.ccola.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.ccola.domain.Category;

/**
 * @ Name: CategoryService
 * @ Author: Cola
 * @ Time: 2022/12/27 19:33
 * @ Description: CategoryService
 */
public interface CategoryService extends IService<Category> {
    public void remove(Long id);
}
