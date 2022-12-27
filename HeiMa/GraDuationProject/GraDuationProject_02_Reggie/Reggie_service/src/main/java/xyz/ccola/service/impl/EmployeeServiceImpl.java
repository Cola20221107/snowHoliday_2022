package xyz.ccola.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xyz.ccola.domain.Employee;
import xyz.ccola.mapper.EmployeeMapper;
import xyz.ccola.service.EmployeeService;

/**
 * @ Name: EmployeeServiceImpl
 * @ Author: Cola
 * @ Time: 2022/12/27 19:44
 * @ Description: EmployeeServiceImpl
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
}
