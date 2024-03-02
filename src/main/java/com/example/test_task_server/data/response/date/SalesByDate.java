package com.example.test_task_server.data.response.date;

import com.example.test_task_server.type.CurrencyCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class SalesByDate {
    private String idSales;
    private String date;
    private String orderedProductSalesAmount;
    private String orderedProductSalesCurrencyCode;
    private String orderedProductSalesB2BAmount;
    private String orderedProductSalesB2BCurrencyCode;
    private int unitsOrdered;
    private int unitsOrderedB2B;
    private int totalOrderItems;
    private int totalOrderItemsB2B;
    private String averageSalesPerOrderItemAmount;
    private String averageSalesPerOrderItemCurrencyCode;
    private String averageSalesPerOrderItemB2BAmount;
    private String averageSalesPerOrderItemB2BCurrencyCode;
    private double averageUnitsPerOrderItem;
    private double averageUnitsPerOrderItemB2B;
    private String averageSellingPriceAmount;
    private String averageSellingPriceCurrencyCode;
    private String averageSellingPriceB2BAmount;
    private String averageSellingPriceB2BCurrencyCode;
    private int unitsRefunded;
    private double refundRate;
    private int claimGranted;
    private String claimsAmount;
    private String claimsCurrencyCode;
    private String shippedProductSalesAmount;
    private String shippedProductSalesCurrencyCode;
    private int unitsShipped;
    private int ordersShipped;

    private String idTraffic;
    private int browserPageViews;
    private  int browserPageViewsB2B;
    private int mobileAppPageViews;
    private int mobileAppPageViewsB2B;
    private int pageViews;
    private int pageViewsB2B;
    private int browserSessions;
    private int browserSessionsB2B;
    private int mobileAppSessions;
    private int mobileAppSessionsB2B;
    private int sessions;
    private int sessionB2B;
    private double buyBoxPercentage;
    private double buyBoxPercentageB2B;
    private double orderItemSessionPercentage;
    private double orderItemSessionPercentageB2B;
    private double unitSessionPercentage;
    private double unitSessionPercentageB2B;
    private double averageOfferCount;
    private double averageParentItems;
    private int feedbackReceived;
    private int negativeFeedbackReceived;
    private int receivedNegativeFeedbackRate;

    public SalesByDate(com.example.test_task_server.entity.SalesByDate salesByDate) {
        this.idSales = salesByDate.getId();
        this.date = String.valueOf(salesByDate.getDate());
        this.orderedProductSalesAmount = String.valueOf(salesByDate.getOrderedProductSalesAmount());
        this.orderedProductSalesCurrencyCode = String.valueOf(salesByDate.getOrderedProductSalesCurrencyCode());
        this.orderedProductSalesB2BAmount = String.valueOf(salesByDate.getOrderedProductSalesB2BAmount());
        this.orderedProductSalesB2BCurrencyCode = String.valueOf(salesByDate.getOrderedProductSalesB2BCurrencyCode());
        this.unitsOrdered = salesByDate.getUnitsOrdered();
        this.unitsOrderedB2B = salesByDate.getUnitsOrderedB2B();
        this.totalOrderItems = salesByDate.getTotalOrderItems();
        this.totalOrderItemsB2B = salesByDate.getTotalOrderItemsB2B();
        this.averageSalesPerOrderItemAmount = String.valueOf(salesByDate.getAverageSalesPerOrderItemAmount());
        this.averageSalesPerOrderItemCurrencyCode = String.valueOf(salesByDate.getAverageSalesPerOrderItemCurrencyCode());
        this.averageSalesPerOrderItemB2BAmount = String.valueOf(salesByDate.getAverageSalesPerOrderItemB2BAmount());
        this.averageSalesPerOrderItemB2BCurrencyCode = String.valueOf(salesByDate.getAverageSalesPerOrderItemB2BCurrencyCode());
        this.averageUnitsPerOrderItem = salesByDate.getAverageUnitsPerOrderItem();
        this.averageUnitsPerOrderItemB2B = salesByDate.getAverageUnitsPerOrderItemB2B();
        this.averageSellingPriceAmount = String.valueOf(salesByDate.getAverageSellingPriceAmount());
        this.averageSellingPriceCurrencyCode = String.valueOf(salesByDate.getAverageSellingPriceCurrencyCode());
        this.averageSellingPriceB2BAmount = String.valueOf(salesByDate.getAverageSellingPriceB2BAmount());
        this.averageSellingPriceB2BCurrencyCode = String.valueOf(salesByDate.getAverageSellingPriceB2BCurrencyCode());
        this.unitsRefunded = salesByDate.getUnitsRefunded();
        this.refundRate = salesByDate.getRefundRate();
        this.claimGranted = salesByDate.getClaimGranted();
        this.claimsAmount = String.valueOf(salesByDate.getClaimsAmount());
        this.claimsCurrencyCode = String.valueOf(salesByDate.getClaimsCurrencyCode());
        this.shippedProductSalesAmount = String.valueOf(salesByDate.getShippedProductSalesAmount());
        this.shippedProductSalesCurrencyCode = String.valueOf(salesByDate.getShippedProductSalesCurrencyCode());
        this.unitsShipped = salesByDate.getUnitsShipped();
        this.ordersShipped = salesByDate.getOrdersShipped();

        this.idTraffic = salesByDate.getTrafficByDate().getId();
        this.browserPageViews = salesByDate.getTrafficByDate().getBrowserPageViews();
        this.browserPageViewsB2B = salesByDate.getTrafficByDate().getBrowserPageViewsB2B();
        this.mobileAppPageViews = salesByDate.getTrafficByDate().getMobileAppPageViews();
        this.mobileAppPageViewsB2B = salesByDate.getTrafficByDate().getMobileAppPageViewsB2B();
        this.pageViews = salesByDate.getTrafficByDate().getPageViews();
        this.pageViewsB2B = salesByDate.getTrafficByDate().getPageViewsB2B();
        this.browserSessions = salesByDate.getTrafficByDate().getBrowserSessions();
        this.browserSessionsB2B = salesByDate.getTrafficByDate().getBrowserSessionsB2B();
        this.mobileAppSessions = salesByDate.getTrafficByDate().getMobileAppSessions();
        this.mobileAppSessionsB2B = salesByDate.getTrafficByDate().getMobileAppSessionsB2B();
        this.sessions = salesByDate.getTrafficByDate().getSessions();
        this.sessionB2B = salesByDate.getTrafficByDate().getSessionB2B();
        this.buyBoxPercentage = salesByDate.getTrafficByDate().getBuyBoxPercentage();
        this.buyBoxPercentageB2B = salesByDate.getTrafficByDate().getBuyBoxPercentageB2B();
        this.orderItemSessionPercentage = salesByDate.getTrafficByDate().getOrderItemSessionPercentage();
        this. orderItemSessionPercentageB2B = salesByDate.getTrafficByDate().getOrderItemSessionPercentageB2B();
        this.unitSessionPercentage = salesByDate.getTrafficByDate().getUnitSessionPercentage();
        this.unitSessionPercentageB2B = salesByDate.getTrafficByDate().getUnitSessionPercentageB2B();
        this.averageOfferCount = salesByDate.getTrafficByDate().getAverageOfferCount();
        this.averageParentItems = salesByDate.getTrafficByDate().getAverageParentItems();
        this.feedbackReceived = salesByDate.getTrafficByDate().getFeedbackReceived();
        this.negativeFeedbackReceived = salesByDate.getTrafficByDate().getNegativeFeedbackReceived();
        this.receivedNegativeFeedbackRate = salesByDate.getTrafficByDate().getReceivedNegativeFeedbackRate();
    }
}
