package xyz.ccola.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.ccola.domain.Setmeal;
import xyz.ccola.dto.SetmealDto;

import java.util.List;

/**
 * @ Name: SetmealService
 * @ Author: Cola
 * @ Time: 2022/12/27 19:49
 * @ Description: SetmealService
 */
public interface SetmealService extends IService<Setmeal> {
    /**
     * 新增套餐，同时需要保存套餐和菜品的关联关系
     * @param setmealDto
     */
    public void saveWithDish(SetmealDto setmealDto);

    /**
     * 删除套餐，同时需要删除套餐和菜品的关联数据
     * @param ids
     */
    public void removeWithDish(List<Long> ids);
}
