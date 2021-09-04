package com.pangea.pub.test.jms;

import com.pangea.pub.test.dto.request.PublishRequest;
import com.pangea.pub.test.util.AppConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class JMSService {
    private static final Logger LOG = LoggerFactory.getLogger(JMSService.class);

    private final String TOPIC_ONE = AppConstant.TOPIC_ONE;
    private final String TOPIC_TWO = AppConstant.TOPIC_TWO;
    private JmsTemplate jmsTemplate;

    private ApplicationContext applicationContext;

    @Autowired
    public JMSService(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        jmsTemplate = applicationContext.getBean(JmsTemplate.class);
    }


    public void jmsServiceSendToTopicOne(String topic, PublishRequest publishRequest){
//        DestionationTopic destination = new DestionationTopic(topic);
//        AppConstant.setDestination(destination);
        //JmsTemplate jmsTemplate = applicationContext.getBean(JmsTemplate.class);

        System.out.println("Sending jms message:::"+TOPIC_ONE);

        LOG.info("\n\n:::Sending JMS Message:::\n{}\n{}\n\n",TOPIC_ONE, publishRequest);
        jmsTemplate.convertAndSend(TOPIC_ONE, publishRequest);
        LOG.info("\n\n:::JMS Message Sent:::\n{}\n{}\n\n",TOPIC_ONE, publishRequest);
    }

    public void jmsServiceSendToTopicTwo(String topic, PublishRequest publishRequest){
//        DestionationTopic destination = new DestionationTopic(topic);
//        AppConstant.setDestination(destination);
        //JmsTemplate jmsTemplate = applicationContext.getBean(JmsTemplate.class);

        System.out.println("Sending jms message:::"+TOPIC_TWO);

        LOG.info("\n\n:::Sending JMS Message:::\n{}\n{}\n\n",TOPIC_TWO, publishRequest);
        jmsTemplate.convertAndSend(TOPIC_TWO, publishRequest);
        LOG.info("\n\n:::JMS Message Sent:::\n{}\n{}\n\n",TOPIC_TWO, publishRequest);
    }
}
