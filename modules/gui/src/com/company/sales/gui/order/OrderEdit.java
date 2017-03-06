package com.company.sales.gui.order;

import com.company.sales.entity.Order;
import com.company.sales.entity.OrderLine;
import com.company.sales.entity.Product;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.AbstractEditor;
import com.haulmont.cuba.gui.data.CollectionDatasource;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

public class OrderEdit extends AbstractEditor<Order> {

    @Inject
    private Metadata metadata;
    @Inject
    private CollectionDatasource<OrderLine, UUID> linesDs;

    @Override
    public void init(Map<String, Object> params) {
        linesDs.addCollectionChangeListener(e -> calculateAmount());
        linesDs.addItemPropertyChangeListener(e -> calculateAmount());
    }

    private void calculateAmount() {
        BigDecimal amount = BigDecimal.ZERO;
        for (OrderLine line : linesDs.getItems()) {
            BigDecimal total = line.getProduct().getPrice().multiply(line.getQuantity())
                    .setScale(2, BigDecimal.ROUND_HALF_UP); // always set scale to the same as defined in the database to avoid modification of the datasource after commit
            line.setTotal(total);
            amount = amount.add(line.getTotal());
        }
        getItem().setAmount(amount);
    }

    public void createOrderLine() {
        openLookup(Product.class,
                items -> {
                    if (!items.isEmpty()) {
                        OrderLine orderLine = metadata.create(OrderLine.class);
                        orderLine.setOrder(getItem());
                        Product product = (Product) items.iterator().next();
                        orderLine.setProduct(product);
                        orderLine.setQuantity(BigDecimal.ZERO);
                        linesDs.addItem(orderLine);
                    }
                },
                WindowManager.OpenType.DIALOG.height(400));
    }
}