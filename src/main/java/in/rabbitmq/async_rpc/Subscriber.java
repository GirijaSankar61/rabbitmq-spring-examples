package in.rabbitmq.async_rpc;

import in.rabbitmq.SampleRequestMessage;
import in.rabbitmq.SampleResponseMessage;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.messaging.handler.annotation.Payload;

public class Subscriber {

    @RabbitHandler
    @RabbitListener(
                    bindings = {
                                    @QueueBinding(value = @Queue("${queue.request}"),
                                                    key = "${routingKey.request}",
                                                    exchange = @Exchange(value = "${exchange.direct}", type = ExchangeTypes.DIRECT, durable = "true"))})
    public SampleResponseMessage subscribeToQueueRed(@Payload SampleRequestMessage sampleRequestMessage, Message message) {
        System.out.println("Received message :" + message);
        return new SampleResponseMessage(sampleRequestMessage.getMessage());
    }
}