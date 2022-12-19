package xyz.ccola.controller;

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
public class MovieController {

    @Autowired
    private MovieService movieService;

    /**
     * 查询所有
     * @return Movie 集
     */
    @RequestMapping("/findAll")
    public List<Movie> findAll(){
       return movieService.list(null);
    }

    /**
     * 保存 Movie
     * @param movie movie
     * @return count
     */
    @RequestMapping("/save")
    public Boolean insert(@RequestBody Movie movie){

        if(movie.getId() == null){
            return movieService.save(movie);

        }else {
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
        return movieService.getById(id);
    }


    /**
     * 根据 id 删除
     * @param id id
     * @return count
     */
    @RequestMapping("/deleteById")
    public Boolean deleteById(Integer id){
        return movieService.removeById(id);
    }

    /**
     * 根据 id 批量删除
     * @param ids ids
     */
    @RequestMapping("/deleteByIds")
    public void deleteByIds(Integer[] ids){
        movieService.deleteByIds(ids);
    }
}
