package xyz.ccola.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import xyz.ccola.domain.AddressBook;

/**
 * @ Name: AddressBook
 * @ Author: Cola
 * @ Time: 2022/12/27 19:38
 * @ Description: AddressBook
 */
@Mapper
public interface AddressBookMapper extends BaseMapper<AddressBook> {
}
