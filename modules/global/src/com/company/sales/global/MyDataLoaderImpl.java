/*
 * Copyright (c) ${YEAR} ${PACKAGE_NAME}
 */

package com.company.sales.global;

import com.company.sales.entity.Customer;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.LoadContext;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.UUID;

/**
 *
 */
@Component(MyDataLoader.NAME)
public class MyDataLoaderImpl implements MyDataLoader {

    @Inject
    private DataManager dataManager;

    @Override
    public Customer loadCustomer(UUID customerId) {
        return dataManager.load(LoadContext.create(Customer.class).setId(customerId));
    }
}
