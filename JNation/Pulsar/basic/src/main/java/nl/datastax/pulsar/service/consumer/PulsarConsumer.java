package nl.datastax.pulsar.service.consumer;

import example.avro.Payment;
import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.common.schema.SchemaType;
import org.springframework.pulsar.annotation.PulsarListener;
import org.springframework.stereotype.Component;

import static org.apache.pulsar.client.api.SubscriptionType.Shared;

@Component
@Slf4j
public class PulsarConsumer {
    private String message;

    // 3.1 create kafka listener with topics payments and groupid something
    @PulsarListener(subscriptionName = "<subscription>", topics = "persistent://<tenant>/<namespace>/<topic>")
    public void getMessage(String message) {
        log.info("received payload= {}", message);
        setMessage(message);
    }

    public String getMessage() {
        return message;
    }

    private void setMessage(String message) {
        this.message = message;
    }
}