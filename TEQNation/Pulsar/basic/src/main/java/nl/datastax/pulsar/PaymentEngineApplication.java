package nl.datastax.pulsar;

import org.apache.pulsar.client.api.AuthenticationFactory;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PaymentEngineApplication {
    public static void main(String[] args) {
        SpringApplication.run(PaymentEngineApplication.class, args);
    }

    @Bean
    public PulsarClient pulsarClient() throws PulsarClientException {
        return PulsarClient.builder()
                .serviceUrl("<service-url>")
                .authentication(
                        AuthenticationFactory.token("<token>")
                )
                .build();
    }
}