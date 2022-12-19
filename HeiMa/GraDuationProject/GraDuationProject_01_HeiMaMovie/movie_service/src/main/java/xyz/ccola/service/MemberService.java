package xyz.ccola.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;
import xyz.ccola.domain.Member;

/**
 * @ Name: MemberService
 * @ Author: Cola
 * @ Time: 2022/12/19 17:22
 * @ Description: MemberService
 */
@Service
public interface MemberService extends IService<Member> {
    /**
     * 根据 id 批量删除
     * @param ids ids
     * @return count
     */
    void deleteByIds(Integer[] ids);
}
