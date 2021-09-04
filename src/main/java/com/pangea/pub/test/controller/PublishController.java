package com.pangea.pub.test.controller;

import com.pangea.pub.test.dto.request.PublishRequest;
import com.pangea.pub.test.jms.JMSService;
import com.pangea.pub.test.service.PublishService;
import com.pangea.pub.test.util.AppConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/publish")
public class PublishController {
    private static final Logger LOG = LoggerFactory.getLogger(PublishController.class);

    private PublishService publishService;
    private JMSService jmsService;

    @Autowired
    public PublishController(@Qualifier("publishServiceImpl") PublishService publishService, JMSService jmsService) {
        this.publishService = publishService;
        this.jmsService = jmsService;
    }

    @RequestMapping(value = "/{topic}", method = RequestMethod.POST,
            produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.ALL_VALUE},
            consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.ALL_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> publish(@PathVariable String topic, @RequestBody Object requestData) {
        LOG.info("\n\nPublish RequestData:\n{}\n\n", requestData);
        PublishRequest publishRequest = getPublishRequest(topic, requestData);
        LOG.info("\n\nPublish Request:\n{}\n\n", publishRequest);
        publishToTopic(topic, publishRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void publishToTopic(String topic, PublishRequest publishRequest) {
        switch (topic){
            case AppConstant.TOPIC_ONE:
                jmsService.jmsServiceSendToTopicOne(topic, publishRequest);
                break;
            case AppConstant.TOPIC_TWO:
                jmsService.jmsServiceSendToTopicTwo(topic, publishRequest);
                break;
            default:
                publishService.publishToTopic(topic, publishRequest);
        }
    }

    private PublishRequest getPublishRequest(String topic, Object requestData) {
        PublishRequest publishRequest = new PublishRequest();
        publishRequest.setTopic(topic);
        publishRequest.setData(requestData);
        return publishRequest;
    }
}
