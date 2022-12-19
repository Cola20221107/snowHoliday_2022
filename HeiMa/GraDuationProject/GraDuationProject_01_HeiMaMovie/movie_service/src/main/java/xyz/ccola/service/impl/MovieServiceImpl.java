package xyz.ccola.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.ccola.domain.Movie;
import xyz.ccola.mapper.MovieMapper;
import xyz.ccola.service.MovieService;

import java.util.List;

/**
 * @ Name: MovieServiceImpl
 * @ Author: Cola
 * @ Time: 2022/12/19 17:30
 * @ Description: MovieServiceImpl
 */
@Service
public class MovieServiceImpl extends ServiceImpl<MovieMapper, Movie> implements MovieService {
    @Autowired
    private MovieMapper movieMapper;

    @Override
    @Transactional
    public void deleteByIds(Integer[] ids) {
        for (Integer id : ids) {
            movieMapper.deleteById(id);
        }
    }

    @Override
    public List<Movie> findByCid(Integer cid) {
        QueryWrapper<Movie> wrapper = new QueryWrapper<>();
        wrapper.eq("cid", cid);
        return movieMapper.selectList(wrapper);
    }
}
