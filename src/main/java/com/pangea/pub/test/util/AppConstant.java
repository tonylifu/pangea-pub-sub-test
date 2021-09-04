package com.pangea.pub.test.util;

import com.pangea.pub.test.dto.response.SubscriptionResponse;
import com.pangea.pub.test.model.Subscription;

import java.util.HashMap;
import java.util.Map;

public class AppConstant {
    public static final String TOPIC_ONE = "topic1";
    public static final String TOPIC_TWO = "topic2";
    private static HashMap<String, Subscription> subscriptions = new HashMap<>();
//    private static DestionationTopic destination = new DestionationTopic();

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

    public static HashMap<String, Subscription> getSubscribersList(String topic, HashMap<String, Subscription> subscriptions){
        HashMap<String, Subscription> subscribers = new HashMap<>();
        for (Map.Entry<String, Subscription> entry : subscriptions.entrySet()) {
            String topicIn = entry.getValue().getTopic();
            if(topicIn.trim().equalsIgnoreCase(topic.trim())){
                subscribers.put(entry.getKey(), entry.getValue());
            }
        }
        return  subscribers;
    }

//    public static DestionationTopic getDestination(){
//        return destination;
//    }
//
//    public static void setDestination(DestionationTopic dest){
//        destination.setTopic(dest.getTopic());
//    }
}
