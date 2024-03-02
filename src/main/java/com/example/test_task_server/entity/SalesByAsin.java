package com.example.test_task_server.entity;

import com.example.test_task_server.type.CurrencyCode;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@Builder
@Document(value = "sales_by_asin")
public class SalesByAsin {

    @Id
    private String id;

    private String parentAsin;

    private int unitsOrdered;

    private int unitsOrderedB2B;

    private BigDecimal orderedProductSalesAmount;

    private CurrencyCode orderedProductSalesCurrencyCode;

    private BigDecimal orderedProductSalesB2BAmount;

    private CurrencyCode orderedProductSalesB2BCurrencyCode;

    private int totalOrderItems;

    private int totalOrderItemsB2B;

    @DBRef
    @Cascade({})
    private TrafficByAsin trafficByAsin;
}
