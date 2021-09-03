package com.pangea.pub.test.service;

import com.pangea.pub.test.dto.response.SubscriptionResponse;

public interface SubscribeService {
    SubscriptionResponse createSubscription(String topic, String url);
}
