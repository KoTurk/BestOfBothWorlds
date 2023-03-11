package nl.datastax.pulsar.function;

import org.apache.pulsar.functions.api.Context;
import org.apache.pulsar.functions.api.Function;

public class SendPaymentFunction implements Function<String, String> {
    public String process(String input, Context context) {
        return "Send amount to systemB";
    }
}