package xyz.ccola.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.ccola.domain.Category;
import xyz.ccola.service.CategoryService;

import java.util.List;

/**
 * @ Name: CategoryController
 * @ Author: Cola
 * @ Time: 2022/12/19 17:51
 * @ Description: CategoryController
 */
@RestController
@RequestMapping("/portal/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 查询所有分类
     * @return Category 集
     */
    @RequestMapping("/findAll")
    public List<Category> findAll(){
        return categoryService.list(null);
    }
}
