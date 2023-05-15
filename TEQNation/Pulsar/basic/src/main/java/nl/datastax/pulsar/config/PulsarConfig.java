package nl.datastax.pulsar.config;

import org.apache.pulsar.client.api.AuthenticationFactory;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PulsarConfig {
    @Value("${pulsar.broker.url}")
    private String brokerUrl;

    @Value("${pulsar.broker.token}")
    private String token;

    @Value("${pulsar.broker.topicName}")
    public String topicName;

    @Value("${pulsar.broker.subscription}")
    public String subscription;

    @Bean
    public PulsarClient pulsarClient() throws PulsarClientException {
        return PulsarClient.builder()
                .serviceUrl(brokerUrl)
                .authentication(
                        AuthenticationFactory.token(token)
                )
                .build();
    }
}