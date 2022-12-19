package xyz.ccola.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.ccola.domain.Member;
import xyz.ccola.service.MemberService;

import java.util.List;

/**
 * @ Name: MemberController
 * @ Author: Cola
 * @ Time: 2022/12/19 17:49
 * @ Description: MemberController
 */
@RestController
@RequestMapping("/admin/member")
@Slf4j
public class MemberController {

    @Autowired
    MemberService memberService;

    /**
     * 查找全部
     * @return Member 集
     */
    @RequestMapping("/findAll")
    public List<Member> findAll(){
        log.info("成功访问到 /admin/member/findAll");
        return memberService.list(null);
    }

    /**
     * 根据 id 查询
     * @param id id
     * @return Member
     */
    @RequestMapping("/findById")
    public Member findById(@RequestParam Integer id){
        log.info("成功访问到 /admin/member/findById");
        return memberService.getById(id);
    }

    @RequestMapping("/save")
    public boolean save(@RequestBody Member member){
        log.info("成功访问到 /admin/member/save");
        if(member.getId() == null){
            log.info("正在进行添加操作");
            return memberService.save(member);
        }else {
            log.info("正在进行更新操作");
            return memberService.updateById(member);
        }
    }

    @RequestMapping("/deleteById")
    public boolean deleteById(@RequestParam Integer id){
        log.info("成功访问到 /admin/member/deleteById");
        return memberService.removeById(id);
    }

    @RequestMapping("/deleteByIds")
    public void deleteByIds(@RequestParam Integer[] ids){
        log.info("成功访问到 /admin/member/deleteByIds");
        memberService.deleteByIds(ids);
    }




}
