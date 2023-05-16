package nl.datastax.pulsar.service.producer;

import example.avro.Payment;
import lombok.RequiredArgsConstructor;
import org.apache.pulsar.client.api.PulsarClientException;

import org.springframework.pulsar.core.PulsarTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PulsarProducer {
    private final PulsarTemplate<String> pulsarTemplate;

    public boolean process(Payment payment) throws PulsarClientException {
       this.pulsarTemplate.send("payment");
        return true;
    }
}