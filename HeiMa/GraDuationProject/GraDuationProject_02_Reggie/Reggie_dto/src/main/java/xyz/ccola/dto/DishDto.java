package xyz.ccola.dto;

import lombok.Data;
import xyz.ccola.domain.Dish;
import xyz.ccola.domain.DishFlavor;

import java.util.ArrayList;
import java.util.List;

/**
 * @ Name: DishDto
 * @ Author: Cola
 * @ Time: 2022/12/27 17:45
 * @ Description: DishDto
 */
@Data
public class DishDto extends Dish {
    private List<DishFlavor> flavors = new ArrayList<>();

    private String categoryName;

    private Integer copies;
}
