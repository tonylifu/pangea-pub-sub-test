package com.pangea.pub.test.controller;

import com.pangea.pub.test.dto.request.SubscriptionRequest;
import com.pangea.pub.test.dto.response.SubscriptionResponse;
import com.pangea.pub.test.service.SubscribeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subscribe")
public class SubscriptionController {
    private final static Logger LOG = LoggerFactory.getLogger(SubscriptionController.class);
    private SubscribeService subscribeService;

    @Autowired
    public SubscriptionController(SubscribeService subscribeService) {
        this.subscribeService = subscribeService;
    }

    @RequestMapping(value = "/{topic}", method = RequestMethod.POST,
            produces= {MediaType.APPLICATION_JSON_VALUE}, consumes={MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public SubscriptionResponse subscribe(@PathVariable String topic, @RequestBody SubscriptionRequest subscriptionRequest) {
        SubscriptionResponse subscriptionResponse = subscribeService.createSubscription(topic, subscriptionRequest.getUrl());
        LOG.info("\n\nSubscription Response:\n{}\n\n", subscriptionResponse);
        return subscriptionResponse;
    }
}
