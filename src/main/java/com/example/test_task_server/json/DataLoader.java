package com.example.test_task_server.json;

import com.example.test_task_server.entity.SalesByAsin;
import com.example.test_task_server.entity.SalesByDate;
import com.example.test_task_server.entity.TrafficByAsin;
import com.example.test_task_server.entity.TrafficByDate;
import com.example.test_task_server.repository.SalesByAsinRepository;
import com.example.test_task_server.repository.SalesByDateRepository;
import com.example.test_task_server.repository.TrafficByAsinRepository;
import com.example.test_task_server.repository.TrafficByDateRepository;
import com.example.test_task_server.type.CurrencyCode;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DataLoader implements CommandLineRunner {

    private final SalesByDateRepository salesByDateRepository;
    private final SalesByAsinRepository salesByAsinRepository;
    private final TrafficByAsinRepository trafficByAsinRepository;
    private final TrafficByDateRepository trafficByDateRepository;
    private final ObjectMapper objectMapper;

    public DataLoader(SalesByDateRepository salesByDateRepository, SalesByAsinRepository salesByAsinRepository, TrafficByAsinRepository trafficByAsinRepository, TrafficByDateRepository trafficByDateRepository, ObjectMapper objectMapper) {
        this.salesByDateRepository = salesByDateRepository;
        this.salesByAsinRepository = salesByAsinRepository;
        this.trafficByAsinRepository = trafficByAsinRepository;
        this.trafficByDateRepository = trafficByDateRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        scheduledLoadData();
    }

    @Scheduled(fixedRate = 60000)
    public void scheduledLoadData() {
        salesByDateRepository.deleteAll();
        trafficByDateRepository.deleteAll();
        salesByAsinRepository.deleteAll();
        trafficByAsinRepository.deleteAll();

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("json/test_report.json")) {
            if (inputStream != null) {
                JsonNode jsonNode = objectMapper.readValue(inputStream, JsonNode.class);

                if (jsonNode != null) {
                    JsonNode salesAndTrafficByDate = jsonNode.get("salesAndTrafficByDate");
                    JsonNode salesAndTrafficByAsin = jsonNode.get("salesAndTrafficByAsin");

                    if (salesAndTrafficByDate != null && salesAndTrafficByDate.isArray()) {
                        for (JsonNode edge : salesAndTrafficByDate) {
                            JsonNode node = edge;

                            SalesByDate salesByDate = createSalesByDateFromNode(edge);
                            salesByDateRepository.save(salesByDate);

                            JsonNode trafficByDateNode = node.get("trafficByDate");
                            TrafficByDate trafficByDate = createTrafficByDateFromNode(trafficByDateNode);
                            trafficByDateRepository.save(trafficByDate);

                            salesByDate.setTrafficByDate(trafficByDate);
                            salesByDateRepository.save(salesByDate);
                        }
                    }

                    if (salesAndTrafficByAsin != null && salesAndTrafficByAsin.isArray()) {
                        for (JsonNode edge : salesAndTrafficByAsin) {
                            JsonNode node = edge;

                            SalesByAsin salesByAsin = createSalesByAsinFromNode(edge);
                            salesByAsinRepository.save(salesByAsin);

                            JsonNode trafficByAsinNode = node.get("trafficByAsin");
                            TrafficByAsin trafficByAsin = createTrafficByAsinFromNode(trafficByAsinNode);
                            trafficByAsinRepository.save(trafficByAsin);

                            salesByAsin.setTrafficByAsin(trafficByAsin);
                            salesByAsinRepository.save(salesByAsin);
                        }
                    }
                } else {
                    throw new RuntimeException("Failed to read JSON data. JsonNode is null.");
                }
            } else {
                throw new RuntimeException("Failed to read JSON data. InputStream is null.");
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read JSON data", e);
        }
    }

    private TrafficByAsin createTrafficByAsinFromNode(JsonNode trafficByAsinNode) {
        JsonNode node = trafficByAsinNode;

        return TrafficByAsin.builder()
                .browserSessions(node.get("browserSessions").asInt())
                .browserSessionsB2B(node.get("browserSessionsB2B").asInt())
                .mobileAppSessions(node.get("mobileAppSessions").asInt())
                .mobileAppSessionsB2B(node.get("mobileAppSessionsB2B").asInt())
                .sessions(node.get("sessions").asInt())
                .sessionsB2B(node.get("sessionsB2B").asInt())
                .browserSessionPercentage(node.get("browserSessionPercentage").asDouble())
                .browserSessionPercentageB2B(node.get("browserSessionPercentageB2B").asDouble())
                .mobileAppSessionPercentage(node.get("mobileAppSessionPercentage").asDouble())
                .mobileAppSessionPercentageB2B(node.get("mobileAppSessionPercentageB2B").asDouble())
                .sessionPercentage(node.get("sessionPercentage").asDouble())
                .sessionPercentageB2B(node.get("sessionPercentageB2B").asDouble())
                .browserPageViews(node.get("browserPageViews").asInt())
                .browserPageViewsB2B(node.get("browserPageViewsB2B").asInt())
                .mobileAppPageViews(node.get("mobileAppPageViews").asInt())
                .mobileAppPageViewsB2B(node.get("mobileAppPageViewsB2B").asInt())
                .pageViews(node.get("pageViews").asInt())
                .pageViewsB2B(node.get("pageViewsB2B").asInt())
                .browserPageViewsPercentage(node.get("browserPageViewsPercentage").asDouble())
                .browserPageViewsPercentageB2B(node.get("browserPageViewsPercentageB2B").asDouble())
                .mobileAppPageViewsPercentage(node.get("mobileAppPageViewsPercentage").asDouble())
                .mobileAppPageViewsPercentageB2B(node.get("mobileAppPageViewsPercentageB2B").asDouble())
                .pageViewsPercentage(node.get("pageViewsPercentage").asDouble())
                .pageViewsPercentageB2B(node.get("pageViewsPercentageB2B").asDouble())
                .buyBoxPercentage(node.get("buyBoxPercentage").asDouble())
                .buyBoxPercentageB2B(node.get("buyBoxPercentageB2B").asDouble())
                .unitSessionPercentage(node.get("unitSessionPercentage").asDouble())
                .unitSessionPercentageB2B(node.get("unitSessionPercentageB2B").asDouble())
                .build();
    }

    private SalesByAsin createSalesByAsinFromNode(JsonNode edge) {
        JsonNode node = edge;
        String parentAsin = node.get("parentAsin").asText();
        int unitsOrdered = node.get("salesByAsin").get("unitsOrdered").asInt();
        int unitsOrderedB2B = node.get("salesByAsin").get("unitsOrderedB2B").asInt();
        BigDecimal orderedProductSalesAmount = new BigDecimal(node.get("salesByAsin").get("orderedProductSales").get("amount").asText());
        CurrencyCode orderedProductSalesCurrencyCode = CurrencyCode.valueOf(node.get("salesByAsin").get("orderedProductSales").get("currencyCode").asText());
        BigDecimal orderedProductSalesB2BAmount = new BigDecimal(node.get("salesByAsin").get("orderedProductSalesB2B").get("amount").asText());
        CurrencyCode orderedProductSalesB2BCurrencyCode = CurrencyCode.valueOf(node.get("salesByAsin").get("orderedProductSalesB2B").get("currencyCode").asText());
        int totalOrderItems = node.get("salesByAsin").get("totalOrderItems").asInt();
        int totalOrderItemsB2B = node.get("salesByAsin").get("totalOrderItemsB2B").asInt();

        return SalesByAsin.builder()
                .parentAsin(parentAsin)
                .unitsOrdered(unitsOrdered)
                .unitsOrderedB2B(unitsOrderedB2B)
                .orderedProductSalesAmount(orderedProductSalesAmount)
                .orderedProductSalesCurrencyCode(orderedProductSalesCurrencyCode)
                .orderedProductSalesB2BAmount(orderedProductSalesB2BAmount)
                .orderedProductSalesB2BCurrencyCode(orderedProductSalesB2BCurrencyCode)
                .totalOrderItems(totalOrderItems)
                .totalOrderItemsB2B(totalOrderItemsB2B)
                .build();
    }


    private SalesByDate createSalesByDateFromNode(JsonNode edge) {
        JsonNode node = edge;
        Date date = parseDate(node.get("date").asText());
        BigDecimal orderedProductSalesAmount = new BigDecimal(node.get("salesByDate").get("orderedProductSales").get("amount").asText());
        CurrencyCode orderedProductSalesCurrencyCode = CurrencyCode.valueOf(node.get("salesByDate").get("orderedProductSales").get("currencyCode").asText());

        BigDecimal orderedProductSalesB2BAmount = new BigDecimal(node.get("salesByDate").get("orderedProductSalesB2B").get("amount").asText());
        CurrencyCode orderedProductSalesB2BCurrencyCode = CurrencyCode.valueOf(node.get("salesByDate").get("orderedProductSalesB2B").get("currencyCode").asText());

        int unitsOrdered = node.get("salesByDate").get("unitsOrdered").asInt();
        int unitsOrderedB2B = node.get("salesByDate").get("unitsOrderedB2B").asInt();

        int totalOrderItems = node.get("salesByDate").get("totalOrderItems").asInt();
        int totalOrderItemsB2B = node.get("salesByDate").get("totalOrderItemsB2B").asInt();

        BigDecimal averageSalesPerOrderItemAmount = new BigDecimal(node.get("salesByDate").get("averageSalesPerOrderItem").get("amount").asText());
        CurrencyCode averageSalesPerOrderItemCurrencyCode = CurrencyCode.valueOf(node.get("salesByDate").get("averageSalesPerOrderItem").get("currencyCode").asText());

        BigDecimal averageSalesPerOrderItemB2BAmount = new BigDecimal(node.get("salesByDate").get("averageSalesPerOrderItemB2B").get("amount").asText());
        CurrencyCode averageSalesPerOrderItemB2BCurrencyCode = CurrencyCode.valueOf(node.get("salesByDate").get("averageSalesPerOrderItemB2B").get("currencyCode").asText());

        double averageUnitsPerOrderItem = node.get("salesByDate").get("averageUnitsPerOrderItem").asDouble();
        double averageUnitsPerOrderItemB2B = node.get("salesByDate").get("averageUnitsPerOrderItemB2B").asDouble();

        BigDecimal averageSellingPriceAmount = new BigDecimal(node.get("salesByDate").get("averageSellingPrice").get("amount").asText());
        CurrencyCode averageSellingPriceCurrencyCode = CurrencyCode.valueOf(node.get("salesByDate").get("averageSellingPrice").get("currencyCode").asText());

        BigDecimal averageSellingPriceB2BAmount = new BigDecimal(node.get("salesByDate").get("averageSellingPriceB2B").get("amount").asText());
        CurrencyCode averageSellingPriceB2BCurrencyCode = CurrencyCode.valueOf(node.get("salesByDate").get("averageSellingPriceB2B").get("currencyCode").asText());

        int unitsRefunded = node.get("salesByDate").get("unitsRefunded").asInt();
        double refundRate = node.get("salesByDate").get("refundRate").asDouble();

        int claimsGranted = node.get("salesByDate").get("claimsGranted").asInt();

        BigDecimal claimsAmount = new BigDecimal(node.get("salesByDate").get("claimsAmount").get("amount").asText());
        CurrencyCode claimsCurrencyCode = CurrencyCode.valueOf(node.get("salesByDate").get("claimsAmount").get("currencyCode").asText());

        BigDecimal shippedProductSalesAmount = new BigDecimal(node.get("salesByDate").get("shippedProductSales").get("amount").asText());
        CurrencyCode shippedProductSalesCurrencyCode = CurrencyCode.valueOf(node.get("salesByDate").get("shippedProductSales").get("currencyCode").asText());

        int unitsShipped = node.get("salesByDate").get("unitsShipped").asInt();
        int ordersShipped = node.get("salesByDate").get("ordersShipped").asInt();

        return SalesByDate.builder()
                .date(date)
                .orderedProductSalesAmount(orderedProductSalesAmount)
                .orderedProductSalesCurrencyCode(orderedProductSalesCurrencyCode)
                .orderedProductSalesB2BAmount(orderedProductSalesB2BAmount)
                .orderedProductSalesB2BCurrencyCode(orderedProductSalesB2BCurrencyCode)
                .unitsOrdered(unitsOrdered)
                .unitsOrderedB2B(unitsOrderedB2B)
                .totalOrderItems(totalOrderItems)
                .totalOrderItemsB2B(totalOrderItemsB2B)
                .averageSalesPerOrderItemAmount(averageSalesPerOrderItemAmount)
                .averageSalesPerOrderItemCurrencyCode(averageSalesPerOrderItemCurrencyCode)
                .averageSalesPerOrderItemB2BAmount(averageSalesPerOrderItemB2BAmount)
                .averageSalesPerOrderItemB2BCurrencyCode(averageSalesPerOrderItemB2BCurrencyCode)
                .averageUnitsPerOrderItem(averageUnitsPerOrderItem)
                .averageUnitsPerOrderItemB2B(averageUnitsPerOrderItemB2B)
                .averageSellingPriceAmount(averageSellingPriceAmount)
                .averageSellingPriceCurrencyCode(averageSellingPriceCurrencyCode)
                .averageSellingPriceB2BAmount(averageSellingPriceB2BAmount)
                .averageSellingPriceB2BCurrencyCode(averageSellingPriceB2BCurrencyCode)
                .unitsRefunded(unitsRefunded)
                .refundRate(refundRate)
                .claimGranted(claimsGranted)
                .claimsAmount(claimsAmount)
                .claimsCurrencyCode(claimsCurrencyCode)
                .shippedProductSalesAmount(shippedProductSalesAmount)
                .shippedProductSalesCurrencyCode(shippedProductSalesCurrencyCode)
                .unitsShipped(unitsShipped)
                .ordersShipped(ordersShipped)
                .build();
    }

    private TrafficByDate createTrafficByDateFromNode(JsonNode edge) {
        JsonNode node = edge;
    return TrafficByDate.builder()
                .browserPageViews(node.get("browserPageViews").asInt())
                .browserPageViewsB2B(node.get("browserPageViewsB2B").asInt())
                .mobileAppPageViews(node.get("mobileAppPageViews").asInt())
                .mobileAppPageViewsB2B(node.get("mobileAppPageViewsB2B").asInt())
                .pageViews(node.get("pageViews").asInt())
                .pageViewsB2B(node.get("pageViewsB2B").asInt())
                .browserSessions(node.get("browserSessions").asInt())
                .browserSessionsB2B(node.get("browserSessionsB2B").asInt())
                .mobileAppSessions(node.get("mobileAppSessions").asInt())
                .mobileAppSessionsB2B(node.get("mobileAppSessionsB2B").asInt())
                .sessions(node.get("sessions").asInt())
                .sessionB2B(node.get("sessionsB2B").asInt())
                .buyBoxPercentage(node.get("buyBoxPercentage").asDouble())
                .buyBoxPercentageB2B(node.get("buyBoxPercentageB2B").asDouble())
                .orderItemSessionPercentage(node.get("orderItemSessionPercentage").asDouble())
                .orderItemSessionPercentageB2B(node.get("orderItemSessionPercentageB2B").asDouble())
                .unitSessionPercentage(node.get("unitSessionPercentage").asDouble())
                .unitSessionPercentageB2B(node.get("unitSessionPercentageB2B").asDouble())
                .averageOfferCount(node.get("averageOfferCount").asDouble())
                .averageParentItems(node.get("averageParentItems").asDouble())
                .feedbackReceived(node.get("feedbackReceived").asInt())
                .negativeFeedbackReceived(node.get("negativeFeedbackReceived").asInt())
                .receivedNegativeFeedbackRate(node.get("receivedNegativeFeedbackRate").asInt())
                .build();
    }

    private Date parseDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException("Failed to parse sales-by-date.data.ts", e);
        }
    }
}
