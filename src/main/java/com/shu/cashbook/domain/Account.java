package com.shu.cashbook.domain;

import javax.persistence.Column;
import java.util.Date;

public class Account extends BaseDomain {
    /**
     * 账本名称
     */
    @Column(name = "acct_name")
    private String acctName;

    /**
     * 币种类型
     */
    @Column(name = "currency_type")
    private String currencyType;

    /**
     * 创建用户ID
     */
    @Column(name = "creator_id")
    private String creatorId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;


    /**
     * 获取账本名称
     *
     * @return acct_name - 账本名称
     */
    public String getAcctName() {
        return acctName;
    }

    /**
     * 设置账本名称
     *
     * @param acctName 账本名称
     */
    public void setAcctName(String acctName) {
        this.acctName = acctName;
    }

    /**
     * 获取币种类型
     *
     * @return currency_type - 币种类型
     */
    public String getCurrencyType() {
        return currencyType;
    }

    /**
     * 设置币种类型
     *
     * @param currencyType 币种类型
     */
    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

    /**
     * 获取创建用户ID
     *
     * @return creator_id - 创建用户ID
     */
    public String getCreatorId() {
        return creatorId;
    }

    /**
     * 设置创建用户ID
     *
     * @param creatorId 创建用户ID
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
}