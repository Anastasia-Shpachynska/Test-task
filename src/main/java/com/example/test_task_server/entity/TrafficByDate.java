package com.example.test_task_server.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(value = "traffic_by_date")
public class TrafficByDate {

    @Id
    private String id;

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
}
