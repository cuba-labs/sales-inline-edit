/*
 * Copyright (c) 2017 com.company.sales.entity
 */
package com.company.sales.entity.foo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import com.haulmont.cuba.core.entity.StandardEntity;

/**
 * @author krivopustov
 */
@Table(name = "SALES_NEW_ENTITY")
@Entity(name = "sales$NewEntity")
public class NewEntity extends StandardEntity {
    private static final long serialVersionUID = -1712627856923083334L;

    @Column(name = "NAME")
    protected String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}