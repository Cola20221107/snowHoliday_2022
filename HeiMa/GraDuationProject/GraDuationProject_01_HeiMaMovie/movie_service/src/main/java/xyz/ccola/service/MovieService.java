package xyz.ccola.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;
import xyz.ccola.domain.Movie;
import xyz.ccola.domain.User;

import java.util.List;

/**
 * @ Name: MovieService
 * @ Author: Cola
 * @ Time: 2022/12/19 17:22
 * @ Description: MovieService
 */
@Service
public interface MovieService extends IService<Movie> {
    /**
     * 根据 id 批量删除
     * @param ids ids
     */
    void deleteByIds(Integer[] ids);

    /**
     * 根据类型 cid 查询
     * @param cid 分类 id
     * @return Movie 集
     */
    List<Movie> findByCid(Integer cid);
}
