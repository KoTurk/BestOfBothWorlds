package nl.datastax.pulsar.controller;

import lombok.RequiredArgsConstructor;
import example.avro.Payment;

import nl.datastax.pulsar.service.producer.PulsarProducer;
import org.apache.pulsar.client.api.PulsarClientException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PaymentController {
    private final PulsarProducer paymentProducer;

    @PostMapping
    public ResponseEntity<String> processPayment(@RequestBody Payment payment) throws PulsarClientException {
        if(paymentProducer.processPayment(payment)) {
            return ResponseEntity.ok("Created payment");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
