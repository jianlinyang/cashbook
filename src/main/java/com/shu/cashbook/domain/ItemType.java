package com.shu.cashbook.domain;

import javax.persistence.*;

@Table(name = "item_type")
public class ItemType extends BaseDomain {
    /**
     * item_id
     */
//    @Id
///    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private String id;

    /**
     * 字典主表Id
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 类型名称
     */
    @Column(name = "item_name")
    private String itemName;

    /**
     * 类型图标路径
     */
    @Column(name = "item_icon")
    private String itemIcon;

    /**
     * 是否删除(可见)
     */
    private Boolean viewable;

    /**
     * 获取item_id
     *
     * @return id - item_id
     */
//    public String getId() {
//        return id;
//    }
//
//    /**
//     * 设置item_id
//     *
//     * @param id item_id
//     */
//    public void setId(String id) {
//        this.id = id;
//    }

    /**
     * 获取字典主表Id
     *
     * @return user_id - 字典主表Id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置字典主表Id
     *
     * @param userId 字典主表Id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取类型名称
     *
     * @return item_name - 类型名称
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * 设置类型名称
     *
     * @param itemName 类型名称
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * 获取类型图标路径
     *
     * @return item_icon - 类型图标路径
     */
    public String getItemIcon() {
        return itemIcon;
    }

    /**
     * 设置类型图标路径
     *
     * @param itemIcon 类型图标路径
     */
    public void setItemIcon(String itemIcon) {
        this.itemIcon = itemIcon;
    }

    /**
     * 获取是否删除(可见)
     *
     * @return viewable - 是否删除(可见)
     */
    public Boolean getViewable() {
        return viewable;
    }

    /**
     * 设置是否删除(可见)
     *
     * @param viewable 是否删除(可见)
     */
    public void setViewable(Boolean viewable) {
        this.viewable = viewable;
    }
}