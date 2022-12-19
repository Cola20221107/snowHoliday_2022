package xyz.ccola.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.ccola.domain.Member;
import xyz.ccola.mapper.MemberMapper;
import xyz.ccola.service.MemberService;

/**
 * @ Name: MemberServiceImpl
 * @ Author: Cola
 * @ Time: 2022/12/19 17:29
 * @ Description: MemberServiceImpl
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    @Autowired
    private MemberMapper memberMapper;

    @Override
    @Transactional
    public void deleteByIds(Integer[] ids) {
        for (Integer id : ids) {
            memberMapper.deleteById(id);
        }
    }
}
