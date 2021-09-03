package com.pangea.pub.test.service;

import com.pangea.pub.test.dto.request.PublishRequest;

public interface PublishService {
    void publishToTopic(String topic, PublishRequest publishRequest);
}
