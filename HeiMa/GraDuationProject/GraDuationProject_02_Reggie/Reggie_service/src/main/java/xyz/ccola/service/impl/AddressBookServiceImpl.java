package xyz.ccola.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xyz.ccola.domain.AddressBook;
import xyz.ccola.mapper.AddressBookMapper;
import xyz.ccola.service.AddressBookService;

/**
 * @ Name: AddressBookServiceImpl
 * @ Author: Cola
 * @ Time: 2022/12/27 19:40
 * @ Description: AddressBookServiceImpl
 */
@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements AddressBookService {
}
