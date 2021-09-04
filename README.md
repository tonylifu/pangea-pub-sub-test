# pangea-pub-sub-test
Pangea Pub Sub Test 
The module pangea-sub runs two endpoints http://localhost:9000/test1 and http://localhost:9000/test2 as subscribing servers
The pangea-pub-sub-test runs two endpoints http://localhost:8000/subscribe/{topic} and http://localhost:8000/publish/{topic} for subscribing to a topic and publishing to a topic respectively.

Spring JMS (activemq) was used for message routing with default fallback to raw logic. The project could be improved to have dynamic topic change at JMSListener.

SUBSCRIBING
http://localhost:8000/subscribe/topic2
Request:
{
    "url": "http://localhost:9000/test2"
}
Response:
{
    "url": "http://localhost:9000/test2",
    "topic": "topic2"
}

PUBLISHING
http://localhost:8000/publish/topic2
Request:
{
    "msg": "Hello to Topic2 Subscribers"
}
Response: OK(200)
