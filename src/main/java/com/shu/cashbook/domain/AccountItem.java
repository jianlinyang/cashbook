package com.shu.cashbook.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Table(name = "account_item")
public class AccountItem extends BaseDomain{
//    /**
//     * 账目主键
//     */
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
///   private String id;
//
    /**
     * 账本主键
     */
    @Column(name = "acct_id")
    private String acctId;

    /**
     * 分类Id
     */
    @Column(name = "tag_id")
    private String tagId;

    /**
     * 操作金额
     */
    @Column(name = "change_money")
    private Double changeMoney;

    /**
     * 收支类型
     */
    @Column(name = "item_type")
    private String itemType;

    /**
     * 创建人
     */
    @Column(name = "creator_id")
    private String creatorId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 备注
     */
    private String note;

    /**
     * 获取账目主键
     *
     * @return id - 账目主键
     */
//    public String getId() {
//        return id;
//    }
//
//    /**
//     * 设置账目主键
//     *
//     * @param id 账目主键
//     */
//    public void setId(String id) {
//        this.id = id;
//    }

    /**
     * 获取账本主键
     *
     * @return acct_id - 账本主键
     */
    public String getAcctId() {
        return acctId;
    }

    /**
     * 设置账本主键
     *
     * @param acctId 账本主键
     */
    public void setAcctId(String acctId) {
        this.acctId = acctId;
    }

    /**
     * 获取分类Id
     *
     * @return tag_id - 分类Id
     */
    public String getTagId() {
        return tagId;
    }

    /**
     * 设置分类Id
     *
     * @param tagId 分类Id
     */
    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    /**
     * 获取操作金额
     *
     * @return change_money - 操作金额
     */
    public Double getChangeMoney() {
        return changeMoney;
    }

    /**
     * 设置操作金额
     *
     * @param changeMoney 操作金额
     */
    public void setChangeMoney(Double changeMoney) {
        this.changeMoney = changeMoney;
    }

    /**
     * 获取收支类型
     *
     * @return item_type - 收支类型
     */
    public String getItemType() {
        return itemType;
    }

    /**
     * 设置收支类型
     *
     * @param itemType 收支类型
     */
    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    /**
     * 获取创建人
     *
     * @return creator_id - 创建人
     */
    public String getCreatorId() {
        return creatorId;
    }

    /**
     * 设置创建人
     *
     * @param creatorId 创建人
     */
    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取备注
     *
     * @return note - 备注
     */
    public String getNote() {
        return note;
    }

    /**
     * 设置备注
     *
     * @param note 备注
     */
    public void setNote(String note) {
        this.note = note;
    }
}