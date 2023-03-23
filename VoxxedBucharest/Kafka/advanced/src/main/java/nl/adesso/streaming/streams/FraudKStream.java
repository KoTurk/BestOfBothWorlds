package nl.adesso.streaming.streams;

import example.avro.Account;
import example.avro.Fraud;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import nl.adesso.streaming.exception.FilterException;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Branched;
import org.apache.kafka.streams.kstream.KStream;

public class FraudKStream {

    public static KStream<Account, Fraud> getFraudKStream(StreamsBuilder builder) {
        KStream<Account, Fraud> fraudKStream = builder.stream("fraud");

        fraudKStream.peek((account, payment) -> {
            System.out.println("Checking Fraud");
        })
        .filter((account, payment) -> {
                    try {
                        return filterOrThrow(payment);
                    } catch (FilterException e) {
                        // create metrics here
                        return false;
                    }
                })
        .mapValues((account, payment) -> new Fraud(payment.getIban()))
        .peek((account, payment) -> {
            System.out.println("Got a Fraud, don't process");
        })
        .split()
        .branch((account, payment) -> false, Branched.withConsumer(ks -> ks.to("rewards")))
        .defaultBranch(Branched.withConsumer(ks -> ks.to("blacklist")));

        return fraudKStream;
    }

    private static boolean analytics(Fraud value) {
        return false;
    }

    private static boolean filterOrThrow(Fraud user) throws FilterException {
        if ("NL63DEUT332454654".equals(user.getIban())) {
            // I MADE A JAVA PROGRAM TO TELL ME MY PURPOSE
            // IT KEEPS SAYING NULLPOINTEREXCEPTION, SO IT WORKS GREAT

            // 5.10 throw exception
            throw new FilterException("oh no, why???");
        } else {
            return true;
        }
    }
}
