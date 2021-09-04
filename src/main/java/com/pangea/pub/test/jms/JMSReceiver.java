package com.pangea.pub.test.jms;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pangea.pub.test.adapter.PublishServiceAdapter;
import com.pangea.pub.test.dto.request.PublishRequest;
import com.pangea.pub.test.httpservice.RestService;
import com.pangea.pub.test.model.Subscription;
import com.pangea.pub.test.util.AppConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class JMSReceiver {
    private static final Logger LOG = LoggerFactory.getLogger(JMSReceiver.class);

    private PublishServiceAdapter publishServiceAdapter;
    private RestService restService;
    private Gson gson;
    final private String TOPIC_ONE = AppConstant.TOPIC_ONE;
    final private String TOPIC_TWO = AppConstant.TOPIC_TWO;

    @Autowired
    public JMSReceiver(PublishServiceAdapter publishServiceAdapter, RestService restService) {
        this.publishServiceAdapter = publishServiceAdapter;
        this.restService = restService;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @JmsListener(destination = TOPIC_ONE, containerFactory = "myFactory")
    public void onTopicOneMessage(PublishRequest publishRequest){
        System.out.println("JMSReceived<JMS topic1>: \n"+ gson.toJson(publishRequest));
        LOG.info("\n\nJMS Message Received: \n{}\n{}\n\n", TOPIC_ONE, gson.toJson(publishRequest));

        HashMap<String, Subscription> subscriptions = AppConstant.getSubscriptions();
        if (subscriptions.size() == 0) return;
        HashMap<String, Subscription> subscribersList = AppConstant.getSubscribersList(TOPIC_ONE, subscriptions);
        for (Map.Entry<String, Subscription> entry : subscribersList.entrySet()) {
            LOG.info("\n\nSubscription<JMS topic1>: \n{}\n\n", gson.toJson(entry.getValue()));
            String url = entry.getValue().getUrl();
            String json = gson.toJson(publishRequest);
            System.out.println("JSON<JMS topic1>: " + json);
            String response = restService.makeServiceCall(json, url);
            LOG.info("\n\nRESPONSE<JMS topic1>: \n{}\n\n", response);
        }
    }

    @JmsListener(destination = TOPIC_TWO, containerFactory = "myFactory")
    public void onTopicTwoMessage(PublishRequest publishRequest){
        System.out.println("JMSReceived<JMS topic2>: \n"+ gson.toJson(publishRequest));
        LOG.info("\n\nJMS Message Received<JMS>: \n{}\n{}\n\n", TOPIC_TWO, gson.toJson(publishRequest));

        HashMap<String, Subscription> subscriptions = AppConstant.getSubscriptions();
        if (subscriptions.size() == 0) return;
        HashMap<String, Subscription> subscribersList = AppConstant.getSubscribersList(TOPIC_TWO, subscriptions);
        for (Map.Entry<String, Subscription> entry : subscribersList.entrySet()) {
            LOG.info("\n\nSubscription<JMS topic2>: \n{}\n\n", gson.toJson(entry.getValue()));
            String url = entry.getValue().getUrl();
            String json = gson.toJson(publishRequest);
            System.out.println("JSON<JMS topic2>: " + json);
            String response = restService.makeServiceCall(json, url);
            LOG.info("\n\nRESPONSE<JMS topic2>: \n{}\n\n", response);
        }
    }

}
