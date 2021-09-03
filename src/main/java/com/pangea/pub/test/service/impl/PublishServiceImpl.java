package com.pangea.pub.test.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pangea.pub.test.dto.request.PublishRequest;
import com.pangea.pub.test.dto.response.PublishResponse;
import com.pangea.pub.test.httpservice.RestService;
import com.pangea.pub.test.model.Subscription;
import com.pangea.pub.test.service.PublishService;
import com.pangea.pub.test.util.AppConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class PublishServiceImpl implements PublishService {

    private static final Logger LOG = LoggerFactory.getLogger(PublishServiceImpl.class);

    private RestService restService;
    private Gson gson;

    @Autowired
    public PublishServiceImpl(RestService restService) {
        this.restService = restService;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @Override
    public void publishToTopic(String topic, PublishRequest publishRequest) {
        PublishResponse publishResponse = new PublishResponse();
        HashMap<String, Subscription> subscriptions = AppConstant.getSubscriptions();
        if(subscriptions.size() == 0) return;
        for (Map.Entry<String, Subscription> entry : subscriptions.entrySet()) {
            //System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
            LOG.info("\n\nSubscription: \n{}\n\n", gson.toJson(entry.getValue()));

            String topicEntry = entry.getValue().getTopic();
            if(topicEntry.trim().equalsIgnoreCase(topic.trim())){
                String url = entry.getValue().getUrl();
                String json = gson.toJson(publishRequest);
                System.out.println("JSON: "+json);
                //String response = restService.restTemplateServiceCall(json, url);
                String response = restService.makeServiceCall(json, url);
                //if(response == null) throw new IllegalArgumentException();
                publishResponse.setTopic(topic);
                publishResponse.setData(response);
            }
        }
    }

}
