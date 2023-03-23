package nl.datastax.pulsar.service.producer;

import example.avro.Payment;
import lombok.RequiredArgsConstructor;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.api.Schema;
import org.apache.pulsar.common.schema.KeyValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PulsarProducer {

    @Autowired
    private PulsarClient pulsarClient;

    @Value("${pulsar.broker.topicName}")
    private String topicName;

    // no key / value? you don't have join problems
    // can talk AVRO
    public boolean processPayment(Payment message) throws PulsarClientException {
        try (Producer<byte[]> producer = createProducer()) {
            producer.send("hello".getBytes());
        }
        return true;
    }

    private Producer<byte[]> createProducer() throws PulsarClientException {
//        return pulsarClient.newProducer(Schema.AVRO(Payment.class))
//                .topic(topicName)
//                .create();
        return  pulsarClient.newProducer()
                .topic("persistent://world/pulsar/payments")
                .create();

        // Send a message to the topic
      //  producer.send("Hello World".getBytes());
    }

    private Producer<KeyValue<Payment, Payment>> createProducer2() throws PulsarClientException {
        return pulsarClient.newProducer(Schema.KeyValue(Payment.class, Payment.class))
                .topic(topicName)
                .create();
    }
}