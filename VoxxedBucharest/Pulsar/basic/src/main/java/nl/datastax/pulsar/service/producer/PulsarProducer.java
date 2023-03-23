package nl.datastax.pulsar.service.producer;

import example.avro.Payment;
import lombok.RequiredArgsConstructor;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.api.Schema;
import org.apache.pulsar.common.schema.KeyValue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PulsarProducer {
    private PulsarClient pulsarClient;

    @Value("${pulsar.broker.topicName}")
    private String topicName;

    // no key / value? you don't have join problems
    // can talk AVRO
    public boolean processPayment(Payment message) throws PulsarClientException {
        try (Producer<Payment> producer = createProducer()) {
            producer.send(message);
        }
        return true;
    }

    private Producer<Payment> createProducer() throws PulsarClientException {
        return pulsarClient.newProducer(Schema.AVRO(Payment.class))
                .topic(topicName)
                .create();

    }

    private Producer<KeyValue<Payment, Payment>> createProducer2() throws PulsarClientException {
        return pulsarClient.newProducer(Schema.KeyValue(Payment.class, Payment.class))
                .topic(topicName)
                .create();
    }
}