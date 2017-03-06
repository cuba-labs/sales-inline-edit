/*
 * Copyright (c) ${YEAR} ${PACKAGE_NAME}
 */

package com.company.sales.global;

import com.company.sales.entity.Customer;

import java.util.UUID;

/**
 *
 */
public interface MyDataLoader {

    String NAME = "sales_MyDataLoader";

    Customer loadCustomer(UUID customerId);
}
