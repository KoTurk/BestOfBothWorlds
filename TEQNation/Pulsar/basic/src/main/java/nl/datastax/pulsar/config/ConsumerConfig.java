package nl.datastax.pulsar.config;

import org.apache.pulsar.client.api.AuthenticationFactory;
import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsumerConfig {
    private PulsarClient client;
    private PulsarConfig config;

    public ConsumerConfig(PulsarClient client, PulsarConfig config) {
        this.client = client;
        this.config = config;
    }

    @Bean
    public Consumer<byte[]> createConsumer() throws PulsarClientException {
        return client.newConsumer()
                .topic(config.topicName)
                .subscriptionName(config.subscription)
                .subscribe();
    }
}