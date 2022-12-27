package xyz.ccola.dto;

import lombok.Data;
import xyz.ccola.domain.Setmeal;
import xyz.ccola.domain.SetmealDish;

import java.util.List;

/**
 * @ Name: SetmealDto
 * @ Author: Cola
 * @ Time: 2022/12/27 17:44
 * @ Description: SetmealDto
 */
@Data
public class SetmealDto extends Setmeal {
    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
