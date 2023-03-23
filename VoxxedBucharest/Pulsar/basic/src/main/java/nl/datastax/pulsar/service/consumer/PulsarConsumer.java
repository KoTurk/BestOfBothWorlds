package nl.datastax.pulsar.service.consumer;

import lombok.RequiredArgsConstructor;
import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PulsarConsumer {
    private PulsarClient pulsarClient;

    public void consume() throws PulsarClientException {
        try (Consumer<byte[]> consumer = createConsumer()) {
            while (true) {
                Message<byte[]> message = consumer.receive();
                // do something with message
                consumer.acknowledge(message);
            }
        }
    }

    private Consumer<byte[]> createConsumer() throws PulsarClientException {
        return pulsarClient.newConsumer()
                .topic("payments")
                .subscriptionName("payment-subscription")
                .subscribe();
    }
}
