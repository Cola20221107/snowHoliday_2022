package xyz.ccola.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import xyz.ccola.domain.Employee;
import xyz.ccola.domain.R;
import xyz.ccola.service.EmployeeService;

import javax.servlet.http.HttpServletRequest;

/**
 * @ Name: EmployeeController
 * @ Author: Cola
 * @ Time: 2022/12/27 18:13
 * @ Description: EmployeeController
 */
@RestController
@RequestMapping("/employee")
@Slf4j
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;


    /**
     * 员工登录
     * @param request request
     * @param employee employee
     * @return R
     */
    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee){

        /*
        将页面提交的密码password进行md5加密处理
         */
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        /*
        根据页面提交的用户名username查询数据库
         */
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername,employee.getUsername());
        Employee emp = employeeService.getOne(queryWrapper);

        /*
        如果没有查询到则返回登录失败结果
         */
        if(emp == null){
            return R.error("登录失败");
        }

        /*
        密码比对，如果不一致则返回登录失败结果
         */
        if(!emp.getPassword().equals(password)){
            return R.error("登录失败");
        }

        /*
        查看员工状态，如果为已禁用状态，则返回员工已禁用结果
         */
        if(emp.getStatus() == 0){
            return R.error("账号已禁用");
        }

        /*
        登录成功，将员工id存入Session并返回登录成功结果
         */
        request.getSession().setAttribute("employee",emp.getId());

        return R.success(emp);
    }

    /**
     * 员工退出
     * @param request request
     * @return R
     */
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){
        /*
        清理Session中保存的当前登录员工的id
         */
        request.getSession().removeAttribute("employee");
        return R.success("退出成功");
    }

    /**
     * 新增员工
     * @param employee employee
     * @return R
     */
    @PostMapping
    public R<String> save(HttpServletRequest request,@RequestBody Employee employee){
        log.info("新增员工，员工信息：{}",employee.toString());

        /*
        设置初始密码123456，需要进行md5加密处理
         */
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));


        employeeService.save(employee);

        return R.success("新增员工成功");
    }

    /**
     * 员工信息分页查询
     * @param page page
     * @param pageSize pageSize
     * @param name name
     * @return R
     */
    @GetMapping("/page")
    public R<Page> page(int page,int pageSize,String name){
        log.info("page = {},pageSize = {},name = {}" ,page,pageSize,name);

        /*
        构造分页构造器
         */
        Page pageInfo = new Page(page,pageSize);

        /*
        构造条件构造器
         */
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper();
        /*
        添加过滤条件并执行查询
         */
        queryWrapper.like(StringUtils.isNotEmpty(name),Employee::getName,name);
        queryWrapper.orderByDesc(Employee::getUpdateTime);
        employeeService.page(pageInfo,queryWrapper);

        return R.success(pageInfo);
    }

    /**
     * 根据id修改员工信息
     * @param employee employee
     * @return R
     */
    @PutMapping
    public R<String> update(HttpServletRequest request,@RequestBody Employee employee){

        employeeService.updateById(employee);

        return R.success("员工信息修改成功");
    }

    /**
     * 根据id查询员工信息
     * @param id id
     * @return R
     */
    @GetMapping("/{id}")
    public R<Employee> getById(@PathVariable Long id){
        log.info("根据id查询员工信息...");
        Employee employee = employeeService.getById(id);
        if(employee != null){
            return R.success(employee);
        }
        return R.error("没有查询到对应员工信息");
    }
}
