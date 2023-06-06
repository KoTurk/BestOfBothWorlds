package nl.datastax.pulsar.function;

import org.apache.pulsar.functions.api.Context;
import org.apache.pulsar.functions.api.Function;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SendPaymentFunction implements Function<String, String> {
    private static Logger log = LoggerFactory.getLogger(SendPaymentFunction.class);

    public String process(String input, Context context) {
        log.info("Payment send to systemB");
        return "payment send";
    }
}