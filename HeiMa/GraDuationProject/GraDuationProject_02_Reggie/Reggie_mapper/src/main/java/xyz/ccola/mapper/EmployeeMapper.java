package xyz.ccola.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import xyz.ccola.domain.Employee;

/**
 * @ Name: EmployeeMapper
 * @ Author: Cola
 * @ Time: 2022/12/27 19:44
 * @ Description: EmployeeMapper
 */
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}
