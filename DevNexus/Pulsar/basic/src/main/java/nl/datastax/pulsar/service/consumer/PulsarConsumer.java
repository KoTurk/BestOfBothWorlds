package nl.datastax.pulsar.service.consumer;

import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.PulsarClientException;
import org.springframework.stereotype.Component;

@Component
public class PulsarConsumer {
    private Consumer<byte[]> consumer;

    public PulsarConsumer(Consumer<byte[]> consumer) {
        this.consumer = consumer;
    }

    public void consume() throws PulsarClientException {
        while (true) {
            Message<byte[]> message = consumer.receive();
            // do something with message
            consumer.acknowledge(message);
        }
    }
}