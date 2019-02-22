package com.shu.cashbook.service;

import com.shu.cashbook.domain.ItemType;

/**
 * @version 1.0
 * @author: yang
 * @date: 2019/2/22 19:09
 */
public interface ItemTypeService extends BaseService<ItemType> {
    ItemType selectByName(String name);
}
