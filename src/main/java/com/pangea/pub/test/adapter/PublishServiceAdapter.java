package com.pangea.pub.test.adapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pangea.pub.test.dto.request.PublishRequest;
import com.pangea.pub.test.httpservice.RestService;
import com.pangea.pub.test.service.PublishService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublishServiceAdapter implements PublishService {

    private static final Logger LOG = LoggerFactory.getLogger(PublishServiceAdapter.class);

    private RestService restService;
    private Gson gson;
    public String TOPIC;

    @Autowired
    public PublishServiceAdapter(RestService restService) {
        this.restService = restService;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @Override
    public void publishToTopic(String topic, PublishRequest publishRequest) {
        TOPIC = topic;

    }
}
