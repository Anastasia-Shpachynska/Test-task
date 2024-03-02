package com.example.test_task_server.entity;

import com.example.test_task_server.type.CurrencyCode;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@Document(value = "sales_by_date")
public class SalesByDate {

    @Id
    private String id;

    private Date date;

    private BigDecimal orderedProductSalesAmount;

    private CurrencyCode orderedProductSalesCurrencyCode;

    private BigDecimal orderedProductSalesB2BAmount;

    private CurrencyCode orderedProductSalesB2BCurrencyCode;

    private int unitsOrdered;

    private int unitsOrderedB2B;

    private int totalOrderItems;

    private int totalOrderItemsB2B;

    private BigDecimal averageSalesPerOrderItemAmount;

    private CurrencyCode averageSalesPerOrderItemCurrencyCode;

    private BigDecimal averageSalesPerOrderItemB2BAmount;

    private CurrencyCode averageSalesPerOrderItemB2BCurrencyCode;

    private double averageUnitsPerOrderItem;

    private double averageUnitsPerOrderItemB2B;

    private BigDecimal averageSellingPriceAmount;

    private CurrencyCode averageSellingPriceCurrencyCode;

    private BigDecimal averageSellingPriceB2BAmount;

    private CurrencyCode averageSellingPriceB2BCurrencyCode;

    private int unitsRefunded;

    private double refundRate;

    private int claimGranted;

    private BigDecimal claimsAmount;

    private CurrencyCode claimsCurrencyCode;

    private BigDecimal shippedProductSalesAmount;

    private CurrencyCode shippedProductSalesCurrencyCode;

    private int unitsShipped;

    private int ordersShipped;

    @DBRef
    @Cascade({})
    private TrafficByDate trafficByDate;
}
