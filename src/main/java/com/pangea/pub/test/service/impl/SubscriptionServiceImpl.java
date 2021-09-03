package com.pangea.pub.test.service.impl;

import com.pangea.pub.test.dto.response.SubscriptionResponse;
import com.pangea.pub.test.service.SubscribeService;
import com.pangea.pub.test.util.AppConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionServiceImpl implements SubscribeService {
    private static final Logger LOG = LoggerFactory.getLogger(SubscriptionServiceImpl.class);
    @Override
    public SubscriptionResponse createSubscription(String topic, String url) {
        LOG.info("\n\nTopic: {}\nURL: {}\n\n", topic, url);
        SubscriptionResponse subscriptionResponse = AppConstant.createSubscription(topic, url);
        return subscriptionResponse;
    }
}
