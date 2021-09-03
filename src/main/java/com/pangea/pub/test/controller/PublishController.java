package com.pangea.pub.test.controller;

import com.pangea.pub.test.dto.request.PublishRequest;
import com.pangea.pub.test.service.PublishService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/publish")
public class PublishController {
    private static final Logger LOG = LoggerFactory.getLogger(PublishController.class);

    private PublishService publishService;

    @Autowired
    public PublishController(PublishService publishService) {
        this.publishService = publishService;
    }

    @PostMapping("/{topic}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> publish(@PathVariable String topic, @RequestBody Object requestData) {
        LOG.info("\n\nPublish RequestData:\n{}\n\n", requestData);
        PublishRequest publishRequest = getPublishRequest(topic, requestData);
        LOG.info("\n\nPublish Request:\n{}\n\n", publishRequest);
        publishService.publishToTopic(topic, publishRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private PublishRequest getPublishRequest(String topic, Object requestData) {
        PublishRequest publishRequest = new PublishRequest();
        publishRequest.setTopic(topic);
        publishRequest.setData(requestData);
        return publishRequest;
    }
}
