package xyz.ccola.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.ccola.domain.Category;
import xyz.ccola.service.CategoryService;

import java.util.List;

/**
 * @ Name: CategoryController
 * @ Author: Cola
 * @ Time: 2022/12/19 17:55
 * @ Description: CategoryController
 */
@RestController
@RequestMapping("/portal/category")
@Slf4j
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 查询所有 Category
     * @return Category 集
     */
    @RequestMapping("/findAll")
    public List<Category> findAll(){
        log.info("成功访问到 /portal/category/findAll");
        return categoryService.list(null);
    }
}
