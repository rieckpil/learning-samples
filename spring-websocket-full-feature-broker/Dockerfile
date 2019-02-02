FROM rabbitmq:3.7-management
RUN rabbitmq-plugins enable --offline rabbitmq_mqtt rabbitmq_federation_management rabbitmq_stomp
# docker run -p 5672:5672 -p 15672:15672 -p 61613:61613 myrabbitmq