package com.pangea.sub.test.controller;

import com.pangea.pub.test.dto.request.PublishRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SubscribeServerController {
    private static final Logger LOG = LoggerFactory.getLogger(SubscribeServerController.class);

    @RequestMapping(value = "/test1", method = RequestMethod.POST,
            produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.ALL_VALUE},
            consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.ALL_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> test1(@RequestBody String publishRequest) {
        LOG.info("\n\nPublished<TEST1> Request:\n{}\n\n", publishRequest);
        System.out.println(publishRequest);
        LOG.info("\n\nPublish<TEST1> Response:\n{}\n\n", HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/test2", method = RequestMethod.POST,
            produces=MediaType.ALL_VALUE, consumes=MediaType.ALL_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> test2(@RequestBody String publishRequest) {
        LOG.info("\n\nPublished<TEST2> Request:\n{}\n\n", publishRequest);
        System.out.println(publishRequest);
        LOG.info("\n\nPublish<TEST2> Response:\n{}\n\n", HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
