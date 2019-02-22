package com.shu.cashbook.service.Impl;

import com.shu.cashbook.domain.ItemType;
import com.shu.cashbook.mapper.ItemTypeMapper;
import com.shu.cashbook.service.ItemTypeService;
import org.springframework.stereotype.Service;

/**
 * @version 1.0
 * @author: yang
 * @date: 2019/2/22 19:11
 */
@Service
public class ItemTypeServiceImpl extends BaseServiceImpl<ItemType, ItemTypeMapper> implements ItemTypeService {
    @Override
    public ItemType selectByName(String name) {
        ItemType itemType=new ItemType();
        itemType.setItemName(name);
        return this.selectOne(itemType);
    }
}
