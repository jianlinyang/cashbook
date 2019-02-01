package com.shu.cashbook.domain;

import javax.persistence.*;

public class Authority {
    @Id
    private String name;

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
}