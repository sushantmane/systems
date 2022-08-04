package edu.sjsu.cs.systems.kafka.producer;


import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

public class ProducerResultCallback implements Callback {

    private final ProducerRecord<String, String> producerRecord;

    public ProducerResultCallback(ProducerRecord producerRecord) {
        this.producerRecord = producerRecord;
    }

    @Override
    public void onCompletion(RecordMetadata rmd, Exception e) {
        if (e != null) {
            e.printStackTrace();
            return;
        }
        System.out.println("Ack - key:" + producerRecord.key() + " ts:" + rmd.timestamp() + " partId:" + rmd.partition() + " offset:" + rmd.offset());
    }
}
