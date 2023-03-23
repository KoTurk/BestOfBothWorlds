package nl.adesso.streaming.streams;

import example.avro.Account;
import example.avro.Balance;
import example.avro.Fraud;
import example.avro.Payment;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;

public class PaymentKStreams {

    public static KStream<Account, Payment> getPaymentKStream(StreamsBuilder builder) {
        KStream<Account, Payment> paymentKStream = builder.stream("payments");
        KTable<Account, Balance> balances = builder.table("balance");

        paymentKStream.peek((key,payment) -> {
            System.out.println("Checking balance");
        })
        .join(balances, PaymentKStreams::enhancedPayment)
        .filter((key, payment) -> payment.getBalance() >= payment.getAmount())
        .mapValues((key, payment) -> new Fraud(payment.getIban()))
        .to("fraud");

        return paymentKStream;
    }

    private static Payment enhancedPayment(Payment payment, Balance balance) {
        return new Payment(payment.getName(), payment.getIban(), payment.getToIban(), payment.getAmount(), balance.getAmount(), payment.getProcessed());
    }

}
