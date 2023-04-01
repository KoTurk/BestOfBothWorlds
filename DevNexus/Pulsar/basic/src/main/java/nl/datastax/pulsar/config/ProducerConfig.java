package nl.datastax.pulsar.config;

import example.avro.Payment;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.api.Schema;
import org.apache.pulsar.common.schema.KeyValue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProducerConfig {
    private PulsarClient client;
    private PulsarConfig config;

    public ProducerConfig(PulsarClient client, PulsarConfig config) {
        this.client = client;
        this.config = config;
    }

    @Bean
    public Producer<byte[]> createProducer() throws PulsarClientException {
//        return pulsarClient.newProducer(Schema.AVRO(Payment.class))
//                .topic(topicName)
//                .create();
        return client.newProducer()
                .topic(config.topicName)
                .create();
    }

    private Producer<KeyValue<Payment, Payment>> createProducer2() throws PulsarClientException {
        return client.newProducer(Schema.KeyValue(Payment.class, Payment.class))
                .topic(config.topicName)
                .create();
    }
}