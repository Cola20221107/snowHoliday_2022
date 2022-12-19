package xyz.ccola.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.ccola.common.VodTemplate;
import xyz.ccola.domain.Movie;
import xyz.ccola.service.MovieService;

import java.util.List;

/**
 * @ Name: MovieFontController
 * @ Author: Cola
 * @ Time: 2022/12/19 17:56
 * @ Description: MovieFontController
 */
@RestController
@RequestMapping("/portal/movie")
@Slf4j
public class MovieFontController {
    @Autowired
    private MovieService movieService;
    @Autowired
    private VodTemplate vodTemplate;

    /**
     * 根据分类标识 cid 查询
     * @param cid 分类 id
     * @return Movie 集
     */
    @RequestMapping("/findByCid")
    public List<Movie> movieList(Integer cid) {
        log.info("成功访问到 /portal/category/findByCid");
        return movieService.findByCid(cid);
    }

    /**
     * 根据 id 查询 movie 并获取播放密钥
     * @param id id
     * @return Movie
     * @throws Exception 异常
     */
    @RequestMapping("/findById")
    public Movie findById(Integer id) throws Exception {
        log.info("成功访问到 /portal/category/findById");
        Movie movie = movieService.getById(id);

        /*
        获取播放密钥
         */
        if (movie != null && StringUtils.equals(movie.getType(), "2")) {//收费视频
            String playAuth = vodTemplate.getVideoPlayAuth(movie.getPlayId()).getPlayAuth();
            movie.setPlayAuth(playAuth);
        }

        return movie;
    }
}
