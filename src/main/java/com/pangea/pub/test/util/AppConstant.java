package com.pangea.pub.test.util;

import com.pangea.pub.test.dto.response.SubscriptionResponse;
import com.pangea.pub.test.model.Subscription;

import java.util.HashMap;

public class AppConstant {
    private static HashMap<String, Subscription> subscriptions = new HashMap<>();

    public static SubscriptionResponse createSubscription(String topic, String url) {
        String key = topic+url;
        Subscription subscription = new Subscription();
        subscription.setTopic(topic);
        subscription.setUrl(url);
        subscriptions.put(key, subscription);
        SubscriptionResponse subscriptionResponse = new SubscriptionResponse(url, topic);
        return subscriptionResponse;
    }

    public static HashMap<String, Subscription> getSubscriptions() {
        return subscriptions;
    }
}
