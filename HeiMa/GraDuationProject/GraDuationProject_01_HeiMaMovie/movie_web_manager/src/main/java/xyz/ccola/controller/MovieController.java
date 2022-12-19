package xyz.ccola.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.ccola.domain.Movie;
import xyz.ccola.service.MovieService;

import java.util.List;

/**
 * @ Name: MovieController
 * @ Author: Cola
 * @ Time: 2022/12/19 17:35
 * @ Description: MovieController
 */
@RestController
@RequestMapping("/admin/movie")
@Slf4j
public class MovieController {

    @Autowired
    private MovieService movieService;

    /**
     * 查询所有
     * @return Movie 集
     */
    @RequestMapping("/findAll")
    public List<Movie> findAll(){
        log.info("成功访问到 /admin/movie/findAll");
       return movieService.list(null);
    }

    /**
     * 保存 Movie
     * @param movie movie
     * @return count
     */
    @RequestMapping("/save")
    public Boolean insert(@RequestBody Movie movie){
        log.info("成功访问到 /admin/movie/save");
        if(movie.getId() == null){
            log.info("正在进行添加操作");
            return movieService.save(movie);

        }else {
            log.info("正在进行更新操作");
            return movieService.updateById(movie);
        }

    }

    /**
     * 根据 id 查询
     * @param id id
     * @return Movie
     */
    @RequestMapping("/findById")
    public Movie findById(Integer id){
        log.info("成功访问到 /admin/movie/findById");
        return movieService.getById(id);
    }


    /**
     * 根据 id 删除
     * @param id id
     * @return count
     */
    @RequestMapping("/deleteById")
    public Boolean deleteById(Integer id){
        log.info("成功访问到 /admin/movie/deleteById");
        return movieService.removeById(id);
    }

    /**
     * 根据 id 批量删除
     * @param ids ids
     */
    @RequestMapping("/deleteByIds")
    public void deleteByIds(Integer[] ids){
        log.info("成功访问到 /admin/movie/deleteByIds");
        movieService.deleteByIds(ids);
    }
}
