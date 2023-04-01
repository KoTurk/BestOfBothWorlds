package nl.datastax.pulsar.service.producer;

import example.avro.Payment;
import nl.datastax.pulsar.config.PulsarConfig;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.api.Schema;
import org.apache.pulsar.common.schema.KeyValue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PulsarProducer {
    private final Producer<byte[]> producer;

    public PulsarProducer(Producer<byte[]> producer) {
        this.producer = producer;
    }

    public boolean processPayment(Payment message) throws PulsarClientException {
        producer.send("hello".getBytes());
        return true;
    }
}